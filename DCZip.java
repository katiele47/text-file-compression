package lab08.compression;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Perform compression and expansion of text files.
 * 
 * @author Grant Braught
 * @author Dickinson College
 * @version Nov 5, 2016
 * 
 * 
 */
public class DCZip {
 
	
	/**
	 * Compress the specified text file into a binary file with the same name
	 * with the extension ".dczip" appended to it.
	 * 
	 * The compressed file should begin with an integer indicating the number of
	 * words in the key. This integer should be followed by each of the words in
	 * the key, in the order they will be used to generate the codes for the
	 * words (i.e. from most frequent to least frequent). The words of the key
	 * will be followed by the codes for each word in the text as byte or short
	 * values depending upon the word. Byte values are positive and short values
	 * are negative, so that they can be differentiated. New lines in the text
	 * are indicated by a code value 0.
	 * 
	 * @param inFile
	 *            the path to the file to be compressed.
	 */
	public static void compress(String inFile) {
		System.out.println("Compressing: " + inFile + " into " + inFile + ".dczip");

		DataOutputStream dos = null;
		Scanner scr = null;
		Scanner scr1 = null;
		Scanner scr2 = null;
		WordList wl = new WordList();
		
		try{
			dos = new DataOutputStream(new FileOutputStream(inFile+".dczip", false));
			scr = new Scanner(new FileInputStream(inFile));
			scr1 = new Scanner(new FileInputStream(inFile));
			scr2 = new Scanner(new FileInputStream(inFile));
		
			//Read each single word on text file and add new words into the array list
			while(scr.hasNextLine()) {
				String line = scr.nextLine();
				String[] words = line.split(" ", -1); 
				for (int i = 0; i<words.length; i++) { 
					wl.addWord(words[i]);  
				} 
			}
					
			//Sort WordIntPair objects by their frequency
			wl.sortByFrequency();
			
			//write the number of words in the key
			int numWord = wl.size();
			dos.writeInt(numWord);
			
			//write each of the words in the key on the binary file
			for(int i = 0; i< wl.size(); i++) {
				dos.writeUTF(wl.getWord(i));
			}
			
			//encode: check the words in the text file --> matching numbers i+1,
			
			while(scr1.hasNextLine()) {
		
				String line1 = scr1.nextLine();
				String[] words1 = line1.split(" ", -1); 
				
				if(numWord < 128) {
				
					for (int j = 0; j<words1.length; j++) {
						for(int i = 0; i<wl.size(); i++) {
							if(words1[j].equals(wl.getWord(i))){
								dos.writeByte(i+1);	
							}
						}		
					}
					if (scr1.hasNextLine()) {
						dos.writeByte(0);
					}
				}
				
				else {
					
					for (int j = 0; j<words1.length; j++) {
						for(int i = 0; i<127; i++) {
							if(words1[j].equals(wl.getWord(i))){
								dos.writeByte(i+1);	
							}
						}
					
						for(int d = 127; d<wl.size(); d++) {
							if(words1[j].equals(wl.getWord(d))) {
								dos.writeShort(-1*(d+1));
							}
						}
						
					}
					if (scr1.hasNextLine()) {	
						dos.writeByte(0);
					}
				}
			
			}
		}
		catch (FileNotFoundException e) {
			System.out.println("Unable to open file: " + inFile);
		}
		catch(IOException e) {
			System.out.println("Error reading file: " + inFile);
		}	
		finally {
			if (dos != null) {
				try {
					dos.close();
					
				} catch (IOException e) {
					System.out.println("Error closing file: " + inFile);
				}
			}
			if (scr != null) {
				scr.close();
			}
			if (scr1 != null) {
				scr1.close();
			}
		}	
		System.out.println("Done.");
	}
	/**
	 * Expand the specified compressed binary file back into a text file with
	 * the specified name.
	 * 
	 * @param inFile
	 *            the path to the compressed binary file to be expanded.
	 * @param outFile
	 *            the path for the expanded text file.
	 */
	public static void expand(String inFile, String outFile) {
		System.out.println("Expanding: " + inFile + " into " + outFile);

		DataInputStream dis = null; 
		DataInputStream dis1 = null; 
		PrintWriter pw = null;
		WordList wll = new WordList();
		
		try {		
			dis = new DataInputStream(new FileInputStream(inFile));
			dis1 = new DataInputStream(new FileInputStream(inFile));
			pw = new PrintWriter(new FileOutputStream(outFile, false));
		
			int numWord = dis.readInt();
			
			for (int i = 0; i< numWord; i++) {
				String word = dis.readUTF();
				wll.addWord(word);
			}
			
			int count = 0;
			for(int i = 0; i<wll.size(); i++) {
				String word = wll.getWord(i);
				
				for(int j = 0; j<word.length(); j++) {
					if(word.charAt(j) != ' ') {
						count++;
					} 
				}
			}
			
			dis1.skip(4 + count + numWord*2);
			
			while(dis1.available()>0) {
				
				byte byteNum = dis1.readByte();		
				
				if (byteNum < 0) {
					byte nextByteNum = dis1.readByte();
					short shortNum = makeShort(byteNum, nextByteNum);
					for (int i = 127; i<wll.size(); i++) {
						if((shortNum*-1)-1 == i) {
							String p = wll.getWord(i);
							pw.print(p);
							pw.print(" ");
						}
					}
				}
				else if(byteNum == 0) {
					pw.println(" ");
				}
				else {
					for(int i = 0; i<wll.size(); i++) {
						if(byteNum-1 == i) {
							String p = wll.getWord(i);
							pw.print(p);
							pw.print(" ");
						}
					}
				} 
			}
	
		} 
		
		catch (FileNotFoundException e) {
			System.out.println("Unable to open file: " + inFile);
		}
		catch(IOException e) {
			System.out.println("Error reading file: " + inFile);
		}	
		finally {
			if (dis != null) {
				try {
					dis.close();
				} catch (IOException e) {
					System.out.println("Error closing file: " + inFile);
				}
			}
			if (dis1 != null) {
				try {
					dis1.close();
				} catch (IOException e) {
					System.out.println("Error closing file: " + inFile);
				}
			}
			if(pw != null) {
				pw.close();
			}
		}
		System.out.println("Done.");
	}

	/**
	 * Turn two bytes into a short. For example:
	 * 
	 * <pre>
	 * if b1 = 0011 1101 and b2 = 1011 0110
	 * then the short value returned will be
	 * 0011 1101 1011 0110
	 * </pre>
	 * 
	 * @param b1
	 *            the most significant byte.
	 * @param b2
	 *            the least significant byte.
	 * @return
	 */
	private static short makeShort(byte b1, byte b2) {
		short s1 = (short) ((0x00FF & b1) << 8);
		short s2 = (short) (0x00FF & b2);

		short val = (short) (s1 | s2);

		return val;
	}

	/**
	 * Process commands entered by the user for compressing or expanding files.
	 * 
	 * @param args none
	 */
	public static void main(String[] args) {
		String path = "src/lab08/compression/documents/";

		Scanner scr = new Scanner(System.in);

		System.out.print("Enter Command: ");
		String cmd = scr.nextLine();
		String[] tokens = cmd.split(" ");

		if (tokens[0].equals("compress") && tokens.length == 2) {
			compress(path + tokens[1]);
		} else if (tokens[0].equals("expand") && tokens.length == 3) {
			expand(path + tokens[1], path + tokens[2]);
		} else {
			System.out.println("Unrecognized command: " + cmd);
		}

		scr.close();
	}
}

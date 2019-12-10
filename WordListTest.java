package lab08.compression;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class WordListTest {

	private WordList wl;

	@Before
	public void setUp() throws Exception {
		wl = new WordList();
	}	
	
	@Test
	public void testConstructor() {
		assertEquals("Incorrect array size", 0, wl.size());
	}

	@Test
	public void testAddWordNewWord() {
		wl.addWord("BB");
		assertEquals("This word should be added", "BB", wl.getWord(0));
	}
	
	@Test
	public void testAddWordExistedWord() {
		wl.addWord("BB");
		wl.addWord("BB");
		assertEquals("Word added twice should be of the same object", 1, wl.size());
		assertEquals("wrong word", "BB", wl.getWord(0)); 
	}
	
	@Test
	public void testGetWord() {
		wl.addWord("Ooo");
		wl.addWord("Aaah");
		assertEquals("Wrong word", "Ooo", wl.getWord(0));
		assertEquals("Wrong word", "Aaah", wl.getWord(1));
	}
	@Test
	public void testGetIndex() {
		wl.addWord("A");
		wl.addWord("B");
		assertEquals("Wrong index", 0, wl.getIndex("A"));
		assertEquals("Wrong index", 1, wl.getIndex("B"));
	}
	
	@Test
	public void testSortByFrequency() {
		wl.addWord("B");//3 times
		wl.addWord("A");//2 times
		wl.addWord("V");//1 times (first)
		wl.addWord("B");
		wl.addWord("B");
		wl.addWord("C");//1 times (second)
		wl.addWord("A");	
		wl.sortByFrequency();
		assertEquals("Wrong index for the most frequent word", 0, wl.getIndex("B"));
		assertEquals("Wrong index for the less frequent word", 1, wl.getIndex("A"));
		assertEquals("Wrong index for the least frequent word", 2, wl.getIndex("V"));
		assertEquals("Wrong index for the least frequent word", 3, wl.getIndex("C"));
	}
	
	
	
	
}

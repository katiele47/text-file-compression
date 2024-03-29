package lab08.compression;


/**
 * A word with an associated integer. The integer can be used to represent
 * different things. Initially this will be used to represent the number of
 * times that the word appeared in the text being compressed. But it can also be
 * used for other purposes (see Bonus). This class implements the Comparable
 * interface so that a list of WordIntPair objects can be sorted into decreasing
 * order by the int value.
 * 
 * @author Grant Braught
 * @author Dickinson College
 * @version Nov 5, 2016
 */
public class WordIntPair implements Comparable<WordIntPair> {

	private String word;
	private int wordCount;
	
	
	/**
	 * Construct a new word.
	 * 
	 * @param word
	 *            the word.
	 */
	public WordIntPair(String word) {
			this.word = word;
	} 
	

	/**
	 * Get the word.
	 * 
	 * @return the word.
	 */
	public String getWord() {
		return word;
	}

	/**
	 * Get the int associated with this word.
	 * 
	 * @return the int value associated with this word.
	 */
	public int getIntValue() {
		return wordCount;
	}

	/**
	 * Set the int value associated with this word.
	 * 
	 * @param newValue
	 *            the new value associated with this word.
	 */
	public void setValue(int newValue) {
		wordCount = newValue;
	}

	/**
	 * Compare this WordIntPairs to one another using their int values.
	 * WordIntPairs having higher int values come before those with lower
	 * values. WordIntPairs with equal values can appear in either order.
	 * 
	 * For more information see the Java JDK documentation for the Comparable
	 * interface, which is implemented by this class.
	 * 
	 * @param w
	 *            the WordIntPair to compare to this one.
	 * @return a negative value if this word comes before w, a positive value if
	 *         this word comes after w, zero if this word and w can appear in
	 *         either order.
	 */
	public int compareTo(WordIntPair w) {
		if (this.getIntValue() > w.getIntValue()) {
			return -1;
		}
		else if (this.getIntValue() < w.getIntValue()) {
			return 1;
		}
		else { 
			return 0; 
		}
	}
	public boolean equals(Object other) {
		WordIntPair otherWip = ((WordIntPair)other);
		return this.word.equals(otherWip.word); 
	}
}
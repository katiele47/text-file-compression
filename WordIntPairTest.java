package lab08.compression;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class WordIntPairTest {

	private WordIntPair wip1;
	private WordIntPair wip2;
	
	@Before
	public void setUp() throws Exception {
		wip1 = new WordIntPair("Hello");
		wip2 = new WordIntPair("days");
	}
	
	@Test 
	public void testConstructor() {	
		assertEquals("incorrect word", "Hello", wip1.getWord());
		wip1.setValue(5);
		assertEquals("wrong value", 5, wip1.getIntValue());
	}
	@Test
	public void testComeBeforeW() {
		wip1.setValue(5);
		wip2.setValue(6); 	
		assertEquals("wrong value", 1, wip1.compareTo(wip2));
	}
	@Test
	public void testComeAfterW() {
		wip1.setValue(6);
		wip2.setValue(2); 	
		assertEquals("wrong value", -1, wip1.compareTo(wip2));
	}
	@Test
	public void testEitherOrderW() {
		wip1.setValue(5);
		wip2.setValue(5); 	
		assertEquals("wrong value", 0, wip1.compareTo(wip2));
	}
	 

}

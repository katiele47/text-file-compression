package lab09.list;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.*;

public class CS132ArrayListTest {

	/*
	 * The myList field can refer to any object that implements the CS132List
	 * interface (e.g. a CS132ArrayList or a CS132LinkedList). In this class it
	 * is set in the setUp method to refer to a CS132ArrayList.
	 * 
	 * If you use CS132List referred to my myList in all of your tests in this
	 * class and also use only the methods in the CS132List interface, then the
	 * CS132SinglyLinkedListTest class that you are given will run all of your
	 * tests against your CS132LinkedList class as well. Thus, you will only
	 * need to create one set of tests!
	 * 
	 * Be sure to look at the CS132SinglyLinkedListTest class to see how this
	 * works! Its pretty clever!
	 */
	protected CS132List myList;

	Object i1;
	Object i2;
	Object i3;
	Object i4;
	String s1;
	String s2;
	
	@Before
	public void setUp() throws Exception {
		myList = new CS132ArrayList();
		i1 = "C";
		i2 = "D";
		i3 = "E";
		i4 = "F";
		s1 = "A";
		s2 = "B";
	}

	//for both
	@Test
	public void testAddEmptyArray() {
		myList.add(i1);
		assertEquals("i1 is not added yet", i1, myList.get(0));
		assertEquals("Wrong size", 1, myList.size());
	}
	
	//for both
	@Test 
	public void testAddAvailableArray() {
		myList.add(i1);
		myList.add(s1);
		assertEquals("i1 is not added yet", i1, myList.get(0));
		assertEquals("s1 is not added yet", s1, myList.get(1));
		assertEquals("Wrong size", 2, myList.size());
	} 
	
	//for ArrayList
	@Test
	public void testAddFullArray() {
		myList.add(i1); 
		myList.add(i2);
		myList.add(i3);
		myList.add(s1);
		myList.add(i4);
		assertEquals("i4 is not added yet", i4, myList.get(4));
		assertEquals("Wrong size", 5, myList.size());
	} 
	
	//for both
	@Test
	public void testSize() {
		assertEquals("List should be empty", 0, myList.size());
		myList.add(i1);
		myList.add(i2);
		assertEquals("Wrong size", 2, myList.size());
	} 
	 
	//for both
	@Test
	public void testGetInvalidIndex() {
		
		try {
			myList.add(i1);
			myList.get(-1);
			myList.get(5);
			fail("Expected exception was not thrown");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		catch (Exception e) {
			fail("Incorrect exception was caught");
		}	
	}  
	
	//for both
	@Test
	public void testGetValidIndex() {	
		myList.add(i1);
		myList.add(i2);
		myList.add(i3);
		assertEquals("Wrong element", i1, myList.get(0));	 //get the first one
		assertEquals("Wrong element", i3, myList.get(2));	// get the last one
	}
	
	//for both
	@Test
	public void testSetInvalidIndex() {
		try {
			myList.add(i1);
			myList.add(i2);
			myList.set(7, i3);
			myList.set(-5, i3);
			fail("Expected exception was not thrown");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		catch (Exception e) {
			fail("Incorrect exception was caught");
		}	 
		
	} 
	
	//for both
	@Test
	public void testSetValidIndex() {
		myList.add(i1);
		myList.add(s2);
		myList.add(s1);
		
		myList.set(1, i3);
		myList.set(0, i4); //set the first one
		myList.set(2, s2); //set the last one
		
		assertEquals("Wrong element", i3, myList.get(1));	
		assertEquals("Wrong element", i4, myList.get(0));	
		assertEquals("Wrong element", s2, myList.get(2));	
	}   
	
	//for both
	@Test
	public void testInsertInvalidIndex() {
		//index < 0
		try {
			myList.add(i1);
			myList.add(i2);
			myList.insert(-5, i4);
			fail("Expected exception was not thrown");
		} 
		catch (IndexOutOfBoundsException e) {
			
		}
		catch (Exception e) {
			fail("Incorrect exception was caught");
		}
		
		//index = size
		try {
			myList.add(i1);
			myList.add(i2);
			myList.insert(7, i3); 

			fail("Expected exception was not thrown");
		} 
		catch (IndexOutOfBoundsException e) {
			
		}
		catch (Exception e) {
			fail("Incorrect exception was caught");
		}	 
	} 
	
	//for both
	@Test
	public void testInsertAvailableList() {
		myList.add(i1);
		myList.add(i2);
		myList.add(i3);
		
		myList.insert(1, s1);  
		
		assertEquals("s1 was not inserted", s1, myList.get(1)); 
		assertEquals("i2 should be moved to index 2", i2, myList.get(2));   
		assertEquals("i3 should be moved to index 3", i3, myList.get(3));  
		assertEquals("Wrong size", 4, myList.size()); 
	}
	
	//for ArrayList
	@Test
	public void testInsertFullList() { 
		myList.add(i1);//0
		myList.add(i2);//1 -> 2
		myList.add(i3);//2 -> 3
		myList.add(i4);//3 -> 4
		
		myList.insert(1, s1); //1
		
		assertEquals("s1 was not inserted", s1, myList.get(1)); 
		assertEquals("i2 should be moved to index 2", i2, myList.get(2));   
		assertEquals("i3 should be moved to index 3", i3, myList.get(3));
		assertEquals("i4 should be moved to index 4", i4, myList.get(4));
		assertEquals("Wrong size", 5, myList.size()); 
	}
	
	//for LinkedList
	@Test
	public void testInsertAtStart() {
		myList.add(i1);
		myList.add(i2);		
		myList.add(i3);
		myList.add(i4);
	
		myList.insert(0, s1);
		assertEquals("Wrong element", s1, myList.get(0)); 
		assertEquals("Wrong element", i1, myList.get(1)); 
		assertEquals("Wrong element", i2, myList.get(2)); 
		assertEquals("Wrong element", i3, myList.get(3)); 
		assertEquals("Wrong element", i4, myList.get(4)); 
		assertEquals("Wrong size", 5, myList.size()); 
	}
	
	//for both
	@Test
	public void testInsertAtSecondLast() {
		myList.add(i1);
		myList.add(i2);
		myList.add(i3);
		myList.add(i4);//3
		
		myList.insert(3, s1);
		
		assertEquals("Wrong element", i1, myList.get(0)); 
		assertEquals("Wrong element", i2, myList.get(1)); 
		assertEquals("Wrong element", i3, myList.get(2)); 
		assertEquals("Wrong element", s1, myList.get(3)); 
		assertEquals("Wrong element", i4, myList.get(4)); 
		assertEquals("Wrong size", 5, myList.size());  
	}
	
	//for both
	@Test
	public void testRemoveInvalidIndex() {
		
		//index = size/length
		try {
			myList.add(i1);
			myList.add(i2);
			myList.remove(7);

			fail("Expected exception was not thrown");
		} 
		catch (IndexOutOfBoundsException e) {
			
		}
		catch (Exception e) {
			fail("Incorrect exception was caught");
		}
		
		//index < 0
		try {
			myList.add(i1);
			myList.add(i2);
			myList.remove(-9); 

			fail("Expected exception was not thrown");
		} 
		catch (IndexOutOfBoundsException e) {
			
		}
		catch (Exception e) {
			fail("Incorrect exception was caught");
		}
		
	}
	
	//for LinkedList
	@Test
	public void testRemoveFirstElement() {
		
		myList.add(i1);//0
		myList.add(i2);//1
		myList.add(i3);//2
		myList.add(i4);//3
			
		myList.remove(0);
		assertEquals("Wrong element", i2, myList.get(0));
		assertEquals("Wrong element", i3, myList.get(1));
		assertEquals("Wrong element", i4, myList.get(2));
		assertEquals("Wrong size", 3, myList.size());
	}
	
	//for LinkedList
	@Test
	public void testRemoveLasttElement() {
		
		myList.add(i1);
		myList.add(i2);
		myList.add(i3);
		myList.add(i4);
			
		myList.remove(3);
		assertEquals("Wrong element", i1, myList.get(0));
		assertEquals("Wrong element", i2, myList.get(1));
		assertEquals("Wrong element", i3, myList.get(2));
		assertEquals("Wrong size", 3, myList.size());

	}
	//for both
	@Test
	public void testRemove() {
		
		myList.add(i1);//0
		myList.add(i2);//1
		myList.add(i3);//2
		myList.add(i4);//3
		
		myList.remove(2);
		assertEquals("Wrong element", i1, myList.get(0));
		assertEquals("Wrong element", i2, myList.get(1));
		assertEquals("Wrong element", i4, myList.get(2));
		assertEquals("Wrong size", 3, myList.size());
	
	}
}

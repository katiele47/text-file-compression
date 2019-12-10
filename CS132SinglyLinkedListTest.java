package lab09.list;

import org.junit.Before;

public class CS132SinglyLinkedListTest extends CS132ArrayListTest {

    /*
     * This class extends CS132ArrayListTest so it inherits all of the tests in
     * that class. It also overrides the setUp method and sets myList to refer
     * to a CS132LinkedList instead of a CS132ArrayList.  
     * 
     * Thus, if all of the tests in CS132ArrayListTest use the myList
     * field and use only methods in the List interface, then this class will
     * run all of those tests using your CS132SinglyLinkedList. Thus, if
     * you have thoroughly tested your CS132ArrayList class then there is no
     * need to write any additional tests for the CS132LinkedList!
     * 
     * Pretty clever!
     */

    @Before
    public void setUp() throws Exception {
        myList = new CS132SinglyLinkedList(); 
 
    } 
    
    
    
    
}

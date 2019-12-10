package lab09.list;

/**
 * An implementation of the List interface using a singly linked list of
 * Objects.
 * 
 * @author Khanh Le & Hana Zherka
 * @version December 9th, 2019
 */
public class CS132SinglyLinkedList implements CS132List {

	private int size;
	private SinglyLinkedNode head;
	private SinglyLinkedNode tail;
    /**
     * Construct a new empty CS132SinglyLinkedList.
     */
    public CS132SinglyLinkedList() {
      
    	//initialize head?
    	head = null;
    	tail = null;
    	size = 0; 	
    }
    /*
     * Structure used to represent a node in the linked list.
     */
    private static class SinglyLinkedNode {
       
    	public SinglyLinkedNode next;
        public Object element;

        public SinglyLinkedNode(Object element) {
            this.element = element;
            next = null; 
        }
    }

	@Override
	public int size() {	 
		return size;
	}

	//go to the last element and append an element
	@Override
	public void add(Object element) {

		SinglyLinkedNode toAdd = new SinglyLinkedNode(element);
		
		if(size == 0) {	
			head = toAdd;
			tail = toAdd;
		}	
		else {
			
			tail.next = toAdd;
			tail = tail.next;
		}
		size = size + 1;
	}

	@Override
	public Object get(int index) throws IndexOutOfBoundsException {
		 
		if(index >= size || index < 0) {
			throw new IndexOutOfBoundsException("Invalid index");
		}
		
		else {
				//get the last node
				if(index == size - 1) {
					return tail.element;
				}
				else {
					SinglyLinkedNode cur = head; 
			        /*
			         * Traverse down the list until we reach the node specified by index.
			         * Note that if index == 0, cur will not change.
			         */
			        for (int i = 0; i < index; i++) {
			            cur = cur.next;
		        
			        }
			        return cur.element; 
	       
				}	 
		}
	}
	
	@Override
	public void set(int index, Object element) throws IndexOutOfBoundsException {
		
		if(index >= size || index < 0) {
			throw new IndexOutOfBoundsException("Invalid index");
		}
		else {
			
			//last element 
			if(index == size - 1) {
				tail.element = element;
			}
			else {
				SinglyLinkedNode cur = head;
				
				for(int i = 0; i < index; i++) {
					cur = cur.next;
				}
				cur.element = element; //if index = 0, head will auto = toSet
				//size does not change
				
			}
		}		
	}

	@Override
	public void insert(int index, Object element) throws IndexOutOfBoundsException {
		
		if (index >= size || index < 0) {
			throw new IndexOutOfBoundsException("Invalid index");
		}
		else {
			
			SinglyLinkedNode cur = head;
			SinglyLinkedNode toInsert = new SinglyLinkedNode(element);
			//SinglyLinkedNode pred = null; 
			
			//insert at Start
			if(index == 0) {
				toInsert.next = head;
				head = toInsert;
			}
			//insert at random index
			else {
				//go to the element preceding the element at the index we want to insert to
				for(int i = 0; i<index-1; i++) {
					//pred = cur;
					cur = cur.next;
					
				}
				toInsert.next = cur.next; //cur.next
				cur.next = toInsert;		
			}
			size = size + 1;	 
		}	 
		
	}

	@Override
	public Object remove(int index) throws IndexOutOfBoundsException {
		
		if (index >= size || index < 0) {
			throw new IndexOutOfBoundsException("Invalid index");
		}
		else {	
			SinglyLinkedNode toRemove = null;
			
			//remove the first node
			if(index == 0) {
				
				toRemove = head;
				
				head = head.next;
				toRemove.next = null; 
			}
	
			else { //work for a random and the last node

		    	 SinglyLinkedNode cur = head; 
		    	 SinglyLinkedNode pred = null;
				
		    	 //go to the preceding element of the element we want to remove
		    	 for(int i = 0; i<index-1; i++) {
		    		 cur = cur.next;
		    		 pred = cur;
		    	 }
		    	 toRemove = pred.next; 
		    	
		    	 if (toRemove == tail) {
		    		 pred.next =  null;
		    		 tail = pred;
		    	 }
		    	 else {
		    		 pred.next = toRemove.next;
		    	 }
			}			
			size = size - 1;
			return toRemove.element; 
		}	
	}
}
//---ask prof: can we set/insert an object for a null node following an existing node?
//----ask prof: how can cur not refer to head.next?


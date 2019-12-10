package lab09.list;

/**
 * An implementation of the CS132List interface using an array of Objects.
 * 
 * @author 
 * @version 
 */
public class CS132ArrayList implements CS132List {

    private int size;
    private Object[] elems;
    
    /**
     * Construct a new empty CS132ArrayList.
     */
    public CS132ArrayList() {
    	elems = new Object[10];
    	size = 0;
    }

	@Override
	public int size() {
		return size;
	}

	@Override
	public void add(Object element) {
			if(size!=elems.length) {
				elems[size]=element;
			}else {
				full();
				elems[size] = element;
			}
			size=size+1;
		}
		
	
	public void full() {
		Object[] newList = new Object[elems.length*2];
		for(int i=0; i<elems.length; i++) {
			newList[i]= elems[i];
		}
		elems = newList;
	}

	@Override
	public Object get(int index) throws IndexOutOfBoundsException {
		if(index >= elems.length||index<0) {
			throw new IndexOutOfBoundsException();
		} else {
			return elems[index];
		}
	}

	@Override
	public void set(int index, Object element) throws IndexOutOfBoundsException {
		if(index >= elems.length||index<0) {
			throw new IndexOutOfBoundsException();
		} else {
			elems[index] = element;
		}	
	}

	@Override
	public void insert(int index, Object element) throws IndexOutOfBoundsException {
		Object[] newList= new Object[size];
		for(int i=0;i<index;i++) {
			newList[i]=elems[i];
		}
		//available array
		if(size+1<elems.length) {
			for(int i=index+1;i<size;i++) {
				newList[i]=elems[i-1];
			}
			size = size+1;
		
		//full array
		}else if(size+1>=elems.length) {
			full();
			for(int i=index+1;i<size;i++) {
					newList[i]=elems[i-1];
			}
			size = size+1;

		//invalid index	
		}else{
				throw new IndexOutOfBoundsException();
			}

		elems= newList;
		elems[index]=element;
	}

	@Override
	public Object remove(int index) throws IndexOutOfBoundsException {
		Object[] newList= new Object[size];
		Object deleted= get(index);
		for(int i=0;i<index;i++) {
			newList[i]=elems[i];
		}
//		if(size-1==0) {
//			for(int i=index-1;i<size;i++) {
//				newList[i]=elems[i+1];
//			}

//		}
		if(index<size-1 && index>=0) {
			for(int i=index;i<size;i++) {
					newList[i]=elems[i+1];
			}
		}else{
				throw new IndexOutOfBoundsException();
			}
		size = size-1;
		elems= newList;
		return deleted;
	}
}


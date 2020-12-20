package project3;

import java.util.NoSuchElementException;

/**
 * This class implements the List interface provided by the project. 
 * 
 * A Doubly-LinkedList is implemented in the class to store the data values.
 * 
 * @author Jonason Wu
 * @version 10/25/2020
 *
 * @param <E> The type of data that is stored by MyList.
 */
public class MyList<E> implements List<E> {
	
	private Node head;
	private Node tail;
	private int size;
	
	/**
	 * The constructor initializes MyList. An empty Doubly-LinkedList is created.
	 */
	public MyList () {
		head = null;
		tail = null;
		size = 0;
	}
	
	/**
	 * This Node<E> is for a Doubly-LinkedList. It stores elements of generic type 
	 * {@code E} and has a Node pointer to the next and previous Node for 
	 * the List.
	 * 
	 * @author Jonason Wu
	 *
	 * @param <E> The data type that is stored by Node.
	 */
	private class Node {
		E data;
		Node next;
		Node prev;
		
		/**
		 * This is the only constructor for the Node. It accepts an element of generic 
		 * type {@code E}.
		 * 
		 * @param data the element of generic type {@code E} to store in the Node.
		 */
		Node(E data) {
			this.data = data;
			this.next = null;
			this.prev = null;
		}
	}

	/**
	 * Position 0 is the first element in MyList. This method creates a new Node to store the
	 * {@code item} and then places the Node at the {@code pos} indicated.
	 * 
	 * @param item the element to be added to this list
	 * @param pos position at which {@code item} should be added
	 * @return true if an element is added to MyList
	 * @throws NoSuchElementException if {@code pos} < 0 or {@code pos} > size of MyList.
	 * @throws IllegalArgumentException if {@code item} == null.
	 */
	@Override
	public boolean add(E item, int pos) throws NoSuchElementException, IllegalArgumentException {
		if (item == null) {
			throw new IllegalArgumentException("Item to add to MyList cannot be null");
		}
		if (pos < 0 || pos > size) {
			throw new NoSuchElementException("The position to add value is invalid.");
		}
		
		Node newNode = new Node(item);
		if (size == 0) {
			//MyList is empty
			head = newNode;
			tail = newNode;
		}
		else if (pos == 0) {
			//Adding to the front of MyList
			newNode.next = head;
			head.prev = newNode;
			head = newNode;
		}
		else if (pos == size) {
			//Adding to the front of MyList
			tail.next = newNode;
			newNode.prev = tail;
			tail = tail.next;
		}
		else {
			//Adding to position inside MyList
			//Get current to position where current.next points to the Node that will be added.
			Node current = head;
			while (pos != 1) {
				current = current.next;
				pos--;
			}
			current.next.prev = newNode;
			newNode.next = current.next;
			newNode.prev = current;
			current.next = newNode;	
		}
		size++;
		return true;
	}

	/**
	 * Position 0 is the first element in MyList. This method removes the Node at the position 
	 * indicated by {@code pos}.
	 * 
	 * @param pos the position from which the element should be removed
	 * @return the element removed from the list
	 * @throws NoSuchElementException if {@code pos} < 0 or {@code pos} >= size of MyList.
	 */
	@Override
	public E remove(int pos) throws NoSuchElementException {
		if (pos < 0 || pos >= size) {
			throw new NoSuchElementException("There is no element at that position in MyList");
		}
		
		E temp;
		if (size == 1) {
			//If size = 1, then pos = 0. MyList is now empty.
			temp = head.data;
			head = null;
			tail = null;
		}
		else if (pos == 0) {
			//The first element in MyList is to be removed
			temp = head.data;
			head = head.next;
			head.prev = null;
		}
		else if (pos == size - 1) {
			//The last element in MyList is to be removed
			temp = tail.data;
			tail = tail.prev;
			tail.next = null;
		}
		else {
			//Removing element inside MyList 
			//Move current to the Node right before the Node to remove.
			Node current = head;
			while (pos != 1) {
				current = current.next;
				pos--;
			}
			temp = current.next.data;
			current.next = current.next.next;
			current.next.prev = current;	
		}
		size--;
		return temp;
	}

	/**
	 * This method uses the {@link MyList#find(E item)} to find the position of the {@code item}. 
	 * From that method, the index returned would either be the position of the node to remove 
	 * or -1 to indicate that the element does not exist in MyList. Then, either 
	 * {@link MyList#remove(int pos)} is used to remove the Node, or null is returned.
	 * 
	 * @param item element to remove
	 * @return the removed element, or null if {@code item} does not exist in MyList.
	 */
	@Override
	public E remove(E item) {
		//See where the item is in MyList
		int index = find(item);
		if (index == -1) {
			//Item does not exist in MyList
			return null;
		}
		return remove(index);
	}

	/**
	 * This method finds the position of the item in MyList, if it exists. If it does not, -1 
	 * is returned. If there are more than one of that item in MyList, returns the position of the 
	 * first occurrence of that item in MyList.
	 * 
	 * @param item to locate in MyList
	 * @return position of {@code item} in MyList or -1 if {@code item} is not found in MyList
	 */
	@Override
	public int find(E item) {
		if(item == null || head == null)
			//MyList does not allow "null" elements to be added. An empty MyList has no elements.
			return -1;
		Node current = head;
		int counter = 0;
		do {
			if (current.data.equals(item)) {
				return counter;
			}
			else {
				current = current.next;
				counter++;
			}
		} while (current != null);
		return -1;
	}

	/**
	 * Retrieves and returns an element from position {@code pos}
	 * @param pos the position of item to return
	 * @return element stored at position {@code pos}
	 * @throws NoSuchElementException if {@code pos} < 0 or {@code pos} >= size of MyList.
	 */
	@Override
	public E get(int pos) throws NoSuchElementException {
		if (pos < 0 || pos >= size) {
			throw new NoSuchElementException("Index out of bounds. Invalid position.");
		}
		Node current = head;
		while (pos != 0) {
			current = current.next;
			pos--;
		}
		return current.data;
	}

	/**
	 * Returns the current number of elements in this list
	 * @return the number of elements in this list
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Removes all elements in MyList. It is done by setting the pointers for MyList to null.
	 * The size of MyList is also set to 0.
	 */
	@Override
	public void clear() {
		head = null;
		tail = null;
		size = 0;
	}
	
	/**
	 * Determines if the MyList object calling this method is equal to {@code obj}. MyList is 
	 * considered equal to {@code obj} if they have the same elements in the same order. 
	 * They should also have the same number of elements in the List as well to be considered 
	 * equal.
	 * 
	 * @param obj an Object that is compared to this list for equality. Usually another MyList object. 
	 * @return true if the MyList calling this method is equal to {@code obj}. 
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj == this)
			return true;
		if(obj == null)
			return false;
		if(!(obj instanceof MyList<?>))
			return false;
		@SuppressWarnings("unchecked")
		MyList<E> ob = (MyList<E>) obj;
		
		//If the sizes of the MyList to be compared are not equal, then they shouldn't be equal.
		if(this.size != ob.size)
			return false;
		
		Node current1 = this.head;
		Node current2 = ob.head;
		
		//Iterate through the MyList and compare each element
		//We know that the lengths of both MyList are equal
		while (current1 != null) {
			if(!current1.data.equals(current2.data)) 
				return false;
			current1 = current1.next;
			current2 = current2.next;
		}
		return true;
	}
	
	/**
	 * Returns a string representation of MyList. If MyList is empty, an empty string is returned. 
	 * If MyList is not empty, the element of position 0 will be concatenated to the string 
	 * first. After that a comma and a space precedes the next element in MyList. This process 
	 * repeats until all elements in MyList are added to the final string, which is then returned.
	 *
	 * @return a string representation of MyList.
	 */
	@Override
	public String toString () {
		if (head == null)
			return "";
		
		String str = head.data.toString();
		Node current = head.next;
		while (current != null) {
			str += ", " + current.data.toString();
			current = current.next;
		}
		return str;
	}
}

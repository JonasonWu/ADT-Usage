package project3;

/**
 * This class implements the Stack interface provided by the project. 
 * 
 * A Singly LinkedList is implemented in the class to store the data values.
 * 
 * @author Jonason Wu
 * @version 10/25/2020
 *
 * @param <E> The type of data that is stored by MyStack.
 */
public class MyStack<E> implements Stack<E> {

	//head points to the top of the stack
	private Node head;
	private int size;
	
	/**
	 * The constructor initializes MyStack. An empty Singly LinkedList is created. 
	 */
	public MyStack () {
		head = null;
		size = 0;
	}
	
	/**
	 * This Node<E> is for a Singly LinkedList. It stores elements of generic type 
	 * {@code E} and has a Node pointer to the next Node for MyStack.
	 * 
	 * @author Jonason Wu
	 *
	 * @param <E> The data type that is stored by Node.
	 */
	private class Node {
		E data;
		Node next;
		
		Node(E data) {
			this.data = data;
			this.next = null;
		}
	}

	/**
	 * Add an element to the beginning of MyStack. The beginning of MyStack is considered the 
	 * top of the Stack. 
	 * 
	 * @param item to be added to MyStack
	 * @throws IllegalArgumentException if {@code item} == null
	 */
	@Override
	public void push(E item) throws IllegalArgumentException {
		if (item == null)
			throw new IllegalArgumentException("Invalid parameter passed in.");
		
		//Add to the start of the MyStack
		Node newNode = new Node (item);
		newNode.next = head;
		head = newNode;
		size++;
	}

	/**
	 * Removes and returns the first element from MyStack. The first element is the 
	 * top of the Stack.
	 * 
	 * @return the first element of MyStack or null if MyStack is empty.
	 */
	@Override
	public E pop() {
		if (size == 0) 
			return null;
		
		//Remove and return first element of MyStack
		E temp = head.data;
		head = head.next;
		size--;
		return temp;
	}

	/**
	 * Returns but does not remove the first element from MyStack. The first element is the 
	 * top of the Stack.
	 * 
	 * @return the first element of MyStack or null if MyStack is empty.
	 */
	@Override
	public E top() {
		if (size == 0) 
			return null;
		
		//Return the first element of MyStack
		E temp = head.data;
		return temp;
	}
	
	/**
	 * Determines if the MyStack calling this method is equal to {@code obj}. MyStack is 
	 * considered equal to {@code obj} if they have the same elements in the same order. They 
	 * should also have the same number of elements in the List as well to be considered equal.
	 * 
	 * @param obj an Object that is compared to this list for equality. 
	 * Usually another MyStack object. 
	 * @return true if the MyStack calling this method is equal to {@code obj}. 
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof MyStack<?>))
			return false;
		
		@SuppressWarnings("unchecked")
		MyStack<E> ob = (MyStack<E>) obj;
		
		//If the number of elements in the 2 MyStack are not equal, then they shouldn't be equal.
		if (this.size != ob.size)
			return false;
		
		Node current1 = this.head;
		Node current2 = ob.head;
		//Iterate through the MyStack and compare all the elements
		//We know that both MyStack have the same size
		while (current1 != null) {
			if (!current1.data.equals(current2.data))
				return false;
			current1 = current1.next;
			current2 = current2.next;
		}		
		return true;
	}
	
	
	/**
	 * Returns a string representation of MyStack. If MyStack is empty, an empty string is 
	 * returned. If MyStack is not empty, the string that is returned will begin with the last 
	 * element (or the bottom) of MyStack and end with the first element (or the top) of MyStack 
	 * all concatenated to the string and separated by a comma and a space.
	 *
	 * @return a string representation of MyStack.
	 */
	@Override
	public String toString () {
		if (size == 0)
			return "";
		
		String str = head.data.toString();
		Node current = head.next;
		while (current != null) {
			str = current.data.toString() + ", " + str;
			current = current.next;
		}
		return str;
	}
}

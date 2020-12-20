package project3;

/**
 * This class implements the Queue interface provided by the project. 
 * 
 * An Array is used in the class to store the data values. 
 * When MyQueue is considered to be full, adding the next element to MyQueue would 
 * create a bigger Array to store the data values.
 * 
 * @author Jonason Wu
 * @version 10/25/2020
 *
 * @param <E> The type of data that is stored by MyStack.
 */
public class MyQueue<E> implements Queue<E> {
	//The array to store the data types of E.
	private Object[] queue;
	//The current size of the queue
	private int size;
	//The capacity of the queue.
	private int capacity;
	//The front of the queue
	private int front;
	//The end of the queue
	private int back;
	
	/**
	 * This constructor initializes MyQueue. An empty Array that could store a maximum of 10 
	 * elements is created.
	 */
	public MyQueue () {
		capacity = 10;
		queue = new Object[capacity];
		front = 0;
		back = 0;
		size = 0;
	}
	
	/**
	 * Add an element to the back of MyQueue. This method would add the element even if 
	 * MyQueue is considered full by expanding the Array. Element to add cannot be null.
	 * 
	 * @param item element to be added to this queue
	 * @throws IllegalArgumentException if {@code item} == null
	 */
	@Override
	public void enqueue(E item) throws IllegalArgumentException {
		if (item == null) 
			throw new IllegalArgumentException("Item to enqueue cannot be null.");
		
		//If the Array to store the elements is considered full, create a bigger array to store 
		//	the elements. Array is considered full when there is only 1 space left in the Array.
		if (size + 1 == capacity) {
			Object[] newArr = new Object[capacity * 2];
			for (int i = front, j = 0; j < size; i++, j++) {
				if(i == capacity)
					i -= capacity;
				newArr[j] = queue[i];	
			}
			front = 0;
			back = size;
			capacity *= 2;
			queue = newArr;
		}
		queue[back] = item;
		back++;
		back %= capacity;
		size++;
	}

	/**
	 * Remove and return the element from the front of this MyQueue.
	 * @return the element from the front of this MyQueue or null if this MyQueue is empty
	 */
	@SuppressWarnings("unchecked")
	@Override
	public E dequeue() {
		if (size == 0)
			return null;
		E ret = (E) queue[front++];
		front %= capacity;
		size--;
		return ret;
	}

	/**
	 * Return but do not remove the element from the front of this MyQueue.
	 * @return the element from the top of this MyQueue or null if this MyQueue is empty
	 */
	@SuppressWarnings("unchecked")
	@Override
	public E peek() {
		if (size == 0)
			return null;
		return (E) queue[front];
	}
	
	/**
	 * Determines if the MyQueue calling this method is equal to {@code obj}. MyQueue is 
	 * considered equal to {@code obj} if they have the same elements in the same order. They 
	 * should also have the same number of elements in the List as well to be considered equal.
	 * 
	 * @param obj an Object that is compared to this list for equality. 
	 * Usually another MyQueue object. 
	 * @return true if the MyQueue calling this method is equal to {@code obj}. 
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof MyQueue<?>))
			return false;
		
		@SuppressWarnings("unchecked")
		MyQueue<E> ob = (MyQueue<E>) obj;
		
		//If the number of elements in the 2 MyQueue are not equal, then they shouldn't be equal.
		if (this.size != ob.size)
			return false;
		
		int front1 = this.front;
		int front2 = ob.front;
		//Iterate through the queue and compare all the elements
		//We know that the size of both MyQueue are equal
		while (front1 != this.back) {
			if (!this.queue[front1++].equals(ob.queue[front2++]))
				return false;
			front1 %= this.capacity;
			front2 %= ob.capacity;
		}
		return true;
	}
	
	/**
	 * Returns a string representation of this MyQueue. The string is constructed by
	 * concatenating all elements of this queue separated by a comma and a single space.
	 * The front of the queue should be the leftmost element of the string. There should be 
	 * no comma after the last element.
	 * 
	 * @return a string representation of this MyQueue.
	 */
	@Override
	public String toString () {
		if (size == 0)
			return "";
		
		int front1 = front;
		String str = queue[front1++].toString();		
		while ((front1 %= capacity) != back) {	
			str += ", " + queue[front1++];
		}
		return str;
	}
}

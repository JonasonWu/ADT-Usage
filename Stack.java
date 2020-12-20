package project3;

public interface Stack<E> {
	/**
	* Add an element to the top of this stack
	* @param item to be added to this stack
	* throws IllegalArgumentException if `item == null`
	*/
	public void push ( E item ) ;

	/**
	* Remove and return the element from the top of this stack
	* @return the element from the top of this stack or null if this stack is empty
	*/
	public E pop () ;

	/** Return the element from the top of this stack.
	* @return  the element from the top of this stack or null if this stack is empty
	*/
	public E top () ;

	/**
	* Determines if this stack is equal to `obj`.
	* @obj an Object that is compared to this stack for equality
	* @return true if this stack is equal to `obj` (same elements in the same order)
	*         false, otherwise
	*/
	public boolean equals(Object obj);

	/**
	* Returns a string representation of this stack. The string is constructed by
	* concatenating all elements of this stack separated by a comma and a single space.
	* The bottom of the stack should be the leftmost element and the top of the stack
	* should be the rightmost element. There should be no comma after the last element.
	* @return a string representation of this stack.
	*/
	public String toString () ;
}
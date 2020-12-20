package project3;

import java.util.NoSuchElementException;

//List  ADT
public interface List<E> {

	/**
	* Adds an element 'item' at position `pos`, shifts elements starting at `pos` by
	* one position to the right (higher position values)
	* @param item the element to be added to this list
	* @param pos postion at which `item` should be added
	* throws NoSuchElementException if `pos` < 0 or `pos` > size
	* throws IllegalArgumentException if `item == null`
	*/
	public boolean add(E item, int pos) throws NoSuchElementException;

	/**
	* Removes and returns an element at position `pos`, shifts elements starting
	* at `pos+1` by one to the left (lower position values)
	* @param pos the position from which the element should be removed
	* @ return the element removed from the list
	* throws NoSuchElementException if pos < 0 or pos >= size
	*/
	public E remove(int pos) throws NoSuchElementException;

	/**
	* Removes and returns an element equal to `item`, shifts elements starting
	* at the next position by one to the left (lower position values)
	* @param item element to remove
	* @return the removed element, or null if element equal to `item` is not in this list
	*/
	public E remove (E item);

	/**
	* Determines if 'item' is in the list and if so returns its position
	* @param item to locate in this list
	* @return position of `item` in this list or -1 if `item` is not found in this list
	*/
	public int find (E item) ;

	/**
	* Retrieves and returns an element from position `pos`
	* @param pos the position of item to return
	* @return element stored at position `pos`
	* throws NoSuchElementException if pos < 0 or pos >= size
	*/
	public E get( int pos) throws NoSuchElementException;

	/**
	* Returns the current number of elements in this list
	* @return the number of elements in this list
	*/
	public int size();

	/**
	* Removes all elements from this list, so it is once again empty.
	*/
	public void clear();

	/**
	* Determines if this list is equal to `obj`.
	* @obj an Object that is compared to this list for equality
	* @return true if this list is equal to `obj` (same elements in the same order)
	*         false, otherwise
	*/
	public boolean equals(Object obj);

	/**
	* Returns a string representation of this list. The string is constructed by
	* concatenating all elements of this list separated by a comma and a single space.
	* There should be no comma after the last element.
	* @return a string representation of this list.
	*/
	public String toString ();
}

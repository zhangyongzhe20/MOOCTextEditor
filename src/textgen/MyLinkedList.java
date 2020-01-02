package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 * @author Zhang Yong Zhe
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;
	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// TODO: Implement this method
		// Initial two sentinel nodes to null
		size =0;
		head = new LLNode<E>(null);
		tail = new LLNode<E>(null);
		//connecting to two sentinel
		head.next = tail;
		tail.prev = head;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element) 
	{
		// TODO: Implement this method
		if(element==null) {
			throw new NullPointerException("Null element is not allowed");
		}

		// Create a new-add nodes
		LLNode<E> add = new LLNode<E>(element, tail.prev, tail);
		//check if the list is empty
		tail.prev.next = add;
		tail.prev = add;
		size++;
		return true;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		// TODO: Implement this method.
		if(index < 0 || index > size -1) {
			throw new IndexOutOfBoundsException("Invalid index");
		}
		// head is sentinel node
		LLNode<E> curr = head;
		while(index >= 0) {
			curr = curr.next;
			index--;
		}
		return curr.data;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		// TODO: Implement this method
		// init check
		if(index < 0 || index > size) {
			throw new IndexOutOfBoundsException("Invalid index");
		}
		if(element == null) {
			throw new NullPointerException("Element can not be null");
		}
		// Create a new-add nodes
		LLNode<E> add = new LLNode<E>(element);
		//check if the list is empty
		LLNode<E> curr = head;
		while(index >= 0) {
			curr = curr.next;
			index--;
		}		
		add.next = curr;
		add.prev = curr.prev;
		curr.prev.next = add;
		curr.prev = add;
		size ++;	
	}


	/** Return the size of the list */
	public int size() 
	{
		// TODO: Implement this method
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		// TODO: Implement this method
		// init check
		if(index < 0 || index > size-1) {
			throw new IndexOutOfBoundsException("Invalid index");
		}
		LLNode<E> curr = head;
		//if the removed node is head, then shift head to the next node
		while(index >= 0) {
			curr = curr.next;
			index--;
		}
		curr.next.prev = curr.prev;
		curr.prev.next = curr.next;
		size --;
		return curr.data;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		// TODO: Implement this method
		if(index < 0 || index > size-1) {
			throw new IndexOutOfBoundsException("Invalid index");
		}
		if(element == null) {
			throw new NullPointerException("Element can not be null");
		}
		LLNode<E> curr = head;
		while(index >= 0) {
			curr = curr.next;
			index--;
		}
		curr.data = element;
		return curr.data;
		
	}   
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}

	/** LLNode constructor
	 * 
	 * @param e Element to add
	 * @param prev Previous node
	 * @param next Next node
	 * 
	 */
	public LLNode(E e, LLNode<E> prev, LLNode<E> next) {
		this.data = e;
		this.prev = prev;
		this.next = next;
	}
	
	@Override
	public String toString(){
		String printout = this.data + ":Previous node " + this.prev.data + ", Next node " + this.next.data;
		return printout;
	}
	

}

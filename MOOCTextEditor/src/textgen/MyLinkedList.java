package textgen;

import java.util.AbstractList;

import com.aggreyah.demonlinkedlist.LLNode;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		LLNode<E> headSentinel = new LLNode<E>();
		LLNode<E> tailSentinel = new LLNode<E>();
		this.head = headSentinel;
		this.tail = tailSentinel;
		headSentinel.next = tailSentinel;
		tailSentinel.prev = headSentinel;
		this.size = 0;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	{
		// TODO: Implement this method
		LLNode<E> currentNode = new LLNode <E>(element);
		currentNode.prev = this.tail.prev;
		this.tail.prev = currentNode;
		currentNode.prev.next = currentNode;
		currentNode.next = this.tail;
		this.size += 1;
		return (this.size > 0);
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		// TODO: Implement this method.
		if (index > this.size - 1 || index < 1)
			throw new IndexOutOfBoundsException("You are attempting to access from an out of bounds index, Sorry!");
		LLNode <E> newNode = this.head;
		if (newNode.next == this.tail)
			System.out.println("An empty list.");
		int i = 0;
		while (i <= index && newNode.next != this.tail) {
			newNode = newNode.next;
			i++;
		}
		return newNode.data;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		// TODO: Implement this method
	}


	/** Return the size of the list */
	public int size() 
	{
		// TODO: Implement this method
		
		return this.size;
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
		return null;
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
		return null;
	}   
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor
	public LLNode() {
		this.data = null;
		this.next = null;
		this.prev = null;
	}

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}

}

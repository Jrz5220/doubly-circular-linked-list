
/**
 * An interface for the ADT deque.
 *  A deque (double-ended queue) is a list with operations to add, remove, and retrieve data from both ends of the list.
 */

public interface DequeInterface<T> {
	
	/**
	 * Adds a new entry to the front of this deque.
	 * @param newEntry An object to be added
	 */
	public void addToFront(T newEntry);
	
	/**
	 * Adds a new entry to the back of this deque.
	 * @param newEntry An object to be added
	 */
	public void addToBack(T newEntry);
	
	/**
	 * Removes and returns the front entry of this deque.
	 * @return The object at the front of the deque.
	 * @throws EmptyQueueException if the deque is empty before the operation.
	 */
	public T removeFront();
	
	/**
	 * Removes and returns the back entry of this deque.
	 * @return The object at the back of the deque
	 */
	public T removeBack();
	
	/**
	 * Retrieves the front entry of this deque.
	 * @return The object at the front of the deque.
	 * @throws EmptyQueueException if the deque is empty.
	 */
	public T getFront();
	
	/**
	 * Retrieves the back entry of this deque.
	 * @return The object at the back of the deque
	 */
	public T getBack();
	
	/**
	 * Detects whether this deque is empty.
	 * @return True if the deque is empty, false if not.
	 */
	public boolean isEmpty();
	
	/**
	 * Remove all entries from this deque.
	 */
	public void clear();

}

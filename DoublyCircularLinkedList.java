
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoublyCircularLinkedList<T> implements DequeInterface<T>, Iterable<T> {
	
	private Node firstNode;
	private int size;
	
	public DoublyCircularLinkedList() {
		firstNode = null;
		size = 0;
	}

	@Override
	public void addToFront(T newEntry) {
		Node newNode = new Node(newEntry);
		if(isEmpty()) {
			firstNode = newNode;
			newNode.setPrevNode(firstNode);
			newNode.setNextNode(firstNode);
		} else {
			newNode.setPrevNode(firstNode.getPrevNode());
			newNode.setNextNode(firstNode);
			firstNode.getPrevNode().setNextNode(newNode);
			firstNode.setPrevNode(newNode);
			firstNode = newNode;
		}
		size++;
	}

	@Override
	public void addToBack(T newEntry) {
		Node newNode = new Node(newEntry);
		if(isEmpty()) {
			firstNode = newNode;
			firstNode.setNextNode(newNode);
		} else {
			newNode.setNextNode(firstNode);
			newNode.setPrevNode(firstNode.getPrevNode());
			firstNode.getPrevNode().setNextNode(newNode);
		}
		firstNode.setPrevNode(newNode);
		size++;
	}

	@Override
	public T removeFront() {
		T removedEntry = getFront();									// Might throw NoSuchElementException
		if(firstNode.getNextNode() == firstNode)
			firstNode = null;
		else {
			firstNode.getPrevNode().setNextNode(firstNode.getNextNode());
			firstNode.getNextNode().setPrevNode(firstNode.getPrevNode());
			firstNode = firstNode.getNextNode();
		}
		size--;
		return removedEntry;
	}

	@Override
	public T removeBack() {
		T removedEntry = getBack();										// Might throw NoSuchElementException
		if(firstNode.getNextNode() == firstNode)
			firstNode = null;
		else {
		firstNode.getPrevNode().getPrevNode().setNextNode(firstNode);
		firstNode.setPrevNode(firstNode.getPrevNode().getPrevNode());
		}
		size--;
		return removedEntry;
	}

	@Override
	public T getFront() {
		if(isEmpty())
			throw new NoSuchElementException("List is empty");
		else
			return firstNode.getData();
	}

	@Override
	public T getBack() {
		if(isEmpty())
			throw new NoSuchElementException("List is empty");
		else
			return firstNode.getPrevNode().getData();
	}

	@Override
	public boolean isEmpty() {
		return firstNode == null;
	}

	@Override
	public void clear() {
		firstNode = null;
		size = 0;
	}
	
	@Override
	public Iterator<T> iterator() {
		return new LinkedListIterator();
	}
	
	/**
	 * Adds an entry to this list at the given position.
	 * @param index The index in this list to place the new entry
	 * @param newEntry An object to be added
	 * @throws NoSuchElementException if this list is empty
	 * @throws IndexOutOfBoundsException if the index is out of bounds
	 */
	public void addEntry(int index, T newEntry) throws NoSuchElementException, IndexOutOfBoundsException {
		if(index < size && index >= 0) {
			if(isEmpty() && index != 0)
				throw new NoSuchElementException();
			else if(index == 0)
				addToFront(newEntry);
			else if(index == size - 1)
				addToBack(newEntry);
			else {
				Node currentNode = getNodeAt(index);
				Node newNode = new Node(currentNode.getPrevNode(), newEntry, currentNode);
				currentNode.getPrevNode().setNextNode(newNode);
				currentNode.setPrevNode(newNode);
				size++;
			}
		} else {
			throw new IndexOutOfBoundsException();
		}
	}
	
	/**
	 * Removes and returns the entry at the given position in this list.
	 * @param index The index of the entry to remove
	 * @return The removed entry
	 * @throws NoSuchElementException if this list is empty
	 * @throws IndexOutOfBoundsException if the index is out of bounds
	 */
	public T removeEntry(int index) throws NoSuchElementException, IndexOutOfBoundsException {
		T removedEntry = null;
		if(index < size && index >= 0) {
			if(isEmpty())
				throw new NoSuchElementException();
			else if(index == 0)
				removedEntry = removeFront();
			else if(index == size - 1)
				removedEntry = removeBack();
			else {
				Node nodeToRemove = getNodeAt(index);
				removedEntry = nodeToRemove.getData();
				nodeToRemove.getPrevNode().setNextNode(nodeToRemove.getNextNode());
				nodeToRemove.getNextNode().setPrevNode(nodeToRemove.getPrevNode());
				size--;
			}
		} else {
			throw new IndexOutOfBoundsException();
		}
		return removedEntry;
	}
	
	/**
	 * Retrieves the entry at the given position in this list.
	 * @param index The index of the entry in this list (index starts at 0)
	 * @return The entry data
	 * @throws NoSuchElementException if this list is empty
	 * @throws IndexOutOfBoundsException if the index is out of bounds
	 */
	public T getEntry(int index) throws NoSuchElementException, IndexOutOfBoundsException {
		T theEntry = null;
		if(index < size && index >= 0) {
			if(isEmpty())
				throw new NoSuchElementException();
			Node theNode = getNodeAt(index);
			theEntry = theNode.getData();
		} else {
			throw new IndexOutOfBoundsException();
		}
		return theEntry;
	}
	
	/**
	 * Replaces an entry in this list with the given entry.
	 * @param index The index to place the new entry
	 * @param newEntry An object be added
	 * @return The replaced entry
	 * @throws NoSuchElementException if this list is empty
	 * @throws IndexOutOfBoundsException if the index is out of bounds
	 */
	public T replace(int index, T newEntry) throws NoSuchElementException, IndexOutOfBoundsException {
		T originalEntry = null;
		if(index < size && index >= 0) {
			if(isEmpty())
				throw new NoSuchElementException();
			Node theNode = getNodeAt(index);
			originalEntry = theNode.getData();
			theNode.setData(newEntry);
			theNode.setData(newEntry);
		} else
			throw new IndexOutOfBoundsException();
		return originalEntry;
	}
	
	/**
	 * Searches this list for the given entry.
	 * @param theEntry The entry to be searched
	 * @return True if at least one occurrence of the given entry exists in this list, false if not
	 */
	public boolean contains(T theEntry) {
		boolean found = false;
		Node currentNode = firstNode;
		for(int i = 0; i < size; i++) {
			if(theEntry.equals(currentNode.getData())) {
				found = true;
				break;
			}
			currentNode = currentNode.getNextNode();
		}
		return found;
	}
	
	/**
	 * Returns the number of entries in this list.
	 * @return The number of entries
	 */
	public int size() {
		return size;
	}
	
	private Node getNodeAt(int index) {
		Node currentNode = firstNode;
		// Assertion: (!isEmpty()) && (index >= 0) && (index < size)
		for(int i = 0; i < index; i++) {
			currentNode = currentNode.getNextNode();
		}
		// Assertion: currentNode != null
		return currentNode;
	}
	
	private class LinkedListIterator implements Iterator<T> {
		
		private Node nextNode;
		private int nodeCount;
		
		private LinkedListIterator() {
			nextNode = firstNode;
			nodeCount = 0;
		}

		@Override
		public boolean hasNext() {
			return nodeCount != size;
		}

		@Override
		public T next() {
			T result = null;
			if(hasNext()) {
				nodeCount++;
				result = nextNode.getData();
				nextNode = nextNode.getNextNode();
			} else {
				throw new NoSuchElementException("Illegal call to next(); iterator is after the end of list.");
			}
			return result;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException("remove() is not supported by this iterator");
		}
		
	} //end LinkedListIterator
	
	private class Node {
		private T data;
		private Node next;
		private Node previous;
		
		public Node() {
			this(null);
		}
		
		public Node(T newData) {
			this(null, newData, null);
		}
		
		public Node(Node prevNode, T newData, Node nextNode) {
			data = newData;
			next = nextNode;
			previous = prevNode;
		}
		
		public T getData() {
			return data;
		}
		
		public void setData(T newData) {
			data = newData;
		}
		
		public Node getNextNode() {
			return next;
		}
		
		public void setNextNode(Node nextNode) {
			next = nextNode;
		}
		
		public Node getPrevNode() {
			return previous;
		}
		
		public void setPrevNode(Node prevNode) {
			previous = prevNode;
		}
	} //end Node

}

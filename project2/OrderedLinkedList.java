/**
 * 
 */
package project2;

/**
 * Generic class the creates and modifies a singly linked list in sorted order,
 * 
 * @author Dimitri Chaber
 * @version 9/25/2017
 */
public class OrderedLinkedList<E extends Comparable<E>> implements OrderedList<E> {
	// data fields
	private Node<E> head = null;
	private Node<E> tail = head;
	private int size = 0;

	// class constructor
	public OrderedLinkedList() {

	}

	/**
	 * Adds the specified element to this list in a sorted order.
	 *
	 * <p>
	 * The element added must implement Comparable<E> interface. This list does not
	 * permit null elements.
	 *
	 * @param e
	 *            element to be appended to this list
	 * @return <tt>true</tt> if this collection changed as a result of the call
	 * @throws ClassCastException
	 *             if the class of the specified element does not implement
	 *             Comparable<E>
	 * @throws NullPointerException
	 *             if the specified element is null
	 */
	public boolean add(E e) {
		if (e == null) {
			throw new NullPointerException();
		}
		Node<E> newNode = new Node<E>(e, null);

		// add element to beginning if list is empty
		if (size == 0) {
			head = newNode;
			tail = head;
			size++;
			return true;
		}

		else if (e.compareTo(head.getElement()) < 0) {
			newNode.setNext(head);
			head = newNode;
			size++;
			return true;
		}
		// add element to end of list if it is greater than tail
		else if (e.compareTo(tail.getElement()) > 0 || e.compareTo(tail.getElement()) == 0) {
			tail.setNext(newNode);
			tail = newNode;
			size++;
			return true;
		}
		// insert element into proper place in list
		else {
			Node<E> current = head;
			Node<E> after = head.getNext();
			// loop through list until proper place is found
			while (after != null) {

				if (e.compareTo(after.getElement()) < 0) {
					newNode.setNext(after);
					current.setNext(newNode);
					size++;
					return true;
				}
				current = current.getNext();
				after = after.getNext();
			}

		}
		// increment size and return true
		size++;
		return true;

	}

	/**
	 * Removes all of the elements from this list. The list will be empty after this
	 * call returns.
	 */
	public void clear() {
		// set head to null to clear list
		head = null;
		size = 0;
	}

	/**
	 * Returns a shallow copy of this list. (The elements themselves are not
	 * cloned.) Implementation based on Data Structures and Algorithms textbook
	 *
	 * @return a shallow copy of this list instance
	 * @throws CloneNotSupportedException
	 *             - if the object's class does not support the Cloneable interface
	 */
	public Object clone() throws CloneNotSupportedException {
		OrderedLinkedList<E> clone = (OrderedLinkedList<E>) super.clone();
		if (size > 0) {
			clone.head = new Node<>(head.getElement(), null);
			Node<E> walk = head.getNext();
			Node<E> cloneTail = clone.head;
			// go through list and set node of clone to reference node of list
			while (walk != null) {
				Node<E> newest = new Node<>(walk.getElement(), null);
				cloneTail.setNext(newest);
				cloneTail = newest;
				walk = walk.getNext();
			}
		}
		return clone;

	}

	/**
	 * Returns <tt>true</tt> if this list contains the specified element.
	 *
	 * @param o
	 *            element whose presence in this list is to be tested
	 * @return <tt>true</tt> if this list contains the specified element
	 * @throws ClassCastException
	 *             if the type of the specified element is incompatible with this
	 *             list
	 * @throws NullPointerException
	 *             if the specified element is null
	 */
	public boolean contains(Object o) {
		// if object is null throw exception
		if (o == null) {
			throw new NullPointerException();
		}
		// if list is empty return false
		if (size == 0)
			return false;
		Node<E> current = head;
		// check to see if list contains object
		while (current != null) {
			if (current.getElement() == o)
				return true;
			current = current.getNext();
		}

		return false;

	}

	/**
	 * Compares the specified object with this list for equality. Returns
	 * {@code true} if and only if the specified object is also a list, both lists
	 * have the same size, and all corresponding pairs of elements in the two lists
	 * are <i>equal</i>. (Two elements {@code e1} and {@code e2} are <i>equal</i> if
	 * {@code e1.equals(e2)}.) In other words, two lists are defined to be equal if
	 * they contain the same elements in the same order.
	 * <p>
	 * Implementation based on Data Structures and Algorithms textbook.
	 *
	 * @param o
	 *            the object to be compared for equality with this list
	 * @return {@code true} if the specified object is equal to this list
	 */
	@Override
	public boolean equals(Object o) {
		if (o == null)
			return false;
		// if the two objects are different types return false
		if (getClass() != o.getClass())
			return false;
		OrderedLinkedList tempList = (OrderedLinkedList) o;
		if (size != tempList.size)
			return false;
		Node walkA = head;
		Node walkB = tempList.head;
		while (walkA != null) {
			if (!walkA.getElement().equals(walkB.getElement()))
				return false;
			walkA = walkA.getNext();
			walkB = walkB.getNext();
		}
		return true;

	}

	/**
	 * Returns the element at the specified position in this list.
	 *
	 * @param index
	 *            index of the element to return
	 * @return the element at the specified position in this list
	 * @throws IndexOutOfBoundsException
	 *             if the index is out of range
	 *             <tt>(index < 0 || index >= size())</tt>
	 */
	public E get(int index) {
		if (index > size || index < 0)
			throw new IndexOutOfBoundsException();
		Node<E> current = head;
		// go to location of index and return element
		for (int i = 0; i < index; i++) {
			current = current.getNext();
		}
		return current.getElement();

	}

	/**
	 * Returns the index of the first occurrence of the specified element in this
	 * list, or -1 if this list does not contain the element.
	 *
	 * @param o
	 *            element to search for
	 * @return the index of the first occurrence of the specified element in this
	 *         list, or -1 if this list does not contain the element
	 */
	public int indexOf(Object o) {
		int index = 0;
		Node<E> current = head;
		// loop until object is found
		while (current != null) {
			if (current.getElement().equals(o)) {
				return index;
			}
			current = current.getNext();
			// increment index
			index++;
		}
		// list does not contain object
		return -1;

	}

	/**
	 * Removes the element at the specified position in this list. Shifts any
	 * subsequent elements to the left (subtracts one from their indices if such
	 * exist). Returns the element that was removed from the list.
	 *
	 * @param index
	 *            the index of the element to be removed
	 * @return the element previously at the specified position
	 * @throws IndexOutOfBoundsException
	 *             if the index is out of range
	 *             <tt>(index < 0 || index >= size())</tt>
	 */
	public E remove(int index) {
		if (index > size || index < 0)
			throw new IndexOutOfBoundsException();
		Node<E> current = head;
		Node<E> before = null;

		// go to location of index and remove node
		for (int i = 0; i < index; i++) {
			before = current;
			current = current.getNext();

		}
		before.setNext(current.getNext());
		size--;
		// return removed element
		return current.getElement();

	}

	/**
	 * Removes the first occurrence of the specified element from this list, if it
	 * is present. If this list does not contain the element, it is unchanged. More
	 * formally, removes the element with the lowest index {@code i} such that
	 * <tt>(o.equals(get(i))</tt> (if such an element exists). Returns {@code true}
	 * if this list contained the specified element (or equivalently, if this list
	 * changed as a result of the call).
	 *
	 * @param o
	 *            element to be removed from this list, if present
	 * @return {@code true} if this list contained the specified element
	 * @throws ClassCastException
	 *             if the type of the specified element is incompatible with this
	 *             list
	 * @throws NullPointerException
	 *             if the specified element is null and this list does not permit
	 *             null elements
	 */
	public boolean remove(Object o) {
		if (o == null) {
			throw new NullPointerException();
		}
		Node<E> before = null;
		Node<E> current = head;
		// check if list is empty
		if (size == 0) {
			return false;
		}
		// check if head is equal to object
		if (current.getElement().equals(o)) {
			head = null;
			size--;
			return true;
		}
		// search for object and remove if it exists
		while (current != null) {
			if (current.getElement().equals(o)) {
				before.setNext(current.getNext());
				size--;
				return true;
			}
			before = current;
			current = current.getNext();
		}
		// otherwise return false
		return false;

	}

	/**
	 * Returns the number of elements in this list.
	 *
	 * @return the number of elements in this list
	 */
	public int size() {
		return size;

	}

	/**
	 * Returns a string representation of this list. The string representation
	 * consists of a list of the list's elements in the order they are stored in
	 * this list, enclosed in square brackets (<tt>"[]"</tt>). Adjacent elements are
	 * separated by the characters <tt>", "</tt> (comma and space). Elements are
	 * converted to strings by the {@code toString()} method of those elements.
	 *
	 * @return a string representation of this list //
	 */
	@Override
	public String toString() {
		Node<E> temp = head;
		if (size == 0) {
			return "[]";
		}
		String listString = "[";

		while (temp != null) {
			listString += temp.getElement();
			if (temp.getNext() != null) {
				listString += ", ";
			}
			temp = temp.getNext();

		}
		listString += "]";
		return listString;

	}

	/**
	 * Nested node class. Implementation based on Data Structures and Algorithms
	 * textbook
	 * 
	 * @author Dimitri Chaber
	 *
	 * @param <E>
	 */
	public static class Node<E> {
		private E element;
		private Node<E> next;

		/**
		 * node constructor
		 * 
		 * @param e
		 *            stored element
		 * @param n
		 *            next node
		 */
		public Node(E e, Node<E> n) {
			element = e;
			next = n;
		}

		/**
		 * returns element
		 * 
		 * @return element
		 */
		public E getElement() {
			return element;
		}

		/**
		 * sets element to e
		 * 
		 * @param e
		 *            new element
		 */
		public void setElement(E e) {
			element = e;
		}

		/**
		 * returns next node
		 * 
		 * @return next node
		 */
		public Node<E> getNext() {
			return next;
		}

		/**
		 * set next node
		 * 
		 * @param next
		 *            node
		 */
		public void setNext(Node<E> n) {
			next = n;
		}
	}

}

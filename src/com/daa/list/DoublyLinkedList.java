package com.daa.list;

public class DoublyLinkedList<K, V> {

	private DNode<K, V> head;
	private DNode<K, V> tail;
	private int size;

	/**
	 * @return the head
	 */
	public DNode<K, V> getHead() {
		return head;
	}

	/**
	 * @return the tail
	 */
	public DNode<K, V> getTail() {
		return tail;
	}

	/**
	 * add element to the beginning of the list. o(1)
	 * 
	 * @param node
	 */
	public void addToHead(DNode<K, V> node) {
		if (head == null) {
			tail = node;
		} else {
			node.setNext(head);
			head.setPrev(node);
		}
		head = node;
		// update size
		size++;
	}

	/**
	 * remove element from tail. o(1)
	 * 
	 */
	public void removeFromTail() {
		if (tail == null) {
			return;
		}
		DNode<K, V> temp = tail;
		tail = tail.getPrev();
		if (tail == null) {
			head = null;
		} else {
			tail.setNext(null);
		}
		// remove obsolete reference
		temp.setPrev(null);
		// update size
		size--;
	}

	/**
	 * Remove given node from list. 
	 * @f:off
	 * o(1)
	 * it is assumed that node exist in the list otherwise it will update wrong head and tail.
	 * @f:on
	 * 
	 * @param node
	 */
	public void remove(DNode<K, V> node) {
		if (head == null) {
			return;
		}
		DNode<K, V> prev = node.getPrev();
		DNode<K, V> next = node.getNext();
		if (prev == null) {
			head = next;
		} else {
			prev.setNext(next);
		}
		if (next == null) {
			tail = prev;
		} else {
			next.setPrev(prev);
		}
		// remove obsolete references
		node.setNext(null);
		node.setPrev(null);
		// update size
		size--;
	}

	/**
	 * @return the size
	 */
	public int size() {
		return size;
	}

	/**
	 * print data present in list. o(n)
	 */
	public void print() {

		DNode<K, V> temp = head;
		while (temp != null) {
			System.out.print(temp.getValue() + "->");
			temp=temp.getNext();
		}
		System.out.println();
	}

}

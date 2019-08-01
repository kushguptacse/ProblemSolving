package com.daa.queue;

import com.daa.list.Node;

/**
 * Insert data in the FIFO order
 * 
 * insertion should be done into rear end and deletion from front end
 * 
 * @author G521885
 *
 */
public class Queue<T extends Comparable<T>> {

	private Node<T> front;
	private Node<T> rear;
	private int size;

	/**
	 * insert data from rear end
	 * o(1)
	 * @param data
	 */
	public void add(T data) {
		Node<T> node = new Node<>(data);
		if (rear == null) {
			front = node;
		} else {
			rear.setNext(node);
		}
		rear = node;
		size++;
	}

	/**
	 * remove data from front end.
	 * o(1)
	 * @return removed data
	 */
	public T poll() {
		if (front == null) {
			return null;
		}
		T temp = front.getData();
		front = front.getNext();
		if (front == null) {
			rear = null;
		}
		size--;
		return temp;
	}

	public void print() {
		Node<T> temp = rear;
		while (temp != null) {
			System.out.print(temp.getData() + " ");
			temp = temp.getNext();
		}
		System.out.println();
	}

	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	public T peek() {
		return front == null ? null : front.getData();
	}

}

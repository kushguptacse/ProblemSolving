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
	 * 
	 * @param data
	 */
	public void add(T data) {
		Node<T> node = new Node<>(data);
		if (front == null) {
			front = node;
			rear = node;
		} else {
			node.setNext(rear);
			rear = node;
		}
		size++;
	}

	/**
	 * remove data from front end.
	 * 
	 * @return
	 */
	public T poll() {
		if (front == null) {
			return null;
		}

		Node<T> temp = rear;
		if (front == temp) {
			front = null;
			rear = null;
			return temp.getData();
		}

		while (temp.getNext() != front) {
			temp = temp.getNext();
		}

		T val = temp.getNext().getData();
		temp.setNext(null);
		front = temp;
		size--;
		return val;
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

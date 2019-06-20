package com.daa.stack;

import com.daa.list.Node;

public class Stack<T extends Comparable<T>> {

	private int size;

	private Node<T> top;

	/**
	 * push the data on top of stack - o(1)
	 * @param data
	 */
	public void push(T data) {
		Node<T> node = new Node<>(data);
		node.setNext(top);
		top = node;
		size++;
	}

	/**
	 * remove the data on top of stack - o(1)
	 * @return data
	 */
	public T pop() {
		if (top == null) {
			return null;
		}
		Node<T> old = top;
		top = top.getNext();
		size--;
		old.setNext(null);
		return old.getData();
	}

	/**
	 * get the data from top of stack without deleting it - o(1)
	 * @return data
	 */
	public T peek() {
		if (top == null) {
			return null;
		}
		return top.getData();
	}

	/**
	 * o(1)
	 * @return size
	 */
	public int size() {
		return size;
	}
	
	/**
	 *  o(1)
	 * @return true if empty
	 */
	public boolean isEmpty() {
		return top==null;
	}

}

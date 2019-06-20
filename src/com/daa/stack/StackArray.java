package com.daa.stack;

public class StackArray<T extends Comparable<T>> {

	private Object[] top;
	
	private int size;
	
	public StackArray(int capacity) {
		top=new Object[capacity];
	}

	public StackArray() {
		top=new Object[10];
	}
	
	/**
	 * push the data on top of stack - o(1)
	 * @param data
	 */
	public void push(Object data) {
		if(top.length>size) {
			top[size]=data;
		}
		size++;
	}

	/**
	 * remove the data on top of stack - o(1)
	 * @return data
	 */
	public Object pop() {
		if(size==0) {
			return null;
		}
		Object data = top[size-1];
		size--;
		return data;
	}

	/**
	 * get the data from top of stack without deleting it - o(1)
	 * @return data
	 */
	public Object peek() {
		if(size==0) {
			return null;
		}
		return top[0];
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
		return size==0;
	}

}

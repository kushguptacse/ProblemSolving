package com.daa.queue;

import java.util.Deque;
import java.util.LinkedList;


/**
 * Insert data in the FIFO order implement Queue using stack.
 * 
 * @f:off
 * we take two stacks. 
 * Insert element in stack1. on delete if stack1 is empty move all
 * items from stack1 to stack 2. 
 * stack2.pop is the answer.
 * @f:on
 * 
 * @author G521885
 *
 * @param <T>
 */
public class QueueUsingStack<T extends Comparable<T>> {

	private Deque<T> stack1 = new LinkedList<>();
	private Deque<T> stack2 = new LinkedList<>();
	private int size;

	/**
	 * add the data to rear of queue
	 * 
	 * @param data
	 */
	public void add(T data) {
		stack1.push(data);
	}

	/**
	 * remove the data from front
	 * 
	 * @return data
	 */
	public T poll() {
		copyStack();
		return stack2.isEmpty() ? null : stack2.pop();
	}

	/**
	 * get the data from front without deleting it
	 * 
	 * @return data
	 */
	public T peek() {
		copyStack();
		return stack2.isEmpty() ? null : stack2.peek();
	}

	/**
	 * if stack2 is empty move data from stack1 to stack2
	 */
	private void copyStack() {
		if (stack2.isEmpty()) {
			while(!stack1.isEmpty()) {
				stack2.push(stack1.pop());
			}
		}
	}

	/**
	 * o(1)
	 * 
	 * @return size
	 */
	public int size() {
		return size;
	}

	/**
	 * o(1)
	 * 
	 * @return true if empty
	 */
	public boolean isEmpty() {
		return stack1.isEmpty() && stack2.isEmpty();
	}

}

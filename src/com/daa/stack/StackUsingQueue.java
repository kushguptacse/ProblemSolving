package com.daa.stack;

import java.util.LinkedList;
import java.util.Queue;

/**
 * In push method just move all the elements back to the queue except the newly
 * inserted one. in this way pop nd peek will return newly inserted element just
 * like stack.
 * 
 * o(n) - push
 * 
 * o(1)-pop,peek
 * 
 * @author G521885
 *
 */
public class StackUsingQueue {

	private Queue<Integer> qa = new LinkedList<>();

	/** Push element x onto stack. */
	public void push(int x) {
		int n = qa.size();
		qa.add(x);
		while (n-- > 0) {
			qa.add(qa.poll());
		}
	}

	/** Removes the element on top of the stack and returns that element. */
	public int pop() {
		return qa.poll();
	}

	/** Get the top element. */
	public int top() {
		return qa.peek();
	}

	/** Returns whether the stack is empty. */
	public boolean empty() {
		return qa.isEmpty();
	}
}

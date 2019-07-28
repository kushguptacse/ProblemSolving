package com.daa.stack;

import java.util.Stack;

/**
 * Given a Stack, keep track of the maximum value in it. The maximum value may be the top
 * element of the stack, but once a new element is pushed or an element is pop from the
 * stack, the maximum element will be now from the rest of the elements.
 * 
 * @f:off
 * two stack needed. one will keep original element for basic push,pop,peek operation
 * and other stack will store the max element.
 * during inserting new item. check if maxStack peek > newItem. 
 * if yes, add top of maxStack again. else add newItem to maxStack.
 * in both cases originalStack will contain all the items.
 * 
 * o(1) time to find max element and o(n) extra space needed.
 * Input : 4 19 7 14 20
 * Output : Max Values in stack are : 4 19 19 19 20
 * @f:on
 * 
 * @author G521885
 *
 */
public class StackMaxElement {

	private final Stack<Integer> stack = new Stack<>();
	private final Stack<Integer> maxStack = new Stack<>();

	//o(1)
	public void push(int data) {
		stack.push(data);
		if (!maxStack.isEmpty() && maxStack.peek() > data) {
			maxStack.push(maxStack.peek());
		} else {
			maxStack.push(data);
		}
	}

	//o(1)
	public int getMax() {
		return maxStack.isEmpty() ? -1 : maxStack.peek();
	}

	public int peek() {
		return stack.isEmpty() ? -1 : stack.peek();
	}
	
	//o(1)
	public int pop() {
		if (stack.isEmpty()) {
			return -1;
		}
		Integer i = stack.pop();
		maxStack.pop();
		return i;
	}

}

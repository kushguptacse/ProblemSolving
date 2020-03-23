package com.daa.stack;

/**
 * Given a Stack, keep track of the maximum value in it. The maximum value may be the top
 * element of the stack, but once a new element is pushed or an element is pop from the
 * stack, the maximum element will be now from the rest of the elements.
 * 
 * @f:off
 * one stack needed. and one variable max is needed.
 * while inserting -
 * 1. check if stack is empty. if yes add element and set max= newItem.
 * 2. else 
 * 2.1. check if newItem x>max.if yes, push x+max to stack and update max=x. else push x in stack. 
 * 
 * pop - 
 * 1. pop from stack. if y is greater than max -> set max=y-max and return old max.
 * else return y
 * 
 * getMax - 
 * 1. return max variable.
 * 
 * o(1) time to find max element and o(1) extra space needed.
 * Input : 4 19 7 14 20
 * Output : Max Values in stack are : 4 19 19 19 20
 * @f:on
 * 
 * @author G521885
 *
 */
public class StackMaxElementImproved {

	private final Deque<Integer> stack = new Deque<>();
	private int max = -1;

	// o(1)
	/**
	 * 
	 * @f:off
	 * 1. check if stack is empty. if yes add element and set max = newItem.
	 * 2. else 
	 * 2.1. check if newItem x>max .if yes, push x+max to stack and update max=x. else push x in stack. 
	 * @f:on
	 * 
	 * @param data
	 */
	public void push(int data) {
		if (stack.isEmpty()) {
			stack.push(data);
			max = data;
		} else {
			if (max < data) {
				stack.push(max + data);
				max = data;
			} else {
				stack.push(data);
			}
		}
	}

	// o(1)
	/**
	 * 
	 * @return get max element from stack
	 */
	public int getMax() {
		return max;
	}

	/**
	 * @f:off
	 * 1. peek from stack. if y is greater than max ->  return max.
	 * else return y
	 * @f:on
	 * @return top element from stack
	 */
	public int peek() {
		if (stack.isEmpty()) {
			return -1;
		}

		int item = stack.peek();
		if (item > max) {
			item = max;
		}
		return item;
	}

	// o(1)
	/**
	 * 1. pop from stack. if y is greater than max -> set max=y-max and return old max.
	 * else return y
	 * 
	 * @return popped item
	 */
	public int pop() {
		if (stack.isEmpty()) {
			return -1;
		}

		int item = stack.pop();
		int res = item;
		if (item > max) {
			res = max;
			max = item - max;
		}
		return res;
	}

}

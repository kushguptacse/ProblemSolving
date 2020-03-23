package com.daa.queue;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Insert data in the FIFO order implement Queue using stack.
 * 
 * @f:off
 * we take one stack. and for other stack we will method call stack.
 *  
 * Insert element in stack1. 
 * on delete - if stack.size==1 return stack.pop.
 * store stack.pop() into item and call poll again. after that add item back to stack. 
 * @f:on
 * 
 * @author G521885
 *
 * @param <T>
 */
public class QueueUsingStackRecursion<T extends Comparable<T>> {

	public static void main(String[] args) {
		QueueUsingStackRecursion<Integer> obj = new QueueUsingStackRecursion<>();
		obj.add(1);
		obj.add(2);
		obj.add(3);
		System.out.println("size "+obj.size());
		System.out.println("poll : "+ obj.poll());
		System.out.println("size "+obj.size());
		System.out.println("poll : "+ obj.poll());
		System.out.println("poll : "+ obj.poll());
		System.out.println("poll : "+ obj.poll());
		obj.add(4);
		System.out.println("poll : "+ obj.poll());
		obj.add(5);
		obj.add(6);
		System.out.println("poll : "+ obj.poll());
		System.out.println("poll : "+ obj.poll());
		System.out.println("size "+obj.size());
	}

	private Deque<T> stack1 = new LinkedList<>();

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
		if (stack1.isEmpty()) {
			return null;
		}
		if (stack1.size() == 1) {
			return stack1.pop();
		}
		T item = stack1.pop();
		T result = poll();
		stack1.push(item);
		return result;
	}

	/**
	 * o(1)
	 * 
	 * @return true if empty
	 */
	public boolean isEmpty() {
		return stack1.isEmpty();
	}

	public int size() {
		return stack1.size();
	}

}

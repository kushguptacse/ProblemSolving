package com.daa.stack;

import java.util.PriorityQueue;

import com.daa.model.Pair;

/**
 * implement stack using PriorityQueue (min heap).
 * 
 * we store pair in heap. where first value is the counter which we keep on decrementing
 * and second value as original value passed. by this whenever we add new element it will
 * be the new minimum and hence be at the top of min-heap. so pop, will always return the
 * latest inserted item.
 * 
 * @author G521885
 *
 * @param <T>
 */
public class StackUsingHeap<T extends Comparable<T>> {

	private PriorityQueue<Pair<Integer, T>> queue = new PriorityQueue<>((o1, o2) -> o1.getFirst().compareTo(o2.getFirst()));
	private int c = Integer.MAX_VALUE;

	/**
	 * push the data on top of stack - o(logn)
	 * 
	 * @param data
	 */
	public void push(T data) {
		queue.add(new Pair<>(--c, data));
	}

	/**
	 * remove the data on top of stack - o(logn)
	 * 
	 * @return data
	 */
	public T pop() {
		return queue.poll().getSecond();
	}

	/**
	 * get the data from top of stack without deleting it - o(1)
	 * 
	 * @return data
	 */
	public T peek() {
		return queue.peek().getSecond();
	}

	/**
	 * o(1)
	 * 
	 * @return size
	 */
	public int size() {
		return queue.size();
	}

	/**
	 * o(1)
	 * 
	 * @return true if empty
	 */
	public boolean isEmpty() {
		return queue.isEmpty();
	}

}

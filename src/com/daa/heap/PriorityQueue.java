package com.daa.heap;

public interface PriorityQueue<T extends Comparable<T>> {

	public int size();
	public void add(T element);
	public T peek();
	public T poll();
	
}

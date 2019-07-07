package com.daa.heap;

/**
 * max heap implementation
 * 
 * @author G521885
 *
 */
public class Heap implements PriorityQueue<Integer> {

	private int size;
	private final Integer[] heapArray;
	private static final int DEFAULT_CAPACITY = 10;

	public Heap() {
		heapArray = new Integer[DEFAULT_CAPACITY];
	}

	public Heap(int initialCapicity) {
		heapArray = new Integer[initialCapicity];
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void add(Integer element) {
		checkRange();
		heapArray[size] = element;
		// fix up wards
		int child = size;
		int parent = (size - 1) / 2;
		while (child > 0 && heapArray[parent] < heapArray[child]) {
			swap(parent, child);
			child = parent;
			parent = (parent - 1) / 2;
		}
		size++;

	}

	private void swap(int i, int j) {
		Integer temp = heapArray[i];
		heapArray[i] = heapArray[j];
		heapArray[j] = temp;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	private void checkRange() {
		if (heapArray.length == size) {
			throw new ArrayIndexOutOfBoundsException("heap size limit crossed");
		}
	}

	@Override
	public Integer peek() {
		if (isEmpty()) {
			return null;
		}
		return heapArray[0];
	}

	@Override
	public Integer poll() {
		if (isEmpty()) {
			return null;
		}
		Integer item = heapArray[0];
		heapArray[0] = heapArray[--size];
		// fix down wards
		fixDown(0);
		return item;
	}

	private void fixDown(int i) {
		int lchild = 2 * i + 1;
		int rchild = 2 * i + 2;
		if(rchild<size) {
			lchild = findMax(lchild, rchild);			
		}
		if (rchild <= size && heapArray[lchild] > heapArray[i]) {
			swap(lchild, i);
			fixDown(lchild);
		}
	}

	private int findMax(int i, int child) {
		return heapArray[i] > heapArray[child] ? i : child;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(size());
		for (int i = 0; i < size(); i++) {
			sb.append(heapArray[i] + ",");
		}
		return sb.toString();
//		return "Heap [heapArray=" + Arrays.toString(heapArray) + "]"
	}

}

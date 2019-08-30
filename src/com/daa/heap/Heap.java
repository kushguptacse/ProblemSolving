package com.daa.heap;

/**
 * max heap implementation.
 * 
 * if we have array of size n. we can call buildHeap and it will convert it to in heap- o(n) to add
 * element in heap it will take -logn time and if we wanted to add n elements one by one
 * it will take nlogn time
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

	/**
	 * update the value present in given index with the value passed. After updating value
	 * heapify is done. to maintain the heap properties. o(logn)
	 * 
	 * @param index
	 * @param value
	 */
	public void updateKey(int index, int value) {
		if (index >= size || isEmpty()) {
			return;
		}
		System.out.println("Updated value present at index : " + index + " to " + value);
		heapArray[index] = value;
		fixDown(index);
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

	/**
	 * heapify tree
	 * 
	 * @param i
	 */
	private void fixDown(int i) {
		int first = 2 * i + 1;
		int second = 2 * i + 2;
		if (second < size) {
			first = findMax(first, second);
		}
		if (second <= size && heapArray[first] > heapArray[i]) {
			swap(first, i);
			fixDown(first);
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

	/**
	 * Delete element from Max Heap array.
	 * 
	 * @f:on
	 * first find the element you wanted to delete. 
	 * swap element with last element.
	 * heapify(fix down) from index deleted.
	 * @f:off
	 * 
	 * @param maxHeap
	 * @param item to be deleted
	 */
	public  void deleteByValue(int item) {
		for(int i=0;i<size;i++) {
			if(item==heapArray[i]) {
				swap(--size, i);
				fixDown(i);
				break;
			}
		}
	} 
	
	public Integer deleteByIndex(int index) {
		if(index>=size) {
			return null;
		}
		int res = heapArray[index];
		swap(--size, index);
		fixDown(index);
		return res;
	}
	
}

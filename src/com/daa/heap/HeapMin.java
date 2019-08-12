package com.daa.heap;

/**
 * min heap implementation
 * 
 * @author G521885
 *
 */
public class HeapMin<T extends Comparable<T>> implements PriorityQueue<T> {

	private int size;
	private final Object[] heapArray;
	private static final int DEFAULT_CAPACITY = 10;

	@SuppressWarnings("unchecked")
	public HeapMin() {
		heapArray = new Object[DEFAULT_CAPACITY];
	}

	@SuppressWarnings("unchecked")
	public HeapMin(int initialCapicity) {
		heapArray = new Object[initialCapicity];
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void add(T element) {
		checkRange();
		heapArray[size] = element;
		// fix up wards
		int child = size;
		int parent = (size - 1) / 2;
		while (child > 0 && ((T)heapArray[parent]).compareTo(((T)heapArray[child])) > 0) {
			swap(parent, child);
			child = parent;
			parent = (parent - 1) / 2;
		}
		size++;

	}

	private void swap(int i, int j) {
		T temp = ((T)heapArray[i]);
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
	public T peek() {
		return isEmpty() ? null : ((T)heapArray[0]);
	}

	/**
	 * update the value present in given index with the value passed. After updating value
	 * heapify is done. to maintain the heap properties. o(logn)
	 * 
	 * @param index
	 * @param value
	 */
	public void updateKey(int index, T value) {
		if (index >= size || isEmpty()) {
			return;
		}
		System.out.println("Updating value present at index : " + index + " to " + value);
		heapArray[index] = value;
		fixDown(index);
	}

	@Override
	public T poll() {
		if (isEmpty()) {
			return null;
		}
		T item = ((T)heapArray[0]);
		heapArray[0] = heapArray[--size];
		// fix down wards
		fixDown(0);
		return item;
	}

	/**
	 * Heapify tree
	 * 
	 * @param i
	 */
	private void fixDown(int i) {
		int first = 2 * i + 1;
		int second = 2 * i + 2;
		if (second < size) {
			first = findMin(first, second);
		}
		if (second <= size && ((T)heapArray[first]).compareTo((T)heapArray[i]) < 0) {
			swap(first, i);
			fixDown(first);
		}
	}

	private int findMin(int i, int child) {
		return ((T)heapArray[i]).compareTo(((T)heapArray[child])) < 0 ? i : child;
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
	public  void deleteByValue(T item) {
		for(int i=0;i<size;i++) {
			if(item==heapArray[i]) {
				swap(--size, i);
				fixDown(i);
				break;
			}
		}
	} 
	
	public T deleteByIndex(int index) {
		if(index>=size) {
			return null;
		}
		T res = ((T)heapArray[index]);
		swap(--size, index);
		fixDown(index);
		return res;
	}
	
}

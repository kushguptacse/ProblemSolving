package com.daa.heap;

import java.util.function.Predicate;

public class HeapMax {

	private final int[] table;
	private int DEFAULT_CAPACITY = 10;
	private int size;

	private Predicate<int[]> isFullPredicate = t -> t.length == getSize();

	public HeapMax(int capacity) {
		table = new int[capacity];
	}

	public HeapMax() {
		table = new int[DEFAULT_CAPACITY];
	}

	public int getSize() {
		return size;
	}

	private void checkRange() {
		if (isFullPredicate.test(table)) {
			throw new ArrayIndexOutOfBoundsException();
		}
	}

	/**
	 * poll will remove max element from heap
	 */
	public int poll() {
		if (size == 0) {
			return -1;
		}
		int res = table[0];
		table[0] = table[--size];
		// fix downwards
		heapify(0);
		return res;
	}

	private void heapify(int index) {
		if (index >= size) {// check if index passed is out of range
			return;
		}
		int l = 2 * index + 1;
		int r = 2 * index + 2;
		if (l >= size) {// check if left child is null
			return;
		}
		int max = l;
		if (r < size) {// check if right also present
			max = table[l] > table[r] ? l : r;
		}
		if (table[max] > table[index]) {
			swap(index, max);
			heapify(max);
		}
	}

	/**
	 * peek will get max element from heap
	 */
	public int peek() {
		return size == 0 ? -1 : table[0];
	}

	public void add(int element) {
		checkRange();
		table[size++] = element;
		// fix upward
		int i = size - 1;
		while (i > 0) {
			int p = (i - 1) / 2;
			if (table[p] < table[i]) {
				swap(i, p);
			} else {
				break;
			}
			i = p;
		}
	}

	private void swap(int i, int p) {
		int temp = table[p];
		table[p] = table[i];
		table[i] = temp;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(getSize());
		for (int i = 0; i < getSize(); i++) {
			sb.append(table[i] + ",");
		}
		return sb.toString();
	}

	public void updateKey(int i, int j) {
		// TODO Auto-generated method stub

	}

	public void deleteByValue(int data) {
		// TODO Auto-generated method stub

	}

	public String deleteByIndex(int i) {
		// TODO Auto-generated method stub
		return null;
	}

}

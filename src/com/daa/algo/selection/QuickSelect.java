package com.daa.algo.selection;

import java.util.Arrays;

/**
 * Hoare Algorithm - udemy
 * 
 * @f:off
 * 
 * Find the kth smallest or kth largest element in un-sorted array.
 * quick select is just the modification of quick sort.
 * It takes avg case - O(n)
 * worst case - o(n^2) . i.e when pivot is chosen from beginning or end just like quick sort 
 * 
 * We can also use heap data structure to do that. But it take (O(NlogK) time complexity and O(K) space)
 * 
 * so, it is a trade off which approach should we choose out of quick select or heap.
 * if k is small heap should be preferred.
 * 
 * @f:on
 */
public class QuickSelect {

	public static void main(String[] args) {
		QuickSelect qs = new QuickSelect();
		int[] a = { 6, 3, 1, 7, -2, 0 };
		System.out.println(Arrays.toString(a));
		for (int i = 1; i <= a.length; i++) {
			System.out.println(i + " min element:" + qs.findkMinElement(a, i));
		}
		int[] arr = { 10, 4, 5, 8, 6, 11, 26 };
		System.out.println(Arrays.toString(arr));
		for (int i = 1; i <= arr.length; i++) {
			System.out.println(i + " min element:" + qs.findkMinElement(arr, i));
		}
	}

	/**
	 * Find the kth smallest or kth largest element in unsorted array.
	 * 
	 * @return kth max element
	 */
	public int findkMinElement(int[] arr, int k) {
		return kthSmallest(arr, 0, arr.length - 1, k - 1);
	}

	/**
	 * 
	 * @param arr
	 * @param l
	 * @param r
	 * @return kth min element
	 */
	private int kthSmallest(int[] arr, int l, int r, int k) {
		if (l <= r) {
			int p = partition(arr, l, r);
			if (k == p) {
				return arr[p];
			} else if (k < p) {
				return kthSmallest(arr, l, p - 1, k);
			}
			return kthSmallest(arr, p + 1, r, k);
		}
		return -1;

	}

	/**
	 * See SortUtil partition method for complete description
	 * 
	 * @param arr
	 * @param start
	 * @param end
	 * @return pivot
	 */
	public int partition(int[] arr, int start, int end) {
		int initPivot = (start + end) / 2;
		swap(arr, initPivot, end);
		// after that end index will be our pivot. and we will put pivot to its correct
		// position after loop terminates
		for (int i = start; i < end; i++) {
			if (arr[i] < arr[end]) {
				swap(arr, i, start);
				start++;
			}
		}
		swap(arr, start, end);
		return start;
	}

	/**
	 * 
	 * @param arr
	 * @param i
	 * @param j
	 */
	private void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

}

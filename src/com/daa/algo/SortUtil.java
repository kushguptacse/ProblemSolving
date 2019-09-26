package com.daa.algo;

import java.util.Arrays;

public class SortUtil {

	private SortUtil() {
		throw new RuntimeException("object creation not allowed");
	}

	public static void main(String[] args) {
		int[] ar = new int[] { 1, 3, 5, 2, 4 };
		mergeSort(ar);
		System.out.println(Arrays.toString(ar));
	}

	/**
	 * O(n^2) - Array after sort
	 * 
	 * bubble sort - compare 0 element with 1 and arrange them. then it take 1
	 * element with 2 and arrange. so after 1st iteration largest element moved to
	 * the end of array. from next iteration onwards we will start from 0 and ignore
	 * last element as it is already in the correct position.
	 * 
	 * @param t
	 */
	public static <T extends Comparable<T>> void bubbleSort(T[] t) {
		for (int i = 0; i < t.length - 1; i++) {
			boolean swap = false;
			for (int j = 0; j < t.length - i - 1; j++) {
				if (t[j].compareTo(t[j + 1]) > 0) {
					swap(t, j, j + 1);
					swap = true;
				}
			}
			if (!swap) {
				break;
			}
		}
	}

	/**
	 * O(n^2)
	 * 
	 * selection sort - find minimum element from 0 to n first and then swap 0th
	 * with min. So, after first iteration first element of array is sorted after
	 * that find min from 1 to n. and repeat it
	 * 
	 * so after 1st iteration largest element moved to the end of array. from next
	 * iteration onwards we will start from 0 and ignore last element as it is
	 * already in the correct position.
	 * 
	 * @param array to be sorted t
	 */
	public static <T extends Comparable<T>> void selectionSort(T[] t) {
		for (int i = 0; i < t.length; i++) {
			int minIndex = i;
			for (int j = i + 1; j < t.length; j++) {
				if (t[minIndex].compareTo(t[j]) > 0) {
					minIndex = j;
				}
			}
			swap(t, i, minIndex);
//			System.out.print("After Pass "+(i+1)+" : ");
//			Arrays.stream(t).forEach(System.out::print);
//			System.out.println();
		}
	}

	/**
	 * O(n^2)
	 * 
	 * insertion sort - it is just like playing with cards.
	 * 
	 * insert ith element in between 0 to i-1 position where it needed to be
	 * present. 3,1,2
	 * 
	 * So, after ith iteration i-1 elements are sorted.
	 * 
	 * @param array to be sorted t
	 */
	public static <T extends Comparable<T>> void insertionSort(T[] t) {
		for (int i = 1; i < t.length; i++) {
			for (int j = 0; j < i; j++) {
				if (t[i].compareTo(t[j]) < 0) {
					swap(t, i, j);
				}
			}
//			System.out.print("After Pass "+(i)+" : ");
//			Arrays.stream(t).forEach(s->System.out.print(s+","));
//			System.out.println();
		}
	}

	/**
	 * Swap a[i] with a[j]
	 * 
	 * @param a
	 * @param i
	 * @param j
	 */
	private static <T> void swap(T[] a, int i, int j) {
		T temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	public static void mergeSort(int[] arr) {
		mergeSort(arr, 0, arr.length - 1);
	}

	private static void mergeSort(int[] arr, int i, int j) {
		if (i < j) {
			int mid = (i + j) / 2;
			mergeSort(arr, i, mid);
			mergeSort(arr, mid + 1, j);
			merge(arr, i, j, mid);
		}
	}

	private static void merge(int[] arr, int l, int r, int mid) {
		int[] temp = new int[r - l + 1];
		int i = l;
		int j = mid + 1;
		int k = 0;
		while (i <= mid && j <= r) {
			if (arr[i] <= arr[j]) {
				temp[k++] = arr[i++];
			} else {
				temp[k++] = arr[j++];
			}
		}

		while (i <= mid) {
			temp[k++] = arr[i++];
		}
		while (j <= r) {
			temp[k++] = arr[j++];
		}

		for (int p = l; p <= r; p++) {
			arr[p] = temp[p - l];
		}
	}

}

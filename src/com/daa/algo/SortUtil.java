package com.daa.algo;

import java.util.Arrays;

/**
 * Stable algo - Stable sorting algorithms maintain the relative order of
 * records with equal keys (i.e. values). That is, a sorting algorithm is stable
 * if whenever there are two records R and S with the same key and with R
 * appearing before S in the original list, R will appear before S in the sorted
 * list
 * 
 * In-place algorithm is an algorithm which transforms input using no auxiliary
 * data structure. However a small amount of extra storage space is allowed for
 * auxiliary variables
 * 
 * An adaptive algorithm is an algorithm that changes its behavior at the time
 * it is run, based on information available and on a priori defined reward
 * mechanism. e.g. shell sort. it takes into consideration some part of data
 * already sorted. and hence work faster on such cases where such type of chunk
 * exists
 * 
 * @author G521885
 *
 */
public final class SortUtil {

	private SortUtil() {
		throw new RuntimeException("object creation not allowed");
	}

	public static void main(String[] args) {
		int[] ar = new int[] { 15, 17, 13, 16, 14 };
		System.out.println("Quick sort -> ");
		quickSort(ar);
		System.out.println(Arrays.toString(ar));
		ar = new int[] { 15, 12, 13, 11, 20, 15, 22, 14 };
		System.out.println("Quick sort -> ");
		quickSort(ar);
		System.out.println(Arrays.toString(ar));

		ar = new int[] { 6, 5, 9, 0, 8, 2, 4, 7 };
		System.out.println("Quick sort - ");
		quickSort(ar);
		System.out.println(Arrays.toString(ar));

		ar = new int[] { 10, 7, 8, 9, 1, 5 };
		System.out.println("Quick sort = ");
		quickSort(ar);
		System.out.println(Arrays.toString(ar));

		ar = new int[] { 5, 0, 4, 3, 2, 11 };
		System.out.println("Quick sort : ");
		quickSort(ar);
		System.out.println(Arrays.toString(ar));

		System.out.println("Insertion sort");
		insertionSort(new Integer[] { 34, -3, 0, 2, 14, 8, -1, 24 });
		System.out.println(Arrays.toString(ar));
		System.out.println("Merge sort");
		mergeSort(ar);
		System.out.println(Arrays.toString(ar));
	}

	/**
	 * 
	 * In-place,I,stable
	 * 
	 * O(n^2) - Array after sort
	 * 
	 * bubble sort - compare 0 element with 1 and arrange them. then it take 1
	 * element with 2 and arrange. so after 1st iteration largest element moved to
	 * the end of array. from next iteration onwards we will start from 0 and ignore
	 * last element as it is already in the correct position.
	 * 
	 * @param array
	 */
	public static <T extends Comparable<T>> void bubbleSort(T[] array) {
		for (int i = 0; i < array.length - 1; i++) {
			boolean swap = false;
			for (int j = 0; j < array.length - i - 1; j++) {
				if (array[j].compareTo(array[j + 1]) > 0) {
					swap(array, j, j + 1);
					swap = true;
				}
			}
			if (!swap) {
				break;
			}
		}
	}

	/**
	 * In-place,I
	 * 
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
	public static <T extends Comparable<T>> void selectionSort(T[] array) {
		for (int i = 0; i < array.length; i++) {
			int minIndex = i;
			for (int j = i + 1; j < array.length; j++) {
				if (array[minIndex].compareTo(array[j]) > 0) {
					minIndex = j;
				}
			}
			swap(array, i, minIndex);
//			System.out.print("After Pass "+(i+1)+" : ");
//			Arrays.stream(t).forEach(System.out::print);
//			System.out.println();
		}
	}

	/**
	 * In-place,I,adaptive,stable
	 * 
	 * O(n^2)
	 * 
	 * insertion sort - it is just like playing with cards.
	 * 
	 * on small data set insertion sort, selection sort works better than merge or
	 * quick sort.
	 * 
	 * insertion sort requires more swap as compared to selection sort and hence is
	 * not preferred where write operation is costly. on other hand insertion sort
	 * is more efficient than bubble or selection as it speeds up when array is
	 * already partially sorted.
	 * 
	 * insert ith element in between 0 to i-1 position where it needed to be
	 * present. 3,1,2
	 * 
	 * So, after ith iteration i-1 elements are sorted.
	 * 
	 * @param array to be sorted t
	 */
	public static <T extends Comparable<T>> void insertionSort(T[] array) {
		for (int i = 1; i < array.length; i++) {
			for (int j = 0; j < i; j++) {
				if (array[i].compareTo(array[j]) < 0) {
					swap(array, i, j);
				}
			}
			System.out.print("After Pass " + (i) + " : ");
			Arrays.stream(array).forEach(s -> System.out.print(s + ","));
			System.out.println();
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

	/**
	 * Ravindra,geeks
	 * @f:off
	 * 
	 * In-place,Not Stable,Not adaptive,Divide and conquer
	 * 
	 * Perform quick sort algo to sort the data. for primitive it is preferred and for objects merge sort is preferred
	 * 
	 * Steps-
	 * For every step we will take one element as pivot(here the first one) and will try to put it in right position. 
	 * by right position we mean that all element to the left of it are small and right to it are greater.
	 * so, basically we fixing pivot position one by one.
	 * 
	 * o(nlogn) - average case o(n^2) - worst case.
	 * 
	 * @f:on
	 * 
	 * @param arr
	 */
	public static void quickSort(int[] arr) {
		quickSortUtil(arr, 0, arr.length - 1);
	}

	private static void quickSortUtil(int[] arr, int low, int high) {
		if (low < high) {
			int p = partition(arr, low, high);
			quickSortUtil(arr, low, p - 1);
			quickSortUtil(arr, p + 1, high);
		}
	}

	/**
	 * partition
	 * 
	 * @f:off
	 * 1. select low index element as pivot and i = high+1.
	 * 2. go from j=high to low-1
	 * 3. check for every j that whether that element is greater than pivot
	 * 4. if yes decrement i and swap i with j.
	 * 
	 * after loop terminates swap pivot with i-1. and return i-1 which is our new pivot
	 * e.g.
	 * 15,17,13,16,14
	 * p            j,i
	 * 
	 * step 1 - 
	 * 15,17,13,16,14
	 * p		j    ,i
	 * 
	 * 15,17,13,14,16
	 * p	 j	   i
	 * 
	 * 15,17,13,14,16
	 * p  j        i
	 * 
	 * 15,14,13,17,16
	 * p        i   
	 * j
	 * 
	 * so,now swap p = i-1. and return i-1 i.e. index 2. so, that we will have two partition {13,14} {17,16} left
	 * 
	 * @f:on
	 * 
	 * @param arr
	 * @param low
	 * @param high
	 * @return new pivot
	 */
	private static int partition(int[] arr, int low, int high) {
		int pivot = low;
		int i = high + 1;
		for (int j = high; j > low; j--) {
			if (arr[j] > arr[pivot]) {
				i--;
				swapIntArray(arr, i, j);
			}
		}
		swapIntArray(arr, pivot, i - 1);
		return i - 1;
	}

	/**
	 * swap i index element with j index element
	 * 
	 * @param a
	 * @param i
	 * @param j
	 */
	private static void swapIntArray(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	/**
	 * Divide and conquer,R,stable algo,not in-place,not adaptive, nlogn
	 * 
	 * @param arr
	 */
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

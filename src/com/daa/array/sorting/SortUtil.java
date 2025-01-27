package com.daa.array.sorting;

import java.util.Arrays;

import com.daa.math.MathUtil;

/**
 * Stable algorithm - Stable sorting algorithms maintain the relative order of
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
 * @f:off
 * HYBRID ALGORITHM - it is combination of two or more sorting algorithm to take advantage of both algo.
 * e.g. TIMSORT - insertion sort + merge sort. 
 * As Insertion sort is faster then both merge or quick sort if elements are small e.g. <10. 
 * for large data set it uses merge sort.
 * 
 * e.g. INTROSORT - Quicksort + Heapsort. 
 * as bad pivot selection can lead to o(n^2) in quick sort worst case. it uses hybrid of both 
 * @f:on
 * 
 */
public final class SortUtil {

	private SortUtil() {
		throw new RuntimeException("object creation not allowed");
	}

	public static void main(String[] args) {
		int[] ar = new int[] { 15, 17, 13, 16, 14 };
		System.out.println("before Quick sort -> "+Arrays.toString(ar));
//		countingSort(ar);
		quickSort(ar);
		System.out.println("after Quick sort -> "+Arrays.toString(ar));
		ar = new int[] { 15, 12, 13, 11, 20, 15, 22, 14 };
		System.out.println("before Quick sort -> "+Arrays.toString(ar));
//		countingSort(ar);
		quickSort(ar);
		System.out.println("after Quick sort -> "+Arrays.toString(ar));
		ar = new int[] { 6, 5, 9, 0, 8, 2, 4, 10 };
		System.out.println("before Quick sort - "+Arrays.toString(ar));
//		countingSort(ar);
		quickSort(ar);
		System.out.println("after Quick sort -> "+Arrays.toString(ar));
		ar = new int[] { 10, 7, 8, 9, 1, 5 };
		System.out.println("before Quick sort -> "+Arrays.toString(ar));
//		countingSort(ar);
		quickSort(ar);
		System.out.println("after Quick sort -> "+Arrays.toString(ar));

		ar = new int[] { 5, 0, 4, 3, 2, 11 };
		System.out.println("before Quick sort -> "+Arrays.toString(ar));
//		countingSort(ar);
		quickSort(ar);
		System.out.println("after Quick sort -> "+Arrays.toString(ar));

		System.out.println("Insertion sort");
		insertionSort(new Integer[] { 34, -3, 0, 2, 14, 8, -1, 24 });
		countingSort(new int[] { 34, -3, 0, 2, 14, 8, -1, 24 });
		countingSort(ar);
		ar=new int[] { 34, -3, 0, 2, 14, 8, -1, 24 };
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
		for (int i = 0; i < array.length-1; i++) {
			// if after entire below loop no swap happen then it means array is already sorted 
			boolean swap=false;
			for (int j = 0; j < array.length - i -1; j++) {
				if (array[j].compareTo(array[j+1]) > 0) {
					swap(array, j, j+1);
					swap=true;
				}
			}
			//break as array is now sorted.
			if(!swap) {
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
				if (array[j].compareTo(array[minIndex]) < 0) {
					minIndex = j;
				}
			}
			swap(array, i, minIndex);
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
			System.out.println(Arrays.toString(array));
		}
	}

	/**
	 * Non-comparison base algorithm. stable ,not in-place.
	 * this is best suited when we know that element inside array is not having large values. it can handle negative values.
	 * 
	 * But what happen if we have data from 1 to n^2. We will need o(n^2) space. Which is not good.for that radix sort is useful.
	 * 
	 * o(n) 
	 * 
	 * @f:off
	 * In it we just create another array of size max-min+1.
	 * and we use original array element as index of the temp array.(i.e. arr[i]-min will be index) and start incrementing the count of that element
	 * after loop finishes.
	 * we iterate Temp array and start printing index+min to k times. where k is the value present at that index.
	 * @f:on
	 * 
	 * Time Complexity: O(n+k) where n is the number of elements in input array and k is the range of input.
	 * Auxiliary Space: O(n+k)
	 * 
	 * @param arr
	 */
	public static void countingSort(int[] arr) {
		int min = MathUtil.findMin(arr);
		int max = MathUtil.findMax(arr);
		int[] temp = new int[max - min + 1];
		for (int i = 0; i < arr.length; i++) {
			temp[arr[i] - min] = temp[arr[i] - min] + 1;
		}

		System.out.print("Counting sort Data : [");
		for (int i = 0; i < temp.length; i++) {
			while (temp[i] > 0) {
				System.out.print(i + min + " ");
				temp[i] = temp[i] - 1;
			}
		}
		System.out.println("]");
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
	 * udemy,geeks - for algo of partition see below code or udemy
	 * @f:off
	 * 
	 * In-place,Not Stable,Not adaptive,Divide and conquer
	 * 
	 * Perform quick sort algo to sort the data. for primitive it is preferred and for objects merge sort is preferred.
	 * as merge sort takes extra o(n) memory it is not preferred for array. 
	 * but for linked list merge sort does not need extra space
	 * 
	 * Steps-
	 * we will take one element as pivot(here the last one) and will try to put it in right position. 
	 * by right position we mean that all element to the left of it are small and right to it are greater than pivot.
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
			int pivot = partition(arr, low, high);
			quickSortUtil(arr, low, pivot - 1);
			quickSortUtil(arr, pivot + 1, high);
		}
	}

	/**
	 * partition
	 * 
	 * 
	 * @f:off
	 * In partition algorithm we fix the pivot (here high index element) and if current element is less than pivot
	 * we swap current element with low and increment low by 1.
	 * once loop terminates low element will be at index where high should be present so we swap it.
	 * 1. select high index element as pivot.
	 * 2. go from j=low to high-1.
	 * 3. check arr[j] < arr[high] . i.e. current element is smaller than pivot if yes.
	 * 4. swap j with low. and increment low by 1.
	 * 
	 * after loop terminates swap high with low. and return low which is our new pivot position.
	 * e.g.
	 * 15, 17, 13, 6, 14
	 * low            high  
	 * j             
	 * 
	 * step 1 - 
	 * 15, 17, 13, 6, 14
	 * low	j	       high
	 * 
	 * 15, 17, 13, 6, 14
	 * low	 	j      high
	 * 
	 * 13, 17, 15, 6, 14
	 *     low     j  high
	 * 
	 * 13, 6, 15, 17, 14
	 *        low      high
	 * 
	 * 
	 * So,now swap high with low. and return low i.e. index 2. 
	 * So,that we will have two partition {13,6} {17,14} now and index 2 element is fixed
	 * 
	 * In this algorithm to save variable we will consider high as pivot.
	 * @f:on
	 * 
	 * @param arr
	 * @param low
	 * @param high
	 * @return new pivot
	 */
	private static int partition(int[] arr, int low, int high) {

		// In worst case it will be o(n^2). i.e. if data is already sorted
		// we can improve it by using middle element as pivot and then before starting
		// anything just swap it with high. so, that high will remain pivot
		// Find mid of the array
		int mid = (low + high) / 2;
		// swap array element present in mid and high index
		swapIntArray(arr, mid, high);
		// now pivot is high again.
		// we goes from low till high-1 because high is reserved for pivot and at end
		// of loop we will swap pivot with the current low.
		for (int j = low; j < high; j++) {
			if (arr[j] < arr[high]) {
				swapIntArray(arr, j, low);
				low++;
			}
		}
		// when i reached high,we know that low is in pivot position. so we just swap it
		swapIntArray(arr, high, low);
		return low;
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
	 * Divide and conquer,R,stable algo,not in-place,not adaptive, time- nlogn,space -o(n)
	 * 
	 * @param arr
	 */
	public static void mergeSort(int[] arr) {
		System.out.print("Merge sort - ");
		System.out.println(Arrays.toString(arr)); 
		mergeSort(arr, 0, arr.length - 1);
	}

	/**
	 * UDEMY,GEEKS
	 * 
	 * @f:off
	 * Approach - 
	 * 1.Divide the array into two sub array recursively.
	 * 2.sort these sub arrays with merge sort again.
	 * 3.when only single item left in sub array. we consider it to be sorted.
	 * 4.Merge the sub array to get final sorted array.
	 * 
	 * Best and worst case  - o(nlogn)
	 * space - o(n)
	 * 
	 * @f:on
	 * 
	 * Here we are dividing array into smallest unit and after that we start combining them.

	 * @param arr
	 * @param i
	 * @param j
	 */
	private static void mergeSort(int[] arr, int i, int j) {
		if (i < j) {
			int mid = (i + j) / 2;
			mergeSort(arr, i, mid); // divide left sub array
			mergeSort(arr, mid + 1, j); // divide right sub array
			merge(arr, i, j, mid); // merge the two sorted array.
		}
	}

	/**
	 * 
	 * merge the two sorted array into single sorted array
	 * 
	 * o(n+m)
	 * 
	 * it takes o(n+m) extra space.
	 * 
	 * @param arr
	 * @param l
	 * @param r
	 * @param mid
	 */
	private static void merge(int[] arr, int l, int r, int mid) {
		int[] temp = new int[r - l + 1];
		int i = l;
		int j = mid + 1;
		int count = 0;
		while (i <= mid && j <= r) {
			if (arr[i] <= arr[j]) {
				temp[count++] = arr[i++];
			} else {
				temp[count++] = arr[j++];
			}
		}
		while (i <= mid) {
			temp[count++] = arr[i++];
		}
		while (j <= r) {
			temp[count++] = arr[j++];
		}
		for (int p = l; p <= r; p++) {
			arr[p] = temp[p - l];
		}
	}

}

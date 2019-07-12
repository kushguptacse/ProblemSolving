package com.daa.heap;

public final class HeapUtil {

	private HeapUtil() {

	}

	public static void main(String[] args) {
		int[] arr = new int[] { 1, 5, 14, 2, 10, 21, 18, 3, 11, 8, 7, 12 };
		System.out.println("maximum element from min heap : " + findMaxFromMinHeap(arr));
//		heapSort(arr);
//		for (int i : arr) {
//			System.out.print(i + " ");
//		}
		System.out.println();

		System.out.println("convert min heap to max heap");
		convertMinHeapToMaxHeap(arr);
		for (int i : arr) {
			System.out.print(i + " ");
		}
		System.out.println();
		System.out.println("is valid max heap : " + isValidMaxHeap(arr));
	}

	/**
	 * sort the array according to heap sort algo o(nlogn)
	 * 
	 * start going from last to first element in array. for each element look downwards and
	 * then adjust that node by swapping. at last for root compare it with max child.
	 * 
	 * it is just like removal. Just we compare from rightmost to start and for each
	 * considered node we compare it till bottom most node like removal. In this way heap is
	 * created of original array.
	 * 
	 * so array can be rebuild to heap in o(n) time. then removal will take o(nlogn) time.
	 * this approach saves memory in a way we don't need to create extra array. as we are not
	 * creating heap by using normal heap insertion. instead we are shuffling existing array.
	 * then removing is same as heap.poll
	 * 
	 * it will take o(nlogn) time
	 * 
	 * @param arr
	 */
	public static void heapSort(int[] arr) {
		// convert array into max heap
		for (int i = arr.length / 2 - 1; i >= 0; i--) {
			heapify(arr, arr.length, i);
		}

		// remove all element one by one.
		for (int i = arr.length - 1; i >= 0; i--) {
			swap(arr, i, 0);
			heapify(arr, i, 0);
		}
	}

	private static void heapify(int[] arr, int length, int i) {
		int lChild = 2 * i + 1;
		int rChild = 2 * i + 2;
		if (rChild < length) {
			lChild = findMaxIndex(arr, rChild, lChild);
		}
		if (rChild <= length && arr[lChild] > arr[i]) {
			swap(arr, lChild, i);
			heapify(arr, length, lChild);
		}
	}

	private static void swap(int[] arr, int j, int i) {
		int temp = arr[j];
		arr[j] = arr[i];
		arr[i] = temp;
	}

	private static int findMaxIndex(int[] arr, int rChild, int lChild) {
		return arr[rChild] > arr[lChild] ? rChild : lChild;
	}

	/**
	 * for all nodes except leaves check whether there exist any node whose child are greater.
	 * if yes return false. and return true in case loop finishes o(n)
	 * 
	 * @param heapArr
	 * @return true if valid max heap
	 */
	public static boolean isValidMaxHeap(int[] heapArr) {
		if (heapArr == null) {
			return false;
		}
		int n = heapArr.length;
		for (int i = (n / 2 - 1); i >= 0; i--) {
			int l = 2 * i + 1;
			int r = 2 * i + 2;
			if (heapArr[l] > heapArr[i]) {
				return false;
			}
			if (r < n && heapArr[r] > heapArr[i]) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Same as first step of heap sort. i.e. just basically convert array into max
	 * heap.(heapify) o(n)
	 * 
	 * @param min
	 */
	public static void convertMinHeapToMaxHeap(int[] min) {
		for (int i = min.length / 2 - 1; i >= 0; i--) {
			heapify(min, min.length, i);
		}
	}

	public static int findMaxFromMinHeap(int[] min) {
		int n = min.length;
		int max = min[(n / 2) - 1];
		for (int i = (n / 2); i < n; i++) {
			if (max < min[i]) {
				max = min[i];
			}
		}
		return max;
	}
}

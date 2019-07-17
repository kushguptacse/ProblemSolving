package com.daa.heap;

import java.util.PriorityQueue;

import com.daa.model.Pair;

public final class HeapUtil {

	private HeapUtil() {

	}

	public static void main(String[] args) {
		int[] arr = { 1, 4, 2, 3, 6, 20, 8, 6, 15, 11, 12, 0, 19 };
		int[][] ar = { { 1, 3 }, { 2, 4, 6 }, { 0, 9, 10, 11 } };
		int[] a={1,5,2,4,9};
		int[] b={8,0,6,10,3};
		printLargestNpairs(a, b);
		arr = mergeKsortedArray(ar);
//		arr = maxKelements(2, arr);
//		int[] arr2 = new int[] { 12, 7, 9 };
//		printNodes(arr, 100);
//		arr = mergeMaxHeaps(arr, arr2);
//		System.out.println("maximum element from min heap : " + findMaxFromMinHeap(arr));
//		heapSort(arr);
//		for (int i : arr) {
//			System.out.print(i + " ");
//		}
//		System.out.println();
//
//		System.out.println("convert min heap to max heap");
//		convertMinHeapToMaxHeap(arr);
		System.out.println("is valid max heap : " + isValidMaxHeap(arr));
		for (int i : arr) {
			System.out.print(i + " ");
		}
		System.out.println();
		int k = 2;
		System.out.println(k + " th smallest element : " + findSmallest2(arr, k));
//		System.out.println(k + " th largest element : " + findLargest(arr, k));
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
		int first = 2 * i + 1;
		int second = 2 * i + 2;
		if (second < length) {
			first = findMaxIndex(arr, second, first);
		}
		if (second <= length && arr[first] > arr[i]) {
			swap(arr, first, i);
			heapify(arr, length, first);
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

	/**
	 * find the maximum element from min heap.
	 * 
	 * max element will always be present in the leaves . so, start from n/2 to n and find the
	 * max o(n)
	 * 
	 * @param min
	 * @return
	 */
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

	/**
	 * print all nodes < k in min heap
	 * 
	 * @param k
	 */
	public static void printNodes(int[] min, int k) {
		System.out.println("Nodes less then: " + k);
		printNodes(min, 0, k);
		System.out.println();
	}

	private static void printNodes(int[] min, int index, int k) {
		if (index >= min.length) {
			return;
		}
		if (min[index] >= k) {
			return;
		}
		System.out.print(min[index] + ",");
		printNodes(min, 2 * index + 1, k);
		printNodes(min, 2 * index + 2, k);
	}

	/**
	 * Take two array and merge them and return max heap.
	 * o(n+m)
	 * 
	 * @f:off
	 * approach is just copy elements from first and second array to result array.
	 * after that heapify the array to construct max heap.
	 * @f:on
	 * 
	 * @param arr1
	 * @param arr2
	 * @return merge array
	 */
	public static int[] mergeMaxHeaps(int[] arr1, int[] arr2) {
		int[] res = new int[arr1.length + arr2.length];
		for (int i = 0; i < arr1.length; i++) {
			res[i] = arr1[i];
		}

		for (int i = 0; i < arr2.length; i++) {
			res[i + arr1.length] = arr2[i];
		}

		for (int i = res.length / 2 - 1; i >= 0; i--) {
			heapify(res, res.length, i);
		}
		return res;
	}

	/**
	 * return the kth smallest element from minHeap.
	 * 
	 * perform k times delete. o(klogn)
	 * 
	 * @param minHeap
	 * @param k
	 * @return kth smallest element
	 */
	public static Integer findSmallest(int minHeap[], int k) {
		Integer res = null;
		for (int i = 0; i < k; i++) {
			res = remove(minHeap, minHeap.length - i - 1);
		}
		return res;
	}

	/**
	 * we can use observation that kth smallest element in min heap is always under kth level.
	 * 
	 * 
	 * @f:off
	 * 	  10
	 *   /   \
	 *  50    40
	 * /  \  /  \ 
	 *75  60 65 45
	 *
	 * here if we want 2nd smallest. then it is at most level 2. 
	 * we don't need to search last level here.
	 * 
	 * approach - 
	 * 1.create a min heap priority Queue P with Pair<value,index> object in it.
	 * 2.add first element of passed min heap array along with index into P.
	 * 3.repeat below step 1 to k-1 times
	 * 3.1. remove the top element from P.
	 * 3.2. add both childs of removed item to P if they exists.
	 * 4.loop terminates.top element of P is the kth smallest of original min heap passed.
	 *
	 * time complexity -
	 * o(klogk)
	 * 
	 * @f:on
	 * 
	 * @param minHeap
	 * @param k
	 * @return kth smallest element from min heap.
	 */
	public static Integer findSmallest2(int minHeap[], int k) {

		PriorityQueue<Pair<Integer, Integer>> queue = new PriorityQueue<>(k, (o1, o2) -> o1.getFirst().compareTo(o2.getFirst()));
		queue.add(new Pair<>(minHeap[0], 0));
		for (int i = 1; i < k; i++) {
			int index = queue.poll().getSecond();
			int left = getLeft(index);
			int right = getRight(index);
			if (left < minHeap.length) {
				queue.add(new Pair<>(minHeap[left], left));
			}
			if (right < minHeap.length) {
				queue.add(new Pair<>(minHeap[right], right));
			}
		}
		return queue.poll().getFirst();
	}

	public static Integer findLargest(int maxHeap[], int k) {

		PriorityQueue<Pair<Integer, Integer>> queue = new PriorityQueue<>(k, (o1, o2) -> o2.getFirst().compareTo(o1.getFirst()));
		queue.add(new Pair<>(maxHeap[0], 0));
		for (int i = 1; i < k; i++) {
			int index = queue.poll().getSecond();
			int left = getLeft(index);
			int right = getRight(index);
			if (left < maxHeap.length) {
				queue.add(new Pair<>(maxHeap[left], left));
			}
			if (right < maxHeap.length) {
				queue.add(new Pair<>(maxHeap[right], right));
			}
		}
		return queue.poll().getFirst();
	}

	/**
	 * find max k elements from unsorted array of billion numbers.
	 * It is similar to finding k maximum elements from infinite unordered stream or very large unordered array.
	 * 
	 * @f:off
	 * approach - 
	 * 1.create a min heap of size k.
	 * 2.start adding items one by one till heap is full.
	 * 3.now just check if new item > root.
	 * 4.if yes swap it and heapify to adjust else don't add it to min heap.
	 * 
	 * so using this approach at any iteration j heap will have k maximum numbers till j items.
	 * 
	 * o(k+(n-k)logk) - where n is the number of items out of which we want to find k maximum elements
	 * 
	 * @f:on
	 * 
	 * @param k
	 * @param stream of un-sorted integer.
	 * 
	 * @return array with k maximum item
	 * 
	 */
	public static int[] maxKelements(int k, int... items) {
		int[] res = new int[k];

		// add first k elements to res - o(k)
		for (int i = 0; i < k; i++) {
			res[i] = items[i];
		}

		// heapify the res array - o(k)
		for (int i = res.length / 2 - 1; i >= 0; i--) {
			heapifyMin(res, res.length, i);
		}

		// start adding rest of elements if item > root
		// o((n-k)(logk))
		for (int i = k; i < items.length; i++) {
			if (res[0] < items[i]) {
				res[0] = items[i];
				heapifyMin(res, res.length, 0);
			}
		}

		return res;
	}

	/**
	 * Receive k sorted arrays (represented by 2d array) and merge them into one sorted array.
	 * 
	 * @f:off
	 * e.g. - 
	 * Input: k = 3 
     * arr[][] = { {1, 3},
     *             {2, 4, 6},
     *             {0, 9, 10, 11}} ;
	 * Output: 0 1 2 3 4 6 9 10 11 
	 * 
	 * approach - 
	 * structure - 
	 * PriorityQueue min heap with size = number of array i.e. k is used. 
	 * 1.store three values in priorityQueue <originalValue,arrayPosition,nextIndex>
	 * 2. original value is used to store priority queue min heap. 
	 * 3. arrayPosition will tell row number and nextIndex will tell next index of array represented by arrayPosition
	 * 
	 * Algorithm -
	 * 1. Store the first element of all array in heap along with array row and next column. <arr[i][0],i,1>
	 * 2. After that iterate till heap is empty.
	 * 2.1 Poll first element from heap and add first element to output array.
	 * 2.2 Insert next element from the array from which the element is extracted using other two variables. 
	 * If the array doesn’t have any more elements, then do nothing.
	 * 3. output array will contain sorted data after heap become empty.
	 * 
	 * o(nlogk)
	 * @f:on
	 * 
	 * @param arr
	 * @return merged array.
	 */
	public static int[] mergeKsortedArray(int[][] arr) {
		int size = 0;
		PriorityQueue<Pair<Integer, Pair<Integer, Integer>>> queue = new PriorityQueue<>(arr.length, (o1, o2) -> o1.getFirst().compareTo(o2.getFirst()));
		for (int i = 0; i < arr.length; i++) {
			size = size + arr[i].length;
			queue.add(new Pair<>(arr[i][0], new Pair<>(i, 1)));
		}

		int[] res = new int[size];
		int i = 0;
		while (!queue.isEmpty()) {
			Pair<Integer, Pair<Integer, Integer>> pair = queue.poll();
			res[i++] = pair.getFirst();
			int arrPos = pair.getSecond().getFirst();
			int nextIndex = pair.getSecond().getSecond();
			if (nextIndex < arr[arrPos].length) {
				queue.add(new Pair<>(arr[arrPos][nextIndex], new Pair<>(arrPos, ++nextIndex)));
			}
		}

		return res;
	}

	/**
	 * Given two array of equal size n. print the max pairs (arr[i],brr[j]).
	 * 
	 * @f:off
	 * e.g. - 
	 * input -
	 * a={1,5,2,4,9}
	 * b={8,0,6,10,3}
	 * 
	 * output - 
	 * <9,10><5,8><4,6><2,3><1,0>
	 * @f:on
	 * 
	 * approach - 
	 * 1.Max heapify both arrays.
	 * 2.start removing element one by one from both heap arrays to get pairs.
	 * o(nlogn)
	 * 
	 * @param arr1
	 * @param arr2
	 */
	public static void printLargestNpairs(int[] arr1, int[] arr2) {
		System.out.println("Largest "+arr1.length+" Pairs are : ");
		// o(n)
		for (int i = arr1.length / 2 - 1; i >= 0; i--) {
			heapify(arr1, arr1.length, i);
		}
		// o(n)
		for (int i = arr2.length / 2 - 1; i >= 0; i--) {
			heapify(arr2, arr2.length, i);
		}
		// o(nlogn)
		for (int i = arr1.length - 1; i >= 0; i--) {
			System.out.print("<"+arr1[0] + "," + arr2[0]+">");
			swap(arr1, i, 0);
			heapify(arr1, i, 0);
			swap(arr2, i, 0);
			heapify(arr2, i, 0);
		}
		System.out.println();

	}

	private static int getLeft(int index) {
		return 2 * index + 1;
	}

	private static int getRight(int index) {
		return 2 * index + 2;
	}

	private static int remove(int[] min, int length) {
		int res = min[0];
		swap(min, length, 0);
		heapifyMin(min, length, 0);
		return res;
	}

	private static void heapifyMin(int[] arr, int length, int i) {
		int first = 2 * i + 1;
		int second = 2 * i + 2;
		if (second < length) {
			first = findMinIndex(arr, second, first);
		}
		if (second <= length && arr[first] < arr[i]) {
			swap(arr, first, i);
			heapifyMin(arr, length, first);
		}
	}

	private static int findMinIndex(int[] arr, int i, int j) {
		return arr[i] < arr[j] ? i : j;
	}

}

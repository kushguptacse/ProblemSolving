package com.daa.heap;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

import com.daa.list.Node;
import com.daa.model.Pair;

public final class HeapUtil {

	private HeapUtil() {

	}

	public static void main(String[] args) {
		int[] arr = { 9, 6, 11, 8, 10, 5, 4, 13, 93, 14 };
		printArray(arr);
		List<Node<Integer>> list = new ArrayList<>();

		Node<Integer> node1 = new Node<>(1);
		node1.setNext(new Node<>(3));
		node1.getNext().setNext(new Node<>(5));
		node1.getNext().getNext().setNext(new Node<>(7));
		list.add(node1);

		Node<Integer> node2 = new Node<>(2);
		node2.setNext(new Node<>(4));
		node2.getNext().setNext(new Node<>(6));
		node2.getNext().getNext().setNext(new Node<>(8));
		list.add(node2);

		Node<Integer> node3 = new Node<>(0);
		node3.setNext(new Node<>(9));
		node3.getNext().setNext(new Node<>(10));
		list.add(node3);

		System.out.println("merged list is : ");
		Node<Integer> result = mergeKsortedList(list);
		while (result != null) {
			System.out.print(result.getData() + "->");
			result = result.getNext();
		}
		System.out.println();
		printMax(arr, 3);
		median(arr);
		int[][] ar = { { 1, 3 }, { 2, 4, 6 }, { 0, 9, 10, 11 } };
		int[] a = { 1, 5, 2, 4, 9 };
		int[] b = { 8, 0, 6, 10, 3 };
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
		printArray(arr);
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
	public static int[] mergeTwoMaxHeaps(int[] arr1, int[] arr2) {
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
	 * @f:off
	 * same approach as merge k sorted array.
	 * nlogk
	 * @f:on
	 * 
	 * @param list
	 * @return merged sorted list
	 */
	public static Node<Integer> mergeKsortedList(List<Node<Integer>> list) {
		Node<Integer> head = null;
		Node<Integer> curr = null;
		PriorityQueue<Node<Integer>> queue = new PriorityQueue<>((o1, o2) -> o1.getData().compareTo(o2.getData()));
		list.forEach(queue::add);
		while (!queue.isEmpty()) {
			if (head != null) {
				curr.setNext(queue.poll());
				curr = curr.getNext();
			} else {
				head = queue.poll();
				curr = head;
			}
			if (curr.getNext() != null) {
				queue.add(curr.getNext());
			}

		}
		return head;
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
		System.out.println("Largest " + arr1.length + " Pairs are : ");
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
			System.out.print("<" + arr1[0] + "," + arr2[0] + ">");
			swap(arr1, i, 0);
			heapify(arr1, i, 0);
			swap(arr2, i, 0);
			heapify(arr2, i, 0);
		}
		System.out.println();

	}

	/**
	 * Print the median of running data.
	 * 
	 * Approach 1 - sort the data and then find middle element.this approach is comparing every element. and require re-shuffling on every insertion.
	 * 
	 * @f:off
	 * 
	 * Approach 2 - heap can be used to implement such concept.
	 * 1.take min heap and max heap. always insert first in min heap and check if both heap has difference of no of elements > 1
	 * 1.2. if yes poll from min heap and add to max heap.
	 * 2.check if both heap has same elements. 
	 * 2.1 if yes (min.peek + max.peek)/2 is the answer
	 * 2.2 else minHeap.peek is the answer.
	 * 
	 * Why above Algo work-  Basically we are dividing array elements equally in two heaps
	 * At any point min heap will either has equal elements or one more element.
	 * 
	 * one half will be in one heap and second half will be in second heap. center is either avg or min heap top. 
	 * 
	 * o(nlogn)
	 * 
	 * @f:on
	 * 
	 * @param arr
	 */
	public static void median(int[] arr) {
		PriorityQueue<Integer> heapMin = new PriorityQueue<>((arr.length / 2) + 1);
		PriorityQueue<Integer> heapMax = new PriorityQueue<>((arr.length / 2) + 1, (o1, o2) -> o2 - o1);
		// stream of data
		for (int i = 0; i < arr.length; i++) {
			System.out.println("Median : " + getMedian(heapMin, heapMax, arr[i]));
		}
	}

	/**
	 * time: logn
	 */
	private static int getMedian(PriorityQueue<Integer> minHeap, PriorityQueue<Integer> maxHeap, int data) {
		// add to minheap first
		minHeap.add(data);
		// check if minHeap has more than 1 element extra
		if (minHeap.size() - maxHeap.size() > 1) {
			maxHeap.add(minHeap.poll());
		}

		// if total numbers are even. median is avg
		if (minHeap.size() == maxHeap.size()) {
			data = (minHeap.peek() + maxHeap.peek()) / 2;
		} else {
			data = minHeap.peek();
		}
		return data;
	}

	/**
	 * Maximum of all sub arrays of size k
	 * 
	 * @f:off
	 * for e.g. - 
	 * Input: arr[] = {8, 5, 10, 7, 9, 4, 15, 12, 90, 13}, K = 4
	 * Output: 10 10 10 15 15 90 90
	 * Use DeQue of size k to store indexes of the element. 
	 * this problem is enhancement of sliding window concept. 
	 * where we want to find max element in every window
	 * 
	 * algo - 
	 * 1.Our aim is to store max element in a window to the beginning of deque.
	 * 2.To maintain that we will repeatedly remove elements from rear end if new item value > then rear value.
	 * in this way initial deque of k size is maintained.
	 * after that for remaining k - n elements -
	 * 1. print the front element.
	 * 2. if front index is outside the window range. remove it.
	 * 3. repeatedly remove rear element till it is less than new item to be added.
	 * 4. add new item to deque last.
	 * o(n)
	 * @f:on
	 * 
	 * @param arr
	 * @param k
	 */
	public static void printMax(int[] arr, int k) {

		Deque<Integer> indexes = new LinkedList<>();

		// prepare initial deque data for first window
		for (int i = 0; i < k; i++) {
			while (!indexes.isEmpty() && arr[i] >= arr[indexes.peekLast()]) {
				indexes.removeLast();
			}
			indexes.addLast(i);
		}
		System.out.println("Max element in subarray of size " + k + " : ");
		for (int i = k; i < arr.length; i++) {
			System.out.print(arr[indexes.peekFirst()] + ",");
			while (!indexes.isEmpty() && i - k >= indexes.peekFirst()) {
				indexes.removeFirst();
			}
			while (!indexes.isEmpty() && arr[i] >= arr[indexes.peekLast()]) {
				indexes.removeLast();
			}
			indexes.addLast(i);
		}
		System.out.println(arr[indexes.peekFirst()]);
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

	private static void printArray(int[] arr) {
		System.out.println("Array :");
		for (int i : arr) {
			System.out.print(i + " ");
		}
		System.out.println();

	}
}

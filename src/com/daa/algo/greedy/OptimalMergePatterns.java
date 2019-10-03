package com.daa.algo.greedy;

import java.util.PriorityQueue;

/**
 * abdul bari,greedy,geeks
 * 
 * When two or more files are to be merged all together to form a single file,
 * the minimum computations done to reach this file are known as Optimal Merge
 * Pattern.
 * 
 * It is similar to huffman coding. O(nlogn)
 */
public class OptimalMergePatterns {

	/**
	 * @f:off
	 * 
	 * Approach - 
	 * 1.store all the files into minHeap.
	 * 2.poll two item at a time from Heap and add the sum to heap.
	 * 3.and also update count with the sum.
	 * 
	 * @f:on
	 * @param files
	 * @return minimum computation
	 */
	public int minComputation(int[] files) {
		PriorityQueue<Integer> queue = new PriorityQueue<>();
		for (int i = 0; i < files.length; i++) {
			queue.add(files[i]);
		}
		int sum = 0;
		while (queue.size() > 1) {
			int temp = queue.poll() + queue.poll();
			sum = sum + temp;
			queue.add(temp);
		}

		return sum;
	}

}

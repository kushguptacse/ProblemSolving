package com.daa.algo.dp;

import com.daa.math.MathUtil;

/**
 * Given an integer array nums, find the contiguous subarray (containing at
 * least one number) which has the largest sum and return its sum.
 * 
 * udemy - o(n)
 * 
 * Example:
 * 
 * Input: [-2,1,-3,4,-1,2,1,-5,4],
 * 
 * Output: 6
 * 
 * Explanation: [4,-1,2,1] has the largest sum = 6.
 *
 */
public class MaximumContiguousSubArray {

	/**
	 * @f:off
	 * take global_max which hold the max overall
	 * and curr_max will hold the max till curr iteration.
	 * we will update curr_max by this - store max of  (current element, curr_max+current element)
	 * By this we make sure that either current is taken or previous one is included in contiguous space.
	 * @f:on
	 * @param nums
	 * @return max sum of contiguos array
	 */
	public int maxSubArray(int[] nums) {
		int curr_max = nums[0];
		int global_max = nums[0];
		for (int i = 1; i < nums.length; i++) {
			curr_max = MathUtil.max(nums[i], nums[i] + curr_max);
			if (curr_max > global_max) {
				global_max = curr_max;
			}
		}
		return curr_max;
	}

	/**
	 * Given a circular array C of integers represented by A, find the maximum
	 * possible sum of a non-empty subarray of C.
	 * 
	 * Here, a circular array means the end of the array connects to the beginning
	 * of the array. (Formally, C[i] = A[i] when 0 <= i < A.length, and
	 * C[i+A.length] = C[i] when i >= 0.)
	 * 
	 * Also, a subarray may only include each element of the fixed buffer A at most
	 * once. (Formally, for a subarray C[i], C[i+1], ..., C[j], there does not exist
	 * i <= k1, k2 <= j with k1 % A.length = k2 % A.length.)
	 * 
	 * Example 1:
	 * 
	 * Input: [1,-2,3,-2]
	 * 
	 * Output: 3
	 * 
	 * Explanation: Subarray [3] has maximum sum 3
	 * 
	 * Example 2:
	 * 
	 * Input: [5,-3,5]
	 * 
	 * Output: 10
	 * 
	 * Explanation: Subarray [5,5] has maximum sum 5 + 5 = 10
	 * 
	 * @param arr
	 * @return maximum sum
	 */
	public int maxSubarraySumCircular(int[] arr) {
		int global_max = arr[0];
		int global_min = arr[0];
		int curr_max = arr[0];
		int curr_min = arr[0];
		int sum = arr[0];

		for (int i = 1; i < arr.length; i++) {
			curr_max = MathUtil.max(curr_max + arr[i], arr[i]);
			curr_min = MathUtil.min(curr_min + arr[i], arr[i]);
			global_max = MathUtil.max(global_max, curr_max);
			global_min = MathUtil.min(global_min, curr_min);
			sum += arr[i];
		}

		return global_max > 0 ? MathUtil.max(sum - global_min, global_max) : global_max;
	}

}

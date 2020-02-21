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

}

package com.daa.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.daa.math.MathUtil;

public class ArrayPractice {

	public static void main(String[] args) {
		System.out.println(ArrayPractice.binarySearch(new int[] { 1, 2, 5, 6 }, 3));
		int[] arr = new int[] { 0, 1, 2, 5, 0, 6, 0 };
		searchAndShift(arr, 0);
		System.out.println(Arrays.toString(arr));
		int[] nums1 = new int[] { 1, 0, 0, 0, 0, 0 };
		int[] nums2 = new int[] { 3, 4 };
		merge(nums1, 1, nums2, 2);
		System.out.println(Arrays.toString(nums1));
	}

	/**
	 * It requires array to be already sorted to work correctly.
	 * 
	 * here we check from index start - mid if element to searched < mid else check
	 * from mid+1,end.
	 * 
	 * o(logn)
	 * 
	 * @param nums
	 * @return index of item else -1
	 */
	public static int binarySearch(int[] nums, int target) {
		int start = 0;
		int end = nums.length - 1;
		int mid = 0;
		while (start <= end) {
			mid = (start + end) / 2;
			if (nums[mid] == target) {
				return mid;
			} else if (nums[mid] < target) {
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}
		return -1;
	}

	/**
	 * Given a sorted array and a target value, return the index if the target is
	 * found. If not, return the index where it would be if it were inserted in
	 * order. You may assume no duplicates in the array.
	 * 
	 * Input: [1,3,5,6], 2 Output: 1
	 * 
	 * @param nums
	 * @param target
	 * @return index of element present or where it should be present
	 */
	public int searchInsert(int[] nums, int target) {
		int start = 0;
		int end = nums.length - 1;
		int mid = 0;
		while (start <= end) {
			mid = (start + end) / 2;
			if (nums[mid] == target) {
				return mid;
			} else if (nums[mid] < target) {
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}
		return start;
	}

	/**
	 * Given an array nums, write a function to move all 0's to the end of it while
	 * maintaining the relative order of the non-zero elements.
	 * 
	 * Example:
	 * 
	 * Input: [0,0,1,2,5,0,6,0]
	 * 
	 * Output: [1,2,5,6,0,0,0,0]
	 * 
	 * @param arr
	 * @param val
	 */
	public static void searchAndShift(int[] arr, int val) {
		int c = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == val) {
				c++;
			} else if (c > 0) {
				arr[i - c] = arr[i];
				arr[i] = val;
			}
		}
	}

	/**
	 * Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array), some elements
	 * appear twice and others appear once.
	 * 
	 * print all the duplicates in this array.
	 * 
	 * o(n)
	 * 
	 * @param nums
	 */
	public static void findDuplicates(int[] nums) {
		for (int i = 0; i < nums.length; i++) {
			int n = Math.abs(nums[i]);
			if (nums[n - 1] < 0) {
				System.out.print(n + " ");
			} else {
				nums[n - 1] = -nums[n - 1];
			}
		}
	}

	/**
	 * leetcode
	 * 
	 * Given a list of non negative integers, arrange them such that they form the
	 * largest number.
	 * 
	 * Example 1:
	 * 
	 * Input: [3,30,34,5,9]
	 * 
	 * Output: "9534330"
	 * 
	 * @param nums
	 * @return largest number
	 */
	public String largestNumber(int[] nums) {
		String[] str = new String[nums.length];
		for (int i = 0; i < nums.length; i++) {
			str[i] = String.valueOf(nums[i]);
		}
		Arrays.sort(str, (o1, o2) -> {
			String s1 = o1 + o2;
			String s2 = o2 + o1;
			return s2.compareTo(s1);
		});

		if ("0".equals(str[0])) {
			return str[0];
		}
		StringBuilder sb = new StringBuilder(nums.length);
		for (String s : str) {
			sb.append(s);
		}
		return sb.toString();
	}

	/**
	 * @f:off
	 * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as
	 * one sorted array.
	 * 
	 * Note:
	 * 
	 * The number of elements initialized in nums1 and nums2 are m and n
	 * respectively. 
	 * You may assume that nums1 has enough space (size that is greater or equal to m + n) 
	 * to hold additional elements from nums2. 
	 * 
	 * Example:
	 * Input: 
	 * nums1 = [1,2,3,0,0,0], m = 3 
	 * nums2 = [2,5,6], n = 3
	 * Output: [1,2,2,3,5,6]
     *
	 * @f:on
	 * 
	 * @param nums1
	 * @param m
	 * @param nums2
	 * @param n
	 */
	public static void merge(int[] nums1, int m, int[] nums2, int n) {
		int i = m + n - 1;
		n--;
		m--;
		while (m >= 0 && n >= 0) {
			if (nums1[m] > nums2[n]) {
				nums1[i--] = nums1[m--];
			} else {
				nums1[i--] = nums2[n--];
			}
		}
		while (n >= 0) {
			nums1[i--] = nums2[n--];
		}
	}

	/**
	 * Given a non-empty array of integers, every element appears twice except for
	 * one. Find that single one.
	 * 
	 * Example 2:
	 * 
	 * Input: [14,1,2,1,2]
	 * 
	 * Output: 14
	 * 
	 * o(n) time,o(1) space
	 * 
	 * @param nums
	 * @return single element among duplicates
	 */
	public static int singleNumber(int[] nums) {
		int res = nums[0];
		for (int i = 1; i < nums.length; i++) {
			res = res ^ nums[i];
		}
		return res;
	}

	/**
	 * Given a binary array, find the maximum length of a contiguous sub-array with
	 * equal number of 0 and 1
	 * 
	 * Input: [1,0,1,1,0]
	 * 
	 * Output: 4
	 * 
	 * Explanation: [0,1,1,0] is a longest contiguous sub array with equal number of
	 * 0 and 1
	 * 
	 * @param nums
	 * @return max length of contiguous array with equal 0 and 1
	 */
	public int maxLengthBinary(int[] nums) {
		int max = 0;
		int sum = 0;
		Map<Integer, Integer> map = new HashMap<>();
		map.put(0, -1);
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i] == 0 ? -1 : 1;
			if (map.containsKey(sum)) {
				max = MathUtil.max(max, i - map.get(sum));
			} else {
				map.put(sum, i);
			}
		}
		return max;
	}

	/**
	 * Given an array nums of n integers where n > 1, return an array output such
	 * that output[i] is equal to the product of all the elements of nums except
	 * nums[i].
	 * 
	 * Example:
	 * 
	 * Input: [1,2,3,4]
	 * 
	 * Output: [24,12,8,6]
	 * 
	 * Note: Please solve it without division and in O(n).
	 * 
	 * @param nums
	 * @return output array - Product of Array Except Self
	 */
	public int[] productExceptSelf(int[] nums) {
		int[] output = new int[nums.length];
		output[0] = 1;
		// count multiplication result from left to right in every index.
		for (int i = 1; i < nums.length; i++) {
			output[i] = nums[i - 1] * output[i - 1];
		}
		//in above example output[]=[1,1,2,6]
		// now every index has multiplication result till left.
		//so, go from right to left and multiply remaining element for every index from
		// right.
		int m = nums[nums.length - 1];//m will keep multiplication from right till i index
		for (int i = nums.length - 2; i >= 0; i--) {
			output[i] = output[i] * m;
			m = m * nums[i];
		}
		return output;
	}
}

package com.daa.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.daa.math.MathUtil;

public class ArrayPractice {

	public static void main(String[] args) {
		System.out.println(ArrayPractice.possibleBipartition(10,
				new int[][] { { 6, 9 }, { 1, 3 }, { 4, 8 }, { 5, 6 }, { 2, 8 }, { 4, 7 }, { 8, 9 }, { 2, 5 }, { 5, 8 },
						{ 1, 2 }, { 6, 7 }, { 3, 10 }, { 8, 10 }, { 1, 5 }, { 3, 6 }, { 1, 10 }, { 7, 9 }, { 4, 10 },
						{ 7, 10 }, { 1, 4 }, { 9, 10 }, { 4, 6 }, { 2, 7 }, { 6, 8 }, { 5, 7 }, { 3, 8 }, { 1, 8 },
						{ 1, 7 }, { 7, 8 }, { 2, 4 } }));
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
	 * Given an array of size n, find the majority element. The majority element is
	 * the element that appears more than ⌊ n/2 ⌋ times.
	 * 
	 * You may assume that the array is non-empty and the majority element always
	 * exist in the array.
	 * 
	 * Example 1:
	 * 
	 * Input: [3,2,3]
	 * 
	 * Output: 3
	 * 
	 * Example 2:
	 * 
	 * Input: [2,2,1,1,1,2,2]
	 * 
	 * Output: 2
	 * 
	 * o(n),o(1)
	 * 
	 * @param nums
	 * @return majority element
	 */
	public int majorityElement(int[] nums) {
		int candidate = nums[0];
		int c = 1;
		for (int i = 1; i < nums.length; i++) {
			if (candidate == nums[i]) {
				c++;
			} else {
				c--;
			}
			if (c == 0) {
				candidate = nums[i];
				c = 1;
			}
		}
		return candidate;
	}

	/**
	 * Given an array of non-negative integers, you are initially positioned at the
	 * first index of the array.
	 * 
	 * Each element in the array represents your maximum jump length at that
	 * position.
	 * 
	 * Determine if you are able to reach the last index.
	 * @f:off
	 * Example 1:
	 * 
	 * Input: [2,3,1,1,4] 
	 * Output: true Explanation: Jump 1 step from index 0 to 1,
	 * then 3 steps to the last index. Example 2:
	 * 
	 * Input: [3,2,1,0,4] 
	 * Output: false Explanation: You will always arrive at index
	 * 3 no matter what. Its maximum jump length is 0, which makes it impossible to reach the last index.
	 * @f:on
	 * 
	 * @param nums
	 * @return true if possible to go from start to end index
	 */
	public static boolean canJumpGame(int[] nums) {
		int far = 0;
		for (int i = 0; i < nums.length; i++) {
			if (far < i) {
				return false;
			}
			far = MathUtil.max(far, i + nums[i]);
		}
		return true;
	}

	/**
	 * 
	 * In a town, there are N people labelled from 1 to N. There is a rumor that one
	 * of these people is secretly the town judge.
	 * 
	 * If the town judge exists, then:
	 * 
	 * The town judge trusts nobody. Everybody (except for the town judge) trusts
	 * the town judge. There is exactly one person that satisfies properties 1 and
	 * 2. You are given trust, an array of pairs trust[i] = [a, b] representing that
	 * the person labelled a trusts the person labelled b.
	 * 
	 * If the town judge exists and can be identified, return the label of the town
	 * judge. Otherwise, return -1.
	 * 
	 * Example 1:
	 * 
	 * Input: N = 2, trust = [[1,2]]
	 * 
	 * Output: 2 Example 2:
	 * 
	 * Input: N = 3, trust = [[1,3],[2,3]]
	 * 
	 * Output: 3 Example 3:
	 * 
	 * Input: N = 3, trust = [[1,3],[2,3],[3,1]]
	 * 
	 * Output: -1 Example 4:
	 * 
	 * Input: N = 3, trust = [[1,2],[2,3]]
	 * 
	 * Output: -1 Example 5:
	 * 
	 * Input: N = 4, trust = [[1,3],[1,4],[2,3],[2,4],[4,3]]
	 * 
	 * Output: 3
	 * 
	 * @param n
	 * @param trust
	 * @return judge else -1
	 */
	public int findJudge(int n, int[][] trust) {
		int[] trustFactor = new int[n + 1];
		for (int i = 0; i < trust.length; i++) {
			trustFactor[trust[i][0]] -= 1;
			trustFactor[trust[i][1]] += 1;
		}
		for (int k = 1; k <= n; k++) {
			if (trustFactor[k] == n - 1) {
				return k;
			}
		}
		return -1;
	}

	/**
	 * Single Element in a Sorted Array -> You are given a sorted array consisting
	 * of only integers where every element appears exactly twice, except for one
	 * element which appears exactly once. Find this single element that appears
	 * only once.
	 * 
	 * 
	 * 
	 * Example 1:
	 * 
	 * Input: [1,1,2,3,3,4,4,8,8]
	 * 
	 * Output: 2
	 * 
	 * Example 2:
	 * 
	 * Input: [3,3,7,7,10,11,11]
	 * 
	 * Output: 10
	 * 
	 * @param nums
	 * @return single unique element
	 */
	public int singleNonDuplicate(int[] nums) {
		int low = 0;
		int high = nums.length - 1;
		int mid;
		while (low < high) {
			mid = low + (high - low) / 2;
			if ((mid % 2 == 0 && nums[mid] == nums[mid + 1]) || (mid % 2 != 0 && nums[mid] == nums[mid - 1])) {
				low = mid + 1;
			} else {
				high = mid;
			}
		}
		return nums[low];
	}

	/**
	 * Given a string, sort it in decreasing order based on the frequency of
	 * characters.
	 * 
	 * Example 1:
	 * 
	 * Input: "tree"
	 * 
	 * Output: "eert"
	 * 
	 * Explanation: 'e' appears twice while 'r' and 't' both appear once. So 'e'
	 * must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
	 * Example 2:
	 * 
	 * Input: "cccaaa"
	 * 
	 * Output: "cccaaa"
	 * 
	 * Explanation: Both 'c' and 'a' appear three times, so "aaaccc" is also a valid
	 * answer. Note that "cacaca" is incorrect, as the same characters must be
	 * together. Example 3:
	 * 
	 * Input: "Aabb"
	 * 
	 * Output: "bbAa"
	 * 
	 * Explanation: "bbaA" is also a valid answer, but "Aabb" is incorrect. Note
	 * that 'A' and 'a' are treated as two different characters.
	 * 
	 * @param s
	 * @return frequency sorted array
	 */
	public String frequencySort(String s) {
		int[][] arr = new int[128][2];
		for (int i = 0; i < s.length(); i++) {
			arr[s.charAt(i)][1] += 1;
			arr[s.charAt(i)][0] = s.charAt(i);
		}
		StringBuilder sb = new StringBuilder();
		Arrays.sort(arr, (o1, o2) -> o2[1] - o1[1]);
		for (int i = 0; i < arr.length; i++) {
			for (int k = 0; k < arr[i][1]; k++) {
				sb.append((char) arr[i][0]);
			}
		}
		return sb.toString();
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

	public static boolean possibleBipartition(int n, int[][] dislikes) {
		if (n == 0 || dislikes.length == 0) {
			return true;
		}
		int[] output = new int[n + 1];
		for (int i = 0; i < dislikes.length; i++) {
			output[dislikes[i][0]] += 1;
			output[dislikes[i][1]] += 1;
		}
		int val = output[1];
		for (int i = 2; i < output.length; i++) {
			if (val != output[i]) {
				return true;
			}
		}
		return false;
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
		// in above example output[]=[1,1,2,6]
		// now every index has multiplication result till left.
		// so, go from right to left and multiply remaining element for every index from
		// right.
		int m = nums[nums.length - 1];// m will keep multiplication from right till i index
		for (int i = nums.length - 2; i >= 0; i--) {
			output[i] = output[i] * m;
			m = m * nums[i];
		}
		return output;
	}

	/**
	 * Given an array of integers and an integer k, you need to find the total
	 * number of continuous sub arrays whose sum equals to k.
	 * 
	 * Example 1:
	 * 
	 * Input:nums = [1,1,1], k = 2
	 * 
	 * Output: 2
	 * 
	 * @param nums
	 * @param k
	 * @return number of contiguous array whose sum is k.
	 */
	public int subarraySum(int[] nums, int k) {
		if (nums.length < 1) {
			return 0;
		}
		Map<Integer, Integer> map = new HashMap<>();
		int c = 0;
		int sum = 0;
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
			if (sum == k) {
				c++;
			}
			if (map.containsKey(sum - k)) {
				c += map.get(sum - k);
			}
			map.put(sum, map.getOrDefault(sum, 0) + 1);
		}
		return c;
	}

}

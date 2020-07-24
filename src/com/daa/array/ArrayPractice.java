package com.daa.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import com.daa.math.MathUtil;

public class ArrayPractice {

	public static void main(String[] args) {
		int num = 534972;
		System.out.println(num + " : " + nextGreaterElement(num));
		num = 538467;
		int res = nextSmallestElement(num);
		System.out.println(num + " : " + res);
		num = nextGreaterElement(res);
		System.out.println(res + " : " + num);
		System.out.println(num + " : " + nextSmallestElement(num));
		num = 230918;
		System.out.println(num + " : " + nextSmallestElement(num));
		System.out.println(ArrayPractice.binarySearch(new int[] { 1, 2, 5, 6 }, 3));
		int[] arr = new int[] { 0, 1, 2, 5, 0, 6, 0 };
		searchAndShift(arr, 0);
		System.out.println(Arrays.toString(arr));
		int[] nums1 = new int[] { 1, 0, 0, 0, 0, 0 };
		int[] nums2 = new int[] { 3, 4 };
		merge(nums1, 1, nums2, 2);
		System.out.println(Arrays.toString(nums1));
	}

	private static int nextSmallestElement(int n) {
		char[] num = String.valueOf(n).toCharArray();
		int i = num.length - 1;
		while (i > 0 && num[i] >= num[i - 1]) {
			i--;
		}
		if (i == 0) {
			return -1;
		}
		i--;
		int j = num.length - 1;
		while (num[j] >= num[i]) {
			j--;
		}
		swap(num, j, i);
		reverse(num, i + 1);
		return Integer.parseInt(String.valueOf(num));
	}

	public static int nextGreaterElement(int n) {
		char[] num = String.valueOf(n).toCharArray();
		int i = num.length - 1;
		while (i > 0 && num[i] <= num[i - 1]) {
			i--;
		}
		if (i == 0) {
			return -1;
		}
		i--;
		int j = num.length - 1;
		while (num[j] <= num[i]) {
			j--;
		}
		swap(num, i, j);
		reverse(num, i + 1);
		long val = Long.parseLong(String.valueOf(num));
		return val > Integer.MAX_VALUE ? -1 : (int) val;
	}

	private static void reverse(char[] num, int l) {
		int h = num.length - 1;
		while (l < h) {
			swap(num, l++, h--);
		}
	}

	private static void swap(char[] num, int i, int j) {
		num[i] ^= num[j];
		num[j] ^= num[i];
		num[i] ^= num[j];
	}

	public int hIndex(int[] citations) {
		int start = 0;
		int end = citations.length - 1;
		while (start <= end) {
			int mid = end - (end - start) / 2;
			if (citations[mid] == citations.length - mid) {
				return citations.length - mid;
			} else if (citations[mid] > citations.length - mid) {
				end = mid - 1;
			} else {
				start = mid + 1;
			}
		}
		return citations.length - start;
	}

	/**
	 * Given two arrays of integers nums and index. Your task is to create target
	 * array under the following rules:
	 * 
	 * Initially target array is empty. From left to right read nums[i] and
	 * index[i], insert at index index[i] the value nums[i] in target array. Repeat
	 * the previous step until there are no elements to read in nums and index.
	 * Return the target array.
	 * 
	 * It is guaranteed that the insertion operations will be valid.
	 * 
	 * 
	 * Example 1:
	 * 
	 * Input: nums = [1,2,3,4,0], index = [0,1,2,3,0]
	 * 
	 * Output: [0,1,2,3,4]
	 *@f:off 
	 *Explanation:
	 *nums       index     target
	 *1            0        [1]
	 *2            1        [1,2]
	 *3            2        [1,2,3]
	 *4            3        [1,2,3,4]
	 *0            0        [0,1,2,3,4]
	 *@f:on 
	 * @param nums
	 * @param index
	 * @return
	 */
	public int[] createTargetArray(int[] nums, int[] index) {
		int[] op = new int[nums.length];
		op[0] = nums[0];
		for (int i = 1; i < nums.length; i++) {
			int ind = index[i];
			if (ind < i) {
				for (int j = i; j > ind; j--) {
					op[j] = op[j - 1];
				}
			}
			op[ind] = nums[i];
		}
		return op;
	}

	/**
	 * Balanced strings are those who have equal quantity of 'L' and 'R' characters.
	 * 
	 * Given a balanced string s split it in the maximum amount of balanced strings.
	 * 
	 * Return the maximum amount of splitted balanced strings.
	 * 
	 * 
	 * 
	 * Example 1:
	 * 
	 * Input: s = "RLRRLLRLRL" Output: 4
	 * 
	 * Explanation: s can be split into "RL", "RRLL", "RL", "RL", each substring
	 * contains same number of 'L' and 'R'. Example 2:
	 * 
	 * Input: s = "RLLLLRRRLR" Output: 3
	 * 
	 * Explanation: s can be split into "RL", "LLLRRR", "LR", each substring
	 * contains same number of 'L' and 'R'. Example 3:
	 * 
	 * Input: s = "LLLLRRRR" Output: 1
	 * 
	 * Explanation: s can be split into "LLLLRRRR". Example 4:
	 * 
	 * Input: s = "RLRRRLLRLL" Output: 2
	 * 
	 * Explanation: s can be split into "RL", "RRRLLRLL", since each substring
	 * contains an equal number of 'L' and 'R'
	 * 
	 * 
	 * Constraints:
	 * 
	 * 1 <= s.length <= 1000 s[i] = 'L' or 'R'
	 * 
	 * @param s
	 * @return count
	 */
	public int balancedStringSplit(String s) {
		int c = 0;
		int j = 0;
		for (int i = 0; i < s.length(); i++) {
			j += s.charAt(i) == 'R' ? 1 : -1;
			if (j == 0) {
				c++;
			}
		}
		return c;
	}

	/**
	 * Given a fixed length array arr of integers, duplicate each occurrence of
	 * zero, shifting the remaining elements to the right.
	 * 
	 * Note that elements beyond the length of the original array are not written.
	 * Do the above modifications to the input array in place, do not return
	 * anything from your function.
	 * 
	 * 
	 * Example 1: Input: [1,0,2,3,0,4,5,0]
	 * 
	 * Explanation: After calling your function, the input array is modified to:
	 * [1,0,0,2,3,0,0,4]
	 * 
	 * Example 2: Input: [1,2,3]
	 * 
	 * Explanation: After calling your function, the input array is modified to:
	 * [1,2,3]
	 * 
	 * @param arr
	 */
	public void duplicateZeros(int[] arr) {
		int zeroC = 0;
		int size = arr.length - 1;
		for (int i = 0; i <= size - zeroC; i++) {
			if (arr[i] == 0) {
				if (i == size - zeroC) {
					arr[size] = 0;
					size--;
					break;
				}
				zeroC++;
			}
		}

		for (int i = size - zeroC; i >= 0; i--) {
			if (arr[i] == 0) {
				arr[i + zeroC] = 0;
				zeroC--;
				arr[i + zeroC] = 0;
			} else {
				arr[i + zeroC] = arr[i];
			}
		}

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

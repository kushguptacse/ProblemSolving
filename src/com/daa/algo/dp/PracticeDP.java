package com.daa.algo.dp;

import com.daa.math.MathUtil;

public class PracticeDP {

	public static void main(String[] args) {
		System.out.println(test(5));
		System.out.println(testImprove(5));
		int a[] = { -2, -3, 4, -1, -2, 1, 5, -3 };
		int max_sum = maxSubArraySum(a);
		System.out.println("Maximum contiguous sum is " + max_sum);
	}

	/**
	 * Given two words word1 and word2, find the minimum number of operations
	 * required to convert word1 to word2.
	 * 
	 * You have the following 3 operations permitted on a word:
	 * 
	 * Insert a character Delete a character Replace a character Example 1:
	 * 
	 * Input: word1 = "horse", word2 = "ros"
	 * 
	 * Output: 3
	 * 
	 * Explanation: horse -> rorse (replace 'h' with 'r') rorse -> rose (remove 'r')
	 * rose -> ros (remove 'e') Example 2:
	 * 
	 * Input: word1 = "intention", word2 = "execution"
	 * 
	 * Output: 5
	 * 
	 * Explanation: intention -> inention (remove 't') inention -> enention (replace
	 * 'i' with 'e') enention -> exention (replace 'n' with 'x') exention ->
	 * exection (replace 'n' with 'c') exection -> execution (insert 'u')
	 * 
	 * @param word1
	 * @param word2
	 * @return minimum distance
	 */
	public static int minDistance(String word1, String word2) {
		int m = word1.length();
		int n = word2.length();
		int[][] dp = new int[m + 1][n + 1];
		for (int i = 1; i <= m; i++) {
			dp[i][0] = i;
		}
		for (int i = 1; i <= n; i++) {
			dp[0][i] = i;
		}
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1];
				} else {
					dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
				}
			}
		}
		return dp[m][n];
	}

	public static int test(int n) {
		if (n < 2) {
			return 2;
		}
		int res = 0;
		for (int i = 1; i < n; i++) {
			res = res + 2 * test(i) * test(i - 1);
		}
		return res;
	}

	/**
	 * We write the integers of A and B (in the order they are given) on two
	 * separate horizontal lines.
	 * 
	 * Now, we may draw connecting lines: a straight line connecting two numbers
	 * A[i] and B[j] such that:
	 * 
	 * A[i] == B[j]; The line we draw does not intersect any other connecting
	 * (non-horizontal) line. Note that a connecting lines cannot intersect even at
	 * the endpoints: each number can only belong to one connecting line.
	 * 
	 * Return the maximum number of connecting lines we can draw in this way.
	 * 
	 * Input: A = [1,4,2], B = [1,2,4]
	 * 
	 * Output: 2
	 * 
	 * Explanation: We can draw 2 uncrossed lines as in the diagram. We cannot draw
	 * 3 uncrossed lines, because the line from A[1]=4 to B[2]=4 will intersect the
	 * line from A[2]=2 to B[1]=2.
	 * 
	 * Example 2:
	 * 
	 * Input: A = [2,5,1,2,5], B = [10,5,2,1,5,2]
	 * 
	 * Output: 3
	 * 
	 * Example 3:
	 * 
	 * Input: A = [1,3,7,1,7,5], B = [1,9,2,5,1]
	 * 
	 * Output: 2
	 * 
	 * LEETCODE
	 * 
	 * @param arr
	 * @param brr
	 * @return max un-crossed line
	 */
	public static int maxUncrossedLines(int[] arr, int[] brr) {
		int[][] table = new int[arr.length + 1][brr.length + 1];
		for (int i = 1; i <= arr.length; i++) {
			for (int j = 1; j <= brr.length; j++) {
				if (arr[i - 1] == brr[j - 1]) {
					table[i][j] = table[i - 1][j - 1] + 1;
				} else {
					table[i][j] = Math.max(table[i - 1][j], table[i][j - 1]);
				}
			}
		}
		return table[arr.length][brr.length];
	}

	/**
	 * Given a m * n matrix of ones and zeros, return how many square submatrices
	 * have all ones.
	 * 
	 * Example 1:
	 * 
	 * Input: matrix = [ [0,1,1,1], [1,1,1,1], [0,1,1,1] ]
	 * 
	 * Output: 15
	 * 
	 * Explanation: There are 10 squares of side 1. There are 4 squares of side 2.
	 * There is 1 square of side 3. Total number of squares = 10 + 4 + 1 = 15.
	 * 
	 * Example 2:
	 * 
	 * Input: matrix = [ [1,0,1], [1,1,0], [1,1,0] ]
	 * 
	 * Output: 7
	 * 
	 * Explanation: There are 6 squares of side 1. There is 1 square of side 2.
	 * Total number of squares = 6 + 1 = 7.
	 * 
	 * LEETCODE
	 * 
	 * @param matrix
	 * @return total number of squares possible
	 */
	public static int countSquares(int[][] matrix) {
		int c = 0;
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (i != 0 && j != 0 && matrix[i][j] != 0) {
					matrix[i][j] = Math.min(matrix[i - 1][j - 1], Math.min(matrix[i - 1][j], matrix[i][j - 1])) + 1;
				}
				c += matrix[i][j];
			}

		}
		return c;
	}

	/**
	 * Given a array which might contain negative numbers. we need to find maximum
	 * sum that can be obtain by considering contiguous elements.
	 * 
	 * e.g. - int a[] = { -2, -3, 4, -1, -2, 1, 5, -3 };
	 * 
	 * output - 7. i.e. {4,-1,-2,1,5}
	 * 
	 * @param arr
	 * @return
	 */
	public static int maxSubArraySum(int[] arr) {
		int max = arr[0];
		int subMax = arr[0];
		for (int i = 1; i < arr.length; i++) {
			subMax = MathUtil.max(arr[i], subMax + arr[i]);
			max = MathUtil.max(max, subMax);
		}
		return max;
	}

	/**
	 * Given an integer array nums, find the contiguous subarray within an array
	 * (containing at least one number) which has the largest product.
	 * 
	 * Example 1:
	 * 
	 * Input: [2,3,-2,4] Output: 6
	 * 
	 * Explanation: [2,3] has the largest product 6.
	 * 
	 * Example 2:
	 * 
	 * Input: [-2,0,-1] Output: 0
	 * 
	 * Explanation: The result cannot be 2, because [-2,-1] is not a sub-array.
	 * 
	 * @param nums
	 * @return maximum product of sub-array possible
	 */
	public int maxProduct(int[] nums) {
		int globalMax = nums[0];
		int currMax = nums[0];
		int currMin = nums[0];
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] < 0) {
				int temp = currMin;
				currMin = currMax;
				currMax = temp;
			}
			currMin = Math.min(nums[i], currMin * nums[i]);
			currMax = Math.max(nums[i], currMax * nums[i]);
			globalMax = Math.max(globalMax, currMax);
		}
		return globalMax;
	}

	public static int testImprove(int n) {
		int[] table = new int[n + 1];
		table[0] = 2;
		table[1] = 2;
		table[2] = 8;
		for (int i = 3; i <= n; i++) {
			table[i] = table[i - 1] + 2 * table[i - 1] * table[i - 2];
		}
		return table[n];
	}
}

package com.daa.algo.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GreedyPractice {
	public static void main(String[] args) {
		System.out.println(new GreedyPractice().partitionLabels("ababcbacadefegdehijhklij"));
	}

	public static int twoCitySchedCost(int[][] costs) {
		Arrays.sort(costs, (a, b) -> a[0] - a[1] - b[0] + b[1]);
		int sum = 0;
		for (int ii = 0; ii < costs.length; ii++) {
			if (ii < costs.length / 2) {
				sum += costs[ii][0];
			} else {
				sum += costs[ii][1];
			}
		}
		return sum;
	}

	/**
	 * A string S of lowercase English letters is given. We want to partition this
	 * string into as many parts as possible so that each letter appears in at most
	 * one part, and return a list of integers representing the size of these parts.
	 * 
	 * Example 1:
	 * 
	 * Input: S = "ababcbacadefegdehijhklij" Output: [9,7,8]
	 * 
	 * Explanation: The partition is "ababcbaca", "defegde", "hijhklij". This is a
	 * partition so that each letter appears in at most one part. A partition like
	 * "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less
	 * parts.
	 * 
	 * 
	 * Note:
	 * 
	 * S will have length in range [1, 500]. S will consist of lowercase English
	 * letters ('a' to 'z') only.
	 * 
	 * @param s
	 * @return
	 */
	public List<Integer> partitionLabels(String s) {
		List<Integer> list = new ArrayList<>();
		if (s == null || s.length() == 0)
			return list;
		int[] last = new int[26];
		for (int i = 0; i < s.length(); i++) {
			last[s.charAt(i) - 'a'] = i;
		}

		int start = 0;
		int end = 0;
		for (int i = 0; i < s.length(); i++) {
			end = Math.max(end, last[s.charAt(i) - 'a']);
			if (i == end) {
				list.add(end - start + 1);
				start = end + 1;
			}
		}
		return list;
	}
}

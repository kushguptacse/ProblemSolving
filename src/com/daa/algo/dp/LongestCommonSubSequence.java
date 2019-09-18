package com.daa.algo.dp;

import com.daa.math.MathUtil;

/**
 * Find the LCS of two strings. LCS Problem Statement: Given two sequences, find the
 * length of longest subsequence present in both of them. A subsequence is a sequence that
 * appears in the same relative order, but not necessarily contiguous. For example, “abc”,
 * “abg”, “bdf”, “aeg”, ‘”acefg”, .. etc are subsequences of “abcdefg”. 
 * 
 * @f:off
 * abdul bari.
 * it is used in DNA matching algorithm.
 * if we apply naive approach of trying every combination recursively without storing result
 * it will take o(2^n).but by using DP we can reduce it to o(n*m). where n and m are size of two substrings.
 *  
 *  Approach - 
 *  1. we will prepare lcs 2D matrix of dimension (n+1)*(m+1).
 *  2.if char[i]==char[j] then lcs[i][j]=1+lcs[i-1][j-1]
 *  3.else lcs[i][j]=max(lcs[i-1][j],lcs[i][j-1])  
 *  
 *  lcs[n-1][m-1] will be the final lcs.
 *  
 *	@f:on
 */
public class LongestCommonSubSequence {

	public static void main(String[] args) {
		LongestCommonSubSequence obj = new LongestCommonSubSequence();
		System.out.println("Length of lcs is: " + obj.printLcs("stone", "longest"));
		System.out.println("length of longest common subsequence : " + obj.printLcs("AGGTAB", "GXTXAYB"));
		System.out.println("Length of lcs is : " + obj.printLcs("abdace", "babce"));
		System.out.println("Length of lcs is : " + obj.printLcs("ABCDGH", "AEDFHR"));

	}

	/**
	 * print the lcs and return length. 
	 * o(nm). 
	 * 
	 * @f:off
	 * we will take one extra size in row and column
	 * as required and starts from i=1 and j=1. basically zero row and column is used as
	 * providing 0 value for further calculation.
	 * @f:on
	 * 
	 * @param s1
	 * @param s2
	 * @return length of lcs
	 */
	public int printLcs(String s1, String s2) {
		int[][] lcs = new int[s1.length() + 1][s2.length() + 1];
		for (int i = 1; i <= s1.length(); i++) {
			for (int j = 1; j <= s2.length(); j++) {
				if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
					lcs[i][j] = 1 + lcs[i - 1][j - 1];
				} else {
					lcs[i][j] = MathUtil.max(lcs[i - 1][j], lcs[i][j - 1]);
				}
			}
		}

		printPath(s1, s2, lcs);
		return lcs[s1.length()][s2.length()];

	}

	private void printPath(String s1, String s2, int[][] lcs) {
		System.out.print("longest common subsequence is : ");
		int i = s1.length();
		int j = s2.length();
		String res = "";
		while (i > 0 && j > 0) {
			if (lcs[i - 1][j] == lcs[i][j]) {
				i = i - 1;
			} else if (lcs[i][j - 1] == lcs[i][j]) {
				j = j - 1;
			} else {
				res = s1.charAt(i - 1) + res;
				i = i - 1;
				j = j - 1;
			}
		}
		System.out.println(res);
	}
}

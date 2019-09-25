package com.daa.string;

public class StringMainApp {
	public static void main(String[] args) {
		String s1 = "Geeks";
		String s2 = "Geks";
		System.out.println("same character in same order: " + StringHelper.contains(s1, s2));
		s1 = "Arnab";
		s2 = "Andrew";
		System.out.println("same character in same order: " + StringHelper.contains(s1, s2));
		s1 = "THIS IS A TEST TEXT";
		s2 = "TEST";
		System.out.println(StringHelper.subStringRabinKarpBetter(s1, s2));
		s1 = "AABAACAADAABAABA";
		s2 = "AABA";
		System.out.println(StringHelper.subStringRabinKarpBetter(s1, s2));
		System.out.println("Is Substring: " + StringHelper.subStringRabinKarpBetter("abcd", "bcd"));
		System.out.println("Is Substring : " + StringHelper.subStringRabinKarpBetter("aabcd", "acd"));
		System.out.println("Is Substring : " + StringHelper.subStringRabinKarpBetter("aaaaabcd", "aabcd"));
		System.out.println("Length of lcs is: " + StringHelper.longestCommonSubsequence("stone", "longest"));
		int res = StringHelper.longestCommonSubsequence("AGGTAB", "GXTXAYB");
		System.out.println("length of longest common subsequence : " + res);
		System.out.println("Length of lcs is : " + StringHelper.longestCommonSubsequence("abdace", "babce"));
		System.out.println("Length of lcs is : " + StringHelper.longestCommonSubsequence("ABCDGH", "AEDFHR"));

	}
}

package com.daa.string;

import java.util.LinkedHashMap;
import java.util.Map;

public class StringMainApp {

	public static void main(String[] args) {
		System.out.println(StringHelper.validIPAddress("0.0.0.-0"));
		System.out.println(StringHelper.validIPAddress("2001:0db8:85a3:0:0:8A2E:0370:7334"));
		System.out.println(StringHelper.checkPermutationInclusion("ab", "eidbaooo"));
		System.out.println(StringHelper.checkValidString("(*))("));
		System.out.println(StringHelper.stringShift("abcdefg", new int[][] { { 1, 1 }, { 1, 1 }, { 0, 2 }, { 1, 3 } }));
		int[][] a = new int[][] { { 1, 8 }, { 1, 4 }, { 1, 3 }, { 1, 6 }, { 0, 6 }, { 1, 4 }, { 0, 2 }, { 0, 1 } };
		System.out.println(StringHelper.stringShift("yisxjwry", a));

		LinkedHashMap<Integer, String> lhm = new LinkedHashMap<Integer, String>(16 + 1, .75F, false) {
			protected boolean removeEldestEntry(Map.Entry<Integer, String> eldest) {
				return size() > 16;
			}
		};
		String str = "radabdar";
		System.out.println("Is '" + str + "' palindrome : " + StringHelper.checkPalindrome(str));
		str = "radar";
		System.out.println("Is '" + str + "' palindrome : " + StringHelper.checkPalindrome(str));
		str = "madama";
		System.out.println("Is '" + str + "' palindrome : " + StringHelper.checkPalindrome(str));
		str = "raar";
		System.out.println("Is '" + str + "' palindrome : " + StringHelper.checkPalindrome(str));
		System.out.println(StringHelper.longestCommonSubString("neversaynever"));
		System.out.println(StringHelper.longestCommonPrefix("hello Ji", "J"));
		System.out.println(StringHelper.reverse("smellycat"));
		System.out.println("Suffix are -----------");
		StringHelper.getSuffixes("apple").forEach(System.out::println);
		String s1 = "Geeks";
		String s2 = "Geks";
		System.out.println("same character in same order: " + StringHelper.contains(s1, s2));
		s1 = "Arnab";
		s2 = "Andrew";
		System.out.println("same character in same order: " + StringHelper.contains(s1, s2));
		s1 = "THIS IS A TEST TEXT";
		s2 = "TEST";
		System.out.println(s2 + " Is Substring of " + s1 + " " + StringHelper.subStringRabinKarpBetter(s1, s2));
		s1 = "AABAACAADAABAABA";
		s2 = "AABA";
		System.out.println(s2 + " Is Substring of " + s1 + " " + StringHelper.subStringRabinKarpBetter(s1, s2));
		s1 = "abcd";
		s2 = "bcd";
		System.out.println(s2 + " Is Substring of " + s1 + " " + StringHelper.subStringRabinKarpBetter(s1, s2));
		s1 = "aabcd";
		s2 = "acd";
		System.out.println(s2 + " Is Substring of " + s1 + " " + StringHelper.subStringRabinKarpBetter(s1, s2));
		s1 = "aaaaabcd";
		s2 = "aabcd";
		System.out.println(s2 + " Is Substring of " + s1 + " " + StringHelper.subStringRabinKarpBetter(s1, s2));
		s1 = "aaabcddd";
		s2 = "abcd";
		System.out.println(s2 + " Is Substring of " + s1 + " " + StringHelper.subStringRabinKarpBetter(s1, s2));
		System.out.println("Length of lcs is: " + StringHelper.longestCommonSubsequence("stone", "longest"));
		int res = StringHelper.longestCommonSubsequence("AGGTAB", "GXTXAYB");
		System.out.println("length of longest common subsequence : " + res);
		System.out.println("Length of lcs is : " + StringHelper.longestCommonSubsequence("abdace", "babce"));
		System.out.println("Length of lcs is : " + StringHelper.longestCommonSubsequence("ABCDGH", "AEDFHR"));
		System.out.println(StringHelper.backspaceCompare("ac#d", "ab#d"));
	}
}

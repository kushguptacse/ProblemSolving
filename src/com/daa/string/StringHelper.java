package com.daa.string;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.daa.math.MathUtil;

public final class StringHelper {

	private StringHelper() {
	}

	/**
	 * Given two strings S and T, return if they are equal when both are typed into
	 * empty text editors. # means a backspace character.
	 * 
	 * Example 1:
	 * 
	 * Input: S = "ab#c", T = "ad#c"
	 * 
	 * Output: true Explanation: Both S and T become "ac".
	 * 
	 * Approach -
	 * 
	 * increment b if current element is backspace else decrement b if b>0. we add
	 * current character to StringBuilder only if it is not # and also b=0.
	 * 
	 * @param s
	 * @param t
	 * @return true if both are equal after backspace removal
	 */
	public static boolean backspaceCompare(String s, String t) {
		return removeBackSpace(s).equals(removeBackSpace(t));
	}

	private static String removeBackSpace(String s) {
		StringBuilder s1 = new StringBuilder();
		int b = 0;
		for (int i = s.length() - 1; i >= 0; i--) {
			if (s.charAt(i) == '#') {
				b++;
			} else {
				if (b > 0) {
					b--;
				} else {
					s1.append(s.charAt(i));
				}
			}
		}
		return s1.toString();
	}

	/**
	 * UDEMY
	 * @f:off
	 * input - "neversaynever"
	 * output - "never"
	 * 
	 * Steps - 
	 * 1. Find the list of all possible suffix of string passed.
	 * 2. sort the list of suffix.
	 * 3. After that find longestCommonPrefix of ith element and i+1th element.
	 * save the longer value to max.
	 * keep repeating it till we complete the list. 
	 * @f:on
	 * 
	 * o(n^2)
	 * 
	 * @param text1
	 * @return length of the longest common/repeated subString
	 */
	public static int longestCommonSubString(String text1) {
		List<String> list = getSuffixes(text1);
		String max = "";
		Collections.sort(list);
		for (int i = 0; i < list.size() - 1; i++) {
			String test = longestCommonPrefix(list.get(i), list.get(i + 1));
			if (test.length() > max.length()) {
				max = test;
			}

		}
		System.out.println("longest Common SubString - " + max);
		return max.length();
	}

	/**
	 * Find the longest common prefix of two strings. here we can also use trie but
	 * for two string this approach is easier. but when we need to find from n
	 * strings trie will be easier.
	 * 
	 * e.g. - "helloJi","hell" -> longest prefix is - "hell"
	 * 
	 * o(n)
	 * 
	 * @param text1
	 * @param text2
	 * @return longest common prefix of two strings
	 */
	public static String longestCommonPrefix(String text1, String text2) {
		String result = "";
		int length = text2.length();
		if (text1.length() < text2.length()) {
			length = text1.length();
		}
		for (int i = 0; i < length; i++) {
			if (text1.charAt(i) == text2.charAt(i)) {
				result = result + text1.charAt(i);
			} else {
				return result;
			}
		}

		return result;
	}

	/**
	 * input - hello
	 *
	 * output - o,lo,llo,ello,hello
	 * 
	 * @param text
	 * @return list of suffixes
	 */
	public static List<String> getSuffixes(String text) {
		List<String> list = new ArrayList<>();
		String s = "";
		for (int i = text.length() - 1; i >= 0; i--) {
			s = text.charAt(i) + s;
			list.add(s);
		}
		return list;
	}

	/**
	 * String builder can also be used
	 * 
	 * @param source
	 * @return reversed string
	 */
	public static String reverse(String source) {
		String res = "";
		for (int i = 0; i < source.length(); i++) {
			res = source.charAt(i) + res;
		}
		return res;
	}

	/**
	 * Check whether two strings contain same characters in same order.
	 * 
	 * Given two strings s1 and s2, the task is to find whether the two string
	 * contain the same characters that occur in the same order.
	 * 
	 * For example: string “Geeks” and string “Geks” contain the same characters in
	 * same order.
	 * 
	 * Input: s1 = “Arnab”, s2 = “Andrew” => Output: No
	 * 
	 * @param s1
	 * @param s2
	 * @return true if both string contains same characters in same order
	 */
	public static boolean contains(String s1, String s2) {
		String res1 = s1.charAt(0) + "";
		String res2 = s2.charAt(0) + "";
		for (int i = 0; i < s1.length() - 1; i++) {
			if (s1.charAt(i) != s1.charAt(i + 1)) {
				res1 = res1 + s1.charAt(i + 1);
			}
		}

		for (int i = 0; i < s2.length() - 1; i++) {
			if (s2.charAt(i) != s2.charAt(i + 1)) {
				res2 = res2 + s2.charAt(i + 1);
			}
		}
		return res1.equals(res2);
	}

	/**
	 * 
	 * o(n*m)
	 * 
	 * check whether a given string str is substring of source.
	 * 
	 * @param source
	 * @param str
	 * @return true if str is substring of source
	 */
	public static boolean subString(String source, String str) {
		int i = 0;
		int j = 0;
		int k = i;
		while (i < source.length()) {
			if (source.charAt(i) != str.charAt(j)) {
				j = 0;
				i = ++k;
			} else {
				i++;
				j++;
			}
			if (j == str.length()) {
				return true;
			}

		}
		return false;
	}

	/**
	 * Abdul bari
	 * 
	 * check whether a given string str is substring of source.
	 * @f:off
	 * worst case - o(n*m)
	 * Best case - o(n+m)
	 * Rabin Karp Algorithm - 
	 * it uses hashcode of a string and instead of matching character one by one. 
	 * we just match hashcode and once hashcode matched we check the content.
	 * it saves time of un-necessary comparison all the time.
	 * but in worst case it might be possible that we might get hashcode same on every check.
	 * o(n*m) in worst case.
	 * to calculate hashcode again we just subtract hashcode of first character
	 * and add hashcode of next character in previous value.
	 * 
	 * For better performance make hash code function better to avoid un-necessary comparison.
	 * 
	 * @f:on
	 * 
	 * @param source
	 * @param str
	 * @return true if str is substring of source
	 */
	public static boolean subStringRabinKarpBetter(String source, String str) {
		int hSource = 0;
		int hStr = 0;
		// calculate hashcode of both source and string for first comparison
		for (int i = 0; i < str.length(); i++) {
			hSource = hSource + hashFunction(source.charAt(i));
			hStr = hStr + hashFunction(str.charAt(i));
		}
		// we compare hash first and if matched return true.
		// calculate hash again except for last value of i as we are generating hash in
		// advanced.
		for (int i = 0; i <= source.length() - str.length(); i++) {
			if (hStr == hSource) {
				int j = 0;
				for (j = 0; j < str.length(); j++) {
					if (source.charAt(j + i) != str.charAt(j)) {
						break;
					}
				}
				if (j == str.length()) {
					return true;
				}
			}
			// to avoid calculation after last index
			if (i < source.length() - str.length()) {
				hSource = hSource - hashFunction(source.charAt(i)) + hashFunction(source.charAt(i + str.length()));
			}
		}
		return false;
	}

	/**
	 * we multiplied char value with prime number.
	 * 
	 * @param s
	 * @return hash value
	 */
	private static int hashFunction(char s) {
		return s * 13;
	}

	/**
	 * An anagram of a string is another string that contains same characters, only
	 * the order of characters can be different. str1 - abcd , str2 - cdab above two
	 * string are anagram of each other.
	 * 
	 * Time complexity - O(N^2) - if map get and put considered worst performance
	 * 
	 * @param str1
	 * @param str2
	 * @return true if they are anagram of each other
	 */
	public static boolean anagram1(String str1, String str2) {

		if (str1.length() != str2.length()) {
			return false;
		}
		char[] ch1 = str1.toCharArray();
		char[] ch2 = str2.toCharArray();
		Map<Character, Integer> map1 = new HashMap<>();
		for (int i = 0; i < ch1.length; i++) {
			map1.put(ch1[i], map1.getOrDefault(ch1[i], 0) + 1);
		}

		for (int i = 0; i < ch2.length; i++) {
			if (map1.get(ch2[i]) == null) {
				return false;
			}
			map1.put(ch2[i], map1.get(ch2[i]) - 1);
		}

		for (Entry<Character, Integer> entry : map1.entrySet()) {
			if (entry.getValue() != 0) {
				return false;
			}
		}

		return true;
	}

	/**
	 * An anagram of a string is another string that contains same characters, only
	 * the order of characters can be different. str1 - abcd , str2 - cdab above two
	 * string are anagram of each other.
	 * 
	 * Time complexity - O(N)
	 * 
	 * @param str1
	 * @param str2
	 * @return true if anagram
	 */
	public static boolean anagram(String str1, String str2) {
		if (str1.length() != str2.length()) {
			return false;
		}

		char[] ch1 = str1.toCharArray();
		char[] ch2 = str2.toCharArray();
		int[] output = new int[256];

		for (int i = 0; i < ch1.length; i++) {
			output[ch1[i]] = output[ch1[i]] + 1;
		}

		for (int i = 0; i < ch2.length; i++) {
			output[ch2[i]] = output[ch2[i]] - 1;
		}

		for (int i = 0; i < output.length; i++) {
			if (output[i] != 0) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Given a time in -hour AM/PM format, convert it to military (24-hour) time.
	 * 
	 * Note: Midnight is 12:00:00AM on a 12-hour clock, and 00:00:00 on a 24-hour
	 * clock. Noon is 12:00:00PM on a 12-hour clock, and 12:00:00 on a 24-hour
	 * clock. input - 07:05:45PM output - 19:05:45
	 * 
	 * @param s
	 * @return string in 24 hour format
	 */
	public static String timeConversion(String s) {
		char[] ch = s.trim().toCharArray();
		boolean isPm = ch[8] == 'P';
		StringBuilder outputSb = new StringBuilder();
		for (int i = 2; i < ch.length - 2; i++) {
			outputSb.append(ch[i]);
		}
		String output = outputSb.toString();
		int res = Integer.parseInt("" + ch[0] + ch[1]);
		if (isPm) {
			int hh = 12;
			if (res != 12) {
				hh = res + 12;
			}
			output = hh + output;
		} else {
			output = res == 12 ? "00" + output : "" + ch[0] + ch[1] + output;
		}
		return output;
	}

	/**
	 * count occurrences of a given char in a long string
	 * 
	 * 
	 * @param content
	 * @param search
	 * @return count
	 */
	public static long countOccurences(String content, char search) {
		Pattern p = Pattern.compile("[^" + search + "]*" + search);
		Matcher match = p.matcher(content);
		long count = 0;
		while (match.find()) {
			count++;
		}
		return count;
	}

	/**
	 * 
	 * find repeated character a in string s for n log sequence.
	 * 
	 * e.g. - "aba" , for n=10 , so, a='a' appeared 7 times for 10 length.
	 * 
	 * O(N)
	 * 
	 * @param s
	 * @param n
	 * @return number of characters
	 */
	public static long repeatedString(String s, long n, char a) {
		int strLength = s.length();
		long aOccurrence = 0;
		long factor = n / strLength;
		long remainder = n % strLength;
		for (int i = 0; i < strLength; i++) {
			if (s.charAt(i) == a) {
				aOccurrence += (i < remainder) ? factor + 1 : factor;
			}
		}
		return aOccurrence;
	}

	/**
	 * Find Longest Common Subsequence between two Strings This solution can be
	 * improved by using dynamic programming.
	 * 
	 * @return length of lCS
	 */
	public static int longestCommonSubsequence(String s1, String s2) {
		return lcsRec(s1.toCharArray(), s2.toCharArray(), 0, 0);
	}

	/**
	 * Time complexity - o(2^n).
	 * A subsequence is a sequence that appears in the same relative order, but not necessarily contiguous. 
	 * For example, “abc”, “abg”, “bdf”, “aeg”, ‘”acefg”, .. etc are subsequences of “abcdefg”.
	 * 
	 * @f:off
	 * As every character has possibility of either to get included or not included
	 * String s1 = abdace
	 * String s2 = babce
	 * LCS are - bace,abce,bce,ce,e
	 * so answer is 4.
	 * LCS for input Sequences “ABCDGH” and “AEDFHR” is ADH of length 3
	 * 
	 * @f:on
	 * @param ch1
	 * @param ch2
	 * @param i
	 * @param j
	 * @return length of lCS
	 */
	private static int lcsRec(char[] ch1, char[] ch2, int i, int j) {
		if (ch1.length == i || ch2.length == j) {
			return 0;
		}
		if (ch1[i] == ch2[j]) {
			return 1 + lcsRec(ch1, ch2, i + 1, j + 1);
		}
		return MathUtil.max(lcsRec(ch1, ch2, i + 1, j), lcsRec(ch1, ch2, i, j + 1));
	}

	/**
	 * check whether number is perfect square
	 * 
	 * O(rootN)
	 * 
	 * @param number
	 * @return true if number is perfect square
	 */
	public static boolean perfectSquare(String numberStr) {

		int num = Integer.parseInt(numberStr);
		int i = 1;
		for (; i * i <= num; i++) {

		}
		int res = i - 1;
		return res * res == num;
	}

	/**
	 * "A palindrome is a string that reads the same forward and backward"
	 * 
	 * For example: radar or madam
	 * 
	 * o(n/2)
	 * 
	 * @param string
	 * @return true if palindrome
	 */
	public static boolean checkPalindrome(String string) {
		for (int i = 0; i < string.length() / 2; i++) {
			if (string.charAt(i) != string.charAt(string.length() - 1 - i)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Given an array of strings, group anagrams together.
	 * 
	 * Example:
	 * 
	 * Input: ["eat", "tea", "tan", "ate", "nat", "bat"]
	 * 
	 * Output: [["ate","eat","tea"], ["nat","tan"], ["bat"] ]
	 * 
	 * @param strs
	 * @return grouped anagrams
	 */
	public static List<List<String>> groupAnagrams(String[] strs) {
		Map<String, List<String>> map = new HashMap<>();
		for (int i = 0; i < strs.length; i++) {
			String k = hash(strs[i]);
			if (!map.containsKey(k)) {
				map.put(k, new LinkedList<>());
			}
			map.get(k).add(strs[i]);
		}
		return new ArrayList<>(map.values());
	}

	/**
	 * o(n)
	 * 
	 * @param str
	 * @return hash value which will be same for anagram strings
	 */
	private static String hash(String str) {
		int[] ch = new int[26];
		// count the occurrences of each character in given string
		for (int i = 0; i < str.length(); i++) {
			ch[str.charAt(i) - 'a'] += 1;
		}
		// now calculate hash value so that for eat hash will be 1a1e1t and for tea also
		// it will be 1a1e1t
		StringBuilder h = new StringBuilder();
		for (int i = 0; i < ch.length; i++) {
			if (ch[i] != 0) {
				h.append(ch[i]);// add count
				h.append((char) ('a' + i));// add character
			}
		}
		return h.toString();
	}

	/**
	 * Given a string containing only three types of characters: '(', ')' and '*',
	 * write a function to check whether this string is valid. We define the
	 * validity of a string by these rules:
	 * 
	 * Any left parenthesis '(' must have a corresponding right parenthesis ')'.
	 * 
	 * Any right parenthesis ')' must have a corresponding left parenthesis '('.
	 * 
	 * Left parenthesis '(' must go before the corresponding right parenthesis ')'.
	 * 
	 * '*' could be treated as a single right parenthesis ')' or a single left
	 * parenthesis '(' or an empty string.
	 * 
	 * An empty string is also valid.
	 * 
	 * @f:off
	 * Input: "(*)" 
	 * Output: True
	 * 
	 * Input: "(*))"
	 * Output: True
	 * @f:on
	 * 
	 * @param s
	 * @return true if valid
	 */
	public static boolean checkValidString(String s) {
		int bal = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(' || s.charAt(i) == '*') {
				bal++;
			} else if (--bal < 0) {
				return false;
			}
		}
		// terminate if brackets are balanced already.
		if (bal == 0) {
			return true;
		}
		// repeat again by going right to left
		bal = 0;
		for (int i = s.length() - 1; i >= 0; i--) {
			if (s.charAt(i) == ')' || s.charAt(i) == '*') {
				bal++;
			} else if (--bal < 0) {
				return false;
			}
		}
		// if we reach here it means valid as it passes both loops without failing
		return true;
	}

	/**
	 * Perform String Shifts
	 * 
	 * You are given a string s containing lowercase English letters, and a matrix
	 * shift, where shift[i] = [direction, amount]:
	 * 
	 * direction can be 0 (for left shift) or 1 (for right shift). amount is the
	 * amount by which string s is to be shifted. A left shift by 1 means remove the
	 * first character of s and append it to the end. Similarly, a right shift by 1
	 * means remove the last character of s and add it to the beginning.
	 * 
	 * Return the final string after all operations.
	 * 
	 * @f:off
	 * Example:
	 * 
	 * Input: s = "abcdefg", shift = [[1,1],[1,1],[0,2],[1,3]] 
	 * 
	 * Output: "efgabcd"
	 * 
	 * Explanation: 
	 * [1,1] means shift to right by 1. "abcdefg" -> "gabcdef" 
	 * [1,1] means shift to right by 1. "gabcdef" -> "fgabcde" 
	 * [0,2] means shift to left by 2. "fgabcde" -> "abcdefg" 
	 * [1,3] means shift to right by 3. "abcdefg" -> "efgabcd"
	 * @f:on
	 * 
	 * LEETCODE,o(n)
	 * 
	 * @param s
	 * @param shift
	 * @return string after rotation.
	 */
	public static String stringShift(String s, int[][] shift) {
		int c = 0;
		for (int i = 0; i < shift.length; i++) {
			if (shift[i][0] == 0) {
				c += shift[i][1];
			} else {
				c -= shift[i][1];
			}
		}

		if (c > 0) {
			return shiftLeft(c % s.length(), s);
		}
		return shiftRight((-c) % s.length(), s);
	}

	/**
	 * 
	 * @param n
	 * @param s
	 * @return String after left rotation n
	 */
	private static String shiftLeft(int n, String s) {
		String pre = s.substring(0, n);
		s = s.substring(pre.length(), s.length());
		return s + pre;
	}

	/**
	 * 
	 * @param n
	 * @param s
	 * @return String after right rotation n
	 */
	private static String shiftRight(int n, String s) {
		String suf = s.substring(s.length() - n, s.length());
		s = s.substring(0, s.length() - suf.length());
		return suf + s;
	}

}

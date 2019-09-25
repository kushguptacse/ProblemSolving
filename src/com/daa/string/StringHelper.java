package com.daa.string;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.daa.math.MathUtil;

public final class StringHelper {
	private StringHelper() {
	}

	/**
	 * Check whether two strings contain same characters in same order.
	 * 
	 * Given two strings s1 and s2, the task is to find whether the two string contain the
	 * same characters that occur in the same order.
	 * 
	 * For example: string “Geeks” and string “Geks” contain the same characters in same
	 * order.
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

		for (int i = 0; i < s2.length()-1; i++) {
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
		int hash = 0;
		int hashStr = 0;
		for (int i = 0; i < str.length(); i++) {
			hash = hash + hashFunction(source.charAt(i));
			hashStr = hashStr + hashFunction(str.charAt(i));
		}
		for (int i = 0; i <= source.length() - str.length(); i++) {
			if (hash == hashStr) {
				int j = 0;
				for (j = 0; j < str.length(); j++) {
					if (source.charAt(j + i) != str.charAt(j)) {
						break;
					}
				}
				if (j == str.length()) {
					System.out.println("Pattern Found at index : " + i);
				}

			} else if (i + str.length() < source.length()) {
				hash = hash - hashFunction(source.charAt(i)) + hashFunction(source.charAt(i + str.length()));
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
	 * An anagram of a string is another string that contains same characters, only the order
	 * of characters can be different. str1 - abcd , str2 - cdab above two string are anagram
	 * of each other.
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
	 * An anagram of a string is another string that contains same characters, only the order
	 * of characters can be different. str1 - abcd , str2 - cdab above two string are anagram
	 * of each other.
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
	 * Note: Midnight is 12:00:00AM on a 12-hour clock, and 00:00:00 on a 24-hour clock. Noon
	 * is 12:00:00PM on a 12-hour clock, and 12:00:00 on a 24-hour clock. input - 07:05:45PM
	 * output - 19:05:45
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
	 * Find Longest Common Subsequence between two Strings This solution can be improved by
	 * using dynamic programming.
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

}

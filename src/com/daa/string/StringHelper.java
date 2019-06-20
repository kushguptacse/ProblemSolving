package com.daa.string;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class StringHelper {
	private StringHelper() {
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
	public static long repeatedString(String s, long n,char a) {
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
		int res = i-1;
		return res*res==num;
	}

}

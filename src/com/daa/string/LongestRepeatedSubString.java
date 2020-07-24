package com.daa.string;

import java.util.HashSet;
import java.util.Set;

/**
 * Longest repeated String OR Longest duplicate String OR longestCommonSubString
 * longestRepeatedSubString OR longestDuplicateSubString
 * 
 * better approach then
 */
public class LongestRepeatedSubString {
	public String longestDupSubstring(String s) {
		int start = 1;
		int end = s.length();
		String max = "";
		while (start <= end) {
			int mid = end - (end - start) / 2;
			String str = rabinKarp(s, mid);
			if (str == null) {
				end = mid - 1;
			} else {
				max = str;
				start = mid + 1;
			}
		}
		return max;
	}

	private String rabinKarp(String s, int len) {
		Set<Long> set = new HashSet<>();
		long h = hash(s.substring(0, len));
		set.add(h);
		long pow = 1;
		for (int l = 1; l < len; l++) {
			pow *= 26;
		}
		for (int i = 1; i <= s.length() - len; i++) {
			h = nextHash(pow, h, s.charAt(i - 1), s.charAt(i + len - 1));
			if (!set.add(h)) {
				return s.substring(i, len + i);
			}
		}
		return null;
	}

	private long nextHash(long pow, long hash, char left, char right) {
		return (hash - left * pow) * 26 + right;
	}

	private long hash(String s) {
		long hash = 0;
		long mul = 1;
		for (int i = s.length() - 1; i >= 0; i--) {
			hash += s.charAt(i) * mul;
			mul *= 26;
		}
		return hash;
	}
}

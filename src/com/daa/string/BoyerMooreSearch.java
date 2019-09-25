package com.daa.string;

import java.util.stream.IntStream;

import com.daa.math.MathUtil;

/**
 * UDEMY
 * 
 * Aim - we want to find substring from a given file or long string
 * @f:off
 * Approach - 
 * 1. brute force take o(nm) time. as we need to shift back to search item again.
 * 2. rabinkarp is better than traditional approach as it calculate hash code. 
 *    We just match hashcode and once hashcode matched we check the content. o(n+m)
 * 3. In Rabin-Karp worst case o(nm). i.e. every comparison has same hashcode and string are different.
 *    It's performance depends on hashcode implementation.
 * 4. Boyer Moore is better in a way that Mismatch character takes about n/m compares. it is even less than linear.
 * 5. for large string it is the best. best case - o(n/m)
 * 	  but if we have pattern like CDDDDDDDDDD and string like CD. it will work equivalent to brute force.
 * @f:on 
 */
public class BoyerMooreSearch {

	public boolean contains(String text, String key) {
		int[] table = new int[256];
		IntStream.range(0, table.length).forEach(i -> table[i] = key.length());
		prepareBadCharacterTable(key, table);
		int i = key.length() - 1;
		int j = i;
		while (i < text.length()) {
			if (key.charAt(j) == text.charAt(i)) {
				i--;
				j--;
			} else {
				i = table[text.charAt(i)] + i;
				j = key.length() - 1;
			}
			if (j==0) {
				System.out.println(i);
				return true;
			}
		}

		return false;
	}

	/**
	 * 
	 * @param key
	 */
	private void prepareBadCharacterTable(String key, int[] table) {
		for (int i = 0; i < key.length(); i++) {
			int max = MathUtil.max(1, key.length() - 1 - i);
			table[key.charAt(i)] = max;
		}
	}

	public static void main(String[] args) {
		BoyerMooreSearch obj = new BoyerMooreSearch();
		System.out.println(obj.contains("My name is Joe!", "Joe"));
		System.out.println(obj.contains("ABAAABCD", "ABC"));
		System.out.println(obj.contains("abcdefghijklmnopqrstuvwxyz", "qrstuv"));
	}

}

package com.daa.hashing;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class PracticeHashMapQuestion {

	public static void main(String[] args) {
		char[] ch = { 'a', '1', 'b', 'a', 'c', '1', 'd' };
		removeDuplicates(ch);
		int[] arr = { 3, 4, 3, 2, 1, 5, 5 };
		int[] brr = { 7, 0, 1 };
		System.out.println("Elements are same in arrays : " + checkSameElements(arr, brr));
		printSymmetricPairs(new int[][] { { 11, 20 }, { 40, 30 }, { 70, 30 }, { 30, 71 }, { 5, 10 }, { 10, 5 }, { 30, 40 } });
		int k = 2;
		printRepeatedElements(arr, k);
		printMaxOcurrances(new int[][] { { 1, 2, 3 }, { 3, 8, 1, 4 }, { 5, 4, 2, 0, 9 }, { 3 }, { 13, 9, 1, 3 } });
		System.out.println("Pair whose some is " + k + " : " + checkSumExists(arr, brr, k));
		firstNonRepeatingCharacter("GeeksQuiz");
		firstRepeatingCharacter("helr rt");
		String str1 = "geeksforgeeks";
		String str2 = "mask";
		System.out.println("String '" + str1 + "' after removing dirty characters from String '" + str2 + "' : " + removeDirtyChars(str1, str2));
		printPairs(new int[] { 2, 3, 4, -2, 6, 8, 9, 11 }, 6);
	}

	/**
	 * 
	 * print pairs whose sum is k from 1-D Array
	 * 
	 * @f:off
	 * Input  :  arr[] = {1, 5, 7, -1, 5}, 
     *           sum = 6
	 * Output : (1, 5) (7, -1) (1, 5)
	 * 
	 * @f:on
	 * 
	 * @param arr
	 * @param k
	 */
	public static void printPairs(int[] arr, int k) {
		Set<Integer> set = new HashSet<>();
		System.out.print("Pairs whose sum is " + k + ": {");
		for (int i = 0; i < arr.length; i++) {
			if (set.contains(k - arr[i])) {
				System.out.print("{" + (k - arr[i]) + "," + arr[i] + "}");
			}
			else {
			set.add(arr[i]);
			}
		}
		System.out.println("}");
	}

	/**
	 * Method removes the specified characters from a given string which are given in another
	 * string
	 * 
	 * @f:off
	 * 
	 * 
	 * input - geeksforgeeks , mask
	 * 
	 * output - geeforgee
	 * 
	 * @f:on
	 * @param str1
	 * @param str2
	 * @return string After removing characters present in second string
	 */
	public static String removeDirtyChars(String str1, String str2) {
		StringBuilder sb = new StringBuilder();
		int[] set = new int[256];
		for (int i = 0; i < str2.length(); i++) {
			set[str2.charAt(i)] = 1;
		}

		for (int i = 0; i < str1.length(); i++) {
			if (set[str1.charAt(i)] != 1) {
				sb.append(str1.charAt(i));
			}
		}

		return sb.toString();
	}

	/**
	 * print the first repeating character that occur in String. the first repeated character
	 * in the string “geeksforgeeks” is ‘e’.
	 * 
	 * @param string
	 */
	public static void firstRepeatingCharacter(String str) {
		System.out.print("First repeating character in '" + str + "' is: ");
		int[] ch = new int[256];
		for (int i = 0; i < str.length(); i++) {
			char ch1 = str.charAt(i);
			if (ch[ch1] == 1) {
				System.out.println((char) ch1);
				break;
			}
			ch[ch1] = ch[ch1] + 1;
		}
	}

	/**
	 * print the first non repeating character that occur in String. the first non-repeated
	 * character in the string “abzddab” is ‘z’.
	 * 
	 * @param string
	 */
	public static void firstNonRepeatingCharacter(String str) {
		System.out.print("First non repeating character in '" + str + "' is: ");
		int[] ch = new int[256];
		for (int i = 0; i < str.length(); i++) {
			char ch1 = str.charAt(i);
			ch[ch1] = ch[ch1] + 1;
		}
		for (int i = 0; i < ch.length; i++) {
			if (ch[i] == 1) {
				System.out.println((char) i);
				break;
			}
		}
	}

	/**
	 * Given two sets A and B, and a number K, Give an algorithm for finding whether there
	 * exists a pair of elements, one from A and one from B, that add up to K.
	 * 
	 * @param arr
	 * @param brr
	 * @param k
	 * @return pair if exists
	 */
	public static String checkSumExists(int[] arr, int[] brr, int k) {
		Set<Integer> set = new HashSet<>();
		for (int i = 0; i < arr.length; i++) {
			set.add(k - arr[i]);
		}
		for (int i = 0; i < brr.length; i++) {
			if (set.contains(brr[i])) {
				return brr[i] + "," + (k - brr[i]);
			}
		}
		return "";
	}

	/**
	 * print the element which appeared in the maximum number of sets.
	 * @f:off
	 * assumption - we are considering set. that means in one set element can appear max 1 time
	 * 
	 * valid set - 
	 * 
	 * new int[][] { { 1, 2, 3 }, { 3, 8, 1, 4 }, { 5, 4, 2, 0, 9 }, { 3 }, { 13, 9, 1, 3 } }
	 * o/p- 3
	 * 
	 * invalid set - 
	 * {{1,2},{1},{4,4,4}}
	 * 
	 * 
	 * @f:on
	 * @param arr
	 */
	public static void printMaxOcurrances(int[][] arr) {
		Map<Integer, Integer> map = new HashMap<>();
		System.out.print("Maximum Element present in sets is: ");
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				map.put(arr[i][j], map.getOrDefault(arr[i][j], 0) + 1);
			}
		}
		int max = Integer.MIN_VALUE;
		int res = 0;
		for (Entry<Integer, Integer> entry : map.entrySet()) {
			if (entry.getValue() > max) {
				max = entry.getValue();
				res = entry.getKey();
			}
		}

		System.out.println(res);

	}

	/**
	 * Given a array which is unordered and can contains duplicate. print the elements that
	 * are repeated exactly 'k' times. o(n)
	 * 
	 * @param arr
	 * @param k
	 */
	public static void printRepeatedElements(int[] arr, int k) {
		Map<Integer, Integer> map = new HashMap<>();
		System.out.println("Elements Repeated " + k + " times are :");

		for (int i = 0; i < arr.length; i++) {
			map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
		}

		map.forEach((i, v) -> {
			if (v == k) {
				System.out.print(i + ",");
			}
		});
		System.out.println();
	}

	/**
	 * print the symmetric pairs in array.
	 * o(n)
	 * @f:off
	 * we can also use map instead of keeping concatenated string in set. but in that case below assumption should be taken -
	 * that the first elements of all pairs are distinct.
	 * 
	 * input - 
	 * {{1,3},{2,6},{3,5},{7,4},{5,3},{8,7}}
	 * output - 
	 * {5,3}
	 * @f:on
	 * 
	 * @param arr
	 */
	public static void printSymmetricPairs(int[][] arr) {
		System.out.println("Symmetric pairs are :");
		System.out.print("{");
		Set<String> set = new HashSet<>();
		for (int i = 0; i < arr.length; i++) {
			if (set.contains(arr[i][0] + "," + arr[i][1])) {
				System.out.print("{" + arr[i][0] + "," + arr[i][1] + "}");
			} else {
				set.add(arr[i][1] + "," + arr[i][0]);
			}
		}
		System.out.println("}");
	}

	/**
	 * check two arrays are same or not. by same means number of elements and count must be same.
	 * order doesn't matter but occurrences must be same.
	 * o(n)
	 * @f:off
	 * 
	 * Input  : arr1[] = {1, 2, 5, 4, 0, 2, 1};
     *   arr2[] = {2, 4, 5, 0, 1, 1, 2}; 
	 *   Output : true 
	 *   
	 * Input : arr1[] = {1, 7, 1};
     *   arr2[] = {7, 7, 1};
	 *   Output : false
	 * @f:on
	 * 
	 * @param arr
	 * @param brr
	 * @return true if same
	 */
	public static boolean checkSameElements(int[] arr, int[] brr) {
		if (arr.length != brr.length) {
			return false;
		}
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < arr.length; i++) {
			map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
			map.put(brr[i], map.getOrDefault(brr[i], 0) - 1);
		}

		for (Integer val : map.values()) {
			if (!val.equals(0)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * from given array remove the duplicates o(n)
	 * 
	 * @param ch
	 */
	public static void removeDuplicates(char[] ch) {
		char[] res = new char[256];
		System.out.println("unique elements are :");
		for (int i = 0; i < ch.length; i++) {
			if (res[ch[i]] != '1') {
				System.out.print(ch[i] + ",");
			}
			res[ch[i]] = '1';
		}
		System.out.println();
	}

}

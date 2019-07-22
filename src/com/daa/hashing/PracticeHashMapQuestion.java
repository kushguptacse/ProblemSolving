package com.daa.hashing;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PracticeHashMapQuestion {

	public static void main(String[] args) {
		char[] ch = { 'a', '1', 'b', 'a', 'c', '1', 'd' };
		removeDuplicates(ch);
		int[] arr = { 1, 7, 1 };
		int[] brr = { 7, 7, 1 };
		System.out.println("Elements are same in arrays : " + checkSameElements(arr, brr));
		printSymmetricPairs(new int[][] { { 11, 20 }, { 30, 40 }, { 30, 70 }, { 5, 10 }, { 40, 30 }, { 10, 5 } });
	}

	/**
	 * print the symmetric pairs in array.
	 * o(n)
	 * @f:off
	 * input - 
	 * {{1,3},{2,6},{3,5},{7,4},{5,3},{8,7}}
	 * output - 
	 * {5,3}
	 * @f:on
	 * 
	 * @param arr
	 */
	private static void printSymmetricPairs(int[][] arr) {
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

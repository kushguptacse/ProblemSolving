package com.daa.backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.daa.array.ArrayUtil;

public class Permutation {

	public static void main(String[] args) {
		Permutation permutation = new Permutation();
		List<List<Integer>> list = permutation.permute(new int[] { 1, 2, 3 });
		list.forEach(System.out::println);
		permutation.permute("abc").forEach(System.out::println);

	}
	
	/**
	 * return all permutations of array. array contains unique elements.
	 * 
	 * @param nums
	 * @return all possible combination list
	 */
	public List<List<Integer>> permute(int[] nums) {
		List<List<Integer>> list = new ArrayList<>();
		permutations(nums, 0, list);
		return list;
	}
	
	/**
	 * print all possible unique permutation of array.
	 * 
	 * @param nums
	 * @return list of unique permutation of array
	 */
	public List<List<Integer>> permuteUnique(int[] nums) {
		List<List<Integer>> list = new ArrayList<>();
		permuteUnique(nums, 0, list);
		return list;
	}


	/**
	 * print all possible permutation of a String.
	 * 
	 * @param str
	 * @return List of all permutation of a String.
	 */
	public List<String> permute(String str) {
		List<String> list = new ArrayList<>();
		permuteString(str, "", list);
		return list;
	}

	private void permuteString(String str, String prefix, List<String> list) {
		if (str.length() == 0) {
			list.add(prefix);
			return;
		}
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i); // get ith character
			String res = str.substring(0, i) + str.substring(i + 1); // get remaining string
			permuteString(res, prefix + ch, list);
		}
	}

	private void permutations(int[] nums, int i, List<List<Integer>> list) {
		if (i == nums.length) {
			List<Integer> sublist = new ArrayList<>();
			for (int j = 0; j < nums.length; j++) {
				sublist.add(nums[j]);
			}
			list.add(sublist);
			return;
		}
		for (int start = i; start < nums.length; start++) {
			ArrayUtil.swap(nums, i, start);
			permutations(nums, i + 1, list);
			ArrayUtil.swap(nums, i, start);
		}

	}


	private void permuteUnique(int[] nums, int i, List<List<Integer>> list) {
		if (i == nums.length) {
			List<Integer> l1 = new ArrayList<>();
			for (int n : nums) {
				l1.add(n);
			}
			list.add(l1);
			return;
		}
		Set<Integer> set = new HashSet<>();
		for (int s = i; s < nums.length; s++) {
			if (set.add(nums[s])) {
				ArrayUtil.swap(nums, s, i);
				permuteUnique(nums, i + 1, list);
				ArrayUtil.swap(nums, s, i);
			}
		}
	}

}

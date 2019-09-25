package com.daa.string;

import java.util.Arrays;

public class TSTMain {

	public static void main(String[] args) {

		insertAndSearch(new TernarySearchTrie());
		print(new TernarySearchTrie());
	}

	private static void print(TernarySearchTrie obj) {
		String[] str = { "apple", "pine", "pineapple", "pin", "in", "i", "mango", "man", "an", "a" };
		Arrays.stream(str).forEach(obj::insert);
		obj.print();

	}

	private static void insertAndSearch(TernarySearchTrie obj) {
		obj.insert("cat");
		obj.insert("cats");
		obj.insert("up");
		obj.insert("bug");
		obj.insert("bugzila");
		System.out.println(obj.lengthOfLargestWorld());
		System.out.println(obj.contains("cats"));
		System.out.println(obj.contains("bu"));
		System.out.println(obj.contains("cat"));

	}

}

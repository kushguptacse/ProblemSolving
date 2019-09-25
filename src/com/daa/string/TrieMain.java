package com.daa.string;

import java.util.Arrays;
import java.util.List;

public class TrieMain {
	public static void main(String[] args) {
		lcs();
		autoComplete();
		insertAndSearch();

	}

	private static void lcs() {
		Trie trie = new Trie();
		String arr[] = { "geeksforgeeks", "geeks", "geek", "geezer" };
		Arrays.stream(arr).forEach(trie::insert);
		System.out.println("The longest common prefix is: " + trie.longestCommonPrefix());
		arr = new String[] { "apple", "ape", "april" };
		Trie trie1 = new Trie();
		Arrays.stream(arr).forEach(trie1::insert);
		System.out.println("The longest common prefix is: " + trie1.longestCommonPrefix());

	}

	private static void insertAndSearch() {
		Trie trie = new Trie();
		// Input keys (use only 'a' through 'z' and lower case)
		String[] keys = { "the", "a", "there", "answer", "any", "by", "bye", "their" };

		String[] output = { "Not present in trie", "Present in trie" };

		// Construct trie
		for (int i = 0; i < keys.length; i++)
			trie.insert(keys[i]);

		trie.print();
		System.out.println("Search operation -");
		// Search for different keys
		if (trie.contains("the"))
			System.out.println("the --- " + output[1]);
		else
			System.out.println("the --- " + output[0]);

		if (trie.contains("these"))
			System.out.println("these --- " + output[1]);
		else
			System.out.println("these --- " + output[0]);

		if (trie.contains("their"))
			System.out.println("their --- " + output[1]);
		else
			System.out.println("their --- " + output[0]);

		if (trie.contains("thaw"))
			System.out.println("thaw --- " + output[1]);
		else
			System.out.println("thaw --- " + output[0]);

		if (trie.contains("bye"))
			System.out.println("bye --- " + output[1]);
		else
			System.out.println("bye --- " + output[0]);

		if (trie.contains("an"))
			System.out.println("an --- " + output[1]);
		else
			System.out.println("an --- " + output[0]);
	}

	private static void autoComplete() {

		Trie trie1 = new Trie();
		trie1.insert("adam");
		trie1.insert("adr");
		trie1.insert("adda");
		trie1.insert("adada");
		trie1.insert("adazzz");
		System.out.println("AutoComplete suggestion -");
		trie1.autocomplete("ada").forEach(System.out::println);

		Trie trie2 = new Trie();
		trie2.insert("adam");
		trie2.insert("kevin");
		trie2.insert("evelin");
		trie2.insert("addams");
		trie2.insert("junior");
		trie2.sort().forEach(System.out::println);

		Trie trie = new Trie();
		trie.insert("hello");
		trie.insert("dog");
		trie.insert("hell");
		trie.insert("cat");
		trie.insert("a");
		trie.insert("hel");
		trie.insert("help");
		trie.insert("helps");
		trie.insert("helping");
		List<String> result = trie.autocomplete("hel");
		System.out.println("AutoComplete suggestion -");
		result.forEach(System.out::println);
	}
}

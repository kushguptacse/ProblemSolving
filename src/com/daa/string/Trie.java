package com.daa.string;

import java.util.ArrayList;
import java.util.List;

/**
 * String searching and sorting approach.
 * 
 * Trie is used to store the data in form of tree where every node will have all the
 * possible characters that search string can have. for e.g. - every node will 26 (a-z)
 * characters.
 * 
 * Udemy.
 * 
 * it is also known as prefix tree and it is used in auto complete and spell checking
 * application. as it offers fastest search and sort mechanism.
 * 
 * As we are storing 26 characters in every node it takes lot of memory.
 *
 */
public class Trie {

	private TrieNode root;

	/**
	 * Insert key into Trie data structure
	 * 
	 * every node will have 26 character array. so on inserting a string we insert it
	 * character by character and first character will go in first array and index of that
	 * character will be character - 'a'.
	 * 
	 * for example - insert - apple
	 * 
	 * so first array initially contain 26 position with null value. index of 'a' will be 0.
	 * so we create new object and add it to array 0 index. for next character 'p' we put it
	 * into second array present in index of 'a'. and index in second array will be p-'a'. in
	 * this way we proceed to third array pointed by p-'a' index and insert there. finally
	 * when we reach at the last character 'e' - we will set the isLeaf flag as true so that
	 * we know it is the end of string
	 * 
	 * o(m) - height of the tree
	 * 
	 * @param key
	 */
	public void insert(String key) {
		if (key == null) {
			return;
		}
		if (root == null) {
			root = new TrieNode();
		}

		TrieNode temp = root;
		for (int i = 0; i < key.length(); i++) {
			int level = key.charAt(i) - 'a';
			if (temp.getCharacters()[level] == null) {
				temp.getCharacters()[level] = new TrieNode();
			}
			temp = temp.getCharacters()[level];
		}
		temp.setEnd(true);
	}

	/**
	 * check whether search string is present in trie or not
	 * 
	 * o(m) - height of trie tree
	 * 
	 * @param search
	 * @return true if exists
	 */
	public boolean contains(String search) {
		if (search == null || root == null) {
			return false;
		}
		TrieNode temp = root;
		for (int i = 0; i < search.length(); i++) {
			int level = search.charAt(i) - 'a';
			if (temp == null || temp.getCharacters()[level] == null) {
				return false;
			}
			temp = temp.getCharacters()[level];
		}
		// if we does not want to check for exact match. i.e. we are also okay if search string is
		// just a substring present in the trie then just return true
		/**
		 * For e.g. - if arr ={apple,app} and search string is ap which is not in arr but is valid
		 * substring then return true
		 */
		return temp.isEnd();
	}

	/**
	 * display the entire data of trie
	 */
	public void print() {
		System.out.println("Trie Data: ");
		List<String> list = new ArrayList<>();
		printUtil(root, list, 0, "");
		list.forEach(System.out::println);
	}

	/**
	 * Find the all possible options according to prefix passed. just like auto-complete
	 * 
	 * @param prefix
	 * @return list of Suggestions
	 */
	public List<String> autocomplete(String search) {
		List<String> list = new ArrayList<>();
		if (search == null || root == null) {
			return list;
		}

		TrieNode temp = root;
		for (int i = 0; i < search.length(); i++) {
			int level = search.charAt(i) - 'a';
			if (temp == null || temp.getCharacters()[level] == null) {
				return list;
			}
			temp = temp.getCharacters()[level];
		}

		printUtil(temp, list, search.length(), search);

		return list;
	}

	/**
	 * uses dfs like approach.
	 * 
	 * @param node
	 * @param result
	 * @param level
	 * @param prefix
	 */
	private void printUtil(TrieNode node, List<String> result, int level, String prefix) {
		if (node == null) {
			return;
		}
		if (node.isEnd()) {
			result.add(prefix);
		}
		for (int i = 0; i < node.getCharacters().length; i++) {
			if (node.getCharacters() != null) {
				char newChar = (char) ('a' + i);
				printUtil(node.getCharacters()[i], result, level + 1, prefix + newChar);
			}
		}
	}

	/**
	 * longest common prefix
	 * 
	 * Input : {“geeksforgeeks”, “geeks”, “geek”, “geezer”} => Output : "gee"
	 * 
	 * Input : {"apple", "ape", "april"} => Output : "ap"
	 * 
	 * @return longest common prefix
	 */
	public String longestCommonPrefix() {
		if (root == null) {
			return "";
		}

		String result = "";
		TrieNode temp = root;
		int level = 0;
		while (temp != null && !temp.isEnd()) {
			int count = 0;
			for (int i = 0; i < temp.getCharacters().length; i++) {
				if (temp.getCharacters()[i] != null) {
					count++;
					level = i;
				}
				if (count == 2) {
					return result;
				}
			}
			result = result + ((char) (level + 'a'));
			temp = temp.getCharacters()[level];
		}
		return result;
	}

	/**
	 * @return sorted list of data present in trie
	 */
	public List<String> sort() {
		return autocomplete("");
	}

}

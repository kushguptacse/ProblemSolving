package com.daa.string;

public class TernarySearchTrie {
	private TSTNode root;

	/**
	 * insert is combination of bst and trie.
	 * @f:off
	 * i.e. we check if character < node. if yes got left.
	 * character> node. go right.
	 * character==node. go middle.
	 * at any point if we reach null. insert node to that position
	 * @f:on
	 * @param key
	 * 
	 * o(length of tree) - in worst case tst may have linear length of tree
	 * 
	 */
	public void insert(String key) {
		root = insertUtil(root, key, 0);
	}

	private TSTNode insertUtil(TSTNode node, String key, int i) {
		if (node == null) {
			node = new TSTNode(key.charAt(i));
		}
		if (node.getCharacter() > key.charAt(i)) {
			node.setLeft(insertUtil(node.getLeft(), key, i));
		} else if (node.getCharacter() < key.charAt(i)) {
			node.setRight(insertUtil(node.getRight(), key, i));
		} else if (i < key.length() - 1) {
			node.setMiddle(insertUtil(node.getMiddle(), key, i + 1));
		} else {
			node.setEnd(true);
		}
		return node;
	}

	/**
	 * 
	 * @param key
	 * @return true if key exists
	 * 
	 *         o(length of tree)
	 */
	public boolean contains(String key) {
		return containsUtil(root, key, 0);
	}

	private boolean containsUtil(TSTNode node, String key, int i) {
		if (node == null) {
			return false;
		}
		if (node.getCharacter() > key.charAt(i)) {
			return (containsUtil(node.getLeft(), key, i));
		} else if (node.getCharacter() < key.charAt(i)) {
			return (containsUtil(node.getRight(), key, i));
		} else if (i < key.length() - 1) {
			return (containsUtil(node.getMiddle(), key, i + 1));
		} else {
			return node.isEnd();
		}
	}

	public void print() {
		System.out.println("Following is traversal of ternary search trie");
		printutil(root, "");
	}

	private void printutil(TSTNode node, String str) {
		if (node == null) {
			return;
		}
		printutil(node.getLeft(), str);

		str = str + node.getCharacter();
		if (node.isEnd()) {
			System.out.println(str);
		}

		printutil(node.getMiddle(), str);
		str = str.substring(0, str.length() - 1);

		printutil(node.getRight(), str);
	}

}

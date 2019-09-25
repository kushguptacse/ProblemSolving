package com.daa.string;

public class TrieNode {

	private static final int DEFAULT_SIZE = 26;

	private final TrieNode[] characters = new TrieNode[DEFAULT_SIZE];

	private boolean leaf;

	/**
	 * @return the characters
	 */
	public TrieNode[] getCharacters() {
		return characters;
	}

	/**
	 * @return the leaf
	 */
	public boolean isLeaf() {
		return leaf;
	}

	/**
	 * @param leaf the leaf to set
	 */
	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}



}

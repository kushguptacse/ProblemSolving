package com.daa.string;

public class TrieNode {

	private static final int DEFAULT_SIZE = 26;

	private final TrieNode[] characters = new TrieNode[DEFAULT_SIZE];

	private boolean end;

	/**
	 * @return the characters
	 */
	public TrieNode[] getCharacters() {
		return characters;
	}

	/**
	 * @return true if it is the end of the word
	 */
	public boolean isEnd() {
		return end;
	}

	/**
	 * @param end the end to set
	 */
	public void setEnd(boolean leaf) {
		this.end = leaf;
	}

}

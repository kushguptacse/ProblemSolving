package com.daa.string;

public class TSTNode {

	private TSTNode left;
	private TSTNode middle;
	private TSTNode right;
	private Character character;
	private boolean end;

	public TSTNode() {
	}

	public TSTNode(Character character) {
		this.character = character;
	}

	/**
	 * @return the left
	 */
	public TSTNode getLeft() {
		return left;
	}

	/**
	 * @param left the left to set
	 */
	public void setLeft(TSTNode left) {
		this.left = left;
	}

	/**
	 * @return the middle
	 */
	public TSTNode getMiddle() {
		return middle;
	}

	/**
	 * @param middle the middle to set
	 */
	public void setMiddle(TSTNode middle) {
		this.middle = middle;
	}

	/**
	 * @return the right
	 */
	public TSTNode getRight() {
		return right;
	}

	/**
	 * @param right the right to set
	 */
	public void setRight(TSTNode right) {
		this.right = right;
	}

	/**
	 * @return the character
	 */
	public Character getCharacter() {
		return character;
	}

	/**
	 * @param character the character to set
	 */
	public void setCharacter(Character character) {
		this.character = character;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return character + "," + (end ? 1 : 0);
	}

	/**
	 * @return the end
	 */
	public boolean isEnd() {
		return end;
	}

	/**
	 * @param end the end to set
	 */
	public void setEnd(boolean end) {
		this.end = end;
	}

}

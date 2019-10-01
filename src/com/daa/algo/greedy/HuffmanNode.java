package com.daa.algo.greedy;

public class HuffmanNode {

	private char ch;
	private int count;
	private HuffmanNode left;
	private HuffmanNode right;

	public HuffmanNode() {
		super();
	}

	public HuffmanNode(char ch, int count) {
		super();
		this.ch = ch;
		this.count = count;
	}

	/**
	 * @return the ch
	 */
	public char getCh() {
		return ch;
	}

	/**
	 * @param ch the ch to set
	 */
	public void setCh(char ch) {
		this.ch = ch;
	}

	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @param count the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * @return the left
	 */
	public HuffmanNode getLeft() {
		return left;
	}

	/**
	 * @param left the left to set
	 */
	public void setLeft(HuffmanNode left) {
		this.left = left;
	}

	/**
	 * @return the right
	 */
	public HuffmanNode getRight() {
		return right;
	}

	/**
	 * @param right the right to set
	 */
	public void setRight(HuffmanNode right) {
		this.right = right;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "[" + ch + "," + count + "]";
	}

}

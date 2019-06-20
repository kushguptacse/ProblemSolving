package com.daa.tree;

public class TreeNodeModified {

	private int data;
	private TreeNodeModified left;
	private TreeNodeModified right;
	private TreeNodeModified nextSibling;

	public TreeNodeModified(int data) {
		super();
		this.data = data;
	}

	/**
	 * @return the data
	 */
	public int getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(int data) {
		this.data = data;
	}

	/**
	 * @return the left
	 */
	public TreeNodeModified getLeft() {
		return left;
	}

	/**
	 * @param left the left to set
	 */
	public void setLeft(TreeNodeModified left) {
		this.left = left;
	}

	/**
	 * @return the right
	 */
	public TreeNodeModified getRight() {
		return right;
	}

	/**
	 * @param right the right to set
	 */
	public void setRight(TreeNodeModified right) {
		this.right = right;
	}

	/**
	 * @return the nextSibling
	 */
	public TreeNodeModified getNextSibling() {
		return nextSibling;
	}

	/**
	 * @param nextSibling the nextSibling to set
	 */
	public void setNextSibling(TreeNodeModified nextSibling) {
		this.nextSibling = nextSibling;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return data + "";
	}

}

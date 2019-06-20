package com.daa.tree;

public class TreeNode<T> {

	public TreeNode() {
		super();
	}

	private T data;
	private TreeNode<T> left;
	private TreeNode<T> right;

	public TreeNode(T data, TreeNode<T> left, TreeNode<T> right) {
		super();
		this.data = data;
		this.left = left;
		this.right = right;
	}

	public TreeNode(T data) {
		super();
		this.data = data;
	}

	/**
	 * @return the data
	 */
	public T getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(T data) {
		this.data = data;
	}

	/**
	 * @return the left
	 */
	public TreeNode<T> getLeft() {
		return left;
	}

	/**
	 * @param left the left to set
	 */
	public void setLeft(TreeNode<T> left) {
		this.left = left;
	}

	/**
	 * @return the right
	 */
	public TreeNode<T> getRight() {
		return right;
	}

	/**
	 * @param right the right to set
	 */
	public void setRight(TreeNode<T> right) {
		this.right = right;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TreeNode [data=" + data + "]";
	}

}

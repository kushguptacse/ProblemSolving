package com.daa.tree;

public class AvlTreeNode<T> {

	public AvlTreeNode() {
		super();
	}

	private T data;
	private AvlTreeNode<T> left;
	private AvlTreeNode<T> right;
	private int height;

	public AvlTreeNode(T data, AvlTreeNode<T> left, AvlTreeNode<T> right, int height) {
		super();
		this.data = data;
		this.left = left;
		this.right = right;
		this.height = height;
	}

	public AvlTreeNode(T data) {
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
	public AvlTreeNode<T> getLeft() {
		return left;
	}

	/**
	 * @param left the left to set
	 */
	public void setLeft(AvlTreeNode<T> left) {
		this.left = left;
	}

	/**
	 * @return the right
	 */
	public AvlTreeNode<T> getRight() {
		return right;
	}

	/**
	 * @param right the right to set
	 */
	public void setRight(AvlTreeNode<T> right) {
		this.right = right;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "[data=" + data + ",height=" + height + "]";
	}

}

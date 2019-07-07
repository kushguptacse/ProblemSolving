package com.daa.tree;

public class SplayTreeNode<T extends Comparable<T>> {

	public SplayTreeNode() {
		super();
	}

	private T data;
	private SplayTreeNode<T> left;
	private SplayTreeNode<T> right;
	private SplayTreeNode<T> parent;

	public SplayTreeNode(T data, SplayTreeNode<T> left, SplayTreeNode<T> right,SplayTreeNode<T> parent) {
		super();
		this.data = data;
		this.left = left;
		this.right = right;
		this.parent = parent;
	}

	public SplayTreeNode(T data) {
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
	public SplayTreeNode<T> getLeft() {
		return left;
	}

	/**
	 * @param left the left to set
	 */
	public void setLeft(SplayTreeNode<T> left) {
		this.left = left;
	}

	/**
	 * @return the right
	 */
	public SplayTreeNode<T> getRight() {
		return right;
	}

	/**
	 * @param right the right to set
	 */
	public void setRight(SplayTreeNode<T> right) {
		this.right = right;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "[data=" + data + "]";
	}

	/**
	 * @return the parent
	 */
	public SplayTreeNode<T> getParent() {
		return parent;
	}

	/**
	 * @param parent the parent to set
	 */
	public void setParent(SplayTreeNode<T> parent) {
		this.parent = parent;
	}

}

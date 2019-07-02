package com.daa.tree;

public class RBTreeNode<T extends Comparable<T>> {

	private T data;

	private boolean red=true;

	private RBTreeNode<T> left;

	private RBTreeNode<T> right;

	private RBTreeNode<T> parent;

	/**
	 * @param data
	 */
	public RBTreeNode(T data) {
		super();
		this.data = data;
	}

	public RBTreeNode() {
		super();
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
	 * @return the black
	 */
	public boolean isRed() {
		return red;
	}

	/**
	 * @param red the black to set
	 */
	public void setRed(boolean red) {
		this.red = red;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String color = red ? "R" : "B";
		return "[" + data + "," + color + "]";
	}

	/**
	 * @return the left
	 */
	public RBTreeNode<T> getLeft() {
		return left;
	}

	/**
	 * @param left the left to set
	 */
	public void setLeft(RBTreeNode<T> left) {
		this.left = left;
	}

	/**
	 * @return the right
	 */
	public RBTreeNode<T> getRight() {
		return right;
	}

	/**
	 * @param right the right to set
	 */
	public void setRight(RBTreeNode<T> right) {
		this.right = right;
	}

	/**
	 * @return the parent
	 */
	public RBTreeNode<T> getParent() {
		return parent;
	}

	/**
	 * @param parent the parent to set
	 */
	public void setParent(RBTreeNode<T> parent) {
		this.parent = parent;
	}

}

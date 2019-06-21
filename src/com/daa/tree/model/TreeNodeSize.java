package com.daa.tree.model;

public class TreeNodeSize<T> {


	public TreeNodeSize() {
		super();
	}

	private T data;
	private TreeNodeSize<T> left;
	private TreeNodeSize<T> right;
	private int size;

	public TreeNodeSize(T data, TreeNodeSize<T> left, TreeNodeSize<T> right,int size) {
		super();
		this.data = data;
		this.left = left;
		this.right = right;
		this.size = size;
	}

	public TreeNodeSize(T data) {
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
	public TreeNodeSize<T> getLeft() {
		return left;
	}

	/**
	 * @param left the left to set
	 */
	public void setLeft(TreeNodeSize<T> left) {
		this.left = left;
	}

	/**
	 * @return the right
	 */
	public TreeNodeSize<T> getRight() {
		return right;
	}

	/**
	 * @param right the right to set
	 */
	public void setRight(TreeNodeSize<T> right) {
		this.right = right;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TreeNodeSize [data=" + data + "]";
	}

	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(int size) {
		this.size = size;
	}


}

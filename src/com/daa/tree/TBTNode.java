package com.daa.tree;

public class TBTNode<T> {

	private TBTNode<T> left;
	private TBTNode<T> right;
	private T data;
	private int lTag = 0;
	private int rTag = 0;


	/**
	 * @return the left
	 */
	public TBTNode<T> getLeft() {
		return left;
	}

	/**
	 * @param left the left to set
	 */
	public void setLeft(TBTNode<T> left) {
		this.left = left;
	}

	/**
	 * @return the right
	 */
	public TBTNode<T> getRight() {
		return right;
	}

	/**
	 * @param right the right to set
	 */
	public void setRight(TBTNode<T> right) {
		this.right = right;
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
	 * @return the lTag
	 */
	public int getlTag() {
		return lTag;
	}

	/**
	 * @param lTag the lTag to set
	 */
	public void setlTag(int lTag) {
		this.lTag = lTag;
	}

	/**
	 * @return the rTag
	 */
	public int getrTag() {
		return rTag;
	}

	/**
	 * @param rTag the rTag to set
	 */
	public void setrTag(int rTag) {
		this.rTag = rTag;
	}

	/**
	 * @param data
	 */
	public TBTNode(T data) {
		super();
		this.data = data;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TBTNode [data=" + data + "]";
	}

	/**
	 * @param left
	 * @param right
	 * @param data
	 * @param lTag
	 * @param rTag
	 */
	public TBTNode(TBTNode<T> left, TBTNode<T> right, T data, int lTag, int rTag) {
		super();
		this.left = left;
		this.right = right;
		this.data = data;
		this.lTag = lTag;
		this.rTag = rTag;
	}

}

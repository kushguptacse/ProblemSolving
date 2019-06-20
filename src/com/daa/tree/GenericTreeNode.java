package com.daa.tree;

public class GenericTreeNode<T> {

	public GenericTreeNode(T data) {
		super();
		this.data = data;
	}

	public GenericTreeNode() {
		super();
	}

	private T data;
	private GenericTreeNode<T> sibling;
	private GenericTreeNode<T> child;

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
	 * @return the sibling
	 */
	public GenericTreeNode<T> getSibling() {
		return sibling;
	}

	/**
	 * @param sibling the sibling to set
	 */
	public void setSibling(GenericTreeNode<T> sibling) {
		this.sibling = sibling;
	}

	/**
	 * @return the child
	 */
	public GenericTreeNode<T> getChild() {
		return child;
	}

	/**
	 * @param child the child to set
	 */
	public void setChild(GenericTreeNode<T> child) {
		this.child = child;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GenericTreeNode [data=" + data + "]";
	}

}

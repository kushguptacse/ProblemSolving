package com.daa.tree;

import java.util.ArrayList;
import java.util.List;

public class GenericTreeNodeSimple<T> {
	private T data;
	private final List<GenericTreeNodeSimple<T>> childs = new ArrayList<>();
	
	public GenericTreeNodeSimple(T data) {
		super();
		this.data = data;
	}

	public GenericTreeNodeSimple() {
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GenericTreeNode [data=" + data + "]";
	}

	/**
	 * @return the childs
	 */
	public List<GenericTreeNodeSimple<T>> getChilds() {
		return childs;
	}

}

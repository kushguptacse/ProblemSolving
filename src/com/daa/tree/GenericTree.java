package com.daa.tree;

public class GenericTree<T extends Comparable<T>> {

	private GenericTreeNode<T> root;
	
	
	/**
	 * @return the root
	 */
	public GenericTreeNode<T> getRoot() {
		return root;
	}

}

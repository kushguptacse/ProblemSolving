package com.daa.tree;

/**
 * in a tree with n nodes number of edges required is n-1 
 * @author G521885
 *
 * @param <T>
 */
public interface Tree<T> {
	/**
	 * postOrder (Left, Right, Root )
	 * 
	 * 	 1 
	 * 	/  \ 
	 * 	2   3 
	 * / \ 
	 *4   5	 
	 * 
	 * output - 45231
	 * 
	 * @return result
	 */
	String postOrderTraversal();

	/**
	 * In order (Left, Root, Right)
	 * 
	 * 	 1 
	 * 	/  \ 
	 * 	2   3 
	 * / \ 
	 *4   5	 
	 * 
	 * output - 42513
	 * 
	 * @return result
	 */
	String inOrderTraversal();

	/**
	 * postOrder (Left, Right, Root )
	 * 
	 * 	 1 
	 * 	/  \ 
	 * 	2   3 
	 * / \ 
	 *4   5	 
	 * 
	 * output - 45231
	 * 
	 * @return result
	 */
	String preOrderTraversal();

	/**
	 * 
	 * @return size of tree
	 */
	int size();

	/**
	 * insert element at tree
	 * @param data
	 */
	void insert(T data);
	
	/**
	 * search element in binary tree
	 * @param data
	 * @return true if found
	 */
	boolean findElement(T data);

}

package com.daa.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.daa.algo.Recurrsion;
import com.daa.math.MathUtil;

/**
 * Binary tree store the data in tree. every node can have at most 2 child. No left child
 * or right child rule. in BST left child value is less than right child i.e. l<r<n.
 * 
 * @author G521885
 *
 */
public class BinaryTree<T> implements Tree<T> {

	private int size;
	private TreeNode<T> root;

	/**
	 * Insert node at left if present else try right. than repeat the process log(n)
	 * 
	 * @param node
	 * @param newNode
	 */
	@Override
	public void insert(T data) {
		TreeNode<T> node = new TreeNode<>(data, null, null);
		if (root == null) {
			root = node;
		} else {
			insertNode(root, node);
		}
		size++;
	}

	/**
	 * 
	 * @return size of binary tree
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * O(N),O(N)
	 * 
	 * @return height or Level of Binary tree
	 */
	public int getHeight() {
		return calculateHeight(root);
	}

	private int calculateHeight(TreeNode<T> node) {
		if (node == null) {
			return 0;
		}
		int l = calculateHeight(node.getLeft());
		int r = calculateHeight(node.getRight());
		return MathUtil.max(l, r) + 1;

	}

	/**
	 * 
	 * 2nCn/(n+1)
	 * 
	 * @return number of possible unlabeled trees for given n node
	 */
	public int numberOfUnLabeledTreePosible(int n) {
		Recurrsion r = new Recurrsion();
		int num = r.fact(2 * n);
		int den = r.fact(n);
		int res = den * den * (n + 1);
		return num / res;
	}

	/**
	 * O(N), space - O(N), As if tree is a single linked list than all the nodes needed to be
	 * in stack at time
	 * 
	 * @return number of leaf in binary tree
	 */
	public int numberOfLeaf() {
		return numberOfLeaf(root);
	}

	public int numberOfFullNodes() {
		return numberOfFullNodes(root);
	}

	public int numberOfFullNodes(TreeNode<T> head) {
		if (head == null) {
			return 0;
		}
		int c = 0;
		if (head.getLeft() != null && head.getRight() != null) {
			c=1;
		}
		return c + numberOfFullNodes(head.getLeft()) + numberOfFullNodes(head.getRight());
	}

	private int numberOfLeaf(TreeNode<T> node) {
		if (node == null) {
			return 0;
		}
		if (node.getLeft() == null && node.getRight() == null) {
//			System.out.print(" "+node.getData());
			return 1;
		}
		return numberOfLeaf(node.getLeft()) + numberOfLeaf(node.getRight());
	}

	/**
	 * O(N), space - O(N), As if tree is a single linked list than all the nodes needed to be
	 * in stack at time
	 * 
	 * @return number of non leaf node
	 */
	public int numberOfNonLeaf() {
		return numberOfNonLeaf(root);
	}

	private int numberOfNonLeaf(TreeNode<T> node) {
		if (node == null) {
			return 0;
		}
		if (node.getLeft() == null && node.getRight() == null) {
			return 0;
		}
		return 1 + numberOfNonLeaf(node.getLeft()) + numberOfNonLeaf(node.getRight());
	}

	/**
	 * 
	 * n!*(2nCn/(n+1))
	 * 
	 * similarly number of possible tree with given pre-order are - (2nCn/(n+1))
	 * 
	 * as it take n! ways to arrange n label.
	 * 
	 * @return number of possible labeled binary trees for given n node
	 */
	public int numberOfLabeledTreePosible(int n) {
		Recurrsion r = new Recurrsion();
		int num = r.fact(2 * n);
		int den = r.fact(n);
		int res = den * (n + 1);
		return num / res;
	}

	/**
	 * In order (Left, Root, Right)
	 *  @f:off
	 *   1 
	 * /   \ 
	 * 2   3 
	 * / \ 
	 * 4 5
	 * 
	 * @f:on

	 * output -42513
	 * 
	 * @return result
	 */
	@Override
	public String inOrderTraversal() {
		StringBuilder sb = new StringBuilder();
		inOrder(root, sb);
		return sb.toString();
	}

	/**
	 * PreOrder (Root, Left, Right)
	 *  @f:off
	 *   1 
	 * /   \ 
	 * 2   3 
	 * / \ 
	 * 4 5
	 * 
	 * @f:on
	 * output - 12453
	 * 
	 * @return result
	 * 
	 */
	@Override
	public String preOrderTraversal() {
		StringBuilder sb = new StringBuilder();
		preOrder(root, sb);
		return sb.toString();
	}

	/**
	 * @f:off
	 * postOrder (Left, Right, Root )
	 * 
	 *   1 
	 * /   \ 
	 * 2   3 
	 * / \ 
	 * 4 5
	 * 
	 * output - 45231
	 * 
	 * @return result
	 * @f:on
	 */
	@Override
	public String postOrderTraversal() {
		StringBuilder sb = new StringBuilder();
		 postOrder(root, sb);
		return sb.toString();
	}

	private void inOrder(TreeNode<T> node, StringBuilder sb) {
		if (node == null) {
			return;
		}
		inOrder(node.getLeft(), sb);
		sb.append(node.getData()).append(" ");
		inOrder(node.getRight(), sb);
	}

	private void preOrder(TreeNode<T> node, StringBuilder sb) {
		if (node == null) {
			return;
		}
		sb.append(node.getData()).append(" ");
		preOrder(node.getLeft(), sb);
		preOrder(node.getRight(), sb);
	}

	private void postOrder(TreeNode<T> node, StringBuilder sb) {
		if (node == null) {
			return;
		}
		postOrder(node.getLeft(), sb);
		postOrder(node.getRight(), sb);
		sb.append(node.getData()).append(" ");
	}

	/**
	 * Insert node at left if space exist else try right. than repeat the process
	 * 
	 * @param node
	 * @param newNode
	 */
	private void insertNode(TreeNode<T> node, TreeNode<T> newNode) {
		Queue<TreeNode<T>> queue = new LinkedList<>();
		queue.add(node);
		while (!queue.isEmpty()) {
			TreeNode<T> node1 = queue.poll();
			if (node1.getLeft() != null) {
				queue.add(node1.getLeft());
			} else {
				node1.setLeft(newNode);
				break;
			}
			if (node1.getRight() != null) {
				queue.add(node1.getRight());
			} else {
				node1.setRight(newNode);
				break;
			}
		}
	}

	/**
	 * It is equivalent of size method O(N)
	 * 
	 * @return total number of nodes
	 */
	public int numberOfNodes() {
		return totalNodes(root);
	}

	private int totalNodes(TreeNode<T> node) {
		if (node != null) {
			int left = totalNodes(node.getLeft());
			int right = totalNodes(node.getRight());
			return 1 + left + right;
		}

		return 0;
	}

	/**
	 * Find the element from the binary tree. it can take o(n) time
	 */
	@Override
	public boolean findElement(T data) {
		if (root == null) {
			return false;
		}
		return search(root, data) != null;
	}

	private TreeNode<T> search(TreeNode<T> node, T data) {
		if (node == null) {
			return null;
		}

		if (data.equals(node.getData())) {
			return node;
		}
		TreeNode<T> curr = search(node.getLeft(), data);
		if (curr != null && data.equals(curr.getData())) {
			return curr;
		}
		curr = search(node.getRight(), data);
		if (curr != null && data.equals(curr.getData())) {
			return curr;
		}

		return null;
	}

	/**
	 * @return the root
	 */
	public TreeNode<T> getRoot() {
		return root;
	}

	/**
	 * Convert Binary Tree to Binary Search Tree with same structure.
	 * 
	 * Algorithm - 1. Calculate in-Order and add element to list in tree. 2. Sort the list. 3.
	 * Now calculate In-Order. update data element of binary tree from sorted list while
	 * calculating In-Order
	 * 
	 * O(NlogN)
	 * 
	 * @param node
	 * 
	 * @return treeNode
	 */
	public <K extends Comparable<K>> TreeNode<K> binaryTreeToBST(TreeNode<K> node) {
		List<K> list = new ArrayList<>();
		if (node == null) {
			return null;
		}
		inOrderAddList(node, list);
		Collections.sort(list);
		parseInOrder(node, list);
		return node;
	}

	private <K extends Comparable<K>> void inOrderAddList(TreeNode<K> node, List<K> list) {
		if (node == null) {
			return;
		}
		inOrderAddList(node.getLeft(), list);
		list.add(node.getData());
		inOrderAddList(node.getRight(), list);
	}

	private <K extends Comparable<K>> void parseInOrder(TreeNode<K> node, List<K> list) {
		if (node == null) {
			return;
		}
		parseInOrder(node.getLeft(), list);
		node.setData(list.remove(0));
		parseInOrder(node.getRight(), list);
	}

	/**
	 * swap the leaf nodes in pairs. starting from left to right.
	 * 
	 */
	public void swapLeafNodes() {
		List<TreeNode<T>> list = new ArrayList<>();
		swapLeafNodes(root, list);
		for (int i = 0; i < list.size() - 1; i = i + 2) {
			T data = list.get(i).getData();
			list.get(i).setData(list.get(i + 1).getData());
			list.get(i + 1).setData(data);
		}
	}

	private void swapLeafNodes(TreeNode<T> node, List<TreeNode<T>> list) {
		if (node == null) {
			return;
		}

		if (node.getLeft() == null && node.getRight() == null) {
			list.add(node);
		}

		if (node.getLeft() != null) {
			swapLeafNodes(node.getLeft(), list);
		}

		if (node.getRight() != null) {
			swapLeafNodes(node.getRight(), list);
		}
	}

}

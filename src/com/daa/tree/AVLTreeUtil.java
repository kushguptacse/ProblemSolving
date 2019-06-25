package com.daa.tree;

import java.util.Scanner;

import com.daa.math.MathUtil;

/**
 * 
 * @author G521885
 *
 */
public final class AVLTreeUtil {

	private AVLTreeUtil() {

	}

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			System.out.println("Enter the element you want to insert in avl tree");
			int data = sc.nextInt();
			AvlTreeNode<Integer> root = null;
			while (data != -1) {
				System.out.println("-------------------");
				System.out.println("Avl tree after inserting element : " + data);
				root = insert(root, data);
				System.out.println("Pre Order of tree - " + preOrderTraversal(root));
				System.out.println("In order traversal : " + inOrderTraversalModified(root));
				System.out.println("-------------------");
				System.out.println("Enter the element you want to insert in avl tree");
				data = sc.nextInt();
			}
			System.out.println("Bye");
		}
	}

	public static AvlTreeNode<Integer> insert(AvlTreeNode<Integer> root, int data) {
		// binary search insert
		if (root == null) {
			return new AvlTreeNode<>(data);
		}
		
		if (root.getData() > data) {
			root.setLeft(insert(root.getLeft(), data));
		} else {
			root.setRight(insert(root.getRight(), data));
		}

		// set height
		updateHeight(root);
		
		// calculate balance factor
		int balance = getBalance(root);
		
		// left left case
		if (balance > 1 && root.getLeft().getData() > data) {
			return rightRotate(root);
		}
		// right right case
		if (balance < -1 && root.getRight().getData() < data) {
			return leftRotate(root);
		}
		// left right case
		if (balance > 1 && root.getLeft().getData() < data) {
			root.setLeft(leftRotate(root.getLeft()));
			return rightRotate(root);
		}
		//right left case
		if (balance < -1 && root.getRight().getData() > data) {
			root.setRight(rightRotate(root.getRight()));
			return leftRotate(root);
		}
		return root;
	}

	/**
	 * Balanced the tree in Right Right case -
	 * 
	 * @f:off
	 * 1. take the right child of root to temp.
	 * 2.set root right as temp.left
	 * 3.set temp left as root.
	 * 4.update height of entire tree from temp node passed
	 * @f:on
	 * 
	 * @param root
	 * @return
	 */
	public static AvlTreeNode<Integer> leftRotate(AvlTreeNode<Integer> root) {
		if (root == null) {
			return null;
		}
		AvlTreeNode<Integer> temp = root.getRight();
		root.setRight(temp.getLeft());
		temp.setLeft(root);
		updateHeight(root);
		updateHeight(temp);
		return temp;
	}

	/**
	 * Balanced the tree in Left Left case -
	 * 
	 * @f:off
	 * 1. take the left child of root to temp.
	 * 2.set root left as temp.right
	 * 3.set temp right as root.
	 * 4.update height of entire tree from temp node passed
	 * @f:on
	 * 
	 * @param root
	 * @return height balanced node
	 */
	public static AvlTreeNode<Integer> rightRotate(AvlTreeNode<Integer> root) {
		if (root == null) {
			return null;
		}
		AvlTreeNode<Integer> temp = root.getLeft();
		root.setLeft(temp.getRight());
		temp.setRight(root);
		updateHeight(root);
		updateHeight(temp);
		return temp;
	}
	/**
	 * get height of the given node. return 0 if node is null
	 * 
	 * @param root
	 * @return height of the node passed
	 */
	public static int getHeight(AvlTreeNode<Integer> root) {
		if (root == null) {
			return -1;
		}
		return root.getHeight();
	}
	
	private static int getBalance(AvlTreeNode<Integer> root) {
		if (root == null)
			return 0;
		return getHeight(root.getLeft()) - getHeight(root.getRight());
	}

	private static void updateHeight(AvlTreeNode<Integer> root) {
		root.setHeight(MathUtil.max(getHeight(root.getLeft()), getHeight(root.getRight())) + 1);
	}



	public static String inOrderTraversal(AvlTreeNode<Integer> root) {
		StringBuilder sb = new StringBuilder();
		inOrder(root, sb);
		return sb.toString();
	}

	public static String inOrderTraversalModified(AvlTreeNode<Integer> root) {
		StringBuilder sb = new StringBuilder();
		inOrderTraversalModified(root, sb);
		return sb.toString();
	}

	private static void inOrderTraversalModified(AvlTreeNode<Integer> node, StringBuilder sb) {
		if (node == null) {
			return;
		}
		inOrderTraversalModified(node.getLeft(), sb);
		sb.append(node).append("->");
		inOrderTraversalModified(node.getRight(), sb);
	}

	private static void inOrder(AvlTreeNode<Integer> node, StringBuilder sb) {
		if (node == null) {
			return;
		}
		inOrder(node.getLeft(), sb);
		sb.append(node.getData()).append(" ");
		inOrder(node.getRight(), sb);
	}

	public static String preOrderTraversal(AvlTreeNode<Integer> root) {
		StringBuilder sb = new StringBuilder();
		preOrder(root, sb);
		return sb.toString();
	}

	private static void preOrder(AvlTreeNode<Integer> node, StringBuilder sb) {
		if (node == null) {
			return;
		}
		sb.append(node.getData()).append(" ");
		preOrder(node.getLeft(), sb);
		preOrder(node.getRight(), sb);
	}
}
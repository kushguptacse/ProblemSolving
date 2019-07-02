package com.daa.tree;

import java.util.Scanner;

public class RedBlackTreeUtil {

	private static RBTreeNode<Integer> root;

	public static void main(String[] args) {
		RBTreeNode<Integer> root = null;
		try (Scanner sc = new Scanner(System.in)) {
			System.out.println("Enter the element you want to insert in Red Black tree");
			int data = sc.nextInt();
			while (data != -1) {
				System.out.println("---------------------");
				System.out.println("Red Black Tree after inserting element : " + data);
				root = insert(data);
				System.out.println("Pre Order of tree - " + preOrderTraversal(root));
				System.out.println("In order traversal : " + inOrderTraversalModified(root));
				System.out.println("-------------------------");
				System.out.println("Enter the element you want to insert in Red Black tree");
				data = sc.nextInt();
			}
			System.out.println("--------------***-------------------------");
		}
	}

	private static RBTreeNode<Integer> insert(int data) {
		RBTreeNode<Integer> node = new RBTreeNode<>(data);
		root = insertBST(root, node);
		return fixViolation(node);
	}

	private static RBTreeNode<Integer> fixViolation(RBTreeNode<Integer> node) {

		RBTreeNode<Integer> parent = null;
		RBTreeNode<Integer> grandParent = null;
		while (root != node && node.isRed() && node.getParent().isRed()) {
			parent = node.getParent();
			grandParent = parent.getParent();
			if (parent == grandParent.getLeft()) {
				RBTreeNode<Integer> uncle = grandParent.getRight();
				if (uncle != null && uncle.isRed()) {
					// re-coloring
					uncle.setRed(false);
					parent.setRed(false);
					grandParent.setRed(true);
					node = grandParent;
				} else {
					if (parent.getRight() == node) {
						leftRotate(parent);
						node = parent;
						parent = node.getParent();
					}
					rightRotate(grandParent);
					boolean temp = parent.isRed();
					parent.setRed(grandParent.isRed());
					grandParent.setRed(temp);
					node = parent;

				}
			} else {
				RBTreeNode<Integer> uncle = grandParent.getLeft();
				if (uncle != null && uncle.isRed()) {
					// re-coloring
					uncle.setRed(false);
					parent.setRed(false);
					grandParent.setRed(true);
					node = grandParent;
				} else {
					if (parent.getLeft() == node) {
						rightRotate(parent);
						node = parent;
						parent = node.getParent();
					}
					leftRotate(grandParent);
					boolean temp = parent.isRed();
					parent.setRed(grandParent.isRed());
					grandParent.setRed(temp);
					node = parent;
				}
			}
		}

		root.setRed(false);
		return root;
	}

	/**
	 * Rotate node left.
	 * 
	 * @param node
	 */
	private static void leftRotate(RBTreeNode<Integer> node) {
		if (node == null) {
			return;
		}
		RBTreeNode<Integer> temp = node.getRight();
		node.setRight(temp.getLeft());
		temp.setLeft(node);
		if (node.getRight() != null) {
			node.getRight().setParent(node);
		}

		if (node.getParent() == null) {
			root = temp;
		} else if (node.getParent().getRight() == node) {
			node.getParent().setRight(temp);
		} else {
			node.getParent().setLeft(temp);
		}
		temp.setParent(node.getParent());
		node.setParent(temp);
	}

	/**
	 * rotate node right.
	 * 
	 * @param node
	 */
	private static void rightRotate(RBTreeNode<Integer> node) {
		if (node == null) {
			return;
		}
		RBTreeNode<Integer> temp = node.getLeft();
		node.setLeft(temp.getRight());
		temp.setRight(node);
		if (node.getLeft() != null) {
			node.getLeft().setParent(node);
		}
		if (node.getParent() == null) {
			root = temp;
		} else if (node.getParent().getLeft() == node) {
			node.getParent().setLeft(temp);
		} else {
			node.getParent().setRight(temp);
		}
		temp.setParent(node.getParent());
		node.setParent(temp);
	}

	/**
	 * Insert element in binary search tree fashion. also update parent variable of inserted
	 * node
	 * 
	 * @param root
	 * @param data
	 * @return root node
	 */
	private static RBTreeNode<Integer> insertBST(RBTreeNode<Integer> root, RBTreeNode<Integer> node) {
		if (root == null) {
			return node;
		}
		if (root.getData() > node.getData()) {
			root.setLeft(insertBST(root.getLeft(), node));
			root.getLeft().setParent(root);
		} else {
			root.setRight(insertBST(root.getRight(), node));
			root.getRight().setParent(root);
		}
		return root;
	}

	public static String inOrderTraversal(RBTreeNode<Integer> root) {
		StringBuilder sb = new StringBuilder();
		inOrder(root, sb);
		return sb.toString();
	}

	public static String inOrderTraversalModified(RBTreeNode<Integer> root) {
		StringBuilder sb = new StringBuilder();
		inOrderTraversalModified(root, sb);
		return sb.toString();
	}

	private static void inOrderTraversalModified(RBTreeNode<Integer> node, StringBuilder sb) {
		if (node == null) {
			return;
		}
		inOrderTraversalModified(node.getLeft(), sb);
		sb.append(node).append("->");
		inOrderTraversalModified(node.getRight(), sb);
	}

	private static void inOrder(RBTreeNode<Integer> node, StringBuilder sb) {
		if (node == null) {
			return;
		}
		inOrder(node.getLeft(), sb);
		sb.append(node.getData()).append(" ");
		inOrder(node.getRight(), sb);
	}

	public static String preOrderTraversal(RBTreeNode<Integer> root) {
		StringBuilder sb = new StringBuilder();
		preOrder(root, sb);
		return sb.toString();
	}

	private static void preOrder(RBTreeNode<Integer> node, StringBuilder sb) {
		if (node == null) {
			return;
		}
		sb.append(node.getData()).append(" ");
		preOrder(node.getLeft(), sb);
		preOrder(node.getRight(), sb);
	}
}

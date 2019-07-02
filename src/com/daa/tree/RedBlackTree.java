package com.daa.tree;

import java.util.Scanner;

public class RedBlackTree<T extends Comparable<T>> {

	private RBTreeNode<T> root;
	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			System.out.println("Enter the element you want to insert in Red Black tree");
			int data = sc.nextInt();
			RedBlackTree<Integer> rbt=new RedBlackTree<>();
			while (data != -1) {
				System.out.println("---------------------");
				System.out.println("Red Black Tree after inserting element : " + data);
				rbt.insert(data);
				rbt.preOrderTraversal();
				rbt.inOrderTraversal();
				System.out.println("-------------------------");
				System.out.println("Enter the element you want to insert in Red Black tree");
				data = sc.nextInt();
			}
			System.out.println("--------------***-------------------------");
		}
	}
	public void insert(T data) {
		RBTreeNode<T> temp = new RBTreeNode<>(data);
		root = insertBST(root, temp);
		fixViolation(temp);
	}

	private void fixViolation(RBTreeNode<T> node) {
		RBTreeNode<T> parent = null;
		RBTreeNode<T> grandParent = null;
		while (root != node && node.isRed() && node.getParent().isRed()) {
			parent = node.getParent();
			grandParent = parent.getParent();
			if (grandParent.getLeft() == parent) {
				RBTreeNode<T> uncle = grandParent.getRight();
				if (uncle != null && uncle.isRed()) {
					// change color
					parent.setRed(false);
					uncle.setRed(false);
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
				RBTreeNode<T> uncle = grandParent.getLeft();
				if (uncle != null && uncle.isRed()) {
					// change color
					parent.setRed(false);
					uncle.setRed(false);
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
	}

	private void leftRotate(RBTreeNode<T> node) {
		if (node == null) {
			return;
		}
		RBTreeNode<T> temp = node.getRight();
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

	private void rightRotate(RBTreeNode<T> node) {
		if (node == null) {
			return;
		}
		RBTreeNode<T> temp = node.getLeft();
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

	private RBTreeNode<T> insertBST(RBTreeNode<T> root, RBTreeNode<T> node) {
		if (root == null) {
			return node;
		}
		if (root.getData().compareTo(node.getData()) > 0) {
			root.setLeft(insertBST(root.getLeft(), node));
			root.getLeft().setParent(root);
		} else {
			root.setRight(insertBST(root.getRight(), node));
			root.getRight().setParent(root);
		}
		return root;
	}

	public void inOrderTraversal() {
		System.out.println("In order of red black tree : ");
		inOrderTraversal(root);
		System.out.println();
	}

	private void inOrderTraversal(RBTreeNode<T> root) {
		if (root == null) {
			return;
		}
		inOrderTraversal(root.getLeft());
		System.out.print(root + "->");
		inOrderTraversal(root.getRight());
	}

	public void preOrderTraversal() {
		System.out.println("Pre order of red black tree : ");
		preOrderTraversal(root);
		System.out.println();
	}

	private void preOrderTraversal(RBTreeNode<T> root) {
		if (root == null) {
			return;
		}
		System.out.print(root.getData() + " ");
		preOrderTraversal(root.getLeft());
		preOrderTraversal(root.getRight());
	}

}

package com.daa.tree;

import java.util.Scanner;

public class SplayTree<T extends Comparable<T>> {

	private SplayTreeNode<T> root;
	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			System.out.println("Enter the element you want to insert");
			int data = sc.nextInt();
			SplayTree<Integer> rbt=new SplayTree<>();
			while (data != -1) {
				System.out.println("---------------------");
				System.out.println("Tree after inserting element : " + data);
				rbt.insert(data);
				rbt.preOrderTraversal();
				rbt.inOrderTraversal();
				System.out.println("-------------------------");
				System.out.println("Enter the element you want to insert");
				data = sc.nextInt();
			}
			System.out.println("--------------***-------------------------");
			System.out.println("Enter the element you want to find");
			data = sc.nextInt();
			System.out.println(rbt.find(data));
			rbt.preOrderTraversal();
			rbt.inOrderTraversal();
		}
	}
	public SplayTreeNode<T> insert(T data) {
		SplayTreeNode<T> node = new SplayTreeNode<>(data);
		root = insertBST(root, node);
		splay(node);
		return root;
	}

	private void splay(SplayTreeNode<T> node) {
		while (node != root) {
			// zig situation
			if (node.getParent().getParent() == null) {
				// left case
				if (node.getParent().getLeft() == node) {
					rightRotate(node.getParent());
				} else {
					// right case
					leftRotate(node.getParent());
				}
				// zig-zig case left left
			} else if (node.getParent() == node.getParent().getParent().getLeft() && node == node.getParent().getLeft()) {
				rightRotate(node.getParent().getParent());
				rightRotate(node.getParent());
			} // zig-zig case Right Right
			else if (node.getParent() == node.getParent().getParent().getRight() && node == node.getParent().getRight()) {
				leftRotate(node.getParent().getParent());
				leftRotate(node.getParent());
			} // zig-zag situation - left right case
			else if (node.getParent() == node.getParent().getParent().getLeft() && node == node.getParent().getRight()) {
				leftRotate(node.getParent());
				rightRotate(node.getParent());
			} else {// zig-zag situation - right left case
				rightRotate(node.getParent());
				leftRotate(node.getParent());
			}

		}
	}

	public void leftRotate(SplayTreeNode<T> node) {
		if (node == null) {
			return;
		}
		SplayTreeNode<T> temp = node.getRight();
		node.setRight(temp.getLeft());
		temp.setLeft(node);
		if (node.getRight() != null) {
			node.getRight().setParent(node);
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

	public void rightRotate(SplayTreeNode<T> node) {
		if (node == null) {
			return;
		}
		SplayTreeNode<T> temp = node.getLeft();
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

	public SplayTreeNode<T> find(T data) {
		SplayTreeNode<T> node = findBST(root, data);
		if (node != null) {
			splay(node);
		}
		return node;
	}

	private SplayTreeNode<T> findBST(SplayTreeNode<T> root, T data) {
		if (root == null || data.equals(root.getData())) {
			return root;
		}
		if (data.compareTo(root.getData()) < 0) {
			return findBST(root.getLeft(), data);
		} else {
			return findBST(root.getRight(), data);
		}
	}

	public SplayTreeNode<T> insertBST(SplayTreeNode<T> root, SplayTreeNode<T> node) {
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
		System.out.println("In order of tree : ");
		inOrderTraversal(root);
		System.out.println();
	}

	private void inOrderTraversal(SplayTreeNode<T> root) {
		if (root == null) {
			return;
		}
		inOrderTraversal(root.getLeft());
		System.out.print(root + "->");
		inOrderTraversal(root.getRight());
	}

	public void preOrderTraversal() {
		System.out.println("Pre order of tree : ");
		preOrderTraversal(root);
		System.out.println();
	}

	private void preOrderTraversal(SplayTreeNode<T> root) {
		if (root == null) {
			return;
		}
		System.out.print(root.getData() + " ");
		preOrderTraversal(root.getLeft());
		preOrderTraversal(root.getRight());
	}

}

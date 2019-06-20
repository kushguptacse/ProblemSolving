package com.daa.tree;

/**
 * change the key provided with given key. the resultant tree should also be a binary search tree
 * 
 * @author G521885
 *
 */
public class ChangeKeys {

	public static void main(String[] args) {
		Node root = insertNewNode(null, 50);
		insertNewNode(root, 30);
		insertNewNode(root, 20);
		insertNewNode(root, 40);
		insertNewNode(root, 70);
		insertNewNode(root, 60);
		insertNewNode(root, 80);

		System.out.println("Inorder traversal of the given tree");
		inorder(root);
		changeKey(root, 40, 10);
		System.out.println("\nInorder traversal after changing key of given tree");
		inorder(root);
	}

	private static void inorder(Node root) {
		if (root == null) {
			return;
		}
		inorder(root.left);
		System.out.print(root.data + " ");
		inorder(root.right);
	}

	private static Node insertNewNode(Node root, int new_key) {
		Node node = new Node(new_key);
		if (root == null) {
			root = node;
			return root;
		}
		insertNewNode(root, node);
		return root;
	}

	private static Node changeKey(Node root, int old_key, int new_key) {
		root = findAndDelete(root, old_key);
		insertNewNode(root, new_key);
		return root;
	}

	private static Node findAndDelete(Node node, int key) {
		if (node == null) {
			return node;
		}

		if (node.data < key) {
			node.right = findAndDelete(node.right, key);
		} else if (node.data > key) {
			node.left = findAndDelete(node.left, key);
		} else {
			if (node.left == null && node.right == null) {
				return null;
			} else if (node.left == null) {
				return node.right;
			} else if (node.right == null) {
				return node.left;
			} else {
				// both nodes has child
				Node max = findMax(node.left);
				node.data = max.data;
				node.left = findAndDelete(node.left, node.data);
			}
		}

		return node;

	}

	private static Node findMax(Node node) {
		if (node.right == null) {
			return node;
		}
		return findMax(node.right);
	}

	private static void insertNewNode(Node node, Node newNode) {
		if (node.data > newNode.data) {
			if (node.left == null) {
				node.left = newNode;
			} else {
				insertNewNode(node.left, newNode);
			}
		} else {
			if (node.right == null) {
				node.right = newNode;
			} else {
				insertNewNode(node.right, newNode);
			}
		}
	}

}
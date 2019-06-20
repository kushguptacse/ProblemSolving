package com.daa.tree;

import java.util.Arrays;

class Node {
	int data;
	Node left, right;

	Node(int item) {
		data = item;
		left = right = null;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Node [data=" + data + "]";
	}
}

public class CheckDeadEnd {// This method mainly calls insertRec()
	// A recursive function
	// to insert a new key in BST
	static Node insert(Node root, int data) {

		// If the tree is empty,
		// return a new node
		if (root == null) {
			root = new Node(data);
			return root;
		}

		/* Otherwise, recur down the tree */
		if (data < root.data)
			root.left = insert(root.left, data);
		else if (data > root.data)
			root.right = insert(root.right, data);

		/* return the (unchanged) node pointer */
		return root;
	}

	public static void main(String[] args) {
		Node root = new Node(60);
		int[] arr= {47 ,58 ,116, 114};
		Arrays.stream(arr).forEach(i->insert(root,i));
	    System.out.println(isDeadEnd(root));
	}

	public static boolean isDeadEnd(Node root) {
		if (root == null) {
			return false;
		}
		java.util.List<Integer> list = new java.util.ArrayList<>();
		java.util.List<Integer> leaf = new java.util.ArrayList<>();
		inOrder(root, list, leaf);
		return checkDeadEnd(list, leaf);

	}

	private static void inOrder(Node node, java.util.List<Integer> list, java.util.List<Integer> leaf) {
		if (node == null) {
			return;
		}
		list.add(node.data);
		if (node.left == null && node.right == null) {
			leaf.add(node.data);
		}
		inOrder(node.left, list, leaf);
		inOrder(node.right, list, leaf);
	}

	private static boolean checkDeadEnd(java.util.List<Integer> list, java.util.List<Integer> leaf) {
		if(leaf.contains(1)){
            return true;
        }
		for (Integer i : leaf) {
			if (list.contains(i + 1) && list.contains(i - 1)) {
				return true;
			}
		}
		return false;
	}

}
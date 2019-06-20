package com.daa.tree;

class RemoveNodeOutsideRange {
	// Removes all nodes having value
	// outside the given range and
	// returns the root of modified tree
	public static Node removeNodeOutsideRange(Node node, int min, int max) {
		if (node == null) {
			return node;
		}
		node.left = (removeNodeOutsideRange(node.left, min, max));
		node.right = (removeNodeOutsideRange(node.right, min, max));
		if (node.data < min) {
			return node.right;
		} else if (node.data > max) {
			return node.left;
		}
		return node;
	}

	private static String inOrder(Node node, StringBuilder sb) {
		if (node == null) {
			return "";
		}
		inOrder(node.left, sb);
		sb.append(node.data).append(" ");
		inOrder(node.right, sb);
		return sb.toString();
	}

	private static Node newNode(int num) {
		Node temp = new Node(num);
		temp.left = null;
		temp.right = null;
		return temp;
	}

	private static Node insert(Node root, int key) {
		if (root == null) {
			return newNode(key);
		}
		if (root.data > key) {
			root.left = insert(root.left, key);
		} else {
			root.right = insert(root.right, key);
		}
		return root;
	}

	/**
	 * In order (Left, Root, Right)
	 * 
	 * 1 / \ 2 3 / \ 4 5
	 * 
	 * output - 42513
	 * 
	 * @return result
	 */
	private static String inOrderTraversal(Node root) {
		return inOrder(root, new StringBuilder());
	}

	// Driver code
	public static void main(String[] args) {
		Node root = null;
		root = insert(root, 6);
		root = insert(root, -13);
		root = insert(root, 14);
		root = insert(root, -8);
		root = insert(root, 15);
		root = insert(root, 13);
		root = insert(root, 7);

		System.out.print("Inorder Traversal of " + "the given tree is: ");
		System.out.println(inOrderTraversal(root));

		root = removeNodeOutsideRange(root, -10, 13);

		System.out.print("\nInorder traversal of " + "the modified tree: ");
		System.out.println(inOrderTraversal(root));
	}

}

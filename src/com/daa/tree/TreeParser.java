package com.daa.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TreeParser {

	public static void main(String[] args) {
		TreeNode<Integer> root = TreeParser.deserialize("[1, 2, 3, 4, 5, null, 6]");
		System.out.println(BinaryTreeUtil.preOrderTraversal(root));
		System.out.println(BinaryTreeUtil.inOrderTraversal(root));

		TreeTraversalPractice.printRootToLeafPaths(root);
		System.out.println(BinaryTreeUtil
				.inOrderTraversal(BinarySearchTreeUtil.getBstFromPreOrder(new int[] { 8, 5, 1, 7, 10, 12 })));
		String s1 = TreeParser.serialize(root);
		System.out.println(s1);
		root = TreeParser.deserialize(s1);
		System.out.println(BinaryTreeUtil.preOrderTraversal(root));
		System.out.println(BinaryTreeUtil.inOrderTraversal(root));
	}

	public static String serialize(TreeNode<Integer> root) {
		List<Integer> list = new ArrayList<>();
		if (root == null) {
			return list.toString();
		}
		Queue<TreeNode<Integer>> queue = new LinkedList<>();
		queue.add(root);
		list.add(root.getData());
		while (!queue.isEmpty()) {
			TreeNode<Integer> node = queue.poll();
			if (node.getLeft() != null) {
				list.add(node.getLeft().getData());
				queue.add(node.getLeft());
			} else {
				list.add(null);
			}
			if (node.getRight() != null) {
				list.add(node.getRight().getData());
				queue.add(node.getRight());
			} else {
				list.add(null);
			}
		}
		return list.toString();
	}

	// Decodes your encoded data to tree.
	public static TreeNode<Integer> deserialize(String data) {
		String[] input = data.substring(1, data.length() - 1).split(", ");
		if (input[0].isEmpty()) {
			return null;
		}
		TreeNode<Integer> root = getNode(0, input);
		Queue<TreeNode<Integer>> queue = new LinkedList<>();
		queue.add(root);
		int i = 0;
		while (!queue.isEmpty()) {
			TreeNode<Integer> node = queue.poll();
			if (++i < input.length) {
				node.setLeft(getNode(i, input));
				if (node.getLeft() != null) {
					queue.add(node.getLeft());
				}
			}
			if (++i < input.length) {
				node.setRight(getNode(i, input));
				if (node.getRight() != null) {
					queue.add(node.getRight());
				}
			}
		}
		return root;
	}

	private static TreeNode<Integer> getNode(int i, String[] input) {
		if (input[i].equals("null")) {
			return null;
		}
		return new TreeNode<>(Integer.parseInt(input[i]));
	}
}

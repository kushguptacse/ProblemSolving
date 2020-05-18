package com.daa.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Codec {

	public static void main(String[] args) {
		Codec c = new Codec();
		TreeNode<Integer> root = c.deserialize("[1, 2, 3, null, null, 4, 5]");
		System.out.println(BinaryTreeUtil.preOrderTraversal(root));
		System.out.println(BinaryTreeUtil.inOrderTraversal(root));
		String s1 = c.serialize(root);
		System.out.println(s1);
		root = c.deserialize(s1);
		System.out.println(BinaryTreeUtil.preOrderTraversal(root));
		System.out.println(BinaryTreeUtil.inOrderTraversal(root));
	}
	
	public String serialize(TreeNode<Integer> root) {
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
	public TreeNode<Integer> deserialize(String data) {
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
				node.setLeft( getNode(++i, input));
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

	private TreeNode<Integer> getNode(int i, String[] input) {
		if (input[i].equals("null")) {
			return null;
		}
		return new TreeNode<>(Integer.parseInt(input[i]));
	}
}

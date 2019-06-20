package com.daa.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import com.daa.model.Model;
import com.daa.model.Pair;

public class BinaryTreeUtil {
	private BinaryTreeUtil() {

	}

	private static int preIndex = 0;

	public static void main(String[] args) {
		TreeNode<Character> root = getTreeFromInAndPreOrder(new Character[] { 'D', 'B', 'E', 'A', 'F', 'C' }, new Character[] { 'A', 'B', 'D', 'E', 'C', 'F' });
		System.out.println("In order :");
		printInOrder(root);
		System.out.println("\nPre order :");
		printPreOrder(root);
		System.out.println("\nPost order :");
		printPostOrder(root);
		System.out.println();
		BinaryTree<Integer> tree = new BinaryTree<>();
		tree.insert(7);
		tree.insert(1);
		tree.insert(6);
		tree.insert(2);
		tree.insert(0);
		tree.insert(5);
		System.out.println(tree.preOrderTraversal());
		System.out.println(tree.inOrderTraversal());
		System.out.println(findMax(tree.getRoot()));
		System.out.println(findMaxIterative(tree.getRoot()));
	}

	public static String preOrderTraversal(TreeNode<Integer> root) {
		StringBuilder sb = new StringBuilder();
		preOrder(root, sb);
		return sb.toString();
	}

	private static void preOrder(TreeNode<Integer> node, StringBuilder sb) {
		if (node == null) {
			return;
		}
		sb.append(node.getData()).append(" ");
		preOrder(node.getLeft(), sb);
		preOrder(node.getRight(), sb);
	}

	/**
	 * print preorder
	 * 
	 * @param root
	 */
	public static <T extends Object> void printPreOrder(TreeNode<T> root) {
		if (root == null) {
			return;
		}
		System.out.print(root.getData() + " ");
		printPreOrder(root.getLeft());
		printPreOrder(root.getRight());
	}

	/**
	 * print post order
	 * 
	 * @param root
	 */
	public static <T extends Object> void printPostOrder(TreeNode<T> root) {
		if (root == null) {
			return;
		}
		printPostOrder(root.getLeft());
		printPostOrder(root.getRight());
		System.out.print(root.getData() + " ");
	}

	/**
	 * print inorder
	 * 
	 * @param root
	 */
	public static <T extends Object> void printInOrder(TreeNode<T> root) {
		if (root == null) {
			return;
		}
		printInOrder(root.getLeft());
		System.out.print(root.getData() + " ");
		printInOrder(root.getRight());
	}

	/**
	 * 
	 * @param node
	 * @return True if given Binary Tree is BInary Search Tree
	 */
	public <K extends Comparable<K>> boolean isBinarySearchTree(TreeNode<K> node) {
		return isBST(node, new TreeNode<K>(null));
	}

	private <K extends Comparable<K>> boolean isBST(TreeNode<K> node, TreeNode<K> prev) {
		if (node == null) {
			return true;
		}

		if (!isBST(node.getLeft(), prev)) {
			return false;
		}

		if (prev != null && prev.getData() != null && prev.getData().compareTo(node.getData()) > 0) {
			return false;
		}
		prev.setData(node.getData());
		return isBST(node.getRight(), prev);
	}

	/**
	 * create binary tree from given inorder and pre-order array.
	 * 
	 * create node from preorder array and find its index in inorder array. after that set
	 * left tree from 0,index-1 and right tree from index+1,end return null when start>end
	 * 
	 * @param in
	 * @param pre
	 * @return root node
	 */
	public static <T extends Object> TreeNode<T> getTreeFromInAndPreOrder(T[] in, T[] pre) {
		preIndex = 0;
		return constructTree(in, pre, 0, in.length - 1);
	}

	private static <T extends Object> TreeNode<T> constructTree(T[] in, T[] pre, int start, int end) {
		if (start > end) {
			return null;
		}
		T item = pre[preIndex++];
		TreeNode<T> node = new TreeNode<>(item);
		int index = searchIndex(in, item);
		node.setLeft(constructTree(in, pre, start, index - 1));
		node.setRight(constructTree(in, pre, index + 1, end));
		return node;
	}

	private static <T extends Object> int searchIndex(T[] in, T item) {
		for (int i = 0; i < in.length; i++) {
			if (item.equals(in[i])) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Find maximum element in tree.
	 * 
	 * calculate max of left tree. calculate max of right tree.
	 * 
	 * compare left and right with root and return max. repeat this process for entire tree.
	 * 
	 * @param root
	 * @return max of tree
	 */
	public static Integer findMax(TreeNode<Integer> root) {
		int max = -1;
		if (root != null) {
			int l = findMax(root.getLeft());
			int r = findMax(root.getRight());
			if (l > r) {
				max = l;
			} else {
				max = r;
			}
			if (root.getData() > max) {
				max = root.getData();
			}
		}

		return max;
	}

	/**
	 * just like level order traversal - add left and right to queue. compare queue poll item
	 * with max. and repeat it till queue is empty.
	 * 
	 * @param root
	 * @return
	 */
	public static int findMaxIterative(TreeNode<Integer> root) {
		if (root == null) {
			return Integer.MIN_VALUE;
		}
		Queue<TreeNode<Integer>> queue = new LinkedList<>();
		queue.add(root);
		int max = Integer.MIN_VALUE;
		while (!queue.isEmpty()) {
			TreeNode<Integer> item = queue.poll();
			if (max < item.getData()) {
				max = item.getData();
			}
			if (item.getLeft() != null) {
				queue.add(item.getLeft());
			}
			if (item.getRight() != null) {
				queue.add(item.getRight());
			}
		}
		return max;
	}

	public static TreeNode<Integer> constructTreeFromConsole(Scanner sc) {
		TreeNode<Integer> root = null;

		System.out.println("Enter Root Data -");
		int d = sc.nextInt();
		if (d == -1) {
			return null;
		}
		root = new TreeNode<>(d);
		readChilds(root, sc);
		return root;
	}

	private static void readChilds(TreeNode<Integer> root, Scanner sc) {
		Queue<TreeNode<Integer>> queue = new LinkedList<>();
		queue.add(root);
		while (!queue.isEmpty()) {
			TreeNode<Integer> node = queue.poll();
			System.out.println("Enter Left child of " + node.getData() + " -");
			int l = sc.nextInt();
			if (l != -1) {
				TreeNode<Integer> left = new TreeNode<>(l);
				node.setLeft(left);
				queue.add(left);
			}
			System.out.println("Enter right child of " + node.getData() + " -");
			int r = sc.nextInt();
			if (r != -1) {
				TreeNode<Integer> right = new TreeNode<>(r);
				node.setRight(right);
				queue.add(right);
			}
		}
	}

	public static String inOrderTraversal(TreeNode<Integer> root) {
		StringBuilder sb = new StringBuilder();
		inOrder(root, sb);
		return sb.toString();
	}

	private static void inOrder(TreeNode<Integer> node, StringBuilder sb) {
		if (node == null) {
			return;
		}
		inOrder(node.getLeft(), sb);
		sb.append(node.getData()).append(" ");
		inOrder(node.getRight(), sb);
	}

	/**
	 * if we calculate pre order traversal of a tree. the node before search node is pre order
	 * predecessor and after that is successor
	 * 
	 * @param data - node to be search
	 * @return pre order Predecessor node
	 */
	public static Integer preOrderPredecessor(TreeNode<Integer> root, Integer data) {
		return preOrderPredecessor(root, data, new Model<Integer>(null));
	}

	private static Integer preOrderPredecessor(TreeNode<Integer> root, Integer data, Model<Integer> prev) {
		if (root == null) {
			return null;
		}
		if (data == root.getData()) {
			return prev.getValue();
		}
		prev.setValue(root.getData());
		Integer res = preOrderPredecessor(root.getLeft(), data, prev);
		if (res == null) {
			res = preOrderPredecessor(root.getRight(), data, prev);
			if (res != null) {
				return res;
			}
		}
		return res;
	}

	/**
	 * If we calculate pre order traversal of a tree. the node before search node is pre order
	 * predecessor and after that is successor
	 * 
	 * @param data - node to be search
	 * @return pre order Successor node
	 */
	public static TreeNode<Integer> preOrderSuccessor(TreeNode<Integer> root, Integer data) {
		return preOrderSuccessor(root, data, new Model<>(false));
	}

	/**
	 * 
	 * @param root
	 * @param data
	 * @return pre order Successor node
	 */
	private static TreeNode<Integer> preOrderSuccessor(TreeNode<Integer> root, Integer data, Model<Boolean> found) {
		if (root == null) {
			return null;
		}

		if (found.getValue()) {
			return root;
		}

		if (root.getData() == data) {
			found.setValue(true);
		}

		TreeNode<Integer> res = preOrderSuccessor(root.getLeft(), data, found);
		if (res == null) {
			res = preOrderSuccessor(root.getRight(), data, found);
			if (res != null) {
				return res;
			}
		}
		return res;

	}

	/**
	 * If we calculate in order traversal of a tree. the node before search node is in order
	 * predecessor and after that is successor
	 * 
	 * @param data - node to be search
	 * @param root - root of tree
	 * @return pre order Successor node
	 */
	public static TreeNode<Integer> inOrderSuccessor(TreeNode<Integer> root, Integer data) {
		Pair<TreeNode<Integer>, Boolean> pair = new Pair<>(null, false);
		inOrderSuccessor(root, data, pair);
		return pair.getFirst();
	}

	private static void inOrderSuccessor(TreeNode<Integer> root, Integer data, Pair<TreeNode<Integer>, Boolean> pair) {
		if (root == null) {
			return;
		}
		inOrderSuccessor(root.getLeft(), data, pair);
		if (pair.getSecond()) {
			pair.setFirst(root);
			pair.setSecond(false);
			return;
		}
		if (data == root.getData()) {
			pair.setSecond(true);
		}
		inOrderSuccessor(root.getRight(), data, pair);
	}
	
}

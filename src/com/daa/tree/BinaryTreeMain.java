package com.daa.tree;

import java.util.Scanner;

public class BinaryTreeMain {
	public static void main(String[] args) {
		TreeNode<Integer> root = null;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter binary tree");
		root = BinaryTreeUtil.constructTreeFromConsole(sc);
		String s1 = BinaryTreeUtil.preOrderTraversal(root);
		System.out.println("pre order traversal is : " + s1);
		String[] str = s1.trim().split(" ");
		for (String s : str) {
			System.out.println("pre-order predecessor of node " + s + " is : "
					+ BinaryTreeUtil.preOrderPredecessor(root, Integer.valueOf(s)));
			System.out.println("pre-order successor  of node " + s + " is : "
					+ BinaryTreeUtil.preOrderSuccessor(root, Integer.valueOf(s)));
		}
		System.out.println("--------------------------------------------");
		s1 = BinaryTreeUtil.inOrderTraversal(root);
		System.out.println("in order traversal is : " + s1);
		str = s1.trim().split(" ");
		for (String s : str) {
			System.out.println("in-order successor  of node " + s + " is : "
					+ BinaryTreeUtil.inOrderSuccessor(root, Integer.valueOf(s)));
		}
	}

}

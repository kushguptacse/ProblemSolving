package com.daa.tree;

import java.util.Scanner;

public class ThreadedBinaryTreeMain {
	public static void main(String[] args) {
		try(Scanner sc = new Scanner(System.in)){
			TBTNode<Integer>  root = ThreadedBinaryTreeUtil.constructTreeFromConsole(sc);
			StringBuilder sb = new StringBuilder();
			System.out.println("--------------------------------------------");
			ThreadedBinaryTreeUtil.inOrderTraversal(root,sb);			
			System.out.println("in order traversal is : " + sb.toString());
			for (String s : sb.toString().trim().split(" ")) {
				System.out.println("in-order successor  of node " + s + " is : "
						+ ThreadedBinaryTreeUtil.inOrderSuccessor(root, Integer.valueOf(s)));
			}
			
		}
	}

}

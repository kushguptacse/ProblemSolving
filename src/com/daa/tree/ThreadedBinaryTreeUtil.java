package com.daa.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public final class ThreadedBinaryTreeUtil {

	private ThreadedBinaryTreeUtil() {
		super();
	}
	
	/**
	 * Print in-order traversal of threaded binary tree.
	 * 
	 * goto the left most node and start calculating inorder -
	 * 
	 * 1. if node RightTag is 0 - it means it doesnot has right child. so node.right
	 * will be in-order successor and print it. 2. else for that node. take its
	 * right child and now find left most child of node.right child. it will give
	 * its in-order successor and print it.
	 * 
	 * Repeat it untill all nodes traversed i.e. right most node of the tree
	 * reached.
	 * 
	 * @param root
	 */
	public static void inOrderTraversal(TBTNode<Integer> root, StringBuilder sb) {
		TBTNode<Integer> cur = findLeftMost(root);
		while (cur != null) {
//			System.out.print(cur.getData() + " ");
			sb.append(cur.getData()).append(" ");
			if (cur.getrTag() == 0) {
				cur = cur.getRight();
			} else {
				cur = findLeftMost(cur.getRight());
			}
		}
	}

	private static TBTNode<Integer> findLeftMost(TBTNode<Integer> root) {
		if (root == null) {
			return null;
		}
		while (root.getLeft() != null) {
			root = root.getLeft();
		}
		return root;
	}
	
	/**
	 * apply in-order traversal of threaded binary tree approach.
	 * 
	 * goto the left most node and match for data node while calculating inorder -
	 * return node if found else left most child.
	 * 
	 * 1. if curr node is the searched node. set match flag true.
	 * 2. if node RightTag is 0 - it means it does not has right child. so node.right will be in-order successor and print it.
	 * 3. else for that node. take its right child and now find left most child of node.right child. it will give its in-order successor and print it.
	 * 4. if match is true this is the desired inOrderSuccessor node.
	 * 
	 * Repeat it un till all nodes traversed i.e. right most node of the tree reached.
	 * @param root
	 */
	public static TBTNode<Integer> inOrderSuccessor(TBTNode<Integer> root,Integer data) {
		TBTNode<Integer> cur=findData(root,data);
		boolean match =false;
		while(cur!=null) {
			if(cur.getData()==data) {
				match=true;
			}
			if (cur.getrTag()==0) {
				cur = cur.getRight();
			} else {
				cur = findData(cur.getRight(),data);
			}
			if(match) {
				return cur;
			}
		}
		return null;
	}

	private static TBTNode<Integer> findData(TBTNode<Integer> root, Integer data) {
		if(root==null) {
			return null;
		}
		while(root.getLeft()!=null) {
			if(root.getData()==data) {
				return root;
			}
			root=root.getLeft();
			
		}
		return root;
	}

	public static TBTNode<Integer> constructTreeFromConsole(Scanner sc) {
		TBTNode<Integer> root = null;

		System.out.println("Enter Root Data -");
		int d = sc.nextInt();
		if (d == -1) {
			return null;
		}
		root = new TBTNode<>(d);
		readChilds(root, sc);
		return root;
	}

	private static void readChilds(TBTNode<Integer> root, Scanner sc) {
		Queue<TBTNode<Integer>> queue = new LinkedList<>();
		queue.add(root);
		while (!queue.isEmpty()) {
			TBTNode<Integer> node = queue.poll();
			System.out.println("Enter Left child of " + node.getData() + " -");
			int l = sc.nextInt();
			if (l != -1) {
				TBTNode<Integer> left = new TBTNode<>(l);
				node.setLeft(left);
				queue.add(left);
			}  
			System.out.println("Enter right child of " + node.getData() + " -");
			int r = sc.nextInt();
			if (r != -1) {
				TBTNode<Integer> right = new TBTNode<>(r);
				node.setRight(right);
				queue.add(right);
			} 
		}
		
		createInorderQueue(root, queue);
		createTBTfromBT(root,queue);
	}
	
	public static void createTBTfromBT(TBTNode<Integer> node, Queue<TBTNode<Integer>> q) {
		if (node == null)
			return;

		if (node.getLeft() != null)
			createTBTfromBT(node.getLeft(), q);
		q.poll();

		if (node.getRight() != null)
			createTBTfromBT(node.getRight(), q);

		// If right pointer is NULL, link it to the
		// inorder successor and set 'isThreaded' bit.
		else {
			node.setRight(q.peek());
			node.setrTag(0);
		}
	}

	private static void createInorderQueue(TBTNode<Integer> root,Queue<TBTNode<Integer>> list) {
		if(root==null) {
			return;
		}
		createInorderQueue(root.getLeft(), list);
		list.add(root);
		createInorderQueue(root.getRight(), list);
	}

}

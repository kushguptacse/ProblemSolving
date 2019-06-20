package com.daa.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class GenericTreeUtil {

	private GenericTreeUtil() {

	}

	/**
	 * calculate sum of nodes by traversing each node one by one
	 * 
	 * @param root
	 * @return sum of nodes
	 */
	public static int sum(GenericTreeNode<Integer> root) {
		if (root == null) {
			return 0;
		}
		return sum(root.getChild()) + sum(root.getSibling()) + root.getData();
	}

	/**
	 * maximum depth = height of the tree. array values represent the parent of that
	 * particular index for root parent is -1 e.g - if array is -
	 * [-1,0,1,6,6,0,0,2,7]
	 * 
	 * so if we consider tree we can take index as nodes and value as parent.
	 * 
	 * so 0 has parent -1 1 has parent 0 2 has parent 1 and height 2 3 has parent 6
	 * and height 2 Similarly height of 8 is 4 (i.e. tree path 0,1,2,7,8)
	 * 
	 * O(n2)
	 * 
	 * @param arr
	 * @return height of the tree
	 */
	public static int getHeight(int[] arr) {
		int max = -1;
		for (int i = 0; i < arr.length; i++) {
			int c = 0;
			int j = i;
			while (arr[j] != -1) {
				c++;
				j = arr[j];
			}
			if (c > max) {
				max = c;
			}
		}
		return max;
	}

	public static GenericTreeNode<Integer> constructTreeFromConsole(Scanner sc) {
		GenericTreeNode<Integer> root = null;

		System.out.println("Enter Root Data -");
		int d = sc.nextInt();
		if (d == -1) {
			return null;
		}
		root = new GenericTreeNode<>(d);
		readChilds(root, sc);
		return root;
	}

	private static void readChilds(GenericTreeNode<Integer> root, Scanner sc) {
		Queue<GenericTreeNode<Integer>> queue = new LinkedList<>();
		queue.add(root);
		while (!queue.isEmpty()) {
			GenericTreeNode<Integer> node = queue.poll();
			System.out.println("Enter first child of " + node.getData() + " -");
			int l = sc.nextInt();
			if (l != -1) {
				GenericTreeNode<Integer> child1 = new GenericTreeNode<>(l);
				node.setChild(child1);
				queue.add(child1);
			}
			System.out.println("Enter next sibling of " + node.getData() + " -");
			int r = sc.nextInt();
			if (r != -1) {
				GenericTreeNode<Integer> sibling = new GenericTreeNode<>(r);
				node.setSibling(sibling);
				queue.add(sibling);
			}
		}
	}

	/**
	 * count the number of siblings a node with data=item in n-ary tree
	 * 
	 * @param root
	 * @param item whose sibling we want to calculate
	 * @return number of siblings
	 */
	public static int countSiblings(GenericTreeNode<Integer> root, int item) {
		if (root == null) {
			return 0;
		}
		Queue<GenericTreeNode<Integer>> queue = new LinkedList<>();
		queue.add(root);
		boolean flag=true;
		int c=0;			
		while (!queue.isEmpty()&&flag) {
			c = 0;
			GenericTreeNode<Integer> node = queue.poll();
			node = node.getChild();
			while (node != null && node.getSibling() != null) {
				if (node.getData() == item || node.getSibling().getData() == item) {
					flag = false;
				}
				queue.add(node.getSibling());
				c++;
				node = node.getSibling();
			}
			
		}
		return c;
	}
	
	/**
	 * Find total child of a node with data=item
	 * 
	 * @param root
	 * @param item
	 * @return total child of node
	 */
	public static int countChilds(GenericTreeNode<Integer> root, int item) {

		if (root == null) {
			return 0;
		}

		Queue<GenericTreeNode<Integer>> queue = new LinkedList<>();
		queue.add(root);
		int c = 0;
		while (!queue.isEmpty()) {
			c=0;
			GenericTreeNode<Integer> node = queue.poll();
			if (node.getData() == item) {
				if (node.getChild() != null) {
					c++;
					node=node.getChild();
					while (node.getSibling() != null) {
						c++;
						node=node.getSibling();
					}
				}
				return c;
			}
			if(node.getChild()!=null) {
				queue.add(node.getChild());
			}
			
			if(node.getSibling()!=null) {
				queue.add(node.getSibling());
			}
		}
		return 0;
	}
	
}

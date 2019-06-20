package com.daa.tree;

import java.util.Scanner;

public class GenericTreeMain {
	public static void main(String[] args) {
		System.out.println("Height of the n-ary tree is : ");
		int h = GenericTreeUtil.getHeight(new int[] { -1, 0, 1, 6, 6, 0, 0, 2, 7, 0 });
		System.out.println(h);
		Scanner sc = new Scanner(System.in);
		GenericTreeNode<Integer> root = GenericTreeUtil.constructTreeFromConsole(sc);
		System.out.println("Sum of the nodes in n-ary tree : ");
		System.out.println(GenericTreeUtil.sum(root));
		System.out.println("Enter the item whose siblings you want to calculate : ");
		int item = sc.nextInt();
		System.out.println("Number of sibling in n-ary tree of node :" + item);
		System.out.println(GenericTreeUtil.countSiblings(root, item));
		System.out.println("Enter the item whose child you want to calculate : ");
		item = sc.nextInt();
		System.out.println("Number of childs in n-ary tree of node :" + item);
		System.out.println(GenericTreeUtil.countChilds(root, item));
		sc.close();
	}
}

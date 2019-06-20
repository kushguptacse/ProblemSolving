package com.daa.tree;

import java.util.Arrays;

public class TreeMain {
	public static void main(String[] args) {
//		testBinaryTree();
		System.out.println("-------");
		testBinarySearchTree();
		
		
	}

	private static void testBinaryTree() {
		BinaryTree<Integer> binaryTree = new BinaryTree<>();
		Arrays.stream(new int[] {3,4,2,5,1}).forEach(binaryTree::insert);
		
//		BinarySearchTree<Integer> binarySTree = new BinarySearchTree<>();
//		Arrays.stream(new int[] {8,5,2,3,7,11,4}).forEach(binarySTree::insert);
//		System.out.println("Is binary tree has dead end : "+binarySTree.isDeadEnd(binarySTree.getRoot()));
//		System.out.println("Is binary search tree : "+ binaryTree.isBinarySearchTree(binarySTree.getRoot()));
		System.out.println("In order (Left,Root,Right) : " + binaryTree.inOrderTraversal());
		System.out.println("Pre order (Root,Left,Right) : " + binaryTree.preOrderTraversal());
		System.out.println("Post order (Left,Right,Root) : " + binaryTree.postOrderTraversal());
		System.out.println("Size : "  + binaryTree.size());
		binaryTree.swapLeafNodes();
		System.out.println("After Swapping Node");
		System.out.println("In order (Left,Root,Right) : " + binaryTree.inOrderTraversal());
		System.out.println("Pre order (Root,Left,Right) : " + binaryTree.preOrderTraversal());
		System.out.println("Post order (Left,Right,Root) : " + binaryTree.postOrderTraversal());
		int elem = 1;
		System.out.println("find element "+elem+" : "+binaryTree.findElement(elem));
		System.out.println("Height of binary Tree : "+binaryTree.getHeight());
		System.out.println("Total number of nodes in binary tree is :"+ binaryTree.numberOfNodes());
		System.out.println("Number of leaves in binary Tree: ");
		System.out.println(binaryTree.numberOfLeaf());
		System.out.println("Number of Non leaves in binary Tree:");
		System.out.println(binaryTree.numberOfNonLeaf());
		System.out.println("Number of Full Nodes in binary Tree: ");
		System.out.println(binaryTree.numberOfFullNodes());
//		System.out.println(binaryTree.numberOfUnLabeledTreePosible(4));
//		System.out.println(binaryTree.numberOfLabeledTreePosible(4));
	}
	
	private static void testBinarySearchTree() {
		BinarySearchTree<Integer> binaryTree = new BinarySearchTree<>();
		Arrays.stream(new int[] {6,-13,14,-8,15,13,7}).forEach(binaryTree::insert);
		System.out.println("In order (Left,Root,Right) : " + binaryTree.inOrderTraversal());
//		binaryTree.deleteNode(50);
		binaryTree.removeNodeOutsideRange(-10, 13);
		System.out.println("Number of Nodes : "+binaryTree.numberOfNodes());
		System.out.println("In order (Left,Root,Right) : " + binaryTree.inOrderTraversal());
		System.out.println("Pre order (Root,Left,Right) : " + binaryTree.preOrderTraversal());
		System.out.println("Post order (Left,Right,Root) : " + binaryTree.postOrderTraversal());
		System.out.println("Size : "  + binaryTree.size());
		System.out.println("minimum element is : "+binaryTree.findMinimum());
		System.out.println("max element is : "+binaryTree.findMaximum());
		int elem = 30;
		System.out.println("find element "+elem+" : "+binaryTree.findElement(elem));
		BinarySearchTree<Integer> binaryTree2 = new BinarySearchTree<>();
		Arrays.stream(new int[] {40,30,20,50,35,55}).forEach(binaryTree2::insert);
//		System.out.println("In order (Left,Root,Right) : " + binaryTree2.inOrderTraversal());
		System.out.println("Two trees are equal : "+binaryTree.compareTree(binaryTree2));
		System.out.println("Sum of all nodes : "+binaryTree2.sumOfBinarySearchTree());
		int k = 5;
		System.out.println(k+" smallest node : "+binaryTree.findKthSmallestItem(k));
		System.out.println("In order (Left,Root,Right) : " + binaryTree.inOrderTraversal());
		System.out.println("is complete binary tree : "+binaryTree.isComplete());
		System.out.println("Level order (Left,Root,Right) : " + binaryTree.levelOrderTraversal());
		System.out.println("Level order (Left,Root,Right) : " + binaryTree2.levelOrderTraversal());
	}
}

package com.daa.tree.main;

import java.util.Arrays;
import java.util.stream.IntStream;

import com.daa.array.Array;
import com.daa.list.LinkedList;
import com.daa.list.List;
import com.daa.stack.Deque;
import com.daa.stack.StackArray;
import com.daa.string.StringHelper;
import com.daa.tree.BinaryTree;

public class RunApplication {
	public static void main(String[] args) {
		testLinkedList();
		int arr[] = { 1, 2, 3, 4, 5 };
		int brr[] = { 0, 1, 2 };
		int res[]=rightArrayRotation1(arr,6, brr);
		for (int i = 0; i < res.length; i++) {
			System.out.println(res[i]);
		}
		printDiamond(6);
	}

	/**
	 * 
	 */
	public static void printDiamond(int n) {
		int loop = 2 * n - 1;
		int j=0;
		for (int i = 1; i <= loop; i++) {
			int count = 0;
			int startPosition = 0;
			if(i>n) {
				count = i-(loop/2)+j;
				j=j+1;
				startPosition = i -n + 1;
			} else {
				startPosition = n - i + 1;
			}
			for (int k = 1; k <= loop; k++) {
				if (count < i && k == startPosition) {
					count++;
					startPosition = startPosition + 2;
					System.out.print("*");
				} else {
					System.out.print(" ");
				}
			}
			System.out.println();

		}
	}

	public static int[] rightArrayRotation1(int[] a, int k, int[] queries) {
		k = k % (a.length);
		int n = a.length;
		reverseArray(a, 0, n - 1);
		reverseArray(a, 0, k - 1);
		reverseArray(a, k, n - 1);

		int fr[] = new int[queries.length];
		int count = 0;
		for (int i : queries) {
			fr[count] = a[i];
			count++;
		}

		return fr;

	}

	private static void reverseArray(int[] a, int start, int end) {
		while (start < end) {
			int temp = a[start];
			a[start] = a[end];
			a[end] = temp;
			start++;
			end--;
		}
	}

	private static void testStackArray(StackArray<Integer> stack) {
		stack.push(1);
		System.out.println(stack.pop());

		IntStream.range(2, 5).forEach(stack::push);
		IntStream.range(0, stack.size()).forEach(o -> System.out.print(stack.peek() + ","));
		System.out.println();
		int size = stack.size();
		IntStream.range(0, size).forEach(o -> System.out.print(stack.pop() + ","));
		System.out.println("\n" + stack.size());
	}

	private static void testStack(Deque<Integer> stack) {

		stack.push(1);
		System.out.println(stack.pop());

		IntStream.range(2, 5).forEach(stack::push);
		IntStream.range(0, stack.size()).forEach(o -> System.out.print(stack.peek() + ","));
		System.out.println();
		int size = stack.size();
		IntStream.range(0, size).forEach(o -> System.out.print(stack.pop() + ","));
		System.out.println("\n" + stack.size());
	}

	private static void testLinkedList() {
		List<String> list = new LinkedList<>();
		list.add("hello");
		list.add("my");
		list.add("name");
		list.add("is");
		list.add("kush");
//		list.print();
//		list.remove("kush");
//		list.print();
//		list.add("luv");
//		list.add(" !");
//		list.print();
//		list.add(0, "hi");
//		list.print();
//		list.add(7,"test");
//		list.print();
//		System.out.println("***********Deletion starts***********");
//		System.out.println("removed item from index 0:"+list.remove(0));
//		list.print();
//		System.out.println("removed item from index 2:"+list.remove(2));
//		list.print();
//		System.out.println("removed item from index 5:"+list.remove(5));
//		list.print();
//		System.out.println("Total Item : "+list.size());
//		list.add(5,"end1");
//		list.print();
//		System.out.println("middle element is :"+((LinkedList<String>)list).findMiddleNode());
		System.out.println("list before reverse : ");
		list.print();
		((LinkedList<String>) list).reverse();
		list.print();
		((LinkedList<String>) list).reverse();
		list.print();
		list.add(0, "hi");
		list.print();
	}

	private static void testString() {
		System.out.println("Strings are anagram: " + StringHelper.anagram("geekhelpedme", "helpedmegeek"));
	}

	private static void testArray() {
		Array arr = new Array(4);
		IntStream.range(1, 8).forEach(arr::add);
//		arr.add(2);
//		arr.add(8);
//		arr.add(8);
//		arr.print();
//		arr.reverse();
		arr.print();
		arr.rotateLeft(2);
		arr.print();
		Arrays.stream(arr.selectKItems(4)).forEach(i -> System.out.print(i + " "));
//		arr.add(26);
//		arr.print();
//		arr.removeElementByIndex(2);
//		arr.print();
	}

	private static void testBinaryTree() {
		BinaryTree binaryTree = new BinaryTree();
		IntStream.range(1, 6).forEach(binaryTree::insert);
		System.out.println("In order (Left,Root,Right) : " + binaryTree.inOrderTraversal());
		System.out.println("Pre order (Root,Left,Right) : " + binaryTree.preOrderTraversal());
		System.out.println("Post order (Left,Right,Root) : " + binaryTree.postOrderTraversal());

	}

}

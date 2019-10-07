package com.daa.algo;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.stream.IntStream;

import com.daa.array.ArrayUtil;
import com.daa.list.LinkedListUtil;
import com.daa.list.Node;

public class RecurrsionMainApp {

	public static void main(String[] args) {
		new Recursion().towerOfHanoi(3, "x", "y", "z");
		practice();
		new RecurrsionMainApp().testGCD();
//		checkCutRod();
//		checkSubSetSum();
//		dpTest();
		new RecurrsionMainApp().testSearch();
	}

	public void testGCD() {
		Recursion obj = new Recursion();
		int a = 10;
		int b = 15;
		int g = obj.gcdBest(a, b);
		System.out.println("GCD(" + a + " , " + b + ") = " + g);

		a = 35;
		b = 10;
		g = obj.gcdBest(a, b);
		System.out.println("GCD(" + a + " , " + b + ") = " + g);

		a = 31;
		b = 2;
		g = obj.gcdBest(a, b);
		System.out.println("GCD(" + a + " , " + b + ") = " + g);
		System.out.println("GCD- " + obj.gcd(7, 9));
		System.out.println("GCD- " + obj.gcd(30, 100));
		System.out.println("GCD: " + obj.gcdBest(7, 9));
		System.out.println("GCD: " + obj.gcdBest(30, 100));

	}

	public void testSearch() {
		Recursion obj = new Recursion();
		int arr[] = { 2, 3, 4, 10, 40 };
		System.out.println("LS Element is present at index: " + obj.linearSearch(arr, 10));
		System.out.println("BS Element is present at index: " + obj.binarySearch(arr, 10));
		System.out.println("LS Element is present at index= " + obj.linearSearch(arr, 40));
		System.out.println("BS Element is present at index= " + obj.binarySearch(arr, 40));
		System.out.println("LS Element is present at index : " + obj.linearSearch(arr, 4));
		System.out.println("BS Element is present at index : " + obj.binarySearch(arr, 4));
		System.out.println("LS Element is present at index> " + obj.linearSearch(arr, 2));
		System.out.println("BS Element is present at index> " + obj.binarySearch(arr, 2));
		System.out.println("LS Element is present at index- " + obj.linearSearch(arr, 23));
		System.out.println("BS Element is present at index- " + obj.binarySearch(arr, 34));
	}

	private static void practice() {
		Recursion recursion = new Recursion();
		recursion.headRecursion(4);
		System.out.println("----------------------------------------");
		recursion.tailRecursion(4);
	}

	private static void dpTest() {
		Recursion recursion = new Recursion();
		System.out.println("minimum coins needed: " + recursion.minCoinChange(new int[] { 1, 2, 3 }, 4));
		int[] coins = { 9, 6, 5, 1 };
		System.out.println("minimum coins needed: " + recursion.minCoinChange(coins, 8));
		int[] val = new int[] { 60, 100, 120 };
		int[] wt = new int[] { 10, 20, 30 };
		int w = 50;
		System.out.println("maximum possible profit is: " + recursion.knapsack(val, wt, w));
		System.out.println("maximum possible profit is: "
				+ recursion.knapsack(new int[] { 1, 2, 5, 6 }, new int[] { 2, 3, 4, 5 }, 8));
		System.out
				.println("Total possible ways to do coin change is: " + recursion.coinChange(new int[] { 1, 2, 3 }, 4));
		System.out.println(
				"Total possible ways to do coin change is: " + recursion.coinChange(new int[] { 2, 5, 3, 6 }, 10));
		System.out.println(
				"total possible ways to do coin change is: " + recursion.coinChange(new int[] { 1, 5, 10 }, 10));
	}

	private static void checkCutRod() {
		Recursion obj = new Recursion();
		int[] arr = new int[] { 1, 5, 8, 9, 10, 17, 17, 20 };
		System.out.println("Maximum Obtainable Value is> " + obj.cutRod(arr, arr.length));
		arr = new int[] { 2, 5, 7, 3 };
		System.out.println("Maximum Obtainable Value is- " + obj.cutRod(arr, 5));
		System.out.println("Maximum Obtainable Value is: " + obj.cutRod(new int[] { 3, 5, 8, 9, 10, 17, 17, 20 }, 8));
	}

	private static void checkSubSetSum() {
		Recursion obj = new Recursion();
		int[] set = { 3, 34, 4, 12, 5, 2 };
		System.out.println("Found a subset: " + obj.subsetSumExists(set, 11));
		set = new int[] { 1, 7, 5, 2 };
		System.out.println("Found a subset: " + obj.subsetSumExists(set, 4));
		System.out.println("Found a subset- " + obj.subsetSumExists(set, 8));
		set = new int[] { 1, 3, 4, 6 };
		System.out.println("Found a subset- " + obj.subsetSumExists(set, 12));
		System.out.println("Found a subset> " + obj.subsetSumExists(set, 5));
		System.out.println("Found a subset " + obj.subsetSumExists(set, 2));
		System.out.println("Found a subset " + obj.subsetSumExists(set, 1));

	}

	public static void test() {
		Recursion recurrsion = new Recursion();
		int n = 6;
		int[] arr = new int[] { 1, 2, 3, 4, 5 };
		System.out.println(recurrsion.binarySearch(arr, 1));
		System.out.println(recurrsion.contains("elf", "waffles"));
		System.out.println(recurrsion.max(new int[] { 8, 133, 3, 14, 511 }));
		System.out.println(recurrsion.sum(n));
		System.out.println((n * (n + 1)) / 2);
		System.out.println(recurrsion.multiply(3, 4));
		System.out.println(recurrsion.power(3, 4));
		System.out.println(recurrsion.gcd(54, 24));
		System.out.println(recurrsion.lcm(9, 12));
		System.out.println(recurrsion.reverse("tset rorrim"));
		System.out.println(recurrsion.sumOfSquares(3));
		System.out.println(recurrsion.sumOfSeries(2));
		System.out.println(recurrsion.equalizeArray(new int[] { 50, 100 }));
		Deque<Integer> stack = new LinkedList<>();
		IntStream.of(2, 3, 1, 14, 8).forEach(stack::push);
		System.out.println("original stack " + stack);
		recurrsion.reverse(stack);
		System.out.println("reverse stack " + stack);
		recurrsion.sort(stack);
		System.out.println("sorted stack " + stack);
		int[] ar = new int[] { 11, 12, 13 };
		recurrsion.insertionSort(ar);
		System.out.println("-----------------");
		Arrays.stream(ar).forEach(o -> System.out.print(o + " , "));
		System.out.println();
		ar = ArrayUtil.mergeTwoSortedArray(new int[] { 2, 6, 41, 118 }, new int[] { 1, 23, 50, 117 });
		System.out.println("-----------------");
		Arrays.stream(ar).forEach(o -> System.out.print(o + " , "));
		Node<Integer> node1 = new Node<>(1);
		node1.setNext(new Node<>(3));
		node1.getNext().setNext(new Node<>(5));

		Node<Integer> node2 = new Node<>(4);
		node2.setNext(new Node<Integer>(8));
		System.out.print("\n-------Merge two sorted list----------");
		node1.print();
		node2.print();
		Node<Integer> result = LinkedListUtil.mergeSortedLists(node1, node2);
		result.print();
	}
}

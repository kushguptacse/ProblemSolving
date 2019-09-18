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
		Recurrsion recurrsion = new Recurrsion();
		checkSubSetSum();
		System.out.println("minimum coins needed: " + recurrsion.minCoinChange(new int[] { 1, 2, 3 }, 4));
		int[] coins = { 9, 6, 5, 1 };
		System.out.println("minimum coins needed: " + recurrsion.minCoinChange(coins, 8));
		int[] val = new int[] { 60, 100, 120 };
		int[] wt = new int[] { 10, 20, 30 };
		int w = 50;
		System.out.println("maximum possible profit is: " + recurrsion.knapsack(val, wt, w));
		System.out.println("maximum possible profit is: " + recurrsion.knapsack(new int[] { 1, 2, 5, 6 }, new int[] { 2, 3, 4, 5 }, 8));
		System.out.println("Total possible ways to do coin change is: " + recurrsion.coinChange(new int[] { 1, 2, 3 }, 4));
		System.out.println("Total possible ways to do coin change is: " + recurrsion.coinChange(new int[] { 2, 5, 3, 6 }, 10));
		System.out.println("total possible ways to do coin change is: " + recurrsion.coinChange(new int[] { 1, 5, 10 }, 10));
	}

	private static void checkSubSetSum() {
		Recurrsion obj = new Recurrsion();
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
		Recurrsion recurrsion = new Recurrsion();
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

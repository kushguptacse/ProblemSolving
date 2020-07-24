package com.daa.graph.backtracking;

import java.util.Arrays;

public class BacktrackPractice {
	public static void main(String[] args) {
		BacktrackPractice bp = new BacktrackPractice();
		bp.printPermutations(new int[] { 1, 2, 3 });
	}

	private void printPermutations(int[] num) {
		printPermutations(num, 0);
	}

	private void printPermutations(int[] num, int l) {
		if (l >= num.length) {
			System.out.println(Arrays.toString(num));
		} else {
			for (int i = l; i < num.length; i++) {
				swap(num, l, i);
				printPermutations(num, l + 1);
				swap(num, l, i);
			}
		}
	}

	private void swap(int[] num, int i, int j) {
		int temp = num[i];
		num[i] = num[j];
		num[j] = temp;
	}
}

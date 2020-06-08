package com.daa.algo.greedy;

import java.util.Arrays;

public class GreedyPractice {
	public static int twoCitySchedCost(int[][] costs) {
		Arrays.sort(costs, (a, b) -> a[0] - a[1] - b[0] + b[1]);
		int sum = 0;
		for (int ii = 0; ii < costs.length; ii++) {
			if (ii < costs.length / 2) {
				sum += costs[ii][0];
			} else {
				sum += costs[ii][1];
			}
		}
		return sum;
	}
}

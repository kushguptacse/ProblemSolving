package com.daa.algo.greedy;

import java.util.Arrays;

/**
 * Given weights and profit of n items, we need to put these items in a knapsack(bag) of
 * capacity W to get the maximum total profit in the knapsack.
 * 
 */
public class FractionalKnapsack {

	public static void main(String[] args) {
		test1();
		test2();
	}

	private static void test1() {
		int[] wt = { 10, 40, 20, 30 };
		int[] val = { 60, 40, 100, 120 };
		int capacity = 50;

		double maxValue = maxProfit(wt, val, capacity);
		System.out.println("Maximum value we can obtain = " + maxValue);
	}

	private static void test2() {
		int[] val = { 2, 28, 25, 18, 9 };
		int[] wt = { 1, 4, 5, 3, 3 };
		int capacity = 15;

		double maxValue = maxProfit(wt, val, capacity);
		System.out.println("Maximum value we can obtain = " + maxValue);
	}

	/**
	 * @f:off
	 * steps- 1.calculate profit/weight ratio. 
	 * 2.arrange data in decreasing order of
	 * profit/weight ratio. 
	 * 3.start picking one by one till capacity not reached. 
	 * 4.After that if fractionally is possible than add fraction of weight also.
	 * @f:on
	 * 
	 * @param weight
	 * @param profit
	 * @param capacity
	 * 
	 * @return max profit that can be availed
	 */
	public static double maxProfit(int[] weight, int[] profit, int capacity) {
		KnapsackItem[] items = new KnapsackItem[profit.length];
		// Initialize
		for (int i = 0; i < profit.length; i++) {
			items[i] = new KnapsackItem(((double) profit[i]) / weight[i], i);
		}

		// Arrange data in profit/weight ratio decreasing order.
		Arrays.sort(items, (o1, o2) -> o2.getRatio().compareTo(o1.getRatio()));
		double total = 0.0d;
		for (KnapsackItem knapsackItem : items) {
			if (capacity - weight[knapsackItem.getIndex()] >= 0) {
				total = total + profit[knapsackItem.getIndex()];
				capacity = capacity - weight[knapsackItem.getIndex()];
			} else {
				total = total + ((double) capacity / weight[knapsackItem.getIndex()]) * profit[knapsackItem.getIndex()];
				break;
			}
		}
		return total;
	}

}

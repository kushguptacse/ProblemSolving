package com.daa.algo.dp;

import com.daa.math.MathUtil;

/**
 * 0-1 knapsack problem. our aim is to get the maximum profit. You cannot break an item,
 * either pick the complete item, or don’t pick it (0-1 property). here greedy method
 * fails as we cannot take fractional part.
 * 
 * o(2^n) time complexity for naive approach. as we have to consider each item and for every item we have two
 * options either to include it or not.
 * DP - by storing results we can solve it faster. 
 * Time Complexity: O(nW) where n is the number of items and W is the capacity of knapsack.
 * @f:off
 * Approach -
 * 1. To store result we will keep 2D matrix of size (n+1)*(w+1) say 'v'. where n is the size of weight array and w is the max capacity of knapsack.
 * 								 __
 * So, for item v(i,wi)= 		| 
 * 								| max(v(i-1,w),v(i-1,w-wi)+pi)   		, wi<w
 *								| v(i-1,w)                   		    , wi>=w	
 *								|__
 * For particular element i, we need to check below conditions - 
 * 1.if it's weight exceeding remaining knapsack capacity. if yes, set v(i,w) = v(i-1,w)
 * 2. else find whether inclusion of it is increasing the profit or not.
 * i.e. we find maximum of both cases (v[i - 1][w], v[i - 1][w - weight[i - 1]] + profit[i - 1]) and set it to v(i,wi).
 * 
 * Abdul bari for knapsack and geeksForGeeks for print knapsack
 * @f:on
 * 
 */
public class Knapsack {

	public static void main(String[] args) {
		Knapsack obj = new Knapsack();
		int val[] = new int[] { 60, 100, 120 };
		int wt[] = new int[] { 10, 20, 30 };
		int w = 50;
		System.out.println("Maximum possible profit is: " + obj.printKnapsackItem(val, wt, w));
		System.out.println("maximum possible profit is: " + obj.printKnapsackItem(new int[] { 1, 2, 5, 6 }, new int[] { 2, 3, 4, 5 }, 8));
		val = new int[] { 40, 100, 50, 60 };
		wt = new int[] { 20, 10, 40, 30 };
		w = 60;
		System.out.println("maximum possible profit is: " + obj.printKnapsackItem(val, wt, w));
	}

	/**
	 * Print the items included in knapsack and also print the total profit obtained.
	 * 
	 * @param weight
	 * @param profit
	 * @param capacity
	 * @return max profit possible
	 */
	public int printKnapsackItem(int[] profit, int[] weight, int capacity) {
		int[][] v = new int[weight.length + 1][capacity + 1];

		for (int i = 1; i <= weight.length; i++) {
			for (int w = 1; w <= capacity; w++) {
				if (w < weight[i - 1]) {
					v[i][w] = v[i - 1][w];
				} else {
					v[i][w] = MathUtil.max(v[i - 1][w], v[i - 1][w - weight[i - 1]] + profit[i - 1]);
				}
			}
		}

		System.out.print("Item to be included in knapsack: ");
		// print knapsack item
		int i = weight.length;
		int cost = v[weight.length][capacity];
		int w = capacity;
		// start from last profit and iterate till profit reach 0 or weight reached 0.
		while (cost > 0 && i > 0) {
			// check if remaining profit is different then row above it.
			// if yes print current weight and decrease profit and weight.
			if (cost != v[i - 1][w]) {
				System.out.print(weight[i - 1] + " ");
				cost = cost - profit[i - 1];
				w = w - weight[i - 1];
			}
			// since now i is pointing to above row and we have updated w. which will be the new
			// column to which we want to check profit condition
			i--;
		}
		System.out.println();
		return v[weight.length][capacity];
	}

}

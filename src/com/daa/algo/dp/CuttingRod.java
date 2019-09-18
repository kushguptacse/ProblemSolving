package com.daa.algo.dp;

import com.daa.math.MathUtil;

/**
 * Determine the maximum value obtainable by cutting up the rod and selling the pieces.
 * 
 * For example, if length of the rod is 8 and the values of different pieces are given as
 * following, then the maximum obtainable value is 22 (by cutting in two pieces of lengths
 * 2 and 6)
 * @f:off
 * length   | 1   2   3   4   5   6   7   8  
 *	--------------------------------------------
 * price    | 1   5   8   9  10  17  17  20
 * 
 * eg2 -> l=5m
 * prices are :
 * 1m = $2
 * 2m = $5
 * 3m = $7
 * 4m = $3
 * solution - {2,3} or {2,2,1}. here both will give same profit $12
 * @f:on
 */
public class CuttingRod {

	public static void main(String[] args) {

		CuttingRod obj = new CuttingRod();
		int[] arr = new int[] { 1, 5, 8, 9, 10, 17, 17, 20 };
		System.out.println("Maximum Obtainable Value is> " + obj.printCuttingRod(arr, arr.length));
		arr = new int[] { 2, 5, 7, 3 };
		System.out.println("Maximum Obtainable Value is- " + obj.printCuttingRod(arr, 5));
		System.out.println("Maximum Obtainable Value is: " + obj.printCuttingRod(new int[] { 3, 5, 8, 9, 10, 17, 17, 20 }, 8));

	}

	/**
	 * Udemy
	 * 
	 * o(l*arr.length)
	 * 
	 * @param arr - where index is the rod length and profit as value to that length
	 * @param l   - length of the rod whom we want to make cuts
	 * @return maximum profit that can be achieved
	 */
	public int printCuttingRod(int[] arr, int l) {
		if (l == 0) {
			return 0;
		}
		int[][] table = new int[arr.length + 1][l + 1];
		for (int i = 1; i <= arr.length; i++) {
			for (int j = 1; j <= l; j++) {
				if (i > j) {
					table[i][j] = table[i - 1][j];
				} else {
					table[i][j] = MathUtil.max(table[i - 1][j], table[i][j - i] + arr[i - 1]);
				}
			}
		}

		printPath(table, arr, l);

		return table[arr.length][l];
	}

	/**
	 * print included length:profit values to get maximum profit
	 * @param table
	 * @param arr
	 * @param l
	 */
	private void printPath(int[][] table, int[] arr, int l) {
		int i = arr.length;
		System.out.print("rod length : [");
		while (i > 0 && l > 0) {
			if (table[i][l] != table[i - 1][l]) {
				System.out.print(i + "m:$" + arr[i - 1] + " ");
				l = l - i;
			} else {
				i--;
			}
		}
		System.out.println("]");

	}

}

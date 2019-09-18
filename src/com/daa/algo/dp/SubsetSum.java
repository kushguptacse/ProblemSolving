package com.daa.algo.dp;

/**
 * Given a set of non-negative integers, and a value sum, determine if there is a subset
 * of the given set with sum equal to given sum. Example:
 * 
 * Input: set[] = {3, 34, 4, 12, 5, 2}, sum = 9 Output: True //There is a subset (4, 5)
 * with sum 9.
 *
 */
public class SubsetSum {

	public static void main(String[] args) {
		SubsetSum obj = new SubsetSum();
		int set[] = { 3, 34, 4, 12, 5, 2 };
		System.out.println("Found a subset: " + obj.printSubsetSumExists(set, 11));
		set = new int[] { 1, 7, 5, 2 };
		System.out.println("Found a subset: " + obj.printSubsetSumExists(set, 4));
		System.out.println("Found a subset- " + obj.printSubsetSumExists(set, 8));
		set = new int[] { 4, 6, 1, 3 };
		System.out.println("Found a subset- " + obj.printSubsetSumExists(set, 12));
		System.out.println("Found a subset> " + obj.printSubsetSumExists(set, 5));
		System.out.println("Found a subset> " + obj.printSubsetSumExists(set, 2));
		System.out.println("Found a subset " + obj.printSubsetSumExists(set, 1));
		System.out.println("Found a subset " + obj.printSubsetSumExists(set, 13));
	}

	/**
	 * Approach - Take 2d array of size - (set.length+1)*(s+1) where row denotes set elements
	 * and s for sum.
	 * 
	 * o(sum*s)
	 * 
	 * @param set
	 * @param s   - sum required
	 * @return true if sum is possible
	 */
	public boolean printSubsetSumExists(int[] set, int s) {
		if (s == 0) {
			return true;
		}

		if (set.length == 0) {
			return false;
		}

		boolean[][] table = new boolean[set.length + 1][s + 1];

		// when s=0, it means we can get s without including any element
		for (int i = 0; i <= set.length; i++) {
			table[i][0] = true;
		}

		for (int i = 1; i <= set.length; i++) {
			for (int j = 1; j <= s; j++) {
				// for every entry check if element > required sum. if yes then it is not possible to get
				// sum. so copy value from previous row without changing column as sum remains same.
				if (set[i - 1] > j) {
					table[i][j] = table[i - 1][j];
				} else {
					// else set true if previous value or value present at previous row and updated sum column
					// is true. if any of them has solution set true. any value can be true in table only if
					// it reaches sum 0
					table[i][j] = table[i - 1][j] || table[i - 1][j - set[i - 1]];
				}
			}
		}
		printPath(table, set, s);
		return table[set.length][s];
	}

	/**
	 * Print the elements present in set.
	 * 
	 * if current element value is different then above row element then that element is part
	 * of set else check for above row
	 * 
	 * @param table
	 * @param set
	 * @param length
	 * @param s
	 */
	private void printPath(boolean[][] table, int[] set, int s) {
		int i = set.length;
		System.out.print("Elements in set: [ ");
		while (i > 0 && s > 0) {
			if (table[i][s] != table[i - 1][s]) {
				System.out.print(set[i - 1] + " ");
				s = s - set[i - 1];
			}
			--i;
		}
		System.out.println(" ]");
	}

}

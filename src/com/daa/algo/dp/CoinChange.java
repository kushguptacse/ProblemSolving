package com.daa.algo.dp;

/**
 * Udemy for explanation.
 */
public class CoinChange {

	public static void main(String[] args) {
		CoinChange obj = new CoinChange();
		System.out.println("Minimum coin required to do change is " + obj.minimumCoinChange(new int[] { 7, 2, 3, 6 }, 13));
//		System.out.println("Minimum coin required to do change is " + obj.minimumCoinChange(new int[] { 9, 6, 5, 1 }, 11));
//		System.out.println("Minimum coin required to do change is " + obj.minimumCoinChange(new int[] { 25, 10, 5 }, 30));
//		System.out.println("Total possible ways to do coin change is: " + obj.count(new int[] { 25, 10, 5 }, 30));
//		System.out.println("Total possible ways to do coin change is: " + obj.count(new int[] { 1, 2, 3 }, 4));
//		System.out.println("Minimum coin required to do change is " + obj.minimumCoinChange(new int[] { 1, 2, 3 }, 5));
//		System.out.println("Total possible ways to do coin change is: " + obj.count(new int[] { 2, 5, 3, 6 }, 10));
//		System.out.println("Minimum coin required to do change is: " + obj.minimumCoinChange(new int[] { 2, 5, 3, 6 }, 10));
//		System.out.println("total possible ways to do coin change is: " + obj.count(new int[] { 1, 5, 10 }, 10));
//		System.out.println("Minimum coin required to do change is: " + obj.minimumCoinChange(new int[] { 1, 5, 10 }, 10));
//		System.out.println("=====================================");
//		System.out.println("Total ways to do coin change is: " + obj.countSpaceOptimized(new int[] { 1, 2, 3 }, 4));
//		System.out.println("Total ways to do coin change is: " + obj.countSpaceOptimized(new int[] { 2, 5, 3, 6 }, 10));
//		System.out.println("total ways to do coin change is: " + obj.countSpaceOptimized(new int[] { 1, 5, 10 }, 10));
	}

	/**
	 * Problem statement-
	 * 
	 * We are given coin array. e.g. [1,2,3] and we want to get change of 4$. so, In how many
	 * ways we can do change. given - we can use same coin multiple times. The order of coins
	 * doesn’t matter. so, here result will contain - { [1,1,1,1],[1,1,2],[1,3],[2,2] } and
	 * answer will be 4.
	 * 
	 * Time Complexity - if we apply naive approach and check every combo. then every can has
	 * two possibility- to be included or not included in result set.so, for n coins - 2^n
	 * 
	 * By using DP we can reduce number of function calls. o(n*m)
	 * 
	 * 
	 * @param coins
	 * @param sum   to be reached
	 * @return total ways of giving change
	 */
	public int count(int[] coins, int sum) {
		// take table array where row is the coin to be considered and column is the sum to be
		// reached.
		int[][] table = new int[coins.length + 1][sum + 1];
		// if sum=0, one solution exists of not including ith coin.
		for (int i = 0; i <= coins.length; i++) {
			table[i][0] = 1;
		}
		// check for all possible combination.
		for (int i = 1; i <= coins.length; i++) {
			for (int j = 1; j <= sum; j++) {
				// if coin at particular index < the column index set table[i][j] = table[i-1][j].
				// i.e. don't consider that coin and hence the count remain same as previous
				if (coins[i - 1] > j) {
					table[i][j] = table[i - 1][j];
				} else {
					// else set table[i][j]=table[i-1][j]+table[i][j-coins[i-1]
					// i.e. we need to add current coin into previous value. here current count index is 1 and
					// to get column it is current column - coin present at that index in coin array.
					// TODO - IMPORTANT POINT - Whenever we can re-use array values. in that case if element
					// is taken into
					// set we does not decrement i as we can again try it.
					table[i][j] = table[i][j - coins[i - 1]] + table[i - 1][j];
				}
			}
		}

		printOneSolution(coins, table, sum);

		return table[coins.length][sum];
	}

	/**
	 * Given a value V, if we want to make change for V cents, and we have infinite supply of
	 * each of C = { C1, C2, .. , Cm} valued coins, what is the minimum number of coins to
	 * make the change?
	 * 
	 * Input: coins[] = {25, 10, 5}, V = 30
	 * 
	 * Output: Minimum 2 coins required. We can use one coin of 25 cents and one of 5 cents
	 * 
	 * @param coins
	 * @param sum
	 * @return minimum coins required to get the desired sum
	 */
	public int minimumCoinChange(int[] coins, int amount) {
		// table[i] will be storing
		// the minimum number of coins
		// required for i value. So
		// table[amount] will have result
		int table[] = new int[amount + 1];

		// Base case (If given value amount is 0)
		table[0] = 0;

		// Compute minimum coins required for all
		// values from 1 to amount
		for (int amt = 1; amt <= amount; amt++) {
			table[amt] = Integer.MAX_VALUE;
			// Go through all coins smaller than i
			for (int j = 0; j < coins.length; j++)
				if (coins[j] <= amt) {
					int subRes = table[amt - coins[j]];
					if (subRes != Integer.MAX_VALUE && subRes + 1 < table[amt]) {
						table[amt] = subRes + 1;
					}
				}
		}
		return table[amount];
	}

	/**
	 * Print one possible solution to get change of sum
	 * 
	 * @param coins
	 * @param table
	 * @param sum
	 */
	private void printOneSolution(int[] coins, int[][] table, int sum) {
		int i = coins.length;
		System.out.print("coins considered for 1st change: [ ");
		while (i > 0 && sum > 0) {
			if (table[i][sum] != table[i - 1][sum]) {
				System.out.print(coins[i - 1] + " ");
				sum = sum - coins[i - 1];
			} else {
				i--;
			}
		}
		System.out.println("]");
	}

	/**
	 * since we are just copying table prev value if for current coin it's value is less then
	 * index. we can optimized space by using 1d array. set table[0]=1, then take coin one by
	 * one and then starting from that coin till table length update the table array.
	 * 
	 * geeksforgeeks for code
	 * 
	 * @param coins
	 * @param sum
	 * @return total number of ways to reach sum
	 */
	public int countSpaceOptimized(int[] coins, int sum) {
		// table[i] will be storing the number of solutions for
		// value i. We need sum+1 rows as the table is constructed
		// in bottom up manner using the base case (n = 0)
		int[] table = new int[sum + 1];
		table[0] = 1;
		// take coin from array one by one
		for (int i = 0; i < coins.length; i++) {
			// here we have ignored index less than coin value.
			for (int j = coins[i]; j <= sum; j++) {
				// update coin index by just adding prev value with the new value
				table[j] = table[j] + table[j - coins[i]];
			}
		}
		return table[sum];
	}

}

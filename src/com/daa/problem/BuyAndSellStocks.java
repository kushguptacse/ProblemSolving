package com.daa.problem;

public class BuyAndSellStocks {

	public static void main(String[] args) {
		System.out.println(maxProfit1(new int[] { 7, 1, 5, 3, 6, 4 }));
		System.out.println(maxProfit1(new int[] { 7, 6, 4, 3, 1 }));
		System.out.println(maxProfit2(new int[] { 1, 2, 3, 0, 2 }));
		System.out.println(maxProfit2(new int[] { 7, 1, 5, 3, 6, 4  }));
	}

	/**
	 * Say you have an array for which the ith element is the price of a given stock
	 * on day i.
	 * 
	 * If you were only permitted to complete at most one transaction (i.e., buy one
	 * and sell one share of the stock), design an algorithm to find the maximum
	 * profit.
	 * 
	 * Note that you cannot sell a stock before you buy one.
	 * 
	 * o(n),o(1)
	 * 
	 * @f:off
	 * Example 1:
	 * Input: [7,1,5,3,6,4] 
	 * Output: 5 
	 * Explanation: Buy on day 2 (price = 1) and sell
	 * on day 5 (price = 6), profit = 6-1 = 5. Not 7-1 = 6, as selling price needs
	 * to be larger than buying price. 
	 * 
	 * Example 2:
	 * Input: [7,6,4,3,1]
	 * Output: 0 
	 * Explanation: In this case, no transaction is done, i.e. max profit = 0.
	 * @f:on
	 * 
	 * @param prices
	 * @return max profit
	 */
	public static int maxProfit1(int[] prices) {
		if (prices == null || prices.length < 2) {
			return 0;
		}
		int min = prices[0];
		int maxProfit = 0;
		for (int i = 1; i < prices.length; i++) {
			if (min > prices[i]) {
				min = prices[i];
			}
			if (prices[i] - min > maxProfit) {
				maxProfit = prices[i] - min;
			}
		}
		return maxProfit;
	}

	/**
	 * 
	 * Say you have an array for which the ith element is the price of a given stock
	 * on day i.
	 * 
	 * Design an algorithm to find the maximum profit. You may complete as many
	 * transactions as you like (i.e., buy one and sell one share of the stock
	 * multiple times).
	 * 
	 * Note: You may not engage in multiple transactions at the same time (i.e., you
	 * must sell the stock before you buy again).
	 * 
	 * @f:off
	 * Example 1:
	 * 
	 * Input: [7,1,5,3,6,4] 
	 * Output: 7 
	 * Explanation: Buy on day 2 (price = 1) and sell
	 * on day 3 (price = 5), profit = 5-1 = 4. Then buy on day 4 (price = 3) and
	 * sell on day 5 (price = 6), profit = 6-3 = 3. Example 2:
	 * 
	 * Input: [1,2,3,4,5] 
	 * Output: 4 
	 * Explanation: Buy on day 1 (price = 1) and sell
	 * on day 5 (price = 5), profit = 5-1 = 4. Note that you cannot buy on day 1,
	 * buy on day 2 and sell them later, as you are engaging multiple transactions
	 * at the same time. You must sell before buying again. Example 3:
	 * 
	 * Input: [7,6,4,3,1] 
	 * Output: 0 
	 * Explanation: In this case, no transaction is
	 * done, i.e. max profit = 0.
	 * @f:on
	 * 
	 * @param prices
	 * @return maxprofit
	 */
	public static int maxProfit2(int[] prices) {
		if (prices == null || prices.length < 2) {
			return 0;
		}
		int maxProfit = 0;
		for (int i = 1; i < prices.length; i++) {
			if (prices[i - 1] < prices[i]) {
				maxProfit += prices[i] - prices[i - 1];
			}
		}
		return maxProfit;
	}

}

package com.daa.algo.dp;

/**
 * Given N number of matrix - A1,A2----An And dimension goes like - d0*d1, d1*d2, d2*d3,
 * d3*d4 ------- dn-1 * dn
 * 
 * In this problem we need to find on which combination there will be least number of
 * multiplication.
 * abdul bari and geeks
 * 
 * @author G521885
 *
 */
public class MatrixChainMultiplication {

	public static void main(String[] args) {
		MatrixChainMultiplication obj = new MatrixChainMultiplication();
		int res = obj.printMinProductOrder(new int[] { 40, 20, 30, 10, 30 });
		System.out.println(res);
		res = obj.printMinProductOrder(new int[] { 10, 20, 30, 40, 30 });
		System.out.println(res);
		res = obj.printMinProductOrder(new int[] { 10, 20, 30 });
		System.out.println(res);
		res = obj.printMinProductOrder(new int[] { 1, 2, 3, 4, 3 });
		System.out.println(res);
		res = obj.printMinProductOrder(new int[] { 1, 2, 3, 4 });
		System.out.println(res);

	}

	/**
	 * @f:off
	 * o(n3)
	 * 
	 * Print the brackets order and calculate the minimum multiplication required 
	 * DP - abdul bari strategy applied.
	 * To make logic simple one extra row is used and zero row is not used in logic calculation.
	 * e.g. - 
	 * for 1*2,2*3,3*4
	 * input array will be dim= [1,2,3,4]
	 * and cost matrix will be
	 *    0 1 2 3
	 * 0  0 0 0 0
	 * 1    0 a b
	 * 2      0 c
	 * 3        0
	 * 
	 * Here only a,b,c is calculated
	 *    0 1 2 3
	 * 0  0 0 0 0
	 * 1    0 6 18
	 * 2      0 24
	 * 3		0
	 * 
	 * so, answer is 18
	 * 
	 * @f:on
	 * @param dim
	 * @return minimum multiplication required
	 */
	public int printMinProductOrder(int[] dim) {
		if (dim.length <= 2) {
			return 0;
		}
		int[][] cost = new int[dim.length][dim.length];
		int[][] brac = new int[dim.length][dim.length];
		// loop will serve as difference between i and j for calculating cost
		for (int k = 0; k < dim.length - 1; k++) {
			// it will start calculating cost diagonally and increase at factor of k as per Algo
			for (int i = 1, j = k + i + 1; j < dim.length; j++, i++) {
				cost[i][j] = findMin(i, j, cost, dim, brac);
			}
		}
		System.out.print("Bracket Order: ");
		printBracketOrder(brac, 1, dim.length - 1);
		System.out.println();
		return cost[1][dim.length - 1];
	}

	/**
	 * It evaluates cost as per minimum value starting from i and end at j. it also stores the
	 * Parenthesis position in bracket array.
	 * 
	 * @param i
	 * @param j
	 * @param cost
	 * @param dim
	 * @param brac
	 * @return min cost for i,j location.
	 */
	private int findMin(int i, int j, int[][] cost, int[] dim, int[][] brac) {
		int min = Integer.MAX_VALUE;
		int minK = i;
		for (int k = i; k < j; k++) {
			int cur = cost[i][k] + cost[k + 1][j] + dim[i - 1] * dim[k] * dim[j];
			if (cur < min) {
				min = cur;
				minK = k;
			}
		}
		brac[i][j] = minK;
		return min;
	}

	/**
	 * takes bracket array as argument and print them using recursion. o(n3)
	 * 
	 * @param brac
	 * @param i
	 * @param j
	 */
	private void printBracketOrder(int[][] brac, int i, int j) {
		if (i == j) {
			System.out.print((char) ('A' + i - 1));
		} else {
			System.out.print("(");
			printBracketOrder(brac, i, brac[i][j]);
			printBracketOrder(brac, brac[i][j] + 1, j);
			System.out.print(")");
		}
	}
}

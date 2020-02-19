package com.daa.array;

public class Array2D {

	public static void main(String[] args) {
		int res = countNegatives(
				new int[][] { { 4, 3, 2, -1 }, { 3, 2, 1, -1 }, { 1, 1, -1, -2 }, { -1, -1, -2, -3 } });
		System.out.println("Number of negative number are:" + res);
		printData(matrixProduct(new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } },
				new int[][] { { 2, 3, 4 }, { 5, 6, 7 }, { 8, 9, 1 } }, 3, 3));
		System.out.println("---");
		printData(matrixProduct(new int[][] { { 1, 2 }, { 3, 4 } }, new int[][] { { 1, 1 }, { 1, 1 } }, 2, 2));
		int mat1[][] = { { 1, 1, 1, 1 }, { 2, 2, 2, 2 }, { 3, 3, 3, 3 }, { 4, 4, 4, 4 } };
		int mat2[][] = { { 1, 1, 1, 1 }, { 2, 2, 2, 2 }, { 3, 3, 3, 3 }, { 4, 4, 4, 4 } };
		printData(matrixProduct(mat1, mat2, 4, 4));
		System.out.println("-------------------");
		int[][] firstMatrix = { { 3, -2, 5 }, { 3, 0, 4 } };
		int[][] secondMatrix = { { 2, 3 }, { -9, 0 }, { 0, 4 } };
		printData(matrixProduct(firstMatrix, secondMatrix, 3, 2));
	}

	/***
	 * Given a m * n matrix grid which is sorted in non-increasing order both
	 * row-wise and column-wise.
	 * 
	 * Return the number of negative numbers in grid.
	 * 
	 * Input: grid = [[4,3,2,-1],[3,2,1,-1],[1,1,-1,-2],[-1,-1,-2,-3]] Output: 8
	 * 
	 * @f:off
	 * 1.Use binary search approach for every individual array (row wise). 
	 * 2.if mid number is negative ignore all right and try from start to mid-1.
	 * 3.if mid is positive check from mid+1 to end
	 * 4.after loop terminates start will reach to the point where last negative number is.
	 * 5.to use column decreasing property we update end to start value. so that we compare from 0 to start for second row
	 * @f:on
	 * 
	 * @param grid
	 * @return count
	 */
	public static int countNegatives(int[][] grid) {
		int c = 0;
		for (int i = 0, end = grid[i].length - 1; i < grid.length; i++) {
			int start = 0;
			while (start <= end) {
				int mid = (start + end) / 2;
				if (grid[i][mid] < 0) {
					end = mid - 1;
				} else {
					start = mid + 1;
				}
			}
			c = c + grid[i].length - start;
			end = start - 1;
		}
		return c;
	}

	/**
	 * print 2D array data.
	 * 
	 * @param array
	 */
	public static void printData(int[][] c) {
		for (int i = 0; i < c.length; i++) {
			for (int j = 0; j < c[i].length; j++) {
				System.out.print(c[i][j] + " ");
			}
			System.out.println();
		}

	}

	/**
	 * o(n3)
	 * 
	 * @param arr
	 * @param brr
	 * @param arrColumn
	 * @param brrColumn
	 * @return result of two matrix
	 */
	public static int[][] matrixProduct(int[][] arr, int[][] brr, int arrColumn, int brrColumn) {
		int[][] res = new int[arr.length][brrColumn];
		if (arrColumn != brr.length) {
			return res;
		}

		for (int i = 0; i < arr.length; i++) {
			for (int k = 0; k < brrColumn; k++) {
				for (int j = 0; j < brr.length; j++) {
					res[i][k] = res[i][k] + arr[i][j] * brr[j][k];
				}
			}
		}
		return res;
	}

}

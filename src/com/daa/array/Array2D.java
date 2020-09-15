package com.daa.array;

import java.util.ArrayList;
import java.util.List;

import com.daa.math.MathUtil;

public class Array2D {

	public static void main(String[] args) {
		System.out.println(searchMatrix(new int[][] { { 1, 3, 5, 7 }, { 10, 11, 16, 20 }, { 23, 30, 34, 50 } }, 3));
		int[][] res1 = intervalIntersection(new int[][] { { 0, 2 }, { 5, 10 }, { 13, 23 }, { 24, 25 } },
				new int[][] { { 1, 5 }, { 8, 12 }, { 15, 24 }, { 25, 26 } });
		printData(res1);
		int c = numIslands(new char[][] { { '1', '1', '0', '0', '0' }, { '1', '1', '0', '0', '0' },
				{ '0', '0', '1', '0', '0' }, { '0', '0', '0', '1', '1' } });
		System.out.println(c);
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

	/**
	 * Given n and m which are the dimensions of a matrix initialized by zeros and
	 * given an array indices where indices[i] = [ri, ci]. For each pair of [ri, ci]
	 * you have to increment all cells in row ri and column ci by 1.
	 * 
	 * Return the number of cells with odd values in the matrix after applying the
	 * increment to all indices.
	 * 
	 * 
	 * Input: n = 2, m = 3, indices = [[0,1],[1,1]]
	 * 
	 * Output: 6
	 * 
	 * Explanation: Initial matrix = [[0,0,0],[0,0,0]]. After applying first
	 * increment it becomes [[1,2,1],[0,1,0]]. The final matrix will be
	 * [[1,3,1],[1,3,1]] which contains 6 odd numbers.
	 * 
	 * @param n
	 * @param m
	 * @param indices
	 * @return count odd elements
	 */
	public int oddCells(int n, int m, int[][] indices) {
		boolean[] rows = new boolean[n];
		boolean[] cols = new boolean[m];
		for (int[] ind : indices) {
			rows[ind[0]] = !rows[ind[0]];
			cols[ind[1]] = !cols[ind[1]];
		}
		int res = 0;
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < m; c++) {
				if (rows[r] ^ cols[c])
					res++;
			}
		}
		return res;
	}

	public static int[][] intervalIntersection(int[][] arr, int[][] brr) {
		List<int[]> output = new ArrayList<>();
		int i = 0;
		int j = 0;
		while (i < arr.length && j < brr.length) {
			int start = Math.max(arr[i][0], brr[j][0]);
			int end = Math.min(arr[i][1], brr[j][1]);
			if (start <= end) {
				output.add(new int[] { start, end });
			}
			if (arr[i][1] <= brr[j][1]) {
				i++;
			} else {
				j++;
			}
		}
		return output.toArray(new int[output.size()][2]);
	}

	/**
	 * search element in a sorted 2-d matrix. Integers in each row are sorted from
	 * left to right. The first integer of each row is greater than the last integer
	 * of the previous row.
	 * 
	 * @param matrix
	 * @param target
	 * @return
	 */
	public static boolean searchMatrix(int[][] matrix, int target) {
		for (int i = 0; i < matrix.length; i++) {
			int start = 0;
			int end = matrix[0].length * matrix.length - 1;
			while (start <= end) {
				int midIndex = start + (end - start) / 2;
				int mid = matrix[midIndex / matrix[0].length][midIndex % matrix[0].length];
				if (mid == target) {
					return true;
				} else if (mid < target) {
					start = midIndex + 1;
				} else {
					end = midIndex - 1;
				}
			}
		}
		return false;
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

	/**
	 * Given a m x n grid filled with non-negative numbers, find a path from top
	 * left to bottom right which minimizes the sum of all numbers along its path.
	 * 
	 * Note: You can only move either down or right at any point in time.
	 * 
	 * Example:
	 * 
	 * Input: [ [1,3,1], [1,5,1], [4,2,1] ]
	 * 
	 * Output: 7 Explanation: Because the path 1→3→1→1→1 minimizes the sum.
	 * 
	 * @param grid
	 * @return minimum path sum
	 */
	public static int minPathSum(int[][] grid) {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				int left = i > 0 ? grid[i - 1][j] : Integer.MAX_VALUE;
				int top = j > 0 ? grid[i][j - 1] : Integer.MAX_VALUE;
				if (i == 0 && j == 0) {
					continue;
				}
				grid[i][j] += MathUtil.min(left, top);
			}
		}
		return grid[grid.length - 1][grid[0].length - 1];
	}

	/**
	 * Given a 2d grid map of '1's (land) and '0's (water), count the number of
	 * islands. An island is surrounded by water and is formed by connecting
	 * adjacent lands horizontally or vertically. You may assume all four edges of
	 * the grid are all surrounded by water.
	 * 
	 * Example 1:
	 * 
	 * Input: 11110 11010 11000 00000
	 * 
	 * Output: 1
	 * 
	 * @param grid
	 * @return number of island
	 */
	public static int numIslands(char[][] grid) {
		int c = 0;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j] == '1') {
					visitIsland(grid, i, j);
					c++;
				}
			}
		}
		return c;
	}

	private static void visitIsland(char[][] grid, int i, int j) {
		if (grid[i][j] == '1') {
			grid[i][j] = 'V';
			if (i - 1 >= 0) {
				visitIsland(grid, i - 1, j);
			}
			if (i + 1 < grid.length) {
				visitIsland(grid, i + 1, j);
			}
			if (j - 1 >= 0) {
				visitIsland(grid, i, j - 1);
			}
			if (j + 1 < grid[i].length) {
				visitIsland(grid, i, j + 1);
			}
		}
	}

	/**
	 * 
	 * An image is represented by a 2-D array of integers, each integer representing
	 * the pixel value of the image (from 0 to 65535).
	 * 
	 * Given a coordinate (sr, sc) representing the starting pixel (row and column)
	 * of the flood fill, and a pixel value newColor, "flood fill" the image.
	 * 
	 * To perform a "flood fill", consider the starting pixel, plus any pixels
	 * connected 4-directionally to the starting pixel of the same color as the
	 * starting pixel, plus any pixels connected 4-directionally to those pixels
	 * (also with the same color as the starting pixel), and so on. Replace the
	 * color of all of the aforementioned pixels with the newColor.
	 * 
	 * At the end, return the modified image.
	 * 
	 * Example 1:
	 * 
	 * Input: image = [[1,1,1],[1,1,0],[1,0,1]] sr = 1, sc = 1, newColor= 2
	 * 
	 * Output: [[2,2,2],[2,2,0],[2,0,1]]
	 * 
	 * Explanation: From the center of the image (with position (sr, sc) = (1, 1)),
	 * all pixels connected by a path of the same color as the starting pixel are
	 * colored with the new color.
	 * 
	 * @param image
	 * @param sr
	 * @param sc
	 * @param newColor
	 * @return array after filling color
	 */
	public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
		floodFill2(image, sr, sc, newColor, image[sr][sc]);
		return image;
	}

	private void floodFill2(int[][] image, int sr, int sc, int newColor, int oldColor) {
		if (image[sr][sc] != oldColor || image[sr][sc] == newColor) {
			return;
		}
		image[sr][sc] = newColor;
		if (sr - 1 >= 0) {
			floodFill2(image, sr - 1, sc, newColor, oldColor);
		}
		if (sr + 1 < image.length) {
			floodFill2(image, sr + 1, sc, newColor, oldColor);
		}
		if (sc - 1 >= 0) {
			floodFill2(image, sr, sc - 1, newColor, oldColor);
		}
		if (sc + 1 < image[0].length) {
			floodFill2(image, sr, sc + 1, newColor, oldColor);
		}
	}

	/**
	 * Two images A and B are given, represented as binary, square matrices of the
	 * same size. (A binary matrix has only 0s and 1s as values.)
	 * 
	 * We translate one image however we choose (sliding it left, right, up, or down
	 * any number of units), and place it on top of the other image. After, the
	 * overlap of this translation return max number of positions that have a 1 in
	 * both images overlapping.
	 * 
	 * Example 1:
	 * 
	 * Input: A = [[1,1,0], [0,1,0], [0,1,0]] B = [[0,0,0], [0,1,1], [0,0,1]]
	 * Output: 3
	 * 
	 * Explanation: We slide A to right by 1 unit and down by 1 unit.
	 * 
	 * Notes:
	 * 
	 * 1 <= A.length = A[0].length = B.length = B[0].length <= 30 0 <= A[i][j],
	 * B[i][j] <= 1
	 * 
	 * @param a
	 * @param b
	 * @return largest overlap possible
	 */
	public int largestOverlap(int[][] a, int[][] b) {
		int max = 0;
		for (int x = 1 - a.length; x < a.length; x++) {
			for (int y = 1 - a[0].length; y < a.length; y++) {
				max = Math.max(max, overlapCount(a, b, x, y));
			}
		}
		return max;
	}

	private int overlapCount(int[][] a, int[][] b, int x, int y) {
		int count = 0;
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[0].length; j++) {
				if (i - x >= 0 && j - y >= 0 && i - x < a.length && j - y < a[0].length) {
					if (a[i][j] == b[i - x][j - y] && a[i][j] == 1) {
						count++;
					}
				}
			}
		}
		return count;
	}
}
package com.daa.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.daa.math.MathUtil;

public class Array2D {

	public static void main(String[] args) {
		System.out.println(new Array2D().canFormArray(new int[] { 49, 18, 16 }, new int[][] { { 16, 18, 49 } }));
//		int arr[][] = { { 1, 1 }, { 1, 0 } };
//		new Array2D().gameOfLife(arr);
//		printData(arr);
//		int a2[][] = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 }, { 10, 11, 12 } };
//		System.out.println(Arrays.toString(new Array2D().diagonalTraversal(a2)));
//		printData(new Array2D().spiralOrderGenerate(3));
//		int a[][] = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 16 } };
//		System.out.println(new Array2D().spiralOrder(a));
//		System.out.println(searchMatrix(new int[][] { { 1, 3, 5, 7 }, { 10, 11, 16, 20 }, { 23, 30, 34, 50 } }, 3));
//		int[][] res1 = intervalIntersection(new int[][] { { 0, 2 }, { 5, 10 }, { 13, 23 }, { 24, 25 } },
//				new int[][] { { 1, 5 }, { 8, 12 }, { 15, 24 }, { 25, 26 } });
//		printData(res1);
//		int c = numIslands(new char[][] { { '1', '1', '0', '0', '0' }, { '1', '1', '0', '0', '0' },
//				{ '0', '0', '1', '0', '0' }, { '0', '0', '0', '1', '1' } });
//		System.out.println(c);
//		int res = countNegatives(
//				new int[][] { { 4, 3, 2, -1 }, { 3, 2, 1, -1 }, { 1, 1, -1, -2 }, { -1, -1, -2, -3 } });
//		System.out.println("Number of negative number are:" + res);
//		printData(matrixProduct(new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } },
//				new int[][] { { 2, 3, 4 }, { 5, 6, 7 }, { 8, 9, 1 } }, 3, 3));
//		System.out.println("---");
//		printData(matrixProduct(new int[][] { { 1, 2 }, { 3, 4 } }, new int[][] { { 1, 1 }, { 1, 1 } }, 2, 2));
//		int mat1[][] = { { 1, 1, 1, 1 }, { 2, 2, 2, 2 }, { 3, 3, 3, 3 }, { 4, 4, 4, 4 } };
//		int mat2[][] = { { 1, 1, 1, 1 }, { 2, 2, 2, 2 }, { 3, 3, 3, 3 }, { 4, 4, 4, 4 } };
//		printData(matrixProduct(mat1, mat2, 4, 4));
//		System.out.println("-------------------");
//		int[][] firstMatrix = { { 3, -2, 5 }, { 3, 0, 4 } };
//		int[][] secondMatrix = { { 2, 3 }, { -9, 0 }, { 0, 4 } };
//		printData(matrixProduct(firstMatrix, secondMatrix, 3, 2));
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
	 * print spiral order traversal of 2d matrix
	 * 
	 * @param matrix
	 * @return list of data arranged in spiral order
	 */
	public List<Integer> spiralOrder(int[][] matrix) {
		List<Integer> list = new ArrayList<>();
		int rs = 0;
		int re = matrix.length - 1;
		int cs = 0;
		int ce = matrix[0].length - 1;
		while (rs <= re && cs <= ce) {
			// go right
			for (int i = cs; i <= ce; i++) {
				list.add(matrix[rs][i]);
			}
			rs++;
			// go down
			for (int i = rs; i <= re; i++) {
				list.add(matrix[i][ce]);
			}
			ce--;
			if (rs <= re) {
				// go left
				for (int i = ce; i >= cs; i--) {
					list.add(matrix[re][i]);
				}
				re--;
			}
			if (cs <= ce) {
				// go up
				for (int i = re; i >= rs; i--) {
					list.add(matrix[i][cs]);
				}
				cs++;
			}
		}
		return list;
	}

	public int[] diagonalTraversal(int[][] matrix) {
		if (matrix == null || matrix.length == 0) {
			return new int[0];
		}
		Map<Integer, List<Integer>> map = new HashMap<>();
		int[] op = new int[matrix.length * matrix[0].length];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				map.computeIfAbsent(i + j, x -> new ArrayList<>()).add(matrix[i][j]);
			}
		}
		int c = 0;
		for (int i = 0; i < matrix.length + matrix[0].length - 1; i++) {
			List<Integer> list = map.get(i);
			if (i % 2 == 0) {
				for (int j = list.size() - 1; j >= 0; j--) {
					op[c++] = list.get(j);
				}
			} else {
				for (int item : list) {
					op[c++] = item;
				}
			}
		}
		return op;
	}

	public int[] diagonalTraversal2(int[][] matrix) {
		if (matrix == null || matrix.length == 0) {
			return new int[0];
		}
		int n = matrix.length;
		int row = 0;
		int col = 0;
		int m = matrix[0].length;
		int c = 0;
		int[] op = new int[n * m];
		while (c < n * m) {
			op[c++] = matrix[row][col];
			if ((row + col) % 2 == 0) { // in even case go up
				if (col == m - 1) {
					row++;
				} else if (row == 0) {
					col++;
				} else {
					row--;
					col++;
				}
			} else { // in odd case go down
				if (row == n - 1) {
					col++;
				} else if (col == 0) {
					row++;
				} else {
					row++;
					col--;
				}
			}
		}
		return op;
	}

	/**
	 * The board is made up of an m x n grid of cells, where each cell has an
	 * initial state: live (represented by a 1) or dead (represented by a 0). Each
	 * cell interacts with its eight neighbors (horizontal, vertical, diagonal)
	 * using the following four rules (taken from the above Wikipedia article):
	 * 
	 * Any live cell with fewer than two live neighbors dies as if caused by
	 * under-population. Any live cell with two or three live neighbors lives on to
	 * the next generation. Any live cell with more than three live neighbors dies,
	 * as if by over-population. Any dead cell with exactly three live neighbors
	 * becomes a live cell, as if by reproduction. The next state is created by
	 * applying the above rules simultaneously to every cell in the current state,
	 * where births and deaths occur simultaneously. Given the current state of the
	 * m x n grid board, return the next state.
	 * 
	 * 
	 * Example 1:
	 * 
	 * Input: board = [[0,1,0],[0,0,1],[1,1,1],[0,0,0]]
	 * 
	 * Output: [[0,0,0],[1,0,1],[0,1,1],[0,1,0]]
	 * 
	 * @param board
	 */
	public void gameOfLife(int[][] board) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				int count = 0;
				if (i - 1 >= 0 && j - 1 >= 0 && Math.abs(board[i - 1][j - 1]) == 1) {
					count++;
				}
				if (i - 1 >= 0 && Math.abs(board[i - 1][j]) == 1) {
					count++;
				}
				if (i - 1 >= 0 && j + 1 < board[0].length && Math.abs(board[i - 1][j + 1]) == 1) {
					count++;
				}
				if (j - 1 >= 0 && Math.abs(board[i][j - 1]) == 1) {
					count++;
				}
				if (j + 1 < board[0].length && Math.abs(board[i][j + 1]) == 1) {
					count++;
				}
				if (i + 1 < board.length && j - 1 >= 0 && Math.abs(board[i + 1][j - 1]) == 1) {
					count++;
				}
				if (i + 1 < board.length && Math.abs(board[i + 1][j]) == 1) {
					count++;
				}
				if (i + 1 < board.length && j + 1 < board[0].length && Math.abs(board[i + 1][j + 1]) == 1) {
					count++;
				}
				if (board[i][j] == 0 && count == 3) {
					board[i][j] = 2;
				} else if (board[i][j] == 1 && (count < 2 || count > 3)) {
					board[i][j] = -1;
				}

			}
		}
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] == 2) {
					board[i][j] = 1;
				} else if (board[i][j] == -1) {
					board[i][j] = 0;
				}
			}
		}

	}

	/**
	 * given a value n. print the n*n array where element starts from 1 and goes
	 * towards n in spiral order.
	 * 
	 * @param n
	 * @return 2d matrix
	 */
	public int[][] spiralOrderGenerate(int n) {
		int[][] matrix = new int[n][n];
		int rs = 0;
		int re = matrix.length - 1;
		int cs = 0;
		int ce = matrix[0].length - 1;
		n = 0;
		while (rs <= re && cs <= ce) {
			// go right
			for (int i = cs; i <= ce; i++) {
				matrix[rs][i] = n++;
			}
			rs++;
			// go down
			for (int i = rs; i <= re; i++) {
				matrix[i][ce] = n++;
			}
			ce--;
			// go left
			for (int i = ce; i >= cs; i--) {
				matrix[re][i] = n++;
			}
			re--;
			// go up
			for (int i = re; i >= rs; i--) {
				matrix[i][cs] = n++;
			}
			cs++;
		}
		return matrix;

	}

	public boolean canFormArray(int[] arr, int[][] pieces) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < arr.length; i++) {
			map.put(arr[i], i);
		}
		for (int i = 0; i < pieces.length; i++) {
			int pre = -1;
			for (int j = 0; j < pieces[i].length; j++) {
				int index = map.getOrDefault(pieces[i][j], -1);
				if (index == -1 || (pre != -1 && index != pre + 1)) {
					return false;
				}
				pre = index;
			}
		}
		return true;
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
	 * @f:off 1.Use binary search approach for every individual array (row wise).
	 *        2.if mid number is negative ignore all right and try from start to
	 *        mid-1. 3.if mid is positive check from mid+1 to end 4.after loop
	 *        terminates start will reach to the point where last negative number
	 *        is. 5.to use column decreasing property we update end to start value.
	 *        so that we compare from 0 to start for second row
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
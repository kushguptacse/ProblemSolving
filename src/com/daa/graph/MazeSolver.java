package com.daa.graph;

public class MazeSolver {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		//@f:off
		int[][] mazeArr = { 
				{ 1, 1, 1, 1, 1, 1, 1 },
				{ 1, 1, 0, 1, 1, 2, 1 }, 
				{ 1, 1, 0, 0, 0, 0, 1 }, 
				{ 1, 1, 0, 1, 1, 1, 1 }, 
				{ 1, 1, 0, 0, 1, 1, 1 },
				{ 1, 1, 1, 1, 3, 1, 1 }, 
				{ 1, 1, 1, 1, 1, 1, 1 } 
			};
		//@f:on

		boolean[][] visited = new boolean[mazeArr.length][mazeArr.length];
		try {
			solveMaze(mazeArr, 1, 5, visited);
			System.out.println("No Solution exists !!");
		} catch (RuntimeException e) {
			System.out.println("Solved !!");
		}
	}

	/**
	 * 
	 * @param mazeArr
	 * @param row
	 * @param column
	 * @param visited
	 */
	private static void solveMaze(int[][] mazeArr, int row, int column, boolean[][] visited) {
		if (row < 0 || row >= mazeArr.length) {
			return;
		}
		if (column < 0 || column >= mazeArr.length) {
			return;
		}
		if (visited[row][column]) {
			return;
		}
		System.out.println("Visiting : " + row + "," + column);
		if (mazeArr[row][column] == 3) {
			throw new RuntimeException();
		}
		if (mazeArr[row][column] == 1) {
			return;
		}

		visited[row][column] = true;
		solveMaze(mazeArr, row + 1, column, visited);//going down
		solveMaze(mazeArr, row, column + 1, visited);//going right
		solveMaze(mazeArr, row, column - 1, visited);//going left
		solveMaze(mazeArr, row - 1, column, visited);//going up
	}

}

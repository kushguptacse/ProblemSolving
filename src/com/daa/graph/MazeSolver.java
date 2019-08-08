package com.daa.graph;

/**
 * We have to see whether Rat can go from source to destination.
 * @f:off
 * 
 * Approach - 
 *  
 * we represent maze with 2D matrix array. where it can have below possible values-
 * source has value = 2 
 * destination has value = 3
 * 0 indicates a path where we can go and 1 indicates wall.
 * so while searching path from start position we check if we encounter 3. if yes. path exists.
 * and if we found 1 backtrack and change path. if we found 0 move further.
 * at any point we have four choices to make- go down,right,left,up.
 * at last if we have visited entire matrix and we cannot go to 3. No solution exists.
 * @f:on
 * @author G521885
 *
 */
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
				{ 1, 1, 0, 0, 0, 1, 1 },
				{ 1, 1, 1, 1, 3, 1, 1 }, 
				{ 1, 1, 1, 1, 1, 1, 1 } 
			};
		//@f:on

		boolean[][] visited = new boolean[mazeArr.length][mazeArr.length];
		if (solveMaze(mazeArr, 1, 5, visited)) {
			System.out.println("Solved !!");
		} else {
			System.out.println("No Solution exists !!");
		}
	}

	/**
	 * 
	 * @param mazeArr
	 * @param row
	 * @param column
	 * @param visited
	 */
	private static boolean solveMaze(int[][] mazeArr, int row, int column, boolean[][] visited) {
		if (row < 0 || row >= mazeArr.length) {
			return false;
		}
		if (column < 0 || column >= mazeArr.length || visited[row][column]) {
			return false;
		}
		System.out.println("Visiting : " + row + "," + column);
		if (mazeArr[row][column] == 3) {
			return true;
		} else if (mazeArr[row][column] == 1) {
			return false;
		}

		visited[row][column] = true;
		return solveMaze(mazeArr, row + 1, column, visited) || solveMaze(mazeArr, row, column + 1, visited) || solveMaze(mazeArr, row, column - 1, visited)
				|| solveMaze(mazeArr, row - 1, column, visited);
	}

}

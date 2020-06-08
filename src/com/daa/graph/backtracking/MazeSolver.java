package com.daa.graph.backtracking;

/**
 * A Maze is given as N*N binary matrix of blocks where source block is the
 * upper left most block i.e., maze[0][0] and destination block is lower
 * rightmost block i.e., maze[N-1][N-1]. A rat starts from source and has to
 * reach the destination. The rat can move only in two directions: forward and
 * down.
 * 
 * In the maze matrix, 0 means the block is a dead end and 1 means the block can
 * be used in the path from source to destination. Note that this is a simple
 * version of the typical Maze problem. For example, a more complex version can
 * be that the rat can move in 4 directions and a more complex version can be
 * with a limited number of moves.
 *
 */
public class MazeSolver {

	public static void main(String[] args) {
		MazeSolver rat = new MazeSolver();
		int maze[][] = { { 1, 1, 1, 1 }, { 0, 1, 1, 0 }, { 0, 0, 1, 1 }, { 0, 1, 0, 1 } };
		System.out.println("orignal maze");
		rat.printMatrix(maze);
		int[][] sol = new int[maze.length][maze.length];
		boolean res = rat.solveMaze(maze, sol);
		System.out.println("solution exists : " + res);
		if (res) {
			rat.printMatrix(sol);
		}
	}

	public boolean solveMaze(int maze[][], int[][] sol) {
		return solveMazeUtil(maze, 0, 0, sol);
	}

	private boolean solveMazeUtil(int maze[][], int i, int j, int[][] sol) {
		if (i == maze.length - 1 && j == maze.length - 1) {
			sol[i][j] = 1;
			return true;
		}
		if (i >= maze.length || j >= maze.length || maze[i][j] == 0) {
			return false;
		}
		sol[i][j] = 1;
		boolean res = solveMazeUtil(maze, i, j + 1, sol) || solveMazeUtil(maze, i + 1, j, sol);
		if (!res) {
			sol[i][j] = 0;
		}
		return res;
	}

	
	private void printMatrix(int[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				System.out.print(" " + arr[i][j]);
			}
			System.out.println();
		}
	}

}

package com.daa.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class GraphPractice {

	public static void main(String[] args) {
		DijkstraAlgo g = new DijkstraAlgo(5);
		g.addEdge(0, 1, 9);
		g.addEdge(0, 2, 6);
		g.addEdge(0, 3, 5);
		g.addEdge(0, 4, 3);
		g.addEdge(2, 1, 2);
		g.addEdge(2, 3, 4);
		g.shortestPath(0);

		GraphPractice p = new GraphPractice();
		p.findCheapestPrice(5,
				new int[][] { { 0, 1, 9 }, { 0, 2, 6 }, { 0, 3, 5 }, { 0, 4, 3 }, { 2, 1, 2 }, { 2, 3, 4 } }, 0, 2, 1);

	}

	/**
	 * Given a 2D board containing 'X' and 'O' (the letter O), capture all regions
	 * surrounded by 'X'.
	 * 
	 * A region is captured by flipping all 'O's into 'X's in that surrounded
	 * region.
	 * 
	 * leetcode
	 * 
	 * Surrounded regions shouldn’t be on the border, which means that any 'O' on
	 * the border of the board are not flipped to 'X'. Any 'O' that is not on the
	 * border and it is not connected to an 'O' on the border will be flipped to
	 * 'X'. Two cells are connected if they are adjacent cells connected
	 * horizontally or vertically.
	 * 
	 * @f:off
	 * input-
	 * XXXX
	 * XOOX
	 * XXOX
	 * XOXX
	 * 
	 * output- 
	 * XXXX
	 * XXXX
	 * XXXX
	 * XOXX
	 * 
	 * @f:on
	 * @param board
	 */
	public void captureAllRegionsExceptBoundary(char[][] board) {
		if (board.length == 0 || board[0].length == 0) {
			return;
		}
		for (int i = 0; i < board[0].length; i++) {
			if (board[0][i] == 'O') {
				dfsBoundaryUtil(board, 0, i);
			}
			if (board[board.length - 1][i] == 'O') {
				dfsBoundaryUtil(board, board.length - 1, i);
			}

		}
		for (int i = 1; i < board.length; i++) {
			if (board[i][0] == 'O') {
				dfsBoundaryUtil(board, i, 0);
			}
			if (board[i][board[0].length - 1] == 'O') {
				dfsBoundaryUtil(board, i, board[0].length - 1);
			}
		}
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] == 'v') {
					board[i][j] = 'O';
				} else if (board[i][j] == 'O') {
					board[i][j] = 'X';
				}
			}
		}
	}

	private void dfsBoundaryUtil(char[][] board, int i, int j) {
		if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != 'O') {
			return;
		}
		board[i][j] = 'v';
		dfsBoundaryUtil(board, i - 1, j);
		dfsBoundaryUtil(board, i + 1, j);
		dfsBoundaryUtil(board, i, j - 1);
		dfsBoundaryUtil(board, i, j + 1);
	}

	/**
	 * 
	 * @param n
	 * @param flights
	 * @param src
	 * @param dst
	 * @param K
	 * @return
	 */
	public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
		List<int[]>[] adjArray = prepareAdjGraph(n, flights);
		PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[1] - b[1]);
		minHeap.add(new int[] { src, 0, k + 1 });
		while (!minHeap.isEmpty()) {
			int[] edgeSrc = minHeap.poll();
			if (edgeSrc[0] == dst) {
				return edgeSrc[1];
			}
			if (edgeSrc[2] > 0) {
				for (int[] j : adjArray[edgeSrc[0]]) {
					minHeap.add(new int[] { j[1], j[2] + edgeSrc[1], edgeSrc[2] - 1 });
				}
			}
		}
		return -1;
	}

	private List<int[]>[] prepareAdjGraph(int n, int[][] array) {
		List<int[]>[] adjArray = new ArrayList[n];
		for (int i = 0; i < n; i++) {
			adjArray[i] = new ArrayList<>();
		}
		for (int i = 0; i < array.length; i++) {
			adjArray[array[i][0]].add(array[i]);
		}
		return adjArray;
	}

}

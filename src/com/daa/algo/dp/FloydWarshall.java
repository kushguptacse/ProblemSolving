package com.daa.algo.dp;

/**
 * @f:off
 * Dynamic Programming
 * 
 * Find the shortest path between all the pairs of vertex in the graph.
 * We can also apply Dijkstra algorithm in a loop by which you can pass all the nodes as source.
 * for adjacency matrix implementation it will take o(n^3). 
 * FloydWarshall also take o(n3) time complexity but it can handle negative weight.
 * 
 * @f:on
 */
public class FloydWarshall {

	private int[][] matrix;
	private int noOfVertices;

	public static void main(String[] args) {
		FloydWarshall fw = new FloydWarshall(4);
		fw.matrix = new int[][] { { 0, 5, Integer.MAX_VALUE, 10 }, { Integer.MAX_VALUE, 0, 3, Integer.MAX_VALUE }, { Integer.MAX_VALUE, Integer.MAX_VALUE, 0, 1 },
				{ Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 0 } };
		fw.allPairShortestPath();
		FloydWarshall fw1 = new FloydWarshall(4);
		fw1.matrix = new int[][] { { 0, Integer.MAX_VALUE, 3, Integer.MAX_VALUE }, { 2, 0, Integer.MAX_VALUE, Integer.MAX_VALUE }, { Integer.MAX_VALUE, 7, 0, 1 },
				{ 6, Integer.MAX_VALUE, Integer.MAX_VALUE, 0 } };
		fw1.allPairShortestPath();

	}

	public FloydWarshall(int v) {
		noOfVertices = v;
		matrix = new int[v][];
	}

	/**
	 * @f:off
	 * Dynamic programming - all Pair Shortest Path
	 * O(n^3)
	 * @f:on
	 */
	public void allPairShortestPath() {
		for (int k = 0; k < noOfVertices; k++) {
			for (int i = 0; i < noOfVertices; i++) {
				for (int j = 0; j < noOfVertices; j++) {
					if (matrix[i][k] != Integer.MAX_VALUE && matrix[k][j] != Integer.MAX_VALUE && matrix[i][j] > matrix[i][k] + matrix[k][j]) {
						matrix[i][j] = matrix[i][k] + matrix[k][j];
					}
				}
			}
		}

		print();
	}

	/**
	 * print the matrix data. o(n2)
	 */
	private void print() {
		System.out.println("Shortest path matrix : ");
		for (int i = 0; i < noOfVertices; i++) {
			for (int j = 0; j < noOfVertices; j++) {
				if (matrix[i][j] == Integer.MAX_VALUE) {
					System.out.print("INF ");
				} else {
					System.out.print(matrix[i][j] + "   ");
				}
			}
			System.out.println();
		}
	}

}

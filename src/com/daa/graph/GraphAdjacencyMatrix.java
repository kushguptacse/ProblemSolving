package com.daa.graph;

/**
 * Graph adjacency matrix implementation of graph -
 * 
 * @f:off
 * prepare v*v matrix where v is the number of vertices.
 * store true if that vertex is connected else false.
 * 
 * for e.g. - 
 * if 0 vertex is connected to 1 vertex. set array[0][1]=true;
 * 
 * input- 
 * 0 - 1\
 * | / | 2
 * 4 - 3/
 * 
 * output-
 * 0 1 0 0 1
 * 1 0 1 1 1 
 * 0 1 0 1 0
 * 0 1 1 0 1
 * 1 1 0 1 0
 * @f:on
 * 
 * cons: irrespective of how many number of edges are there. it will take memory space - o(n2)
 * pros: it will always take o(1) time to add,remove and find the edge.
 * 
 */
public class GraphAdjacencyMatrix {

	private final int noOfVertices;

	private final boolean[][] matrix;

	/**
	 * 
	 */
	public GraphAdjacencyMatrix(int v) {
		this.noOfVertices = v;
		matrix = new boolean[noOfVertices][noOfVertices];
	}

	/**
	 * take two vertices and add it to matrix. if i and j is outside the size. it will ignore
	 * edge. take o(1) time
	 * 
	 * @param i
	 * @param j
	 */
	public void addEdge(int i, int j) {
		if (i >= 0 && j >= 0 && i < noOfVertices && j < noOfVertices) {
			matrix[i][j] = true;
			matrix[j][i] = true;
		}
	}

	/**
	 * if vertices are invalid return false else will remove the edges from i to j and j to i.
	 * o(1)
	 * 
	 * @param i
	 * @param j
	 * @return true if edge removed
	 */
	public boolean removeEdge(int i, int j) {
		if (i >= 0 && j >= 0 && i < noOfVertices && j < noOfVertices) {
			matrix[i][j] = false;
			matrix[j][i] = false;
			return true;
		}
		return false;
	}

	/**
	 * check if there is edge between two vertices. if outside range index is passed false is
	 * returned. o(1)
	 * 
	 * @param i
	 * @param j
	 * @return true if there is edge between two vertices.
	 */
	public boolean isEdge(int i, int j) {
		if (i >= 0 && j >= 0 && i < noOfVertices && j < noOfVertices) {
			return matrix[i][j];
		}
		return false;
	}

	/**
	 * print the data
	 */
	public void print() {
		System.out.println("Adjacency Matrix data -");
		for (int i = 0; i < noOfVertices; i++) {
			for (int j = 0; j < noOfVertices; j++) {
				System.out.print(" " + (matrix[i][j] ? 1 : 0));
			}
			System.out.println();
		}
	}

}

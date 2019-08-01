package com.daa.graph;

import java.util.Queue;
import java.util.Deque;
import java.util.LinkedList;

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
public class GraphAdjacencyMatrix implements Graph {

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
	@Override
	public void addEdge(int i, int j) {
		if (i >= 0 && j >= 0 && i < noOfVertices && j < noOfVertices) {
			matrix[i][j] = true;
			// un-comment it for un-directed graph.
			matrix[j][i] = true;
		}
	}

	/**
	 * Breadth First Search.
	 * 
	 * first all the adjacent vertex traversed then we move forward. it is just like
	 * level-order traversal of binary tree.
	 * 
	 * @param v - source node
	 */
	@Override
	public void bfs(int v) {
		if (v < 0 || v >= noOfVertices) {
			return;
		}
		System.out.println("Breadth First Traversal (starting from vertex " + v + ")");
		boolean[] visited = new boolean[noOfVertices];
		Queue<Integer> queue = new LinkedList<>();
		queue.add(v);
		visited[v] = true;
		while (!queue.isEmpty()) {
			int j = queue.poll();
			System.out.print(j + " ");
			for (int i = 0; i < matrix[j].length; i++) {
				if (matrix[j][i] && !visited[i]) {
					visited[i] = true;
					queue.add(i);
				}
			}
		}
		System.out.println();

	}
	
	/**
	 * Depth First Search.
	 * 
	 * first we visit a vertex then we go to first neighbor and visit it and then we go again
	 * to that node first neighbor. traversed it then we move forward. it is just like
	 * pre-order traversal of binary tree.
	 * 
	 * Stack is used. o(V+E)
	 * 
	 * @param v - source node
	 */
	@Override
	public void dfs(int v) {
		if (v < 0 || v >= noOfVertices) {
			return;
		}
		System.out.println("Depth First Traversal (starting from vertex " + v + ")");
		boolean[] visited = new boolean[noOfVertices];
		Deque<Integer> stack = new LinkedList<>();
		stack.push(v);
		visited[v] = true;
		while (!stack.isEmpty()) {
			int j = stack.pop();
			System.out.print(j + " ");
			for (int i = 0; i < matrix[j].length; i++) {
				if (!visited[i] && matrix[j][i]) {
					visited[i] = true;
					stack.push(i);
				}
			}
		}
		System.out.println();

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
	@Override
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

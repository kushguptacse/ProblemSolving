package com.daa.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Graph adjacency List implementation of graph -
 * 
 * @f:off
 * prepare array of List. where array size is v. where v is the number of vertices.
 * addEdge - i,j. we will use i as index and add j to end of the list to that index.
 * 
 * for e.g. - 
 * if 0 vertex is connected to 1 vertex. set array[0][1]=true;
 * 
 * input- 
 * 0 - 3
 * |\
 * | 2
 * |/ 
 * 1 
 * 
 * output-
 * 0 ->1->2->3
 * 1 ->0->2
 * 2 ->0->1
 * 3 ->0
 * 
 * @f:on
 * 
 * cons: Removal is very complex. to check whether an edge exist between two vertices can take o(n) time.
 * pros: In case of sparse graph (less edges) it will take less space. Saves space O(|V|+|E|). Adding a vertex is easier.
 *
 */
public class GraphAdjacencyList {

	private int noOfVertices;
	private LinkedList<Integer>[] adjListArray;

	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	public GraphAdjacencyList(int v) {
		this.noOfVertices = v;
		adjListArray = (LinkedList<Integer>[]) new LinkedList[noOfVertices];
		for (int i = 0; i < adjListArray.length; i++) {
			adjListArray[i] = new LinkedList<>();
		}
	}

	/**
	 * add edge to adjacency list
	 * 
	 * @param i
	 * @param j
	 */
	public void addEdge(int i, int j) {
		if (i >= 0 && j >= 0 && i < noOfVertices && j < noOfVertices) {
			add(i, j);
			// uncomment below line for undirected graph 
			 add(j, i);
		}
	}

	private void add(int x, int y) {
		adjListArray[x].add(y);
	}

	/**
	 * Breadth First Search.
	 * 
	 * first all the adjacent vertex traversed then we move forward.
	 * it is just like level-order traversal of binary tree.
	 * 
	 * @param v - source node
	 */
	public void bfs(int v) {

		if (v < 0 && v >= adjListArray.length) {
			return;
		}

		boolean[] visited = new boolean[noOfVertices];
		Queue<Integer> queue = new LinkedList<>();
		queue.add(v);
		visited[v]=true;
		System.out.println("Breadth First Traversal (starting from vertex " + v + ")");
		while (!queue.isEmpty()) {
			int i = queue.poll();
			System.out.print(i + " ");
			LinkedList<Integer> list = adjListArray[i];
			list.forEach(o -> {
				if(!visited[o]) {
					visited[o] = true;
					queue.add(o);					
				}
			});
		}
		System.out.println();
	}

	/**
	 * print the data vertex wise
	 */
	public void print() {
		for (int i = 0; i < adjListArray.length; i++) {
			System.out.println("Adjacency list of vertex " + i);
			System.out.print("head");
			adjListArray[i].forEach(o -> System.out.print("->" + o));
			System.out.println();
		}
	}

}

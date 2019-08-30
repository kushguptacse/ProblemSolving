package com.daa.graph;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * in case of negative weight it will works as compared to Dijkstra. but take o(ve) time.
 * it can detect negative weight cycle also if present.
 * 
 * if we know ther is no cycle and graph is directed (DAG). then in such case DAG shortest
 * path algo can be used. o(E+V) is time complexity of DAG Shortest Path Algo.
 * 
 * Dynamic Programming
 * 
 * @author G521885
 *
 */
public class BellManFordAlgo {

	private final List<GraphWeightedNode> adjList;
	private final int noOfVertices;
	private final boolean directed;

	public static void main(String[] args) {
		test1();
		System.out.println("2----#--------");
		test2();
		System.out.println("3-------------");
		test3();
		System.out.println("4-------------");
		test4();
		System.out.println("5************");
		test5();
	}

	/**
	 * @param no Of Vertices in a graph
	 */
	public BellManFordAlgo(int v) {
		this.noOfVertices = v;
		this.adjList = new LinkedList<>();
		this.directed = false;
	}

	public BellManFordAlgo(int v, boolean directed) {
		this.noOfVertices = v;
		this.adjList = new LinkedList<>();
		this.directed = directed;
	}

	/**
	 * Add Source and Dest to the Adjacency List with passed weight/cost. o(1)
	 * 
	 * @param node
	 * @param source
	 */
	public void addEdge(int source, int dest, int weight) {
		if (dest < 0 || dest >= noOfVertices) {
			return;
		}
		if (source < 0 || source >= noOfVertices) {
			return;
		}
		adjList.add(new GraphWeightedNode(source, dest, weight));
		if (!directed) {
			adjList.add(new GraphWeightedNode(dest, source, weight));
		}
	}

	/**
	 * Here we will relax every edge v-1 times. In Adjacancy list implementation we keep
	 * List<Edge> where Edge has data <source,dest,weight>. than it will take O(VE) time. we
	 * can start from any order. as we will any way relax every node v-1 times. After relaxing
	 * v-1 times. we will try one more time to see if further any distance can be updated. if
	 * yes- it means cycle exists.
	 * 
	 * @param source
	 */
	public boolean shortestPath(int source) {

		int[] dist = new int[noOfVertices];
		// initialize all the distance to max value as per Algo
		IntStream.range(0, dist.length).forEach(i -> dist[i] = Integer.MAX_VALUE);
		dist[source] = 0;
		for (int i = 0; i < noOfVertices - 1; i++) {
			for (GraphWeightedNode node : adjList) {
				int sum = dist[node.getSource()] + node.getWeight();
				if (sum < dist[node.getDest()]) {
					dist[node.getDest()] = sum;
				}
			}
		}
		// check one more time for negative weight cycle Detection
		for (GraphWeightedNode node : adjList) {
			int sum = dist[node.getSource()] + node.getWeight();
			if (sum < dist[node.getDest()]) {
				return false;
			}
		}

		System.out.println("BellMan Ford Shortest path from node :");
		for (int i = 0; i < dist.length; ++i) {
			System.out.println(source + " -> " + i + " has minimum cost " + dist[i]);
		}
		return true;
	}

	private static void test1() {
		BellManFordAlgo g = new BellManFordAlgo(5);
		g.addEdge(0, 1, 9);
		g.addEdge(0, 2, 6);
		g.addEdge(0, 3, 5);
		g.addEdge(0, 4, 3);
		g.addEdge(2, 1, 2);
		g.addEdge(2, 3, 4);
		System.out.println(g.shortestPath(0));
	}

	private static void test2() {
		BellManFordAlgo g = new BellManFordAlgo(9);
		g.addEdge(0, 1, 4);
		g.addEdge(0, 7, 8);
		g.addEdge(1, 2, 8);
		g.addEdge(1, 7, 11);
		g.addEdge(2, 3, 7);
		g.addEdge(2, 8, 2);
		g.addEdge(2, 5, 4);
		g.addEdge(3, 4, 9);
		g.addEdge(3, 5, 14);
		g.addEdge(4, 5, 10);
		g.addEdge(5, 6, 2);
		g.addEdge(6, 7, 1);
		g.addEdge(6, 8, 6);
		g.addEdge(7, 8, 7);
		System.out.println(g.shortestPath(0));
	}

	private static void test3() {
		BellManFordAlgo g = new BellManFordAlgo(5);
		g.addEdge(0, 1, 10);
		g.addEdge(0, 4, 3);
		g.addEdge(1, 2, 2);
		g.addEdge(1, 4, 4);
		g.addEdge(2, 3, 9);
		g.addEdge(3, 2, 7);
		g.addEdge(4, 1, 1);
		g.addEdge(4, 2, 8);
		g.addEdge(4, 3, 2);
		System.out.println(g.shortestPath(0));
	}

	private static void test4() {
		BellManFordAlgo g = new BellManFordAlgo(5, true);
		g.addEdge(0, 1, -1);
		g.addEdge(0, 2, 4);
		g.addEdge(1, 2, 3);
		g.addEdge(1, 3, 2);
		g.addEdge(1, 4, 2);
		g.addEdge(3, 2, 5);
		g.addEdge(3, 1, 1);
		g.addEdge(4, 3, -3);
		System.out.println(g.shortestPath(0));
	}

	private static void test5() {
		BellManFordAlgo g = new BellManFordAlgo(9, true);
		g.addEdge(0, 1, 1);
		g.addEdge(1, 2, 1);
		g.addEdge(2, 4, 1);
		g.addEdge(4, 3, -3);
		g.addEdge(3, 2, 1);
		g.addEdge(1, 5, 4);
		g.addEdge(1, 6, 4);
		g.addEdge(5, 6, 5);
		g.addEdge(6, 7, 4);
		g.addEdge(5, 7, 3);
		System.out.println(g.shortestPath(0));
	}

}

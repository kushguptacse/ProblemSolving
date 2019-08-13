package com.daa.graph;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

public class DijkstraAlgo {

	private final List<GraphAdjacencyNode>[] adjArr;
	private final boolean directed;

	public static void main(String[] args) {
		testDijkstra1();
		System.out.println("-------------");
		testDijkstra2();
		System.out.println("-------------");
		testDijkstra3();
		System.out.println("****##########*****");
		test4();
		System.out.println("*******************");
		test5();
	}

	/**
	 * @param no Of Vertices in a graph
	 */
	@SuppressWarnings("unchecked")
	public DijkstraAlgo(int noOfVertices) {
		this.adjArr = new LinkedList[noOfVertices];
		IntStream.range(0, adjArr.length).forEach(o -> adjArr[o] = new LinkedList<>());
		directed = false;
	}

	@SuppressWarnings("unchecked")
	public DijkstraAlgo(int noOfVertices, boolean isDirected) {
		this.adjArr = new LinkedList[noOfVertices];
		IntStream.range(0, adjArr.length).forEach(o -> adjArr[o] = new LinkedList<>());
		directed = isDirected;
	}

	/**
	 * Add source and dest to the Adjacency List array with passed weight/cost. o(1)
	 * 
	 * @param node
	 * @param source
	 */
	public void addEdge(int source, int dest, int weight) {
		if (dest < 0 || dest >= adjArr.length) {
			return;
		}
		if (source < 0 || source >= adjArr.length) {
			return;
		}
		GraphAdjacencyNode node2 = new GraphAdjacencyNode(dest, weight);
		adjArr[source].add(node2);
		if (!directed) {
			GraphAdjacencyNode node1 = new GraphAdjacencyNode(source, weight);
			adjArr[dest].add(node1);
		}
	}

	/**
	 * Find Shortest path from Source node to all other nodes.
	 * result may be in-consistent if node weight are negative.
	 * o(ElogV)-  if we did not print path.
	 * BFS - Is used to find the shortest path
	 * 
	 * @f:off
	 * Approach -
	 * Distance array is used to store the min distance for every node.
	 * array of settled node is taken. which will hold true if the index of vertex is settled.
	 * PriorityQueue MinHeap is used. so that we can get the minimum node at o(1) time. 
	 * the data stored in heap will be the distance of node on given index.
	 * 
	 * Initialization -
	 * 
	 * set distance of all nodes to max_Value and source as 0.
	 * add source to MinHeap.
	 * 
	 * Algo - 
	 * get top element u from heap and add it to the settled set.
	 * for every adjacent node v of popped element u. check if that node is already present in settled set.
	 * if not. perform relaxation. i.e. update distance of v if dist[u]+v.wieght<dist[v]
	 * after that add that adj node v to heap with updated distance.
	 * 
	 * @f:on
	 * @param source
	 */
	public void shortestPath(int source) {

		PriorityQueue<GraphAdjacencyNode> minHeap = new PriorityQueue<>();
		int[] dist = new int[adjArr.length];
		boolean[] settled = new boolean[dist.length];
		int[] prev = new int[dist.length];

		// initialize all the distance to max value as per Dijkstra algo
		IntStream.range(0, dist.length).forEach(i -> dist[i] = Integer.MAX_VALUE);
		dist[source] = 0;
		// add source node to minHeap
		minHeap.add(new GraphAdjacencyNode(source, 0));
		// to keep track of previous array. i.e. path. we will store parent as value and index as
		// child. we keep on traversing till we reach -1 i.e. source
		prev[source] = -1;
		while (!minHeap.isEmpty()) {
			// get the node with minimum cost first.
			GraphAdjacencyNode minNode = minHeap.poll();
			// adding the node whose distance is finalized
			settled[minNode.getIndex()] = true;
			// perform relaxation
			for (GraphAdjacencyNode adjNode : adjArr[minNode.getIndex()]) {
				// if adjacent node not already settled.check if distance needed to be adjust or not.
				if (!settled[adjNode.getIndex()]) {
					int newSum = dist[minNode.getIndex()] + adjNode.getWeight();
					// If new distance is cheaper in cost
					if (newSum < dist[adjNode.getIndex()]) {
						dist[adjNode.getIndex()] = newSum;
						// add current node to minHeap.
						minHeap.add(new GraphAdjacencyNode(adjNode.getIndex(), dist[adjNode.getIndex()]));
						prev[adjNode.getIndex()] = minNode.getIndex();
					}
				}
			}
		}
		System.out.println("Dijkstra Shortest path from node :");
		for (int i = 0; i < prev.length; ++i) {
			System.out.print(source + " -> " + i + " has minimum cost " + dist[i] + " and path is [ ");
			printRoute(prev, i);
			System.out.println("]");
		}
	}

	private void printRoute(int[] prev, int i) {
		if (i < 0) {
			return;
		}
		printRoute(prev, prev[i]);
		System.out.print(i + " ");
	}

	private static void testDijkstra1() {
		DijkstraAlgo g = new DijkstraAlgo(5);
		g.addEdge(0, 1, 9);
		g.addEdge(0, 2, 6);
		g.addEdge(0, 3, 5);
		g.addEdge(0, 4, 3);
		g.addEdge(2, 1, 2);
		g.addEdge(2, 3, 4);
		g.shortestPath(0);
	}

	private static void testDijkstra2() {
		DijkstraAlgo g = new DijkstraAlgo(9);
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
		g.shortestPath(0);
	}

	private static void testDijkstra3() {
		DijkstraAlgo g = new DijkstraAlgo(5);
		g.addEdge(0, 1, 10);
		g.addEdge(0, 4, 3);
		g.addEdge(1, 2, 2);
		g.addEdge(1, 4, 4);
		g.addEdge(2, 3, 9);
		g.addEdge(3, 2, 7);
		g.addEdge(4, 1, 1);
		g.addEdge(4, 2, 8);
		g.addEdge(4, 3, 2);
		g.shortestPath(0);
	}

	private static void test4() {
		DijkstraAlgo g = new DijkstraAlgo(5, true);
		g.addEdge(0, 1, -1);
		g.addEdge(0, 2, 4);
		g.addEdge(1, 2, 3);
		g.addEdge(1, 3, 2);
		g.addEdge(1, 4, 2);
		g.addEdge(3, 2, 5);
		g.addEdge(3, 1, 1);
		g.addEdge(4, 3, -3);
	}

	private static void test5() {
		DijkstraAlgo g = new DijkstraAlgo(9, true);
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
	}
}

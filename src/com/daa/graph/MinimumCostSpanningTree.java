package com.daa.graph;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

/**
 * Tree formed by covering all the nodes of graph with minimum cost is Minimum Cost
 * spanning Tree.
 * 
 * @author G521885
 *
 */
public class MinimumCostSpanningTree {

	private List<GraphAdjacencyNode>[] adjArr;

	public static void main(String[] args) {
		test1();
		test2();
		test3();
	}

	@SuppressWarnings("unchecked")
	public MinimumCostSpanningTree(int noOfVertices) {
		adjArr = new LinkedList[noOfVertices];
		IntStream.range(0, adjArr.length).forEach(i -> adjArr[i] = new LinkedList<>());
	}

	/**
	 * undirected graph considered
	 * 
	 * @param i
	 * @param j
	 * @param weight
	 */
	public void addEdge(int i, int j, int weight) {
		GraphAdjacencyNode dest = new GraphAdjacencyNode(j, weight);
		adjArr[i].add(dest);
		GraphAdjacencyNode source = new GraphAdjacencyNode(i, weight);
		adjArr[j].add(source);
	}

	/**
	 * Prim's Algorithm
	 * 
	 * @f:off
	 * It is used to find the minimum cost spanning tree in a weighted undirected Graph.
	 * greedy algorithm is used. 
	 * o(ElogV)
	 * 
	 * Approach -
	 * start from source and repeat below steps -
	 * 1. find minimum edge from all the adjacent edges to that node and store it.
	 * 2. repeat this process till all the vertex are visited. 
	 * 
	 * To find minimum node we can use MinHeap. to get it in o(1) time. 
	 * and to keep track of visited node we use visited boolean array.
	 * and distance array is used to keep track of min distance of edge.
	 * 
	 * @f:on
	 * 
	 * @return Total Cost
	 */
	public int primsAlgorithm() {
		int source = 0;
		PriorityQueue<GraphAdjacencyNode> minHeap = new PriorityQueue<>();
		boolean[] visited = new boolean[adjArr.length];
		int[] parent = new int[adjArr.length];
		int[] dist = new int[adjArr.length];
		IntStream.range(0, adjArr.length).forEach(i -> {
			parent[i] = -1;
			dist[i] = Integer.MAX_VALUE;
		});
		dist[source] = 0;
		minHeap.add(new GraphAdjacencyNode(source, 0));

		while (!minHeap.isEmpty()) {
			GraphAdjacencyNode node = minHeap.poll();
			visited[node.getIndex()] = true;
			List<GraphAdjacencyNode> adjNodes = adjArr[node.getIndex()];
			for (GraphAdjacencyNode adjNode : adjNodes) {
				if (!visited[adjNode.getIndex()] && dist[adjNode.getIndex()] > adjNode.getWeight()) {
					dist[adjNode.getIndex()] = adjNode.getWeight();
					parent[adjNode.getIndex()] = node.getIndex();
					minHeap.add(adjNode);
				}
			}
		}

		int total = dist[0];
		System.out.println("Minimum Cost Spanning Tree According to Prim's Algorithm");
		for (int j = 1; j < parent.length; j++) {
			System.out.println(parent[j] + " - " + j + " : " + dist[j]);
			if (dist[j] != Integer.MAX_VALUE) {
				total = total + dist[j];
			}
		}

		return total;
	}

	private static void test1() {
		MinimumCostSpanningTree e = new MinimumCostSpanningTree(9);
		e.addEdge(0, 1, 4);
		e.addEdge(0, 7, 8);
		e.addEdge(1, 2, 8);
		e.addEdge(1, 7, 11);
		e.addEdge(2, 3, 7);
		e.addEdge(2, 8, 2);
		e.addEdge(2, 5, 4);
		e.addEdge(3, 4, 9);
		e.addEdge(3, 5, 14);
		e.addEdge(4, 5, 10);
		e.addEdge(5, 6, 2);
		e.addEdge(6, 7, 1);
		e.addEdge(6, 8, 6);
		e.addEdge(7, 8, 7);
		System.out.println(e.primsAlgorithm());
	}

	private static void test2() {
		MinimumCostSpanningTree e = new MinimumCostSpanningTree(7);
		e.addEdge(1 - 1, 2 - 1, 28);
		e.addEdge(1 - 1, 6 - 1, 10);
		e.addEdge(6 - 1, 5 - 1, 25);
		e.addEdge(5 - 1, 4 - 1, 22);
		e.addEdge(5 - 1, 7 - 1, 24);
		e.addEdge(4 - 1, 7 - 1, 18);
		e.addEdge(4 - 1, 3 - 1, 12);
		e.addEdge(3 - 1, 2 - 1, 16);
		e.addEdge(2 - 1, 7 - 1, 14);
		System.out.println(e.primsAlgorithm());
	}

	private static void test3() {
		MinimumCostSpanningTree e = new MinimumCostSpanningTree(7);
		e.addEdge(0, 1, 1);
		e.addEdge(0, 2, 5);
		e.addEdge(1, 2, 4);
		e.addEdge(1, 4, 7);
		e.addEdge(1, 3, 8);
		e.addEdge(2, 3, 6);
		e.addEdge(2, 5, 2);
		e.addEdge(3, 4, 11);
		e.addEdge(3, 5, 9);
		e.addEdge(4, 5, 3);
		e.addEdge(4, 6, 10);
		e.addEdge(5, 6, 12);
		System.out.println(e.primsAlgorithm());
	}
}

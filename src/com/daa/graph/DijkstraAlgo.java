package com.daa.graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.stream.IntStream;

/**
 * Find Shortest path from Source node to all other nodes.
 * result may be in-consitant if node weight are negative.
 * 
 * @f:off
 * Approach -
 * Distance array is used to store the min distance for every node.
 * Set of settled node is taken. which will hold the index of vertex that are settled.
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
 *
 */
public class DijkstraAlgo {

	private final List<GraphAdjacencyNode>[] adjArr;

	public static void main(String[] args) {
		testDijkstra2();
		testDijkstra1();
	}

	/**
	 * @param no Of Vertices in a graph
	 */
	@SuppressWarnings("unchecked")
	public DijkstraAlgo(int noOfVertices) {
		this.adjArr = new LinkedList[noOfVertices];
		IntStream.range(0, adjArr.length).forEach(o -> adjArr[o] = new LinkedList<>());
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
		GraphAdjacencyNode node1 = new GraphAdjacencyNode(source, weight);
		GraphAdjacencyNode node2 = new GraphAdjacencyNode(dest, weight);
		adjArr[source].add(node2);
		adjArr[dest].add(node1);
	}

	/**
	 * Print the shortest path from source vertex to all other vertex in graph. 
	 * @f:off
	 * o(ElogV),
	 * BFS - Is used to find the shortest path
	 * 
	 * @f:on
	 * @param source
	 */
	public void shortestPath(int source) {

		PriorityQueue<GraphAdjacencyNode> minHeap = new PriorityQueue<>();
		int[] dist = new int[adjArr.length];
		Set<Integer> settled = new HashSet<>();
		
		// initialize all the distance to max value as per Dijkstra algo
		IntStream.range(1, dist.length).forEach(i -> dist[i] = Integer.MAX_VALUE);
		// add source node to minHeap
		minHeap.add(new GraphAdjacencyNode(source, 0));
		
		while (settled.size() < adjArr.length) {
			// get the node with minimum cost first.
			GraphAdjacencyNode minNode = minHeap.poll();
			// adding the node whose distance is finalized
			settled.add(minNode.getIndex());
			// perform relaxation
			for (GraphAdjacencyNode adjNode : adjArr[minNode.getIndex()]) {
				// if adjacent node not already settled.check if distance needed to be adjust or not.
				if (!settled.contains(adjNode.getIndex())) {
					int newSum = dist[minNode.getIndex()] + adjNode.getWeight();
					// If new distance is cheaper in cost
					if (newSum < dist[adjNode.getIndex()]) {
						dist[adjNode.getIndex()] = newSum;
					}
					// add current node to minHeap.
					minHeap.add(new GraphAdjacencyNode(adjNode.getIndex(), dist[adjNode.getIndex()]));
				}
			}
		}
		System.out.println("Shortest path from node :");
		IntStream.range(0, dist.length).forEach(i -> System.out.println(source + " to " + i + " is " + dist[i]));
		
		
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

}

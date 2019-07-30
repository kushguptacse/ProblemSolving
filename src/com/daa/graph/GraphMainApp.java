package com.daa.graph;

/**
 * 
 */
public class GraphMainApp {
	public static void main(String[] args) {
		testAdjacencyList();
		testAdjacencyMatrix();
	}

	private static void testAdjacencyList() {
//		GraphAdjacencyList graph = new GraphAdjacencyList(5);
//		graph.addEdge(0, 1);
//		graph.addEdge(0, 4);
//		graph.addEdge(1, 2);
//		graph.addEdge(1, 3);
//		graph.addEdge(1, 4);
//		graph.addEdge(2, 3);
//		graph.addEdge(3, 4);

		GraphAdjacencyList g = new GraphAdjacencyList(4);
		g.addEdge(0, 1);
		g.addEdge(0, 2);
		g.addEdge(1, 2);
		g.addEdge(3, 0);

		// print the adjacency list representation of the above graph
		g.print();
	}

	private static void testAdjacencyMatrix() {
		GraphAdjacencyMatrix g = new GraphAdjacencyMatrix(4);
		g.addEdge(0, 1);
		g.addEdge(0, 2);
		g.addEdge(1, 2);
		g.addEdge(3, 0);
		g.print();
	}
}

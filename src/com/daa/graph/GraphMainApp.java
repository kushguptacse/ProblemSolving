package com.daa.graph;

/**
 * 
 */
public class GraphMainApp {
	public static void main(String[] args) {

		testTopologySort(new GraphAdjacencyList(6));
		testDFS(new GraphAdjacencyList(4));
		System.out.println("-----------------------------------------------");
		testDFS(new GraphAdjacencyMatrix(4));
		testBFSAndDfs(new GraphAdjacencyMatrix(5), new GraphAdjacencyMatrix(4));
		testBFSAndDfs(new GraphAdjacencyList(5), new GraphAdjacencyList(4));
		testAdjacencyMatrix();
		testAdjacencyList();
	}

	private static void testTopologySort(GraphAdjacencyList g) {
		 // Create a graph given in the above diagram 
		g.addEdge(5, 2); 
        g.addEdge(5, 0); 
        g.addEdge(4, 0); 
        g.addEdge(4, 1); 
        g.addEdge(2, 3); 
        g.addEdge(3, 1); 
  
        g.topologicalSort(); 		
	}

	private static void testDFS(Graph g) {
		g.addEdge(0, 1);
		g.addEdge(0, 2);
		g.addEdge(1, 2);
		g.addEdge(2, 0);
		g.addEdge(2, 3);
		g.addEdge(3, 3);
		g.dfs(2);
		if (g instanceof GraphAdjacencyList) {
			((GraphAdjacencyList) g).dfsIterative(2);
		}
	}

	private static void testBFSAndDfs(Graph g1, Graph g) {

		g1.addEdge(0, 1);
		g1.addEdge(0, 2);
		g1.addEdge(1, 2);
		g1.addEdge(1, 3);
		g1.addEdge(1, 4);
		g1.addEdge(2, 4);
		g1.addEdge(3, 4);
		g1.bfs(1);
		g1.dfs(1);
		g.addEdge(0, 1);
		g.addEdge(0, 2);
		g.addEdge(1, 2);
		g.addEdge(2, 0);
		g.addEdge(2, 3);
		g.addEdge(3, 3);
		g.bfs(2);
		g.dfs(2);
	}

	private static void testAdjacencyList() {
		GraphAdjacencyList graph = new GraphAdjacencyList(5);
		graph.addEdge(0, 1);
		graph.addEdge(0, 4);
		graph.addEdge(1, 2);
		graph.addEdge(1, 3);
		graph.addEdge(1, 4);
		graph.addEdge(2, 3);
		graph.addEdge(3, 4);

		graph.print();
		System.out.println("------------------");
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
		g.bfs(3);
	}
}

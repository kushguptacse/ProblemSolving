package com.daa.disjoint;

public class DisjointSetMainApp {
	public static void main(String[] args) {
		testGraphCycle2();
		DisjointSet disjointSet = new DisjointSet(5);
		disjointSet.union(0, 2);
		disjointSet.union(4, 2);
		disjointSet.union(3, 1);
		disjointSet.print();
		if (disjointSet.find(4) == disjointSet.find(0))
			System.out.println("Yes");
		else
			System.out.println("No");

		// Check if 1 is a friend of 0
		if (disjointSet.find(1) == disjointSet.find(0))
			System.out.println("Yes");
		else
			System.out.println("No");
//		disjointSet.union(0, 1);
//		disjointSet.union(2, 3);
//		disjointSet.union(4, 5);
//		disjointSet.union(6, 7);
//
//		disjointSet.print();
//		disjointSet.union(1, 3);
//		disjointSet.print();
//		disjointSet.union(1, 4);
//		disjointSet.print();
//		disjointSet.union(4, 5);
//		disjointSet.print();
//		disjointSet.union(5, 7);
//		disjointSet.print();
//		disjointSet.find(7);
//		disjointSet.print();
	}

	private static void testGraphCycle2() {
		Graph graph = new Graph(8, 9);
		graph.getEdges()[0] = new Edge(0,1);
		graph.getEdges()[1] = new Edge(2,3);
		graph.getEdges()[2] = new Edge(4,5);
		graph.getEdges()[3] = new Edge(6,7);
		graph.getEdges()[4] = new Edge(1,3);
		graph.getEdges()[5] = new Edge(1,4);
		graph.getEdges()[6] = new Edge(0,2);
		graph.getEdges()[7] = new Edge(5,7);
		graph.getEdges()[8] = new Edge(4,6);

		if (graph.isCycle())
			System.out.println("Graph contains cycle");
		else
			System.out.println("Graph doesn't contain cycle");

	}
	
	private static void testGraphCycle() {
		Graph graph = new Graph(4, 3);

		// add edge 0-1
		graph.getEdges()[0]= new Edge(0,1);

		// add edge 1-2
		graph.getEdges()[1]= new Edge(1,2);

		// add edge 0-2
		graph.getEdges()[2]= new Edge(1,3);

		if (graph.isCycle())
			System.out.println("Graph contains cycle");
		else
			System.out.println("Graph doesn't contain cycle");
	}
}

package com.daa.disjoint;

public class DisjointSetMainApp {
	public static void main(String[] args) {
		testGraphCycle();
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

	private static void testGraphCycle() {
		Graph graph = new Graph(4, 3);

		// add edge 0-1
		graph.getEdges()[0]= new Edge();
		graph.getEdges()[0].setSrc(0);
		graph.getEdges()[0].setDest(1);

		// add edge 1-2
		graph.getEdges()[1]= new Edge();
		graph.getEdges()[1].setSrc(1);
		graph.getEdges()[1].setDest(2);

		// add edge 0-2
		graph.getEdges()[2]= new Edge();
		graph.getEdges()[2].setSrc(1);
		graph.getEdges()[2].setDest(3);

		if (graph.isCycle())
			System.out.println("Graph contains cycle");
		else
			System.out.println("Graph doesn't contain cycle");
	}
}

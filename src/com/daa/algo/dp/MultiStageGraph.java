package com.daa.algo.dp;

/**
 * A Multistage graph is a directed graph in which the nodes can be divided into a set of
 * stages such that there is no edge between vertices of same stage and from a vertex of
 * current stage to previous stage.
 */
public class MultiStageGraph {

	public static void main(String[] args) {
		MultiStageGraph obj = new MultiStageGraph();
		int[][] graph = new int[][] { { 0, 1, 2, 5, 0, 0, 0, 0 }, { 0, 0, 0, 0, 4, 11, 0, 0 }, { 0, 0, 0, 0, 9, 5, 16, 0 }, { 0, 0, 0, 0, 0, 0, 2, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 18 }, { 0, 0, 0, 0, 0, 0, 0, 13 }, { 0, 0, 0, 0, 0, 0, 0, 2 } };

		System.out.println("Shortest distance is: " + obj.printShortestPath(graph, 8));
	}

	/**
	 * The below implementation assumes that vertex are numbered from 0 to N-1 from first
	 * stage (source) to last stage (destination). We also assume that the input graph is
	 * multistage. we can also use DAG shortest path algo here as it takes less time. o(n^2)
	 * 
	 * dist[i] = min(graph[i][j]+dist[j]) , where i+1<=j<n
	 * 
	 * @param graph
	 * @param noOfVertex
	 * @return shortest path
	 */
	public int printShortestPath(int[][] graph, int noOfVertex) {
		// distance array to keep the min value for the particular vertex
		int[] dist = new int[noOfVertex];
		// initialize last index to 0 as we know to reach to target from target cost is 0.
		dist[noOfVertex - 1] = 0;
		int[] path = new int[noOfVertex];

		// iterate from backwards to calculate min distance from that node to destination.
		for (int i = noOfVertex - 2; i >= 0; i--) {
			// setting initial value for the current node to max value.
			dist[i] = Integer.MAX_VALUE;
			// starting from current node to the target node find distance to all possible path and
			// set min to current node distance. since in code it is assumed that it is not possible
			// to have path from i+1 node to i. i.e. why we have not started from j=0
			for (int j = i + 1; j < noOfVertex; j++) {
				// ignore those edges which does not exist.
				if (graph[i][j] != 0 && graph[i][j] + dist[j] < dist[i]) {
					dist[i] = graph[i][j] + dist[j];
					path[i] = j;
				}
			}
		}

		printPath(noOfVertex, path);
		// distance 0 will have the final answer
		return dist[0];
	}

	private void printPath(int noOfVertex, int[] path) {
		int i = 0;
		System.out.println("Path from source to Destination: ");
		System.out.print(0 + "->");
		while (i < noOfVertex - 1) {
			System.out.print("->" + path[i]);
			i = path[i];
		}

		System.out.println();
	}

}

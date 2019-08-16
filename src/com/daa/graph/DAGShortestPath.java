package com.daa.graph;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

public class DAGShortestPath {

	private final List<GraphAdjacencyNode>[] adjArr;

	public static void main(String[] args) {
		test1();
		test2();
	}

	private static void test2() {
		DAGShortestPath g = new DAGShortestPath(6);
		g.addEdge(0, 1, 5);
		g.addEdge(0, 2, 3);
		g.addEdge(1, 3, 6);
		g.addEdge(1, 2, 2);
		g.addEdge(2, 4, 4);
		g.addEdge(2, 5, 2);
		g.addEdge(2, 3, 7);
		g.addEdge(3, 5, 1);
		g.addEdge(3, 4, -1);
		g.addEdge(4, 5, -2);

		int s = 1;
		g.shortestPath(s);
		g.longestPath(s);
	}

	private static void test1() {
		DAGShortestPath g = new DAGShortestPath(6);
		g.addEdge(0, 1, 5);
		g.addEdge(0, 2, 3);
		g.addEdge(1, 3, 6);
		g.addEdge(1, 2, 2);
		g.addEdge(2, 4, 4);
		g.addEdge(2, 5, 2);
		g.addEdge(2, 3, 7);
		g.addEdge(3, 4, -1);
		g.addEdge(4, 5, -2);

		int s = 1;
		g.shortestPath(s);
	}

	@SuppressWarnings("unchecked")
	public DAGShortestPath(int v) {
		adjArr = new LinkedList[v];
		IntStream.range(0, v).forEach(i -> adjArr[i] = new LinkedList<>());
	}

	/**
	 * If we need to print shortest path from source to all other nodes in directed acyclic
	 * graph, DAG shortest path Algo can be used.It works faster than Dijkstra or Bellman
	 * ford. O(V+E)
	 * 
	 * @f:off
	 * 
	 * @f:on
	 * 
	 * @param source
	 */
	public void shortestPath(int source) {
		Deque<Integer> stack = getTopologicalOrder();
		int[] dist = new int[adjArr.length];
		IntStream.range(0, adjArr.length).forEach(i -> dist[i] = Integer.MAX_VALUE);
		dist[source] = 0;
		while (!stack.isEmpty()) {
			int i = stack.pop();
			for (GraphAdjacencyNode node : adjArr[i]) {
				if (dist[i] != Integer.MAX_VALUE) {
					int sum = dist[i] + node.getWeight();
					if (sum < dist[node.getIndex()]) {
						dist[node.getIndex()] = sum;
					}
				}
			}

		}
		System.out.println("Following are shortest distances " + "from source " + source);
		// Print the calculated shortest distances
		for (int i = 0; i < dist.length; i++) {
			System.out.print(dist[i] + " ");
		}
		System.out.println();
	}

	/**
	 * Same as shortest path Algorithm. just we initialize all nodes except source to minus
	 * infinity. and we would update the node distance if new path is longer than already
	 * stored distance. o(V+E)
	 * 
	 * @param source
	 */
	public void longestPath(int source) {
		int[] dist = new int[adjArr.length];
		IntStream.range(0, adjArr.length).forEach(i -> dist[i] = Integer.MIN_VALUE);
		dist[source] = 0;
		Deque<Integer> stack = getTopologicalOrder();
		int[] prev = new int[dist.length];
		IntStream.range(0, prev.length).forEach(i -> prev[i] = -1);
		while (!stack.isEmpty()) {
			int i = stack.pop();
			for (GraphAdjacencyNode node : adjArr[i]) {
				if (dist[i] != Integer.MIN_VALUE) {
					int sum = dist[i] + node.getWeight();
					if (sum > dist[node.getIndex()]) {
						dist[node.getIndex()] = sum;
						prev[node.getIndex()] = i;
					}
				}
			}
		}

		System.out.println("Following are longest distances " + "from source " + source);
		// Print the calculated longest distances
		for (int i = 0; i < prev.length; ++i) {
			System.out.print(source + " -> " + i + " has minimum cost " + dist[i] + " and path is [ ");
			printRoute(prev, i);
			System.out.println("]");
		}
		System.out.println();
	}
	
	private void printRoute(int[] prev, int i) {
		if (i < 0) {
			return;
		}
		printRoute(prev, prev[i]);
		System.out.print(i + " ");
	}

	private Deque<Integer> getTopologicalOrder() {
		Deque<Integer> stack = new LinkedList<>();
		boolean[] visited = new boolean[adjArr.length];
		for (int i = 0; i < adjArr.length; i++) {
			topologicalSortRecursion(i, visited, stack);
		}
		return stack;
	}

	public void addEdge(int i, int v, int w) {
		adjArr[i].add(new GraphAdjacencyNode(v, w));
	}

	private void topologicalSortRecursion(int i, boolean[] visited, Deque<Integer> stack) {
		if (visited[i]) {
			return;
		}
		visited[i] = true;
		List<GraphAdjacencyNode> list = adjArr[i];
		list.forEach(o -> topologicalSortRecursion(o.getIndex(), visited, stack));
		stack.push(i);
	}
}

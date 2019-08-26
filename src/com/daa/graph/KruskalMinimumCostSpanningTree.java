package com.daa.graph;

import java.util.Arrays;
import java.util.stream.IntStream;

import com.daa.math.MathUtil;

/**
 * Tree formed by covering all the vertex of graph with V-1 edges and minimum cost is
 * Minimum Cost spanning Tree. for unique edges MST is always unique. with duplicate edge
 * weights final cost is always same but two or more MST is possible.
 * 
 * @f:off
 * Here we have considered undirected graph. This algorithm also works with directed graph
 * @f:on
 * @author G521885
 *
 */
public class KruskalMinimumCostSpanningTree {

	private GraphWeightedNode[] edges;
	private int noOfVertices;
	private int count = 0;

	public static void main(String[] args) {
		test1();
		test2();
		test3();
	}

	public KruskalMinimumCostSpanningTree(int noOfVertices, int noOfEdges) {
		edges = new GraphWeightedNode[noOfEdges];
		this.noOfVertices = noOfVertices;
	}

	/**
	 * un-directed graph considered
	 * 
	 * @param i
	 * @param j
	 * @param weight
	 */
	public void addEdge(int i, int j, int weight) {
		edges[count++] = new GraphWeightedNode(i, j, weight);
		edges[count++] = new GraphWeightedNode(j, i, weight);
	}

	/**
	 * Kruskal's Algorithm
	 * 
	 * @f:off
	 * It is used to find the minimum cost spanning tree in a weighted undirected Graph.
	 * greedy algorithm is used. 
	 * o(ElogV) or o(ElogE)
	 * 
	 * Approach -
	 * 1. Sort the nodes according to weights in ascending order.
	 * 2. For every node present in the list check if that node addition form cycle -
	 * 2.1 If yes - don't consider that node.
	 * 2.2 else- add it to MST. 
	 * 
	 * To check cycle exists or not we can disjoint sets.
	 * union by rank and find by path compression technique.
	 * We will use parent array where index will be the vertex and value has two parts - sign and magnitude.
	 * positive sign means further parent exists. and negative means no parent for that node.
	 * magnitude decide the rank. i.e. how many child's that index has.
	 * 
	 * @f:on
	 * 
	 * @return Total Cost
	 */
	public int kruskalsAlgorithm() {
		GraphWeightedNode[] result = new GraphWeightedNode[noOfVertices - 1];
		Arrays.sort(edges, (o1, o2) -> o1.getWeight() - o2.getWeight());
		int[] parent = new int[noOfVertices];
		IntStream.range(0, parent.length).forEach(o -> parent[o] = -1);
		int c = 0;
		for (int i = 0; i < edges.length; i++) {
			int x = find(parent, edges[i].getSource());
			int y = find(parent, edges[i].getDest());
			if (x != y) {
				result[c++] = edges[i];
				union(parent, x, y);
			}
		}

		int total = 0;
		System.out.println("Minimum Cost Spanning Tree According to Kruskal's Algorithm");
		for (int j = 0; j < result.length; j++) {
			if (result[j] != null) {
				System.out.println(result[j].getSource() + " - " + result[j].getDest() + " : " + result[j].getWeight());
				total = total + result[j].getWeight();
			}
		}

		return total;
	}

	/**
	 * @f:off
	 * 
	 * Disjoint Set -
	 * 
	 * Path compression is used. parent is cached for next time faster result.
	 * So for every node we goto root and return it. but before returning it we update the passed node parent to root. 
	 * By this way next time we don't need to go up to the hierarchy to reach root.
	 * 
	 * @f:on
	 * 
	 * @param i
	 * @param parent
	 * o(logn)
	 * @return parent of the node passed. if no parent then return itself.
	 */
	private int find(int[] parent, int x) {
		int res = x;
		while (parent[x] > -1) {
			x = parent[x];
		}
		if (res != x) {
			parent[res] = x;
		}
		return x;
	}

	/**
	 * 
	 * @f:off
	 * Disjoint Set -
	 * Perform union of x and y.
	 * 
	 * To check which out of x or y will parent. 
	 * We need to first perform find x and find y operation.(which is done before calling this method).
	 * 
	 * Check if absoluteValue of (parent[x1]) < absoluteValue of (parent[y1]). 
	 * i.e. we are checking which of them has higher rank and parent holds the rank if we ignore sign.
	 * 1. If yes it means y1 should be the parent of x1. and we will update the rank of parent y1 as original value + old parent[x1]. 
	 * o(1)
	 * @f:on
	 * 
	 * @param parent
	 * @param x
	 * @param y
	 */
	private void union(int[] parent, int x1, int y1) {
		if (MathUtil.abs(parent[x1]) < MathUtil.abs(parent[y1])) {
			int res = parent[x1];
			parent[x1] = y1;
			parent[y1] = parent[y1] + res;
		} else {
			int res = parent[y1];
			parent[y1] = x1;
			parent[x1] = parent[x1] + res;
		}
	}

	private static void test1() {
		KruskalMinimumCostSpanningTree e = new KruskalMinimumCostSpanningTree(9, 28);
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
		System.out.println(e.kruskalsAlgorithm());
	}

	private static void test2() {
		KruskalMinimumCostSpanningTree e = new KruskalMinimumCostSpanningTree(7, 18);
		e.addEdge(0, 2 - 1, 28);
		e.addEdge(0, 6 - 1, 10);
		e.addEdge(6 - 1, 5 - 1, 25);
		e.addEdge(5 - 1, 4 - 1, 22);
		e.addEdge(5 - 1, 7 - 1, 24);
		e.addEdge(4 - 1, 7 - 1, 18);
		e.addEdge(4 - 1, 3 - 1, 12);
		e.addEdge(3 - 1, 2 - 1, 16);
		e.addEdge(2 - 1, 7 - 1, 14);
		System.out.println(e.kruskalsAlgorithm());
	}

	private static void test3() {
		KruskalMinimumCostSpanningTree e = new KruskalMinimumCostSpanningTree(7, 24);
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
		System.out.println(e.kruskalsAlgorithm());
	}
}

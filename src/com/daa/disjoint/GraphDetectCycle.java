package com.daa.disjoint;

import com.daa.math.MathUtil;

/**
 * Find and union operation of disjoint set is used to detect cycle in graph. for better
 * Find- caching is used. for better union- rank is used.
 * 
 * @f:on
 * Single array is used. 
 * Where positive value decide the index of parent and for root node negative value is used. 
 * to decide rank - Negative value is used as no parent sign and larger the magnitude heaver the node/rate.
 * @f:off
 * 
 * @author G521885
 *
 */
public class GraphDetectCycle {

	private int noOfVertices;
	private Edge[] edges;
	private int count = 0;

	/**
	 * @param noOfVertices
	 * @param noOfEdges
	 */
	public GraphDetectCycle(int noOfVertices, int noOfEdges) {
		super();
		this.noOfVertices = noOfVertices;
		edges = new Edge[noOfEdges];
	}

	/**
	 * @f:off
	 * To check cycle exists or not we can disjoint sets.
	 * union by rank and find by path compression technique.
	 * We will use parent array where index will be the vertex and value has two parts - sign and magnitude.
	 * positive sign means further parent exists. and negative means no parent for that node.
	 * magnitude decide the rank. i.e. how many child's that index has. for 1 child value will be -2.
	 * 
	 * O(ElogV) here logV is very slow growing function and is very close to constant
	 * @f:on
	 * 
	 * @param graph
	 * @return true if cycle exits
	 */
	public boolean isCycle() {
		int[] parent = new int[noOfVertices];

		for (int i = 0; i < noOfVertices; i++) {
			parent[i] = -1;
		}

		for (int i = 0; i < edges.length; i++) {
			int x = find(parent, edges[i].getSrc());
			int y = find(parent, edges[i].getDest());
			if (x == y) {
				return true;
			}
			union(parent, x, y);
		}
		return false;
	}

	public void print(int[] parent) {
		System.out.println("Array data :");
		for (int i = 0; i < parent.length; i++) {
			System.out.print(parent[i] + " ");
		}
		System.out.println();
	}

	/**
	 * Perform union of x and y. 
	 * also update rank to decide which node needed to be parent. if
	 * rank of y>x. than parent[x]=y. and new rank of y will be original + number of new nodes
	 * added.
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
	 * @f:on
	 * 
	 * @param parent
	 * @param x1
	 * @param y1
	 */
	public void union(int[] parent, int x1, int y1) {

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
	 * @param x
	 * @param parent
	 * 
	 * @return parent of the node passed. if no parent then return itself.
	 */
	public int find(int[] parent, int x) {
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
	 * @return the edges
	 */
	public void addEdge(int x, int y) {
		edges[count++] = new Edge(x, y);
	}
}

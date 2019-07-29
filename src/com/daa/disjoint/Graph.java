package com.daa.disjoint;

import com.daa.math.MathUtil;

/**
 * find and union operation of disjoint set is used to detect cycle in graph. for better
 * find- caching is used. for better union- rank is used.
 * 
 * single array is used. where positive value decide the index of parent. and for root
 * node negative value is used. to decide rate negative value is used as no parent sign
 * and larger the magnitude heaver the node/rate.
 * 
 * @author G521885
 *
 */
public class Graph {

	private int noOfVertices;
	private int noOfEdges;
	private Edge[] edges;

	/**
	 * @param noOfVertices
	 * @param noOfEdges
	 */
	public Graph(int noOfVertices, int noOfEdges) {
		super();
		this.noOfVertices = noOfVertices;
		this.noOfEdges = noOfEdges;
		edges = new Edge[noOfEdges];
	}

	/**
	 * 
	 * @param graph
	 * @return true if cycle exits
	 */
	public boolean isCycle() {
		int[] parent = new int[noOfVertices];

		for (int i = 0; i < noOfVertices; i++) {
			parent[i] = -1;
		}

		for (int i = 0; i < noOfEdges; i++) {
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
	 * perform union of x and y. also assign rank to decide which node needed to be parent. if
	 * rank of y>x. than parent[x]=y. and new rank of y will be original + number of new nodes
	 * added.
	 * 
	 * @param x
	 * @param y
	 */
	public void union(int[] parent, int x, int y) {

		if (MathUtil.abs(parent[x]) < MathUtil.abs(parent[y])) {
			int res = parent[x];
			parent[x] = y;
			parent[y] = parent[y] + res;
		} else {
			int res = parent[y];
			parent[y] = x;
			parent[x] = parent[x] + res;
		}
	}

	/**
	 * find the parent and also cache the parent for faster search next time. (Path
	 * Compression)
	 * 
	 * @param x
	 * @return parent
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
	public Edge[] getEdges() {
		return edges;
	}
}

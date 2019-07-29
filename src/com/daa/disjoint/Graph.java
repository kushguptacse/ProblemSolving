package com.daa.disjoint;

import com.daa.math.MathUtil;

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

	private void union(int[] parent, int x, int y) {

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
	 * @return the edges
	 */
	public Edge[] getEdges() {
		return edges;
	}
}

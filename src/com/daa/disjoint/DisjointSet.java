package com.daa.disjoint;

import com.daa.math.MathUtil;

/**
 * 
 * @author G521885
 *
 */
public class DisjointSet {

	private final int[] parent;
	private static final int INITIAL_CAPACITY = 8;

	public DisjointSet() {
		parent = new int[INITIAL_CAPACITY];
		makeSet();
	}

	public DisjointSet(int n) {
		parent = new int[n];
		makeSet();
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
	 * 
	 * @return parent of the node passed. if no parent then return itself.
	 */
	public int find(int x) {
		int temp = x;
		while (parent[x] > -1) {
			x = parent[x];
		}
		if (temp != x) {
			parent[temp] = x;
		}
		return x;
	}

	/**
	 * @f:off
	 * perform union of x and y.
	 * Also update rank to decide which node needed to be parent.
	 * First find the root of x as x1 and y as y1 by using DisjointSet.Find method.
	 * after that check which of x1 or y1 has more children i.e. more rank. so for that
	 * if (MathUtil.abs(parent[x1]) < MathUtil.abs(parent[y1])) i.e. magnitude of value for vertex x1 < magnitude of value for vertex y1.
	 * it means y1 should be the parent of x1. i.e. parent[x1] = y1;
	 * and we need to update the rank of y1 as it has new child tree added.
	 * so, parent[y1] = parent[y1] + old value of parent[x1];
	 * 
	 * @f:on
	 * 
	 * @param x
	 * @param y
	 */
	public void union(int x, int y) {
		int x1 = find(x);
		int y1 = find(y);
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

	public void print() {
		System.out.println("Array data :");
		for (int i = 0; i < parent.length; i++) {
			System.out.print(parent[i] + " ");
		}
		System.out.println();
	}

	/**
	 * 
	 */
	public void makeSet() {
		for (int i = 0; i < parent.length; i++) {
			parent[i] = -1;
		}
	}

}

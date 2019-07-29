package com.daa.disjoint;

import com.daa.math.MathUtil;

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
	 * find the parent and also cache the parent for faster search next time. (Path
	 * Compression)
	 * 
	 * @param x
	 * @return parent
	 */
	public int find(int x) {
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
	 * perform union of x and y.
	 * also assign rank to decide which node needed to be parent.
	 * if rank of y>x. than parent[x]=y. and new rank of y will be original + number of new nodes added.
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

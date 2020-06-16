package com.daa.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class BiPartiteGraph {
	public boolean possibleBipartition(int n, int[][] dislikes) {
		List<Integer>[] graph = constructGraph(n, dislikes);
		int[] color = new int[n];
		for (int i = 0; i < n; i++) {
			// if (color[i] == 0 && !dfsUtil(color, graph, i)) {
			if (color[i] == 0 && !bfsUtil(color, graph, i)) {
				return false;
			}
		}
		return true;
	}

	private boolean bfsUtil(int[] color, List<Integer>[] graph, int source) {
		color[source] = 1;
		Queue<Integer> queue = new LinkedList<>();
		queue.add(source);
		while (!queue.isEmpty()) {
			int curr = queue.poll();
			for (int adj : graph[curr]) {
				if (color[curr] == color[adj]) {
					return false;
				}
				if (color[adj] == 0) {
					color[adj] = -color[curr];
					queue.add(adj);
				}

			}
		}
		return true;
	}

	private boolean dfsUtil(int[] color, List<Integer>[] graph, int curr, int c) {
		if (color[curr] != 0) {
			return color[curr] == c;
		}
		color[curr] = c;
		for (int adj : graph[curr]) {
			if (!dfsUtil(color, graph, adj, -c)) {
				return false;
			}
		}

		return true;
	}

	private List<Integer>[] constructGraph(int n, int[][] dislikes) {
		List<Integer>[] graph = new ArrayList[n];
		for (int i = 0; i < n; i++) {
			graph[i] = new ArrayList<>();
		}
		for (int i = 0; i < dislikes.length; i++) {
			graph[dislikes[i][0] - 1].add(dislikes[i][1] - 1);
			graph[dislikes[i][1] - 1].add(dislikes[i][0] - 1);
		}
		return graph;
	}

}
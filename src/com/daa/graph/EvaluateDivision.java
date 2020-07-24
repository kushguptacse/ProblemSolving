package com.daa.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class EvaluateDivision {

	public static void main(String[] args) {
		List<List<String>> equations = Arrays.asList(Arrays.asList("a", "b"), Arrays.asList("b", "c"));
		double[] values = { 2.0, 3.0 };
		List<List<String>> queries = Arrays.asList(Arrays.asList("a", "c"), Arrays.asList("b", "a"),
				Arrays.asList("a", "e"), Arrays.asList("a", "a"), Arrays.asList("x", "x"));
		System.out.println(Arrays.toString(new EvaluateDivision().calcEquation(equations, values, queries)));

		equations = Arrays.asList(Arrays.asList("a", "b"), Arrays.asList("b", "c"), Arrays.asList("c", "d"),
				Arrays.asList("d", "e"));
		values = new double[] { 3.0, 4.0, 5.0, 6.0 };
		queries = Arrays.asList(Arrays.asList("a", "e"), Arrays.asList("e", "b"), Arrays.asList("b", "d"),
				Arrays.asList("b", "b"), Arrays.asList("b", "x"), Arrays.asList("y", "y"));
		System.out.println(Arrays.toString(new EvaluateDivision().calcEquation(equations, values, queries)));

	}

	private class Node {
		private double val;
		private String dest;

		Node(double val, String dest) {
			this.val = val;
			this.dest = dest;
		}

		@Override
		public String toString() {
			return val + ":" + dest;
		}

	}

	public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
		double[] output = new double[queries.size()];
		Map<String, List<Node>> map = prepareGraph(equations, values);
		for (int i = 0; i < queries.size(); i++) {
			String src = queries.get(i).get(0);
			String dest = queries.get(i).get(1);
			if (map.containsKey(src) && map.containsKey(dest)) {
				output[i] = dfs(map, src, dest, new HashSet<>());
			}
			if (output[i] <= 0.0) {
				output[i] = -1;
			}
		}
		return output;
	}

	private double dfs(Map<String, List<Node>> map, String src, String dest, Set<String> visited) {
		if (src.equals(dest)) {
			return 1.0;
		}
		if (visited.contains(src)) {
			return -1.0;
		}
		double res = 1.0;
		visited.add(src);
		for (Node node : map.get(src)) {
			res = node.val * dfs(map, node.dest, dest, visited);
			if (res > 0.0) {
				return res;
			}
		}
		return res;
	}

	private Map<String, List<Node>> prepareGraph(List<List<String>> equations, double[] values) {
		Map<String, List<Node>> map = new HashMap<>();
		for (int i = 0; i < equations.size(); i++) {
			String src = equations.get(i).get(0);
			String dest = equations.get(i).get(1);
			List<Node> list = map.getOrDefault(src, new ArrayList<>());
			list.add(new Node(values[i], dest));
			map.put(src, list);
			List<Node> list2 = map.getOrDefault(dest, new ArrayList<>());
			list2.add(new Node((1.0 / values[i]), src));
			map.put(dest, list2);
		}
		return map;
	}

}
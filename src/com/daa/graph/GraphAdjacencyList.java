package com.daa.graph;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Graph adjacency List implementation of graph -
 * 
 * @f:off
 * prepare array of List. where array size is v. where v is the number of vertices.
 * addEdge - i,j. we will use i as index and add j to end of the list to that index.
 * 
 * for e.g. - 
 * if 0 vertex is connected to 1 vertex. set array[0][1]=true;
 * 
 * input- 
 * 0 - 3
 * |\
 * | 2
 * |/ 
 * 1 
 * 
 * output-
 * 0 ->1->2->3
 * 1 ->0->2
 * 2 ->0->1
 * 3 ->0
 * 
 * @f:on
 * 
 * cons: Removal is very complex. to check whether an edge exist between two vertices can take o(n) time.
 * pros: In case of sparse graph (less edges) it will take less space. Saves space O(|V|+|E|). Adding a vertex is easier.
 *
 */
public class GraphAdjacencyList implements Graph {

	private int noOfVertices;
	private LinkedList<Integer>[] adjListArray;

	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	public GraphAdjacencyList(int v) {
		this.noOfVertices = v;
		adjListArray = (LinkedList<Integer>[]) new LinkedList[noOfVertices];
		for (int i = 0; i < adjListArray.length; i++) {
			adjListArray[i] = new LinkedList<>();
		}
	}

	/**
	 * add edge to adjacency list. o(1)
	 * 
	 * @param i
	 * @param j
	 */
	@Override
	public void addEdge(int i, int j) {
		if (i >= 0 && j >= 0 && i < noOfVertices && j < noOfVertices) {
			add(i, j);
			// uncomment below line for undirected graph
//			add(j, i);
		}
	}

	private void add(int x, int y) {
		adjListArray[x].add(y);
	}

	/**
	 * Breadth First Search.
	 * 
	 * first all the adjacent vertex traversed then we move forward. it is just like
	 * level-order traversal of binary tree.
	 * 
	 * Queue is used. o(V+E)
	 * 
	 * @param v - source node
	 */
	@Override
	public void bfs(int v) {

		if (v < 0 || v >= adjListArray.length) {
			return;
		}
		System.out.println("Breadth First Traversal (starting from vertex " + v + ")");
		boolean[] visited = new boolean[noOfVertices];
		Queue<Integer> queue = new LinkedList<>();
		queue.add(v);
		visited[v] = true;
		while (!queue.isEmpty()) {
			int i = queue.poll();
			System.out.print(i + " ");
			LinkedList<Integer> list = adjListArray[i];
			list.forEach(o -> {
				if (!visited[o]) {
					visited[o] = true;
					queue.add(o);
				}
			});
		}
		System.out.println();
	}

	/**
	 * Depth First Search.
	 * 
	 * first we visit a vertex then we go to first neighbor and visit it and then we go again
	 * to that node first neighbor. traversed it then we move forward. it is just like
	 * pre-order traversal of binary tree.
	 * 
	 * Stack is used. o(V+E)
	 * 
	 * @param v - source node
	 */
	public void dfsIterative(int v) {
		if (v < 0 || v >= adjListArray.length) {
			return;
		}
		System.out.println("Depth First Traversal (starting from vertex " + v + ")");
		boolean[] visited = new boolean[noOfVertices];
		Deque<Integer> stack = new LinkedList<>();
		stack.push(v);
		visited[v] = true;
		while (!stack.isEmpty()) {
			int i = stack.pop();
			System.out.print(i + " ");
			List<Integer> list = adjListArray[i];
			list.forEach(o -> {
				if (!visited[o]) {
					visited[o] = true;
					stack.push(o);
				}
			});
		}
		System.out.println();

	}

	/**
	 * Prepare the topological order of the graph. multiple order possible.
	 * 
	 * Rule is - in (u,v) vertex pair. u will appear before v. due to this property it is used
	 * in build tool like maven. o(u+v)
	 */
	public void topologicalSort() {
		System.out.println("Topological sort :");
		Deque<Integer> stack = new LinkedList<>();
		boolean[] visited = new boolean[noOfVertices];
		for (int i = 0; i < noOfVertices; i++) {
			if (!visited[i]) {
				topologicalSortRecursion(i, visited, stack);
			}
		}
		while (!stack.isEmpty()) {
			System.out.print(stack.pop() + " ");
		}
		System.out.println();
	}

	private void topologicalSortRecursion(int v, boolean[] visited, Deque<Integer> stack) {
		visited[v] = true;
		List<Integer> list = adjListArray[v];
		list.forEach(o -> {
			if (!visited[o]) {
				topologicalSortRecursion(o, visited, stack);
			}
		});
		stack.push(v);
	}

	/**
	 * perform DFS using recursion. In this way we can use recursion call stack. performance
	 * wise both are same. o(V+E)
	 */
	public void dfs(int v) {
		if (v < 0 || v >= adjListArray.length) {
			return;
		}
		System.out.println("Depth First Traversal (starting from vertex " + v + ")");
		boolean[] visited = new boolean[noOfVertices];
		visited[v] = true;
		dfsRecursionUtil(v, visited);
		System.out.println();
	}

	private void dfsRecursionUtil(int v, boolean[] visited) {
		System.out.print(v + " ");
		List<Integer> list = adjListArray[v];
		list.forEach(o -> {
			if (!visited[o]) {
				visited[o] = true;
				dfsRecursionUtil(o, visited);
			}
		});

	}

	/**
	 * print the data vertex wise
	 */
	@Override
	public void print() {
		for (int i = 0; i < adjListArray.length; i++) {
			System.out.println("Adjacency list of vertex " + i);
			System.out.print("head");
			adjListArray[i].forEach(o -> System.out.print("->" + o));
			System.out.println();
		}
	}

}

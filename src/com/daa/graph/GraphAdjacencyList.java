package com.daa.graph;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Graph adjacency List implementation of graph -
 * 
 * @f:off
 * Prepare array of List. where array size is v. where v is the number of vertices.
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
	 * in build tool like Maven. o(E+V)
	 * 
	 * @f:off
	 * To achieve it - we need two stack. and one adjList array.
	 * stack1 - To hold the vertex as per normal DFS logic. for it we can use recursion method stack here.
	 * stack2 - To keep result vertex.
	 * 
	 * we will add vertex to stack2 only if all it's neighbors are visited.
	 * 
	 * we will run a loop for all vertices. and if it is not visited call recursive method.
	 * method - 
	 * 1. set vertex as visited.
	 * 2. for every node v of all adjacent nodes.check if v is not visited. if yes, call method again.
	 * after the loop. just push passed vertex to stack2.
	 * The node that will first come out of the loop will have all the neighbors visited first.
	 * @f:on
	 */
	public void topologicalSort() {
		System.out.println("Topological sort :");
		Deque<Integer> stack = new LinkedList<>();
		boolean[] visited = new boolean[noOfVertices];
		for (int i = 0; i < noOfVertices; i++) {
			topologicalSortRecursion(i, visited, stack);
		}
		while (!stack.isEmpty()) {
			System.out.print(stack.pop() + " ");
		}
		System.out.println();
	}

	private void topologicalSortRecursion(int v, boolean[] visited, Deque<Integer> stack) {
		if (visited[v]) {
			return;
		}
		visited[v] = true;
		List<Integer> list = adjListArray[v];
		list.forEach(o -> topologicalSortRecursion(o, visited, stack));
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
		dfsRecursionUtil(v,  new boolean[noOfVertices]);
		System.out.println();
	}

	private void dfsRecursionUtil(int v, boolean[] visited) {
		System.out.print(v + " ");
		List<Integer> list = adjListArray[v];
		visited[v] = true;
		list.forEach(o -> {
			if (!visited[o]) {
				dfsRecursionUtil(o, visited);
			}
		});

	}

	/**
	 * Check if cycle exist in directed graph. O(V + E) DFS
	 * 
	 * @return true if cycle exist
	 */
	public boolean isCycleInDirectedGraph() {
		boolean[] visited = new boolean[noOfVertices];
		boolean[] recStack = new boolean[noOfVertices];
		for (int i = 0; i < noOfVertices; i++) {
			if (isCycleInDirectedGraphRec(i, visited, recStack)) {
				return true;
			}
			recStack[i] = false;
		}
		return false;
	}

	/**
	 * @f:off
	 * Approach is similar to topological sort. but here our interest is just to check cycle.
	 * so we will not push item into second stack to get the order.
	 * we have observed during topological sort that-
	 *  1. if there is a cycle in graph then recursion method call stack will have that value twice.
	 *  so basically we need to keep track whether there exist duplicate element in the method call stack.
	 *  so, for that we keep boolean array just like visited array.
	 *  once the element is moved to method call stack we set flag true.
	 *  at any point if flag is already true we just return from function and print true.
	 *  and if for the particular index the method stack is empty.
	 *  than we need to reset recursionSTack boolean. as we need to check for new index again.
	 * @f:on
	 * @param v
	 * @param visited
	 * @param recStack
	 * @return true if exists.
	 */
	private boolean isCycleInDirectedGraphRec(int v, boolean[] visited, boolean[] recStack) {
		if (visited[v]) {
			return false;
		}
		if (recStack[v]) {
			return true;
		}
		visited[v] = true;
		recStack[v] = true;

		List<Integer> list = adjListArray[v];
		for (Integer o : list) {
			if (isCycleInDirectedGraphRec(o, visited, recStack)) {
				return true;
			}
		}

		return false;
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

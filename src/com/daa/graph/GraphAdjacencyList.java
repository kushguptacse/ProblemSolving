package com.daa.graph;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.IntStream;

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
	private List<Integer>[] adjListArray;

	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	public GraphAdjacencyList(int v) {
		this.noOfVertices = v;
		adjListArray = (List<Integer>[]) new LinkedList[noOfVertices];
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
			List<Integer> list = adjListArray[i];
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
	 * first we visit a vertex then we go to first neighbor and visit it and then we
	 * go again to that node first neighbor. traversed it then we move forward. it
	 * is just like pre-order traversal of binary tree.
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
			if (!visited[i]) {
				topologicalSortRecursion(i, visited, stack);
			}
		}
		while (!stack.isEmpty()) {
			System.out.print(stack.pop() + " ");
		}
		System.out.println();
	}

	private void topologicalSortRecursion(int source, boolean[] visited, Deque<Integer> stack) {
		visited[source] = true;
		for (int j : adjListArray[source]) {
			if (!visited[j]) {
				topologicalSortRecursion(j, visited, stack);
			}
		}
		stack.push(source);
	}

	/**
	 * perform DFS using recursion. In this way we can use recursion call stack.
	 * performance wise both are same. o(V+E) , o(height of tree)
	 */
	public void dfs(int v) {
		if (v < 0 || v >= adjListArray.length) {
			return;
		}
		System.out.println("Depth First Traversal (starting from vertex " + v + ")");
		dfsRecursionUtil(v, new boolean[noOfVertices]);
		System.out.println();
	}

	private void dfsRecursionUtil(int v, boolean[] visited) {
		visited[v] = true;
		System.out.print(v + " ");
		adjListArray[v].forEach(o -> {
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
		System.out.println("Test if graph contain cycle");
		boolean[] visited = new boolean[noOfVertices];
		boolean[] recStack = new boolean[noOfVertices];
		for (int i = 0; i < noOfVertices; i++) {
			if (isCycleUtil(i, visited, recStack)) {
				return true;
			}
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
	private boolean isCycleUtil(int i, boolean[] visited, boolean[] recStack) {
		if (recStack[i]) {
			return true;
		}
		if (visited[i]) {
			return false;
		}
		visited[i] = true;
		recStack[i] = true;
		for (int j : adjListArray[i]) {
			if (isCycleUtil(j, visited, recStack)) {
				return true;
			}
		}
		recStack[i] = false;
		return false;
	}

	/**
	 * A vertex in an undirected connected graph is an articulation point (or cut vertex) if
	 * removing it (and edges through it) disconnects the graph.
	 * we don't want any articulation point in network as failure of such node result in a connection breakdown
	 * @f:off
	 * o(E+V)
	 * DFS
	 * @f:on
	 * TODO
	 */
	public void printArticulationPoints() {
		// hold the visited nodes in graph
		boolean[] visited = new boolean[noOfVertices];
		// hold true if particular vertex is articulation point
		boolean[] ap = new boolean[noOfVertices];
		// holds the parent of the vertex
		int[] parent = new int[noOfVertices];
		IntStream.range(0, noOfVertices).forEach(o -> parent[o] = -1);
		// holds the discovery order. that's the order in which node is visited in DFS.
		int[] disc = new int[noOfVertices];
		// it is maintain to hold the back edge.
		int[] low = new int[noOfVertices];

		for (int j = 0; j < visited.length; j++) {
			if (!visited[j]) {
				findAP(j, visited, ap, disc, low, parent);
			}
		}

		System.out.println("Articulation Points are : ");
		// any vertex is an articulation point if low[child]>=disc[parent]
		for (int i = 0; i < ap.length; i++) {
			if (ap[i]) {
				System.out.print(i + " ");
			}
		}
		System.out.println();
	}

	/**
	 * Method to find the articulation point for particular vertex j TODO
	 * 
	 * @param j
	 * @param visited
	 * @param ap
	 * @param disc
	 * @param low
	 * @param parent
	 */
	private void findAP(int j, boolean[] visited, boolean[] ap, int[] disc, int[] low, int[] parent) {

	}

	/**
	 * In degree of the vertex is the number of the incoming edges. Out degree of the vertex
	 * is the number of outgoing edges.
	 * 
	 * @f:off
	 * approach -
	 * Iterate through the adjList and the number of elements at particular index is out degree.
	 * and number of times any vertex occurs in all list is in degree
	 * 
	 * @f:on
	 */
	public void printInAndOutDegree() {
		int[] in = new int[noOfVertices];
		int[] out = new int[noOfVertices];
		for (int i = 0; i < adjListArray.length; i++) {
			out[i] = adjListArray[i].size();
			for (int obj : adjListArray[i]) {
				in[obj] = in[obj] + 1;
			}
		}
		System.out.println("v  in out");
		for (int i = 0; i < in.length; i++) {
			System.out.println(i + "  " + in[i] + "  " + out[i]);
		}

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

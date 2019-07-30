package com.daa.graph;

import com.daa.list.Node;

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
public class GraphAdjacencyList {

	private int noOfVertices;
	private Node<Integer>[] adjListArray;

	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	public GraphAdjacencyList(int v) {
		this.noOfVertices = v;
		adjListArray = (Node<Integer>[]) new Node[noOfVertices];
	}

	/**
	 * 
	 * @param i
	 * @param j
	 */
	public void addEdge(int i, int j) {
		if (i >= 0 && j >= 0 && i < noOfVertices && j < noOfVertices) {
			add(i, j);
			add(j, i);
		}
	}

	private void add(int x, int y) {
		Node<Integer> node = adjListArray[x];
		Node<Integer> node1 = new Node<>(y);
		if (node == null) {
			adjListArray[x] = node1;
		} else {
			while (node.getNext() != null) {
				node = node.getNext();
			}
			node.setNext(node1);
		}
	}

	/**
	 * print the data vertex wise
	 */
	public void print() {
		for (int i = 0; i < adjListArray.length; i++) {
			System.out.println("Adjacency list of vertex " + i);
			System.out.print("head");
			Node<Integer> temp = adjListArray[i];
			while (temp != null) {
				System.out.print("->" + temp.getData());
				temp = temp.getNext();
			}
			System.out.println();
		}
	}

}

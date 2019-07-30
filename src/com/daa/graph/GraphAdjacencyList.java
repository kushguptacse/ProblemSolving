package com.daa.graph;

import com.daa.list.Node;

/**
 * 
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
	 * TODO
	 * @param i
	 * @param j
	 */
	public void addEdge(int i, int j) {

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
			}
		}
	}

}

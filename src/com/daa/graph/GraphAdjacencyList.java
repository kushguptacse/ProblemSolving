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
		adjListArray = (Node<Integer>[])new Node[noOfVertices];
	}
	
	public void addEdge(int i,int j) {
		
	}
	
	public void print() {
		
	}

}

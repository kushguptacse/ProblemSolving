package com.daa.graph;

public class GraphAdjacencyNode {

	private int index;
	private int weight = Integer.MAX_VALUE;


	public GraphAdjacencyNode(int index) {
		super();
		this.index = index;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "[i=" + index + ", w=" + weight + "]";
	}
}

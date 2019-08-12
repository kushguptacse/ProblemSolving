package com.daa.graph;

/**
 * Holds index and weight of a Graph node.
 * 
 * @author G521885
 *
 */
public class GraphAdjacencyNode implements Comparable<GraphAdjacencyNode> {

	private int index;
	private int weight;

	public GraphAdjacencyNode(int index, int weight) {
		this.index = index;
		this.weight = weight;
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

	@Override
	public int compareTo( GraphAdjacencyNode node2) {
		if (this.getWeight() < node2.getWeight())
			return -1;
		if (this.getWeight() > node2.getWeight())
			return 1;
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + weight;
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		return weight == ((GraphAdjacencyNode) obj).weight;
	}
}

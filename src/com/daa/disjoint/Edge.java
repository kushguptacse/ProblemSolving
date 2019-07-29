package com.daa.disjoint;

public class Edge {

	private int src;
	private int dest;

	/**
	 * @return the src
	 */
	public int getSrc() {
		return src;
	}

	/**
	 * @param src the src to set
	 */
	public void setSrc(int src) {
		this.src = src;
	}

	/**
	 * @return the dest
	 */
	public int getDest() {
		return dest;
	}

	/**
	 * @param dest the dest to set
	 */
	public void setDest(int dest) {
		this.dest = dest;
	}

	/**
	 * @param src
	 * @param dest
	 */
	public Edge(int src, int dest) {
		super();
		this.src = src;
		this.dest = dest;
	}

	/**
	 * 
	 */
	public Edge() {
		super();
		// TODO Auto-generated constructor stub
	}

}

package com.daa.model;

public class Pair<K, V> {

	private K first;
	private V second;

	public Pair(K first, V second) {
		super();
		this.first = first;
		this.second = second;
	}

	public Pair() {

	}

	/**
	 * @return the first
	 */
	public K getFirst() {
		return first;
	}

	/**
	 * @param first the first to set
	 */
	public void setFirst(K first) {
		this.first = first;
	}

	/**
	 * @return the second
	 */
	public V getSecond() {
		return second;
	}

	/**
	 * @param second the second to set
	 */
	public void setSecond(V second) {
		this.second = second;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Pair [first=" + first + ", second=" + second + "]";
	}

}

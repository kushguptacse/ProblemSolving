package com.daa.algo.greedy;

public class KnapsackItem {
	/**
	 * holds the profit/weight ratio
	 */
	private Double ratio;
	/**
	 * index of the item
	 */
	private int index;

	/**
	 * @return the ratio
	 */
	public Double getRatio() {
		return ratio;
	}

	/**
	 * @param ratio the ratio to set
	 */
	public void setRatio(Double ratio) {
		this.ratio = ratio;
	}

	/**
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * @param index the index to set
	 */
	public void setIndex(int index) {
		this.index = index;
	}

	/**
	 * @param ratio
	 * @param index
	 */
	public KnapsackItem(double ratio, int index) {
		super();
		this.ratio = ratio;
		this.index = index;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "[pw=" + ratio + ", i=" + index + "]";
	}

}

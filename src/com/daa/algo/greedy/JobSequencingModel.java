package com.daa.algo.greedy;

public class JobSequencingModel {
	private char name;
	private int profit;
	private int deadline;

	/**
	 * @param name
	 * @param profit
	 * @param deadline
	 */
	public JobSequencingModel(char name, int profit, int deadline) {
		this.name = name;
		this.profit = profit;
		this.deadline = deadline;
	}

	/**
	 * @return the profit
	 */
	public int getProfit() {
		return profit;
	}

	/**
	 * @param profit the profit to set
	 */
	public void setProfit(int profit) {
		this.profit = profit;
	}

	/**
	 * @return the deadline
	 */
	public int getDeadline() {
		return deadline;
	}

	/**
	 * @param deadline the deadline to set
	 */
	public void setDeadline(int deadline) {
		this.deadline = deadline;
	}

	/**
	 * @return the name
	 */
	public char getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(char name) {
		this.name = name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "[n=" + name + ", p=" + profit + ", d=" + deadline + "]";
	}

}

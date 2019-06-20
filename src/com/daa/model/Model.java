package com.daa.model;

public class Model<T> {

	public Model(T value) {
		this.value = value;
	}

	public Model() {
	}

	private T value;

	/**
	 * @return the value
	 */
	public T getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(T value) {
		this.value = value;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Model [value=" + value + "]";
	}
}

package com.daa.hashing.probing;

import com.daa.math.MathUtil;

public class HashMapLinear {

	private final HashItemProbing[] table;
	private static final int DEFAULT_CAPACITY = 11;

	/**
	 * 
	 */
	public HashMapLinear() {
		super();
		table = new HashItemProbing[DEFAULT_CAPACITY];
	}

	public HashMapLinear(int capacity) {
		table = new HashItemProbing[capacity];
	}

	public void put(int key, int value) {
		int i = index(key);
		while (table[i] != null && table[i].getKey() != key) {
			i = index(i + 1);
		}
		if (table[i] == null) {
			table[i] = new HashItemProbing(key, value);
		} else {
			table[i].setValue(value);
		}
	}

	public int get(int key) {
		int i = index(key);
		while (table[i] != null && table[i].getKey() != key) {
			i = index(i + 1);
		}
		return table[i] == null ? -1 : table[i].getValue();
	}

	private int index(int key) {
		return MathUtil.abs(key) % table.length;
	}

}

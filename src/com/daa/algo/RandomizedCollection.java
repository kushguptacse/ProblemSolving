package com.daa.algo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class RandomizedCollection {

	public static void main(String[] args) {
		RandomizedCollection set = new RandomizedCollection();
//		System.out.println(set.insert(4));
//		System.out.println(set.insert(3));
//		System.out.println(set.insert(4));
//		System.out.println(set.insert(2));
//		System.out.println(set.insert(4));
//		System.out.println(set.remove(4));
//		System.out.println(set.remove(3));
//		System.out.println(set.remove(4));
//		System.out.println(set.remove(4));
		System.out.println(set.insert(0));
		System.out.println(set.insert(1));
		System.out.println(set.remove(0));
		System.out.println(set.insert(2));
		System.out.println(set.remove(1));
	}

	private Map<Integer, List<Integer>> map = new HashMap<>();
	private List<Integer> list = new ArrayList<>();
	private Random r = new Random();

	/** Initialize your data structure here. */
	public RandomizedCollection() {

	}

	/**
	 * Inserts a value to the collection. Returns true if the collection did not
	 * already contain the specified element.
	 */
	public boolean insert(int val) {
		List<Integer> list1 = map.getOrDefault(val, new ArrayList<>());
		list1.add(list.size());
		list.add(val);
		if (list1.size() == 1) {
			map.put(val, list1);
			return true;
		}
		return false;
	}

	/**
	 * Removes a value from the collection. Returns true if the collection contained
	 * the specified element.
	 */
	public boolean remove(int val) {
		List<Integer> list1 = map.get(val);
		if (list1 == null || list1.isEmpty()) {
			return false;
		}
		int index = list1.get(list1.size() - 1);
		list1.remove(list1.size() - 1);
		if (list1.isEmpty()) {
			map.remove(val);
		}
		if (index != list.size() - 1) {
			int n = list.get(list.size() - 1);
			list.set(index, n);
			List<Integer> q = map.get(n);
			q.remove(q.size() - 1);
			q.add(index);
		}
		list.remove(list.size() - 1);
		return true;
	}

	/** Get a random element from the collection. */
	public int getRandom() {
		return list.get(r.nextInt(list.size()));
	}
}

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection(); boolean param_1 =
 * obj.insert(val); boolean param_2 = obj.remove(val); int param_3 =
 * obj.getRandom();
 */

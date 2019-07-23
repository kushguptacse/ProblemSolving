package com.daa.hashing.probing;

public class HashTestProbingApp {

	public static void main(String[] args) {
		HashMapLinear obj = new HashMapLinear();
		obj.put(1, 10);
		obj.put(11, 101);
		System.out.println(obj.get(1));
		System.out.println(obj.get(21));
		System.out.println(obj.get(11));
		obj.put(12, 102);
		System.out.println(obj.get(12));
		obj.put(2, 20);
		obj.put(1, 999);
		System.out.println(obj.get(2));
		System.out.println(obj.get(22));
		System.out.println(obj.get(1));
	}

}

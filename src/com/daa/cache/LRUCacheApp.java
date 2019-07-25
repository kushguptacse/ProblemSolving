package com.daa.cache;

public class LRUCacheApp {
	public static void main(String[] args) {
		LeastRecentlyUsed<Integer,String> cache = new LeastRecentlyUsed<>();
		cache.put(0, "A");
		cache.put(1, "B");
		cache.put(2, "C");
		cache.put(3, "D");
		cache.put(4, "E");
		cache.put(5, "F");
		cache.put(6, "G");
		
		System.out.println("6 "+cache.get(6));
		cache.print();
		
		System.out.println("3 "+cache.get(3));
		cache.print();
		
		System.out.println("4 "+cache.get(4));
		cache.print();
		System.out.println("1 "+cache.get(1));
		cache.print();
		cache.put(1, "kush");
		cache.print();
		cache.put(7, "luv");
		cache.print();
	}
}

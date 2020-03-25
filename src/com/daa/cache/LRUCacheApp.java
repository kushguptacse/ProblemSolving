package com.daa.cache;

public class LRUCacheApp {
	public static void main(String[] args) {
		testLRUcacheSimplified();
		LeastRecentlyUsed<Integer, String> cache = new LeastRecentlyUsed<>();
		cache.put(0, "A");
		cache.put(1, "B");
		cache.put(2, "C");
		cache.put(3, "D");
		cache.put(4, "E");
		cache.put(5, "F");
		cache.put(6, "G");

		System.out.println("6 " + cache.get(6));
		cache.print();

		System.out.println("3 " + cache.get(3));
		cache.print();

		System.out.println("4 " + cache.get(4));
		cache.print();
		System.out.println("1 " + cache.get(1));
		cache.print();
		cache.put(1, "kush");
		cache.print();
		cache.put(7, "luv");
		cache.print();
	}

	private static void testLRUcacheSimplified() {
		LRUCacheSimplified cache = new LRUCacheSimplified(2);
		cache.put(1, 1);
		cache.put(2, 2);
		System.out.println(cache.get(1)); // returns 1
		cache.put(3, 3); // evicts key 2
		System.out.println(cache.get(2)); // returns -1 (not found)
		cache.put(4, 4); // evicts key 1
		System.out.println(cache.get(1)); // returns -1 (not found)
		System.out.println(cache.get(3)); // returns 3
		System.out.println(cache.get(4)); // returns 4
		System.out.println("-----------------------");
		LRUCacheSimplified cache1 = new LRUCacheSimplified(2);
		System.out.println(cache1.get(2));
		cache1.put(2, 6);
		System.out.println(cache1.get(1));
		cache1.put(1, 5);
		cache1.put(1, 2);
		System.out.println(cache1.get(1));
		System.out.println(cache1.get(2));
	}
}

package com.daa.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * 1. since we want to retrieve data in o(1) we will use hashmap. And we will
 * use double-linked-list to store data. 
 * 2. Head and last are used to hold linked list. 
 * 3. we will remove from last and insert at head.
 * 4. when we get item. In that case we remove it and insert node at head. 
 * 5. when we put if capacity is full. We remove from last and insert node at head. 
 * 6. to simplify exceptional cases like only last or only head is present or delete last or
 * head element. We keep head and last as dummy value with 0 data. 
 * 7. so, we are always sure that head and last always exist and we use map.size to know
 * current capacity.
 *
 */
public class LRUCacheSimplified {
	class Node {
		int key;
		int value;
		Node prev;
		Node next;

		Node(int k, int v) {
			key = k;
			value = v;
		}
	}

	private Map<Integer, Node> map = new HashMap<>();
	private Node head;
	private Node last;
	private int capacity;

	public LRUCacheSimplified(final int capacity) {
		this.capacity = capacity;
		head = new Node(0, 0);
		last = new Node(0, 0);
		head.next = last;
		last.prev = head;
	}

	public int get(int key) {
		Node node = map.get(key);
		if (node == null) {
			return -1;
		}
		remove(node);
		insert(node);
		return node.value;
	}

	private void insert(Node node) {
		map.put(node.key, node);
		node.next = head.next;
		head.next.prev = node;
		node.prev = head;
		head.next = node;
	}

	public void put(int key, int value) {
		if (map.containsKey(key)) {
			remove(map.get(key));
		}
		if (map.size() == capacity) {
			remove(last.prev);
		}
		insert(new Node(key, value));
	}

	private void remove(Node node) {
		map.remove(node.key);
		node.prev.next = node.next;
		node.next.prev = node.prev;
	}

}

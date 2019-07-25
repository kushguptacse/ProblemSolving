package com.daa.cache;

import java.util.HashMap;
import java.util.Map;

import com.daa.list.DNode;
import com.daa.list.DoublyLinkedList;

public class LeastRecentlyUsed<K, V> {

	private final DoublyLinkedList<K, V> list = new DoublyLinkedList<>();
	private final Map<K, DNode<K, V>> map = new HashMap<>();
	private final int lruCapacity;

	public LeastRecentlyUsed() {
		lruCapacity = 6;
	}

	public LeastRecentlyUsed(int capacity) {
		this.lruCapacity = capacity;
	}

	/**
	 * put the data in the LRU cache. 
	 * 
	 * @f:off
	 * Steps-
	 * 
	 * 1. if key exists - update node data. remove node from list. add that node to head.
	 * to head.
	 * 2. else - check if cache is full. 
	 * if yes remove tail node and remove it's entry from map. 
	 * after that add new node to map and head of the list.
	 * 
	 * @f:on
	 * @param key
	 * @param value
	 */
	public void put(K key, V value) {
		// for simplicity key and value null is not allowed
		if (key == null || value == null) {
			return;
		}
		if (map.containsKey(key)) {
			// overwrite case - update node data. remove node from list. add that node to head.
			DNode<K, V> node = map.get(key);
			node.setValue(value);
			list.remove(node);
			list.addToHead(node);
		} else {
			if (list.size() == lruCapacity) {
				// cache full case - remove from map. remove from tail. add new node to head and map.
				map.remove(list.getTail().getKey());
				list.removeFromTail();
			}
			DNode<K, V> node = new DNode<>(key, value);
			map.put(key, node);
			list.addToHead(node);
		}

	}

	/**
	 * Get the value from LRU.
	 * 
	 * @f:off
	 * 
	 * Steps -
	 * 1. find node and remove that node and add it to head.
	 * 
	 * @f:on
	 * 
	 * @param key
	 * @return value
	 */
	public V get(K key) {
		DNode<K, V> node = map.get(key);
		if (node == null) {
			return null;
		}
		if (node != list.getHead()) {
			list.remove(node);
			list.addToHead(node);
		}
		return node.getValue();
	}

	public void print() {
		System.out.println("Data  in LRU cache: ");
		list.print();
	}

	/**
	 * @return the size
	 */
	public int size() {
		return list.size();
	}

}

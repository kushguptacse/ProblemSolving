package com.daa.hashing;

public class HashMap<K, V> {

	private static final int DEFAULT_CAPACITY = 10;

	private int size;

	private HashItem<K, V>[] table;

	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	public HashMap() {
		table = (HashItem<K, V>[]) new HashItem[DEFAULT_CAPACITY];
	}

	@SuppressWarnings("unchecked")
	public HashMap(int capacity) {
		table = (HashItem<K, V>[]) new HashItem[capacity];
	}

	/**
	 * put the element in the map. if two key has same index add element to last of the
	 * linkedList present at that index. if two key has same index and are equals. it just
	 * replaces the old value with new one and return old value. else return null;
	 * 
	 * @param key
	 * @param value
	 * @return old value associated with key
	 */
	public V put(K key, V value) {

		int index = hashCode(key);
		if (table[index] == null) {
			table[index] = new HashItem<>(key, value);
			size++;
		} else {
			HashItem<K, V> temp = table[index];
			HashItem<K, V> prev = null;
			while (temp != null) {
				if (temp.getKey().equals(key)) {
					V res = temp.getValue();
					temp.setValue(value);
					return res;
				}
				prev = temp;
				temp = temp.getNext();
			}
			if (prev != null) {
				prev.setNext(new HashItem<>(key, value));
				size++;
			}
		}
		return null;
	}

	/**
	 * return 0 if key is null.
	 * 
	 * @param key
	 * @return index of table array
	 */
	private int hashCode(K key) {
		if (key == null) {
			return 0;
		}
		if (key.hashCode() == 0) {
			return table.length - 1;
		}
		return key.hashCode() % (table.length - 1);
	}

	/**
	 * Get the value of associated with key passed
	 * 
	 * @param key
	 * @return search value or null
	 */
	public V get(K key) {
		int index = hashCode(key);
		if (table[index] != null) {
			return findValue(key, index);
		}

		return null;
	}

	/**
	 * find the value associated with key passed
	 * 
	 * @param key
	 * @param index
	 * @return search value or null
	 */
	private V findValue(K key, int index) {
		HashItem<K, V> temp = table[index];
		while (temp != null) {
			if (temp.getKey().equals(key)) {
				return temp.getValue();
			}
			temp = temp.getNext();
		}
		return null;
	}

	/**
	 * TODO
	 * 
	 * @param key
	 * @return
	 */
	public boolean remove(K key) {
		return false;
	}

	/**
	 * @return the size
	 */
	public int size() {
		return size;
	}

}

package com.daa.hashing;

/**
 * HashMap - with chaining collision resolution technique
 * 
 * @author G521885
 *
 * @param <K>
 * @param <V>
 */
public class HashMapChaining<K, V> {

	private static final int DEFAULT_CAPACITY = 10;

	private int size;

	private HashItem<K, V>[] table;

	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	public HashMapChaining() {
		table = (HashItem<K, V>[]) new HashItem[DEFAULT_CAPACITY];
	}

	@SuppressWarnings("unchecked")
	public HashMapChaining(int capacity) {
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
			}
		}
		size++;
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
		HashItem<K, V> temp = findNode(key);
		return temp == null ? null : temp.getValue();
	}

	private HashItem<K, V> findNode(K key) {
		int index = hashCode(key);
		HashItem<K, V> temp = table[index];
		while (temp != null) {
			if (temp.getKey() !=null && temp.getKey().equals(key)) {
				return temp;
			}
			temp = temp.getNext();
		}
		return null;
	}

	/**
	 * remove element from hashMap. return null if not removed or value is null itself for key
	 * removed.
	 * same as java behavior
	 * 
	 * 
	 * @param key
	 * @return removed value
	 */
	public V remove(K key) {
		int index = hashCode(key);
		HashItem<K, V> prev = null;
		HashItem<K, V> temp = table[index];
		while (temp != null) {
			if (temp.getKey() != null && temp.getKey().equals(key)) {
				V value = temp.getValue();
				if (prev == null) {
					if (temp.getNext() == null) {
						table[index] = null;
					} else {
						table[index] = temp.getNext();
					}
				} else {
					prev.setNext(temp.getNext());
				}
				size--;
				return value;
			}
			prev = temp;
			temp = temp.getNext();
		}
		return null;
	}

	/**
	 * @return the size
	 */
	public int size() {
		return size;
	}

}

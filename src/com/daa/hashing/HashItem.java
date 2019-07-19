package com.daa.hashing;

public class HashItem<K, V> {

	private K key;
	private V value;
	private HashItem<K, V> next;

	public HashItem() {

	}

	public HashItem(K key, V value) {
		this.key = key;
		this.value = value;
	}

	/**
	 * @return the key
	 */
	public K getKey() {
		return key;
	}

	/**
	 * @param key the key to set
	 */
	public void setKey(K key) {
		this.key = key;
	}

	/**
	 * @return the value
	 */
	public V getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(V value) {
		this.value = value;
	}

	/**
	 * @return the next
	 */
	public HashItem<K, V> getNext() {
		return next;
	}

	/**
	 * @param next the next to set
	 */
	public void setNext(HashItem<K, V> next) {
		this.next = next;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "[key=" + key + ", value=" + value + "]";
	}

}

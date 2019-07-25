package com.daa.list;

public class DNode<K, V> {

	private V value;
	private DNode<K, V> next;
	private DNode<K, V> prev;
	private K key;

	/**
	 * @param value
	 * @param key
	 */
	public DNode(K key, V value) {
		super();
		this.value = value;
		this.key = key;
	}

	public DNode() {
		super();
	}

	/**
	 * @return the next
	 */
	public DNode<K, V> getNext() {
		return next;
	}

	/**
	 * @param next the next to set
	 */
	public void setNext(DNode<K, V> next) {
		this.next = next;
	}

	/**
	 * @return the prev
	 */
	public DNode<K, V> getPrev() {
		return prev;
	}

	/**
	 * @param prev the prev to set
	 */
	public void setPrev(DNode<K, V> prev) {
		this.prev = prev;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "[value=" + value + ", key=" + key + "]";
	}

}

package com.daa.list;

public interface List<T> {

	/**
	 * 
	 * @return size of list
	 */
	int size();
	
	/**
	 * Add item to the last
	 * @param item
	 * @return true if added successfully
	 */
	boolean add(T item);
	
	/**
	 * Add item to the specific index
	 * @param item
	 * @param index
	 * @return true if added successfully
	 */
	boolean add(int index,T item);
	
	/**
	 * remove item
	 * @param item
	 * @return removed item
	 */
	boolean remove(T item);
	
	/**
	 * remove item by index
	 * @param index
	 * @return removed item
	 */
	T remove(int index);
	
	void print();

}

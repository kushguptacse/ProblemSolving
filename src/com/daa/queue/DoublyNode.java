package com.daa.queue;

public class DoublyNode<T extends Comparable<T>> {

	private T data;
	private DoublyNode<T> next;
	private DoublyNode<T> prev;

	public DoublyNode(T data) {
		this.data = data;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public DoublyNode<T> getNext() {
		return next;
	}

	public void setNext(DoublyNode<T> next) {
		this.next = next;
	}

	public void print() {
		DoublyNode<T> node = this;
		System.out.println();
		while (node != null) {
			System.out.print(node.getData() + "->");
			node = node.getNext();
		}
		System.out.println();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "[data=" + data + "]";
	}

	/**
	 * @return the prev
	 */
	public DoublyNode<T> getPrev() {
		return prev;
	}

	/**
	 * @param prev the prev to set
	 */
	public void setPrev(DoublyNode<T> prev) {
		this.prev = prev;
	}
}

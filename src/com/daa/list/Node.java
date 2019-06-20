package com.daa.list;

public class Node<T extends Comparable<T>> {

	private T data;
	private Node<T> next;
	
	public Node(T data) {
		this.data = data;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Node<T> getNext() {
		return next;
	}

	public void setNext(Node<T> next) {
		this.next = next;
	}
	
	public void print() {
		Node<T> node = this;
		System.out.println();
		while(node!=null) {
			System.out.print(node.getData()+"->");
			node=node.getNext();
		}
		System.out.println();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Node [data=" + data + "]";
	}
}

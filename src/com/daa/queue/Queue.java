package com.daa.queue;

import com.daa.list.Node;

/**
 * Insert data in the FIFO order
 * 
 * insertion should be done into rear end and deletion from front end
 * 
 * @author G521885
 *
 */
public class Queue<T extends Comparable<T>> {

	private Node<T> front;
	private Node<T> rear;
	private int size;
	
	public void add(T data) {
		Node<T> node = new Node<>(data);
		node.setNext(rear);
		if(rear==null) {
			front=node;
		}
		rear=node;
		size++;
	}
	
	public T poll() {
		if(rear==null) {
			return null;
		}
		if(front==rear) {
			front=null;
			rear=null;
			return null;
		}
		Node<T> temp = rear;
		while(temp.getNext()!=front) {
			temp=temp.getNext();
		}
		temp.setNext(null);
		T data = front.getData();
		front=temp;
		size--;
		return data;
	}
	
	public void print() {
		Node<T> temp = rear;
		while(temp!=null) {
			System.out.print(temp.getData()+" ");
			temp=temp.getNext();
		}
		System.out.println();
	}

	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}
	
	public T peek() {
		if(front==null) {
			return null;
		}
		return front.getData();
	}

}

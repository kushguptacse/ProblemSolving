package com.daa.queue;

/**
 * Insert data in the FIFO order
 * 
 * insertion should be done into rear end and deletion from front end
 * 
 * @author G521885
 *
 */
public class DeQueue<T extends Comparable<T>> {

	private DoublyNode<T> front;
	private DoublyNode<T> rear;
	private int size;

	/**
	 * insert data from rear end o(1)
	 * 
	 * @param data
	 */
	public void addRear(T data) {
		DoublyNode<T> node = new DoublyNode<>(data);
		if (front == null) {
			front = node;
		} else {
			rear.setNext(node);
			node.setPrev(rear);
		}
		rear = node;
		size++;
	}

	/**
	 * remove data from front end. o(1)
	 * 
	 * @return removed item.
	 */
	public T pollFront() {
		if (front == null) {
			return null;
		}
		T data= front.getData();
		front = front.getNext();
		if (front == null) {
			rear = null;
		} else {
			front.setPrev(null);
		}
		size--;
		return data;
	}

	public void print() {
		DoublyNode<T> temp = rear;
		while (temp != null) {
			System.out.print(temp.getData() + " ");
			temp = temp.getNext();
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
		return front == null ? null : front.getData();
	}

}

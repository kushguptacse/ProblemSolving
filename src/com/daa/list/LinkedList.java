package com.daa.list;

public class LinkedList<T extends Comparable<T>> implements List<T>{

	private Node<T> head;
	private int size;
	
	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean add(T item) {
		Node<T> newNode = new Node<>(item);
		if(head==null) {
			head=newNode;
			size++;
			return true;
		}
		Node<T> temp = head;
		while (temp.getNext()!=null) {
			temp=temp.getNext();
		}
		size++;
		temp.setNext(newNode);
		return true;
	}

	@Override
	public boolean remove(T item) {
		if(head==null) {
			return false;
		}
		
		if(item.equals(head.getData())) {
			head=head.getNext();
			size--;
			return true;
		}
		
		Node<T> temp = head.getNext();
		Node<T> prev = head;
		while(temp!=null) {
			if(item.equals(temp.getData())) {
				prev.setNext(temp.getNext());
				size--;
				return true;
			}
			prev=prev.getNext();
			temp=temp.getNext();
		}
		
		return false;
	}

	@Override
	public void print() {
		Node<T> temp = head;
		while(temp!=null) {
			System.out.print(temp.getData()+"->");
			temp=temp.getNext();
		}
		System.out.println();
	}

	@Override
	public boolean add(int index, T item) {
		Node<T> newNode = new Node<T>(item);
		if (index<0 || index>size) {
			return false;
		}
		if (index == 0) {
			newNode.setNext(head);
			head = newNode;
			size++;
			return true;
		}

		int count = 1;
		Node<T> temp = head;
		while (temp != null) {
			if (count == index) {
				newNode.setNext(temp.getNext());
				temp.setNext(newNode);
				size++;
				return true;
			}
			temp = temp.getNext();
			count++;
		}

		return false;
	}

	@Override
	public T remove(int index) {

		if (head == null || index < 0 || index > size) {
			return null;
		}

		if (index == 0) {
			T data = head.getData();
			head = head.getNext();
			size--;
			return data;
		}

		Node<T> current = head.getNext();
		Node<T> prev = head;
		int count = 1;
		while (prev != null) {
			if (count == index) {
				T data = current.getData();
				prev.setNext(current.getNext());
				size--;
				return data;
			}
			count++;
			prev = prev.getNext();
			current = current.getNext();
		}

		return null;
	}
	
	/**
	 * O(N/2) 
	 * @return content of MiddleNode
	 */
	public T findMiddleNode() {
		Node<T> fast = head;
		Node<T> slow = head;
		while (fast.getNext() != null && fast.getNext().getNext() != null) {
			slow = slow.getNext();
			fast = fast.getNext().getNext();
		}

		return slow == null ? null : slow.getData();
	}
	
	/**
	 * 
	 * Reverse link list O(N) . take three pointer prev curr and last.
	 * prev point to previous
	 * curr to the current intially head 
	 * last point next node to the curr.
	 * 
	 */
	public void reverse() {
		
		if(head==null||head.getNext()==null) {
			return;
		}
		
		Node<T> last = head.getNext();
		Node<T> prev = null;
		Node<T> temp=head;
		while ( last!=null) {
			temp.setNext(prev);
			prev = temp;
			temp = last;
			last = last.getNext();
		}
		temp.setNext(prev);
		head = temp;
	}

	/**
	 * @return the head
	 */
	public Node<T> getHead() {
		return head;
	}

	public boolean checkPalindrome() {
		return checkPalindromeString(head);
	}

	private Node<T> left=head;
	private boolean checkPalindromeString(Node<T> node) {
		if (left == null || node == null) {
			return true;
		}

		boolean res = checkPalindromeString(node.getNext());

		if (res && left.getData().equals(node.getData())) {
			left = left.getNext();
			return true;
		}
		return false;

	}
	
	
	
}

package com.daa.list;

import java.util.stream.IntStream;

public final class LinkedListUtil {
	private LinkedListUtil() {

	}

	public static void main(String[] args) {
		LinkedList<Character> list = new LinkedList<>();
		list.add('A');
		list.add('B');
		list.add('C');
		list.add('B');
		list.add('A');
		list.print();
		System.out.println(list.checkPalindrome());
		LinkedList<Integer> list1 = new LinkedList<>();
		IntStream.of(1, 2, 4, 7).forEach(list1::add);
		insertInSortedList(list1.getHead(), 5).print();
	}

	/**
	 * On a given sorted list. insert data in sorted manner.
	 * 
	 * O(n)
	 * 
	 * @f:off
	 * e.g. - 
	 * Input - 
	 * 1->2->4->7 , 3
	 * Output -
	 * 1->2->3->4->7
	 * @f:on
	 * 
	 * @param head
	 * @return head node
	 */
	public static Node<Integer> insertInSortedList(Node<Integer> head, int item) {
		if (head == null) {
			return head;
		}
		Node<Integer> newNode = new Node<>(item);
		Node<Integer> prev = head;
		if (item < prev.getData()) {
			newNode.setNext(head);
			head = newNode;
		} else {
			while (prev.getNext() != null && prev.getNext().getData() < item) {
				prev = prev.getNext();
			}
			newNode.setNext(prev.getNext());
			prev.setNext(newNode);
		}
		return head;

	}

	/**
	 * merge two sorted list
	 * @param node1
	 * @param node2
	 * @return merge sorted list
	 */
	public static Node<Integer> mergeSortedLists(Node<Integer> node1, Node<Integer> node2) {
		if (node1 == null) {
			return node2;
		}
		if (node2 == null) {
			return node1;
		}
		if (node1.getData() < node2.getData()) {
			node1.setNext(mergeSortedLists(node1.getNext(), node2));
			return node1;
		} else {
			node2.setNext(mergeSortedLists(node1, node2.getNext()));
			return node2;
		}
	}

	public static int size(Node<Integer> head) {
		int i = 0;
		while (head != null) {
			i++;
			head = head.getNext();
		}
		return i;
	}

}

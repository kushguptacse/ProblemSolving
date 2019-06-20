package com.daa.list;

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
	}

	/**
	 * 
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
		int i=0;
		while(head!=null) {
			i++;
			head=head.getNext();
		}
		return i;
	}

}

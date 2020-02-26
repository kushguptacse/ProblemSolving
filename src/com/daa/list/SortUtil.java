package com.daa.list;

public class SortUtil {

	public static void main(String[] args) {
		LinkedList<Integer> list = new LinkedList<>();
		list.add(-3);
		list.add(34);
		list.add(0);
		list.add(2);
		list.add(14);
		list.add(8);
		list.add(-1);
		list.add(24);
		list.print();
		insertionSort(list.getHead());
		list.print();
	}

	/**
	 * Sort the single linked list data in ascending order using insertion sort
	 * 
	 * @f:off
	 * 
	 * o(n2),leetcode,geeks
	 * 
	 * approach - 
	 * 1.curr node will point to current iteration element.
	 * 2.prev will point to previous node after which element will be needed to insert
	 * 3.dummy - use to hold the result modified sorted list.
	 * Steps - 
	 * 1.Start curr from head and set dummy as least integer and prev also.
	 * 2.if item to be inserted is greater then prev node. we need to start searching from start i.e. from dummy. so, set prev=dummy
	 * 3.now loop till we find position of new item. i.e. where it is less then prev.next or prev.next is null. 
	 * In this way we now know after prev we can add that item as after that other elements are greater.
	 * 4.now just add element between prev and prev.next
	 * 5.update curr node to next element of the loop.
	 * @f:on
	 * 
	 * @param head
	 * @return head of the modified sorted list
	 */
	public static Node<Integer> insertionSort(Node<Integer> head) {
		Node<Integer> dummy = new Node<Integer>(Integer.MIN_VALUE);
		Node<Integer> curr = head;
		Node<Integer> prev = dummy;
		while (curr != null) {
			// Store nextNode for next iteration 
			Node<Integer> nextNode = curr.getNext();
			
			// to save checking from start- below condition is used
			if (prev.getData() > curr.getData()) {
				prev = dummy;
			}
			// go to a point where we need to insert new item starting from prev.
			while (prev.getNext() != null && prev.getNext().getData() < curr.getData()) {
				prev = prev.getNext();
			}
			// insert current node between prev and prev.next
			curr.setNext(prev.getNext());
			prev.setNext(curr);
			curr = nextNode;
		}
		return dummy.getNext();
	}

}

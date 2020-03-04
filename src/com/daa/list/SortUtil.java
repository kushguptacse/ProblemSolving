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
//		list.add(5);
		list.print();
		bubbleSort(list.getHead());
//		insertionSort(list.getHead());
//		selectionSort(list.getHead());
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

	/**
	 * Apply selection sort to sort the list.
	 * 
	 * @f:off
	 * 1. In it we find min in Linked list and swap it with 0 index and then start
	 * searching min again from 1 to n and now swap min with 1 and so on. 
	 * 2. For swapping we will swap content of the data not the node itself. 
	 * 3. It is an in-place algorithm.
	 * @f:on
	 * 
	 * o(n^2)
	 * 
	 * @param head
	 * @return head Node
	 */
	public static Node<Integer> selectionSort(Node<Integer> head) {

		Node<Integer> temp = head;
		while (temp != null) {
			Node<Integer> minNode = temp;
			Node<Integer> dummy = temp.getNext();
			while (dummy != null) {
				if (dummy.getData() < minNode.getData()) {
					minNode=dummy;
				}
				dummy=dummy.getNext();
			}
			int data = minNode.getData();
			minNode.setData(temp.getData());
			temp.setData(data);
			temp = temp.getNext();
		}

		return head;
	}

	/**
	 * 
	 * 1. Compare 0 element with 1 and arrange them. Then it take 1 element with 2
	 * and arrange. So after 1st iteration largest element moved to the end of
	 * array. From next iteration onwards we will start from 0 and ignore last
	 * element as it is already in the correct position. 
	 * 
	 * 2. Stable, In-place
	 * 
	 * o(n^2)
	 * 
	 * @param head
	 * @return head
	 */
	public static Node<Integer> bubbleSort(Node<Integer> head) {
		Node<Integer> end = null;
		while (end != head) {
			Node<Integer> next = head;
			while (next.getNext() != null && next.getNext() != end) {
				if (next.getData() > next.getNext().getData()) {
					int t = next.getData();
					next.setData(next.getNext().getData());
					next.getNext().setData(t);
				}
				next = next.getNext();
			}
			end = next;
		}

		return head;
	}
	
}

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
		System.out.println("is palindrome : "+list.checkPalindrome());
		LinkedList<Integer> list2 = new LinkedList<>();
		IntStream.of(1, 2, 2, 2, 1).forEach(list2::add);
		list2.print();
		System.out.println("is palindrome : "+checkPalindromeIterative(list2.getHead()));
		LinkedList<Integer> list1 = new LinkedList<>();
		IntStream.of(1, 2, 4, 7).forEach(list1::add);
		System.out.println(detectCycle(list1.getHead()));
		
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

	/**
	 * check if a linked list has a cycle
	 * take two pointers one slow and other fast. 
	 * if they meet there is a cycle.
	 * 
	 * o(n),o(1)
	 * 
	 * leetcode 
	 * 
	 * @param head
	 * @return true if exists.
	 */
	public static boolean hasCycle(Node<Integer> head) {
		Node<Integer> slow = head;
		Node<Integer> fast = head;
		while (fast != null && fast.getNext() != null) {
			slow = slow.getNext();
			fast = fast.getNext().getNext();
			if (slow == fast) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 
	 * @param head
	 * @return null if no cycle else return the starting point of loop
	 */
	public static Node<Integer> detectCycle(Node<Integer> head) {
        Node<Integer> slow = head;
		Node<Integer> fast = head.getNext();
		while (fast != null && fast.getNext() != null) {
			slow = slow.getNext();
			fast = fast.getNext().getNext();
			if (slow == fast) {
				slow=head;
				while(slow!=fast) {
					slow=slow.getNext();
					fast=fast.getNext();
				}
				return fast;
			}
		}
        return null;
    }
	
	/**
	 * Write a program to find the node at which the intersection of two singly
	 * linked lists begins.
	 * 
	 * o(n)
	 * 
	 * @f:off
	 * Get count of the nodes in the first list, let count be c1.
	 * Get count of the nodes in the second list, let count be c2.
	 * Get the difference of counts d = abs(c1 – c2)
	 * Now traverse the bigger list from the first node till d nodes so that from here onwards both the lists have equal no of nodes.
	 * Then we can traverse both the lists in parallel till we come across a common node. 
	 * (Note that getting a common node is done by comparing the address of the nodes)
	 * @f:on
	 * 
	 * @param headA
	 * @param headB
	 * @return intersection node or null if list does not intersect
	 */
	public Node<Integer> getIntersectionNode(Node<Integer> headA, Node<Integer> headB) {
		int lenA = getLength(headA);
		int lenB = getLength(headB);
		Node<Integer> first = null;
		Node<Integer> second = null;
		int diff = 0;
		if (lenA > lenB) {
			first = headA;
			second = headB;
			diff = lenA - lenB;
		} else {
			first = headB;
			second = headA;
			diff = lenB - lenA;
		}
		for (int i = 0; i < diff; i++) {
			first = first.getNext();
		}
		while (first != null && second != null) {
			if (first == second) {
				return first;
			}
			first = first.getNext();
			second = second.getNext();
		}
		return null;
	}

	/**
	 * calculate length of the list
	 * 
	 * @param temp
	 * @return total number of nodes starting from temp
	 */
	public int getLength(Node<Integer> temp) {
		int len = 0;
		while (temp != null) {
			temp = temp.getNext();
			len++;
		}
		return len;
	}
	
	/**
	 * @f:off
	 * 2. In Iterative solution-> 
	 * 2.1 First find the middle of the linked list.
	 * After that set middle.next as null. So that we have now two linked list. 
	 * 2.2 Reverse one of the linked list and now compare one by one both linked list
	 * and if item different list is not palindrome.
	 * @f:on
	 * 
	 * @param head
	 * @return true if palindrome exists
	 */
	public static boolean checkPalindromeIterative(Node<Integer> head) {
		if (head == null || head.getNext() == null) {
			return true;
		}
		// step1 find middle of the linked list
		Node<Integer> middle = findMiddle(head);
		// step2 partition the list into two halves- right and head
		Node<Integer> right = middle.getNext();
		middle.setNext(null);
		// reverse one half
		right = reverse(right);
		//iterate with smaller half not null. in this way we ignore middle odd element if present.
		while (right != null) {
			if (head.getData() != right.getData()) {
				return false;
			}
			head = head.getNext();
			right = right.getNext();
		}
		return true;
	}

	/**
	 * 
	 * Reverse link list O(N). 
	 * 
	 * take three pointer prev curr and next.
	 * prev point to previous
	 * curr to the current initially head 
	 * last point next node to the curr.
	 * 
	 */
	private static Node<Integer> reverse(Node<Integer> head) {
		Node<Integer> prev = null;
		while (head != null) {
			Node<Integer> next = head.getNext();
			head.setNext(prev);
			prev = head;
			head = next;
		}
		return prev;
	}

	/**
	 * @f:off
	 * Find the middle element of the linked list.
	 * 
	 * Input: [1,2,3,4,5,6]
	 * Output: Node 3
	 *  
	 * If we want output to be 4 in such case run loop -
	 * while (fast != null && fast.getNext() != null) 
	 * @f:on
	 * 
	 * o(n)
	 * 
	 * @param head
	 * @return middle element
	 */
	public static Node<Integer> findMiddle(Node<Integer> head) {
		if (head == null) {
			return head;
		}
		Node<Integer> fast = head;
		Node<Integer> slow = head;
		while (fast.getNext() != null && fast.getNext().getNext() != null) {
			fast = fast.getNext().getNext();
			slow = slow.getNext();
		}
		return slow;
	}
	
}

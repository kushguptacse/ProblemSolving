package com.daa.list;

import java.util.stream.IntStream;

public final class LinkedListUtil {
	private LinkedListUtil() {

	}

	public static void main(String[] args) {
		LinkedList<Integer> list3 = new LinkedList<>();
		IntStream.of(1, 2, 3, 4, 5).forEach(list3::add);
		oddEvenList(list3.getHead()).print();
		LinkedList<Character> list = new LinkedList<>();
		list.add('A');
		list.add('B');
		list.add('C');
		list.add('B');
		list.add('A');
		list.print();
		System.out.println("is palindrome : " + list.checkPalindrome());
		LinkedList<Integer> list2 = new LinkedList<>();
		IntStream.of(1, 2, 2, 2, 1).forEach(list2::add);
		list2.print();
		System.out.println("is palindrome : " + checkPalindromeIterative(list2.getHead()));
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
		int i = 0;
		while (head != null) {
			i++;
			head = head.getNext();
		}
		return i;
	}

	/**
	 * check if a linked list has a cycle take two pointers one slow and other fast.
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
				slow = head;
				while (slow != fast) {
					slow = slow.getNext();
					fast = fast.getNext();
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
	 * Get the difference of counts d = abs(c1 � c2)
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
		// iterate with smaller half not null. in this way we ignore middle odd element
		// if present.
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
	 * take three pointer prev curr and next. prev point to previous curr to the
	 * current initially head last point next node to the curr.
	 * 
	 */
	public static Node<Integer> reverse(Node<Integer> head) {
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
	 * reverse linked list.
	 * 
	 * o(n),o(n)
	 * 
	 * @param head
	 * 
	 * @return head
	 */
	public Node<Integer> reverseListRecursive(Node<Integer> head) {
        return reverseRecursive(head,null);
    }
    
	/**
	 * reverse the list
	 * 
	 * o(n),o(n)
	 * @param head
	 * @param prev
	 * @return head
	 */
    private Node<Integer> reverseRecursive(Node<Integer> head,Node<Integer> prev){
        if(head==null){
            return prev;
        }
        Node<Integer> next=head.getNext();
        head.setNext(prev);
        return reverseRecursive(next,head);
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

	/**
	 * Given a singly linked list, group all odd nodes together followed by the even
	 * nodes. Please note here we are talking about the node number and not the
	 * value in the nodes.
	 * 
	 * Input: 2->1->3->5->6->4->7->NULL 
	 * 
	 * Output: 2->3->6->7->1->5->4->NULL
	 * 
	 * @f:off
	 *  Approach -
	 *	1.hold odd and even node, now in loop first update odd node
	 *	odd.next=even.next and odd=even.next;
	 *	2.now if odd.next is null. that means we dont have anything to be append to even node. so set even.next=null
	 *	3.else there is element. so , set even.next=odd.next and even=even.next
	 *	4.once loop finishes we have two different nodes -> odd will be at end element of itself and headEven which we already stored earlier will be starting of even
	 *	5.so, set odd.next=headEven;
	 * @f:on
	 * 
	 * o(n) time,o(1) space
	 * 
	 * 
	 * @param head
	 * @return head
	 */
	public static Node<Integer> oddEvenList(Node<Integer> head) {
		if (head == null || head.getNext() == null || head.getNext().getNext() == null) {
			return head;
		}
		Node<Integer> odd = head;
		Node<Integer> even = head.getNext();
		Node<Integer> evenHead = even;
		while (even != null && even.getNext() != null) {
			odd.setNext(even.getNext());
			odd = odd.getNext();
			if (odd.getNext() == null) {
				even.setNext(null);
			} else {
				even.setNext(odd.getNext());
				even = even.getNext();
			}
		}
		odd.setNext(evenHead);
		return head;
	}

	/**
	 * Given a linked list, rotate the list to the right by k places, where k is
	 * non-negative. 
	 * 
	 * @f:off
	 * 
	 * Input: 1->2->3->4->5->NULL, k = 2 
	 * Output: 4->5->1->2->3->NULL
	 * Explanation: 
	 * rotate 1 steps to the right: 5->1->2->3->4->NULL 
	 * rotate 2 steps to the right: 4->5->1->2->3->NULL
	 * 
	 * Input: 0->1->2->NULL, k = 4
	 * Output: 2->0->1->NULL
	 * Explanation:
	 * rotate 1 steps to the right: 2->0->1->NULL
	 * rotate 2 steps to the right: 1->2->0->NULL
	 * rotate 3 steps to the right: 0->1->2->NULL
	 * rotate 4 steps to the right: 2->0->1->NULL
	 * 
	 * @f:on
	 * 
	 * o(n),o(1),leetcode
	 * 
	 * @param head
	 * @param k
	 * @return head of modified list
	 */
	public Node<Integer> rotateRight(Node<Integer> head, int k) {
		if (head == null || head.getNext() == null || k == 0) {
			return head;
		}
		// find length
		int len = 1;
		Node<Integer> last = head;
		while (last.getNext() != null) {
			last = last.getNext();
			len++;
		}
		k = k % len;
		if (k == 0) {
			return head;
		}
		// update k = len - k
		k = len - k;
		Node<Integer> temp = head;
		// goto till k. i.e. break point
		for (int i = 1; i < k; i++) {
			temp = temp.getNext();
		}
		Node<Integer> newHead = temp.getNext();
		temp.setNext(null);
		last.setNext(head);
		return newHead;
	}

	/**
	 * Given a linked list, remove the n-th node from the end of list and return its
	 * head.
	 * 
	 * Example:
	 * 
	 * Given linked list: 1->2->3->4->5, and n = 2.
	 * 
	 * After removing the second node from the end, the linked list becomes
	 * 1->2->3->5. Note:
	 * 
	 * Given n will always be valid.
	 * 
	 * o(n),leetcode
	 * 
	 * @param head
	 * @param n
	 * @return head of modified list
	 */
	public Node<Integer> removeNthFromEnd(Node<Integer> head, int n) {
		Node<Integer> curr = head;
		for (int i = 0; i < n; i++) {
			curr = curr.getNext();
		}
		if (curr == null) {
			return head.getNext();
		}
		Node<Integer> curr2 = head;
		while (curr.getNext() != null) {
			curr2 = curr2.getNext();
			curr = curr.getNext();
		}
		curr2.setNext(curr2.getNext().getNext());
		return head;
	}
	
	/**
	 * Given a linked list, swap every two adjacent nodes and return its head.
	 * 
	 * You may not modify the values in the list's nodes, only nodes itself may be changed.
	 * 
	 * Example:
	 * 
	 * Given 1->2->3->4, you should return the list as 2->1->4->3.
	 * 
	 * @param head
	 * @return head
	 */
	public static Node<Integer> swapPairsRecursive(Node<Integer> head) {
		if (head == null || head.getNext() == null) {
			return head;
		}
		Node<Integer> second = head.getNext();
		Node<Integer> third = second.getNext();
		second.setNext(head);
		head.setNext(swapPairsRecursive(third));
		return second;
	}

}

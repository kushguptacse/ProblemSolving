package com.daa.bitwise;

import com.daa.list.Node;

public class BitWisePractice {

	/**
	 * Given head which is a reference node to a singly-linked list. The value of
	 * each node in the linked list is either 0 or 1. The linked list holds the
	 * binary representation of a number.
	 * 
	 * Input: head = [1,0,1] 
	 * 
	 * Output: 5
	 * 
	 * o(n)
	 * 
	 * @param head
	 * @return decimal equivalent of binary number
	 */
	public static int getDecimalValue(Node<Integer> head) {
		int number = 0;
		Node<Integer> temp = head;
		while (temp != null) {
			number = (number * 2) + temp.getData();
			temp = temp.getNext();
		}
		return number;
	}
	
}

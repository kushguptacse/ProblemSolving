package com.daa.bitwise;

import com.daa.list.LinkedList;
import com.daa.list.Node;

public class BitWisePractice {

	public static void main(String[] args) {
		LinkedList<Integer> list = new LinkedList<>();
		list.add(1);
		list.add(0);
		list.add(1);
		list.add(0);
		list.print();
		int dec = getDecimalValue(list.getHead());
		System.out.println(dec);
	}

	/**
	 * Given head which is a reference node to a singly-linked list. The value of
	 * each node in the linked list is either 0 or 1. The linked list holds the
	 * binary representation of a number. find decimal equivalent of binary number.
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
			number = (number << 1) + temp.getData();
			temp = temp.getNext();
		}
		return number;
	}

	/**
	 * Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND
	 * of all numbers in this range, inclusive.
	 * 
	 * Example 1:
	 * 
	 * Input: [5,7] 
	 * 
	 * Output: 4
	 * 
	 * right shift by 1 will remove last digit from binary representation of number
	 * left shift by 1 will add 0 after last digit in binary representation of
	 * number
	 * 
	 * @param m
	 * @param n
	 * @return bitwise and of numbers in range m to n.
	 */
	public int rangeBitwiseAnd(int m, int n) {
		int c = 0;
		while (m != n) {
			m = m >> 1;
			n = n >> 1;
			c++;
		}
		return n << c;
	}

}

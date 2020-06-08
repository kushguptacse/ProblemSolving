package com.daa.bitwise;

import com.daa.list.LinkedList;
import com.daa.list.Node;

public class BitWisePractice {

	public static void main(String[] args) {
		System.out.println(isPowerOfThree(243));
		System.out.println(isPowerOfTwo(7));
		System.out.println(5 | 8);
		LinkedList<Integer> list = new LinkedList<>();
		list.add(1);
		list.add(0);
		list.add(1);
		list.add(0);
		list.print();
		int dec = getDecimalValue(list.getHead());
		System.out.println(dec);
	}

//	public static boolean isPowerOfTwo(int n) {
//		if (n <= 0)
//			return false;
//		int digit = (int) (Math.log(n) / Math.log(2));
//		return (1 << digit) == n;
//	}

	public static boolean isPowerOfTwo(int n) {
		if (n <= 0)
			return false;
		return (n & n - 1) == 0;
	}

	public static boolean isPowerOfThree(int n) {
		if (n <= 0) {
			return false;
		}
		int val = (int) Math.round((Math.log(n) / Math.log(3)));
		return (int) Math.round(Math.pow(3, val)) == n;
	}

	public static boolean isPowerOfFour(int num) {
		if (num <= 0)
			return false;
		int i = num & (num - 1);// check if number is power of two.
		int digit = (int) (Math.log(num) / Math.log(2));
		return i == 0 && digit % 2 == 0;
	}

	/**
	 * Given a non negative integer number num. For every numbers i in the range 0 ≤
	 * i ≤ num calculate the number of 1's in their binary representation and return
	 * them as an array.
	 * 
	 * Example 1:
	 * 
	 * Input: 2 Output: [0,1,1]
	 * 
	 * Example 2:
	 * 
	 * Input: 5 Output: [0,1,1,2,1,2]
	 * 
	 * @param num
	 * @return
	 */
	public static int[] countBits(int num) {
		int[] res = new int[num + 1];
		for (int i = 1; i < res.length; i++) {
			res[i] = res[i >> 1] + (i & 1);
		}
		return res;
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

	/**
	 * Given a positive integer, output its complement number. The complement
	 * strategy is to flip the bits of its binary representation.
	 * 
	 * 
	 * Example 1:
	 * 
	 * Input: 5
	 * 
	 * Output: 2
	 * 
	 * Explanation: The binary representation of 5 is 101 (no leading zero bits),
	 * and its complement is 010. So you need to output 2.
	 * 
	 * @param num
	 * @return complement of number
	 */
	public int findComplement(int num) {
		int digit = (int) (Math.log(num) / Math.log(2)) + 1;
		int mask = (1 << digit) - 1;
		return num ^ mask;
	}

}

package com.daa.algo;

import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.stream.IntStream;

import com.daa.array.ArrayUtil;
import com.daa.list.LinkedListUtil;
import com.daa.list.Node;

public class Recurrsion {
	public static void main(String[] args) {
		Recurrsion recurrsion = new Recurrsion();
		int n = 6;
		int[] arr = new int[] { 1, 2, 3, 4, 5 };
		System.out.println(recurrsion.binarySearch(arr, 1));
		System.out.println(recurrsion.contains("elf", "waffles"));
		System.out.println(recurrsion.max(new int[] { 8, 133, 3, 14, 511 }));
		System.out.println(recurrsion.sum(n));
		System.out.println((n * (n + 1)) / 2);
		System.out.println(recurrsion.multiply(3, 4));
		System.out.println(recurrsion.power(3, 4));
		System.out.println(recurrsion.gcd(54, 24));
		System.out.println(recurrsion.lcm(9, 12));
		System.out.println(recurrsion.reverse("tset rorrim"));
		System.out.println(recurrsion.sumOfSquares(3));
		System.out.println(recurrsion.sumOfSeries(2));
		System.out.println(recurrsion.equalizeArray(new int[] {50,100 }));
		Deque<Integer> stack = new LinkedList<>();
		IntStream.of(2,3,1,14,8).forEach(stack::push);
		System.out.println("original stack "+stack);
		recurrsion.reverse(stack);
		System.out.println("reverse stack "+stack);
		recurrsion.sort(stack);
		System.out.println("sorted stack "+stack);
		int[] ar= new int[] {11,12,13};
		recurrsion.insertionSort(ar);
		System.out.println("-----------------");
		Arrays.stream(ar).forEach(o->System.out.print(o+" , "));
		System.out.println();
		ar = ArrayUtil.mergeTwoSortedArray(new int[] {2,6,41,118}, new int[] {1,23,50,117});
		System.out.println("-----------------");
		Arrays.stream(ar).forEach(o->System.out.print(o+" , "));
		Node<Integer> node1 = new Node<>(1);
		node1.setNext(new Node<>(3));
		node1.getNext().setNext(new Node<>(5));
		
		Node<Integer> node2 = new Node<>(4);
		node2.setNext(new Node<Integer>(8));
		System.out.print("\n-------Merge two sorted list----------");
		node1.print();
		node2.print();
		Node<Integer> result = LinkedListUtil.mergeSortedLists(node1, node2);
		result.print();
	}

	public int max(int[] a) {
		return max(a, a.length - 1);
	}

	private int max(int[] a, int n) {
		if (n == 0) {
			return a[0];
		}
		int high = max(a, n - 1);
		if (a[n] < high) {
			return high;
		} else {
			return a[n];
		}
	}

	public int printFibonacci(int n) {
		int a = 0;
		int b = 1;
		int c = 0;
		System.out.print(a + " " + b + " ");
		for (int i = 2; i <= n; i++) {
			c = a + b;
			System.out.print(c + " ");
			a = b;
			b = c;
		}
		System.out.println();
		return b;
	}

	public int sumFibonacci(int n) {
		if (n <= 1) {
			return n;
		}

		int res = sumFibonacci(n - 1);
		int r2 = sumFibonacci(n - 2);
		return res + r2;
	}

	/**
	 * 
	 * @param n
	 * @return factorial
	 */
	public int fact(int n) {
		if (n == 1 || n == 0) {
			return 1;
		}
		return n * fact(n - 1);
	}

	/**
	 * move n disc from tower x to tower y
	 * 
	 * @param n
	 * @param x
	 * @param y
	 * @param z
	 * @return number of steps required
	 */
	public int towerOfHanoi(int n, String x, String y, String z) {
		int step = 0;
		if (n > 0) {
			step = towerOfHanoi(n - 1, x, z, y);
			System.out.println("move disc number " + n + " from tower " + x + " to " + y);
			step++;
			step = step + towerOfHanoi(n - 1, z, y, x);
		}
		return step;
	}

	/**
	 * 
	 * @param n
	 * @return sum of n natural number
	 */
	public int sum(int n) {
		if (n <= 1) {
			return 1;
		}
		return n + sum(n - 1);
	}

	/**
	 * return a*b
	 * 
	 * @param a
	 * @param b
	 * @return multiply a and b
	 */
	public int multiply(int a, int b) {
		if (b == 1) {
			return a;
		}
		return a + multiply(a, b - 1);
	}

	/**
	 * calculate a^b
	 * 
	 * @param a
	 * @param b
	 * @return a^b
	 */
	public int power(int a, int b) {
		if (b == 1) {
			return a;
		}

		return a * power(a, b - 1);

	}

	/**
	 * out of all divisors of a and b. the highest common divisor is GCD or HCF for
	 * 20 and 60 . gcd is 20
	 * 
	 * All possible factors of 20 are 1,2,4,5,10 and 20 All possible factors of 60
	 * are 1,3,4,5,6,10,12,15,20,30,60
	 * 
	 * Approach - here if we subtract smallest from largest repeatedly till we reach
	 * a==b. that value is HCF. a=54 and b=24 e.g. -> 54-24 = 30 (24,30) 30-24=6
	 * (6,24) 24-6=18 (6,18) 18-6=12 (6,12) 12-6=6 (6,6)
	 * 
	 * @param a
	 * @param b
	 * 
	 * @return gcd
	 */
	public int gcd(int a, int b) {
		if (a == b) {
			return a;
		}
		if (a < b) {
			return gcd(a, b - a);
		}
		return gcd(a - b, b);
	}

	/**
	 * find all the prime factors of a and b. LCM is the union of all prime factors
	 * of the two numbers for e.g. - 9 , 12 3*3 and 3*2*2 union (LCM)- 3*2*2*3=36
	 * 
	 * Approach 1- relationship between LCM and HCF -> lcm = (a*b)/hcf.
	 * 
	 * Approach 2- start adding the max to the result till we get result%small ==0
	 * result is the lCM.
	 * 
	 * e.g. - a=12,b=9 (12+12)%9 (24+12)%9 so answer is 36
	 * 
	 * @param a
	 * @param b
	 * @return lcm of two number
	 */
	public int lcm(int a, int b) {
		if (a < b) {
			return lcm(b, a, b);
		}
		return lcm(a, b, a);

	}

	private int lcm(int a, int b, int i) {
		if (a % b == 0) {
			return a;
		}
		return lcm(a + i, b, i);
	}

	/**
	 * reverse of string
	 * 
	 * @param orig
	 * @return reverse of string
	 */
	public String reverse(String orig) {
		if(orig.isEmpty()) {
			return "";
		}
		return reverse(orig.substring(1))+orig.charAt(0);
	}

	/**
	 * Reverse array
	 * 
	 * @param ch
	 * @return reverse array
	 */
	public char[] reverseArray(char[] ch) {
		reverseArray(ch, 0, ch.length - 1);
		return ch;
	}

	/**
	 * reverse the character array
	 * 
	 * @param ch
	 * @param start
	 * @param end
	 */
	private void reverseArray(char[] ch, int start, int end) {
		if (start >= ch.length / 2) {
			return;
		}
		char temp = ch[start];
		ch[start] = ch[end];
		ch[end] = temp;
		reverseArray(ch, start + 1, end - 1);
	}

	
	/**
	 * check whether second string contains all the character of first string at
	 * least once. e.g. -
	 * 
	 * @param first
	 * @param second
	 * @return true if second string contains all the characters of first string in
	 *         any order
	 */
	public boolean contains(String first, String second) {
		Set<Character> set = new HashSet<>();
		for (int i = 0; i < second.length(); i++) {
			set.add(second.charAt(i));
		}
		return contains(first.toCharArray(), set, 0);
	}

	/**
	 * 
	 * @param first
	 * @param second
	 */
	private boolean contains(char[] first, Set<Character> set, int index) {
		if (index < first.length) {
			if (set.add(first[index])) {
				return false;
			} else {
				return contains(first, set, index + 1);
			}
		}
		return true;
	}

	/**
	 * linearly search the item in array
	 * 
	 * @param arr
	 * @param item
	 * @return index else -1
	 */
	public int linearSearch(int[] arr, int item) {
		return search(item, arr, 0);
	}

	private int search(int item, int[] arr, int i) {
		if (arr.length == i) {
			return -1;
		}
		if (item == arr[i]) {
			return i;
		}
		return search(item, arr, i + 1);
	}

	public int binarySearch(int[] arr, int item) {
		int end = arr.length - 1;
		return binarySearch(arr, item, 0, end);
	}

	private int binarySearch(int[] arr, int item, int start, int end) {
		if (start > end) {
			return -1;
		}
		int mid = (start + end) / 2;
		if (arr[mid] == item) {
			return mid;
		}
		if (item < arr[mid]) {
			return binarySearch(arr, item, start, mid - 1);
		} else {
			return binarySearch(arr, item, mid + 1, end);
		}
	}

	/**
	 * Sum of the series 1^2 + 2^2 + 3^2 + ….. + n^2 using recursion
	 * 
	 * @param n
	 * @return sum
	 */
	public int sumOfSquares(int n) {
		if (n == 1) {
			return 1;
		}
		return (n * n) + sumOfSquares(n - 1);
	}

	/**
	 * Sum of the series 1^1 + 2^2 + 3^3 + ….. + n^n using recursion
	 * 
	 * @param n
	 * @return sum
	 */
	public long sumOfSeries(int n) {
		if (n == 1) {
			return 1;
		}
		long res = 1;
		for (int i = 1; i <= n; i++) {
			res = n * res;
		}
		return res + sumOfSeries(n - 1);
	}

	/**
	 * Equalize array -
	 * 
	 * The task is to make all the array elements equal with the given operation. In
	 * a single operation, any element of the array can be either multiplied by 2 or
	 * by 3.
	 * 
	 * @return true if array can be equalized
	 */
	public boolean equalizeArray(int[] arr) {
		equalizeArray(arr, 0);

		for (int i = 0; i < arr.length - 1; i++) {
			if (arr[i] != arr[i + 1]) {
				return false;
			}
		}
		return true;
	}

	private void equalizeArray(int[] arr, int index) {
		if (index < arr.length) {
			if (arr[index] % 2 == 0) {
				arr[index] = arr[index] / 2;
				equalizeArray(arr, index);
			}
			if (arr[index] % 3 == 0) {
				arr[index] = arr[index] / 3;
				equalizeArray(arr, index);
			}
			equalizeArray(arr, index + 1);
		}

	}
	
	/**
	 * Reverse the stack
	 * 
	 * @param stack
	 */
	public void reverse(Deque<Integer> stack) {
		if(stack.isEmpty()) {
			return;
		}
		int item = stack.pop();
		reverse(stack);
		insertAtStackBottom(stack,item);
	}

	/**
	 * sort the stack
	 * 
	 * @param st
	 */
	public void sort(Deque<Integer> st) {
		if(st.isEmpty()) {
			return;
		}
		int item =st.pop();
		sort(st);
		insertInSortedOrder(st,item);
	}
	
	private void insertInSortedOrder(Deque<Integer> st, int item) {
		if(st.isEmpty()|| st.peek()<item) {
			st.push(item);
		} else {
			int a = st.pop();
			insertInSortedOrder(st, item);
			st.push(a);
		}
	}

	private void insertAtStackBottom(Deque<Integer> stack, int item) {
		if(stack.isEmpty()) {
			stack.push(item);
		} else {
			int item1 = stack.pop();
			insertAtStackBottom(stack, item);
			stack.push(item1);
		}
	}
	
	public void insertionSort(int[] arr) {
//		for(int i=1;i<arr.length;i++) {
//			for(int j=0;j<i;j++) {
//				if(arr[i]<arr[j]) {
//					swap(arr,i,j);
//				}
//			}
//		}
		
		insertionSort(arr,1,0);
		
	}

	private void insertionSort(int[] arr, int i,int j) {
		if(i==arr.length) {
			return;
		}
		if(j<i) {
			if(arr[i]<arr[j]) {
				swap(arr,i,j);
			}
			insertionSort(arr,i,j+1);
		}
		insertionSort(arr, i+1, 0);
	}

	private void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i]=arr[j];
		arr[j]=temp;
	}
	

}

package com.daa.algo;

import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

import com.daa.math.MathUtil;

public class Recurrsion {

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
	 * out of all divisors of a and b. the highest common divisor is GCD or HCF for 20 and 60
	 * . gcd is 20
	 * 
	 * All possible factors of 20 are 1,2,4,5,10 and 20 All possible factors of 60 are
	 * 1,3,4,5,6,10,12,15,20,30,60
	 * 
	 * Approach - here if we subtract smallest from largest repeatedly till we reach a==b.
	 * that value is HCF. a=54 and b=24 e.g. -> 54-24 = 30 (24,30) 30-24=6 (6,24) 24-6=18
	 * (6,18) 18-6=12 (6,12) 12-6=6 (6,6)
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
	 * find all the prime factors of a and b. LCM is the union of all prime factors of the two
	 * numbers for e.g. - 9 , 12 3*3 and 3*2*2 union (LCM)- 3*2*2*3=36
	 * 
	 * Approach 1- relationship between LCM and HCF -> lcm = (a*b)/hcf.
	 * 
	 * Approach 2- start adding the max to the result till we get result%small ==0 result is
	 * the lCM.
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
		if (orig.isEmpty()) {
			return "";
		}
		return reverse(orig.substring(1)) + orig.charAt(0);
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
	 * check whether second string contains all the character of first string at least once.
	 * e.g. -
	 * 
	 * @param first
	 * @param second
	 * @return true if second string contains all the characters of first string in any order
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
		System.out.println("Item is present in index : ");
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
	 * The task is to make all the array elements equal with the given operation. In a single
	 * operation, any element of the array can be either multiplied by 2 or by 3.
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
	 * 0-1 knapsack problem. our aim is to get the maximum profit. You cannot break an item,
	 * either pick the complete item, or don’t pick it (0-1 property). here greedy method
	 * fails as we cannot take fractional part.
	 * 
	 * o(2^n) time complexity. as we have to consider each item and for every item we have two
	 * options either to include it or not. for better approach see DP algo for 0/1 knapsack.
	 * 
	 * @f:off
	 * 								 __
	 * So, for item knapsack(i,wi)= | 
	 * 								| max(knapsack(i-1,w),knapsack(i-1,w-wi)+pi)   , wi<w
	 *								| 0											   , i=0 || w=0
	 *								| knapsack(i-1,w)                   		   , wi>w	
	 *								|__
	 * For particular element i, we need to check three conditions - 
	 * 1.if it's weight exceeding remaining knapsack capacity. if yes, discard it and test for remaining i-1 element.
	 * 2.if remaining weight is 0 or i=0. return 0.
	 * 3.if it's weight is under capacity.find whether inclusion of it is increasing the profit or we should ignore it.
	 * i.e. in step 3 we find maximum of both cases.
	 * 
	 * @f:on
	 * 
	 * @param profit
	 * @param weight
	 * @param capacity
	 * @return max profit that can be achieved
	 * 
	 */
	public int knapsack(int[] profit, int[] weight, int capacity) {
		return knapsackRec(profit, weight, capacity, weight.length - 1);
	}

	/**
	 * 
	 * @param profit
	 * @param weight
	 * @param capacity
	 * @param i
	 * @return max profit
	 */
	private int knapsackRec(int[] profit, int[] weight, int capacity, int i) {
		if (i == 0 || capacity == 0) {
			return 0;
		}
		if (capacity < weight[i]) {
			return knapsackRec(profit, weight, capacity, i - 1);
		}
		return MathUtil.max(knapsackRec(profit, weight, capacity, i - 1), knapsackRec(profit, weight, capacity - weight[i], i - 1) + profit[i]);
	}

	/**
	 * Reverse the stack
	 * 
	 * @param stack
	 */
	public void reverse(Deque<Integer> stack) {
		if (stack.isEmpty()) {
			return;
		}
		int item = stack.pop();
		reverse(stack);
		insertAtStackBottom(stack, item);
	}

	/**
	 * Problem statement-
	 * 
	 * We are given coin array. e.g. [1,2,3] and we want to get change of 4$. so, In how many
	 * ways we can do change. given - we can use same coin multiple times. The order of coins
	 * doesn’t matter. so, here result will contain - { [1,1,1,1],[1,1,2],[1,3],[2,2] } and
	 * answer will be 4.
	 * 
	 * Time Complexity - Every can has two possibility- to be included or not included in
	 * result set.so, for m coins - 2^m.
	 * 
	 * SEE DP section for better approach.
	 * 
	 * @param coins
	 * @param n     - sum to reach
	 * @return total
	 */
	public int coinChange(int[] coins, int n) {
		return coinChangeRec(coins, coins.length, n);
	}

	/**
	 * 
	 * @param coins
	 * @param m
	 * @param n     - sum to reach
	 * @return total possible ways to give change
	 */
	private int coinChangeRec(int[] coins, int m, int n) {
		// one solution exist if we want 0 rs change. i.e. not include any coin in set
		if (n == 0) {
			return 1;
		}
		// if index is less than 0 or n become negative return 0.
		if (m <= 0 || n < 0) {
			return 0;
		}
		// we partition the coin at index m into two set. first set contains the coin and second
		// does not
		return coinChangeRec(coins, m, n - coins[m - 1]) + coinChangeRec(coins, m - 1, n);
	}

	/**
	 * sort the stack
	 * 
	 * @param st
	 */
	public void sort(Deque<Integer> st) {
		if (st.isEmpty()) {
			return;
		}
		int item = st.pop();
		sort(st);
		insertInSortedOrder(st, item);
	}

	private void insertInSortedOrder(Deque<Integer> st, int item) {
		if (st.isEmpty() || st.peek() < item) {
			st.push(item);
		} else {
			int a = st.pop();
			insertInSortedOrder(st, item);
			st.push(a);
		}
	}

	private void insertAtStackBottom(Deque<Integer> stack, int item) {
		if (stack.isEmpty()) {
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
		insertionSort(arr, 1, 0);

	}

	private void insertionSort(int[] arr, int i, int j) {
		if (i == arr.length) {
			return;
		}
		if (j < i) {
			if (arr[i] < arr[j]) {
				swap(arr, i, j);
			}
			insertionSort(arr, i, j + 1);
		}
		insertionSort(arr, i + 1, 0);
	}

	private void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

}

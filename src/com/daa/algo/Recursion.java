package com.daa.algo;

import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

import com.daa.array.ArrayUtil;
import com.daa.math.MathUtil;

/**
 * Ravindra
 * 
 * tail Recursion - method is called at the last. it is just like for loop. head
 * Recursion - method is called in the beginning
 *
 */
public class Recursion {

	/**
	 * Given a non-negative integer num, return the number of steps to reduce it to
	 * zero. If the current number is even, you have to divide it by 2, otherwise,
	 * you have to subtract 1 from it.
	 * 
	 * 
	 * leetcode
	 * 
	 * o(n)
	 * 
	 * @f:off
	 * Example 1:
	 * 
	 * Input: num = 14 
	 * Output: 6 
	 * 
	 * Explanation: 
	 * Step 1) 14 is even; divide by 2 and obtain 7. 
	 * Step 2) 7 is odd; subtract 1 and obtain 6. 
	 * Step 3) 6 is even; divide by 2 and obtain 3. 
	 * Step 4) 3 is odd; subtract 1 and obtain 2. 
	 * Step 5) 2 is even; divide by 2 and obtain 1. 
	 * Step 6) 1 is odd; subtract 1 and obtain 0.
	 * @f:on
	 * 
	 * @param num
	 * @return number of steps
	 */
	public static int numberOfSteps(int num) {
		if (num == 0) {
			return 0;
		}
		if (num % 2 == 0) {
			return numberOfSteps(num / 2) + 1;
		} else {
			return numberOfSteps(num - 1) + 1;
		}
	}

	public void headRecursion(int n) {
		if (n == 0)
			return;
		headRecursion(n - 1);
		System.out.println(n);
	}

	public void tailRecursion(int n) {
		if (n == 0)
			return;
		System.out.println(n);
		tailRecursion(n - 1);
	}

	public void headAndTailRecursion(int n) {
		if (n == 0)
			return;
		System.out.println(n);
		headAndTailRecursion(n - 1);
		System.out.println(n);
	}

	public boolean isPalindrome(String str) {
		return isPalindrome(str, 0, str.length() - 1);
	}

	private boolean isPalindrome(String str, int i, int length) {
		if (i >= length) {
			return true;
		}
		if (str.charAt(i) == str.charAt(length)) {
			return isPalindrome(str, i + 1, length - 1);
		}
		return false;
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

	public void printFibonacci(int n) {
		if (n >= 0) {
			printFibonacci(n - 1);
			System.out.print(sumFibonacci(n) + " ");
		}
	}

	public void printFibonacciReverse(int n) {
		if (n >= 0) {
			System.out.print(sumFibonacci(n) + " ");
			printFibonacciReverse(n - 1);
		}
	}

	public int sumFibonacci(int n) {
		return n < 2 ? n : sumFibonacci(n - 1) + sumFibonacci(n - 2);
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
	 * udemy
	 * 
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
			// move n-1 discs from x to z. so that after that we can directly move last disc
			// from x to y.
			step = towerOfHanoi(n - 1, x, z, y);
			// i.e. we are moving disc number 1 from x to y finally.
			System.out.println("move disc number " + n + " from tower " + x + " to " + y);
			// increment step to include above 1 move.
			step++;
			// after above move we have all n-1 disc in z. and x is empty and destination is
			// y. so we need to move now n-1 discs from y to z keeping x as auxiliary
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
	 * @f:off
	 * Euclidean algorithms -
	 * 
	 * If we subtract smaller number from larger (we reduce larger number), GCD doesn’t change. 
	 * So if we keep subtracting repeatedly the larger of two, we end up with GCD. 
	 * Now instead of subtraction, if we divide
	 * smaller number, the algorithm stops when we find remainder 0.

	 * O(Log min(a, b))
	 * @f:on
	 * 
	 * @param a
	 * @param b
	 * @return GCD OR HCF
	 */
	public int gcdBest(int a, int b) {
		if (b == 0) {
			return a;
		}
		return gcd(b, a % b);
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
	public int[] reverseArray(int[] ch) {
		reverseArray(ch, 0, ch.length - 1);
		return ch;
	}

	/**
	 * reverse the array
	 * 
	 * @param ch
	 * @param start
	 * @param end
	 */
	private void reverseArray(int[] ch, int start, int end) {
		if (start >= ch.length / 2) {
			return;
		}
		ArrayUtil.swap(ch, start, end);
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
		return linearSearch(arr, item, 0);
	}

	/**
	 * No pre-sorted array needed
	 * 
	 * o(n)
	 * 
	 * @param arr
	 * @param item
	 * @param i
	 * @return index of the item found else -1
	 */
	private int linearSearch(int[] arr, int item, int i) {
		if (i == arr.length) {
			return -1;
		}
		if (arr[i] == item) {
			return i;
		}
		return linearSearch(arr, item, i + 1);
	}

	/**
	 * it requires array to be already sorted to work correctly.
	 * 
	 * here we check from index start - mid if element to searched < mid else check
	 * from mid+1,end.
	 * 
	 * o(logn)
	 * 
	 * @param arr
	 * @param item
	 * @return index of item else -1
	 */
	public int binarySearch(int[] arr, int item) {
		return binarySearchUtil(arr, item, 0, arr.length - 1);
	}

	/**
	 * 
	 * @param arr
	 * @param item
	 * @param start
	 * @param end
	 * @return index of element else -1
	 */
	private int binarySearchUtil(int[] arr, int item, int start, int end) {
		if (start > end) {
			return -1;
		}
		int mid = (start + end) / 2;
		if (arr[mid] == item) {
			return mid;
		}
		if (arr[mid] > item) {
			return binarySearchUtil(arr, item, start, mid - 1);
		}
		return binarySearchUtil(arr, item, mid + 1, end);
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
		return MathUtil.max(knapsackRec(profit, weight, capacity, i - 1),
				knapsackRec(profit, weight, capacity - weight[i], i - 1) + profit[i]);
	}

	/**
	 * Determine the maximum value obtainable by cutting up the rod and selling the pieces.
	 * 
	 * For example, if length of the rod is 8 and the values of different pieces are given as
	 * following, then the maximum obtainable value is 22 (by cutting in two pieces of lengths
	 * 2 and 6)
	 * @f:off
	 * length   | 1   2   3   4   5   6   7   8  
	 *	--------------------------------------------
	 * price    | 1   5   8   9  10  17  17  20
	 * 
	 * eg 2 - l=5m
	 * prices are :
	 * 1m = $2
	 * 2m = $5
	 * 3m = $7
	 * 4m = $3
	 * solution - {2,3} or {2,2,1}. here both will give same profit $12
	 * @f:on
	 * 
	 * @param arr - where index is the rod length and profit as value to that length
	 * @param l - length of the rod whom we want to make cuts
	 * @return maximum profit that can be achieved
	 */
	public int cutRod(int[] arr, int l) {
		return cutRodRec(arr, l, arr.length);
	}

	/**
	 * similar to knapsack. DP has better performance.
	 * 
	 * @param arr
	 * @param l
	 * @param i
	 * @return maximum profit that can be achieved
	 */
	private int cutRodRec(int[] arr, int l, int i) {
		if (l == 0 || i <= 0) {
			return 0;
		}
		if (i > l) {
			return cutRodRec(arr, l, i - 1);
		}
		return MathUtil.max(cutRodRec(arr, l, i - 1), cutRodRec(arr, l - i, i) + arr[i - 1]);
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
	 * given a subset and Sum S we need to tell whether there exists a subset of the
	 * set which can have sum S
	 * 
	 * similar to coin change
	 * 
	 * @param set
	 * @param sum
	 * @return
	 */
	public boolean subsetSumExists(int[] set, int s) {
		return subsetSumExistsRec(set, set.length, s);
	}

	/**
	 * here i starts with the last element index and if i<=0 return false. s is the
	 * sum to reach.
	 * 
	 * on every step if we consider the element to be included in set we subtract
	 * that element from s. when s reaches 0 return true as we have reach our sum.
	 * but if sum<0 it means not possible.
	 * 
	 * For better approach see DP
	 * 
	 * @param set
	 * @param i
	 * @param s
	 * @return true if sum is possible
	 */
	private boolean subsetSumExistsRec(int[] set, int i, int s) {
		if (s == 0) {
			return true;
		}
		if (i <= 0 || s < 0) {
			return false;
		}
		// here in both cases we need to consider next element and update the sum if we
		// are
		// including that element
		return subsetSumExistsRec(set, i - 1, s) || subsetSumExistsRec(set, i - 1, s - set[i - 1]);
	}

	/**
	 * Problem statement-
	 * 
	 * We are given coin array. e.g. [1,2,3] and we want to get change of 5$.
	 * 
	 * Given - we can use same coin multiple times. what is the minimum number of
	 * coins required to make change. The order of coins doesn’t matter. so, here
	 * result will be - [3,2] and answer will be 2.
	 * 
	 * Time Complexity - Every coin has two possibility- to be included or not
	 * included in result set.so, for m coins - 2^m.
	 * 
	 * SEE DP section for better approach.
	 * 
	 * @param coins
	 * @param n     - sum to reach
	 * @return total
	 */
	public int minCoinChange(int[] coins, int n) {
		return minCoinChangeRec(coins, coins.length, n);
	}

	/**
	 * 
	 * Approach - we need to take every coin in each step and see whether inclusion
	 * is less than required sum.
	 * 
	 * @param coins
	 * @param m     - current coin to be considered
	 * @param n     - amount we want to do change
	 * @return minimum coins require to reach sum n.
	 */
	private int minCoinChangeRec(int[] coins, int m, int n) {
		if (n == 0) {
			return 0;
		}
		int res = Integer.MAX_VALUE;
		for (int i = 0; i < m; i++) {
			if (coins[i] <= n) {
				int subres = minCoinChangeRec(coins, m, n - coins[i]);
				if (subres != Integer.MAX_VALUE && subres + 1 < res) {
					res = subres + 1;
				}
			}
		}
		return res;
	}

	/**
	 * Problem statement-
	 * 
	 * We are given coin array. e.g. [1,2,3] and we want to get change of 4$. so, In
	 * how many ways we can do change. given - we can use same coin multiple times.
	 * The order of coins doesn’t matter. so, here result will contain - {
	 * [1,1,1,1],[1,1,2],[1,3],[2,2] } and answer will be 4.
	 * 
	 * Time Complexity - Every coin has two possibility- to be included or not
	 * included in result set.so, for m coins - 2^m.
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
		// we partition the coin at index m into two set. first set contains the coin
		// and second
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

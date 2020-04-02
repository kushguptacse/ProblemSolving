package com.daa.math;

public class MathUtil {

	private MathUtil() {

	}

	public static int abs(int a) {
		return a < 0 ? -a : a;
	}

	public static int squareRoot(int num) {
		int result = 0;

		int start = 1;
		int end = num;

		while (start <= end) {
			int mid = (start + end) / 2;

			if (mid * mid == num) {
				System.out.println("perfect square");
				return mid;
			}

			if (mid * mid < num) {
				start = mid + 1;
				result = mid;
			} else {
				end = mid - 1;
			}

		}

		return result;
	}

	/**
	 * convert number to binary
	 * 
	 * 5 - 101
	 * 
	 * O(N)
	 * 
	 * @param n
	 * @return String of binary
	 */
	public static String intToBinary(int n) {
		StringBuilder res = new StringBuilder();
		while (n > 0) {
			int rem = n % 2;
			res.insert(0, rem);
			n = n / 2;
		}
		return res.toString();
	}

	/**
	 * 
	 * @param a
	 * @param b
	 * @return maximum of two numbers
	 */
	public static int max(Integer a, Integer b) {
		return a > b ? a : b;
	}

	/**
	 * 
	 * @param a
	 * @param b
	 * @return minimum of two numbers
	 */
	public static int min(Integer a, Integer b) {
		return a < b ? a : b;
	}

	/**
	 * Time Complexity - O(sqrt(N))
	 * 
	 * @param n
	 * @return true if number is prime
	 */
	public static boolean checkPrime(int n) {
		if (n == 1) {
			return false;
		}
		for (int i = 2; i * i <= n; i++) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * return max of the three integer
	 * 
	 * @param a
	 * @param b
	 * @param c
	 * @return
	 */
	public static int max(int a, int b, int c) {
		return (a >= b && a >= c) ? a : (b >= a && b >= c) ? b : c;
	}

	/**
	 * 
	 * @param arr
	 * @return max element of the array
	 */
	public static int findMax(int[] arr) {
		int max = arr[0];
		for (int i = 1; i < arr.length; i++) {
			if (max < arr[i]) {
				max = arr[i];
			}
		}
		return max;
	}

	/**
	 * 
	 * @param arr
	 * @return min element of the array
	 */
	public static int findMin(int[] arr) {
		int min = arr[0];
		for (int i = 1; i < arr.length; i++) {
			if (min > arr[i]) {
				min = arr[i];
			}
		}
		return min;
	}

	/**
	 * Reverse a given integer. For example if the input of the algorithm is 1234
	 * then the output should be 4321.
	 * 
	 * @param num
	 * @return reverse number
	 */
	public static int reverse(int num) {
		int res = 0;
		while (num > 0) {
			res = res * 10 + (num % 10);
			num = num / 10;
		}
		return res;
	}

	/**
	 * Determine whether an integer is a palindrome. An integer is a palindrome when
	 * it reads the same backward as forward.
	 * 
	 * Example 1:
	 * 
	 * Input: 121
	 * 
	 * Output: true
	 * 
	 * @param num
	 * @return true if palindrome
	 */
	public static boolean checkPalindrome(int num) {
		int num1 = num;
		int res = reverse(num);
		return num1 == res;
	}

	/**
	 * Write an algorithm to determine if a number is "happy".
	 * 
	 * A happy number is a number defined by the following process: Starting with
	 * any positive integer, replace the number by the sum of the squares of its
	 * digits, and repeat the process until the number equals 1 (where it will
	 * stay), or it loops endlessly in a cycle which does not include 1. Those
	 * numbers for which this process ends in 1 are happy numbers.
	 * 
	 * @f:off
	 * Input: 19
	 * Output: true
     * Explanation: 
     * 12 + 92 = 82
     * 82 + 22 = 68
     * 62 + 82 = 100
     * 12 + 02 + 02 = 1
	 * @f:on
	 * 
	 * Approach - 
	 * 1. Loop infinite and find squareAndSum of n.
	 * 2. If n reach to 7 or 1. it means it is happy number as 7 will also resolve to 1.
	 * 3. any other single number digit will never resolve to 1 or 7. 
	 * and hence we return false in such case
	 * 
	 * 
	 * @param n
	 * @return true if number ends to 1
	 */
	public boolean isHappy(int n) {
		if (n < 1) {
			return false;
		}
		while (true) {
			n = squareAndSum(n);
			if (n == 1 || n == 7)
				return true;
			if (n < 10)
				return false;
		}
	}

	private int squareAndSum(int n) {
		int sum = 0;
		while (n != 0) {
			int rem = n % 10;
			n = n / 10;
			sum = sum + (rem * rem);
		}
		return sum;
	}
}

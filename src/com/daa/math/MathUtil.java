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

}

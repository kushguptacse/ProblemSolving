package com.daa.algo.dp;

import com.daa.math.MathUtil;

public class PracticeDP {

	public static void main(String[] args) {
		System.out.println(test(5));
		System.out.println(testImprove(5));
		int a[] = { -2, -3, 4, -1, -2, 1, 5, -3 };
		int max_sum = maxSubArraySum(a);
		System.out.println("Maximum contiguous sum is " + max_sum);
	}

	public static int test(int n) {
		if (n < 2) {
			return 2;
		}
		int res = 0;
		for (int i = 1; i < n; i++) {
			res = res + 2 * test(i) * test(i - 1);
		}
		return res;
	}

	/**
	 * Given a array which might contain negative numbers. we need to find maximum sum that
	 * can be obtain by considering contiguous elements.
	 * 
	 * e.g. - int a[] = { -2, -3, 4, -1, -2, 1, 5, -3 };
	 * 
	 * output - 7. i.e. {4,-1,-2,1,5}
	 * 
	 * @param arr
	 * @return
	 */
	public static int maxSubArraySum(int[] arr) {
		int max = arr[0];
		int subMax = arr[0];
		for (int i = 1; i < arr.length; i++) {
			subMax = MathUtil.max(arr[i], subMax + arr[i]);
			max = MathUtil.max(max, subMax);
		}
		return max;
	}

	public static int testImprove(int n) {
		int[] table = new int[n + 1];
		table[0] = 2;
		table[1] = 2;
		table[2] = 8;
		for (int i = 3; i <= n; i++) {
			table[i] = table[i - 1] + 2 * table[i - 1] * table[i - 2];
		}
		return table[n];
	}
}

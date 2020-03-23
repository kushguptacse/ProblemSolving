package com.daa.stack;

import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.stream.IntStream;

public class StackPractice {

	public static void main(String[] args) {
		int[] op = nextGreatestElement(IntStream.of(11, 3, 13, 21, 5).toArray());
		System.out.println(Arrays.toString(op));
		op = nextGreatestElement(IntStream.of(2, 4).toArray(), IntStream.of(1, 2, 3, 4).toArray());
		System.out.println(Arrays.toString(op));
		op = nextGreatestElementCircular(IntStream.of(1, 22, 1).toArray());
		System.out.println(Arrays.toString(op));

	}

	/**
	 * Given an array, print the Next Greater Element (NGE) for every element. The
	 * Next greater Element for an element x is the first greater element on the
	 * right side of x in array. Elements for which no greater element exist,
	 * consider next greater element as -1.
	 * 
	 * e.g. - 
	 * @f:off
	 * For input array ->
	 * {4, 5, 2, 25}
	 * 
	 * Element       NGE
	 *	4      -->   5
   	 *	5      -->   25
   	 *	2      -->   25
   	 *	25     -->   -1
	 * 
	 * @f:on
	 * 
	 * It can be done using two loops where we first find the element and after that next greatest element.
	 * but it can be done in better way by using stack.
	 * algo - 
	 * 1.if stack is empty push item into stack
	 * 2. else check if top element from stack < current array element.
	 * 3.if yes pop element. that current array element is the next greatest element of the popped element.
	 * 4.Keep popping from the stack while the popped element is smaller than next. 
	 *   next becomes the next greater element for all such popped elements
	 * 5.Finally, push current element to the stack.
	 * 6.after the array is traversed completely the elements remained in the stack has -1 as next greatest element.
	 * 
	 * Since we wanted to return output array,
	 * we can store index of the element instead of actual element in stack. 
	 * and when we need to pop we uses it as a index of output to store the current element.
	 * 
	 * o(n)
	 * 
	 * @param arr
	 * @return output array
	 */
	public static int[] nextGreatestElement(int[] arr) {
		int n = arr.length;
		int[] res = new int[n];
		Deque<Integer> stack = new LinkedList<>();
		// fill array with -1
		for (int i = 0; i < n; i++) {
			res[i] = -1;
		}

		for (int i = 0; i < n; i++) {
			while (!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
				res[stack.pop()] = arr[i];
			}
			stack.push(i);
		}
		return res;
	}

	/**
	 * 
	 * You are given two arrays (without duplicates) nums1 and nums2 where nums1’s
	 * elements are subset of nums2. Find all the next greater numbers for nums1's
	 * elements in the corresponding places of nums2.
	 * 
	 * The Next Greater Number of a number x in nums1 is the first greater number to
	 * its right in nums2. If it does not exist, output -1 for this number.
	 * 
	 * Example 1:
	 * 
	 * Input: nums1 = [4,1,2], nums2 = [1,3,4,2].
	 * 
	 * Output: [-1,3,-1]
	 * 
	 * Example 2:
	 * 
	 * Input: nums1 = [2,4], nums2 = [1,2,3,4].
	 * 
	 * Output: [3,-1]
	 * 
	 * @param nums1
	 * @param nums2
	 * @return output array
	 */
	public static int[] nextGreatestElement(int[] nums1, int[] nums2) {
		Deque<Integer> stack = new LinkedList<>();
		Map<Integer, Integer> map = new HashMap<>();
		int[] output = new int[nums1.length];
		// here since nums1 is subset of nums2. so we can use nums2 as array to be store
		// on stack and keep track nge
		for (int i = 0; i < nums2.length; i++) {
			while (!stack.isEmpty() && stack.peek() < nums2[i]) {
				map.put(stack.pop(), nums2[i]);
			}
			stack.push(nums2[i]);
		}
		// and after that just filter out values present in nums1
		for (int i = 0; i < nums1.length; i++) {
			output[i] = map.getOrDefault(nums1[i], -1);
		}
		return output;
	}

	/**
	 * Find the next greatest element from array in a circular manner.
	 * 
	 * @param arr
	 * @return output array
	 */
	public static int[] nextGreatestElementCircular(int[] arr) {
		int n = arr.length;
		int[] res = new int[n];
		Deque<Integer> stack = new LinkedList<>();
		for (int i = 0; i < n; i++) {
			res[i] = -1;
		}
		for (int i = 0; i < n * 2; i++) {
			while (!stack.isEmpty() && arr[stack.peek()] < arr[i % n])
				res[stack.pop()] = arr[i % n];
			stack.push(i % n);
		}
		return res;
	}

	/**
	 * Given a string containing just the characters '(', ')', '{', '}', '[' and
	 * ']', determine if the input string is valid.
	 * 
	 * An input string is valid if:
	 * 
	 * Open brackets must be closed by the same type of brackets. Open brackets must
	 * be closed in the correct order. Note that an empty string is also considered
	 * valid.
	 * 
	 * Input: "()" , Output: true
	 * 
	 * Input: "()[]{}" ,Output: true
	 * 
	 * Input: "([)]", Output: false
	 * 
	 * Input: "{[]}", Output: true
	 * 
	 * o(n),o(n)
	 * 
	 * @param s
	 * @return true if valid
	 */
	public boolean isValidParentheses(String s) {
		Deque<Character> stack = new LinkedList<>();
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			if (ch == '(') {
				stack.push(')');
			} else if (ch == '[') {
				stack.push(']');
			} else if (ch == '{') {
				stack.push('}');
			} else if (stack.isEmpty() || stack.pop() != ch) {
				return false;
			}
		}
		return stack.isEmpty();
	}
}

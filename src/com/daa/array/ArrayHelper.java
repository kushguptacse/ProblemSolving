package com.daa.array;

public class ArrayHelper {

	public static void main(String[] args) {
		System.out.println(ArrayHelper.binarySearch(new int[] { 1, 2, 5, 6 }, 3));
	}

	/**
	 * It requires array to be already sorted to work correctly.
	 * 
	 * here we check from index start - mid if element to searched < mid else check
	 * from mid+1,end.
	 * 
	 * o(logn)
	 * 
	 * @param nums
	 * @return index of item else -1
	 */
	public static int binarySearch(int[] nums, int target) {
		int start = 0;
		int end = nums.length - 1;
		int mid = 0;
		while (start <= end) {
			mid = (start + end) / 2;
			if (nums[mid] == target) {
				return mid;
			} else if (nums[mid] < target) {
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}
		return -1;
	}

	/**
	 * Given a sorted array and a target value, return the index if the target is
	 * found. If not, return the index where it would be if it were inserted in
	 * order.
	 * You may assume no duplicates in the array.
	 * 
	 * Input: [1,3,5,6], 2
	 * Output: 1
	 * 
	 * @param nums
	 * @param target
	 * @return index of element present or where it should be present
	 */
	public int searchInsert(int[] nums, int target) {
		int start = 0;
		int end = nums.length - 1;
		int mid = 0;
		while (start <= end) {
			mid = (start + end) / 2;
			if (nums[mid] == target) {
				return mid;
			} else if (nums[mid] < target) {
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}
		return start;
	}
}

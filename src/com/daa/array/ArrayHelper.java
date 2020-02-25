package com.daa.array;

import java.util.Arrays;

public class ArrayHelper {

	public static void main(String[] args) {
		System.out.println(ArrayHelper.binarySearch(new int[] { 1, 2, 5, 6 }, 3));
		int[] arr = new int[] { 0, 1, 2, 5, 0, 6, 0 };
		searchAndShift(arr, 0);
		System.out.println(Arrays.toString(arr));
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
	 * order. You may assume no duplicates in the array.
	 * 
	 * Input: [1,3,5,6], 2 Output: 1
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

	/**
	 * Given an array nums, write a function to move all 0's to the end of it while
	 * maintaining the relative order of the non-zero elements.
	 * 
	 * Example:
	 * 
	 * Input: [0,0,1,2,5,0,6,0]
	 * 
	 * Output: [1,2,5,6,0,0,0,0]
	 * 
	 * @param arr
	 * @param val
	 */
	public static void searchAndShift(int[] arr, int val) {
		int c = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == val) {
				c++;
			} else if (c > 0) {
				arr[i - c] = arr[i];
				arr[i] = val;
			}
		}
	}
	
	/**
	 * Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array), some elements
	 * appear twice and others appear once.
	 * 
	 * print all the duplicates in this array. 
	 * 
	 * o(n)
	 * 
	 * @param nums
	 */
	public static void findDuplicates(int[] nums) {
		for (int i = 0; i < nums.length; i++) {
			int n=Math.abs(nums[i]);
			if (nums[n-1] < 0) {
				System.out.print(n+" ");
			} else {
				nums[n-1] = -nums[n-1];
			}
		}
	}
	
	/**
	 * leetcode
	 * 
	 * Given a list of non negative integers, arrange them such that they form the
	 * largest number.
	 * 
	 * Example 1:
	 * 
	 * Input: [3,30,34,5,9] 
	 * 
	 * Output: "9534330"
	 * 
	 * @param nums
	 * @return largest number
	 */
	public String largestNumber(int[] nums) {
        String[] str = new String[nums.length];
        for(int i=0;i<nums.length;i++){
            str[i]=String.valueOf(nums[i]);
        }
        Arrays.sort(str,(o1,o2)->{
	            String s1=o1+o2;
                String s2=o2+o1;
                return s2.compareTo(s1);
	        });
       
        if("0".equals(str[0])){
            return str[0];
        }
        StringBuilder sb=new StringBuilder(nums.length);
        for(String s:str){
            sb.append(s);
        }
        return sb.toString();
    }

}
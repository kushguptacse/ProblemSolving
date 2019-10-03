package com.daa.algo.greedy;

import java.util.Arrays;

import com.daa.math.MathUtil;

/**
 * Given an array of jobs where every job has a deadline and associated profit
 * if the job is finished before the deadline. It is also given that every job
 * takes single unit of time, so the minimum possible deadline for any job is 1.
 * How to maximize total profit if only one job can be scheduled at a time.
 * 
 * @f:off
 * Input: Five Jobs with following deadlines and profits
 * JobID  Deadline  Profit
 * a      2        100   
 * b      1        19
 * c      2        27  
 * d      1        25
 * e	  3		   15	
 * Output: Following is maximum profit sequence of jobs
 *      c, a, e   
 * @f:on
 */
public class JobSequencingWithDeadline {

	public static void main(String[] args) {
		JobSequencingWithDeadline obj = new JobSequencingWithDeadline();
		int[] profit = { 6, 8, 5, 10 };
		int[] deadline = { 2, 1, 1, 2 };
		char[] name = { '1', '2', '3', '4' };
		JobSequencingModel[] arr = new JobSequencingModel[profit.length];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = new JobSequencingModel(name[i], profit[i], deadline[i]);
		}
		System.out.println(obj.printMaxProfit(arr));

		profit = new int[] { 200, 180, 190, 300, 120, 100 };
		deadline = new int[] { 5, 3, 3, 2, 4, 2 };
		name = new char[] { '1', '2', '3', '4', '5', '6' };
		arr = new JobSequencingModel[profit.length];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = new JobSequencingModel(name[i], profit[i], deadline[i]);
		}
		System.out.println(obj.printMaxProfit(arr));

		profit = new int[] { 2, 4, 3, 1, 10 };
		deadline = new int[] { 3, 3, 3, 4, 4 };
		name = new char[] { '1', '2', '3', '4', '5' };
		arr = new JobSequencingModel[profit.length];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = new JobSequencingModel(name[i], profit[i], deadline[i]);
		}
		System.out.println(obj.printMaxProfit(arr));

		profit = new int[] { 15, 20, 30, 18, 18, 10, 23, 16, 25 };
		deadline = new int[] { 7, 2, 5, 3, 4, 5, 2, 7, 3 };
		name = new char[] { '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		arr = new JobSequencingModel[profit.length];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = new JobSequencingModel(name[i], profit[i], deadline[i]);
		}
		System.out.println(obj.printMaxProfit(arr));

		profit = new int[] { 100, 19, 27, 25, 15 };
		deadline = new int[] { 2, 1, 2, 1, 3 };
		name = new char[] { 'a', 'b', 'c', 'd', 'e' };
		arr = new JobSequencingModel[profit.length];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = new JobSequencingModel(name[i], profit[i], deadline[i]);
		}
		System.out.println(obj.printMaxProfit(arr));

	}

	/**
	 * Greedy algorithm, o(n^2), geeksForgeeks, Ravindra babu ravula.
	 * 
	 * @f:off
	 * Approach - 
	 * 1. sort the array in descending order of profit.
	 * 2. pick one by one & check If current job can fit in the current result sequence without missing the deadline, 
	 *    add current job to the result. Else ignore the current job.
	 * 3. when all jobs got considered we have our deadline array with final answer
	 * @f:on
	 * 
	 * @param arr
	 * @return max profit possible
	 */
	public int printMaxProfit(JobSequencingModel[] arr) {
		Arrays.sort(arr, (o1, o2) -> o2.getProfit() - o1.getProfit());
		JobSequencingModel[] deadline = new JobSequencingModel[arr.length];
		for (JobSequencingModel obj : arr) {
			int i = MathUtil.min(arr.length, obj.getDeadline()) - 1;
			while (i >= 0) {
				if (deadline[i] == null) {
					deadline[i] = obj;
					break;
				}
				i = i - 1;
			}
		}
		int result = 0;
		System.out.print("Job considered: [");
		for (JobSequencingModel obj : deadline) {
			if (obj != null) {
				result = result + obj.getProfit();
				System.out.print(" " + obj.getName());
			}
		}
		System.out.println(" ]");
		return result;
	}

}

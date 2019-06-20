package com.daa.array;

import java.util.Arrays;

public final class ArrayUtil {
	private ArrayUtil() {
		
	}
	
	public static void main(String[] args) {
//		System.out.println(isSorted(new int[] {111,222,322}));
		reorder(new int[]{24,56,74,-23,87,91},new int[] {2,3,4,1,5,6});
		int[] res=sortAwithB(new int[]{24,56,74,-23,87,91},new int[] {2,3,4,1,5,6});
		System.out.println("Array after reordering is : ");
		Arrays.stream(res).forEach(i->System.out.print(i+" "));
		System.out.println();
	}
	
	public static int[] mergeTwoSortedArray(int[] a,int[] b) {
		int[] arr = new int[a.length+b.length];
		int i=0;
		int j=0;
		int k=0;
		while(i<a.length&&j<b.length) {
			if(a[i]<b[j]) {
				arr[k]=a[i];
				i++;
			} else {
				arr[k]=b[j];
				j++;
			}
			k++;
		}
		
		while(i<a.length) {
			arr[k]=a[i];
			k++;
			i++;
		}
		
		while(j<b.length) {
			arr[k]=b[j];
			k++;
			j++;
		}
		return arr;
	}
	
	/**
	 * check if array is sorted or not
	 * 
	 * @param arr
	 * @return true if sorted
	 */
	public static boolean isSorted(int[] arr) {
		return isSorted(arr,0);
	}

	private static boolean isSorted(int[] arr, int i) {
		if(arr.length-1==i) {
			return true;
		}
		
		if(arr[i]>arr[i+1]) {
			return false; 
		} else {
			return isSorted(arr,i+1);
		}
	}
	
	/**
	 * Reorder the first array arr using index provided in second array brr
	 * 
	 * arr=[10,11,12]
	 * brr=[1,0,2]
	 * 
	 * output - [11,10,12]
	 * 
	 * @param arr
	 * @param brr
	 */
	public static void reorder(int[] arr,int[] brr) {
		System.out.println("Array before reordering is : ");
		Arrays.stream(arr).forEach(i->System.out.print(i+" "));
		System.out.println();
		
		for(int i=0;i<arr.length;i++) {
			brr[i] = arr[brr[i]-1];
		}
		
		System.out.println("Array after reordering is : ");
		Arrays.stream(brr).forEach(i->System.out.print(i+" "));
		System.out.println();
	}
	
	static int[] sortAwithB(int A[], int B[]) {
	    int i = 0;
	    while (i < A.length) {
	        int pos = B[i]-1;
	        if (pos != i) { // not the right position
	             swap(A, i, pos);
	             swap(B, i, pos);
	        } else // otherwise, skip
	             i++;
	    }
	    return A;
	}

	private static void swap(int[] a, int i, int pos) {
		int temp=a[i];
		a[i]=a[pos];
		a[pos]=temp;
	}
	
}

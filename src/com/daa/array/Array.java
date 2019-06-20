package com.daa.array;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.stream.IntStream;

public class Array {

	private Integer[] arr;
	
	private int size;
	
	public int getSize() {
		return size;
	}

	private static final int INITIAL_CAPACITY = 10;
	
	public Array(int size) {
		arr=new Integer[size];	
	}
	
	public Array() {
		arr=new Integer[INITIAL_CAPACITY];
	}
	
	/**
	 * reverse array - O(N/2)
	 */
	public void reverse() {
		for(int i=0;i<size/2;i++) {
			int temp = arr[i];
			arr[i]=arr[size-i-1];
			arr[size-i-1]=temp;
		}
	}

	
	/**
	 * print element of array. O(N)
	 */
	public void print() {
		IntStream.range(0, size).forEach(i->System.out.print(arr[i]+" "));
		System.out.println();
	}
	
	/**
	 * add item to the end of array
	 * O(1) if space is there otherwise O(N)
	 * @param item
	 */
	public void add(int item) {
		if(size>=arr.length) {
			Integer[] temp = new Integer[arr.length*2];
			IntStream.range(0, size).forEach(i->temp[i]=arr[i]);
			arr=temp;
		}
		arr[size]=item;
		size++;
	}
	
	/**
	 * O(N2) as hash set worst case will be O(N) and also uses extra memory of hashset
	 */
//	public void removeDuplicate() {
//		Set<Integer> hashSet = new LinkedHashSet<>();
//		for(int i=0;i<size;i++) {
//			hashSet.add(arr[i]);
//		}
//		
//		int count=0;
//		Iterator<Integer> it=hashSet.iterator();
//		while(it.hasNext()) {
//			arr[count]=it.next();
//			count++;
//		}
//		size=count;
//	}
	
	/**
	 * o(NLOGN) for sorting. if already sorted array passed then o(N)
	 */
	public void removeDuplicate1() {
		Comparator<Integer> comp = (i1, i2) -> {
			if (i1 == null || i2 == null) {
				return 1;
			} else {
				return i1.compareTo(i2);
			}
		};
		Arrays.sort(arr, comp);
		System.out.println("sorted array : ");
		print();
		
		//logic started after sorting
		int j = 0;
		
		for (int i = 0; i < size-1; i++) {
			if(arr[i]!=arr[i+1]) {
				arr[j]=arr[i];
				j++;
			} 
			//find duplicate
//			else {
//				System.out.println("duplicate item found: "+ arr[i]);
//			}
		}
		arr[j]=arr[size-1];
		size=++j;
		
	}
	
	/**
	 * O(N) time complexity and taking extra 
	 * @param rotateCount
	 */
	public void rotateLeft(int d) {
		d=d%arr.length;
		reverse(arr,0,d-1);
		reverse(arr,d,size-1);
		reverse(arr,0,size-1);
	}
	
	/**
	 * O(N) time complexity and taking extra 
	 * @param rotateCount
	 */
	public void rotateRight(int d) {
		d=d%arr.length;
		reverse(arr,0,size-1);
		reverse(arr,d,size-1);
		reverse(arr,0,d-1);
	}
	
	/**
	 * reverse array - O(N/2)
	 */
	private void reverse(Integer[] arr, int start, int end) {
		for(int i=start;i<end;i++,end--) {
			int temp = arr[i];
			arr[i]=arr[end];
			arr[end]=temp;
		}
	}
	
	/**
	 * O(N)
	 * 
	 * @param index
	 * @return true if removed
	 */
	public boolean removeElementByIndex(int index) {
		if (index < 0 && index >= size) {
			return false;
		}

		for (int i = index; i < size-1; i++) {
			int temp = arr[i];
			arr[i] = arr[i + 1];
			arr[i+1]=temp;
		}
		size--;
		return true;
	}
	
	/**
	 * select K random Items from array of large size.
	 * this problem is known as reservoir sampling.
	 * O(N)
	 * First copy K element to new array.
	 * Then iterate from k+1 to n. and then find random value -(0,i]. 
	 * this will give random value which can be in size range of new array or outside. 
	 * if it is in range than copy the current array object to new array.
	 * @param k
	 */
	public Integer[] selectKItems(int k) {
		Random random = new Random();
		Integer[] reserviors = new Integer[k];
		IntStream.range(0, k).forEach(i -> reserviors[i] = arr[i]);
		for(int i=k+1;i<size;i++) {
			int newVal =random.nextInt(i+1);
			if(newVal<k) {
				reserviors[newVal] = arr[i];
			}
		}
		return reserviors;
		
	}
	
	
}

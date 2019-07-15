package com.daa.heap;

import java.util.Scanner;

public class HeapTestApp {
	public static void main(String[] args) {

		Heap heapObj = new Heap();
		try (Scanner sc = new Scanner(System.in)) {
			System.out.println("Enter the element you want to insert");
			int data = sc.nextInt();
			int c = 0;
			while (data != -1) {
				System.out.println("---------------------");
				System.out.println("heap after inserting element : " + data);
				heapObj.add(data);
				System.out.println(heapObj.toString());
				System.out.println("-------------------------");
				System.out.println("Enter the element you want to insert");
				data = sc.nextInt();
				c++;
			}
			System.out.println("--------------***-------------------------");
			System.out.println("Enter the element you want to delete");
			data = sc.nextInt();
			heapObj.deleteByValue(data);
			System.out.println(heapObj.toString());
			System.out.println("Enter the index from which you want to delete");
			data = sc.nextInt();
			System.out.println("Deleted item : "+heapObj.deleteByIndex(data));
//			for (int i = 0; i < c; i++)
//				System.out.println("Deleted item from heap : " + heapObj.poll());
			System.out.println(heapObj.toString());
		}

	}
}

package com.daa.heap;

import java.util.Scanner;

public class HeapTestApp {
	public static void main(String[] args) {

		Heap rbt = new Heap();
		try (Scanner sc = new Scanner(System.in)) {
			System.out.println("Enter the element you want to insert");
			int data = sc.nextInt();
			int c = 0;
			while (data != -1) {
				System.out.println("---------------------");
				System.out.println("heap after inserting element : " + data);
				rbt.add(data);
				System.out.println(rbt.toString());
				System.out.println("-------------------------");
				System.out.println("Enter the element you want to insert");
				data = sc.nextInt();
				c++;
			}
			System.out.println("--------------***-------------------------");
			for (int i = 0; i < c; i++)
				System.out.println("Deleted item from heap : " + rbt.poll());
			System.out.println(rbt.toString());
		}

	}
}

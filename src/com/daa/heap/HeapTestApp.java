package com.daa.heap;

import java.util.Scanner;

public class HeapTestApp {
	public static void main(String[] args) {

		Heap rbt = new Heap();
		try (Scanner sc = new Scanner(System.in)) {
			System.out.println("Enter the element you want to insert");
			int data = sc.nextInt();
			while (data != -1) {
				System.out.println("---------------------");
				System.out.println("heap after inserting element : " + data);
				rbt.add(data);
				System.out.println(rbt.toString());
				System.out.println("-------------------------");
				System.out.println("Enter the element you want to insert");
				data = sc.nextInt();
			}
			System.out.println("--------------***-------------------------");
		}

	}
}

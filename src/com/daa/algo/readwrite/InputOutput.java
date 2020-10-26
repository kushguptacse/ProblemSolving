package com.daa.algo.readwrite;

import java.util.Scanner;

public class InputOutput {
	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			int n = sc.nextInt(); 
			System.out.println(n % 2 == 0 ? n + 1 : n - 1);
		}
	}

}

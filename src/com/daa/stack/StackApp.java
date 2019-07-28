package com.daa.stack;

public class StackApp {
	public static void main(String[] args) {
		StackMaxElementImproved st = new StackMaxElementImproved();
		int[] arr = { 4, 19, 7, 14, 20 };
		System.out.print("Max element in stack : ");
		for (int i = 0; i < arr.length; i++) {
			st.push(arr[i]);
			System.out.print(st.getMax() + " ");
		}
		System.out.println();
		st = new StackMaxElementImproved();
		st.push(3);
		st.push(5);
		System.out.println("max: " + st.getMax());
		st.push(7);
		st.push(19);
		System.out.println("max : " + st.getMax());
		System.out.println("pop: " + st.pop());
		System.out.println("max : " + st.getMax());
		System.out.println("pop: " + st.pop());
		System.out.println("peek: " + st.peek());

	}
}

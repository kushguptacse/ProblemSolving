package com.daa.queue;

public class QueueMain {
	public static void main(String[] args) {
		Queue<Integer> queue = new Queue<>();
		queue.add(1);
		queue.print();
		queue.add(2);
		queue.print();
		queue.add(3);
		queue.print();
		queue.add(4);
		queue.print();
		System.out.println("element pop :" + queue.pop());
		queue.print();
		System.out.println("element pop :" + queue.pop());
		queue.print();
		System.out.println("element peek :" + queue.peek());
		System.out.println("element pop :" + queue.pop());
		queue.print();
		System.out.println("element pop :" + queue.pop());
		queue.print();
		System.out.println("element peek :" + queue.peek());

	}
}

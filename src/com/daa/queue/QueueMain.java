package com.daa.queue;

public class QueueMain {

	public static void main(String[] args) {
		DeQueue<Integer> queue = new DeQueue<>();
		queue.addRear(1);
//		queue.print();
		queue.addRear(2);
//		queue.print();
		queue.addRear(3);
//		queue.print();
		queue.addRear(4);
		queue.print();
		System.out.println("element poll :" + queue.pollFront());
//		queue.print();
		System.out.println("element poll :" + queue.pollFront());
//		queue.print();
		System.out.println("element peek :" + queue.peek());
		System.out.println("element poll :" + queue.pollFront());
//		queue.print();
		System.out.println("element poll :" + queue.pollFront());
//		queue.print();
		System.out.println("element peek :" + queue.peek());

	}
}

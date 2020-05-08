package com.daa.heap;

import java.util.Scanner;

import com.daa.queue.QueueUsingHeap;
import com.daa.stack.StackUsingHeap;

public class HeapTestApp {
	public static void main(String[] args) {

//		testQueue();
//		testStack();
//		HeapMin<Integer> heapObj = new HeapMin<>();
		HeapMax heapObj = new HeapMax();
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
			heapObj.updateKey(0, 5);
			System.out.println(heapObj.toString());
			System.out.println("--------------***-------------------------");
			System.out.println("Enter the element you want to delete");
			data = sc.nextInt();
			heapObj.deleteByValue(data);
			System.out.println(heapObj.toString());
			System.out.println("Enter the index from which you want to delete");
			data = sc.nextInt();
			System.out.println("Deleted item : " + heapObj.deleteByIndex(data));
			for (int i = 0; i < c; i++) {
				System.out.println("Deleted item from heap : " + heapObj.poll());
				System.out.println(heapObj.toString());
			}
			System.out.println("Deleted item from heap : " + heapObj.poll());
		}

	}

	private static void testStack() {
		StackUsingHeap<Integer> stack = new StackUsingHeap<>();
		stack.push(10);
		stack.push(20);
		stack.push(30);
		System.out.println("stack size : " + stack.size());
		System.out.println("stack pop: " + stack.pop());
		System.out.println("stack pop : " + stack.pop());
		System.out.println("stack pop : " + stack.pop());
	}

	private static void testQueue() {
		QueueUsingHeap<Integer> queue = new QueueUsingHeap<>();
		queue.add(10);
		queue.add(20);
		queue.add(30);
		System.out.println("queue size : " + queue.size());
		System.out.println("queue poll: " + queue.poll());
		System.out.println("queue poll : " + queue.poll());
		System.out.println("queue poll : " + queue.poll());
	}

}

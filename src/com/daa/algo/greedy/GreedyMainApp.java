package com.daa.algo.greedy;

public class GreedyMainApp {
	public static void main(String[] args) {
		testHuffman();
	}

	private static void testHuffman() {
		HuffmanEncoding obj = new HuffmanEncoding();
		String orignal = "GeeksforGeeks";
		System.out.println("orignal- " + orignal);
		String encodedStr = obj.encode(orignal);
		System.out.println("encoded- " + encodedStr);
		System.out.println("decoded- " + obj.decode(encodedStr, obj.getRoot()));
		System.out.println("------------------------------------");
		orignal = "AAAAAABCCCCCCDDEEEEE";
		System.out.println("orignal- " + orignal);
		encodedStr = obj.encode(orignal);
		System.out.println("encoded- " + encodedStr);
		System.out.println("decoded- " + obj.decode(encodedStr, obj.getRoot()));
		System.out.println("------------------------------------");
		orignal = "BCCABBDDAECCBBAEDDCC";
		System.out.println("orignal> " + orignal);
		encodedStr = obj.encode(orignal);
		System.out.println("encoded> " + encodedStr);
		System.out.println("decoded> " + obj.decode(encodedStr, obj.getRoot()));

	}
}

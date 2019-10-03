package com.daa.algo.greedy;

public class GreedyMainApp {
	public static void main(String[] args) {
		testOptimalMergePatterns();
		System.out.println("----------------------------------");
		testHuffman();
	}

	private static void testOptimalMergePatterns() {
		OptimalMergePatterns obj = new OptimalMergePatterns();
		System.out.println("Minimum Computations = " + obj.minComputation(new int[] { 2, 3, 4, 5, 6, 7 }));
		System.out.println("Minimum Computations = " + obj.minComputation(new int[] { 2, 3, 4 }));
		System.out.println("Minimum Computations - " + obj.minComputation(new int[] { 4, 9, 2, 6, 3, 31, 41 }));
		System.out.println("Minimum Computations - " + obj.minComputation(new int[] { 10, 20, 30 }));
		System.out.println("Minimum Computations : " + obj.minComputation(new int[] { 40, 10, 20, 15, 25, 30 }));
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

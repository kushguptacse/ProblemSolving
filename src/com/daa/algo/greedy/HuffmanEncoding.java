package com.daa.algo.greedy;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;

/**
 * @f:off
 * ABDUL BARI,geeks,GREEDY approach
 * 
 * Unlike run length encoding approach we want to avoid sending actual content as it might be taking too much space.
 * 
 * e.g. - AABBBB.
 * ASCII value of A is 65. to represent a single character we need 8 bit. total bits =  2*8+4*8 = 48
 * To save space instead of sending complete 8 bit for each character we could assign our own shorter codes.
 * and to further save we could assign shorter code to character that is repeating most and so on.
 * 
 * while decoding apart from sending encodedString we will also provide table which contain character to code mapping.
 * 
 * This is the main Idea behind huffman-coding
 * 
 * @f:on
 */
public class HuffmanEncoding {

	private HuffmanNode root;

	/**
	 * O(nlogn) where n is the number of unique characters.
	 * 
	 * @f:off
	 * 
	 * Approach - 
	 * 1. first calculate frequency of every character and add all the nodes in the min heap.
	 * 2. extract first two minNodes and add the frequency to create new node and make that node parent.
	 * 3. iterate perform step 2 till all nodes are covered.
	 * 
	 * @f:on
	 * 
	 * @param plainStr
	 * @return encoded string as per huffmanAlgo
	 */
	public String encode(String plainStr) {

		Map<Character, Integer> freqMap = calculateFrequency(plainStr);
		PriorityQueue<HuffmanNode> minHeap = new PriorityQueue<>((o1, o2) -> o1.getCount() - o2.getCount());
		// add leaf nodes to the min heap
		for (Entry<Character, Integer> entry : freqMap.entrySet()) {
			minHeap.add(new HuffmanNode(entry.getKey(), entry.getValue()));
		}

		while (minHeap.size() > 1) {
			HuffmanNode left = minHeap.poll();
			HuffmanNode right = minHeap.poll();
			HuffmanNode internalNode = new HuffmanNode();
			internalNode.setCount(left.getCount() + right.getCount());
			internalNode.setLeft(left);
			internalNode.setRight(right);
			minHeap.add(internalNode);
		}
		// root node of the tree
		root = minHeap.poll();
		// display huffman tree
		System.out.println("Data Table : ");
		Map<Character, String> codeMap = new HashMap<>();
		printHuffmanTree(root, "", codeMap);
		System.out.println();
		// encode the input using codeMap
		return encodeInput(plainStr, codeMap);
	}

	/**
	 * display the data of Huffman-tree and also stores code with char in hashmap
	 * for encoding
	 * 
	 * @param root
	 * @param str
	 * @param codeMap
	 */
	private void printHuffmanTree(HuffmanNode root, String str, Map<Character, String> codeMap) {
		if (root == null) {
			return;
		}
		if (root.getLeft() == null && root.getRight() == null) {
			System.out.print("[" + root.getCh() + ":" + str + "]");
			codeMap.put(root.getCh(), str);
		}
		printHuffmanTree(root.getLeft(), str + "0", codeMap);
		printHuffmanTree(root.getRight(), str + "1", codeMap);
	}

	/**
	 * By using codeMap it will encode the input and return the encodedString
	 * 
	 * @param plainStr
	 * @param codeMap
	 * @return encodedString
	 */
	private String encodeInput(String plainStr, Map<Character, String> codeMap) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < plainStr.length(); i++) {
			sb.append(codeMap.get(plainStr.charAt(i)));
		}
		return sb.toString();
	}

	private Map<Character, Integer> calculateFrequency(String plainStr) {
		Map<Character, Integer> freqMap = new HashMap<>();
		for (int i = 0; i < plainStr.length(); i++) {
			char c = plainStr.charAt(i);
			freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
		}
		return freqMap;
	}

	/**
	 * @return the root
	 */
	public HuffmanNode getRoot() {
		return root;
	}

	/**
	 * decode the encodedString using huffmanTree
	 * 
	 * o(n)
	 * 
	 * @param encodedStr
	 * @param root
	 * @return decoded string using huffmanTree passed
	 */
	public String decode(String encodedStr, HuffmanNode root) {
		HuffmanNode node = root;
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < encodedStr.length(); i++) {
			char c = encodedStr.charAt(i);
			if (c == '1') {
				node = node.getRight();
			} else {
				node = node.getLeft();
			}
			if (node.getLeft() == null && node.getRight() == null) {
				result.append(node.getCh());
				node = root;
			}
		}
		return result.toString();
	}

}

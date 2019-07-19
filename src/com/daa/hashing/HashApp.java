package com.daa.hashing;
//import java.util.HashMap;
import java.util.stream.IntStream;

public class HashApp {

	public static void main(String[] args) {
		HashMap<Integer, String> map = new HashMap<>();
		System.out.println("Map.put old " + 0 + " : " + map.put(0, "kush"));
		System.out.println("Map.get 0 : " + map.get(0));
		IntStream.range(0, 6).forEach(i -> {
			System.out.println("Map.put old " + i + " : " + map.put(i, "hello - " + i));
			System.out.println("Map.get " + i + " : " + map.get(i));
		});
		
		System.out.println("Map.put old " + 12 + " : " + map.put(12, "data12"));
		System.out.println("Map.get " + 12 + " : " + map.get(12));
		System.out.println("Map.put old " + 2 + " : " + map.put(2, "data2"));
		System.out.println("Map.get " + 2 + " : " + map.get(2));
		System.out.println("Size : "+map.size());
	}

}

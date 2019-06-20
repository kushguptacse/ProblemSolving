package com.daa.math;

public class Puzzle {
	// Complete the saveThePrisoner function below.
	public static int saveThePrisoner(int n, int m, int s) {

		if ((m + s - 1) % n == 0)
			return n;
		else
			return ((m + s - 1) % n);

	}

}

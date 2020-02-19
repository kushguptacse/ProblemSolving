package com.daa.math;

public class MathMain {
	public static void main(String[] args) {
		int num = 4321;
		System.out.println("reverse of - " + num + " is " + MathUtil.reverse(num));
		num = 432134;
		System.out.println("is " + num + " palindrome " + MathUtil.checkPalindrome(num));
		num = 121;
		System.out.println("is " + num + " palindrome " + MathUtil.checkPalindrome(num));
		num = 4334;
		System.out.println("is " + num + " palindrome " + MathUtil.checkPalindrome(num));
	}
}

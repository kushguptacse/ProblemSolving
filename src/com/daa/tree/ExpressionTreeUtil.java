package com.daa.tree;

import java.util.Deque;
import java.util.LinkedList;

public final class ExpressionTreeUtil {

	private ExpressionTreeUtil() {
	}

	//@f:off
	/**
	 * create tree using postfix expression.
	 * e.g. for postfix - 359+2*+
	 * tree would be 
	 * 		+
	 *	   / \
	 *	  3   *
	 *		  /\
	 *		  + 2
	 *		 /\
	 *		5  9
	 * @param postFix
	 * @return node
	 */
	 // @f:on
	public static TreeNode<String> createTreeFromPostFix(String postFix) {
		Deque<TreeNode<String>> stack = new LinkedList<>();
		for (int i = 0; i < postFix.length(); i++) {
			TreeNode<String> node = new TreeNode<>(postFix.charAt(i) + "");
			if (isOperator((postFix.charAt(i) + ""))) {
				node.setRight(stack.pop());
				node.setLeft(stack.pop());
			}
			stack.push(node);
		}
		return stack.peek();
	}

	private static boolean isOperator(String op) {
		return op.equals("+") || op.equals("-") || op.equals("*") || op.equals("/");
	}

	/**
	 * in order gives infix
	 * 
	 * @param root
	 */
	public static void infixExpression(TreeNode<String> root) {
		if (root == null)
			return;
		infixExpression(root.getLeft());
		System.out.print(root.getData() + " ");
		infixExpression(root.getRight());

	}

	public static void prefixExpression(TreeNode<String> root) {
		if (root == null)
			return;
		System.out.print(root.getData() + " ");
		prefixExpression(root.getLeft());
		prefixExpression(root.getRight());
	}

	/**
	 * evaluate tree by using stack. push element on stack if operand else pop two element and
	 * perform operation. then push result into stack. at last only one element will remain in
	 * stack which is the answer
	 *@f:off
	 * 		+
	 *	   / \
	 *	  3   *
	 *		  /\
	 *		  + 2
	 *		 /\
	 *		5  9
	 *@f:on
	 * answer - 31
	 * 
	 * @param root
	 * @return evaluation of infix
	 */
	public static int evaluateExpression(TreeNode<String> root) {
		Deque<Integer> stack = new LinkedList<>();
		evaluateExpression(root, stack);
		return stack.peek();
	}

	/**
	 * 
	 * @param root
	 * @param stack
	 */
	private static void evaluateExpression(TreeNode<String> root, Deque<Integer> stack) {
		if (root == null) {
			return;
		}
		evaluateExpression(root.getLeft(), stack);
		evaluateExpression(root.getRight(), stack);
		if (isOperator(root.getData())) {
			stack.push(performOperation(stack.pop(), stack.pop(), root.getData()));
		} else {
			stack.push(Integer.valueOf(root.getData()));
		}
	}

	private static Integer performOperation(int a, int b, String op) {
		switch (op) {
		case "+":
			return b + a;
		case "-":
			return b - a;
		case "/":
			return b / a;
		default:
			return b * a;
		}
	}

}

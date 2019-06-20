package com.daa.tree;

public final class ExpressionTreeMain {
	public static void main(String[] args) {
		TreeNode<String> root = ExpressionTreeUtil.createTreeFromPostFix("359-2*+");
		System.out.println("Infix of tree is : ");
		ExpressionTreeUtil.infixExpression(root);
		System.out.println();
		System.out.println("prefix of tree is : ");
		ExpressionTreeUtil.prefixExpression(root);
		System.out.println();
		System.out.print("evaluation of expression tree is : ");
		int res = ExpressionTreeUtil.evaluateExpression(root);
		System.out.println(res);

	}
}

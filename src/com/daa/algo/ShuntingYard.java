package com.daa.algo;

import com.daa.stack.Stack;

/**
 * algo used to solve the mathematical expression
 * @author G521885
 *
 */
public class ShuntingYard {
	public static void main(String[] args) {
		Double res = new ShuntingYard().solveEquation("( ( ( 1 + 2 ) * ( 40 / 2 ) ) + ( 5 - 1 ) )");
		System.out.println(res);
	}

	private Stack<String> operatorStack = new Stack<>();
	private Stack<Double> operandStack = new Stack<>();

	/**
	 * solve the expression by pushing operator in stack1 and operand in stack 2.
	 * when ) is popped-> solve the expression
	 * 
	 * @param str
	 * @return final result
	 */
	private Double solveEquation(String str) {
		String[] s = str.split(" ");
		for (int i = 0; i < s.length; i++) {
			if ("(".equals(s[i])) {

			} else if (")".equals(s[i])) {
				String op = operatorStack.pop();
				double first = operandStack.pop();
				double second = operandStack.pop();
				if ("+".equals(op)) {
					operandStack.push(first + second);
				} else if ("-".equals(op)) {
					operandStack.push(second - first);
				}else if ("*".equals(op)) {
					operandStack.push(first * second);
				}else if ("/".equals(op)) {
					operandStack.push(second / first);
				}
			} else if ("+".equals(s[i])||"-".equals(s[i])||"*".equals(s[i])||"/".equals(s[i])) {
				operatorStack.push(s[i]);
			} else {
				operandStack.push(Double.valueOf(s[i]));
			}

		}
		return operandStack.pop();
	}

}

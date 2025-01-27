package com.daa.tree;

import java.util.Scanner;

import com.daa.math.MathUtil;
import com.daa.model.Model;

/**
 * 
 * @author G521885
 *
 */
public final class AVLTreeUtil {

	private AVLTreeUtil() {

	}

	public static void main(String[] args) {
		AvlTreeNode<Integer> root = null;
		int h = 2;
		root = constructHeightBalancedZeroTree(h);
		System.out.println("Number of Nodes in the range 2 , 5 is: " + countNodesInRange(root, 2, 5));
		System.out.println("Nodes in range 2 , 5 :");
		printNodesInRange(root, 2, 5);
		System.out.println();
		System.out.println("Pre Order of tree: " + preOrderTraversal(root));
		System.out.println("In order traversal: " + inOrderTraversalModified(root));
		root = constructMinimalAvltree(h);
		System.out.println("Pre Order of tree - " + preOrderTraversal(root));
		System.out.println("In order traversal - " + inOrderTraversalModified(root));
		try (Scanner sc = new Scanner(System.in)) {
			TreeNode<Integer> bstRoot = BinaryTreeUtil.constructTreeFromConsole(sc);
			System.out.println("Is Avl Tree : " + isAvlTree(bstRoot));
			System.out.println("Enter the element you want to insert in avl tree");
			int data = sc.nextInt();
			while (data != -1) {
				System.out.println("---------------------");
				System.out.println("Avl tree after inserting element : " + data);
				root = insert(root, data);
				System.out.println("Pre Order of tree - " + preOrderTraversal(root));
				System.out.println("In order traversal : " + inOrderTraversalModified(root));
				System.out.println("-------------------------");
				System.out.println("Enter the element you want to insert in avl tree");
				data = sc.nextInt();
			}
			System.out.println("--------------***-------------------------");
			String[] s = preOrderTraversal(root).split(" ");
			for (String s1 : s) {
				System.out.println("Avl tree after deleting element : " + s1);
				root = delete(root, Integer.valueOf(s1));
				System.out.println("In order traversal : " + inOrderTraversal(root));
			}

			System.out.println("Bye");
		}
	}

	/**
	 * check if binary search tree is avl tree.
	 * 
	 * @param root
	 * @return true if bst is avl tree
	 */
	public static boolean isAvlTree(TreeNode<Integer> root) {
		if (root == null) {
			return true;
		}
		int bf = BinarySearchTreeUtil.getHeight(root.getLeft()) - BinarySearchTreeUtil.getHeight(root.getRight());
		if (bf < -1 || bf > 1) {
			return false;
		}
		return isAvlTree(root.getLeft()) && isAvlTree(root.getRight());
	}

	/**
	 * Perform delete operation avl tree.
	 * @f:off
	 * 1. perform normal delete operation in bst
	 * 2. set height of current node.
	 * 3. get balance factor of current node and check rotation needed.
	 * 4. perform shift according to case .
	 * 5. return current node.
	 * @f:on
	 * @param root
	 * @param data
	 * @return root of node after deletion
	 */
	public static AvlTreeNode<Integer> delete(AvlTreeNode<Integer> root, int data) {
		if (root == null) {
			return null;
		}

		if (root.getData() == data) {
			if (root.getLeft() == null && root.getRight() == null) {
				return null;
			}
			if (root.getLeft() == null) {
				return root.getRight();
			}
			if (root.getRight() == null) {
				return root.getLeft();
			}
			// both child
			AvlTreeNode<Integer> temp = findMax(root.getLeft());
			if (temp != null) {
				root.setData(temp.getData());
				root.setLeft(delete(root.getLeft(), root.getData()));
			}
		} else if (root.getData() < data) {
			root.setRight(delete(root.getRight(), data));
		} else {
			root.setLeft(delete(root.getLeft(), data));
		}
		// set height of the current node
		root.setHeight(MathUtil.max(getHeight(root.getLeft()), getHeight(root.getRight())) + 1);
		// get balance factor of current node
		int bal = getBalance(root);
		// left-left or left-right shift case
		if (bal > 1) {
			if (getBalance(root.getLeft()) < 0) {
				root.setLeft(leftRotate(root.getLeft()));
			}
			return rightRotate(root);
		}
		// right-right or right-left case.
		if (bal < -1) {
			if (getBalance(root.getRight()) > 0) {
				root.setRight(rightRotate(root.getRight()));
			}
			return leftRotate(root);
		}
		return root;
	}

	public static AvlTreeNode<Integer> insert(AvlTreeNode<Integer> root, int data) {
		// binary search insert
		if (root == null) {
			return new AvlTreeNode<>(data);
		}

		if (root.getData() > data) {
			root.setLeft(insert(root.getLeft(), data));
		} else {
			root.setRight(insert(root.getRight(), data));
		}

		// set height
		updateHeight(root);

		// calculate balance factor
		int balance = getBalance(root);

		// left left case
		if (balance > 1 && root.getLeft().getData() > data) {
			return rightRotate(root);
		}
		// right right case
		if (balance < -1 && root.getRight().getData() < data) {
			return leftRotate(root);
		}
		// left right case
		if (balance > 1 && root.getLeft().getData() < data) {
			root.setLeft(leftRotate(root.getLeft()));
			return rightRotate(root);
		}
		// right left case
		if (balance < -1 && root.getRight().getData() > data) {
			root.setRight(rightRotate(root.getRight()));
			return leftRotate(root);
		}
		return root;
	}

	/**
	 * Balanced the tree in Right Right case -
	 * 
	 * @f:off
	 * 1. take the right child of root to temp.
	 * 2.set root right as temp.left
	 * 3.set temp left as root.
	 * 4.update height of entire tree from temp node passed
	 * @f:on
	 * 
	 * @param root
	 * @return
	 */
	public static AvlTreeNode<Integer> leftRotate(AvlTreeNode<Integer> root) {
		if (root == null) {
			return null;
		}
		AvlTreeNode<Integer> temp = root.getRight();
		root.setRight(temp.getLeft());
		temp.setLeft(root);
		updateHeight(root);
		updateHeight(temp);
		return temp;
	}

	/**
	 * Balanced the tree in Left Left case -
	 * 
	 * @f:off
	 * 1. take the left child of root to temp.
	 * 2.set root left as temp.right
	 * 3.set temp right as root.
	 * 4.update height of entire tree from temp node passed
	 * @f:on
	 * 
	 * @param root
	 * @return height balanced node
	 */
	public static AvlTreeNode<Integer> rightRotate(AvlTreeNode<Integer> root) {
		if (root == null) {
			return null;
		}
		AvlTreeNode<Integer> temp = root.getLeft();
		root.setLeft(temp.getRight());
		temp.setRight(root);
		updateHeight(root);
		updateHeight(temp);
		return temp;
	}

	/**
	 * get height of the given node. return 0 if node is null
	 * 
	 * @param root
	 * @return height of the node passed
	 */
	public static int getHeight(AvlTreeNode<Integer> root) {
		if (root == null) {
			return -1;
		}
		return root.getHeight();
	}

	private static int getBalance(AvlTreeNode<Integer> root) {
		if (root == null)
			return 0;
		return getHeight(root.getLeft()) - getHeight(root.getRight());
	}

	private static void updateHeight(AvlTreeNode<Integer> root) {
		root.setHeight(MathUtil.max(getHeight(root.getLeft()), getHeight(root.getRight())) + 1);
	}

	public static String inOrderTraversal(AvlTreeNode<Integer> root) {
		StringBuilder sb = new StringBuilder();
		inOrder(root, sb);
		return sb.toString();
	}

	public static String inOrderTraversalModified(AvlTreeNode<Integer> root) {
		StringBuilder sb = new StringBuilder();
		inOrderTraversalModified(root, sb);
		return sb.toString();
	}

	private static void inOrderTraversalModified(AvlTreeNode<Integer> node, StringBuilder sb) {
		if (node == null) {
			return;
		}
		inOrderTraversalModified(node.getLeft(), sb);
		sb.append(node).append("->");
		inOrderTraversalModified(node.getRight(), sb);
	}

	private static void inOrder(AvlTreeNode<Integer> node, StringBuilder sb) {
		if (node == null) {
			return;
		}
		inOrder(node.getLeft(), sb);
		sb.append(node.getData()).append(" ");
		inOrder(node.getRight(), sb);
	}

	public static String preOrderTraversal(AvlTreeNode<Integer> root) {
		StringBuilder sb = new StringBuilder();
		preOrder(root, sb);
		return sb.toString();
	}

	private static void preOrder(AvlTreeNode<Integer> node, StringBuilder sb) {
		if (node == null) {
			return;
		}
		sb.append(node.getData()).append(" ");
		preOrder(node.getLeft(), sb);
		preOrder(node.getRight(), sb);
	}

	/**
	 * find max node of the AVL
	 * 
	 * @param node
	 * @return root
	 */
	public static AvlTreeNode<Integer> findMax(AvlTreeNode<Integer> node) {
		if (node == null)
			return null;
		while (node.getRight() != null) {
			node = node.getRight();
		}
		return node;
	}

	/**
	 * construct HB(0) tree. i.e. a tree where every node has balance factor zero i.e. it is complete binary tree
	 * @f:off
	 * 1. construct tree in in-order fashion and construct till height reaches 0
	 * @f:on
	 * @return root of tree
	 */
	public static AvlTreeNode<Integer> constructHeightBalancedZeroTree(int h) {
		System.out.println("Height balanced AVL Tree of zero balance factor for height " + h);
		return constructHeightBalancedZeroTree(h + 1, new Model<>(0));
	}

	private static AvlTreeNode<Integer> constructHeightBalancedZeroTree(int h, Model<Integer> model) {
		if (h == 0) {
			return null;
		}
		AvlTreeNode<Integer> node = new AvlTreeNode<>();
		node.setLeft(constructHeightBalancedZeroTree(h - 1, model));
		node.setData(model.getValue());
		node.setHeight(h - 1);
		model.setValue(model.getValue() + 1);
		node.setRight(constructHeightBalancedZeroTree(h - 1, model));
		return node;
	}

	/**
	 * construct minimal avl tree with height h.
	 * 
	 * @f:off
	 * Minimum Number of nodes N(h) at height h -
	 * N(h) = N(h-1)+N(h-2)+1
	 * where N(0)=1, n(1)=2
	 * construct tree with h-1 and h-2 height.
	 * @f:on
	 * 
	 * @param height
	 * @return root node
	 */
	public static AvlTreeNode<Integer> constructMinimalAvltree(int h) {
		System.out.println("Minimal AVL Tree of height " + h);
		return constructMinimalAvltree(h + 2, new Model<>(0));
	}

	private static AvlTreeNode<Integer> constructMinimalAvltree(int h, Model<Integer> count) {
		if (h <= 1) {
			return null;
		}
		AvlTreeNode<Integer> node = new AvlTreeNode<>();
		node.setLeft(constructMinimalAvltree(h - 1, count));
		node.setData(count.getValue());
		count.setValue(count.getValue() + 1);
		node.setHeight(h - 2);
		node.setRight(constructMinimalAvltree(h - 2, count));
		return node;
	}

	/**
	 * same approach as removing nodes outside range. just instead of setting value we just
	 * count if in the range
	 * 
	 * @param root
	 * @param min
	 * @param max
	 * @return node count in range
	 */
	public static int countNodesInRange(AvlTreeNode<Integer> root, int min, int max) {
		if (root == null) {
			return 0;
		}
		int c = 0;
		if (root.getData() >= min && root.getData() <= max) {
			c = 1;
		}

		return countNodesInRange(root.getLeft(), min, max) + countNodesInRange(root.getRight(), min, max) + c;
	}

	public static void printNodesInRange(AvlTreeNode<Integer> root, int min, int max) {
		if (root == null) {
			return;
		}
		if (root.getData() > min) {
			printNodesInRange(root.getLeft(), min, max);
		}
		if (root.getData() >= min && root.getData() <= max) {
			System.out.print(root.getData() + " ");
		}
		if (root.getData() < max) {
			printNodesInRange(root.getRight(), min, max);
		}
	}

}

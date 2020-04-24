package com.daa.tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.TreeMap;

import com.daa.math.MathUtil;
import com.daa.model.Model;
import com.daa.model.Pair;

public class TreeTraversalPractice {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		TreeNode<Integer> root = null;
		TreeNode<Integer> head = null;
		root = BinaryTreeUtil.constructTreeFromConsole(sc);
		System.out.println("Number of Leaf Nodes : " + numberOfLeaves(root));
		int item = 12;
		System.out.println("Find node with data " + item + " is -> " + findNode(root, item));
		System.out.println("Minimum depth in a tree : " + BinaryTreeUtil.minimumDepthRecursive(root));
		System.out.println("Minimum depth in a tree : " + BinaryTreeUtil.minimumDepthIterative(root));

//		TreeNode<Integer> special = constructSpecialBinaryTree(new int[] {1,1,0,0,0});
//		System.out.println("in order :");
//		printInOrder(special);
//		System.out.println("pre order :");
//		printPreOrder(special);
//		System.out.println("post order :");
//		printPostOrder(special);
//		printLevelOrder(special);
//		System.out.println("Construct tree from in order and pre order");
		TreeNode<Integer> node = getTreeFromInOrderAndPreOrder(new int[] { 4, 8, 2, 5, 1, 6, 3, 7 },
				new int[] { 1, 2, 4, 8, 5, 3, 6, 7 });
//		System.out.println("in order :");
//		printInOrder(node);
//		System.out.println("pre order :");
//		printPreOrder(node);
//		System.out.println("post order :");
//		printPostOrder(node);
//		printLevelOrder(node);
//		System.out.println("-----------");
//		node = getTreeFromInOrderAndPostOrder(new int[] { 4, 8, 2, 5, 1, 6, 3, 7 },
//				new int[] { 8, 4, 5, 2, 6, 7, 3, 1 });
		System.out.println("in order :");
		printInOrderIterative(node);
		System.out.println("pre order :");
		printPreOrderIterative(node);
		printLevelOrderIterative(node);
		System.out.println("Ancestor of node : " + 8);
		getAllAncestor(8, node);
		System.out.println();
		System.out.println("Lowest common Ancestor of node : 2,6 ");
		System.out.println(lowestCommonAncestor(node, 2, 6));

		System.out.println("Enter First tree");
		root = BinaryTreeUtil.constructTreeFromConsole(sc);
		System.out.println("Enter second tree");
		head = BinaryTreeUtil.constructTreeFromConsole(sc);

		System.out.println("pre order :");
		printPreOrderIterative(root);
		System.out.println("in order :");
		printInOrderIterative(root);
		ZigZagTraversal(root);
		verticalOrderTraversalIterative(root);
		verticalOrderTraversal(root);
		sumVerticalNodes(root);
//		System.out.println("post order :");
//		printPostOrder(node);
//		System.out.println(findNode(node, 35));
		printLevelOrderIterative(root);
		System.out.println("size : " + size(root));
//		printLevelOrderReverse(node);
		System.out.println("Height of the tree is : " + getHeight(root));
//		System.out.println("Height of the tree iterative is : "+getHeightIterative(node));
//		System.out.println("Minimum Height of the tree is : "+getMinHeight(node));
//		System.out.println("Minimum Height of the tree iterative is : "+getMinHeightIterative(node));
		System.out.println("Number of leaves : " + numberOfLeaves(root));
		System.out.println("Number of leaves Iterative : " + numberOfLeavesIterative(root));
		System.out.println("Number of Full Nodes : " + numberOfFullNodes(root));
		System.out.println("Number of Full Nodes  Iterative : " + numberOfFullNodesIterative(root));
		System.out.println("Number of Half Nodes : " + numberOfHalfNodes(root));
		System.out.println("Number of Half Nodes Iterative : " + numberOfHalfNodesIterative(root));
		System.out.println("two tree are identical : " + identical(root, head));
		System.out.println("Diameter : " + getDiameter(root));
		System.out.println("Diameter optimized: " + diameterOptimized(root));
		System.out.println("Maximum width : " + maximumWidth(root));
		System.out.println("Maximum sum of nodes accross level : " + maxLevelSum(root));
		printRootToLeafPaths(root);
		System.out.println("Enter the sum to check whether there exists a path from root to any node ");
		int sum = sc.nextInt();
		checkSumExists(root, sum);
		System.out.println("Enter the sum to check whether there exists a path from root to leaf node ");
		int sum1 = sc.nextInt();
		System.out.println("Path exists : " + checkSumFromRootToLeaf(root, sum1));
		System.out.println("Sum of all nodes : " + sumOfNodes(root));
//		System.out.print("Mirror tree ");
//		printLevelOrder(mirror(root));
		System.out.println("Two tree are mirror : " + mirror(root, head));

		sc.close();
	}

	/***
	 * 
	 * printPreOrderIterative
	 * we can re-place recursive call with stack. in pre-order -> NLR
	 * 
	 * @f:off
	 * Pop all items one by one. Do following for every popped item 
     *a) print it 
     *b) push its right child 
     *c) push its left child 
     *Note that right child is pushed first so that left is processed first 
	 * @f:on
	 * 
	 * @param node
	 */
	public static void printPreOrderIterative(TreeNode<Integer> node) {
		if (node == null) {
			return;
		}
		Deque<TreeNode<Integer>> stack = new LinkedList<>();
		stack.push(node);
		while (!stack.isEmpty()) {
			node = stack.pop();
			System.out.print(node.getData() + " ");
			if (node.getRight() != null) {
				stack.push(node.getRight());
			}
			if (node.getLeft() != null) {
				stack.push(node.getLeft());
			}
		}
		System.out.println();
	}

	/**
	 * It is just like reversing the output of pre-order traversal and hence we
	 * follow same algorithm as pre-order traversal iterative and to reverse the
	 * output we will take another stack. So, that when we pop from it data will be
	 * opposite.
	 * 
	 * 
	 * o(n),o(n)
	 * 
	 * @param node
	 */
	public static void printPostOrderIterative(TreeNode<Integer> node) {
		if (node == null) {
			return;
		}
		Deque<TreeNode<Integer>> stack = new LinkedList<>();
		Deque<TreeNode<Integer>> result = new LinkedList<>();
		stack.push(node);
		while (!stack.isEmpty()) {
			node = stack.pop();
			result.push(node);
			if (node.getLeft() != null) {
				stack.push(node.getLeft());
			}
			if (node.getRight() != null) {
				stack.push(node.getRight());
			}
		}
		while (!result.isEmpty()) {
			System.out.print(result.pop().getData() + " ");
		}
		System.out.println();
	}

	/**
	 * 
	 * 1.we will push all left nodes first and after that pop element which will be
	 * the last left node. 2.print node and set current right. now again repeat step
	 * 1 for current node
	 * 
	 * @param node
	 */
	public static void printInOrderIterative(TreeNode<Integer> node) {
		Deque<TreeNode<Integer>> st = new LinkedList<>();
		while (!st.isEmpty() || node != null) {
			while (node != null) {
				st.push(node);
				node = node.getLeft();
			}
			node = st.pop();
			System.out.print(node.getData() + " ");
			node = node.getRight();
		}
		System.out.println();
	}

	/**
	 * Search node in binary tree. compare root with item. if found return Node.
	 * check item in left tree recursively if found return Node. if after above call
	 * node still null then check right tree recursively.
	 * 
	 * @param root
	 * @param item
	 * @return node if exists else null
	 */
	public static TreeNode<Integer> findNode(TreeNode<Integer> root, int item) {
		if (root == null) {
			return null;
		}
		if (root.getData() == item) {
			return root;
		}
		TreeNode<Integer> res = findNode(root.getLeft(), item);
		if (res == null) {
			res = findNode(root.getRight(), item);
		}
		return res;
	}

	/**
	 * Insert data in a binary tree
	 * 
	 * @param root
	 */
	public static TreeNode<Integer> insertNodeIterative(TreeNode<Integer> root, int data) {
		if (root == null) {
			return new TreeNode<>(data);
		}
		Queue<TreeNode<Integer>> queue = new LinkedList<>();
		queue.add(root);
		TreeNode<Integer> node = new TreeNode<>(data);
		while (!queue.isEmpty()) {
			TreeNode<Integer> item = queue.poll();
			if (item.getLeft() == null) {
				item.setLeft(node);
				break;
			} else {
				queue.add(item.getLeft());
			}
			if (item.getRight() == null) {
				item.setRight(node);
				break;
			} else {
				queue.add(item.getRight());
			}
		}
		return root;
	}

	/**
	 * insert data acc to binary search rule
	 * 
	 * @param root
	 * @param item
	 * @return
	 */
	public static TreeNode<Integer> insertRecursion(TreeNode<Integer> root, int item) {
		if (root == null) {
			return new TreeNode<>(item);
		}
		if (root.getData() < item) {
			if (root.getRight() == null) {
				root.setRight(new TreeNode<>(item));
			} else {
				insertRecursion(root.getRight(), item);
			}
		} else {
			if (root.getLeft() == null) {
				root.setLeft(new TreeNode<>(item));
			} else {
				insertRecursion(root.getLeft(), item);
			}
		}
		return root;
	}

	public static int size(TreeNode<Integer> root) {
		if (root == null) {
			return 0;
		}
		return size(root.getLeft()) + size(root.getRight()) + 1;
	}

	/**
	 * print the level order traversal of binary tree in reverse order
	 * 
	 * @param root
	 */
	public static void printLevelOrderReverse(TreeNode<Integer> root) {
		if (root == null) {
			return;
		}

		Queue<TreeNode<Integer>> queue = new LinkedList<>();
		queue.add(root);
		Deque<Integer> stack = new LinkedList<>();
		while (!queue.isEmpty()) {
			TreeNode<Integer> node = queue.poll();
			stack.push(node.getData());
			if (node.getRight() != null) {
				queue.add(node.getRight());
			}
			if (node.getLeft() != null) {
				queue.add(node.getLeft());
			}
		}
		System.out.println("Level Order Traversal in reverse order : ");
		while (!stack.isEmpty()) {
			System.out.print(stack.pop() + " ");
		}
		System.out.println();
	}

	public static void printLevelOrderIterative(TreeNode<Integer> root) {
		if (root == null) {
			return;
		}
		Queue<TreeNode<Integer>> queue = new LinkedList<>();
		queue.add(root);
		System.out.println("Level Order Traversal : ");
		while (!queue.isEmpty()) {
			TreeNode<Integer> node = queue.poll();
			System.out.print(node.getData() + " ");
			if (node.getLeft() != null) {
				queue.add(node.getLeft());
			}
			if (node.getRight() != null) {
				queue.add(node.getRight());
			}
		}
		System.out.println();
	}

	public static int getHeight(TreeNode<Integer> root) {
		if (root == null) {
			return 0;
		}
		int l = getHeight(root.getLeft());
		int r = getHeight(root.getRight());
		return MathUtil.max(l, r)+1;
	}

	public static int getHeightIterative(TreeNode<Integer> root) {
		if (root == null) {
			return 0;
		}
		Queue<TreeNode<Integer>> queue = new LinkedList<>();
		queue.add(root);
		int res = 0;
		while (!queue.isEmpty()) {
			int max = queue.size();
			while (max-- > 0) {
				TreeNode<Integer> node = queue.poll();
				if (node.getLeft() != null) {
					queue.add(node.getLeft());
				}
				if (node.getRight() != null) {
					queue.add(node.getRight());
				}
			}
			res++;
		}
		return res;

	}

	/**
	 * return smallest height of tree. i.e. smallest path from root to leaf
	 * 
	 * @param root
	 * @return smallest height
	 */
	public static int getMinHeight(TreeNode<Integer> root) {
		if (root == null) {
			return 0;
		}
		int l = getHeight(root.getLeft());
		int r = getHeight(root.getRight());
		return l < r ? l + 1 : r + 1;
	}

	public static int getMinHeightIterative(TreeNode<Integer> root) {
		if (root == null) {
			return 0;
		}
		Queue<TreeNode<Integer>> queue = new LinkedList<>();
		queue.add(root);
		int res = 1;
		while (!queue.isEmpty()) {
			int max = queue.size();
			while (max-- > 0) {
				TreeNode<Integer> node = queue.poll();
				if (node.getLeft() == null && node.getRight() == null) {
					return res;
				}
				if (node.getLeft() != null) {
					queue.add(node.getLeft());
				}
				if (node.getRight() != null) {
					queue.add(node.getRight());
				}
			}
			res++;
		}
		return res;

	}

	public static int numberOfLeaves(TreeNode<Integer> root) {
		if (root == null) {
			return 0;
		}
		if (root.getLeft() == null && root.getRight() == null) {
			return 1;
		}
		return numberOfLeaves(root.getLeft()) + numberOfLeaves(root.getRight());
	}

	/**
	 * 
	 * @param root
	 * @return number of full
	 */
	public static int numberOfLeavesIterative(TreeNode<Integer> root) {
		if (root == null) {
			return 0;
		}
		Queue<TreeNode<Integer>> queue = new LinkedList<>();
		queue.add(root);
		int c = 0;
		while (!queue.isEmpty()) {
			TreeNode<Integer> node1 = queue.poll();
			if (node1.getLeft() == null && node1.getRight() == null) {
				c++;
			}
			if (node1.getLeft() != null) {
				queue.add(node1.getLeft());
			}
			if (node1.getRight() != null) {
				queue.add(node1.getRight());
			}
		}
		return c;
	}

	/**
	 * 
	 * @param root
	 * @return number of leaves
	 */
	public static int numberOfFullNodesIterative(TreeNode<Integer> root) {
		if (root == null) {
			return 0;
		}
		Queue<TreeNode<Integer>> queue = new LinkedList<>();
		queue.add(root);
		int c = 0;
		while (!queue.isEmpty()) {
			TreeNode<Integer> temp = queue.poll();
			if (temp.getLeft() != null && temp.getRight() != null) {
				c++;
			}
			if (temp.getLeft() != null) {
				queue.add(temp.getLeft());
			}
			if (temp.getRight() != null) {
				queue.add(temp.getRight());
			}
		}
		return c;
	}

	/**
	 * all the node with 2 childs
	 * 
	 * @param root
	 * @return count
	 */
	public static int numberOfFullNodes(TreeNode<Integer> root) {
		if (root == null) {
			return 0;
		}
		int c = 0;
		if (root.getLeft() != null && root.getRight() != null) {
			c = 1;
		}
		return c + numberOfFullNodes(root.getLeft()) + numberOfFullNodes(root.getRight());
	}

	/**
	 * ALl the nodes with 1 child
	 * 
	 * @param root
	 * @return count
	 */
	public static int numberOfHalfNodes(TreeNode<Integer> root) {
		if (root == null) {
			return 0;
		}
		int c = 0;
		if (root.getLeft() != null && root.getRight() == null) {
			c++;
		}
		if (root.getLeft() == null && root.getRight() != null) {
			c++;
		}
		return c + numberOfHalfNodes(root.getLeft()) + numberOfHalfNodes(root.getRight());
	}

	public static int numberOfHalfNodesIterative(TreeNode<Integer> root) {
		if (root == null) {
			return 0;
		}
		Queue<TreeNode<Integer>> queue = new LinkedList<>();
		queue.add(root);
		int c = 0;
		while (!queue.isEmpty()) {
			TreeNode<Integer> node = queue.poll();
			if (node.getLeft() != null) {
				queue.add(node.getLeft());
				if (node.getRight() == null) {
					c++;
				}
			}
			if (node.getRight() != null) {
				queue.add(node.getRight());
				if (node.getLeft() == null) {
					c++;
				}
			}
		}
		return c;
	}

	/**
	 * two tree are identical if there structure are identical.
	 * 
	 * @param root1
	 * @param root2
	 * @return
	 */
	public static boolean identical(TreeNode<Integer> root1, TreeNode<Integer> root2) {
		if (root1 == null && root2 == null) {
			return true;
		}

		if (root2 == null || root1 == null) {
			return false;
		}

		return identical(root1.getLeft(), root2.getLeft()) && identical(root1.getRight(), root2.getRight());
	}

	public static boolean identicalIterative(TreeNode<Integer> root1, TreeNode<Integer> root2) {
		if (root1 == null && root2 == null) {
			return true;
		}
		Queue<TreeNode<Integer>> queue1 = new LinkedList<>();
		Queue<TreeNode<Integer>> queue2 = new LinkedList<>();
		queue1.add(root1);
		queue2.add(root2);
		while (!queue1.isEmpty() && !queue2.isEmpty()) {
			TreeNode<Integer> node1 = queue1.poll();
			TreeNode<Integer> node2 = queue2.poll();
			if (node1.getLeft() != null && node2.getLeft() != null) {
				queue1.add(node1.getLeft());
				queue2.add(node2.getLeft());
			}
			if (node1.getRight() != null && node2.getRight() != null) {
				queue1.add(node1.getRight());
				queue2.add(node2.getRight());
			}
			if ((node1.getLeft() != null && node2.getLeft() == null)
					|| (node2.getLeft() != null && node1.getLeft() == null)) {
				return false;
			}
		}
		return queue1.isEmpty() && queue2.isEmpty();
	}

	/**
	 * find the diameter of the tree. it is the number of nodes in the longest path
	 * of two leaf nodes. i.e. count the number of nodes while finding the the
	 * longest of two leafs. it can be maximum of below three operation - 1. height
	 * of the tree 2. diameter of left tree 3. diameter of right tree. O(n2)
	 * 
	 * @param root
	 * @return diameter
	 */
	public static int getDiameter(TreeNode<Integer> root) {
		if (root == null) {
			return 0;
		}
		int res = getHeight(root.getLeft()) + getHeight(root.getRight());
		int l = getDiameter(root.getLeft());
		int r = getDiameter(root.getRight());
		return MathUtil.max(res + 1, Math.max(l, r));
	}

	/**
	 * optimized version which take o(n) time instead of o(n2)
	 * 
	 * Diameter of a tree can be calculated by only using the height function,
	 * because the diameter of a tree is nothing but maximum value of (left_height +
	 * right_height + 1) for each node.
	 * 
	 * @param root
	 * @return diameter
	 */
	public static int diameterOptimized(TreeNode<Integer> root) {
		int[] max = new int[1];
		diameterOptimized(root, max);
		return max[0];
	}

	private static int diameterOptimized(TreeNode<Integer> root, int[] max) {
		if (root == null) {
			return 0;
		}
		int l = diameterOptimized(root.getLeft(), max);
		int r = diameterOptimized(root.getRight(), max);
		max[0] = MathUtil.max(l + r + 1, max[0]); // update maximum by checking current node height
		return MathUtil.max(l, r) + 1;
	}

	/**
	 * Maximum width of a tree is the maximum number of nodes present in the level.
	 * calculate the number of nodes at each level and update the max according to
	 * nodes.
	 * 
	 * find the number of element at queue and then iterate over that to get all the
	 * nodes added to queue in particular level.
	 * 
	 * @param root
	 * @return max width
	 */
	public static int maximumWidth(TreeNode<Integer> root) {
		if (root == null) {
			return 0;
		}
		Queue<TreeNode<Integer>> queue = new LinkedList<>();
		queue.add(root);
		int max = 0;
		while (!queue.isEmpty()) {
			int size = queue.size();
			if (queue.size() > max) {
				max = queue.size();
			}
			while (size-- > 0) {
				TreeNode<Integer> node = queue.poll();
				if (node.getLeft() != null) {
					queue.add(node.getLeft());
				}
				if (node.getRight() != null) {
					queue.add(node.getRight());
				}
			}
		}
		return max;
	}

	/**
	 * Find the level with maximum sum of nodes. use level order approach and
	 * calculate sum of nodes level wise. and update the max.
	 * 
	 * @param root
	 * @return maximum sum
	 * 
	 */
	public static int maxLevelSum(TreeNode<Integer> root) {
		if (root == null) {
			return 0;
		}
		Queue<TreeNode<Integer>> queue = new LinkedList<>();
		queue.add(root);
		int max = 0;
		while (!queue.isEmpty()) {
			int size = queue.size();
			int sum = 0;
			while (size-- > 0) {
				TreeNode<Integer> node = queue.poll();
				sum = sum + node.getData();
				if (node.getLeft() != null) {
					queue.add(node.getLeft());
				}
				if (node.getRight() != null) {
					queue.add(node.getRight());
				}
			}
			if (sum > max) {
				max = sum;
			}
		}
		return max;
	}

	/**
	 * Print all root to leaf paths in a tree. O(n2)
	 * 
	 * use pre-order traversal and add node.data to index of there order in array.
	 * Once you reach leaf. loop the array till index and print the data.
	 * 
	 * @param root
	 */
	public static void printRootToLeafPaths(TreeNode<Integer> root) {
		printRootToLeafPaths(root, new ArrayList<>());
	}

	private static void printRootToLeafPaths(TreeNode<Integer> root, List<Integer> list) {
		if (root == null) {
			return;
		}
		list.add(root.getData());
		if (root.getLeft() == null && root.getRight() == null) {
			list.forEach(i -> System.out.print(i + " "));
			System.out.println();
		}
		printRootToLeafPaths(root.getLeft(), list);
		printRootToLeafPaths(root.getRight(), list);
		list.remove(list.size() - 1);
	}

	/**
	 * Enter the sum to check whether there exists a path from root to any node.
	 * print all such paths.
	 * 
	 * @param root
	 * @param sum
	 */
	public static void checkSumExists(TreeNode<Integer> root, int sum) {
		checkAndPrintSumExists(root, new ArrayList<>(), 0, sum);
	}

	private static void checkAndPrintSumExists(TreeNode<Integer> root, List<Integer> list, int sumSoFar, int sum) {
		if (root == null) {
			return;
		}
		sumSoFar = sumSoFar + root.getData();
		list.add(root.getData());
		if (sumSoFar == sum) {
			System.out.println("exist path : ");
			for (int i = 0; i < list.size(); i++) {
				System.out.print(list.get(i) + " ");
			}
			System.out.println();
		}
		checkAndPrintSumExists(root.getLeft(), list, sumSoFar, sum);
		checkAndPrintSumExists(root.getRight(), list, sumSoFar, sum);
		list.remove(list.size() - 1);
	}

	/**
	 * Enter the sum to check whether there exists a path from root to leaf node.
	 * 
	 * @param root
	 * @param sum
	 * @return true if exists
	 */
	public static boolean checkSumFromRootToLeaf(TreeNode<Integer> root, int sum) {
		if (root == null) {
			return false;
		}
		if (sum == root.getData() && root.getLeft() == null && root.getRight() == null) {
			return true;
		} else {
			int data = sum - root.getData();
			return checkSumFromRootToLeaf(root.getLeft(), data) || checkSumFromRootToLeaf(root.getRight(), data);
		}
	}

	/**
	 * Find sum of all nodes in binary tree
	 * 
	 * @param root
	 * @return sum
	 */
	public static int sumOfNodes(TreeNode<Integer> root) {
		if (root == null) {
			return 0;
		}
		return sumOfNodes(root.getLeft()) + sumOfNodes(root.getRight()) + root.getData();
	}

	public static TreeNode<Integer> mirror(TreeNode<Integer> root) {
		if (root == null) {
			return root;
		}
		TreeNode<Integer> temp = root.getLeft();
		root.setLeft(root.getRight());
		root.setRight(temp);
		mirror(root.getLeft());
		mirror(root.getRight());
		return root;
	}

	/**
	 * apply identical structure check approach. and add left.data and right.data
	 * comparison also
	 * 
	 * @param root1
	 * @param root2
	 * @return true if they are mirror image of each other
	 */
	public static boolean mirror(TreeNode<Integer> root1, TreeNode<Integer> root2) {
		if (root1 == null && root2 == null) {
			return true;
		}

		if (root1 == null || root2 == null) {
			return false;
		}
		if (root1.getData().equals(root2.getData())) {
			return mirror(root1.getLeft(), root2.getRight()) && mirror(root1.getRight(), root2.getLeft());
		} else {
			return false;
		}
	}

	/**
	 * 
	 * get node from preorder and search that in inorder array to get its position
	 * from left or right
	 * 
	 * @param in
	 * @param pre
	 * @return root node
	 */
	public static TreeNode<Integer> getTreeFromInOrderAndPreOrder(int[] in, int[] pre) {
		return getTreeFromInOrderAndPreOrder(new Model<Integer>(0), in, pre, 0, pre.length - 1);
	}

	private static TreeNode<Integer> getTreeFromInOrderAndPreOrder(Model<Integer> c, int[] in, int[] pre, int start,
			int end) {
		if (start > end) {
			return null;
		}
		TreeNode<Integer> root = new TreeNode<>(pre[c.getValue()]);
		c.setValue(c.getValue() + 1);
		int mid = findIndex(in, root.getData());
		root.setLeft(getTreeFromInOrderAndPreOrder(c, in, pre, start, mid - 1));
		root.setRight(getTreeFromInOrderAndPreOrder(c, in, pre, mid + 1, end));
		return root;
	}

	/**
	 * find and return the index of item to be searched
	 * 
	 * @param in
	 * @param item
	 * @return index
	 */
	private static int findIndex(int[] in, int item) {
		for (int i = 0; i < in.length; i++) {
			if (in[i] == item) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * 
	 * Get node from post-order and search that in in-order array to get its
	 * position from left or right
	 * 
	 * @param in   array
	 * @param post array
	 * @return root node
	 */
	public static TreeNode<Integer> getTreeFromInOrderAndPostOrder(int[] in, int[] post) {
		return getTreeFromInOrderAndPostOrder(new Model<Integer>(post.length - 1), in, post, 0, post.length - 1);
	}

	private static TreeNode<Integer> getTreeFromInOrderAndPostOrder(Model<Integer> model, int[] in, int[] post,
			int start, int end) {
		if (start > end) {
			return null;
		}
		TreeNode<Integer> node = new TreeNode<>(post[model.getValue()]);

		model.setValue(model.getValue() - 1);
		int mid = findIndex(in, node.getData());//we can use hashmap to make it perform better
		node.setRight(getTreeFromInOrderAndPostOrder(model, in, post, mid + 1, end));
		node.setLeft(getTreeFromInOrderAndPostOrder(model, in, post, start, mid - 1));
		return node;
	}

	/**
	 * Takes root of a tree and print all ancestor of a given node. i.e. it is just
	 * like printing the path from root to that node.
	 * 
	 * @param data
	 * @param root
	 */
	public static boolean getAllAncestor(int data, TreeNode<Integer> root) {

		if (root == null) {
			return false;
		}
		if (root.getData() == data) {
			System.out.print(root.getData() + " ");
			return true;
		}
		if (getAllAncestor(data, root.getLeft()) || getAllAncestor(data, root.getRight())) {
			System.out.print(root.getData() + " ");
			return true;
		}
		return false;
	}

	/**
	 * Find the lowest common ancestor of two nodes. Assumption - both nodes are
	 * always present. if two nodes stated are parent child than parent is lca.
	 * 
	 * The lowest common ancestor between two nodes n1 and n2 is defined as the
	 * lowest node in T that has both n1 and n2 as descendants if n1 is 2 and n2 =4
	 * and n1 and n2 are child of 3. than lca is 3 if n1 is 1 and n2=2 and n1 is
	 * child of n2. then lca is 2
	 * 
	 * @param root
	 * @param item1
	 * @param item2
	 * @return lca node
	 */
	public static TreeNode<Integer> lowestCommonAncestor(TreeNode<Integer> root, int item1, int item2) {
		if (root == null) {
			return null;
		}
		if (root.getData() == item1 || root.getData() == item2) {
			return root;
		}

		TreeNode<Integer> left = lowestCommonAncestor(root.getLeft(), item1, item2);
		TreeNode<Integer> right = lowestCommonAncestor(root.getRight(), item1, item2);
		if (left != null && right != null) {
			return root;
		}
		return left != null ? left : right;
	}

	/**
	 * print the tree in zig zag order.
	 * 
	 * i.e. print data in level order traversal wise. just print data left to right
	 * and right to left alternatively take two stacks. one that is holding current
	 * level data and second using next level data. just on alternate levels add
	 * left child first and then right child first. in this way zig zag order will
	 * be maintained. boolean is kept to alter the level once current level
	 * finished. in case of current level is empty just swap it with with next level
	 * stack. as we are popping from current only.
	 * 
	 * @param root
	 */
	public static void ZigZagTraversal(TreeNode<Integer> root) {
		if (root == null) {
			return;
		}
		Deque<TreeNode<Integer>> current = new LinkedList<>();
		Deque<TreeNode<Integer>> next = new LinkedList<>();
		current.push(root);
		boolean leftToRight = true;
		System.out.println("Zig Zag order traversal is : ");
		while (!current.isEmpty()) {
			TreeNode<Integer> node = current.pop();
			System.out.print(node.getData() + " ");
			if (leftToRight) {
				if (node.getLeft() != null) {
					next.push(node.getLeft());
				}
				if (node.getRight() != null) {
					next.push(node.getRight());
				}
			} else {
				if (node.getRight() != null) {
					next.push(node.getRight());
				}
				if (node.getLeft() != null) {
					next.push(node.getLeft());
				}
			}
			if (current.isEmpty()) {
				leftToRight = !leftToRight;
				Deque<TreeNode<Integer>> temp = current;
				current = next;
				next = temp;
			}
		}
		System.out.println();
	}

	/**
	 * use level order traversal approach and keep treemap to store key as
	 * horizontal distance (HD) and value as list of integer with corresponding sum
	 * tree-map is used to data in horizontal distance wise. queue store pair model
	 * with first value as node and second as HD of that node.
	 * 
	 * Hd formula - HD for root is 0, a right edge (edge connecting to right
	 * subtree) is considered as +1 horizontal distance and a left edge is
	 * considered as -1 horizontal distance
	 * 
	 * O(N)
	 * 
	 * @param root
	 */
	public static void verticalOrderTraversalIterative(TreeNode<Integer> root) {
		if (root == null) {
			return;
		}

		Queue<Pair<TreeNode<Integer>, Integer>> queue = new LinkedList<>();
		TreeMap<Integer, List<Integer>> map = new TreeMap<>();
		queue.add(new Pair<>(root, 0));
		List<Integer> list = new LinkedList<>();
		list.add(root.getData());
		map.put(0, list);
		while (!queue.isEmpty()) {
			Pair<TreeNode<Integer>, Integer> pair = queue.poll();
			if (pair.getFirst().getLeft() != null) {
				if (!map.containsKey(pair.getSecond() - 1)) {
					map.put(pair.getSecond() - 1, new LinkedList<>());
				}
				map.get(pair.getSecond() - 1).add(pair.getFirst().getLeft().getData());
				queue.add(new Pair<>(pair.getFirst().getLeft(), pair.getSecond() - 1));
			}
			if (pair.getFirst().getRight() != null) {
				if (!map.containsKey(pair.getSecond() + 1)) {
					map.put(pair.getSecond() + 1, new LinkedList<>());
				}
				map.get(pair.getSecond() + 1).add(pair.getFirst().getRight().getData());
				queue.add(new Pair<>(pair.getFirst().getRight(), pair.getSecond() + 1));
			}
		}
		System.out.println("Vertical order traversal is : ");
		map.forEach((k, v) -> v.forEach(o -> System.out.print(o + " ")));
		System.out.println();
	}

	/**
	 * use tree-map to store HD as key and list of items as values for that hd.
	 * pre-order approach is used to travsere tree.
	 * 
	 * @param root
	 */
	public static void verticalOrderTraversal(TreeNode<Integer> root) {
		TreeMap<Integer, List<Integer>> map = new TreeMap<>();
		verticalOrderTraversal(root, 0, map);
		System.out.println("Vertical order traversal is : ");
		map.forEach((k, v) -> v.forEach(o -> System.out.print(o + " ")));
		System.out.println();
	}

	private static void verticalOrderTraversal(TreeNode<Integer> root, int hd, TreeMap<Integer, List<Integer>> map) {
		if (root == null) {
			return;
		}
		if (!map.containsKey(hd)) {
			map.put(hd, new LinkedList<>());
		}
		map.get(hd).add(root.getData());
		verticalOrderTraversal(root.getLeft(), hd - 1, map);
		verticalOrderTraversal(root.getRight(), hd + 1, map);
	}

	/**
	 * sum of the vertical nodes in a tree.
	 * 
	 * same as above approach just instead of list we just need to keep the sum of
	 * nodes.
	 * 
	 * @param root
	 */
	public static void sumVerticalNodes(TreeNode<Integer> root) {
		TreeMap<Integer, Integer> map = new TreeMap<>();
		sumVerticalNodes(root, 0, map);
		System.out.println("Vertical order sum of nodes : ");
		map.forEach((k, v) -> System.out.print(v + " "));
		System.out.println();
	}

	private static void sumVerticalNodes(TreeNode<Integer> root, int hd, TreeMap<Integer, Integer> map) {
		if (root == null) {
			return;
		}
		if (!map.containsKey(hd)) {
			map.put(hd, root.getData());
		} else {
			map.put(hd, map.get(hd) + root.getData());
		}
		sumVerticalNodes(root.getLeft(), hd - 1, map);
		sumVerticalNodes(root.getRight(), hd + 1, map);
	}

	/**
	 * update the next sibling of treeNode.
	 * use level order approach
	 * @f:off
	 * output - 
	 * 
	 * 	  1-->X
	 *	 / \
	 *  2-->3-->X
	 * / \   \
	 *4-->5-->6-->X
	 *
	 * @f:on
	 * 
	 * @param root
	 */
	public static void nextSibling(TreeNodeModified root) {

		if (root == null) {
			return;
		}
		Queue<TreeNodeModified> queue = new LinkedList<>();
		queue.add(root);

		while (!queue.isEmpty()) {
			int count = queue.size();
			while (count > 0) {
				TreeNodeModified node = queue.poll();
				node.setNextSibling(queue.peek());
				if (node.getLeft() != null) {
					queue.add(node.getLeft());
				}
				if (node.getRight() != null) {
					queue.add(node.getRight());
				}
				count--;
			}
		}

	}

	/**
	 * construct tree form pre-order only. pre-order data will either 1 or 0. 1
	 * Represent parent and 0 child. there will be either 0 child or 2 child at any
	 * given node.
	 * 
	 * @param pre
	 * @return root node of the tree
	 */
	public static TreeNode<Integer> constructSpecialBinaryTreeIterative(int[] pre) {
		int start = 0;
		TreeNode<Integer> root = new TreeNode<>(pre[start++]);
		Queue<TreeNode<Integer>> queue = new LinkedList<>();
		queue.add(root);
		while (!queue.isEmpty()) {
			TreeNode<Integer> node = queue.poll();
			if (node.getLeft() == null) {
				node.setLeft(new TreeNode<>(pre[start++]));
				if (node.getLeft().getData() == 1) {
					queue.add(node.getLeft());
				}
			}
			if (node.getRight() == null) {
				node.setRight(new TreeNode<>(pre[start++]));
				if (node.getRight().getData() == 1) {
					queue.add(node.getRight());
				}
			}
		}
		return root;
	}

	/**
	 * construct tree form pre-order only. pre-order data will either 1 or 0. 1
	 * Represent parent and 0 child. there will be either 0 child or 2 child at any
	 * given node.
	 * 
	 * @param pre
	 * @return root node of the tree
	 */
	public static TreeNode<Integer> constructSpecialBinaryTree(int[] pre) {
		return constructSpecialBinaryTree(pre, 0);
	}

	private static TreeNode<Integer> constructSpecialBinaryTree(int[] pre, int index) {
		if (index >= pre.length) {
			return null;
		}
		TreeNode<Integer> root = new TreeNode<>(pre[index]);
		if (pre[index] == 0) {
			return root;
		}
		root.setLeft(constructSpecialBinaryTree(pre, index + 1));
		root.setRight(constructSpecialBinaryTree(pre, index + 2));
		return root;
	}

}

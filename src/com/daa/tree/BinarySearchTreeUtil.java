package com.daa.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

import com.daa.array.sorting.SortUtil;
import com.daa.list.LinkedListUtil;
import com.daa.list.Node;
import com.daa.math.MathUtil;
import com.daa.model.Model;
import com.daa.tree.model.TreeNodeSize;

public class BinarySearchTreeUtil {

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			TreeNode<Integer> root1 = BinaryTreeUtil.constructTreeFromConsole(sc);
//			System.out.println("print nodes in range 10 and 22 : ");
//			printNodesInRange(root1, 10, 22);
//			System.out.println();
			System.out.println("remove node outside range -10 and 13");
			root1 = removeNodeOutsideRange(root1, -10, 13);
			System.out.println("in order traversal : " + BinaryTreeUtil.inOrderTraversal(root1));
			TreeNode<Integer> root2 = BinaryTreeUtil.constructTreeFromConsole(sc);
			System.out.println("Two tree has same elements : " + sameElements(root1, root2));
			printIntersection(root1, root2);
			printUnion(root1, root2);
			TreeNode<Integer> root = mergeTwoTrees(root1, root2);
//			TreeNode<Integer> root = getBstFromInorder(new int[] { 1, 3, 5, 7, 9, 11, 13 });
			String s = BinaryTreeUtil.preOrderTraversal(root);
			System.out.println("Pre Order of tree - " + s);
			System.out.println("in order traversal : " + BinaryTreeUtil.inOrderTraversal(root));
//			IntStream.range(1, 8).forEach(k -> System.out.println(k + " smallest of tree is : " + findKthSmallestItem(root, k)));
//			IntStream.range(1, 9).forEach(k -> System.out.println("ceil node of " + k + " is : " + ceilNode(root, k)));
//			IntStream.range(1, 9).forEach(k -> System.out.println("floor Node of " + k + " is : " + floorNode(root, k)));
//			System.out.println("----------------------------------------");
//			System.out.println("is binary search tree : " + isBinarySearchTree(root));
//			System.out.println("convert binary search tree to double linked list ");
//			TreeNode<Integer> headDll = getDoublyLinkedList(root);
//			printDLL(headDll);
//			System.out.println("convert double linked list to binary search tree ");
//			TreeNode<Integer> node = getBSTfromDLL(headDll);
//			System.out.println("in order traversal : " + BinaryTreeUtil.inOrderTraversal(node));
//			testSll();
//			String[] ch = s.split(" ");
//			for (int i = 0; i < ch.length; i++) {
//				System.out.println("Search Node with data -> " + ch[i] + " : " + findNodeIterative(root, Integer.valueOf(ch[i])));
//			}
//			System.out.println("Search Node with data -> " + 0 + " : " + findNodeIterative(root, 0));
//
//			System.out.println("--------------------------------------------");
//			String s1 = BinaryTreeUtil.inOrderTraversal(root);
//			System.out.println("in order traversal is : " + s1);
//			String[] str = s1.trim().split(" ");
//			for (String so : str) {
//				System.out.println("in-order successor of node " + so + " is : " + inOrderSuccessor(root, Integer.valueOf(so)));
//			}
//			System.out.println("--------------------------------------------");
//			for (String so : str) {
//				System.out.println("in-order predecessor of node " + so + " is : " + inOrderPredecessor(root, Integer.valueOf(so)));
//			}
//			System.out.println("--------------------------------------------");
//			System.out.println("Enter node you want to insert in BST : ");
//			int d = sc.nextInt();
//			System.out.println("--------------------------------------------");
//			root = insertNode(root, d);
//			String s2 = BinaryTreeUtil.preOrderTraversal(root);
//			System.out.println("pre order traversal is : " + s2);
//			System.out.println("--------------------------------------------");
//			System.out.println("shortest path : "+shortestPath(root, 3, 10));
//			String[] res = s.split(" ");
//			for (int i = 0; i < res.length; i++) {
//				for (int j = i + 1; j < res.length; j++) {
//					if (i != j) {
//						System.out.println("LCA of Nodes : [" + res[i] + " , " + res[j] + "] is : "
//								+ lowestCommonAncestor(root, Integer.valueOf(res[i]), Integer.valueOf(res[j])) + " and Path is : "
//								+ shortestPath(root, Integer.valueOf(res[i]), Integer.valueOf(res[j])));
//					}
//				}
//			}
//			for (String so : s2.split(" ")) {
//				System.out.print("After Deleting node " + so + " Tree ");
//				root = deleteNode(root, Integer.valueOf(so));
//				String q = BinaryTreeUtil.inOrderTraversal(root);
//				System.out.println("in order traversal is : " + q);
//			}
//
		}
	}

	private static void testSll() {
		Node<Integer> node = new Node<>(1);
		node.setNext(new Node<Integer>(2));
		node.getNext().setNext(new Node<Integer>(3));
		node.getNext().getNext().setNext(new Node<Integer>(4));
		node.getNext().getNext().getNext().setNext(new Node<Integer>(5));
		node.getNext().getNext().getNext().getNext().setNext(new Node<Integer>(6));
		System.out.println("**********************************************");
		System.out.println("Binary search tree from sorted single linked list");
		TreeNode<Integer> root = getBSTfromSLL(node);
		System.out.println("in order traversal : " + BinaryTreeUtil.inOrderTraversal(root));
	}

	public static int getHeight(TreeNode<Integer> root) {
		if (root == null) {
			return 0;
		}
		return MathUtil.max(getHeight(root.getLeft()), getHeight(root.getRight())) + 1;
	}
	/**
	 * 
	 * check if both node smaller than root if yes go left else if both node are greater than
	 * root go right. if both above conditions failed it means we have lca node.
	 * 
	 * @return node which is lowest common ancestor of two nodes
	 * 
	 */
	public static TreeNode<Integer> lowestCommonAncestor(TreeNode<Integer> root, Integer node1, Integer node2) {
		while (root != null) {
			if (root.getData() > node1 && root.getData() > node2) {
				root = root.getLeft();
			} else if (root.getData() < node1 && root.getData() < node2) {
				root = root.getRight();
			} else {
				break;
			}
		}
		return root;
	}

	/**
	 * Calculate Inorder From PreOrder sort the array.
	 * 
	 * @param arr inorder
	 */
	public static void inOrderFromPreOrder(int[] arr) {
		Arrays.sort(arr);
		System.out.println("In Order of Tree");
		Arrays.stream(arr).forEach(i -> System.out.print(i + " "));
		System.out.println();
	}

	/**
	 * from given in-order array construct binary search tree find the mid element and make it
	 * head. all the left will be its left and right will be right. repeat the process till
	 * start<end else return null.
	 * 
	 * @param arr
	 * @return Root node
	 */
	public static TreeNode<Integer> getBstFromInorder(int[] arr) {
		return bstFromInorder(arr, 0, arr.length - 1);
	}

	private static TreeNode<Integer> bstFromInorder(int[] arr, int start, int end) {
		if (start > end) {
			return null;
		}
		int mid = (start + end) / 2;
		TreeNode<Integer> node = new TreeNode<>(arr[mid]);
		node.setLeft(bstFromInorder(arr, start, mid - 1));
		node.setRight(bstFromInorder(arr, mid + 1, end));
		return node;
	}

	private BinarySearchTreeUtil() {

	}

	public static void preOrderFromInOrder(int[] arr) {
		System.out.println("Pre Order of Tree");
		preOrderFromInOrder(arr, 0, arr.length - 1);
		System.out.println();
	}

	public static void postOrderFromInOrder(int[] arr) {
		System.out.println("Post Order of Tree");
		postOrderFromInOrder(arr, 0, arr.length - 1);
		System.out.println();
	}

	private static void preOrderFromInOrder(int[] arr, int start, int end) {
		if (start > end) {
			return;
		}
		int mid = (start + end) / 2;
		System.out.print(arr[mid] + " ");
		preOrderFromInOrder(arr, start, mid - 1);
		preOrderFromInOrder(arr, mid + 1, end);
	}

	private static void postOrderFromInOrder(int[] arr, int start, int end) {
		if (start > end) {
			return;
		}
		int mid = (start + end) / 2;
		postOrderFromInOrder(arr, start, mid - 1);
		postOrderFromInOrder(arr, mid + 1, end);
		System.out.print(arr[mid] + " ");
	}

	public static TreeNode<Integer> bstFromPreOrder(int[] pre) {
		SortUtil.mergeSort(pre);
		return getBstFromInorder(pre);

	}

	/**
	 * print the common nodes from both tree.
	 * 
	 * @param root1
	 * @param root2
	 */
	public static void printCommonNodes(TreeNode<Integer> root1, TreeNode<Integer> root2) {
		List<Integer> first = new ArrayList<>();
		inorderModified(root1, first, true);
		inorderModified(root2, first, false);
	}

	/**
	 * in-order which add data to list if flag is true otherwise print the data if list
	 * contains that data
	 * 
	 * @param node
	 * @param list
	 * @param flag
	 */
	private static void inorderModified(TreeNode<Integer> node, List<Integer> list, boolean flag) {
		if (node == null) {
			return;
		}
		inorderModified(node.getLeft(), list, flag);
		if (flag) {
			list.add(node.getData());
		} else if (list.contains(node.getData())) {
			System.out.print(node.getData() + " ");
		}
		inorderModified(node.getRight(), list, flag);
	}

	/**
	 * search node in BST. check data is greater than root - move right less than root - move
	 * left if match return;
	 * 
	 * @param root
	 * @param data
	 * @return search node
	 */
	public static TreeNode<Integer> findNode(TreeNode<Integer> root, int data) {
		if (root == null)
			return null;
		if (root.getData().equals(data)) {
			return root;
		}
		if (root.getData() < data) {
			return findNode(root.getRight(), data);
		}

		return findNode(root.getLeft(), data);
	}

	/**
	 * 
	 * @param root
	 * @param data
	 * @return searched node from tree
	 */
	public static TreeNode<Integer> findNodeIterative(TreeNode<Integer> root, int data) {
		while (root != null) {
			if (root.getData().equals(data)) {
				return root;
			}
			if (root.getData() < data) {
				root = root.getRight();
			} else {
				root = root.getLeft();
			}
		}
		return null;
	}

	/**
	 * if search node has right child. then left most node of right child is its in-order
	 * successor node. else if data is greater than root. check right tree to search node.
	 * else set inOrderSuccessor node as root and check left tree to search node.
	 * 
	 * @param root
	 * @param data
	 * @return inorder successor of a given node
	 */
	public static TreeNode<Integer> inOrderSuccessor(TreeNode<Integer> root, int data) {
		Model<TreeNode<Integer>> model = new Model<>();
		findInOrderSuccessor(root, model, data);
		return model.getValue();
	}

	private static void findInOrderSuccessor(TreeNode<Integer> root, Model<TreeNode<Integer>> succ, int data) {
		if (root == null)
			return;
		if (root.getData() == data) {
			if (root.getRight() != null) {
				TreeNode<Integer> temp = root.getRight();
				while (temp.getLeft() != null) {
					temp = temp.getLeft();
				}
				succ.setValue(temp);
			}
		} else if (root.getData() < data) {
			findInOrderSuccessor(root.getRight(), succ, data);
		} else {
			succ.setValue(root);
			findInOrderSuccessor(root.getLeft(), succ, data);
		}
	}

	/**
	 * if search node has left child. then right most node of left child is its inOrder
	 * Predecessor node. else if data is greater than root. set inOrderPredecessor node as
	 * root.and check right tree to search node. else check left tree to search node.
	 * 
	 * @param root
	 * @param data
	 * @return inOrderPredecessor of a given node
	 */
	public static TreeNode<Integer> inOrderPredecessor(TreeNode<Integer> root, int data) {
		Model<TreeNode<Integer>> model = new Model<>(null);
		inOrderPredecessor(root, model, data);
		return model.getValue();
	}

	private static void inOrderPredecessor(TreeNode<Integer> root, Model<TreeNode<Integer>> pred, int data) {
		if (root == null) {
			return;
		}
		if (root.getData() == data) {
			if (root.getLeft() != null) {
				TreeNode<Integer> temp = root.getLeft();
				while (temp.getRight() != null) {
					temp = temp.getRight();
				}
				pred.setValue(temp);
			}
		} else if (root.getData() > data) {
			inOrderPredecessor(root.getLeft(), pred, data);
		} else {
			pred.setValue(root);
			inOrderPredecessor(root.getRight(), pred, data);
		}
	}

	/**
	 * check if root is null - return new node. now move to left or right acc to data. and
	 * reach node whose left or right is null. and set accordingly
	 * 
	 * @param root
	 * @param data
	 * @return root
	 */
	public static TreeNode<Integer> insertNode(TreeNode<Integer> root, int data) {
		if (root == null) {
			return new TreeNode<>(data);
		}
		if (root.getData() < data) {
			root.setRight(insertNode(root.getRight(), data));
		} else {
			root.setLeft(insertNode(root.getLeft(), data));
		}
		return root;
	}

	public static TreeNode<Integer> deleteNode(TreeNode<Integer> root, int data) {
		if (root == null) {
			return null;
		}
		if (root.getData() == data) {
			if (root.getLeft() == null && root.getRight() == null) {
				return null;
			}
			if (root.getRight() == null) {
				return root.getLeft();
			}
			if (root.getLeft() == null) {
				return root.getRight();
			}
			// node has both children
			root.setData(findMax(root.getLeft()).getData());
			root.setLeft(deleteNode(root.getLeft(), root.getData()));
		} else if (root.getData() < data) {
			root.setRight(deleteNode(root.getRight(), data));
		} else {
			root.setLeft(deleteNode(root.getLeft(), data));
		}
		return root;
	}

	/**
	 * find max node of the BST
	 * 
	 * @param node
	 * @return root
	 */
	public static TreeNode<Integer> findMax(TreeNode<Integer> node) {
		if (node == null)
			return null;
		while (node.getRight() != null) {
			node = node.getRight();
		}
		return node;
	}

	/**
	 * first find lca of two nodes.
	 * 
	 * find distance from root to node1 and node2 and add them
	 * 
	 * @param root
	 * @param node1
	 * @param node2
	 * @return
	 */
	public static int shortestPath(TreeNode<Integer> root, int node1, int node2) {
		TreeNode<Integer> lca = lowestCommonAncestor(root, node1, node2);
		return findDistanceFromNode(lca, node1) + findDistanceFromNode(lca, node2);
	}

	private static int findDistanceFromNode(TreeNode<Integer> root, int data) {
		if (root == null || data == root.getData()) {
			return 0;
		}
		if (root.getData() > data) {
			return findDistanceFromNode(root.getLeft(), data) + 1;
		}
		return findDistanceFromNode(root.getRight(), data) + 1;
	}

	/**
	 * we follow in order traversal and check if prev node is less than current. if not we
	 * return false. else we repeat process.
	 * 
	 * @param root
	 * @return true if it is a binary search tree
	 */
	public static boolean isBinarySearchTree(TreeNode<Integer> root) {
		return isBST(root, new Model<>(Integer.MIN_VALUE));
	}

	private static boolean isBST(TreeNode<Integer> root, Model<Integer> model) {
		if (root == null) {
			return true;
		}
		if (!isBST(root.getLeft(), model)) {
			return false;
		}
		if (root.getData() < model.getValue()) {
			return false;
		}
		model.setValue(root.getData());
		return isBST(root.getRight(), model);
	}

	public static TreeNode<Integer> getDoublyLinkedList(TreeNode<Integer> root) {
		Model<TreeNode<Integer>> model = new Model<>();
		convertBTtoDLL(root, model);
		return model.getValue();
	}

	private static void convertBTtoDLL(TreeNode<Integer> root, Model<TreeNode<Integer>> prev) {
		if (root == null) {
			return;
		}
		convertBTtoDLL(root.getRight(), prev);
//		TreeNode<Integer> curr=new TreeNode<>(root.getData());
		root.setRight(prev.getValue());
		if (prev.getValue() != null) {
			prev.getValue().setLeft(root);
		}
		prev.setValue(root);
		convertBTtoDLL(root.getLeft(), prev);
	}

	public static void printDLL(TreeNode<Integer> root) {
		System.out.println("Double Linked list data -> ");
		while (root != null) {
			System.out.print(root.getData() + "->");
			root = root.getRight();
		}
		System.out.println();
	}

	public static TreeNode<Integer> getBSTfromDLL(TreeNode<Integer> head) {
		int length = countDLL(head);
		return length > 0 ? getBSTfromDLL(head, 0, length - 1) : null;
	}

	private static TreeNode<Integer> getBSTfromDLL(TreeNode<Integer> head, int start, int end) {
		if (start > end) {
			return null;
		}
		int mid = (start + end) / 2;
		TreeNode<Integer> left = getBSTfromDLL(head, start, mid - 1);
		TreeNode<Integer> node = new TreeNode<>(head.getData());
		node.setLeft(left);
		if (head.getRight() != null) {
			head.setData(head.getRight().getData());
			head.setRight(head.getRight().getRight());
		}
		node.setRight(getBSTfromDLL(head, mid + 1, end));
		return node;
	}

	public static int countDLL(TreeNode<Integer> head) {
		int length = 0;
		while (head != null) {
			length++;
			head = head.getRight();
		}
		return length;
	}

	public static TreeNode<Integer> getBSTfromSLL(Node<Integer> head) {
		int length = LinkedListUtil.size(head);
		return length > 0 ? getBSTfromSLL(head, 0, length - 1) : null;
	}

	private static TreeNode<Integer> getBSTfromSLL(Node<Integer> head, int start, int end) {
		if (start > end) {
			return null;
		}
		int mid = start + (end - start) / 2;
		TreeNode<Integer> left = getBSTfromSLL(head, start, mid - 1);
		TreeNode<Integer> node = new TreeNode<>(head.getData());
		node.setLeft(left);
		if (head.getNext() != null) {
			head.setData(head.getNext().getData());
			head.setNext(head.getNext().getNext());
		}
		node.setRight(getBSTfromSLL(head, mid + 1, end));
		return node;
	}

	public static TreeNode<Integer> findKthSmallestItem(TreeNode<Integer> root, int k) {
		return findKthSmallestItem(root, new Model<Integer>(k));
	}

	private static TreeNode<Integer> findKthSmallestItem(TreeNode<Integer> root, Model<Integer> k) {
		if (root == null) {
			return null;
		}
		TreeNode<Integer> left = findKthSmallestItem(root.getLeft(), k);
		if (left != null) {
			return left;
		}
		if (k.getValue() == 1) {
			return root;
		}
		k.setValue(k.getValue() - 1);
		return findKthSmallestItem(root.getRight(), k);
	}

	public static TreeNode<Integer> ceilNode(TreeNode<Integer> root, int data) {
		if (root == null) {
			return null;
		}
		if (root.getData() == data) {
			return root;
		}
		TreeNode<Integer> left = ceilNode(root.getLeft(), data);
		if (left != null) {
			return left;
		}
		if (root.getData() > data) {
			return root;
		}
		return ceilNode(root.getRight(), data);
	}

	public static TreeNode<Integer> floorNode(TreeNode<Integer> root, int data) {
		return floorNode(root, data, new Model<TreeNode<Integer>>());
	}

	private static TreeNode<Integer> floorNode(TreeNode<Integer> root, int data, Model<TreeNode<Integer>> prev) {
		if (root == null) {
			return null;
		}
		if (root.getData() == data) {
			return root;
		}
		TreeNode<Integer> left = floorNode(root.getLeft(), data, prev);
		if (left != null) {
			return left;
		}
		if (root.getData() > data) {
			return prev.getValue();
		} else {
			prev.setValue(root);
			return floorNode(root.getRight(), data, prev);
		}
	}

	public static boolean sameElements(TreeNode<Integer> root1, TreeNode<Integer> root2) {
		Queue<Integer> list1 = new LinkedList<>();
		inOrderQueue(root1, list1);
		return checkSameElement(root2, list1);
	}

	private static boolean checkSameElement(TreeNode<Integer> root, Queue<Integer> queue) {
		if (root == null)
			return true;
		boolean res = checkSameElement(root.getLeft(), queue);
		if (queue.poll() != root.getData()) {
			return false;
		}
		if (!res) {
			return false;
		}
		return checkSameElement(root.getRight(), queue);
	}

	private static void inOrderQueue(TreeNode<Integer> root1, Queue<Integer> list) {
		if (root1 == null)
			return;
		inOrderQueue(root1.getLeft(), list);
		list.add(root1.getData());
		inOrderQueue(root1.getRight(), list);
	}

	public static void printIntersection(TreeNode<Integer> root1, TreeNode<Integer> root2) {
		Queue<Integer> list = new LinkedList<>();
		System.out.println("Intersection fo two Trees are : ");
		inOrderQueue(root1, list);
		printIntersection(root2, list);
		System.out.println();
	}

	private static void printIntersection(TreeNode<Integer> root2, Queue<Integer> list) {
		if (root2 == null) {
			return;
		}
		printIntersection(root2.getLeft(), list);
		if (list.contains(root2.getData())) {
			System.out.print(root2.getData() + " ");
		}
		printIntersection(root2.getRight(), list);
	}

	public static void printUnion(TreeNode<Integer> root1, TreeNode<Integer> root2) {
		Queue<Integer> list = new LinkedList<>();
		System.out.println("Union fo two Trees are : ");
		printOrderQueue(root1, list);
		printUnion(root2, list);
		System.out.println();
	}

	private static void printUnion(TreeNode<Integer> root2, Queue<Integer> list) {
		if (root2 == null) {
			return;
		}
		printUnion(root2.getLeft(), list);
		if (!list.contains(root2.getData())) {
			System.out.print(root2.getData() + " ");
		}
		printUnion(root2.getRight(), list);
	}

	private static void printOrderQueue(TreeNode<Integer> root1, Queue<Integer> list) {
		if (root1 == null)
			return;
		printOrderQueue(root1.getLeft(), list);
		list.add(root1.getData());
		System.out.print(root1.getData() + " ");
		printOrderQueue(root1.getRight(), list);
	}

	/**
	 * make two balanced bst into a single balanced bst. store inorder of r1 in arr1 store
	 * inorder of 2 in arr2 merge arr1 and arr2 into arr3 convert arr3 in bst.
	 * 
	 * @param root1
	 * @param root2
	 * @return root
	 */
	public static TreeNode<Integer> mergeTwoTrees(TreeNode<Integer> root1, TreeNode<Integer> root2) {
		List<Integer> arr1 = new ArrayList<>();
		List<Integer> arr2 = new ArrayList<>();
		System.out.println("balanced BST after merging two balanced BST : ");
		inOrder(root1, arr1);
		inOrder(root2, arr2);
		List<Integer> arr3 = mergeTwoLists(arr1, arr2);
		return getBstFromInorder(arr3);
	}

	private static TreeNode<Integer> getBstFromInorder(List<Integer> list) {
		return getBstFromInorder(list, 0, list.size() - 1);
	}

	private static TreeNode<Integer> getBstFromInorder(List<Integer> list, int start, int end) {
		if (start > end)
			return null;
		int mid = start + (end - start) / 2;
		TreeNode<Integer> node = new TreeNode<>(list.get(mid));
		node.setLeft(getBstFromInorder(list, start, mid - 1));
		node.setRight(getBstFromInorder(list, mid + 1, end));
		return node;
	}

	private static List<Integer> mergeTwoLists(List<Integer> arr1, List<Integer> arr2) {
		List<Integer> list = new ArrayList<>();
		int i = 0;
		int j = 0;
		while (arr1.size() > i && arr2.size() > j) {
			if (arr1.get(i) < arr2.get(j)) {
				list.add(arr1.get(i));
				i++;
			} else {
				list.add(arr2.get(j));
				j++;
			}
		}
		while (arr1.size() > i) {
			list.add(arr1.get(i));
			i++;
		}
		while (arr2.size() > j) {
			list.add(arr2.get(j));
			j++;
		}
		return list;
	}

	private static void inOrder(TreeNode<Integer> root1, List<Integer> arr) {
		if (root1 == null)
			return;
		inOrder(root1.getLeft(), arr);
		arr.add(root1.getData());
		inOrder(root1.getRight(), arr);
	}

	public static int size(TreeNode<Integer> root) {
		if (root == null) {
			return 0;
		}
		return size(root.getLeft()) + size(root.getRight()) + 1;
	}
	
	public static void printNodesInRange(TreeNode<Integer> root, int min, int max) {
		if (root == null) {
			return;
		}
		if (root.getData() > min) {
			printNodesInRange(root.getLeft(), min, max);
		}
		if (root.getData() >= min && root.getData() <= max) {
			System.out.print(root.getData()+" ");
		}
		if (root.getData() < max) {
			printNodesInRange(root.getRight(), min, max);
		}
	}
	
	/**
	 * Traverse the tree in post order fashion. i.e. before deleting node its left and right tree already corrected.
	 * @param root
	 * @param min
	 * @param max
	 * @return root
	 */
	public static TreeNode<Integer> removeNodeOutsideRange(TreeNode<Integer> root, int min, int max) {
		if(root==null) {
			return null;
		}
		root.setLeft(removeNodeOutsideRange(root.getLeft(), min, max));
		root.setRight(removeNodeOutsideRange(root.getRight(), min, max));
		if(root.getData()<min) {
			return root.getRight();
		}
		if(root.getData()>max) {
			return root.getLeft();
		}
		return root;
	}
	
	/**
	 * to count iterate till node are null.
	 * if node.data == k . then
	 * 
	 * @param node
	 * @param k
	 * @return number of nodes Greater Than K
	 */
	public static int nodesGreaterThanK(TreeNodeSize<Integer> node, int k) {
		int c = 0;
		while (node != null) {
			if (node.getData() == k) {
				c = c + node.getRight().getSize();
				break;
			} else if (node.getData() < k) {
				node = node.getRight();
			} else {
				c = c + node.getRight().getSize();
				node = node.getLeft();
			}
		}
		return c;
	}
	
}

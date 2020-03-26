package com.daa.tree;

import java.util.LinkedList;
import java.util.Queue;

import com.daa.algo.Recursion;
import com.daa.model.Model;

/**
 * Binary Search tree stores the data in a binary tree. every node can have at
 * most 2 child.
 * 
 * The left subtree of a node contains only nodes with keys lesser than the node’s key.
 * The right subtree of a node contains only nodes with keys greater than the node’s key.
 * The left and right subtree each must also be a binary search tree.
 * 
 * @author G521885
 *
 */
public class BinarySearchTree<T extends Comparable<T>> implements Tree<T> {

	private int size;
	private TreeNode<T> root;

	/**
	 * Insert node at left if it is less then new-node else try right. than repeat
	 * the process
	 * 
	 * @param node
	 * @param newNode
	 */
	@Override
	public void insert(T data) {
		TreeNode<T> node = new TreeNode<>(data, null, null);
		if (root == null) {
			root = node;
		} else {
			insert(root, node);
		}
		size++;

	}

	/**
	 * 
	 * 2nCn/(n+1)
	 * 
	 * @return number of possible binary search trees for given n distinct keys
	 */
	public int numberOfBSTposible(int n) {
		Recursion r = new Recursion();
		int num = r.fact(2 * n);
		int den = r.fact(n);
		int res = den * den * (n + 1);
		return num / res;
	}

	/**
	 * Insert data in o(logn) time
	 * 
	 * It will take o(logn) time if data is inserted in a balanced way else in worst
	 * case it can be o(n) if data inserted in tree is in sorted order. e.g. 1,2,3,4
	 * 1->2->3->4 now if we insert 5 again. it need to search 4 nodes to goto 5th
	 * node
	 * 
	 * @param curNode
	 * @param node
	 */
	private void insert(TreeNode<T> curNode, TreeNode<T> node) {

		if (node.getData().compareTo(curNode.getData()) < 0) {
			if (curNode.getLeft() == null) {
				curNode.setLeft(node);
			} else {
				insert(curNode.getLeft(), node);
			}
		} else {
			if (curNode.getRight() == null) {
				curNode.setRight(node);
			} else {
				insert(curNode.getRight(), node);
			}
		}
	}

	/**
	 * @return the size
	 */
	@Override
	public int size() {
		return size;
	}

	@Override
	public String preOrderTraversal() {
		StringBuilder sb = new StringBuilder();
		preOrderTraversal(root, sb);
		return sb.toString();
	}

	/**
	 * PreOrder (Root, Left, Right)
	 * @f:off
	 *   1 
	 *  / \ 
	 *  2  3 
	 * / \ 
	 *4  5
	 * @f:on
	 * output - 12453
	 * 
	 * @return result
	 * 
	 */
	private void preOrderTraversal(TreeNode<T> node, StringBuilder sb) {
		if (node == null) {
			return ;
		}
		sb.append(node.getData()).append(" ");
		preOrderTraversal(node.getLeft(), sb);
		preOrderTraversal(node.getRight(), sb);
	}
	

	/**
	 * Find the Minimum element of BST. just go to farthest left child and that
	 * would be minimum
	 * 
	 * @return min element of BST
	 */
	public T findMinimum() {
		if (root == null) {
			return null;
		}
		return findMin(root).getData();
	}

	private TreeNode<T> findMin(TreeNode<T> node) {
		if (node.getLeft() == null) {
			return node;
		}
		return findMin(node.getLeft());
	}

	/**
	 * Calculate the number of nodes in binary search tree. O(N)
	 * 
	 * @return
	 */
	public int numberOfNodes() {
		return numberOfNodes(root);
	}

	private int numberOfNodes(TreeNode<T> node) {
		if (node != null) {
			int l = numberOfNodes(node.getLeft());
			int r = numberOfNodes(node.getRight());
			return l + r + 1;
		}
		return 0;
	}

	/**
	 * Find the Maximum element of BST. just go to farthest right child and that
	 * would be max
	 * 
	 * @return max element of BST
	 */
	public T findMaximum() {
		if (root == null) {
			return null;
		}
		return findMax(root).getData();
	}

	private TreeNode<T> findMax(TreeNode<T> node) {
		if (node.getRight() == null) {
			return node;
		}
		return findMax(node.getRight());
	}

	/**
	 * In order (Left, Root, Right)
	 * 
	 * 1 / \ 2 3 / \ 4 5
	 * 
	 * In order of binary search tree is sorted data of all the elements in the
	 * tree.
	 * 
	 * output - 42513
	 * 
	 * @return result
	 */
	@Override
	public String inOrderTraversal() {
		StringBuilder sb = new StringBuilder();
		inOrderTraversal(root, sb);
		return sb.toString();
	}

	private void inOrderTraversal(TreeNode<T> node, StringBuilder sb) {
		if (node == null) {
			return ;
		}
		inOrderTraversal(node.getLeft(), sb);
		sb.append(node.getData()).append(" ");
		inOrderTraversal(node.getRight(), sb);
	}

	
	@Override
	public String postOrderTraversal() {
		StringBuilder sb = new StringBuilder();
		postOrderTraversal(root, sb);
		return sb.toString();
	}

	/**
	 * postOrder (Left, Right, Root )
	 * 
	 * 1 / \ 2 3 / \ 4 5
	 * 
	 * output - 45231
	 * 
	 * @return result
	 */
	private void postOrderTraversal(TreeNode<T> node, StringBuilder sb) {
		if (node == null) {
			return ;
		}
		postOrderTraversal(node.getLeft(), sb);
		postOrderTraversal(node.getRight(), sb);
		sb.append(node.getData()).append(" ");
	}

	/**
	 * Find the element from the binary search tree. it will take o(logn) time if
	 * data is inserted in a balanced way else in worst case it can be o(n) if data
	 * inserted in tree is in sorted order. e.g. 1,2,3,4 1->2->3->4
	 */
	@Override
	public boolean findElement(T data) {
		if (root == null) {
			return false;
		}
		return search(root, data) != null;
	}

	private TreeNode<T> search(TreeNode<T> node, T data) {
		if (node == null || data.compareTo(node.getData()) == 0) {
			return node;
		} else if (data.compareTo(node.getData()) < 0) {
			return search(node.getLeft(), data);
		}
		return search(node.getRight(), data);
	}

	public void deleteNode(T data) {
		root = removeNode(root, data);
	}

	/**
	 * Delete Node from BST
	 * 
	 * O(N) if tree is not balanced and data is entered in ascending/descending
	 * order else O(logn)
	 * 
	 * @param node
	 * @param data to be searched
	 * @return Root node
	 */
	private TreeNode<T> removeNode(TreeNode<T> node, T data) {
		if (node == null) {
			return null;
		}

		if (data.compareTo(node.getData()) > 0) {
			node.setRight(removeNode(node.getRight(), data));
		} else if (data.compareTo(node.getData()) < 0) {
			node.setLeft(removeNode(node.getLeft(), data));
		} else {
			if (node.getLeft() == null && node.getRight() == null) {
				System.out.println("removing leaf node :" + node.getData());
				return null;
			}
			if (node.getLeft() == null) {
				System.out.println("removing node  with right child:" + node.getData());
				return node.getRight();
			}
			if (node.getRight() == null) {
				System.out.println("removing node  with left child: " + node.getData());
				return node.getLeft();
			}

			// both node has child
			System.out.println("removing node with both child : " + node.getData());
			TreeNode<T> max = findMax(node.getLeft());
			node.setData(max.getData());
			node.setLeft(removeNode(node.getLeft(), node.getData()));

		}
		return node;

	}
	
	/**
	 * Remove nodes from BST if it is outside the given range. the result should itself be BST
	 * 
	 * @param min
	 * @param max
	 */
	public void removeNodeOutsideRange(T min,T max) {
		root=removeNodeOutsideRange(root ,min, max);
	}

	/**
	 * Remove node outside the range
	 * 
	 * @param node
	 * @param min
	 * @param max
	 */
	private TreeNode<T> removeNodeOutsideRange(TreeNode<T> node, T min, T max) {
		if(node==null) {
			return node;
		}
		node.setLeft(removeNodeOutsideRange(node.getLeft(), min, max));
		node.setRight(removeNodeOutsideRange(node.getRight(), min, max));
		if(node.getData().compareTo(min)<0) {
			return node.getRight();
		} else if(node.getData().compareTo(max)>0) {
			return node.getLeft();
		}
		return node;
	}

	public boolean compareTree(BinarySearchTree<T> tree2) {
		return compareTree(this.root, tree2.root);
	}

	private boolean compareTree(TreeNode<T> node1, TreeNode<T> node2) {
		if (node1 == null || node2 == null) {
			return node1 == node2;
		}
		if (node1.getData().compareTo(node2.getData()) != 0) {
//			System.out.println("Tree fields are not equal : "+node1.getData()+","+node2.getData());
			return false;
		}
		boolean res = compareTree(node1.getLeft(), node2.getLeft());
		if (res) {
			res = compareTree(node1.getRight(), node2.getRight());
		}
//		else {
//			System.out.println("left Tree are not equal");
//		}
		return res;
	}

	/**
	 * get the sum of all the nodes in bianry search tree.
	 * 
	 * @return sum of nodes
	 */
	public int sumOfBinarySearchTree() {
		return sumOfBinarySearchTree(root);
	}

	private int sumOfBinarySearchTree(TreeNode<T> node) {
		if(node==null) {
			return 0;
		}
		int l = sumOfBinarySearchTree(node.getLeft());
		int r = sumOfBinarySearchTree(node.getRight());
		
		return l+r+(Integer)node.getData();
	}
	
	public T findKthSmallestItem(int k) {
		TreeNode<T> node = findKthSmallestItem(root, new Model<Integer>(k));
		return node == null ? null : node.getData();
	}
	
	private TreeNode<T> findKthSmallestItem(TreeNode<T> root, Model<Integer> k) {
		if (root == null) {
			return null;
		}
		TreeNode<T> left = findKthSmallestItem(root.getLeft(), k);
		if (left != null) {
			return left;
		}
		if (k.getValue() == 1) {
			return root;
		}
		k.setValue(k.getValue() - 1);
		return findKthSmallestItem(root.getRight(), k);
	}
	
	public boolean isComplete() {
		return isComplete(root);
	}

	private boolean isComplete(TreeNode<T> node) {
		if(node==null || (node.getLeft()==null && node.getRight()==null)) {
			return true;
		}
		if(node.getLeft()!=null && node.getRight()!=null) {
			return isComplete(node.getLeft()) && isComplete(node.getRight());			
		}
		return false;
		
	}
	
	/**
	 * O(N) time complexity and O(N) space for queue
	 * 
	 * @return Level order string
	 */
	public String levelOrderTraversal() {
		if(root==null) {
			return "";
		} 
		Queue<TreeNode<T>> queue= new LinkedList<>();
		StringBuilder sb = new StringBuilder();
		queue.add(root);
		while(!queue.isEmpty()) {
			TreeNode<T> temp = queue.poll();
			sb.append(temp.getData()).append(" ");
			if(temp.getLeft()!=null) {
				queue.add(temp.getLeft());
			} 
			if(temp.getRight()!=null) {
				queue.add(temp.getRight());
			}
		}
		return sb.toString().trim();
	}
	
	/**
	 * check whether there is a node on which no child can be added. like if a leaf node has 1 value it is dead-end
	 * it is possible if for any leaf node with value i. tree already have i+1 and i-1 value nodes
	 * 
	 * @return true if dead-end exists
	 */
	public boolean isDeadEnd(TreeNode<Integer> head) {
		return checkDeadEnd(head,1,Integer.MAX_VALUE);
	}
	
	private boolean checkDeadEnd(TreeNode<Integer> node,int min,int max) {
		if (node==null) {
			return false;
		}
		if(min==max) {
			return true;
		}
		return checkDeadEnd(node.getLeft(), min, node.getData()-1)||checkDeadEnd(node.getRight(), node.getData()+1, max);
	}
	
	/**
	 * changeKey() changes old key value present in the tree and changes it to new key value.
	 * 
	 * @param oldKey
	 * @param newKey
	 */
	public void changeKey(T oldKey, T newKey) {
		root = removeNode(root, oldKey);
		insert(newKey);
	}
	
	/**
	 * @return the root
	 */
	public TreeNode<T> getRoot() {
		return root;
	}
	
}

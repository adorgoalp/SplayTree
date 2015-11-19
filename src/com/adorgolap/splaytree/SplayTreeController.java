package com.adorgolap.splaytree;

import com.adorgolap.splaytree.lib.SplayTreePrinter;

public class SplayTreeController {
	SplayNode root = null;

	public SplayTreeController() {
	}

	public void print() {
		if (root != null) {
			SplayTreePrinter.printNode(root);
		} else {
			System.out.println("Empty tree. ");
		}
	}
	public void splayInsert(int key) {
		if(root == null)
		{
			root = new SplayNode(key);
			return;
		}
		SplayNode leftSubTree,rightSubTree;
		splay(key);
		if(root.key >= key)
		{
			leftSubTree = root.left;
			rightSubTree = root;
			if(rightSubTree.left != null)
			{
				rightSubTree.left = null;
			}
		}else{
			leftSubTree = root;
			rightSubTree = root.right;
			if(leftSubTree.right != null)
			{
				leftSubTree.right = null;
			}
		}
		SplayNode node = new SplayNode(key,null);
		node.left = leftSubTree;
		if(leftSubTree!=null)
		{
			leftSubTree.parent = node;
		}
		node.right = rightSubTree;
		if(rightSubTree != null)
		{
			rightSubTree.parent = node;
		}
		root = node;
	}
	public void splayDelete(int key) {
		if(root == null)
		{
			System.out.println("Empty tree");
			return;
		}
		splay(key);
		if(root.key == key)
		{
			SplayNode leftSubTree,rightSubTree;
			leftSubTree = root.left;
			rightSubTree = root.right;
			if(leftSubTree != null)
			{
				root = leftSubTree;
				splay(Integer.MAX_VALUE);
				root.right = rightSubTree;
				rightSubTree.parent = root;
			}else
			{
				if(root.right != null)
				{
					root = root.right;
					root.parent = null;
				}else {
					root = null;
				}
			}
		}else
		{
			System.out.println("key not found");
		}
	}
	public void insert(int key) {
		if (root == null) {
			root = new SplayNode(key, null);
		} else {
			insertHelper(root, key);
		}
	}

	private void insertHelper(SplayNode entryPoint, int key) {
		if (key >= entryPoint.key) {
			if (entryPoint.right == null) {
				SplayNode node = new SplayNode(key);
				entryPoint.right = node;
				node.parent = entryPoint;
			} else {
				insertHelper(entryPoint.right, key);
			}
		} else {
			if (entryPoint.left == null) {
				SplayNode node = new SplayNode(key);
				entryPoint.left = node;
				node.parent = entryPoint;
			} else {
				insertHelper(entryPoint.left, key);
			}
		}
	}

	public void delete(int key) {
		SplayNode nodeTodelete = search(key);
		System.out.println(nodeTodelete);
		if (nodeTodelete == null) {
			System.out.println("Node does not exist.");
			return;
		}
		if (nodeTodelete.isLeaf()) {
			if (nodeTodelete.equals(root)) {
				root = null;
				return;
			}
			if (nodeTodelete.parent.left != null && nodeTodelete.parent.left.key == nodeTodelete.key) {
				nodeTodelete.parent.left = null;
			} else {
				nodeTodelete.parent.right = null;
			}
		} else if (nodeTodelete.hasOnlyLeftChild()) {
			if (nodeTodelete.equals(root)) {
				root = nodeTodelete.left;
				nodeTodelete.left.parent = null;
				return;
			}
			if (nodeTodelete.parent.left != null && nodeTodelete.parent.left.key == nodeTodelete.key) {
				nodeTodelete.parent.left = nodeTodelete.left;
			} else {
				nodeTodelete.parent.right = nodeTodelete.left;
			}
			nodeTodelete.left.parent = nodeTodelete.parent;
		} else if (nodeTodelete.hasOnlyRightChild()) {
			if (nodeTodelete.equals(root)) {
				root = nodeTodelete.right;
				nodeTodelete.right.parent = null;
				return;
			}
			if (nodeTodelete.parent.left != null && nodeTodelete.parent.left.key == nodeTodelete.key) {
				nodeTodelete.parent.left = nodeTodelete.right;
			} else {
				nodeTodelete.parent.right = nodeTodelete.right;
			}
			nodeTodelete.right.parent = nodeTodelete.parent;
		} else if (nodeTodelete.hasBothChild()) {
			SplayNode minInRightSubtree = getMinNodeInRightSubtree(nodeTodelete);
			System.out.println(minInRightSubtree);
			if (minInRightSubtree.right != null) {
				if (minInRightSubtree.parent.right != null
						&& minInRightSubtree.parent.right.key == minInRightSubtree.key) {
					minInRightSubtree.parent.right = minInRightSubtree.right;
					minInRightSubtree.right.parent = minInRightSubtree.parent;
				} else {
					minInRightSubtree.parent.left = minInRightSubtree.right;
					minInRightSubtree.right.parent = minInRightSubtree.parent;
				}
			}
			nodeTodelete.key = minInRightSubtree.key;
			if (nodeTodelete.right.key == minInRightSubtree.key) {
				nodeTodelete.right = null;
			} else {
				if (minInRightSubtree.parent.left.key == minInRightSubtree.key) {
					minInRightSubtree.parent.left = null;
				}
			}
		}
	}

	private SplayNode getMinNodeInRightSubtree(SplayNode nodeTodelete) {
		SplayNode temp = nodeTodelete.right;
		while (true) {
			if (temp.left != null) {
				temp = temp.left;
			} else {
				return temp;
			}
		}
		// return null;
	}

	public void splay(int key) {
		splay(search(key));
	}

	public void splay(SplayNode n) {
		if (isRoot(n)) {
//			System.out.println("It is root");
			return;
		} else if (isChildOfRoot(n)) {
			SplayNode r = n.parent;
			zig(n, r);
			splay(n);
		} else {
//			System.out.println("It is in deep");
			SplayNode p = n.parent;
			SplayNode g = p.parent;
			if(g.left != null && g.left.equals(p))
			{
				if(p.left!=null && p.left.equals(n))
				{
					singleRightRotation(n, p);
					singleRightRotation(n, g);
				}else
				{
					singleLeftRotation(n, p);
					singleRightRotation(n, g);
				}
			}else {
				if(p.left!=null && p.left.equals(n))
				{
					singleRightRotation(n, p);
					singleLeftRotation(n, g);
				}else
				{
					singleLeftRotation(n, p);
					singleLeftRotation(n, g);
				}
			}
			splay(n);
			
		}
	}

	private void zigZig(SplayNode g, SplayNode p, SplayNode n) {
		zig(n, p);
		zig(n, g);
	}

	private void zigZag(SplayNode g, SplayNode p, SplayNode n) {
		zig(n, p);
		zig(n, g);
	}

	private void zig(SplayNode n, SplayNode r) {
//		System.out.println("n " + n + " r " + r);
		if (r.left != null && r.left.equals(n)) {
			singleRightRotation(n, r);
//			System.out.println("after rr");
//			SplayTreePrinter.printNode(root);
		} else {
			singleLeftRotation(n, r);
//			System.out.println("after lr");
//			SplayTreePrinter.printNode(root);
		}
	}

	private void singleLeftRotation(SplayNode n, SplayNode r) {
		if(r.parent!=null)
		{
			if(r.parent.left!= null && r.parent.left.key == r.key)
			{
				r.parent.left = n;
			}else
			{
				r.parent.right = n;
			}
			n.parent = r.parent;
		}else
		{
			n.parent = null;
			root = n;
		}
		r.right = n.left;
		if(n.left != null)
		{
			n.left.parent = r;
		}
		n.left = r;
		r.parent = n;
	}

	private void singleRightRotation(SplayNode n, SplayNode r) {
		if(r.parent!=null)
		{
			if(r.parent.left!= null && r.parent.left.key == r.key)
			{
				r.parent.left = n;
			}else
			{
				r.parent.right = n;
			}
			n.parent = r.parent;
		}else
		{
			root = n;
			n.parent = null;
		}
		r.left = n.right;
		if(n.right != null)
		{
			n.right.parent = r;
		}
		n.right = r;
		r.parent = n;
	}

	private boolean isChildOfRoot(SplayNode node) {
		if (node.parent.key == root.key)
			return true;
		return false;
	}

	private boolean isRoot(SplayNode node) {
		if (root.key == node.key || node.parent == null) {
			return true;
		}
		return false;
	}

	public SplayNode search(int key) {
		SplayNode result;
		if (root == null) {
			return null;
		}
		if (root.key == key) {
			result = root;
		} else {
			result = searchHelper(root, key);
		}
		// SplayTreePrinter.printNode(result);
		splay(result);
		return result;
	}

	private SplayNode searchHelper(SplayNode searchingPoint, int key) {
		if (searchingPoint.key == key) {
			return searchingPoint;
		}
		if (key >= searchingPoint.key && searchingPoint.right != null) {
			return searchHelper(searchingPoint.right, key);
		} else if (key < searchingPoint.key && searchingPoint.left != null) {
			return searchHelper(searchingPoint.left, key);
		}
		return searchingPoint;
	}
}

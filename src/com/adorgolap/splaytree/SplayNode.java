package com.adorgolap.splaytree;

public class SplayNode implements Comparable<SplayNode>{
	public int key;
	public SplayNode left;
	public SplayNode right;
	public SplayNode parent;
	public SplayNode(int key) {
		this.key = key;
	} 
	public SplayNode(int key, SplayNode parent) {
		this.key = key;
		this.parent = parent;
	} 
	public void makeRoot() {
		parent = null;
	}

	@Override
	public int compareTo(SplayNode o) {
		// TODO Auto-generated method stub
		return this.key-o.key;
	}
	@Override
	public String toString() {
		String lv,rv,pv;
		if(left == null)
		{
			lv = "NUL";
		}else
		{
			lv = left.key+"";
		}
		if(right == null)
		{
			rv = "NUL";
		}else
		{
			rv = right.key+"";
		}
		if(parent == null)
		{
			pv = "NUL";
		}else{
			pv = parent.key+"";
		}
		return key + " p "+pv+ " lc " +lv+ " rc "+rv ;
	}
	public boolean isLeaf() {

		return (left!=null || right!=null) ? false : true;
	}
	public boolean hasOnlyLeftChild() {
		if(right == null && left != null)
			return true;
		return false;
	}
	public boolean hasOnlyRightChild() {
		if(right != null && left == null)
			return true;
		return false;
	}
	public boolean hasBothChild() {
		return (right != null && left != null)?true:false;
	}
	public boolean isRoot() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean equals(Object obj) {
		SplayNode n = (SplayNode) obj;
		return this.key == n.key ? true :false;
	}
	
}

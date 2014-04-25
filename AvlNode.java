
public class AvlNode {
	AvlNode left;
	AvlNode right;
	AvlNode parent;
	int height; //height of current node defined by max(left,right) + 1
	int balanceFactor; //height(right subtree) - height(left subtree)
	int value;
	
	public AvlNode(int value) {
		left = null;
		right = null;
		parent = null;
		height = 0;
		balanceFactor = 0;
		this.value = value;
	}
	
	public void setLeft(AvlNode left) {
		this.left = left;
	}
	
	public void setRight(AvlNode right) {
		this.right = right;
	}
	
	public AvlNode getLeft() {
		return left;
	}
	
	public AvlNode getRight() {
		return right;
	}
	
	public int getValue() {
		return value;
	}
	
	public AvlNode getParent() {
		return parent;
	}
	
	public void setParent(AvlNode par) {
		parent = par;
	}
	
	public void setBalanceFactor(int bf) {
		balanceFactor = bf;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public int getHeight() {
		return height;
	}
	

}

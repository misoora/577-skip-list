import java.util.*;

public class QuadNode {
	private QuadNode up, down, left, right;
	private int data;
	private boolean isInfinity; //determines if this node is a positive or negative infinity node (edge node)
	private boolean isLocked; //determines whether the node is in use and shouldn't be accessed by other threads
	
	QuadNode(int _data, boolean infNode) {
		data = _data;
		isInfinity = infNode;
		up = down = left = right = null;
		isLocked = false;
	}
	
	public boolean isInfinity() {
		return isInfinity;
	}
	
	public boolean isLocked() {
		return isLocked();
	}
	
	public void setLock() {
		isLocked = true;
	}
	
	public void unLock() {
		isLocked = false;
	}
	
	public void setData(int _data) {
		data = _data;
	}
	
	public int getData() {
		return data;
	}
	
	public void setUp(QuadNode _up) {
		up = _up;
	}
	
	public QuadNode getUp() {
		return up;
	}
	
	public void setDown(QuadNode _down) {
		down = _down;
	}
	
	public QuadNode getDown() {
		return down;
	}
	
	public void setLeft(QuadNode _Left) {
		left = _Left;
	}
	
	public QuadNode getLeft() {
		return left;
	}
	public void setRight(QuadNode _right) {
		right = _right;
	}
	
	public QuadNode getRight() {
		return right;
	}
}

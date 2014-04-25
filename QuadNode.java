import java.util.*;

public class QuadNode {
	private QuadNode up, down, left, right;
	private int data;
	private boolean isInfinity; //determines if this node is a positive or negative infinity node (edge node)
	
	QuadNode(int _data, boolean infNode) {
		data = _data;
		isInfinity = infNode;
		up = down = left = right = null;
	}
	
	public boolean isInfinity() {
		return isInfinity;
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

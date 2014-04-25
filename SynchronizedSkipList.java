import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;


public class SynchronizedSkipList {
	private final int MAX_HEIGHT = 20; //max height that the skip list can be
	private int currHeight; //returns current height of tree
	private int width; //width of list denoted by number of nodes in bottom list.
	HashMap<Integer, Integer> bottomList;//Used to hold all distinct values in SkipList (value => amount)
	private QuadNode head, rightHead, currTopHead;
	
	public SynchronizedSkipList() {
		currHeight = 1; //Height of list ignoring empty infinity lines.
		width = 2; //initially there are two infinity nodes in bottom list
		bottomList = new HashMap<Integer, Integer>(); 
		head = new QuadNode(Integer.MIN_VALUE, true);
		rightHead = new QuadNode(Integer.MAX_VALUE, true); //Represents the start of the +infinity nodes
		QuadNode left, right, leftBelow, rightBelow; //Used to set up the original (2 * MAX_HEIGHT) nodes. (half -Infinity, half +infinity)
		left = head; //pointer starting at left head
		right = rightHead; //pointer starting at right head
		for (int i=0; i < MAX_HEIGHT; i++) {
			left.setRight(right);
			right.setLeft(left);
			
			leftBelow = new QuadNode(Integer.MIN_VALUE, true);
			rightBelow = new QuadNode(Integer.MAX_VALUE, true);
			
			if (i < (MAX_HEIGHT-1)) { //Bottom list doesn't have pointers going downwards
				leftBelow.setUp(left);
				rightBelow.setUp(right);
				
				left.setDown(leftBelow);
				right.setDown(rightBelow);
				left = leftBelow;
				right = rightBelow;
			}	
		}
		currTopHead = left.getUp(); //The highest level that is currently being used
	}
	
	public synchronized void insert(int val) { 
		Random rand = new Random();
		int numHeads = 0;
		int  randNum = rand.nextInt(2) + 1; //Let 1 equal heads, and 2 equals tails
		while (randNum == 1 && (numHeads + 1 < MAX_HEIGHT)) {
			numHeads++;
			randNum = rand.nextInt(2) + 1;
		}

		QuadNode curr = search(val, true);
		if (curr.getData() == val) {
			//Value already exists in SkipList
			return;
		} else {
			if (numHeads == 0) {
				QuadNode newNode = new QuadNode(val, false);
				newNode.setLeft(curr);
				newNode.setRight(curr.getRight());
				curr.getRight().setLeft(newNode);
				curr.setRight(newNode);
			} else {
				//insert new node into each level recursively upwards
				//recursivelyAddNodes()
				recursivelyAddNodes(curr, null, 0, numHeads, val);
			}
			width++; //new node added so the list will expand horizontally
			getBottomList().put(val, numHeads+1);
		}

	}

	/* This function will recursively add Nodes from the bottom level of the skiplist
	 * and proceed upwards for amount of heads flipped.
	 * 
	 * */
	private synchronized QuadNode recursivelyAddNodes(QuadNode curr, QuadNode currNewNode, int level, int numHeads, int val) {
		QuadNode newNode = new QuadNode(val, false);
		if (level == 0) { //first node to be added
			newNode.setLeft(curr);
			newNode.setRight(curr.getRight());
			curr.getRight().setLeft(newNode);
			curr.setRight(newNode);
			level++;
			while (curr.getUp() == null)
				curr = curr.getLeft(); //backtrack upwards to add node to next level
			curr = curr.getUp();
			QuadNode nodeAbove = recursivelyAddNodes(curr, newNode, level, numHeads, val);
			newNode.setUp(nodeAbove);
		} else if (level == numHeads) { //last node to be added
			newNode.setLeft(curr);
			newNode.setRight(curr.getRight());
			curr.getRight().setLeft(newNode);
			curr.setRight(newNode);
			newNode.setDown(currNewNode);
			//Don't call recursive method again because we're done adding nodes
			if (currHeight < (numHeads + 1)) { //update the current height of the skipList if necessary
				currHeight = (numHeads + 1);
				currTopHead = curr; //update new head pointer
			}
		} else { //middle nodes that will have pointers below and above
			newNode.setLeft(curr);
			newNode.setRight(curr.getRight());
			curr.getRight().setLeft(newNode);
			curr.setRight(newNode);
			newNode.setDown(currNewNode);
			level++;
			while (curr.getUp() == null)
				curr = curr.getLeft(); //backtrack upwards to add node to next level
			curr = curr.getUp();
			QuadNode nodeAbove = recursivelyAddNodes(curr, newNode, level, numHeads, val);
			newNode.setUp(nodeAbove);
		}
		return newNode;
	}
	
	public int getCurrHeight() {
		return currHeight; //current height of skipList (NOT max height list can be)
	}
	
	public synchronized QuadNode getHead() { 
		return currTopHead;
	}
	
	private synchronized HashMap<Integer, Integer> getBottomList() {
		return bottomList;
	}
	
	public boolean contains(int val) {
		if (search(val,false) != null)
			return true;
		else
			return false;
	}
	
	/* val - the value that we are looking up in our search
	 * insertion - flag that denotes if the sender is using the find() for an insertion
	 * 			   If so, we will return the QuadNode directly left of where we want to insert
	 * 			   (instead of returning null as we do for contains()
	 *  */
	private QuadNode search(int val, boolean isInsertion) {
		QuadNode curr = getHead();
		//QuadNode curr = head;

		while (curr.getDown() != null) { //traverse downwards
			curr = curr.getDown();
			while (curr.getRight().getData() <= val && !curr.getRight().isInfinity()) { //traverse right
				curr = curr.getRight();
				if (curr.getData() == val)
					return curr;
			}
		}
		if (isInsertion == true) {
			return curr; //found the node
		}
		return null; //Couldn't find desired value so just return null
	}
	
	/*
	 * This function will return the number of affected nodes in order to find a desired
	 * value. The purpose of this function is to assess how many nodes we have to search
	 * through to find a value
	 */
	public int numberOfScanForwardsToFindVal(int val) {

		QuadNode curr = currTopHead;
		int scanForwards = 0;

		while (curr.getDown() != null) { //traverse downwards
			curr = curr.getDown();
			while (curr.getRight().getData() <= val && !curr.getRight().isInfinity()) { //traverse right
				scanForwards++;
				curr = curr.getRight();
				if (curr.getData() == val)
					return scanForwards;
			}
		}
		return -1; //Value isn't in skip list
	}
	
	public int numberOfDropdownsToFindVal(int val) {

		QuadNode curr = currTopHead;
		int numDropdowns = 0;

		while (curr.getDown() != null) { //traverse downwards
			curr = curr.getDown();
			numDropdowns++;
			while (curr.getRight().getData() <= val && !curr.getRight().isInfinity()) { //traverse right
				curr = curr.getRight();
				if (curr.getData() == val)
					return numDropdowns;
			}
		}
		return -1; //Value isn't in skip list
	}
	
	public void displayListWithoutSpacing() {
		QuadNode headPointer = currTopHead;
		QuadNode curr = currTopHead;
		while (curr != null) { //The loop traversing downwards
			while (curr != null) { //The loop traversing to the right
				if (curr.isInfinity())
					System.out.print("Inf\t");
				else
					System.out.print(curr.getData() + "\t");
				curr = curr.getRight();
			}
			System.out.println();
			headPointer = headPointer.getDown();
			curr = headPointer;
		}
	}
	
	public void displayList() {
		QuadNode currBottomLeft = getHead();
		while (currBottomLeft.getDown() != null) {
			currBottomLeft = currBottomLeft.getDown(); //find the node in the bottom left corner of list
		}
		QuadNode curr = currBottomLeft;
		//go through bottomList and display at each level of skipList
		for (int i=currHeight; i>0; i--) {
			System.out.print("-Inf\t");
			while(curr.getRight() != null && !curr.getRight().isInfinity()){
				curr = curr.getRight();
				int value = curr.getData();
				int numValue = getBottomList().get(value);
				if (numValue >= i) {
					System.out.print(value + "\t");
				} else {
					System.out.print(" \t");
				}
			}
			System.out.println("+Inf");
			curr = currBottomLeft;
		}
		
	}
	

}
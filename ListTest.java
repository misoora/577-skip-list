import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;


public class ListTest {
	static SkipList list;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		setUpIncreasing(1000);
		//testScanFordwardsForRetrieval(1000);
		//testDropdownsForRetrieval(1000);
		printList();
		
		
	}
	
	/*
	 * Create a skiplist and insert random numbers into it for the amount (numElements). Duplicates
	 * will be ignored
	 */
	public static void setUpRandom(int numElements) {
		list = new SkipList();
		Random rand = new Random();
		int randNum = rand.nextInt(numElements*100);
		for (int i=0; i < numElements; i++) {
			list.insert(randNum);
			randNum = rand.nextInt(numElements*10);
		}
	}
	
	public static void setUpIncreasing(int numElements) {
		list = new SkipList();
		for (int i=0; i < numElements; i++) {
			list.insert(i);
		}
	}
	
	public void setUpDecreasing(int numElements) {
		list = new SkipList();
		for (int i=numElements; i > 0; i--) {
			list.insert(i);
		}
	}
	
	public static void printList() {
		list.displayList();
	}
	
	public static void testScanFordwardsForRetrieval(int numElements) {
		for (int i=0; i< numElements; i++) {
			System.out.println(list.numberOfScanForwardsToFindVal(i));
		}
	}
	
	public static void testDropdownsForRetrieval(int numElements) {
		for (int i=0; i< numElements; i++) {
			System.out.println(list.numberOfDropdownsToFindVal(i));
		}
	}

}

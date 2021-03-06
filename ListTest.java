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
		//testNumItemsInList(1000);
		setUpIncreasing(1000);
		//testScanFordwardsForRetrieval(1000);
		//testDropdownsForRetrieval(1000);		
		printList();
		
		
	}
	
	public static void testInsertionTime() {
		double thousand = 1000;
		long time1 = System.currentTimeMillis();
		setUpIncreasing(10);
		long time2 = System.currentTimeMillis();
		System.out.println("Inserting 10 elements took: " + ((time2 - time1) / thousand) + " seconds");
		
		time1 = System.currentTimeMillis();
		setUpIncreasing(100);
		time2 = System.currentTimeMillis();
		System.out.println("Inserting 100 elements took: " + ((time2 - time1) / thousand) + " seconds");
		
		time1 = System.currentTimeMillis();
		setUpIncreasing(1000);
		time2 = System.currentTimeMillis();
		System.out.println("Inserting 1,000 elements took: " + ((time2 - time1) / thousand) + " seconds");
		
		time1 = System.currentTimeMillis();
		setUpIncreasing(10000);
		time2 = System.currentTimeMillis();
		System.out.println("Inserting 10,000 elements took: " + ((time2 - time1) / thousand) + " seconds");
		
		time1 = System.currentTimeMillis();
		setUpIncreasing(100000);
		time2 = System.currentTimeMillis();
		System.out.println("Inserting 100,000 elements took: " + ((time2 - time1) / thousand) + " seconds");
		
		time1 = System.currentTimeMillis();
		setUpIncreasing(1000000);
		time2 = System.currentTimeMillis();
		System.out.println("Inserting 1,000,000 elements took: " + ((time2 - time1) / thousand) + " seconds");
		
		time1 = System.currentTimeMillis();
		setUpIncreasing(10000000);
		time2 = System.currentTimeMillis();
		System.out.println("Inserting 10,000,000 elements took: " + ((time2 - time1) / thousand) + " seconds");
	}
	
	public static void testLookUpTime() {
		double thousand = 1000;
		setUpIncreasing(10000000);
		long time1 = System.currentTimeMillis();
		testContains(10);
		long time2 = System.currentTimeMillis();
		System.out.println("Looking up first 10 elements took: " + ((time2 - time1) / thousand) + " seconds");
		
		time1 = System.currentTimeMillis();
		testContains(100);
		time2 = System.currentTimeMillis();
		System.out.println("Looking up first 100 elements took: " + ((time2 - time1) / thousand) + " seconds");
		
		time1 = System.currentTimeMillis();
		testContains(1000);
		time2 = System.currentTimeMillis();
		System.out.println("Looking up first 1,000 elements took: " + ((time2 - time1) / thousand) + " seconds");
		
		time1 = System.currentTimeMillis();
		testContains(10000);
		time2 = System.currentTimeMillis();
		System.out.println("Looking up first 10,000 elements took: " + ((time2 - time1) / thousand) + " seconds");
		
		time1 = System.currentTimeMillis();
		testContains(100000);
		time2 = System.currentTimeMillis();
		System.out.println("Looking up first 100,000 elements took: " + ((time2 - time1) / thousand) + " seconds");
		
		time1 = System.currentTimeMillis();
		testContains(1000000);
		time2 = System.currentTimeMillis();
		System.out.println("Looking up first 1,000,000 elements took: " + ((time2 - time1) / thousand) + " seconds");
		
		time1 = System.currentTimeMillis();
		testContains(10000000);
		time2 = System.currentTimeMillis();
		System.out.println("Looking up first 10,000,000 elements took: " + ((time2 - time1) / thousand) + " seconds");
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
	
	public static void testContains(int numElements) {
		for (int i=0; i<numElements; i++) {
			list.contains(i);
		}
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
	
	public static void testNumItemsInList(int numItems) {
		setUpIncreasing(numItems);
		QuadNode currDown = list.getHead();
		QuadNode currRight = list.getHead();
		int[] lists = new int[list.getCurrHeight()];
		int currList = lists.length-1;
		while (currDown != null) {
			while (!currRight.getRight().isInfinity()) {
				currRight = currRight.getRight();
				lists[currList] += 1;
			}
			currList--;
			currDown = currDown.getDown();
			currRight = currDown;
		}
		
		double expectedNums;
		for (int i=list.getCurrHeight()-1; i>=0; i--) {
			expectedNums = (numItems) / (Math.pow(2, i)); // n/2^i is the expected size for list i
			System.out.println("List s" + i + " has " + lists[i] + " nodes. Theoretically it should have " + expectedNums + " nodes");
		}
	}

}

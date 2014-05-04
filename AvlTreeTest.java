
import java.util.Random;


public class AvlTreeTest {
	static AvlTree tree;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		testLookUpTime();
		//testScanFordwardsForRetrieval(1000);
		//testDropdownsForRetrieval(1000);		
		//printTree();
		
		
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
		tree = new AvlTree(0);
		Random rand = new Random();
		int randNum = rand.nextInt(numElements*100);
		for (int i=0; i < numElements; i++) {
			tree.insert(randNum);
			randNum = rand.nextInt(numElements*10);
		}
	}
	
	public static void setUpIncreasing(int numElements) {
		tree = new AvlTree(0);
		for (int i=0; i < numElements; i++) {
			tree.insert(i);
		}
	}
	
	public void setUpDecreasing(int numElements) {
		tree = new AvlTree(0);
		for (int i=numElements; i > 0; i--) {
			tree.insert(i);
		}
	}
	
	public static void testContains(int numElements) {
		for (int i=0; i<numElements; i++) {
			tree.contains(i);
		}
	}
	
	public static void printTree() {
		tree.inorderPrint();
	}
	

}
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class SynchronizedListTest {
	public static SynchronizedSkipList list;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		list = new SynchronizedSkipList();
		//new Thread(t1).start();
        //new Thread(t2).start();
        ExecutorService es = Executors.newCachedThreadPool();
        es.execute(t1);
        es.execute(t2);
        es.execute(t3);
        es.execute(t4);
        es.shutdown();
        try {
        	boolean finished = false;
        	while (!finished)
        		finished = es.awaitTermination(1, TimeUnit.MINUTES);
        	
			list.displayList();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
        
	}
	
	 private static Runnable t1 = new Runnable() {
	        public void run() {
	            try{
	                for(int i=0; i<4000; i++){
	                	list.insert(i);
	                }
	            } catch (Exception e){}

	        }
	    };

	    private static Runnable t2 = new Runnable() {
	        public void run() {
	            try{
	                for(int i=0; i<4000; i++){
	                    list.insert(i);
	                }
	            } catch (Exception e){}
	       }
	    };
	    
	    private static Runnable t3 = new Runnable() {
	        public void run() {
	            try{
	                for(int i=0; i<4000; i++){
	                    list.insert(i);
	                }
	            } catch (Exception e){}
	       }
	    };
	    
	    private static Runnable t4 = new Runnable() {
	        public void run() {
	            try{
	                for(int i=0; i<4000; i++){
	                    list.insert(i);
	                }
	            } catch (Exception e){}
	       }
	    };
	    

}

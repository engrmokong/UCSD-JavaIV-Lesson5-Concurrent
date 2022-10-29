import java.util.Scanner;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Assignment6 {

	public static AtomicLong count = new AtomicLong();
	public static int locknumber;
    public static Lock countLock = new ReentrantLock();


	public static void main(String[] args) throws InterruptedException{
		// TODO Auto-generated method stub


		//Scanner keyboard = new Scanner(System.in);
		//int num_threads = keyboard.nextInt();
		//System.out.println("Enter number of threads: " + num_threads);
		int num_threads = Runtime.getRuntime().availableProcessors();
		System.out.println("Number of threads: "+ num_threads);

		Runnable numberOfChar = () -> {
			String s1 = "Hello World!";
			int x = 0;
			for(int i=0; i < 20; i++) {

				//if(s1.charAt(i) != ' ') {
					//x++;
				
				System.out.println("number of characters: " + i);
			}
			//System.out.println("number of characters: " + i);
		};
		
		Runnable atomic = () -> {
			String s2 = "Another Hello World";
			for (int k = 1; k <= 20; k++) {
				count.incrementAndGet();
				
				
				System.out.println("AtomicLong: "  + count);
			}
		};
		
		Runnable task = () -> {
            for (int k = 1; k <= 1000; k++) {
                countLock.lock();
                try {
                	
                    locknumber++;
                    
    			
                } finally {
                    countLock.unlock(); // Make sure the lock is unlocked
                }                    
            }
        };

		ExecutorService executor = Executors.newFixedThreadPool(num_threads);



		executor.execute(task);
		executor.execute(atomic);
		executor.execute(numberOfChar);
		executor.shutdown();
	}
}



package com.xiaocui.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestCallable {
	public static void main(String[] args) throws Exception {
		ExecutorService  pool = Executors.newFixedThreadPool(4);  
		TestTask2 t1 = new TestTask2();
		TestTask2 t2 = new TestTask2();
		Future<Integer> i1 = pool.submit(t1);
		Future<Integer> i2 = pool.submit(t2);
		pool.shutdown(); 
		while (true) {  

            if (pool.isTerminated()) {  
                break;  
            }  
        }
		System.out.println("i1:"+i1.get());
		System.out.println("i2:"+i2.get());
	}
	
}

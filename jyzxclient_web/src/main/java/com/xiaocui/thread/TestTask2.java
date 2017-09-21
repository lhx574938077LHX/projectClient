package com.xiaocui.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;

public class  TestTask2  implements Callable<Integer>{
	public static int sys=0;;
	@Override
	public Integer call() throws Exception {
		synchronized(TestTask2.class){
			TestTask2.sys+=1;
			System.out.println("sys:"+TestTask2.sys);
		}
		BlockingQueue  queue = new ArrayBlockingQueue<String>(10);
		ConcurrentLinkedQueue  queue1 = new ConcurrentLinkedQueue<String>();
		return TestTask2.sys;
	}

}

package com.xiaocui.controller;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import com.aspose.cells.Cell;
import com.aspose.cells.Cells;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import com.aspose.cells.WorksheetCollection;
import com.xiaocui.thread.TestTask;

public class SearchTest {

	static ExecutorService  pool = Executors.newFixedThreadPool(4);  
	
	static ExecutorService pool1 = Executors.newCachedThreadPool();
	
	static ExecutorService pool2 = Executors.newSingleThreadExecutor();
	
	static ExecutorService pool3 = Executors.newScheduledThreadPool(1);
	
	static ExecutorService pool4 = Executors.newWorkStealingPool();
	
	
	public static void main(String[] args) {
		
		int cpuNums = Runtime.getRuntime().availableProcessors();
		System.out.println(cpuNums);
		
		long nowTime1 = System.currentTimeMillis();
		
		String path = args[0];
		try {
			Workbook workbook = new Workbook(path);
			//读取第一个sheet
			WorksheetCollection worksheets = workbook.getWorksheets();
			Worksheet sheet = worksheets.get(0);
			Cells rows = sheet.getCells();
			
//			int cpuNums = Runtime.getRuntime().availableProcessors();
			for (int j = 0; j < sheet.getCells().getRows().getCount(); j++) {
				Cell cellName = rows.get(j, 0);
				Cell cellIdcard = rows.get(j, 1);
				String realName = (String) cellName.getValue();
				String idCard = (String) cellIdcard.getValue();
				TestTask testTask = new TestTask();
				Thread t = new Thread(testTask);
				t.setPriority(Thread.NORM_PRIORITY  );
				testTask.setRealName(realName);
				testTask.setIdCard(idCard);
				pool.execute(t);
			}	
			
			pool.shutdown(); 
			while (true) {  

	            if (pool.isTerminated()) {  
	                break;  
	            }  
	        }
			long nowTime2 = System.currentTimeMillis();
			System.out.println("运行时间："+(nowTime2-nowTime1));
 			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			
		}
		
	}
}

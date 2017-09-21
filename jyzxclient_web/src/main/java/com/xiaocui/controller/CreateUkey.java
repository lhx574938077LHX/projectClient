package com.xiaocui.controller;

import java.util.Random;
import java.util.UUID;

import com.aspose.cells.Cells;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import com.aspose.cells.WorksheetCollection;


public class CreateUkey {
	public static void main(String[] args) {
		String path = "C:\\Users\\LHX\\Desktop\\91征信接口开发文件\\ukey.xlsx";
		try {
			Workbook workbook = new Workbook(path);
			//读取第一个sheet
			WorksheetCollection worksheets = workbook.getWorksheets();
			Worksheet sheet = worksheets.get(0);
			Cells cells = sheet.getCells();
			
//			System.out.println(Integer.toHexString(new Random().nextInt()).toUpperCase());  //读写密码
//			System.out.println(UUID.randomUUID().toString().toUpperCase().replaceAll("-", ""));  //密钥、pass
			
			for(int i=1;i<=201;i++){
				cells.get(i,3).setValue(Integer.toHexString(new Random().nextInt()).toUpperCase());
				cells.get(i,4).setValue(Integer.toHexString(new Random().nextInt()).toUpperCase());
				cells.get(i,5).setValue(Integer.toHexString(new Random().nextInt()).toUpperCase());
				cells.get(i,6).setValue(Integer.toHexString(new Random().nextInt()).toUpperCase());
				cells.get(i,7).setValue(UUID.randomUUID().toString().toUpperCase().replaceAll("-", ""));
				cells.get(i,8).setValue(UUID.randomUUID().toString().toUpperCase().replaceAll("-", ""));
				cells.get(i,9).setValue(UUID.randomUUID().toString().toUpperCase().replaceAll("-", ""));
				cells.get(i,12).setValue("SN:"+cells.get(i,10).getValue()+"|KEY:"+cells.get(i,9).getValue());
			}
			workbook.save(path);
			System.out.println("final");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

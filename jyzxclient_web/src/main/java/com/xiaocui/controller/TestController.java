package com.xiaocui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xiaocui.service.TestServiceImpl;

@Controller
@RequestMapping("test")
public class TestController {
	
	@Autowired
	private TestServiceImpl testService;

	
	@RequestMapping("testSave")	
	public void testSave(){
		
		try {
			System.out.println("执行方法。。。");
			testService.testSaveXyvalidation();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

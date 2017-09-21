package com.xiaocui.controller;

import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.aspose.cells.Cell;
import com.aspose.cells.Cells;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import com.aspose.cells.WorksheetCollection;
import com.xiaocui.platform.entity.ResultJson;
import com.xiaocui.thread.InsertIndexMd5Task;
import com.xiaocui.thread.InsertIndexTask;

@Controller
@RequestMapping("searchIndex")
public class SearchIndexController {
	
	@RequestMapping("insertIndexV")
	public String insertIndexV(){
		return "searchIndex";
	}
	
	@RequestMapping("insertIndexMd5V")
	public String insertIndexMd5V(){
		return "insertIndexMd5";
	}	
	
	@RequestMapping("saveSearchIndex")
	@ResponseBody
	public ResultJson saveSearchIndex(HttpServletRequest request, HttpServletResponse response){
		ResultJson resultJson = new ResultJson();
		resultJson.setSuccess(false);
		String companyId = request.getParameter("companyId").trim();
		String companyCode = request.getParameter("companyCode").trim();
		String isOnlineStr = request.getParameter("isOnline");
		Short isOnline = new Short(isOnlineStr);
		System.out.println("公司ID："+companyId+"公司编码："+companyCode+"是否上线："+isOnline);

		try {
			//SpringMVC上传文件方式
			CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
			if(commonsMultipartResolver.isMultipart(request))
			{
				MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;   
		        Iterator<String> iterator = multipartRequest.getFileNames();
		        
		        while(iterator.hasNext())
				{
		        	MultipartFile file = multipartRequest.getFile(iterator.next());
		        	String filename = file.getOriginalFilename();   //文件初始名称
					String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));  //后缀
		        	System.out.println("文件初始名称:"+filename+"后缀:"+suffix);
		        	switch(suffix)
					{
						case ".xls":
						case ".xlsx":
							break;
						default:
							{
								resultJson.setData("文件格式错误，请使用excel文件");
								resultJson.setSuccess(false);
								return resultJson;
							}
					}
		        	Workbook workbook = new Workbook(file.getInputStream());
		        	WorksheetCollection worksheets = workbook.getWorksheets();
					Worksheet sheet = worksheets.get(0);
					Cells rows = sheet.getCells();
					int cpuNums = Runtime.getRuntime().availableProcessors();
					ExecutorService pool = Executors.newFixedThreadPool(cpuNums);  
					for (int j = 0; j < sheet.getCells().getRows().getCount(); j++) {
						Cell cellName = rows.get(j, 0);
						Cell cellIdcard = rows.get(j, 1);
						String realName = (String) cellName.getValue();
						String idCard = (String) cellIdcard.getValue();
						System.out.println(realName+"------"+idCard);
						InsertIndexTask insertIndexTaskThread=new InsertIndexTask();
						insertIndexTaskThread.setRealName(realName.trim());
						insertIndexTaskThread.setIdCard(idCard.trim());
						insertIndexTaskThread.setIsOnline(isOnline);
						insertIndexTaskThread.setCompanyId(companyId);
						insertIndexTaskThread.setCompanyCode(companyCode);
						pool.execute(new Thread(insertIndexTaskThread));
					}
					pool.shutdown(); 
			        while (true) {  

			            if (pool.isTerminated()) {  
			                break;  
			            }  
			        }
				}
			}else{
				System.out.println("没有响应的文件");
			}
            
		} catch (Exception e) {
			e.printStackTrace();
			resultJson.setData("插入异常！");
			resultJson.setSuccess(false);
		} finally{
			
		}
		resultJson.setData("插入成功！");
		resultJson.setSuccess(true);
		return resultJson;
	}
	
//	
	@RequestMapping("insertIndexMd5")
	@ResponseBody
	public ResultJson insertIndexMd5(HttpServletRequest request, HttpServletResponse response){
		ResultJson resultJson = new ResultJson();
		resultJson.setSuccess(false);
		String companyId = request.getParameter("companyId").trim();
		String companyCode = request.getParameter("companyCode").trim();
		String isOnlineStr = request.getParameter("isOnline");
		Short isOnline = new Short(isOnlineStr);
		System.out.println("公司ID："+companyId+"公司编码："+companyCode+"是否上线："+isOnline);

		try {
			//SpringMVC上传文件方式
			CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
			if(commonsMultipartResolver.isMultipart(request))
			{
				MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;   
		        Iterator<String> iterator = multipartRequest.getFileNames();
		        
		        while(iterator.hasNext())
				{
		        	MultipartFile file = multipartRequest.getFile(iterator.next());
		        	String filename = file.getOriginalFilename();   //文件初始名称
					String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));  //后缀
		        	System.out.println("文件初始名称:"+filename+"后缀:"+suffix);
		        	switch(suffix)
					{
						case ".xls":
						case ".xlsx":
							break;
						default:
							{
								resultJson.setData("文件格式错误，请使用excel文件");
								resultJson.setSuccess(false);
								return resultJson;
							}
					}
		        	Workbook workbook = new Workbook(file.getInputStream());
		        	WorksheetCollection worksheets = workbook.getWorksheets();
					Worksheet sheet = worksheets.get(0);
					Cells rows = sheet.getCells();
					int cpuNums = Runtime.getRuntime().availableProcessors();
					ExecutorService pool = Executors.newFixedThreadPool(cpuNums);  
					for (int j = 1; j < sheet.getCells().getRows().getCount(); j++) {
						Cell idCardMd5Cell = rows.get(j, 0);
						String idCardMd5 = (String)idCardMd5Cell.getValue();
						
						System.out.println("-----传入参数："+idCardMd5+"------公司编码："+companyCode);
						
						InsertIndexMd5Task insertIndexMd5Task = new InsertIndexMd5Task();
						insertIndexMd5Task.setCompanyCode(companyCode);
						insertIndexMd5Task.setCompanyId(companyId);
						insertIndexMd5Task.setIsOnline((short)1);//0.未上线1.已上线
						insertIndexMd5Task.setIdCardMd5(idCardMd5);
						pool.execute(new Thread(insertIndexMd5Task));
					}
					pool.shutdown(); 
			        while (true) {  

			            if (pool.isTerminated()) {  
			                break;  
			            }  
			        }
				}
			}else{
				System.out.println("没有响应的文件");
			}
            
		} catch (Exception e) {
			e.printStackTrace();
			resultJson.setData("插入异常！");
			resultJson.setSuccess(false);
		} finally{
			
		}
		resultJson.setData("插入成功！");
		resultJson.setSuccess(true);
		return resultJson;
	}
	
}



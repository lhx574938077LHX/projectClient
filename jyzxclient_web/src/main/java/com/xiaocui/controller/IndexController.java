package com.xiaocui.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xiaocui.entity.PkgHeader;
import com.xiaocui.service.Task3001;
import com.xiaocui.service.Task3002;
import com.xiaocui.settings.SystemSetting;

@Controller
@RequestMapping("/")
public class IndexController {
//	private static final String companyCode = "P2PHT8B0000000001";	//请以配置文件形式或储存到数据库形式保存获取
	
	@Autowired
	private SystemSetting systemSetting;
	
	@Autowired
	private Task3001 task3001;
	
	@Autowired
	private Task3002 task3002;
	
	@RequestMapping("indexV")
	public String indexV(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("1111111111");
		return "index";
	}
	
	@RequestMapping("receive")
	public void receive(HttpServletRequest request, HttpServletResponse response) {
		OutputStream os = null;
		ByteArrayOutputStream swapStream = null;
		try {
			System.out.println("测试开始");
			swapStream = new ByteArrayOutputStream();  
		    byte[] buf = new byte[4096];  
		    int num = 0;  
		    while ((num = request.getInputStream().read(buf,0,4096)) > 0) {  
		        swapStream.write(buf, 0, num);  
		    }  
		    byte[] reqData = swapStream.toByteArray();
		    byte[] rspData = zxservice(reqData);
		    
		    os = response.getOutputStream();
		    os.write(rspData);
		    os.flush();
		    os.close();
		    swapStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(os!=null){try{os.close();}catch(IOException e){}};		
			if(swapStream!=null){try{swapStream.close();}catch(IOException e){}};		
		}
	}

	public byte[] zxservice(byte[] postData){
		PkgHeader reqPkg = new PkgHeader();	//请求报文
		PkgHeader rspPkg = new PkgHeader();	//响应报文
		
		rspPkg.setCustNo(systemSetting.getCompanyCode());	//配置服务器编号
		rspPkg.setEncode(reqPkg.getEncode());	//设置编码
		rspPkg.setEncryptType(reqPkg.getEncryptType());	//设置加密类型
		rspPkg.setMsgType(reqPkg.getMsgType());	//设置消息类型
		rspPkg.setVersion(reqPkg.getVersion());//设置版本
		
		try {
			reqPkg.parseFormBytes(postData,reqPkg.getEncode());	//解析请求报文
			rspPkg = distributeReq(reqPkg,rspPkg);   //进行消息派发处理
		}
		catch (Exception e) {
			e.printStackTrace();
			rspPkg.setRetCode("9999");
			rspPkg.setRetMsg("系统异常消息处理失败");
			rspPkg.setMsgBody("");
		}
		
		return rspPkg.toPkgBytes("UTF-8");
	}
	
	public PkgHeader distributeReq(PkgHeader reqPkg,PkgHeader rspPkg){
		try {
			String txnCode = reqPkg.getTrxCode();
			switch (txnCode) {
				//客户端查询处理
				case "3001":
					{
						rspPkg = task3001.doTask(reqPkg,rspPkg);
						rspPkg.setTrxCode("4001");
					}
					break;
				case "3002":
					{
						rspPkg = task3002.doTask(reqPkg,rspPkg);
						rspPkg.setTrxCode("4002");
					}
					break;
				default:
				{
					throw new Exception("未知的报文类型");
				}
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
			rspPkg.setRetCode("9999");
			rspPkg.setRetMsg("系统异常消息处理失败");
			rspPkg.setMsgBody("");
		}
		return rspPkg;
	}
	
	
	
	
}

package com.xiaocui.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.net.ssl.SSLContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.aspose.cells.Cell;
import com.aspose.cells.Cells;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import com.aspose.cells.WorksheetCollection;
import com.fasterxml.jackson.core.type.TypeReference;
import com.xiaocui.common.JsonSerializer;
import com.xiaocui.common.SystemSetting;
import com.xiaocui.entity.LoanInfo;
import com.xiaocui.entity.Pkg3001;
import com.xiaocui.entity.Pkg3002;
import com.xiaocui.entity.Pkg4001;
import com.xiaocui.entity.PkgHeader;
import com.xiaocui.platform.common.StringUtils;
import com.xiaocui.platform.dao.search.TestDataResultMapper;
import com.xiaocui.platform.entity.AUDJson;
import com.xiaocui.platform.entity.DataGridJson;
import com.xiaocui.platform.entity.ResultJson;
import com.xiaocui.platform.entity.Task3001Json;
import com.xiaocui.platform.model.search.JdData;
import com.xiaocui.platform.model.search.TestDataResult;
import com.xiaocui.service.SearchTask;
import com.xiaocui.service.TestManageServiceImpl;

@Controller
@RequestMapping("testManage")
public class TestManageController {
	
	private final static Logger logger = Logger.getLogger(TestManageController.class);
	
	private static final String companyCode = "P2P4HJK0000100010";	
	
	@Autowired
	private TestDataResultMapper testDataResultMapper;
	
	@Autowired
	private TestManageServiceImpl testManageService;
	
	@RequestMapping("queryJdDataLJP")
	@ResponseBody
	public DataGridJson queryJdDataLJP(HttpServletRequest request, HttpServletResponse response, int start, int limit){
		DataGridJson result = new DataGridJson();
		try {
			result = testManageService.queryJdData(start,limit);
		} catch (Exception e) {
			e.printStackTrace();
//			logger.error("91征信  Exception: ", e);
			result.setResult(false);
			result.setMsg("系统异常！");
			result.setDataCount(0);
			result.setDatas(null);
		}
		return result;
	}
	
	@RequestMapping("addJdData")
	@ResponseBody
	public AUDJson addJdData(HttpServletRequest request, HttpServletResponse response, JdData jdData){
		AUDJson result = new AUDJson();
		try {
			result = testManageService.addJdData(jdData);
		} catch (Exception e) {
			e.printStackTrace();
//			logger.error("91征信  Exception: ", e);
			result.setData(null);
			result.setMsg("系统异常！");
			result.setResult(false);
		}
		return result;
	}
	
	@RequestMapping("deleteJdData")
	@ResponseBody
	public AUDJson deleteJdData(HttpServletRequest request, HttpServletResponse response, long id){
		AUDJson result = new AUDJson();
		try {
			testManageService.deleteJdData(id);
			
			result.setData(null);
			result.setMsg("删除成功");
			result.setResult(true);
		} catch (Exception e) {
			e.printStackTrace();
//			logger.error("91征信  Exception: ", e);
			result.setData(null);
			result.setMsg("系统异常！");
			result.setResult(false);
		}
		return result;
	}	
	
	@RequestMapping("editJdData")
	@ResponseBody
	public AUDJson editJdData(HttpServletRequest request, HttpServletResponse response, JdData jdData){
		AUDJson result = new AUDJson();
		try {
			testManageService.editJdData(jdData);
			result.setData(null);
			result.setMsg("保存成功");
			result.setResult(true);
		} catch (Exception e) {
			e.printStackTrace();
//			logger.error("91征信  Exception: ", e);
			result.setData(null);
			result.setMsg("系统异常！");
			result.setResult(false);			
		}
		
		return result;
	}
	
	@RequestMapping("send3001Data")
	@ResponseBody	
	public Task3001Json send3001Data(HttpServletRequest request, HttpServletResponse response, String realName,String idCard,String url){
		Task3001Json jsonResult = new Task3001Json();
		
		Pkg3001 pkg3001 = new Pkg3001();
		pkg3001.setIdCard(idCard);
		pkg3001.setRealName(realName);
		pkg3001.setCompanyCode(companyCode);
		
		String msgBody = JsonSerializer.serializer(pkg3001);
		
		PkgHeader reqPkg = new PkgHeader();
		PkgHeader rspPkg = new PkgHeader();
		reqPkg.setVersion("01");			//默认01
		reqPkg.setCustNo(companyCode);				//公司代码
		reqPkg.setEncode("01");			//01.UTF8 02.GBK
		reqPkg.setTrxCode("3001");			//报文编号 默认四位 例:0001
		reqPkg.setEncryptType("01");	//加密类型 01.不加密 02.RSA
		reqPkg.setMsgType("01");			//01.JSON 02.XML 03.Protobuf
		reqPkg.setMsgBody(msgBody);			//报文主体
		
		CloseableHttpClient httpclient = createSSLClientDefault();
		HttpPost post = new HttpPost(url);
		System.out.println("-----------------------");   
		System.out.println(reqPkg.toPkgStr());   //输出请求报文
		System.out.println("-----------------------");
		ByteArrayEntity reqEntity = new ByteArrayEntity(reqPkg.toPkgBytes("UTF-8"));

		post.setEntity(reqEntity);	
		
		CloseableHttpResponse responses;
		String result = "";		
		try {
			responses = httpclient.execute(post);
			HttpEntity rspEntity = responses.getEntity();

		    if (rspEntity != null) {
		    	result = EntityUtils.toString(rspEntity);
		    }
		    //输出返回信息
		    System.out.println(result);

		    rspPkg.parseFromString(result); //解析报文头
		    
	 	    System.out.println(rspPkg);
		    
	 	   jsonResult.setData(result);
	 	    
		    if(rspPkg.getRetCode().equals("0000"))
		    {
			    System.out.println("响应消息1:" + rspPkg.getRetMsg());
		    	Pkg4001 pkg4001 = (Pkg4001) JsonSerializer.deserializer(rspPkg.getMsgBody(),new TypeReference<Pkg4001>(){});
		    	
		    	for(LoanInfo loanInfo : pkg4001.getLoanInfos())
				{
		    		System.out.println(loanInfo.getRepayState());
		    		System.out.println(loanInfo.getContractDate());
				}
		    	if(pkg4001.getLoanInfos().size()!=0){
		    		jsonResult.setRedata(pkg4001.getLoanInfos());
		    	}else{
		    		jsonResult.setRedata(null);
		    	}
		    	jsonResult.setMsg("报文格式正确！");
		    }
		    else
		    {
		    	System.out.println("响应消息2:" + rspPkg.getRetMsg());
		    	jsonResult.setMsg("报文格式有误！");
		    	jsonResult.setRedata(null);
		    }
		    jsonResult.setResult(true);
		    responses.close();
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setMsg("数据发送失败！");
			jsonResult.setResult(false);
	    	jsonResult.setRedata(null);
	    	jsonResult.setData(null);
		}
		
		return jsonResult;
	}
	
	@RequestMapping("send3002Data")
	@ResponseBody	
	public AUDJson send3002Data(HttpServletRequest request, HttpServletResponse response,String trxNo,String url,@RequestParam(defaultValue = "0")short borrowType
			,@RequestParam(defaultValue = "0")short borrowState,@RequestParam(defaultValue = "0")Short borrowAmount,@RequestParam(defaultValue = "0")long contractDate
			,@RequestParam(defaultValue = "0")Short loanPeriod,@RequestParam(defaultValue = "0")Short repayState,@RequestParam(defaultValue = "0")Long arrearsAmount){
		AUDJson audJson = new AUDJson();
		Pkg3002 pkg3002 = new Pkg3002();
		pkg3002.setTrxNo(trxNo);
		
		List<LoanInfo> loanInfos = new ArrayList<LoanInfo>();
		LoanInfo loanInfo = new LoanInfo();
		loanInfo.setBorrowType(borrowType);
		loanInfo.setBorrowState(borrowState);
		loanInfo.setBorrowAmount(borrowAmount);
		loanInfo.setContractDate(new Date(contractDate));
		loanInfo.setLoanPeriod(loanPeriod);
		loanInfo.setRepayState(repayState);
		loanInfo.setArrearsAmount(arrearsAmount);
		loanInfo.setCompanyCode(companyCode);
		for(int i=0;i<2;i++){
			loanInfos.add(loanInfo);
		}
		
		pkg3002.setLoanInfos(loanInfos);
		
		String msgBody = JsonSerializer.serializer(pkg3002);
		
		PkgHeader reqPkg = new PkgHeader();
		PkgHeader rspPkg = new PkgHeader();
		reqPkg.setVersion("01");			//默认01
		reqPkg.setCustNo(companyCode);				//公司代码
		reqPkg.setEncode("01");			//01.UTF8 02.GBK
		reqPkg.setTrxCode("3002");			//报文编号 默认四位 例:0001
		reqPkg.setEncryptType("01");	//加密类型 01.不加密 02.RSA
		reqPkg.setMsgType("01");			//01.JSON 02.XML 03.Protobuf
		reqPkg.setMsgBody(msgBody);			//报文主体		
		
		CloseableHttpClient httpclient = createSSLClientDefault();
		HttpPost post = new HttpPost(url);
		System.out.println("-----------------------");
		System.out.println(reqPkg.toPkgStr());   //输出请求报文
		System.out.println("-----------------------");
		ByteArrayEntity reqEntity = new ByteArrayEntity(reqPkg.toPkgBytes("UTF-8"));
		post.setEntity(reqEntity);	
		
		CloseableHttpResponse responses;
		String result = "";
		try {
			responses = httpclient.execute(post);
			HttpEntity rspEntity = responses.getEntity();

		    if (rspEntity != null) {
		    	result = EntityUtils.toString(rspEntity);
		    }
		    
		    System.out.println(result);
		    
		    rspPkg.parseFromString(result); //解析报文头
		    
		    System.out.println(rspPkg);
		    
		    audJson.setData(result);
		    
		    if(rspPkg.getRetCode().equals("0000"))
		    {
			    System.out.println("响应消息1:" + rspPkg.getRetMsg());
			    audJson.setMsg("报文格式正确！");
		    }
		    else
		    {
		    	System.out.println("响应消息2:" + rspPkg.getRetMsg());
		    	 audJson.setMsg("报文格式错误！");
		    }
		    audJson.setResult(true);
		} catch (Exception e) {
			e.printStackTrace();
			audJson.setResult(false);
			audJson.setMsg("数据传输失败！");
			audJson.setData(null);
		}		
		
		
		return audJson;
	}
	
	protected static CloseableHttpClient createSSLClientDefault(){
		try {
			 SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
			 //信任所有
			     public boolean isTrusted(X509Certificate[] chain,String authType) throws CertificateException {
			         return true;
			     }
			 }).build();
			 SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
			 return HttpClients.custom().setSSLSocketFactory(sslsf).build();
		 } catch (KeyManagementException e) {
		     e.printStackTrace();
		 } catch (NoSuchAlgorithmException e) {
		     e.printStackTrace();
		 } catch (KeyStoreException e) {
		     e.printStackTrace();
		 }
		 return  HttpClients.createDefault();
	}
	
	
	@RequestMapping("fileUpload")
	@ResponseBody	
	public ResultJson fileUpload(HttpServletRequest request, HttpServletResponse response,String companyCode,String sign){
		ResultJson result = new ResultJson();
		System.out.println(companyCode);
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
//		        	String fileId= StringUtils.getUUID();//保存的文件名称
					String fileId= "";//保存的文件名称
		        	String path = SystemSetting.saveFilePath + fileId + suffix;//保存的文件路径
		        	System.out.println(path);
		        	
		        	File fileExcle = new File(path);
		        	file.transferTo(fileExcle);//springMvc 保存文件
		        	
		        	System.out.println(sign);
		        	
		        	//测试数据
		        	testExcle(request, response, companyCode, sign, path);
		    		result.setData(path);
				}
			}else{
				System.out.println("没有响应的文件");
			}
            
		} catch (Exception e) {
			e.printStackTrace();
			result.setData(null);
			result.setSuccess(false);
		}

		result.setSuccess(true);
		return result;
	}
	
	/**
	 * 保存测试文件到客户端
	 * @param request
	 * @param response
	 * @param path
	 * @param filename
	 */
	@RequestMapping("saveFile")
	public void saveFile(HttpServletRequest request, HttpServletResponse response,String path,String filename){
//		InputStream is = null;
		OutputStream os = null;
		BufferedInputStream bis = null;
//		BufferedOutputStream bos = null;
		try {
	    	//文件下载到本地、然后删除
			response.setContentType("application/msexcel");
	        response.setCharacterEncoding("utf-8");  
	        response.setHeader("Content-disposition", "attachment; filename="+ filename);  
			
	    	FileInputStream fileInput = new FileInputStream(path);
	    	bis = new BufferedInputStream(fileInput);
	//    	bos = new java.io.BufferedOutputStream(response.getOutputStream());
	    	os = response.getOutputStream();
	    	byte[] buff = new byte[2048];  
	        int i;  
	
	//        while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {  
	//	        bos.write(buff, 0, bytesRead);  
	        while ((i = bis.read(buff)) != -1) {  
	        	os.write(buff, 0, i);  
	            i++;  
	        }
	        os.flush(); 
	        
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(bis!=null){try {bis.close();} catch (IOException e) {e.printStackTrace();}bis = null;}
			if(os!=null){try {os.close();} catch (IOException e) {e.printStackTrace();}os = null;}
		}
	}
	
	//查询数据
	public void testExcle(HttpServletRequest request, HttpServletResponse response,String companyCode,String sign,String path){
		
		long nowTime1 = System.currentTimeMillis();
		
		testManageService.deleteTestDataResult(); //删除剩余数据
		
		int clients = 0; //提交客户量
		int reClients = 0;//反馈客户量
		int reInfors = 0;//反馈信息量
		try {
			Workbook workbook = new Workbook(path);
			
			//读取第一个sheet
			WorksheetCollection worksheets = workbook.getWorksheets();
			Worksheet sheet = worksheets.get(0);
			Cells rows = sheet.getCells();
			
			//新建一个sheet
			Worksheet sheet1 = worksheets.add("数据反馈-91征信");
			Cells cells = sheet1.getCells();
			cells.get(4, 0).setValue("编号");
			cells.get(4, 1).setValue("姓名");
			cells.get(4, 2).setValue("身份证");
			cells.get(4, 3).setValue("数据反馈时间");
			cells.get(4, 4).setValue("借款类型");
			cells.get(4, 5).setValue("借款状态");
			cells.get(4, 6).setValue("借款年月");
			cells.get(4, 7).setValue("借款金额");
			cells.get(4, 8).setValue("借款期数");
			cells.get(4, 9).setValue("当前状态");
			cells.get(4, 10).setValue("欠款金额");
			int i = 5;
			
			ExecutorService pool = Executors.newFixedThreadPool(3);  
			for (int j = 1; j < sheet.getCells().getRows().getCount(); j++) {
				clients = clients + 1;
				Cell cellName = rows.get(j, 0);
				Cell cellIdcard = rows.get(j, 1);
				String realName = (String) cellName.getValue();
				String idCard = (String) cellIdcard.getValue();
				System.out.println(realName+"------"+idCard);
				//方法1：将线程 用@Autowired注入,线程里就可以调用数据库了 2：new 线程，将mapper当做参数传入线程中
				SearchTask searchTaskThread = new SearchTask();
				searchTaskThread.setCompanyCode(companyCode);
				searchTaskThread.setIdCard(idCard);
				searchTaskThread.setRealName(realName);
				searchTaskThread.setTestDataResultMapper(testDataResultMapper);
				searchTaskThread.setSign(sign);
				pool.execute(new Thread(searchTaskThread));
				
//				Future<?> f = pool.submit(new Thread(searchTaskThread));
//				f.get();//如果Executor后台线程池还没有完成Callable的计算，这调用返回Future对象的get()方法，会阻塞直到计算完成。
			}	
			
			pool.shutdown(); 
	        while (true) {  

	            if (pool.isTerminated()) {  
	                break;  
	            }  
	        }
			
			String realName="";
			
			List<TestDataResult> testDataResults = testManageService.searchTestDataResult();
			for(TestDataResult testDataResult:testDataResults){
				reInfors = reInfors  +1;
				if(!realName.equals(testDataResult.getRealName())){
					reClients=reClients+1;
					realName = testDataResult.getRealName();
				}
				cells.get(i, 0).setValue(i-4);//编号
				cells.get(i, 1).setValue(testDataResult.getRealName());//姓名
				cells.get(i, 2).setValue(testDataResult.getIdcard());//身份证号
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String nowDate = sdf.format(new Date(testDataResult.getRecTime()));
				cells.get(i, 3).setValue(nowDate);//数据反馈时间
				
				if(testDataResult.getBorrowtYpe()!=null){
					switch (testDataResult.getBorrowtYpe()) {
						case 1:
							cells.get(i, 4).setValue("个人信贷");//借款类型
							break;
						case 2:
							cells.get(i, 4).setValue("个人抵押");//借款类型
							break;
						case 3:
							cells.get(i, 4).setValue("企业信贷");//借款类型
							break;
						case 4:
							cells.get(i, 4).setValue("企业抵押");//借款类型
							break;
						default:
							cells.get(i, 4).setValue("未知");//借款类型
							break;
					}
				}else{
					cells.get(i, 4).setValue("-");//借款类型
				}
				if(testDataResult.getBorrowState()!=null){
					switch (testDataResult.getBorrowState()) {
						case 1:
							cells.get(i, 5).setValue("拒贷");//借款状态
							break;
						case 2:
							cells.get(i, 5).setValue("批贷已放款");//借款状态
							break;
						case 4:
							cells.get(i, 5).setValue("借款人放弃申请");//借款状态
							break;
						case 5:
							cells.get(i, 5).setValue("审核中");//借款状态
							break;
						case 6:
							cells.get(i, 5).setValue("待放款");//借款状态
							break;
						default:
							cells.get(i, 5).setValue("未知");//借款状态
							break;
					}
				}else{
					cells.get(i, 5).setValue("-");//借款状态
				}
				
				SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM");
				Object contractStr = sdf1.format(testDataResult.getContractDate());
				cells.get(i, 6).setValue(contractStr);//借款年月
				
				if(testDataResult.getBorrowAmount()!=null){
					switch (testDataResult.getBorrowAmount()) {
						case 1:
							cells.get(i, 7).setValue("【10000-20000)");//借款金额
							break;
						case 0:
							cells.get(i, 7).setValue("-");//借款金额
							break;
						case -1:
							cells.get(i, 7).setValue("【8000-10000)");//借款金额
							break;
						case -2:
							cells.get(i, 7).setValue("【6000-8000)");//借款金额
							break;
						case -3:
							cells.get(i, 7).setValue("【4000-6000)");//借款金额
							break;
						case -4:
							cells.get(i, 7).setValue("【3000-4000)");//借款金额
							break;
						case -5:
							cells.get(i, 7).setValue("【2000-3000)");//借款金额
							break;
						case -6:
							cells.get(i, 7).setValue("【1000-2000)");//借款金额
							break;
						case -7:
							cells.get(i, 7).setValue("【1-1000)");//借款金额
							break;
						default:
							int reBorrowAmount = (int)testDataResult.getBorrowAmount();
							cells.get(i, 7).setValue("【"+(reBorrowAmount*2-2)+"0000-"+(reBorrowAmount*2)+"0000)");//借款金额
							break;
					}	
				}else{
					cells.get(i, 7).setValue("-");//借款金额
				}
				
				if(testDataResult.getLoanPeriod()!=null){
					cells.get(i, 8).setValue(testDataResult.getLoanPeriod());//借款期数
				}else{
					cells.get(i, 8).setValue("-");//借款期数
				}
				
				if(testDataResult.getRepayState()!=null){
					switch (testDataResult.getRepayState()) {
						case 0:
							cells.get(i, 9).setValue("数据未反馈");//当前状态
							break;
						case 1:
							cells.get(i, 9).setValue("正常");//当前状态
							break;
						case 2:
							cells.get(i, 9).setValue("M1");//当前状态
							break;
						case 3:
							cells.get(i, 9).setValue("M2");//当前状态
							break;
						case 4:
							cells.get(i, 9).setValue("M3");//当前状态
							break;
						case 5:
							cells.get(i, 9).setValue("M4");//当前状态
							break;
						case 6:
							cells.get(i, 9).setValue("M5");//当前状态
							break;
						case 7:
							cells.get(i, 9).setValue("M6");//当前状态
							break;
						case 8:
							cells.get(i, 9).setValue("M6+");//当前状态
							break;
						case 9:
							cells.get(i, 9).setValue("已还清");//当前状态
							break;
						default:
							cells.get(i, 5).setValue("-");//当前状态
							break;						
					}
				}else{
					cells.get(i, 5).setValue("-");//当前状态
				}
				
				if(testDataResult.getArrearsAmount()>0){
					BigDecimal arrearsDecimal = new BigDecimal(testDataResult.getArrearsAmount());
					double arrearsAmount = arrearsDecimal.divide(new BigDecimal(100000)).doubleValue();
					cells.get(i, 10).setValue(arrearsAmount);
				}else{
					cells.get(i, 10).setValue("-");
				}
				i = i+1;
			}
			
			cells.get(0, 0).setValue("提交客户量");
			cells.get(1, 0).setValue("反馈客户量");
			cells.get(2, 0).setValue("反馈信息量");
			cells.get(0, 1).setValue(clients);
			cells.get(1, 1).setValue(reClients);
			cells.get(2, 1).setValue(reInfors);
			
			workbook.save(path);
			
			long nowTime2 = System.currentTimeMillis();
			System.out.println(nowTime2-nowTime1);
 			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			
		}
		
	}
	
}

package com.xiaocui.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.SocketTimeoutException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;	
import org.apache.http.util.EntityUtils;
import org.apache.taglibs.standard.lang.jstl.ELException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.type.TypeReference;
import com.xiaocui.common.HttpClientUtils;
import com.xiaocui.common.JsonSerializer;
import com.xiaocui.common.MD5Utils;
import com.xiaocui.entity.BlackListInfo;
import com.xiaocui.entity.Datas;
import com.xiaocui.entity.Details;
import com.xiaocui.entity.LoanInfo;
import com.xiaocui.entity.Pkg1001;
import com.xiaocui.entity.Pkg1002;
import com.xiaocui.entity.Pkg2001;
import com.xiaocui.entity.Pkg2002;
import com.xiaocui.entity.Pkg3001;
import com.xiaocui.entity.Pkg3002;
import com.xiaocui.entity.Pkg4001;
import com.xiaocui.entity.PkgHeader;
import com.xiaocui.platform.common.HttpUtils;
import com.xiaocui.platform.common.StringUtils;
import com.xiaocui.service.Task3001;
import com.xiaocui.service.Task3002;
import com.xiaocui.service.TestServiceImpl;
import com.xiaocui.settings.SystemSetting;

@Controller
@RequestMapping("/")
public class IndexController {
	private static final String companyCode = "P2P4HJK0000100010";	
	private static final String sign = "5a59392b2f3c0f13f56cbbcfccfff25b";	
	private static final String urlPath = "http://210.72.229.172:8181/jyzx/zxservice.do";
	
	
	@Autowired
	private Task3001 task3001;
	
	@Autowired
	private Task3002 task3002;
	
	public void sort(int a[]){
		
	}
	
	public static void main(String[] args) {
		IndexController main = new IndexController();
//		main.sendDatas();
		int[] a = {3,1,5,7,2,4,9,6};
		System.out.println(a[7]);
		System.out.println(a.length);
		System.gc();
//		main.searchOneCompany("", "");
	}
	
	//用来测试用户3002接口
	@RequestMapping("sendDatas")
	@ResponseBody
	public void sendDatas(){
		Pkg3002 pkg3002 = new Pkg3002();
		String trxNo = "6aecad0a8b7e46df94386924b2095ada";
		pkg3002.setTrxNo(trxNo);
		List<LoanInfo> loanInfos = new ArrayList<LoanInfo>();
//		for(int i=0;i<1;i++){
//			LoanInfo loanInfo = new LoanInfo();
//			loanInfo.setBorrowType((short) 1);
//			loanInfo.setBorrowState((short) 2);
//			loanInfo.setBorrowAmount((short) 3);
//			loanInfo.setContractDate(new Date(1442035016251L));
//			loanInfo.setLoanPeriod((short) 9);
//			loanInfo.setRepayState((short) 8);
//			loanInfo.setArrearsAmount((long) 0);
//			loanInfo.setCompanyCode(companyCode);
//			loanInfos.add(loanInfo);
//		}
		pkg3002.setLoanInfos(loanInfos);
		
		String msgBody = JsonSerializer.serializer(pkg3002);
		PkgHeader reqPkg = new PkgHeader();
		PkgHeader rspPkg = new PkgHeader();
		reqPkg.setVersion("01");			//默认01
		reqPkg.setCustNo("SRV1A4G0000000001");				//公司代码
		reqPkg.setEncode("01");			//01.UTF8 02.GBK
		reqPkg.setTrxCode("3002");			//报文编号 默认四位 例:0001
		reqPkg.setEncryptType("02");	//加密类型 01.不加密 02.RSA
		reqPkg.setMsgType("01");			//01.JSON 02.XML 03.Protobuf
		reqPkg.setMsgBody(msgBody);			//报文主体	


		String url = "http://172.19.2.110:8080/phjrhmd/servlet/ReceiveServlet";	//公司回调地址
		CloseableHttpClient httpclient = HttpClientUtils.getCloseableHttpClient();
		HttpPost post = new HttpPost(url);
		//设置超时
		RequestConfig requestConfig = RequestConfig.custom()
				.setConnectionRequestTimeout(10000) //连接池请求超时时间
				.setSocketTimeout(10000)	//响应超时时间
				.setConnectTimeout(10000)	//连接超时时间
				.build();
		post.setConfig(requestConfig);
		
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
		    
		    if(rspPkg.getRetCode().equals("0000"))
		    {
			    System.out.println("响应消息1:" + rspPkg.getRetMsg());
		    }
		    else
		    {
		    	System.out.println("响应消息2:" + rspPkg.getRetMsg());
		    }
		} 
		catch(SocketTimeoutException se){
			System.out.println("响应超时！");
		}
		catch(ConnectTimeoutException ce){
			System.out.println("连接超时！");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//用来测试用户3001接口
	@RequestMapping("searchOneCompany")
	@ResponseBody	
	public void searchOneCompany(String realName,String idCard){
		Pkg3001 pkg3001 = new Pkg3001();
		pkg3001.setIdCard(idCard);
		pkg3001.setRealName(realName);
		pkg3001.setCompanyCode("SRV1A4G0000000001");
		
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
//		reqPkg.setSign(sign);
		
//		String url = urlPath;	//公司回调地址
		String url = "http://localhost:9080/JyzxDemo/ReceiveServletSjd";	//公司回调地址
		
		CloseableHttpClient httpclient = HttpClientUtils.getCloseableHttpClient();
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
		    
		    try {
			    System.out.println("响应消息1:" + rspPkg.getRetMsg());
		    	Pkg4001 pkg4001 = (Pkg4001) JsonSerializer.deserializer(rspPkg.getMsgBody(),new TypeReference<Pkg4001>(){});
		    	
		    	for(LoanInfo loanInfo : pkg4001.getLoanInfos())
				{
		    		System.out.println(loanInfo.getRepayState());
		    		System.out.println(loanInfo.getContractDate());
				}
			} finally {
		    	System.out.println("响应消息2:" + rspPkg.getRetMsg());
			}

		    responses.close();
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
	}
	
	
	//客户端 调用1001接口
	@RequestMapping("search")
	@ResponseBody
	public void search(HttpServletRequest request, HttpServletResponse response,String realName,String idCard){

			Pkg1001 pkg1001 = new Pkg1001();
			pkg1001.setIdCard(idCard);
			pkg1001.setRealName(realName);
			
			String msgBody = JsonSerializer.serializer(pkg1001);
			
			PkgHeader reqPkg = new PkgHeader();
			PkgHeader rspPkg = new PkgHeader();
			reqPkg.setVersion("01");			//默认01
			reqPkg.setCustNo(companyCode);				//公司代码  - 测试
			reqPkg.setEncode("01");			//01.UTF8 02.GBK
			reqPkg.setTrxCode("1001");			//报文编号 默认四位 例:0001
			reqPkg.setEncryptType("01");	//加密类型 01.不加密 02.RSA
			reqPkg.setMsgType("01");			//01.JSON 02.XML 03.Protobuf
			reqPkg.setMsgBody(msgBody);			//报文主体
			reqPkg.setSign(sign);    //签名  - 测试
			
			String url = urlPath;	//数据服务地址  - 测试
			CloseableHttpClient httpclient = HttpClientUtils.getCloseableHttpClient();
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
				System.out.println("-----------------------");
			    System.out.println(result);
				System.out.println("-----------------------");
			    
			    rspPkg.parseFromString(result); //解析报文头
			    
			    if(rspPkg.getRetCode().equals("0000"))
			    {
				    Pkg2001 pkg2001 = (Pkg2001) JsonSerializer.deserializer(rspPkg.getMsgBody(),new TypeReference<Pkg2001>(){});
				    String trxNo = pkg2001.getTrxNo();
			    }
			    else
			    {
			    	System.out.println("响应消息:" + rspPkg.getRetMsg());
			    }
			  
			    responses.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	
	//回调地址
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

		rspPkg.setCustNo(companyCode);	//配置服务器编号
		rspPkg.setEncode(reqPkg.getEncode());	//设置编码
		rspPkg.setEncryptType(reqPkg.getEncryptType());	//设置加密类型
		rspPkg.setMsgType(reqPkg.getMsgType());	//设置消息类型
		rspPkg.setVersion(reqPkg.getVersion());//设置版本
		
		try {
			reqPkg.parseFormBytes(postData,reqPkg.getEncode());	//解析请求报文
			rspPkg = distributeReq(reqPkg,rspPkg);   //进行消息派发处理
			System.out.println(reqPkg.toPkgStr());
			System.out.println(rspPkg.toPkgStr());
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
	
	//客户端 调用1002接口
	@RequestMapping("searchOTwoV")
	public String searchOTwoV(HttpServletRequest request, HttpServletResponse response,String trxNo){
			
			Pkg1002 pkg1002 = new Pkg1002();
			pkg1002.setTrxNo(trxNo);
			
			String msgBody = JsonSerializer.serializer(pkg1002);
	
			PkgHeader reqPkg = new PkgHeader();
			PkgHeader rspPkg = new PkgHeader();
			reqPkg.setVersion("01");			//默认01
			reqPkg.setCustNo(companyCode);				//公司代码
			reqPkg.setEncode("01");			//01.UTF8 02.GBK
			reqPkg.setTrxCode("1002");			//报文编号 默认四位 例:0001
			reqPkg.setEncryptType("01");	//加密类型 01.不加密 02.RSA
			reqPkg.setMsgType("01");			//01.JSON 02.XML 03.Protobuf
			reqPkg.setMsgBody(msgBody);			//报文主体
			reqPkg.setSign(sign);
			
			String url = urlPath;	//数据服务地址
			CloseableHttpClient httpclient = HttpClientUtils.getCloseableHttpClient();
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
			    
			    if(rspPkg.getRetCode().equals("0000"))
			    {
				    Pkg2002 pkg2002 = (Pkg2002) JsonSerializer.deserializer(rspPkg.getMsgBody(),new TypeReference<Pkg2002>(){});
				    List<LoanInfo> loanInfos = pkg2002.getLoanInfos();
				    request.setAttribute("loanInfos", loanInfos);
			    }
			    else
			    {
			    	System.out.println("响应消息:" + rspPkg.getRetMsg());
			    }
			  
			    responses.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return "showData";
	}
	
	//客户端 调用1003接口
	@RequestMapping("searchOThreeV")
	public String searchOThreeV(HttpServletRequest request, HttpServletResponse response,String realName,String idCard){
			Pkg1001 pkg1003 = new Pkg1001();
			pkg1003.setIdCard(idCard);
			pkg1003.setRealName(realName);
			
			String msgBody = JsonSerializer.serializer(pkg1003);
			
			PkgHeader reqPkg = new PkgHeader();
			PkgHeader rspPkg = new PkgHeader();
			reqPkg.setVersion("01");			//默认01
			reqPkg.setCustNo(companyCode);				//公司代码
			reqPkg.setEncode("01");			//01.UTF8 02.GBK
			reqPkg.setTrxCode("1003");			//报文编号 默认四位 例:0001
			reqPkg.setEncryptType("01");	//加密类型 01.不加密 02.RSA
			reqPkg.setMsgType("01");			//01.JSON 02.XML 03.Protobuf
			reqPkg.setMsgBody(msgBody);			//报文主体
			reqPkg.setSign(sign);
			
			String url = urlPath;	//数据服务地址
			CloseableHttpClient httpclient = HttpClientUtils.getCloseableHttpClient();
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
			    
			    if(rspPkg.getRetCode().equals("0000"))
			    {
				    System.out.println("响应消息:" + rspPkg.getRetMsg());
				    
				    Pkg3002 pkg2003 = (Pkg3002) JsonSerializer.deserializer(rspPkg.getMsgBody(),new TypeReference<Pkg3002>(){});
				    
				    System.out.println("查询交易代码:" + pkg2003.getTrxNo());
				    List<LoanInfo> loanInfos = pkg2003.getLoanInfos();
				    request.setAttribute("loanInfos",loanInfos);
			    }
			    else
			    {
			    	System.out.println("响应消息:" + rspPkg.getRetMsg());
			    	
			    }
			  
			    responses.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "showData";
	}
	
	//人人催黑名单
	@RequestMapping("searchBlackList")
	@ResponseBody
	public void searchBlackList(HttpServletRequest request, HttpServletResponse response,@RequestParam(defaultValue="徐志嵩")String realName
			,@RequestParam(defaultValue="310107198302072552")String idCard){
		HttpSession session = request.getSession();
		session.getId();
		String url = "http://service.91zhengxin.com/jyzx/blackList/searchLJO.do?realName=" + realName + "&idCard=" + idCard;
		CloseableHttpClient httpclient = HttpClientUtils.getCloseableHttpClient();
		HttpGet get = new HttpGet(url);

		get.setHeader("COMPANY_CODE", "P2PN9286HPO35JYDF");
		get.setHeader("CONN_KEY", "a7b73a65d07a4dc9bcb96a77764c2940");//小写  A7B73A65D07A4DC9BCB96A77764C2940
		get.setHeader("SIGN", MD5Utils.MD5(realName + ":" + idCard));
	
		CloseableHttpResponse responses;
		String result = "";
		try {
			responses = httpclient.execute(get);
			HttpEntity rspEntity = responses.getEntity();
			
			JSONObject jsonResult = null;
			
			if (rspEntity != null) {
				result = EntityUtils.toString(rspEntity);
				System.out.println(result);
				jsonResult=new JSONObject(result);
			}
			System.out.println(result);
			
			BlackListInfo  blackListInfo= new BlackListInfo();
			if(jsonResult!=null){
				blackListInfo.setDataCount((Integer) jsonResult.get("dataCount"));
				blackListInfo.setResult((boolean) jsonResult.get("result"));
				blackListInfo.setMsg((String) jsonResult.get("msg"));
				String dataStr = (String) jsonResult.get("datas").toString();
				
				Datas datas = new Datas();
				
				if(dataStr!=null){
					JSONObject dataJson = new JSONObject(dataStr);
					
					datas.setTrxNo((String) dataJson.get("trxNo"));
					JSONArray detailList = (JSONArray) dataJson.get("details");
					List<Details>  details= new ArrayList<Details>(); 
					if(detailList.length()>0){
						for (int i = 0; i < detailList.length(); i++) {
							JSONObject jsonOb = new JSONObject(detailList.get(i).toString());
							Details detail = new Details();
							detail.setBlackList((boolean) jsonOb.get("isBlackList"));
							detail.setLevel((String) jsonOb.get("level"));
							detail.setUpdateDate((String) jsonOb.get("updateDate"));
							details.add(detail);
						}
						datas.setDetails(details);
					}
					blackListInfo.setDatas(datas);
					System.out.println(blackListInfo.getMsg());
				}
			}
			
			responses.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("webSocketV")
	public String webSocketV(HttpServletRequest request, HttpServletResponse response) {
		return "webSocket";
	}
	
	@RequestMapping("indexV")
	public String indexV(HttpServletRequest request, HttpServletResponse response) {

		//添加cookies
//		Cookie namecookie = new Cookie("name","lihongxinag");     
//		Cookie passwordcookie = new Cookie("password","123456");   
//		
//		namecookie.setMaxAge(60*60*24*365);     
//		passwordcookie.setMaxAge(60*60*24*365);  
//		
////		namecookie.setDomain("www.***.com");  
////		passwordcookie.setDomain("www.***.com");  
//		
//		response.addCookie(namecookie);     
//		response.addCookie(passwordcookie); 
		
		//读取cookies
		Cookie[] cookies = request.getCookies();     
		if(cookies!=null)     
		{     
		    for (int i = 0; i < cookies.length; i++)      
		    {     
		       Cookie c = cookies[i];   
		       System.out.println("我看看你是啥："+c.getName()+"--"+c.getValue());
		       if(c.getName().equalsIgnoreCase("name"))     
		       {     
		          System.out.println("姓名："+c.getValue());
		          //删除cookies
		          c.setMaxAge(0);
		          response.addCookie(c);
		        }     
		        else if(c.getName().equalsIgnoreCase("password"))     
		        {     
		          System.out.println("密码："+c.getValue());
		          c.setMaxAge(0);
		          response.addCookie(c);
		        }
		       
		    }      
		  } 
		
		return "index";
	}
	
}

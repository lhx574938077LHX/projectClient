package com.xiaocui.webservice.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.jws.WebService;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.xiaocui.common.HttpClientUtils;
import com.xiaocui.common.JsonSerializer;
import com.xiaocui.entity.LoanInfo;
import com.xiaocui.entity.Pkg1001;
import com.xiaocui.entity.Pkg1002;
import com.xiaocui.entity.Pkg2001;
import com.xiaocui.entity.Pkg2002;
import com.xiaocui.entity.PkgHeader;
import com.xiaocui.webservice.SearchService;

@WebService(endpointInterface = "com.xiaocui.webservice.SearchService")
public class SearchServiceImpl implements SearchService{
	
	static ExecutorService pool = Executors.newFixedThreadPool(3); 
	
	@Override
	public List<LoanInfo> searchCustInfo(String realName, String idCard,String companyCode, String sign) {
		List<LoanInfo> loanInfos = new ArrayList<LoanInfo>();
		try {
			String trxNo = getTrxNo(realName, idCard, companyCode, sign);
			
			if(!"".equals(trxNo)){
				Thread.sleep(5*1000);
				loanInfos = getLoanInfos(trxNo, companyCode, sign);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return loanInfos;
	}
	
	
	//得到查询编号
	public static String getTrxNo(String realName, String idCard,String companyCode, String sign){
		String trxNo = "";

		Pkg1001 pkg1001 = new Pkg1001();
		pkg1001.setIdCard(idCard);
		pkg1001.setRealName(realName);
		
		String msgBody = JsonSerializer.serializer(pkg1001);
		
		PkgHeader reqPkg = new PkgHeader();
		PkgHeader rspPkg = new PkgHeader();
		reqPkg.setVersion("01");			//默认01
		reqPkg.setCustNo(companyCode);				//公司代码
		reqPkg.setEncode("01");			//01.UTF8 02.GBK
		reqPkg.setTrxCode("1001");			//报文编号 默认四位 例:0001
		reqPkg.setEncryptType("01");	//加密类型 01.不加密 02.RSA
		reqPkg.setMsgType("01");			//01.JSON 02.XML 03.Protobuf
		reqPkg.setMsgBody(msgBody);			//报文主体
		reqPkg.setSign(sign);
		
		String url = "http://114.113.101.219:8801/xcif/zxservice.do";	//数据服务地址
		CloseableHttpClient httpclient = HttpClientUtils.getCloseableHttpClient();
		HttpPost post = new HttpPost(url);

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
//		    System.out.println(result);
		    
		    rspPkg.parseFromString(result); //解析报文头
		    
		    if(rspPkg.getRetCode().equals("0000"))
		    {
			    System.out.println("响应消息:" + rspPkg.getRetMsg());
			    
			    Pkg2001 pkg2001 = (Pkg2001) JsonSerializer.deserializer(rspPkg.getMsgBody(),new TypeReference<Pkg2001>(){});
			    
			    trxNo = pkg2001.getTrxNo();
			    
		    }
		    else
		    {
		    	System.out.println("响应消息:" + rspPkg.getRetMsg());
		    }
		  
		    responses.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return trxNo;
	}
	
	public static List<LoanInfo> getLoanInfos(String trxNo,String companyCode,String sign){
		List<LoanInfo> loanInfos = new ArrayList<LoanInfo>();

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
		
		String url = "http://114.113.101.219:8801/xcif/zxservice.do";	//数据服务地址
		CloseableHttpClient httpclient = HttpClientUtils.getCloseableHttpClient();
		HttpPost post = new HttpPost(url);

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
//		    System.out.println(result);
		    
		    rspPkg.parseFromString(result); //解析报文头
		    
		    if(rspPkg.getRetCode().equals("0000"))
		    {
			    System.out.println("响应消息:" + rspPkg.getRetMsg());
			    
			    Pkg2002 pkg2002 = (Pkg2002) JsonSerializer.deserializer(rspPkg.getMsgBody(),new TypeReference<Pkg2002>(){});

		    	loanInfos = pkg2002.getLoanInfos();
			    
		    }
		    else
		    {
		    	System.out.println("响应消息:" + rspPkg.getRetMsg());
		    	
		    }
		  
		    responses.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return loanInfos;
	}


}

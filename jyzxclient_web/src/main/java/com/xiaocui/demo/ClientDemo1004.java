package com.xiaocui.demo;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;

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

import com.fasterxml.jackson.core.type.TypeReference;
import com.xiaocui.common.HttpClientUtils;
import com.xiaocui.common.JsonSerializer;
import com.xiaocui.entity.Pkg1001;
import com.xiaocui.entity.Pkg2001;
import com.xiaocui.entity.Pkg2004;
import com.xiaocui.entity.PkgHeader;

public class ClientDemo1004 {
//	private static final String companyCode = "P2P4HJK0000100010";	//请以配置文件形式或储存到数据库形式保存获取
	private static final String companyCode = "P2PWA57XKD8VRO4OG";	//请以配置文件形式或储存到数据库形式保存获取
	
	
	public static void main(String[] args) {
		System.out.println(System.currentTimeMillis());
			
		PkgHeader reqPkg = new PkgHeader();
		PkgHeader rspPkg = new PkgHeader();
		reqPkg.setVersion("01");			//默认01
		reqPkg.setCustNo(companyCode);				//公司代码
		reqPkg.setEncode("01");			//01.UTF8 02.GBK
		reqPkg.setTrxCode("1004");			//报文编号 默认四位 例:0001
		reqPkg.setEncryptType("01");	//加密类型 01.不加密 02.RSA
		reqPkg.setMsgType("01");			//01.JSON 02.XML 03.Protobuf
		reqPkg.setMsgBody("");			//报文主体
//		reqPkg.setSign("5a59392b2f3c0f13f56cbbcfccfff25b");
		reqPkg.setSign("8908077FA43F4CD79D6D515624D06288");
		
		String pkgStr = reqPkg.toPkgStr();
		PkgHeader pkgHeader = new PkgHeader();
		pkgHeader.parseFromString(pkgStr);
		
		
//		String url = "http://s1.91zhengxin.com/jyzx/zxservice.do";	//数据服务地址
		String url = "http://172.19.3.84:8080/credit91-web-portal/zxservice.do";	//数据服务地址
//		String url = "http://localhost:8080/credit91-web-portal/zxservice.do";	//数据服务地址
		CloseableHttpClient httpclient = HttpClientUtils.getCloseableHttpClient();
		HttpPost post = new HttpPost(url);
		System.out.println("-----------------------");
		System.out.println(reqPkg.toPkgStr());   //输出请求报文
		System.out.println("-----------------------");
		ByteArrayEntity reqEntity = new ByteArrayEntity(reqPkg.toPkgBytes("UTF-8"));
		post.setEntity(reqEntity);
		
		CloseableHttpResponse response;
		String result = "";
		try {
			response = httpclient.execute(post);
			HttpEntity rspEntity = response.getEntity();

		    if (rspEntity != null) {
		    	result = EntityUtils.toString(rspEntity);
		    }
		    //输出返回信息
		    System.out.println(result);
		    
		    rspPkg.parseFromString(result); //解析报文头
		    
		    if(rspPkg.getRetCode().equals("0000"))
		    {
				System.out.println(System.currentTimeMillis());
			    System.out.println("响应消息:" + rspPkg.getRetMsg());
			    Pkg2004 pkg2004 = (Pkg2004) JsonSerializer.deserializer(rspPkg.getMsgBody(),new TypeReference<Pkg2004>(){});
			    System.out.println("积分:" + pkg2004.getIntegralNum());
		    }
		    else
		    {
		    	System.out.println("响应消息:" + rspPkg.getRetMsg());
		    }
		  
		    response.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}


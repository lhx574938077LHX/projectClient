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
import com.xiaocui.common.JsonSerializer;
import com.xiaocui.entity.Pkg1001;
import com.xiaocui.entity.Pkg3002;
import com.xiaocui.entity.PkgHeader;

public class ClientDemo1003 {
//	private static final String companyCode = "P2P4HJK0000101101";	//正式
	private static final String companyCode = "P2P4HJK0000100010";	//测试
	
	public static void main(String[] args) {
		Pkg1001 pkg1001 = new Pkg1001();
		pkg1001.setIdCard("422422196805304755");
		pkg1001.setRealName("李云华");
		String msgBody = JsonSerializer.serializer(pkg1001);
		
		PkgHeader reqPkg = new PkgHeader();
		PkgHeader rspPkg = new PkgHeader();
		reqPkg.setVersion("01");			//默认01
		reqPkg.setCustNo(companyCode);				//公司代码
		reqPkg.setEncode("01");			//01.UTF8 02.GBK
		reqPkg.setTrxCode("1003");			//报文编号 默认四位 例:0001
		reqPkg.setEncryptType("01");	//加密类型 01.不加密 02.RSA
		reqPkg.setMsgType("01");			//01.JSON 02.XML 03.Protobuf
		reqPkg.setMsgBody(msgBody);			//报文主体
//		reqPkg.setSign("42c036b481339f2ef80504a020be1805");//正式
		reqPkg.setSign("5a59392b2f3c0f13f56cbbcfccfff25b");//测试
		
		String pkgStr = reqPkg.toPkgStr();
		PkgHeader pkgHeader = new PkgHeader();
		pkgHeader.parseFromString(pkgStr);
		
//		String url = "http://service.91zhengxin.com/jyzx/zxservice.do";	//数据服务地址
		String url = "http://localhost:9080/jyzx/zxservice.do";	//数据服务地址
		CloseableHttpClient httpclient = createSSLClientDefault();
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
			    System.out.println("响应消息:" + rspPkg.getRetMsg());
			    Pkg3002 pkg3002 = (Pkg3002) JsonSerializer.deserializer(rspPkg.getMsgBody(),new TypeReference<Pkg3002>(){});
			    System.out.println("查询交易代码:" + pkg3002.getTrxNo());
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
	
	
	private  static CloseableHttpClient createSSLClientDefault(){
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
}


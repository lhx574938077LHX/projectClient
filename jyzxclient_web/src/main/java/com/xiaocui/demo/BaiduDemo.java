package com.xiaocui.demo;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.UUID;

import javax.net.ssl.SSLContext;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;

import com.xiaocui.common.JsonSerializer;
import com.xiaocui.entity.BaiduRequest;

public class BaiduDemo {
	public static void main(String[] args) throws Exception {
		BaiduRequest baiduRequest = new BaiduRequest();
		baiduRequest.setEventType("black_list");
//		baiduRequest.setSourceName("sourceNameTest");
		baiduRequest.setSourceName("91zhengxin");
		baiduRequest.setReqId(UUID.randomUUID().toString());
		baiduRequest.setSignType("1");
		baiduRequest.setSign("");
		baiduRequest.setAgencyCode("91zhengxin");
//		baiduRequest.setTimestamp(DateUtils.dateToStr(new Date(),5));
		baiduRequest.setTimestamp(String.valueOf(new Date().getTime()));
		baiduRequest.setPrcid("320483199006162528");
		baiduRequest.setName("左敏娜");
		baiduRequest.setPhoneNumber("13614090708");
		
		String str = JsonSerializer.serializer(baiduRequest);

//		baiduRequest.setSign(DigestUtils.md5Hex(str +"verificationCode=verificationCodeTest"));
		baiduRequest.setSign(DigestUtils.md5Hex(str +"verificationCode=9d8b41cea7d4a24da61479c044c4a9b5"));
		str = JsonSerializer.serializer(baiduRequest);
		
		String url = "http://180.149.145.95:8010/credit/api";	//正式地址
//		String url = "http://180.149.144.184:8090/credit/api";	//测试地址
		CloseableHttpClient httpclient = createSSLClientDefault();
		System.out.println("发送请求："+str);
		HttpPost post = new HttpPost(url);
		post.setEntity(new StringEntity(str,"utf-8"));
		
		CloseableHttpResponse response;
		String result = "";
		try {
			response = httpclient.execute(post);
			int retCode = response.getStatusLine().getStatusCode();
			if(retCode!=200)
				System.out.println("反馈请求状态码："+retCode);
			HttpEntity rspEntity = response.getEntity();

		    if (rspEntity != null) {
		    	result = EntityUtils.toString(rspEntity);
		    }
		    //输出返回信息
		    System.out.println(result);
		    //输出返回信息
		    System.out.println("成功结束！");
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

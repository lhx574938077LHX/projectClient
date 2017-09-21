package com.xiaocui.service;

import java.util.Date;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.xiaocui.common.HttpClientUtils;
import com.xiaocui.common.JsonSerializer;
import com.xiaocui.entity.BaiduEntity;
import com.xiaocui.entity.BaiduRequest;

public class blackTask implements Runnable {
	
	String realName;
	String idCard;
	
	@Override
	public void run() {
		
		try {
			BaiduRequest baiduRequest = new BaiduRequest();
			baiduRequest.setEventType("black_list");
	//		baiduRequest.setSourceName("sourceNameTest");
			baiduRequest.setSourceName("91zhengxin");
			baiduRequest.setReqId(UUID.randomUUID().toString());
			baiduRequest.setSignType("1");
			baiduRequest.setSign("");
	//		baiduRequest.setAgencyCode("");
	//		baiduRequest.setTimestamp(DateUtils.dateToStr(new Date(),5));
			baiduRequest.setTimestamp(String.valueOf(new Date().getTime()));
			baiduRequest.setPrcid(idCard);
			baiduRequest.setName(realName);
			baiduRequest.setPhoneNumber("13614090708");
			
			String str = JsonSerializer.serializer(baiduRequest);
	
	//		baiduRequest.setSign(DigestUtils.md5Hex(str +"verificationCode=verificationCodeTest"));
			baiduRequest.setSign(DigestUtils.md5Hex(str +"verificationCode=9d8b41cea7d4a24da61479c044c4a9b5"));
			str = JsonSerializer.serializer(baiduRequest);
			
			String url = "http://180.149.145.95:8010/credit/api";	//数据服务地址
			CloseableHttpClient httpclient = HttpClientUtils.getCloseableHttpClient();
			System.out.println("发送请求："+str);
			HttpPost post = new HttpPost(url);
			post.setEntity(new StringEntity(str,"utf-8"));
			
			CloseableHttpResponse response;
			String result = "";

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
		    BaiduEntity baidu = (BaiduEntity) JsonSerializer.deserializer(result,new TypeReference<BaiduEntity>(){});
		    
		    //输出返回信息
		    System.out.println("成功结束！");
		    response.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	
	
	
}

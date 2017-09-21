package com.xiaocui.demo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.xiaocui.common.HttpClientUtils;

public class TestDemo {
	
	public static void main(String[] args) throws Exception {
		String url = "http://localhost:9080/credit91-web-manager/crmSta/getIntegralStatistics.do";
		CloseableHttpClient httpclient = HttpClientUtils.getCloseableHttpClient();
        List<NameValuePair> params = new ArrayList<>();
//        String paramStr = EntityUtils.toString(new UrlEncodedFormEntity(params, "UTF-8"));
//		HttpPost post = new HttpPost(url+"?"+paramStr);
		HttpPost post = new HttpPost(url);
		post.setHeader("CONN_KEY", "42c036b481339f2ef80504a020be1805");
		CloseableHttpResponse response;

		response = httpclient.execute(post);
		HttpEntity rspEntity = response.getEntity();
		String result = "";
	    if (rspEntity != null) {
	    	result = EntityUtils.toString(rspEntity);
	    }
	    System.out.println(result);
	    System.out.println("完成");
	    response.close();
	}
	
}

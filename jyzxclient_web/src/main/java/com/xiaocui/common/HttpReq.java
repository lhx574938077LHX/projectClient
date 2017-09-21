package com.xiaocui.common;


import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreProtocolPNames;


public class HttpReq {
	/**
	 * 发起http请求信息
	 * @param surl
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public static String sendJsonWithHttps(String surl, UrlEncodedFormEntity entity) throws Exception {
        HttpClient client = new DefaultHttpClient();
//        client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,2000);	//链接超时
//        client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,2000);	//读取超时
        client.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION,HttpVersion.HTTP_1_1);
        HttpPost post = new HttpPost(surl);
        post.setEntity(entity);
        HttpResponse res = client.execute(post);
		String result = IOUtils.toString(res.getEntity().getContent(), "utf-8");
		return result;
    }
	
	/**
	 * 请求中携带的参数
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static UrlEncodedFormEntity paramInfo(String key1, String val1, String key2, String val2, String key3, String val3) throws UnsupportedEncodingException{
		List<NameValuePair> params= new ArrayList<NameValuePair>();
        params.add( new BasicNameValuePair(key1, val1));
        params.add( new BasicNameValuePair(key2, val2));
        params.add( new BasicNameValuePair(key3, val3));
        UrlEncodedFormEntity entity= new UrlEncodedFormEntity(params,"utf-8" );
        return entity;
	}

}

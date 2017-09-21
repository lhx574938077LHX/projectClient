package com.xiaocui.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import com.xiaocui.common.HttpClientUtils;
import com.xiaocui.controller.IndexController;

public class InsertIndexMd5Task implements Runnable {
	
	private Lock lock = new ReentrantLock();
	private String companyCode;
	private String companyId;
	private Short isOnline;
	private String idCardMd5;
	
	
	public String getIdCardMd5() {
		return idCardMd5;
	}
	public void setIdCardMd5(String idCardMd5) {
		this.idCardMd5 = idCardMd5;
	}
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public Short getIsOnline() {
		return isOnline;
	}
	public void setIsOnline(Short isOnline) {
		this.isOnline = isOnline;
	}
	public Lock getLock() {
		return lock;
	}
	public void setLock(Lock lock) {
		this.lock = lock;
	}
	
	@Override
	public void run() {
		CloseableHttpClient httpclient = HttpClientUtils.getCloseableHttpClient();
		
		String url = "http://s1.91zhengxin.com/jyif/manager/addSearchIndexLJO.do?&md5Index="+idCardMd5.toUpperCase()+"&companyId="+companyId+"&companyCode="+companyCode+"&isOnline="+isOnline;
		HttpPost post = new HttpPost(url);

		try {
			post.setHeader("CONN_KEY", "781aed21d7a4b3a17e6a62bb5464af68");//小写 
			CloseableHttpResponse response = httpclient.execute(post);
			HttpEntity rspEntity = response.getEntity();
			String result = "";
			if (rspEntity != null) {
		    	result = EntityUtils.toString(rspEntity);
		    }
			System.out.println(result);
			response.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

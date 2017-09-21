package com.xiaocui.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import com.xiaocui.common.HttpClientUtils;

@Component
public class InsertIndexTask  implements Runnable {
	
	private String realName;
	private String idCard;
	private String companyCode;
	private String companyId;
	private Short isOnline;
	private Lock lock = new ReentrantLock();

	public Lock getLock() {
		return lock;
	}
	public void setLock(Lock lock) {
		this.lock = lock;
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

	@Override
	public void run() {
		if(lock.tryLock()){
			CloseableHttpClient httpclient = HttpClientUtils.getCloseableHttpClient();
			
			String url = "http://s1.91zhengxin.com/jyif/manager/addSearchIndexLJO.do?realName="+realName+"&idCard="+idCard
					+"&companyId="+companyId+"&companyCode="+companyCode+"&isOnline="+isOnline;
//			String url = "http://service.91zhengxin.com/manager/addSearchIndexLJO.do";
			HttpPost post = new HttpPost(url);
//			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
//			nvps.add(new BasicNameValuePair("realName", realName));
//			nvps.add(new BasicNameValuePair("idCard", idCard));
//			nvps.add(new BasicNameValuePair("md5Index", md5Index));
//			nvps.add(new BasicNameValuePair("companyId", companyId));
//			nvps.add(new BasicNameValuePair("companyCode", companyCode));
//			nvps.add(new BasicNameValuePair("isOnline", isOnline.toString()));
			try {
//				post.setEntity(new UrlEncodedFormEntity(nvps));
				post.setHeader("CONN_KEY", "781aed21d7a4b3a17e6a62bb5464af68");//小写 
				CloseableHttpResponse response = httpclient.execute(post);
				HttpEntity rspEntity = response.getEntity();
				String result = "";
				if (rspEntity != null) {
			    	result = EntityUtils.toString(rspEntity);
			    }
				System.out.println(realName+"---"+idCard+"---"+result);
				response.close();
			    //输出返回信息
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}

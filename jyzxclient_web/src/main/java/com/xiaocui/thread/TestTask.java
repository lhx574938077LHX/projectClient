package com.xiaocui.thread;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;

import com.xiaocui.common.HttpClientUtils;
import com.xiaocui.common.JsonSerializer;
import com.xiaocui.entity.Pkg1001;
import com.xiaocui.entity.PkgHeader;

public class TestTask implements Runnable {
	private String realName;
	private String idCard;
	
	@Override
	public void run() {
		Pkg1001 pkg1001 = new Pkg1001();
		pkg1001.setIdCard(this.realName);
		pkg1001.setRealName(this.idCard);
		System.out.println(System.currentTimeMillis());
		String msgBody = JsonSerializer.serializer(pkg1001);
		
		PkgHeader reqPkg = new PkgHeader();
		reqPkg.setVersion("01");			//默认01
		reqPkg.setCustNo("P2P4HJK0000100010");//公司代码
		reqPkg.setEncode("01");			//01.UTF8 02.GBK
		reqPkg.setTrxCode("1001");			//报文编号 默认四位 例:0001
		reqPkg.setEncryptType("01");	//加密类型 01.不加密 02.RSA
		reqPkg.setMsgType("01");			//01.JSON 02.XML 03.Protobuf
		reqPkg.setMsgBody(msgBody);			//报文主体
		reqPkg.setSign("5a59392b2f3c0f13f56cbbcfccfff25b");
		
		String pkgStr = reqPkg.toPkgStr();
		PkgHeader pkgHeader = new PkgHeader();
		pkgHeader.parseFromString(pkgStr);
		
		String url = "http://localhost:8080/91zhengxin/zxservice.do";	//数据服务地址
		CloseableHttpClient httpclient = HttpClientUtils.getCloseableHttpClient();
		HttpPost post = new HttpPost(url);
		System.out.println(realName+"请求报文："+reqPkg.toPkgStr());   //输出请求报文
		ByteArrayEntity reqEntity = new ByteArrayEntity(reqPkg.toPkgBytes("UTF-8"));
		post.setEntity(reqEntity);
		
		try {
			 httpclient.execute(post);
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

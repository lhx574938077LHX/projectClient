package com.xiaocui.service;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.xiaocui.common.JsonSerializer;
import com.xiaocui.entity.LoanInfo;
import com.xiaocui.entity.Pkg1001;
import com.xiaocui.entity.Pkg3002;
import com.xiaocui.entity.PkgHeader;
import com.xiaocui.platform.dao.search.TestDataResultMapper;
import com.xiaocui.platform.model.data.JyzxJdxxcxfk;
import com.xiaocui.platform.model.search.TestDataResult;

@Component
public class SearchTask  implements Runnable{
	
	private TestDataResultMapper testDataResultMapper;
	
	private String realName;
	private String idCard;
	private String companyCode;
	private Pkg3002 pkg2003;
	private String sign; 
	
	private Lock lock = new ReentrantLock();
	
	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
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
	
	public Pkg3002 getPkg2003() {
		return pkg2003;
	}

	public void setPkg2003(Pkg3002 pkg2003) {
		this.pkg2003 = pkg2003;
	}

	@Override
	public void run() {
		if(lock.tryLock()){
			Pkg1001 pkg1003 = new Pkg1001();
			pkg1003.setIdCard(this.idCard);
			pkg1003.setRealName(this.realName);
			
			String msgBody = JsonSerializer.serializer(pkg1003);
			
			PkgHeader reqPkg = new PkgHeader();
			PkgHeader rspPkg = new PkgHeader();
			reqPkg.setVersion("01");			//默认01
			reqPkg.setCustNo(this.companyCode);				//公司代码
			reqPkg.setEncode("01");			//01.UTF8 02.GBK
			reqPkg.setTrxCode("1003");			//报文编号 默认四位 例:0001
			reqPkg.setEncryptType("01");	//加密类型 01.不加密 02.RSA
			reqPkg.setMsgType("01");			//01.JSON 02.XML 03.Protobuf
			reqPkg.setMsgBody(msgBody);			//报文主体
			reqPkg.setSign(this.sign);
			
//			String url = "http://114.113.101.218/jyzx/zxservice.do";	//数据服务地址
			String url = "http://114.113.101.219:8801/xcif/zxservice.do";	//数据服务地址
			
			CloseableHttpClient httpclient = createSSLClientDefault();
			HttpPost post = new HttpPost(url);
			System.out.println("-----------------------");
			System.out.println(reqPkg.toPkgStr());   //输出请求报文
			System.out.println("-----------------------");
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
			    System.out.println(result);
			    
			    rspPkg.parseFromString(result); //解析报文头
			    
			    if(rspPkg.getRetCode().equals("0000"))
			    {
				    System.out.println("响应消息:" + rspPkg.getRetMsg());
				    
				    Pkg3002 pkg2003 = (Pkg3002) JsonSerializer.deserializer(rspPkg.getMsgBody(),new TypeReference<Pkg3002>(){});
				    System.out.println("查询交易代码:" + pkg2003.getTrxNo());
					
				    saveTestDataResult(pkg2003, realName, idCard);
			    }
			    else
			    {
			    	System.out.println("响应消息:" + rspPkg.getRetMsg());
			    }
			  
			    responses.close();
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				lock.unlock();
			}
		}
	}
	
	
	public void saveTestDataResult(Pkg3002 pkg2003,String realName,String idCard){
		if(pkg2003.getLoanInfos().size()>0){
			 for(LoanInfo loanInfo : pkg2003.getLoanInfos())
				{
				 	TestDataResult testDataResult = new TestDataResult();
				    //查询编码
				 	testDataResult.setTrxNo(pkg2003.getTrxNo());
				    //用户姓名
				 	testDataResult.setRealName(realName);
				    //身份证号
				 	testDataResult.setIdcard(idCard);
				    //公司编码
				 	testDataResult.setCompanyCode(loanInfo.getCompanyCode());
				    //借款类型 0.未知1.个人信贷2.个人抵押3.企业信贷4.企业抵押
				 	testDataResult.setBorrowtYpe(loanInfo.getBorrowType());
				    //借款状态 0.未知1.拒贷2.批贷已放款3.批贷未放款4.借款人放弃申请5.审核中6.待放款
				 	testDataResult.setBorrowState(loanInfo.getBorrowState());
				    //借款金额 -7.[0,0.1) -6.[0.1,0.2) -5.[0.2,0.3) -4.[0.3,0.4) -3.[0.4,0.6) -2.[0.6,0.8) -1.[0.8,1) 1.[1,2) 2.[2,4) 3.[4,6) 4.[6,8)…….
				 	testDataResult.setBorrowAmount(loanInfo.getBorrowAmount());
				    //合同日期
				 	testDataResult.setContractDate(loanInfo.getContractDate().getTime());
				    //批贷期数
				 	testDataResult.setLoanPeriod(loanInfo.getLoanPeriod());
				    //还款状态 0.未知1.正常2.M1 3.M2 4.M3 5.M4 6.M5 7.M6 8.M6+
				 	testDataResult.setRepayState(loanInfo.getRepayState());
				    //欠款金额
				 	testDataResult.setArrearsAmount(loanInfo.getArrearsAmount());
				    //记录创建时间
				 	testDataResult.setRecTime(System.currentTimeMillis());
					
					testDataResultMapper.insertSelective(testDataResult);
				}
		}
	}
	
	protected static CloseableHttpClient createSSLClientDefault(){
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

	public TestDataResultMapper getTestDataResultMapper() {
		return testDataResultMapper;
	}

	public void setTestDataResultMapper(TestDataResultMapper testDataResultMapper) {
		this.testDataResultMapper = testDataResultMapper;
	}
	
}

package com.xiaocui.controller;

import java.io.IOException;
import java.nio.charset.Charset;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.type.TypeReference;
import com.xiaocui.common.ByteUtils;
import com.xiaocui.common.Credit91CallbackEnum;
import com.xiaocui.common.Pkg1001;
import com.xiaocui.common.Pkg2001;
import com.xiaocui.common.PkgHeader;
import com.xiaocui.dao.model.CompanyInfoBean;
import com.xiaocui.platform.utils.CertHelper;
import com.xiaocui.platform.utils.HttpClientUtils;
import com.xiaocui.platform.utils.JsonSerializer;
import com.xiaocui.service.jyzxServiceImpl;

@Controller
@RequestMapping("/")
public class jyzxController {

    private static Logger logger = Logger.getLogger(jyzxController.class);
	
    @Autowired
    private jyzxServiceImpl jyzxService;
    
    
	@ResponseBody
    @RequestMapping("zxservice")
    public void zxservice(HttpServletRequest request, HttpServletResponse response, @RequestParam(defaultValue = "utf-8") String encode) {
        PkgHeader rspHeader = new PkgHeader();
        String result = "";
        PkgHeader reqHeader = null;
        try {
            byte[] postData = ByteUtils.getByteArrayFromInputStream(request.getInputStream());
            String postStr = new String(postData, Charset.forName("UTF-8"));
            String[] postDataStr = org.apache.commons.lang3.StringUtils.splitPreserveAllTokens(postStr, "|");
            if (postDataStr[4].equals("02")) {
                String companyCode = postDataStr[1];
                //获取服务器私钥
                CompanyInfoBean companyInfo = jyzxService.selectCompany(companyCode);
                if (companyInfo == null) {
                    //公司不存在
                    rspHeader.setRetMsg("非法请求");
                    rspHeader.setRetCode("9999");
                    result = rspHeader.toPkgStr(encode);
                    response.getWriter().write(result);
                    return;
                }
                String publicKeyS = companyInfo.getPublicKeyS();
                String privateKeyS = companyInfo.getPrivateKeyS();

                String privateKeyP = companyInfo.getPrivateKeyP();
                String publicKeyP = companyInfo.getPublicKeyP();
                try {
                    RSAPublicKey rsaPublicKey = CertHelper.loadPublicKeyByStr(publicKeyS);
                    RSAPrivateKey rsaPrivateKey = CertHelper.loadPrivateKeyByStr(privateKeyS);

                    RSAPublicKey rsaPublicKey1 = CertHelper.loadPublicKeyByStr(publicKeyP);
                    RSAPrivateKey rsaPrivateKey1 = CertHelper.loadPrivateKeyByStr(privateKeyP);

                    reqHeader = new PkgHeader(rsaPrivateKey, rsaPublicKey, "S");
                    rspHeader = new PkgHeader(rsaPrivateKey1, rsaPublicKey1, "S");
                } catch (Exception e) {
                    rspHeader.setRetCode("9999");
                    rspHeader.setRetMsg("非法请求");
                    result = rspHeader.toPkgStr(encode);
                    response.getWriter().write(result);
                    return;
                }
            } else {
                reqHeader = new PkgHeader();
            }
            reqHeader.parseFormBytes(postData, "UTF-8");

            distributeReq(reqHeader, rspHeader);
            result = rspHeader.toPkgStr(encode);

            response.getWriter().write(result);
            
        } catch (IOException | ArrayIndexOutOfBoundsException e) {
            rspHeader.setRetCode("9999");
            rspHeader.setRetMsg("系统发生异常查询请求失败");
            rspHeader.setMsgBody("");
            result = rspHeader.toPkgStr(encode);
            try {
                response.getWriter().write(result);
            } catch (IOException e1) {
            }
            logger.error("GET EXCEPTION", e);
        }finally{
        	if("0000".equals(rspHeader.getRetCode())&&rspHeader.getTrxCode().equals("2001")){
        		send3002(reqHeader, rspHeader);
        	}
        }
        
    }
	
    public void distributeReq(PkgHeader reqHeader, PkgHeader rspHeader) {
        rspHeader.setCompanyCode("SRV000000001");    //配置服务器编号
        rspHeader.setEncode(reqHeader.getEncode());
        rspHeader.setEncryptType(reqHeader.getEncryptType());
        rspHeader.setMsgType(reqHeader.getMsgType());
        rspHeader.setVersion(reqHeader.getVersion());

        String txnCode = reqHeader.getTrxCode();
        Credit91CallbackEnum credit91CallbackEnum = Credit91CallbackEnum.EXCEPTION;
        try {
            switch (txnCode) {
                //异步查询
                case "1001": {
                    logger.debug("PKG|COMPANY_CODE:" + reqHeader.getCompanyCode() + "|1001:" + reqHeader.toPkgStr());
                    credit91CallbackEnum = jyzxService.authority(reqHeader.getCompanyCode(), "1001", reqHeader.getConnKey());
                    if(credit91CallbackEnum == Credit91CallbackEnum.OK){
                    	jyzxService.asyncSearch(reqHeader, rspHeader);
                    } else {
                        rspHeader.setRetMsg(credit91CallbackEnum.getDescription());
                        rspHeader.setRetCode("9999");
                    }
                    rspHeader.setTrxCode("2001");
                    logger.debug("PKG|COMPANY_CODE:" + reqHeader.getCompanyCode() + "|2001:" + rspHeader.toPkgStr());
                }
                break;
                //结果查询
                case "1002": {
                    logger.debug("PKG|COMPANY_CODE:" + reqHeader.getCompanyCode() + "|1002:" + reqHeader.toPkgStr());
                    credit91CallbackEnum = jyzxService.authority(reqHeader.getCompanyCode(), "1002", reqHeader.getConnKey());
                    if(credit91CallbackEnum == Credit91CallbackEnum.OK){
                    	jyzxService.searchResult(reqHeader, rspHeader);
                    } else {
                        rspHeader.setRetMsg(credit91CallbackEnum.getDescription());
                        rspHeader.setRetCode("9999");
                    }
                    rspHeader.setTrxCode("2002");
                    logger.debug("PKG|COMPANY_CODE:" + reqHeader.getCompanyCode() + "|2002:" + rspHeader.toPkgStr());
                }
                break;
                //同步查询
                case "1003": {
                    logger.debug("PKG|COMPANY_CODE:" + reqHeader.getCompanyCode() + "|1003:" + reqHeader.toPkgStr());
                    credit91CallbackEnum = jyzxService.authority(reqHeader.getCompanyCode(), "1003", reqHeader.getConnKey());
                    if(credit91CallbackEnum == Credit91CallbackEnum.OK){
                    	jyzxService.syncSearch(reqHeader, rspHeader);
                    } else {
                        rspHeader.setRetMsg(credit91CallbackEnum.getDescription());
                        rspHeader.setRetCode("9999");
                    }
                    rspHeader.setTrxCode("2003");
                    logger.debug("PKG|COMPANY_CODE:" + reqHeader.getCompanyCode() + "|2003:" + rspHeader.toPkgStr());
                }
                break;
            }
        }  catch (Exception e) {
            logger.error("SEARCH EXCEPTION", e);
            rspHeader.setRetCode("9999");
            rspHeader.setRetMsg("系统发生异常查询请求失败");
            rspHeader.setMsgBody("");
        }
    }

    public void send3002(PkgHeader reqPkg, PkgHeader rspPkg) {
    	PkgHeader reqHeader = new PkgHeader();
	    PkgHeader rspHeader = new PkgHeader();
    	try{
        	Thread.sleep(2000L);
        	CompanyInfoBean companyInfo = jyzxService.selectCompany(reqPkg.getCompanyCode());
        	
            if (companyInfo.getP00003() == 2) {
                String publicKeyS = companyInfo.getPublicKeyS();
                String privateKeyS = companyInfo.getPrivateKeyS();

                String privateKeyP = companyInfo.getPrivateKeyP();
                String publicKeyP = companyInfo.getPublicKeyP();
                RSAPublicKey rsaPublicKey = CertHelper.loadPublicKeyByStr(publicKeyS);
                RSAPrivateKey rsaPrivateKey = CertHelper.loadPrivateKeyByStr(privateKeyS);
                rspPkg = new PkgHeader(rsaPrivateKey, rsaPublicKey, "S");

                RSAPublicKey rsaPublicKey1 = CertHelper.loadPublicKeyByStr(publicKeyP);
                RSAPrivateKey rsaPrivateKey1 = CertHelper.loadPrivateKeyByStr(privateKeyP);
                reqPkg = new PkgHeader(rsaPrivateKey1, rsaPublicKey1, "S");
                reqPkg.setEncryptType("02");
            } else {
                reqPkg = new PkgHeader();
                rspPkg = new PkgHeader();
                reqPkg.setEncryptType("01");
            }
            reqPkg.setEncode("01");
            reqPkg.setMsgType("01");
            reqPkg.setRetCode("");
            reqPkg.setRetMsg("");
            reqPkg.setTrxCode("3002");
            reqPkg.setVersion("01");
        	
            Pkg1001 pkg1001 = (Pkg1001) JsonSerializer.deserializer(reqPkg.getMsgBody(), new TypeReference<Pkg1001>(){});
        	Pkg2001 pkg2001 = (Pkg2001) JsonSerializer.deserializer(rspPkg.getMsgBody(),new TypeReference<Pkg2001>(){});
        	jyzxService.searchDatas(pkg1001,pkg2001,reqHeader);
        	logger.debug("PKG|COMPANY_CODE:" + companyInfo.getCompanyCode() + "|3002:" + reqHeader.toPkgStr());
        	
        	String url = companyInfo.getServiceUrl();	//公司回调地址
    		CloseableHttpClient httpclient = HttpClientUtils.getCloseableHttpClient();
    		HttpPost post = new HttpPost(url);
    		//设置超时
    		RequestConfig requestConfig = RequestConfig.custom()
    				.setConnectionRequestTimeout(10000) //连接池请求超时时间
    				.setSocketTimeout(10000)	//响应超时时间
    				.setConnectTimeout(10000)	//连接超时时间
    				.build();
    		post.setConfig(requestConfig);
    		
    		ByteArrayEntity reqEntity = new ByteArrayEntity(reqHeader.toPkgBytes("UTF-8"));
    		post.setEntity(reqEntity);		
    		
    		CloseableHttpResponse responses;
    		String result = "";
    		try {
    			responses = httpclient.execute(post);
    			System.out.println(responses.getStatusLine().getStatusCode());
    			if(responses.getStatusLine().getStatusCode()!=200){
    				jyzxService.updateSearchLog(pkg2001.getTrxNo(),false);
    				throw new Exception("HTTP返回代码有误:" + responses.getStatusLine().getStatusCode());
    			}
    			HttpEntity rspEntity = responses.getEntity();

    		    if (rspEntity != null) {
    		    	result = EntityUtils.toString(rspEntity);
    		    }
    		    
				rspHeader.parseFromString(result); //解析报文头
            	logger.debug("PKG|COMPANY_CODE:" + companyInfo.getCompanyCode() + "|4002:" + rspHeader.toPkgStr());
    		    
			    jyzxService.updateSearchLog(pkg2001.getTrxNo(),true);

    		} catch (Exception e) {
    			e.printStackTrace();
                logger.error("SEND EXCEPTION", e);
    		}
        	
    	}catch (Exception e) {
			e.printStackTrace();
            logger.error("PORTAL EXCEPTION", e);
		}
    }
    
    
    
}

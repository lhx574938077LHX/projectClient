package com.xiaocui.service;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.apache.log4j.chainsaw.Main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.xiaocui.common.BASE64Encoder;
import com.xiaocui.common.JsonSerializer;
import com.xiaocui.entity.LoanInfo;
import com.xiaocui.entity.Pkg3001;
import com.xiaocui.entity.Pkg4001;
import com.xiaocui.entity.PkgHeader;
import com.xiaocui.platform.dao.data.JyzxJdxxcxfkMapper;
import com.xiaocui.platform.dao.data.JyzxJdxxcxqqMapper;
import com.xiaocui.platform.dao.data.JyzxJdxxgxjkMapper;
import com.xiaocui.platform.model.data.JyzxJdxxgxjk;
import com.xiaocui.settings.SystemSetting;

@Service("task3001")
public class Task3001 implements ITask {
	
	private final static Logger logger = Logger.getLogger(Task3001.class);
	
	@Autowired
	private SystemSetting systemSetting;
	
	@Autowired
	JyzxJdxxcxfkMapper jyzxJdxxcxfkMapper;

	@Autowired
	JyzxJdxxcxqqMapper jyzxJdxxcxqqMapper;	
	
	@Autowired
	JyzxJdxxgxjkMapper jyzxJdxxgxjkMapper;
	
	
	
	@Override
	public PkgHeader doTask(PkgHeader reqPkg,PkgHeader rspPkg) {
		Pkg4001 pkg4001 = new Pkg4001();	
		try {
			Pkg3001 pkg3001 = (Pkg3001) JsonSerializer.deserializer(reqPkg.getMsgBody(),new TypeReference<Pkg3001>(){});
			
			String companyCode = pkg3001.getCompanyCode();	//发起查询的公司代码，具体指指令是由哪家公司请求 一般用作记录分析
			String realName = pkg3001.getRealName();	//被查询人的姓名
			String idCard = pkg3001.getIdCard();	//被查询人的身份证号
			
			List<LoanInfo> loanInfos = new ArrayList<LoanInfo>();
			LoanInfo loanInfo = new LoanInfo();
			loanInfo.setBorrowType((short) 1);
			loanInfo.setBorrowState((short) 2);
			loanInfo.setBorrowAmount((short) 3);
			loanInfo.setContractDate(new Date(1442035016251L));
			loanInfo.setLoanPeriod((short) 9);
			loanInfo.setRepayState((short) 8);
			loanInfo.setArrearsAmount((long) 0);
			loanInfo.setCompanyCode(companyCode);

			pkg4001.setLoanInfos(loanInfos);
			
			//正常情况
			rspPkg.setRetCode("0000");
			rspPkg.setRetMsg("查询成功");
//			rspPkg.setSign("5A59392B2F3C0F13F56CBBCFCCFFF25B");
		} 
		catch (Exception e) {
			e.printStackTrace();
			rspPkg.setRetCode("9999");
			rspPkg.setRetMsg("提交参数有误");
			rspPkg.setMsgBody("");
			throw e;
		}
		
		String msgBodyJson = JsonSerializer.serializer(pkg4001);
		rspPkg.setMsgBody(msgBodyJson);
		return rspPkg;
	}
	
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		String str = "{\"loanInfos\":[{\"borrowType\":0,\"borrowState\":0,\"borrowAmount\":0,\"contractDate\":0,\"loanPeriod\":0,\"repayState\":0,\"arrearsAmount\":0}]}";
		String retMsg1 = Base64.encodeBase64String(str.getBytes("UTF-8"));
		
		String retMsg = new BASE64Encoder().encode(str.getBytes("UTF-8"));
		System.out.println(retMsg);
		System.out.println(retMsg1);
	}
	
}


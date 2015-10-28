package com.xiaocui.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.xiaocui.common.JsonSerializer;
import com.xiaocui.entity.LoanInfo;
import com.xiaocui.entity.Pkg3001;
import com.xiaocui.entity.Pkg4001;
import com.xiaocui.entity.PkgHeader;

@Service("task3001")
public class Task3001 implements ITask {

	@Override
	public PkgHeader doTask(PkgHeader reqPkg,PkgHeader rspPkg) {
		Pkg4001 pkg4001 = new Pkg4001();	
		try {
			Pkg3001 pkg3001 = (Pkg3001) JsonSerializer.deserializer(reqPkg.getMsgBody(),new TypeReference<Pkg3001>(){});
			
			String companyCode = pkg3001.getCompanyCode();	//发起查询的公司代码，具体指指令是由哪家公司请求 一般用作记录分析
			String realName = pkg3001.getRealName();	//被查询人的姓名
			String idCard = pkg3001.getIdCard();	//被查询人的身份证号
			
			List<LoanInfo> loanInfos = new ArrayList<LoanInfo>();
			/*
			 * TODO: 此处从数据库中查询到数据添加到loanInfos结果集中
			 */
			
			pkg4001.setLoanInfos(loanInfos);
			
			//正常情况
			rspPkg.setRetCode("0000");
			rspPkg.setRetMsg("查询成功");
			
//			异常情况
//			rspPkg.setRetCode("9999");
//			rspPkg.setRetMsg("查询失败");
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
	

}


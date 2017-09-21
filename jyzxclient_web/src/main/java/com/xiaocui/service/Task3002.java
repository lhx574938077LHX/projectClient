package com.xiaocui.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.xiaocui.common.JsonSerializer;
import com.xiaocui.entity.LoanInfo;
import com.xiaocui.entity.Pkg3002;
import com.xiaocui.entity.Pkg4002;
import com.xiaocui.entity.PkgHeader;
import com.xiaocui.platform.dao.data.JyzxJdxxcxfkMapper;
import com.xiaocui.platform.dao.data.JyzxJdxxcxqqMapper;
import com.xiaocui.platform.model.data.JyzxJdxxcxfk;
import com.xiaocui.platform.model.data.JyzxJdxxcxqq;
import com.xiaocui.settings.SystemSetting;

@Service("task3002")
public class Task3002 implements ITask {
	
	private final static Logger logger = Logger.getLogger(Task3002.class);
	
	@Autowired
	private SystemSetting systemSetting;
	
	@Autowired
	JyzxJdxxcxfkMapper jyzxJdxxcxfkMapper;

	@Autowired
	JyzxJdxxcxqqMapper jyzxJdxxcxqqMapper;
	
	
	@Override
	public PkgHeader doTask(PkgHeader reqPkg,PkgHeader rspPkg) {
		Pkg4002 pkg4002 = new Pkg4002();	//4002报文为空不需设置任何属性
		try {
			System.out.println("输出msdBody："+reqPkg.getMsgBody());
			
			Pkg3002 pkg3002 = (Pkg3002) JsonSerializer.deserializer(reqPkg.getMsgBody(),new TypeReference<Pkg3002>(){});

			//正常情况
			rspPkg.setVersion("01");			//默认01
			rspPkg.setCustNo(systemSetting.getCompanyCode());				//公司代码
			rspPkg.setEncode("01");			//01.UTF8 02.GBK
			rspPkg.setTrxCode("4002");			//报文编号 默认四位 例:0001
			rspPkg.setEncryptType("01");	//加密类型 01.不加密 02.RSA
			rspPkg.setMsgType("01");			//01.JSON 02.XML 03.Protobuf
			rspPkg.setSign("5A59392B2F3C0F13F56CBBCFCCFFF25B");
			rspPkg.setRetCode("0000");
			rspPkg.setRetMsg("数据反馈成功");		
		} 
		catch (Exception e) {
			e.printStackTrace();
			rspPkg.setRetCode("9999");
			rspPkg.setRetMsg("提交参数有误");
			rspPkg.setMsgBody("");
			throw e;
		}
		
		rspPkg.setMsgBody("");
		
		return rspPkg;
	}



}


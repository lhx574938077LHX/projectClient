package com.xiaocui.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.xiaocui.common.JsonSerializer;
import com.xiaocui.entity.LoanInfo;
import com.xiaocui.entity.Pkg3002;
import com.xiaocui.entity.Pkg4002;
import com.xiaocui.entity.PkgHeader;
import com.xiaocui.platform.dao.data.XyvalidationMapper;
import com.xiaocui.platform.model.data.Xyvalidation;

@Service("task3002")
public class Task3002 implements ITask {
	
	@Autowired
	private XyvalidationMapper xyvalidationMapper;
	
	@Override
	public PkgHeader doTask(PkgHeader reqPkg,PkgHeader rspPkg) {
		Pkg4002 pkg4002 = new Pkg4002();	//4002报文为空不需设置任何属性
		try {
			Pkg3002 pkg3002 = (Pkg3002) JsonSerializer.deserializer(reqPkg.getMsgBody(),new TypeReference<Pkg3002>(){});
			int i=0;
			String trxNo = pkg3002.getTrxNo();	//发起查询时返回的编号用于做信息匹对
			System.out.println("开始保存。。。");
			for(LoanInfo loanInfo : pkg3002.getLoanInfos())
			{
				//TODO:此处将返回的结果信息进行保存处理
				Xyvalidation xyvalidation = new Xyvalidation();
			    //序号
				xyvalidation.setSerno(UUID.randomUUID().toString());
			    //查询标识码
				xyvalidation.setTrxno(trxNo);
			    //借款类型 0.未知1.个人信贷2.个人抵押3.企业信贷4.企业抵押
				xyvalidation.setBorrowtype(loanInfo.getBorrowType().toString());
			    //借款状态 0.未知1.拒贷2.批贷已放款3.批贷未放款4.借款人放弃申请5.审核中6.待放款
				xyvalidation.setBorrowstate(loanInfo.getBorrowState().toString());
			    //借款金额
				xyvalidation.setBorrowamount(loanInfo.getBorrowAmount().toString());
			    //合同日期
				xyvalidation.setContractdate(loanInfo.getContractDate());
			    //还款状态 0.未知1.正常2.M1 3.M2 4.M3 5.M4 6.M5 7.M6 8.M6+
				xyvalidation.setRepaystate(loanInfo.getRepayState().toString());
			    //批贷期数
				xyvalidation.setLoanperiod(loanInfo.getLoanPeriod().toString());
			    //欠款金额
				xyvalidation.setArrearsamount(loanInfo.getArrearsAmount().toString());
			    //公司代码
				xyvalidation.setCompanycode(loanInfo.getCompanyCode());
				
				xyvalidationMapper.insert(xyvalidation);
				i=i+1;
				System.out.println("保存第"+i+"条数据");
			}
			System.out.println("结束保存。。。");
			//正常情况
			rspPkg.setRetCode("0000");
			rspPkg.setRetMsg("成功接收到结果");
			
		} 
		catch (Exception e) {
			e.printStackTrace();
			rspPkg.setRetCode("9999");
			rspPkg.setRetMsg("提交参数有误");
			rspPkg.setMsgBody("");
			throw e;
		}
		
		String msgBodyJson = JsonSerializer.serializer(pkg4002);
		rspPkg.setMsgBody(msgBodyJson);
		
		return rspPkg;
	}
	

}


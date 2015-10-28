package com.xiaocui.service;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaocui.platform.dao.data.XyvalidationMapper;
import com.xiaocui.platform.model.data.Xyvalidation;

@Service("testService")
public class TestServiceImpl {
	
	@Autowired
	private XyvalidationMapper xyvalidationMapper;

	public void testSaveXyvalidation(){
		try {
			System.out.println("保存开始。。。");
			
			Xyvalidation xyvalidation = new Xyvalidation();
		    //序号
			xyvalidation.setSerno(UUID.randomUUID().toString());
		    //查询标识码
			xyvalidation.setTrxno("1");
		    //借款类型 0.未知1.个人信贷2.个人抵押3.企业信贷4.企业抵押
			xyvalidation.setBorrowtype("1");
		    //借款状态 0.未知1.拒贷2.批贷已放款3.批贷未放款4.借款人放弃申请5.审核中6.待放款
			xyvalidation.setBorrowstate("2");
		    //借款金额
			xyvalidation.setBorrowamount("200000");
		    //合同日期
			xyvalidation.setContractdate(new Date());
		    //还款状态 0.未知1.正常2.M1 3.M2 4.M3 5.M4 6.M5 7.M6 8.M6+
			xyvalidation.setRepaystate("M4");
		    //批贷期数
			xyvalidation.setLoanperiod("5");
		    //欠款金额
			xyvalidation.setArrearsamount("12000");
		    //公司代码
			xyvalidation.setCompanycode("9000001");
			xyvalidationMapper.insert(xyvalidation);
			
			System.out.println("保存成功。。。");
		} catch (Exception e) {
			
		}
		
	}
	
}

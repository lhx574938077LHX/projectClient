package com.xiaocui.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaocui.entity.LoanInfo;
import com.xiaocui.platform.dao.data.JyzxJdxxcxfkMapper;
import com.xiaocui.platform.dao.data.JyzxJdxxcxqqMapper;
import com.xiaocui.platform.model.data.JyzxJdxxcxfk;
import com.xiaocui.platform.model.data.JyzxJdxxcxqq;

@Service("testService")
public class TestServiceImpl {
	
	private final static Logger logger = Logger.getLogger(TestServiceImpl.class);
	
	@Autowired
	JyzxJdxxcxfkMapper jyzxJdxxcxfkMapper;

	@Autowired
	JyzxJdxxcxqqMapper jyzxJdxxcxqqMapper;
	
	public void insertTrxNo(String trxNo,String realName,String idcard){

		JyzxJdxxcxqq jyzxjdxxcxqq = new JyzxJdxxcxqq();
	    //用户姓名
		jyzxjdxxcxqq.setRealName(realName);
	    //身份证号
		jyzxjdxxcxqq.setIdcard(idcard);
	    //查询编码
		jyzxjdxxcxqq.setTrxNo(trxNo);
	    //记录创建时间
		jyzxjdxxcxqq.setRecTime(System.currentTimeMillis());
		jyzxJdxxcxqqMapper.insertSelective(jyzxjdxxcxqq);

	}
	

	public void save91Zhengxin(String trxNo,List<LoanInfo> loanInfos){
		String condition = " TRX_NO = '"+trxNo+"'";
		long count = jyzxJdxxcxqqMapper.selectCount(condition);
		if(count!=0){
			JyzxJdxxcxqq jyzxJdxxcxqq = jyzxJdxxcxqqMapper.selectOne(condition);
			String realName = jyzxJdxxcxqq.getRealName();
			String idCard = jyzxJdxxcxqq.getIdcard();
			
			long countqq = jyzxJdxxcxqqMapper.selectCount(condition);
			if(countqq>0){
				Map<String, String> paramMap = new HashMap<String, String>();
				String conditionqq = " TRX_NO = '"+trxNo+"'";
				paramMap.put("condition", conditionqq);
				List<JyzxJdxxcxfk> jyzxJdxxcxfkList = jyzxJdxxcxfkMapper.selectList(paramMap);
				for(JyzxJdxxcxfk jyzxJdxxcxfOther : jyzxJdxxcxfkList)	
				{
					jyzxJdxxcxqqMapper.deleteByPrimaryKey(jyzxJdxxcxfOther.getId());
				}
			}
	
			logger.info("1002开始保存。。。");
			
			for(LoanInfo loanInfo : loanInfos)
			{
				JyzxJdxxcxfk jyzxjdxxcxfk = new JyzxJdxxcxfk();
			    //查询编码
				jyzxjdxxcxfk.setTrxNo(trxNo);
			    //用户姓名
				jyzxjdxxcxfk.setRealName(realName);
			    //身份证号	
				jyzxjdxxcxfk.setIdcard(idCard);
			    //公司编码
				jyzxjdxxcxfk.setCompanyCode(loanInfo.getCompanyCode());
			    //借款类型 0.未知1.个人信贷2.个人抵押3.企业信贷4.企业抵押
				jyzxjdxxcxfk.setBorrowtYpe(loanInfo.getBorrowType());
			    //借款状态 0.未知1.拒贷2.批贷已放款3.批贷未放款4.借款人放弃申请5.审核中6.待放款
				jyzxjdxxcxfk.setBorrowState(loanInfo.getBorrowState());
			    //借款金额 -7.[0,0.1) -6.[0.1,0.2) -5.[0.2,0.3) -4.[0.3,0.4) -3.[0.4,0.6) -2.[0.6,0.8) -1.[0.8,1) 1.[1,2) 2.[2,4) 3.[4,6) 4.[6,8)…….
				jyzxjdxxcxfk.setBorrowAmount(loanInfo.getBorrowAmount());
			    //合同日期
				jyzxjdxxcxfk.setContractDate(loanInfo.getContractDate().getTime());
			    //批贷期数
				jyzxjdxxcxfk.setLoanPeriod(loanInfo.getLoanPeriod());
			    //还款状态 0.未知1.正常2.M1 3.M2 4.M3 5.M4 6.M5 7.M6 8.M6+
				jyzxjdxxcxfk.setRepayState(loanInfo.getRepayState());
			    //欠款金额
				jyzxjdxxcxfk.setArrearsAmount(loanInfo.getArrearsAmount());
			    //记录创建时间
				jyzxjdxxcxfk.setRecTime(System.currentTimeMillis());
				jyzxJdxxcxfkMapper.insertSelective(jyzxjdxxcxfk);
				
			}
			logger.info("1002结束保存。。。");			
		}else{
			logger.info("查询编码不存在");
		}
	}
	
	public void save91Zhengxin2(String trxNo,List<LoanInfo> loanInfos,String realName,String idCard){
		logger.info("1003开始保存。。。");
		
		for(LoanInfo loanInfo : loanInfos)
		{
			JyzxJdxxcxfk jyzxjdxxcxfk = new JyzxJdxxcxfk();
		    //查询编码
			jyzxjdxxcxfk.setTrxNo(trxNo);
		    //用户姓名
			jyzxjdxxcxfk.setRealName(realName);
		    //身份证号
			jyzxjdxxcxfk.setIdcard(idCard);
		    //公司编码
			jyzxjdxxcxfk.setCompanyCode(loanInfo.getCompanyCode());
		    //借款类型 0.未知1.个人信贷2.个人抵押3.企业信贷4.企业抵押
			jyzxjdxxcxfk.setBorrowtYpe(loanInfo.getBorrowType());
		    //借款状态 0.未知1.拒贷2.批贷已放款3.批贷未放款4.借款人放弃申请5.审核中6.待放款
			jyzxjdxxcxfk.setBorrowState(loanInfo.getBorrowState());
		    //借款金额 -7.[0,0.1) -6.[0.1,0.2) -5.[0.2,0.3) -4.[0.3,0.4) -3.[0.4,0.6) -2.[0.6,0.8) -1.[0.8,1) 1.[1,2) 2.[2,4) 3.[4,6) 4.[6,8)…….
			jyzxjdxxcxfk.setBorrowAmount(loanInfo.getBorrowAmount());
		    //合同日期
			jyzxjdxxcxfk.setContractDate(loanInfo.getContractDate().getTime());
		    //批贷期数
			jyzxjdxxcxfk.setLoanPeriod(loanInfo.getLoanPeriod());
		    //还款状态 0.未知1.正常2.M1 3.M2 4.M3 5.M4 6.M5 7.M6 8.M6+
			jyzxjdxxcxfk.setRepayState(loanInfo.getRepayState());
		    //欠款金额
			jyzxjdxxcxfk.setArrearsAmount(loanInfo.getArrearsAmount());
		    //记录创建时间
			jyzxjdxxcxfk.setRecTime(System.currentTimeMillis());
			jyzxJdxxcxfkMapper.insertSelective(jyzxjdxxcxfk);
			
		}
		logger.info("1003结束保存。。。");			

	}
	
}

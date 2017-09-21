package com.xiaocui.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.xiaocui.common.Credit91CallbackEnum;
import com.xiaocui.common.LoanInfo;
import com.xiaocui.common.Pkg1001;
import com.xiaocui.common.Pkg1002;
import com.xiaocui.common.Pkg1003;
import com.xiaocui.common.Pkg2001;
import com.xiaocui.common.Pkg2002;
import com.xiaocui.common.Pkg2003;
import com.xiaocui.common.Pkg3002;
import com.xiaocui.common.PkgHeader;
import com.xiaocui.dao.facade.CompanyInfoBeanMapper;
import com.xiaocui.dao.facade.JdDataMapper;
import com.xiaocui.dao.facade.SearchLogMapper;
import com.xiaocui.dao.facade.SearchResultMapper;
import com.xiaocui.dao.model.CompanyInfoBean;
import com.xiaocui.dao.model.JdData;
import com.xiaocui.dao.model.SearchLog;
import com.xiaocui.dao.model.SearchResult;
import com.xiaocui.platform.utils.IDCardUtils;
import com.xiaocui.platform.utils.JsonSerializer;
import com.xiaocui.platform.utils.RandomUtils;
import com.xiaocui.platform.utils.ValidatorUtils;

@Service
public class jyzxServiceImpl {
	
	@Autowired
	private SearchLogMapper searchLogMapper;
	
	@Autowired
	private CompanyInfoBeanMapper companyInfoBeanMapper;
	
	@Autowired
	private SearchResultMapper searchResultMapper;
	
	@Autowired
	private JdDataMapper jdDataMapper;
	
	public CompanyInfoBean selectCompany(String companyCode){
		return  companyInfoBeanMapper.selectOne("COMPANY_CODE = '"+companyCode+"'");
	}
	
	//1001
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public PkgHeader asyncSearch(PkgHeader reqPkg, PkgHeader rspPkg) {
        Pkg2001 pkg2001 = new Pkg2001();
        Pkg1001 pkg1001 = (Pkg1001) JsonSerializer.deserializer(reqPkg.getMsgBody(), new TypeReference<Pkg1001>() {});
        String trxNo = RandomUtils.generate32UUID();

        String realName = pkg1001.getRealName().trim().toUpperCase();
        String idCard = pkg1001.getIdCard().trim().toUpperCase();

        boolean isRealName = ValidatorUtils.isChinese(realName);
        if (!isRealName) {
            rspPkg.setRetCode("9999");
            rspPkg.setRetMsg("姓名错误");
            return rspPkg;
        }
        boolean isIdCard = IDCardUtils.verifi(idCard);
        if (!isIdCard) {
            rspPkg.setRetCode("9999");
            rspPkg.setRetMsg("身份证号错误");
            return rspPkg;
        }

        String companyCode = reqPkg.getCompanyCode().trim().toUpperCase();
        CompanyInfoBean companyInfo = companyInfoBeanMapper.selectOne("COMPANY_CODE = '"+companyCode+"'");
        
        // 记录查询记录
        SearchLog searchLog = new SearchLog();
        searchLog.setRealName(realName);
        searchLog.setIdCard(idCard);
        searchLog.setSearchTrxNo(trxNo);
        searchLog.setReqCompanyId(companyInfo.getId());
        searchLog.setReqCompanyCode(companyCode);
        searchLog.setState((short) 2);
        searchLog.setSyncState((short) 1);
        searchLog.setSearchType((short) 4);
        searchLog.setRecTime(System.currentTimeMillis());
        searchLog.setEndTime(0L);
        searchLogMapper.insertSelective(searchLog);

        pkg2001.setTrxNo(trxNo);

        rspPkg.setRetCode("0000");
        rspPkg.setRetMsg("查询订单提交成功");
        rspPkg.setMsgBody(JsonSerializer.serializer(pkg2001));
        return rspPkg;
		
	}
	//1002
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public PkgHeader searchResult(PkgHeader reqPkg, PkgHeader rspPkg) {
        Pkg2002 pkg2002 = new Pkg2002();
        Pkg1002 pkg1002 = JsonSerializer.deserializer(reqPkg.getMsgBody(), Pkg1002.class);

        String trxNo = pkg1002.getTrxNo();
        pkg2002.setTrxNo(trxNo);
        if (!ValidatorUtils.isOrderNum(trxNo)) {
            rspPkg.setRetCode("9999");
            rspPkg.setRetMsg("查询编号非法");
            return rspPkg;
        }
        SearchLog searchLog = searchLogMapper.selectOne("SEARCH_TRX_NO = '"+trxNo+"'");
        if (searchLog == null) {
            rspPkg.setRetCode("9999");
            rspPkg.setRetMsg("查询编号非法");
            return rspPkg;
        }
        
        if(!searchLog.getReqCompanyCode().equals(reqPkg.getCompanyCode())){
            rspPkg.setRetCode("9999");
            rspPkg.setRetMsg("查询编号非法");
            return rspPkg;
        }
        
        if (searchLog.getSyncState() != 2 && searchLog.getSyncState() != 3) {
            rspPkg.setRetCode("9999");
            rspPkg.setRetMsg("暂时无法查询");
            return rspPkg;
        }
        rspPkg.setRetCode("0000");
        rspPkg.setRetMsg("查询成功");
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("condition", " SEARCH_TRX_NO = '"+trxNo+"'");
		List<SearchResult> searchResults = searchResultMapper.selectList(paramMap );

        List<LoanInfo> loanIfs = new ArrayList<>();
        if (searchResults != null) {
            for (SearchResult searchResult : searchResults) {
                LoanInfo loanInfo = new LoanInfo();
                loanIfs.add(loanInfo);
                loanInfo.setRepayState(searchResult.getRepayState());
                loanInfo.setCompanyCode(searchResult.getRspCompanyCode());
                loanInfo.setLoanPeriod(searchResult.getLoanPeriod());
                loanInfo.setContractDate(new Date(searchResult.getContractDate()));
                loanInfo.setArrearsAmount(searchResult.getArrearsAmount());
                loanInfo.setBorrowAmount(searchResult.getBorrowAmount());
                loanInfo.setBorrowState(searchResult.getBorrowState());
                loanInfo.setBorrowType(searchResult.getBorrowType());
            }
        }
        pkg2002.setLoanInfos(loanIfs);
        rspPkg.setMsgBody(JsonSerializer.serializer(pkg2002));
        return rspPkg;
		
	}
	//1003
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public PkgHeader syncSearch(PkgHeader reqPkg, PkgHeader rspPkg) {
        Pkg2003 pkg2003 = new Pkg2003();
        Pkg1003 pkg1003 = JsonSerializer.deserializer(reqPkg.getMsgBody(), Pkg1003.class);

        String trxNo = RandomUtils.generate32UUID();
        pkg2003.setTrxNo(trxNo);
        String realName = pkg1003.getRealName();
        String idCard = pkg1003.getIdCard();

        boolean isRealName = ValidatorUtils.isChinese(realName);
        if (!isRealName) {
            rspPkg.setRetCode("9999");
            rspPkg.setRetMsg("姓名错误");
            return rspPkg;
        }
        boolean isIdCard = IDCardUtils.verifi(idCard);
        if (!isIdCard) {
            rspPkg.setRetCode("9999");
            rspPkg.setRetMsg("身份证号错误");
            return rspPkg;
        }

        String companyCode = reqPkg.getCompanyCode().trim().toUpperCase();
        CompanyInfoBean companyInfo = companyInfoBeanMapper.selectOne("COMPANY_CODE = '"+companyCode+"'");
        
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("condition", " REAL_NAME = '"+realName+"' AND ID_CARD = '"+idCard+"'");
		List<JdData> jdDatas = jdDataMapper.selectList(paramMap);
        
        // 记录查询记录
        SearchLog searchLog = new SearchLog();
        searchLog.setRealName(realName);
        searchLog.setIdCard(idCard);
        searchLog.setSearchTrxNo(trxNo);
        searchLog.setReqCompanyId(companyInfo.getId());
        searchLog.setReqCompanyCode(companyCode);
        searchLog.setState((short) 2);
        searchLog.setSyncState((short) 1);
        searchLog.setSearchType((short) 3);
        searchLog.setRecTime(System.currentTimeMillis());
        searchLog.setEndTime(0L);
        searchLogMapper.insertSelective(searchLog);

        //查询结果记录
		List<LoanInfo> loanInfos = new ArrayList<LoanInfo>();
		
        for(JdData jdData:jdDatas){
        	LoanInfo loanInfo = new LoanInfo();
			loanInfo.setBorrowType(jdData.getBorrowType()==null?0:jdData.getBorrowType());//借款类型 0.未知1.个人信贷2.个人抵押3.企业信贷4.企业抵押
			loanInfo.setBorrowState(jdData.getBorrowState()==null?0:jdData.getBorrowState());//借款状态 0.未知1.拒贷2.批贷已放款3.批贷未放款4.借款人放弃申请5.审核中6.待放款
			loanInfo.setBorrowAmount(jdData.getBorrowAmount()==null?0:jdData.getBorrowAmount());//借款金额
			loanInfo.setContractDate(new Date(jdData.getContractDate()));//合同日期
			loanInfo.setLoanPeriod(jdData.getLoanPeriod()==null?0:jdData.getLoanPeriod());//批贷期数
			loanInfo.setRepayState(jdData.getRepayState()==null?0:jdData.getRepayState());//还款状态 0.未知1.正常2.M1 3.M2 4.M3 5.M4 6.M5 7.M6 8.M6+
			loanInfo.setArrearsAmount(jdData.getArrearsAmount()==null?0:jdData.getArrearsAmount()); //欠款金额
			loanInfo.setCompanyCode(jdData.getCompanyCode()); //公司代码
			loanInfos.add(loanInfo);
        	
			SearchResult searchResult = new SearchResult();
			searchResult.setSearchLogId(searchLog.getId());
			searchResult.setSearchTrxNo(searchLog.getSearchTrxNo());
			searchResult.setRspCompanyCode(jdData.getCompanyCode());
			searchResult.setRspCompanyId(jdData.getCompanyId());
			searchResult.setBorrowType(loanInfo.getBorrowType());
			searchResult.setBorrowState (loanInfo.getBorrowState());
			searchResult.setBorrowAmount(loanInfo.getBorrowAmount());
			searchResult.setContractDate(loanInfo.getContractDate().getTime());
			searchResult.setLoanPeriod(loanInfo.getLoanPeriod());
			searchResult.setRepayState(loanInfo.getRepayState());
			searchResult.setArrearsAmount(loanInfo.getArrearsAmount());
			searchResult.setRecTime(System.currentTimeMillis());
			searchResult.setResultType((short)1);
			searchResult.setDataType((short)1);
			//记录请求公司信息
			searchResult.setReqCompanyCode(reqPkg.getCompanyCode());
			searchResult.setReqCompanyId(companyInfo.getId());
			searchResultMapper.insertSelective(searchResult);
        }
        pkg2003.setLoanInfos(loanInfos);
        
        rspPkg.setRetCode("0000");
        rspPkg.setRetMsg("查询成功");
        rspPkg.setMsgBody(JsonSerializer.serializer(pkg2003));
        
        searchLog.setState((short) 6);
        searchLog.setSyncState((short) 2);
        searchLogMapper.updateByPrimaryKeySelective(searchLog);
        return rspPkg;
	}
	
	//判断权限
	public Credit91CallbackEnum authority(String companyCode,String state,String connkey){
		long count = companyInfoBeanMapper.selectCount("COMPANY_CODE = '"+companyCode+"'");
		if(count==0){
			return Credit91CallbackEnum.NO_COMPANY_INFO;
		}
				
		CompanyInfoBean companyInfo = companyInfoBeanMapper.selectOne("COMPANY_CODE = '"+companyCode+"'");
		if(companyInfo.getP00003()==1){
			if(!companyInfo.getConnSign().equals(connkey)){
				return Credit91CallbackEnum.CONN_KEY_ERROR;
			}
		}
		
		switch (state) {
			case "1001":
				 if(companyInfo.getP00002()==0){
					 return Credit91CallbackEnum.NO_PORTAL_PERM;
				 }
				break;
			case "1002":
				
				break;
			case "1003":
				 if(companyInfo.getP00004()==0){
					 return Credit91CallbackEnum.NO_PORTAL_PERM;
				 }
				break;
	
			default:
				break;
		}
		return Credit91CallbackEnum.OK;
	}

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void searchDatas(Pkg1001 pkg1001, Pkg2001 pkg2001, PkgHeader reqHeader) {
		Pkg3002 pkg3002 = new Pkg3002();
        String realName = pkg1001.getRealName().trim().toUpperCase();
        String idCard = pkg1001.getIdCard().trim().toUpperCase();
        
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("condition", " REAL_NAME = '"+realName+"' AND ID_CARD = '"+idCard+"'");
        SearchLog searchLog = searchLogMapper.selectOne("SEARCH_TRX_NO = '"+pkg2001.getTrxNo()+"'");
		List<JdData> jdDatas = jdDataMapper.selectList(paramMap);
		List<LoanInfo> loanInfos = new ArrayList<LoanInfo>();
        CompanyInfoBean companyInfo = companyInfoBeanMapper.selectOne("COMPANY_CODE = '"+searchLog.getReqCompanyCode()+"'");
		
        for(JdData jdData:jdDatas){
        	LoanInfo loanInfo = new LoanInfo();
			loanInfo.setBorrowType(jdData.getBorrowType()==null?0:jdData.getBorrowType());//借款类型 0.未知1.个人信贷2.个人抵押3.企业信贷4.企业抵押
			loanInfo.setBorrowState(jdData.getBorrowState()==null?0:jdData.getBorrowState());//借款状态 0.未知1.拒贷2.批贷已放款3.批贷未放款4.借款人放弃申请5.审核中6.待放款
			loanInfo.setBorrowAmount(jdData.getBorrowAmount()==null?0:jdData.getBorrowAmount());//借款金额
			loanInfo.setContractDate(new Date(jdData.getContractDate()));//合同日期
			loanInfo.setLoanPeriod(jdData.getLoanPeriod()==null?0:jdData.getLoanPeriod());//批贷期数
			loanInfo.setRepayState(jdData.getRepayState()==null?0:jdData.getRepayState());//还款状态 0.未知1.正常2.M1 3.M2 4.M3 5.M4 6.M5 7.M6 8.M6+
			loanInfo.setArrearsAmount(jdData.getArrearsAmount()==null?0:jdData.getArrearsAmount()); //欠款金额
			loanInfo.setCompanyCode(jdData.getCompanyCode()); //公司代码
			loanInfos.add(loanInfo);
        	
			SearchResult searchResult = new SearchResult();
			searchResult.setSearchLogId(searchLog.getId());
			searchResult.setSearchTrxNo(searchLog.getSearchTrxNo());
			searchResult.setRspCompanyCode(jdData.getCompanyCode());
			searchResult.setRspCompanyId(jdData.getCompanyId());
			searchResult.setBorrowType(loanInfo.getBorrowType());
			searchResult.setBorrowState (loanInfo.getBorrowState());
			searchResult.setBorrowAmount(loanInfo.getBorrowAmount());
			searchResult.setContractDate(loanInfo.getContractDate().getTime());
			searchResult.setLoanPeriod(loanInfo.getLoanPeriod());
			searchResult.setRepayState(loanInfo.getRepayState());
			searchResult.setArrearsAmount(loanInfo.getArrearsAmount());
			searchResult.setRecTime(System.currentTimeMillis());
			searchResult.setResultType((short)2);
			searchResult.setDataType((short)1);
			//记录请求公司信息
			searchResult.setReqCompanyCode(searchLog.getReqCompanyCode());
			searchResult.setReqCompanyId(companyInfo.getId());
			searchResultMapper.insertSelective(searchResult);
        }
        pkg3002.setTrxNo(pkg2001.getTrxNo());
        pkg3002.setLoanInfos(loanInfos);
        
        reqHeader.setCompanyCode("SRV1A4G0000000001");
        reqHeader.setTrxCode("3002");
        reqHeader.setMsgBody(JsonSerializer.serializer(pkg3002));

        searchLog.setState((short) 4);
        searchLog.setSyncState((short) 3);
        searchLogMapper.updateByPrimaryKeySelective(searchLog);
	}

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateSearchLog(String trxNo,boolean isOver){
		SearchLog searchLog = searchLogMapper.selectOne("SEARCH_TRX_NO = '"+trxNo+"'");
		if(isOver){
	        searchLog.setState((short) 5);
	        searchLog.setSyncState((short) 2);
		}else{
	        searchLog.setState((short) 4);
	        searchLog.setSyncState((short) 3);
		}
        searchLogMapper.updateByPrimaryKeySelective(searchLog);		
	}
	
	
}

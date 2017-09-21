package com.xiaocui.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xiaocui.platform.common.MD5Utils;
import com.xiaocui.platform.dao.ext.JoinConditionExtMapper;
import com.xiaocui.platform.dao.search.CompanyInfoMapper;
import com.xiaocui.platform.dao.search.JoinConditionMapper;
import com.xiaocui.platform.dao.search.SearchLogMapper;
import com.xiaocui.platform.dao.search.SearchResultMapper;
import com.xiaocui.platform.entity.AUDJson;
import com.xiaocui.platform.entity.DataGridJson;
import com.xiaocui.platform.model.ext.JoinConditionExt;
import com.xiaocui.platform.model.search.CompanyInfo;
import com.xiaocui.platform.model.search.JoinCondition;
import com.xiaocui.platform.model.search.SearchLog;
import com.xiaocui.platform.model.search.SearchResult;

@Service("accountService")
public class AccountServiceImpl {
	
	private final static Logger logger = Logger.getLogger(AccountServiceImpl.class);
	
	@Autowired
	private CompanyInfoMapper companyInfoMapper;
	
	@Autowired
	private SearchLogMapper searchLogMapper;
	
	@Autowired
	private SearchResultMapper searchResultMapper;
	
	@Autowired
	private JoinConditionMapper joinConditionMapper;
	
	@Autowired
	private JoinConditionExtMapper joinConditionExtMapper;
	
	public DataGridJson queryAccountList(int start, int limit, String cpName, String cpCode) {
		DataGridJson result = new DataGridJson();
		long count = 0;
		String condition = " 1 = 1";
		
		int end = start + limit ;
		
		Map<String, String> paramMap = new HashMap<String,String>();
		paramMap.put("end", end+"");
		paramMap.put("start", start+"");

		paramMap.put("orderBy","ID DESC");
		
		if(!"0".equals(cpName)&&!"".equals(cpName)){
			condition = condition + " AND COMPANY_NAME like '%"+cpName+"%'";
		}
		if(!"0".equals(cpCode)&&!"".equals(cpCode)){
			condition = condition + " AND COMPANY_CODE = '"+cpCode+"'";
		}
		
		paramMap.put("condition", condition);
		count = companyInfoMapper.selectCount(condition);
		List<CompanyInfo> companyList = companyInfoMapper.selectList(paramMap);
		
		result.setDatas(companyList);
		result.setDataCount(count);
		result.setMsg("查询成功");
		result.setResult(true);
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public AUDJson addAccount(CompanyInfo companyInfo) {
		AUDJson result = new AUDJson();
		try {
			String condition = " COMPANY_CODE = '" + companyInfo.getCompanyCode() + "' or COMPANY_NAME = '"+companyInfo.getCompanyName()+"'";
			long count = companyInfoMapper.selectCount(condition);
			if(count>0){
				result.setData(null);
				result.setMsg("保存失败，公司名称和公司代码不可重复");
				result.setResult(false);
				return result;
			}
			
			String connSign = MD5Utils.MD5(companyInfo.getCompanyName()+companyInfo.getCompanyCode());
			companyInfo.setConnSign(connSign);
			companyInfo.setIntegral(0L); //积分
			companyInfo.setState((short) 3);
			companyInfo.setRecTime(System.currentTimeMillis());
			companyInfo.setSourceType((short) 0);
			companyInfo.setExcluded(companyInfo.getCompanyCode());
			companyInfo.setIntegralOverdraft((long) 0); //积分透支额度
			companyInfoMapper.insertSelective(companyInfo);
			
			JoinCondition joinCondition = new JoinCondition();
			joinCondition.setCompanyId(companyInfo.getId());
			joinCondition.setStartTime(System.currentTimeMillis());
			joinCondition.setUpdateTime(System.currentTimeMillis());
			joinCondition.setRecTime(System.currentTimeMillis());
			joinConditionMapper.insertSelective(joinCondition);
			
			result.setData(companyInfo);
			result.setMsg("保存成功");
			result.setResult(true);
		} catch (Exception e) {
			e.printStackTrace();
//			logger.error("91征信  Exception: ", e);
			result.setData(null);
			result.setMsg("系统异常！");
			result.setResult(false);			
		}
		
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public AUDJson updateAccount(CompanyInfo companyInfo) {
		AUDJson result = new AUDJson();
		try {
			String condition =" ID != "+companyInfo.getId() + " AND COMPANY_CODE = '" + companyInfo.getCompanyCode() + "'";
			long count = companyInfoMapper.selectCount(condition);
			if(count>0){
				result.setData(null);
				result.setMsg("保存失败，公司名称和公司代码不可重复");
				result.setResult(false);
				return result;
			}
			
			CompanyInfo company = companyInfoMapper.selectByPrimaryKey(companyInfo.getId());
			company.setCompanyName(companyInfo.getCompanyName());
			company.setCompanyCode(companyInfo.getCompanyCode());
			company.setServiceUrl(companyInfo.getServiceUrl());
			company.setP00001(companyInfo.getP00001());
			company.setP00002(companyInfo.getP00002());
			company.setP00003(companyInfo.getP00003());
			company.setP00004(companyInfo.getP00004());
			company.setP00005(companyInfo.getP00005());
			company.setP00005Param(companyInfo.getP00005Param());
			companyInfoMapper.updateByPrimaryKeySelective(company);
			
			result.setData(company);
			result.setMsg("保存成功");
			result.setResult(true);			
		} catch (Exception e) {
			e.printStackTrace();
//			logger.error("91征信  Exception: ", e);
			result.setData(null);
			result.setMsg("系统异常！");
			result.setResult(false);
		}
		
		return result;
	}
	
	public DataGridJson checkAccount(int start, int limit,Long id, String trxNo) {
		DataGridJson result = new DataGridJson();
		try {
			String condition = "1 = 1 AND REQ_COMPANY_ID = "+id;
			if(!"0".equals(trxNo)&&!"".equals(trxNo)){
				condition = condition + " AND SEARCH_TRX_NO = '"+trxNo+"'";
			}
			long count = searchLogMapper.selectCount(condition);
			Map<String, String> paramMap = new HashMap<String,String>();
			int end = start + limit;
			paramMap.put("end", end+"");
			paramMap.put("start", start+"");
			paramMap.put("orderBy","ID DESC");
			paramMap.put("condition",condition);
			List<SearchLog> searchLogList = searchLogMapper.selectList(paramMap);
			
			
			result.setDataCount(count);
			result.setDatas(searchLogList);
			result.setMsg("查询成功");
			result.setResult(true);
			
		} catch (Exception e) {
			e.printStackTrace();
//			logger.error("91征信  Exception: ", e);
			result.setResult(false);
			result.setMsg("系统异常！");
			result.setDataCount(0);
			result.setDatas(null);
		}
		return result;
	}

	public DataGridJson selectSearchResult(String trxNo) {
		DataGridJson result = new DataGridJson();
		try {
			Map<String, String> paramMap = new HashMap<String,String>();
			String condition = " SEARCH_TRX_NO ='" + trxNo+"'";
			paramMap.put("condition", condition);
			long count = searchResultMapper.selectCount(condition);
			List<SearchResult> searchResultList = searchResultMapper.selectList(paramMap);
			result.setDataCount(count);
			result.setDatas(searchResultList);
			result.setMsg("查询成功");
			result.setResult(true);
		} catch (Exception e) {
			e.printStackTrace();
//			logger.error("91征信  Exception: ", e);
			result.setResult(false);
			result.setMsg("系统异常！");
			result.setDataCount(0);
			result.setDatas(null);
		}
		return result;
	}

	public DataGridJson selectJoinCondition(int start, int limit,String companyName, String bdName, short joinState, long startdate, long enddate) {
		DataGridJson result = new DataGridJson();
		try {
			Map<String, String> paramMap = new HashMap<String,String>();
			String condition = "1=1";
			
			if(!"0".equals(companyName)){
				condition = condition+ " AND COMPANY_NAME like '%"+companyName+"%'";
			}
			
			if(!"0".equals(bdName)){
				condition = condition+ " AND BD_NAME like '%"+bdName+"%'";				
			}
			
			if(joinState!=-1){
				condition = condition+ " AND JOIN_STATE = '"+joinState+"'";				
			}
			
			if(startdate!=0){
				condition = condition+ " AND UPDATE_TIME > '"+startdate+"'";				
			}
			
			if(enddate!=0){
				condition = condition+ " AND UPDATE_TIME < '"+enddate+"'";				
			}
			
			int end = start + limit;
			paramMap.put("end", end+"");
			paramMap.put("start", start+"");
			paramMap.put("orderBy","COMPANY_ID DESC");
			paramMap.put("condition", condition);
			
			List<JoinConditionExt> joinCondition = joinConditionExtMapper.selectList(paramMap);
			long count = joinConditionExtMapper.selectCount(condition);
			
			result.setDataCount(count);
			result.setDatas(joinCondition);
			result.setMsg("查询成功");
			result.setResult(true);
			
		} catch (Exception e) {
			e.printStackTrace();
//			logger.error("91征信  Exception: ", e);
			result.setResult(false);
			result.setMsg("系统异常！");
			result.setDataCount(0);
			result.setDatas(null);
		}
		return result;
	}

	public AUDJson selectJoinConditionOne(Long companyId) {
		AUDJson result = new AUDJson();
		try {
			String condition = " COMPANY_ID = '"+ companyId +"'" ;
			long count = joinConditionMapper.selectCount(condition);
			if(count==0){
				JoinCondition joinCondition = new JoinCondition();
				joinCondition.setCompanyId(companyId);
				joinCondition.setUpdateTime(System.currentTimeMillis());
				joinCondition.setRecTime(System.currentTimeMillis());
				joinConditionMapper.insertSelective(joinCondition);
			}
			JoinConditionExt joinConditionExt = joinConditionExtMapper.selectOne(condition);
			result.setData(joinConditionExt);
			result.setMsg("查询成功");
			result.setResult(true);
		} catch (Exception e) {
			e.printStackTrace();
//			logger.error("91征信  Exception: ", e);
			result.setResult(false);
			result.setMsg("系统异常！");
			result.setData(null);
		}
		return result;
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public AUDJson updateJoinCondition(JoinCondition joinCondition) {
		AUDJson result = new AUDJson();
		try {
			joinCondition.setUpdateTime(System.currentTimeMillis());
			long a = joinConditionMapper.updateByPrimaryKeySelective(joinCondition);
			result.setData(joinCondition);
			result.setMsg("保存成功");
			result.setResult(true);
		} catch (Exception e) {
			e.printStackTrace();
//			logger.error("91征信  Exception: ", e);
			result.setResult(false);
			result.setMsg("系统异常！");
			result.setData(null);
		}
		return result;
	}

	public List<JoinConditionExt> deriveDatas(String cpName,
			String bdName, short joinState, long startdate, long enddate) {
		Map<String, String> paramMap = new HashMap<String,String>();
		String condition = "1=1";
		
		if(!"0".equals(cpName)){
			condition = condition+ " AND COMPANY_NAME like '%"+cpName+"%'";
		}		
		if(!"0".equals(bdName)){
			condition = condition+ " AND BD_NAME like '%"+bdName+"%'";				
		}		
		if(joinState!=-1){
			condition = condition+ " AND JOIN_STATE = '"+joinState+"'";				
		}		
		if(startdate!=0){
			condition = condition+ " AND UPDATE_TIME > '"+startdate+"'";				
		}		
		if(enddate!=0){
			condition = condition+ " AND UPDATE_TIME < '"+enddate+"'";				
		}
		
		paramMap.put("orderBy","COMPANY_ID DESC");
		paramMap.put("condition", condition);
		
		List<JoinConditionExt> joinConditionList = joinConditionExtMapper.selectList(paramMap);
		
		return joinConditionList;
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public AUDJson reSendSearchResult(long id) {
		AUDJson result = new AUDJson();
		try {
			SearchLog searchLog = searchLogMapper.selectByPrimaryKey(id);
			if(searchLog.getSearchType()!=4){
				result.setResult(true);
				result.setMsg("非异步查询，不可以重复报送！");
				result.setData(null);
				return result;
			}
			searchLog.setState((short) 3);
			searchLog.setSyncState((short) 3);
			searchLogMapper.updateByPrimaryKeySelective(searchLog);
			
			result.setResult(true);
			result.setMsg("报送成功！");
			result.setData(null);
		} catch (Exception e) {
			e.printStackTrace();
//			logger.error("91征信  Exception: ", e);
			result.setResult(false);
			result.setMsg("系统异常！");
			result.setData(null);
		}	
		return result;
	}
	
}

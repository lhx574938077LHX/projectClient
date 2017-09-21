package com.xiaocui.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xiaocui.platform.dao.search.JdDataMapper;
import com.xiaocui.platform.dao.search.TestDataResultMapper;
import com.xiaocui.platform.entity.AUDJson;
import com.xiaocui.platform.entity.DataGridJson;
import com.xiaocui.platform.model.search.JdData;
import com.xiaocui.platform.model.search.TestDataResult;

@Service("testManageService")
public class TestManageServiceImpl {
	
	private final static Logger logger = Logger.getLogger(TestManageServiceImpl.class);
	
	@Autowired
	private JdDataMapper jdDataMapper;
	
	@Autowired
	private TestDataResultMapper testDataResultMapper;
	
	public void deleteTestDataResult(){
		List<TestDataResult> testDataResults = new ArrayList<TestDataResult>();
		Map<String, String> paramMap = new HashMap<String, String>();
		testDataResults = testDataResultMapper.selectList(paramMap);
		for(TestDataResult  testDataResult:testDataResults){
			testDataResultMapper.deleteByPrimaryKey(testDataResult.getId());
		}
	}
	
	public List<TestDataResult> searchTestDataResult(){
		List<TestDataResult> testDataResults = new ArrayList<TestDataResult>();
		Map<String, String> paramMap = new HashMap<String, String>();
		testDataResults = testDataResultMapper.selectList(paramMap);
		
		return testDataResults;
		
	}
	
	public DataGridJson queryJdData(int start, int limit) {
		DataGridJson result = new DataGridJson();
		String condition = " 1 = 1";
		int end = start + limit ;
		
		Map<String, String> paramMap = new HashMap<String,String>();
		paramMap.put("end", end+"");
		paramMap.put("start", start+"");

		paramMap.put("orderBy","ID DESC");
		
		paramMap.put("condition", condition);
		long count = jdDataMapper.selectCount(condition);
		List<JdData> dDataList = jdDataMapper.selectList(paramMap);
		
		result.setDataCount(count);
		result.setDatas(dDataList);
		result.setMsg("查询成功");
		result.setResult(true);
		
		return result;
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public AUDJson addJdData(JdData jdData) {
		AUDJson result = new AUDJson();
		try {
			jdData.setCompanyCode("P2P4HJK0000100010");
			jdData.setCompanyId(1L);
			jdData.setRecTime(System.currentTimeMillis());
			jdDataMapper.insertSelective(jdData);
			
			result.setData(jdData);
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
	public void deleteJdData(long id) {
		try {
			jdDataMapper.deleteByPrimaryKey(id);			
		} catch (Exception e) {
			e.printStackTrace();
//			logger.error("91征信  Exception: ", e);
		}

	}

	public void editJdData(JdData jdData) {
		try {
			jdDataMapper.updateByPrimaryKeySelective(jdData);
		} catch (Exception e) {
			e.printStackTrace();
//			logger.error("91征信  Exception: ", e);
		}
	}
	
	

}

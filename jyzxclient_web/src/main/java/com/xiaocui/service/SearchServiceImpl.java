package com.xiaocui.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaocui.common.MultipleDataSource;
import com.xiaocui.entity.LoanInfo;
import com.xiaocui.entity.Pkg3002;
import com.xiaocui.platform.dao.data.JyzxJdxxcxfkMapper;
import com.xiaocui.platform.model.data.JyzxJdxxcxfk;

@Service("searchService")
public class SearchServiceImpl {
	
	@Autowired
	private JyzxJdxxcxfkMapper jyzxJdxxcxfkMapper;
	
	public void deleteJyzxJdxxcxfk(){
		//链接dataSource数据源
		MultipleDataSource.setDataSourceKey("dataSource");
		List<JyzxJdxxcxfk> jyzxJdxxcxfks = new ArrayList<JyzxJdxxcxfk>();
		Map<String, String> paramMap = new HashMap<String, String>();
		jyzxJdxxcxfks = jyzxJdxxcxfkMapper.selectList(paramMap);
		for(JyzxJdxxcxfk jyzxJdxxcxfk:jyzxJdxxcxfks){
			jyzxJdxxcxfkMapper.deleteByPrimaryKey(jyzxJdxxcxfk.getId());
		}
	}
	
	public List<JyzxJdxxcxfk> searchJyzxJdxxcxfk(){
		//链接dataSource数据源
		MultipleDataSource.setDataSourceKey("dataSource");
		List<JyzxJdxxcxfk> jyzxJdxxcxfks = new ArrayList<JyzxJdxxcxfk>();
		Map<String, String> paramMap = new HashMap<String, String>();
		jyzxJdxxcxfks = jyzxJdxxcxfkMapper.selectList(paramMap);
		
		return jyzxJdxxcxfks;
		
	}
	
}

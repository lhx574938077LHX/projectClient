package com.xiaocui.dao.facade;

import java.util.List;
import java.util.Map;

import com.xiaocui.dao.model.SearchLog;

public interface SearchLogMapper {
    long deleteByPrimaryKey(Long id);
	
	long deleteByCondition(String condition);
	
    long insert(SearchLog record);

    long insertSelective(SearchLog record);

    SearchLog selectByPrimaryKey(Long id);

    long updateByPrimaryKeySelective(SearchLog record);

    long updateByPrimaryKey(SearchLog record);
    
    List<SearchLog> selectList(Map<String,String> paramMap);
    
    long selectCount ( String condition);
    
    SearchLog selectOne ( String condition);
    
    long updateByCondition(Map<String,String> paramMap);
}

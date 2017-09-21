package com.xiaocui.platform.dao.search;

import java.util.List;
import java.util.Map;

import com.xiaocui.platform.model.search.SearchCount;

public interface SearchCountMapper {
    long deleteByPrimaryKey(Long id);
	
	long deleteByCondition(String condition);
	
    long insert(SearchCount record);

    long insertSelective(SearchCount record);

    SearchCount selectByPrimaryKey(Long id);

    long updateByPrimaryKeySelective(SearchCount record);

    long updateByPrimaryKey(SearchCount record);
    
    List<SearchCount> selectList(Map<String,String> paramMap);
    
    long selectCount ( String condition);
    
    SearchCount selectOne ( String condition);
    
    long updateByCondition(Map<String,String> paramMap);
}

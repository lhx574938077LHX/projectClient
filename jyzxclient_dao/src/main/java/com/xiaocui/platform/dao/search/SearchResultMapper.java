package com.xiaocui.platform.dao.search;

import java.util.List;
import java.util.Map;

import com.xiaocui.platform.model.search.SearchResult;

public interface SearchResultMapper {
    long deleteByPrimaryKey(Long id);
	
	long deleteByCondition(String condition);
	
    long insert(SearchResult record);

    long insertSelective(SearchResult record);

    SearchResult selectByPrimaryKey(Long id);

    long updateByPrimaryKeySelective(SearchResult record);

    long updateByPrimaryKey(SearchResult record);
    
    List<SearchResult> selectList(Map<String,String> paramMap);
    
    long selectCount ( String condition);
    
    SearchResult selectOne ( String condition);
    
    long updateByCondition(Map<String,String> paramMap);
}

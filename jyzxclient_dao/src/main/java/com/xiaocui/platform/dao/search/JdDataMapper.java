package com.xiaocui.platform.dao.search;

import java.util.List;
import java.util.Map;

import com.xiaocui.platform.model.search.JdData;

public interface JdDataMapper {
    long deleteByPrimaryKey(Long id);
	
	long deleteByCondition(String condition);
	
    long insert(JdData record);

    long insertSelective(JdData record);

    JdData selectByPrimaryKey(Long id);

    long updateByPrimaryKeySelective(JdData record);

    long updateByPrimaryKey(JdData record);
    
    List<JdData> selectList(Map<String,String> paramMap);
    
    long selectCount ( String condition);
    
    JdData selectOne ( String condition);
    
    long updateByCondition(Map<String,String> paramMap);
}
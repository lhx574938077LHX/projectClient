package com.xiaocui.platform.dao.search;

import java.util.List;
import java.util.Map;

import com.xiaocui.platform.model.search.JoinCondition;

public interface JoinConditionMapper {
    long deleteByPrimaryKey(Long id);
	
	long deleteByCondition(String condition);
	
    long insert(JoinCondition record);

    long insertSelective(JoinCondition record);

    JoinCondition selectByPrimaryKey(Long id);

    long updateByPrimaryKeySelective(JoinCondition record);

    long updateByPrimaryKey(JoinCondition record);
    
    List<JoinCondition> selectList(Map<String,String> paramMap);
    
    long selectCount ( String condition);
    
    JoinCondition selectOne ( String condition);
    
    long updateByCondition(Map<String,String> paramMap);
}

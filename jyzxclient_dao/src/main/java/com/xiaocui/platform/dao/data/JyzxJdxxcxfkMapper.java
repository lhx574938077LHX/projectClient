package com.xiaocui.platform.dao.data;

import java.util.List;
import java.util.Map;

import com.xiaocui.platform.model.data.JyzxJdxxcxfk;

public interface JyzxJdxxcxfkMapper {
    long deleteByPrimaryKey(Long id);
	
	long deleteByCondition(String condition);
	
    long insert(JyzxJdxxcxfk record);

    long insertSelective(JyzxJdxxcxfk record);

    JyzxJdxxcxfk selectByPrimaryKey(Long id);

    long updateByPrimaryKeySelective(JyzxJdxxcxfk record);

    long updateByPrimaryKey(JyzxJdxxcxfk record);
    
    List<JyzxJdxxcxfk> selectList(Map<String,String> paramMap);
    
    long selectCount ( String condition);
    
    JyzxJdxxcxfk selectOne ( String condition);
    
    long updateByCondition(Map<String,String> paramMap);
}

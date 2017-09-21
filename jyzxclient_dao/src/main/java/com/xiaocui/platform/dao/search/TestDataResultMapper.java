package com.xiaocui.platform.dao.search;

import java.util.List;
import java.util.Map;

import com.xiaocui.platform.model.search.TestDataResult;

public interface TestDataResultMapper {
    long deleteByPrimaryKey(Long id);
	
	long deleteByCondition(String condition);
	
    long insert(TestDataResult record);

    long insertSelective(TestDataResult record);

    TestDataResult selectByPrimaryKey(Long id);

    long updateByPrimaryKeySelective(TestDataResult record);

    long updateByPrimaryKey(TestDataResult record);
    
    List<TestDataResult> selectList(Map<String,String> paramMap);
    
    long selectCount ( String condition);
    
    TestDataResult selectOne ( String condition);
    
    long updateByCondition(Map<String,String> paramMap);
}

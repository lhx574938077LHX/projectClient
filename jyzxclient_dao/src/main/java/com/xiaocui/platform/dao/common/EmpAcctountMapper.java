package com.xiaocui.platform.dao.common;

import java.util.List;
import java.util.Map;

import com.xiaocui.platform.model.common.EmpAcctount;

public interface EmpAcctountMapper {
    long deleteByPrimaryKey(Long id);
	
	long deleteByCondition(String condition);
	
    long insert(EmpAcctount record);

    long insertSelective(EmpAcctount record);

    EmpAcctount selectByPrimaryKey(Long id);

    long updateByPrimaryKeySelective(EmpAcctount record);

    long updateByPrimaryKey(EmpAcctount record);
    
    List<EmpAcctount> selectList(Map<String,String> paramMap);
    
    long selectCount ( String condition);
    
    EmpAcctount selectOne ( String condition);
    
    long updateByCondition(Map<String,String> paramMap);
}

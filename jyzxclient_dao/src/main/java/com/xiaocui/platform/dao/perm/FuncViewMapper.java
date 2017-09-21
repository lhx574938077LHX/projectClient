package com.xiaocui.platform.dao.perm;

import java.util.List;
import java.util.Map;

import com.xiaocui.platform.model.perm.FuncView;

public interface FuncViewMapper {
    long deleteByPrimaryKey(Long id);
	
	long deleteByCondition(String condition);
	
    long insert(FuncView record);

    long insertSelective(FuncView record);

    FuncView selectByPrimaryKey(Long id);

    long updateByPrimaryKeySelective(FuncView record);

    long updateByPrimaryKey(FuncView record);
    
    List<FuncView> selectList(Map<String,String> paramMap);
    
    long selectCount ( String condition);
    
    FuncView selectOne ( String condition);
    
    long updateByCondition(Map<String,String> paramMap);
}

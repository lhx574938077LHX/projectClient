package com.xiaocui.platform.dao.perm;

import java.util.List;
import java.util.Map;

import com.xiaocui.platform.model.perm.NavTree;

public interface NavTreeMapper {
    long deleteByPrimaryKey(Long id);
	
	long deleteByCondition(String condition);
	
    long insert(NavTree record);

    long insertSelective(NavTree record);

    NavTree selectByPrimaryKey(Long id);

    long updateByPrimaryKeySelective(NavTree record);

    long updateByPrimaryKey(NavTree record);
    
    List<NavTree> selectList(Map<String,String> paramMap);
    
    long selectCount ( String condition);
    
    NavTree selectOne ( String condition);
    
    long updateByCondition(Map<String,String> paramMap);
}

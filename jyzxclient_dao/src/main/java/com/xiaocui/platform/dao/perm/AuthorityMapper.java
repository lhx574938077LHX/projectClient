package com.xiaocui.platform.dao.perm;

import java.util.List;
import java.util.Map;

import com.xiaocui.platform.model.perm.Authority;

public interface AuthorityMapper {
    long deleteByPrimaryKey(Long id);
	
	long deleteByCondition(String condition);
	
    long insert(Authority record);

    long insertSelective(Authority record);

    Authority selectByPrimaryKey(Long id);

    long updateByPrimaryKeySelective(Authority record);

    long updateByPrimaryKey(Authority record);
    
    List<Authority> selectList(Map<String,String> paramMap);
    
    long selectCount ( String condition);
    
    Authority selectOne ( String condition);
    
    long updateByCondition(Map<String,String> paramMap);
}

package com.xiaocui.platform.dao.perm;

import java.util.List;
import java.util.Map;

import com.xiaocui.platform.model.perm.AuthTemplate;

public interface AuthTemplateMapper {
    long deleteByPrimaryKey(Long id);
	
	long deleteByCondition(String condition);
	
    long insert(AuthTemplate record);

    long insertSelective(AuthTemplate record);

    AuthTemplate selectByPrimaryKey(Long id);

    long updateByPrimaryKeySelective(AuthTemplate record);

    long updateByPrimaryKey(AuthTemplate record);
    
    List<AuthTemplate> selectList(Map<String,String> paramMap);
    
    long selectCount ( String condition);
    
    AuthTemplate selectOne ( String condition);
    
    long updateByCondition(Map<String,String> paramMap);
}

package com.xiaocui.platform.dao.search;

import java.util.List;
import java.util.Map;

import com.xiaocui.platform.model.search.CompanyInfo;

public interface CompanyInfoMapper {
    long deleteByPrimaryKey(Long id);
	
	long deleteByCondition(String condition);
	
    long insert(CompanyInfo record);

    long insertSelective(CompanyInfo record);

    CompanyInfo selectByPrimaryKey(Long id);

    long updateByPrimaryKeySelective(CompanyInfo record);

    long updateByPrimaryKey(CompanyInfo record);
    
    List<CompanyInfo> selectList(Map<String,String> paramMap);
    
    long selectCount ( String condition);
    
    CompanyInfo selectOne ( String condition);
    
    long updateByCondition(Map<String,String> paramMap);
}

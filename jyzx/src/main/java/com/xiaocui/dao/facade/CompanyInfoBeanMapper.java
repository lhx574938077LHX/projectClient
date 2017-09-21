package com.xiaocui.dao.facade;

import java.util.List;
import java.util.Map;

import com.xiaocui.dao.model.CompanyInfoBean;

public interface CompanyInfoBeanMapper {
    long deleteByPrimaryKey(Long id);
	
	long deleteByCondition(String condition);
	
    long insert(CompanyInfoBean record);

    long insertSelective(CompanyInfoBean record);

    CompanyInfoBean selectByPrimaryKey(Long id);

    long updateByPrimaryKeySelective(CompanyInfoBean record);

    long updateByPrimaryKey(CompanyInfoBean record);
    
    List<CompanyInfoBean> selectList(Map<String,String> paramMap);
    
    long selectCount ( String condition);
    
    CompanyInfoBean selectOne ( String condition);
    
    long updateByCondition(Map<String,String> paramMap);
}

package com.xiaocui.platform.dao.data;

import java.util.List;
import java.util.Map;

import com.xiaocui.platform.model.data.JyzxJdxxcxqq;

public interface JyzxJdxxcxqqMapper {
    long deleteByPrimaryKey(Long id);

    long insert(JyzxJdxxcxqq record);

    long insertSelective(JyzxJdxxcxqq record);

    JyzxJdxxcxqq selectByPrimaryKey(Long id);

    long updateByPrimaryKeySelective(JyzxJdxxcxqq record);

    long updateByPrimaryKey(JyzxJdxxcxqq record);
    
    List<JyzxJdxxcxqq> selectList(Map<String,String> paramMap);
    
    long selectCount ( String condition);
    
    JyzxJdxxcxqq selectOne ( String condition);
    
    long updateByCondition(Map<String,String> paramMap);
}

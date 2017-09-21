package com.xiaocui.platform.dao.data;

import java.util.List;
import java.util.Map;

import com.xiaocui.platform.model.data.JyzxJdxxgxjk;

public interface JyzxJdxxgxjkMapper {
    long deleteByPrimaryKey(Long id);

    long insert(JyzxJdxxgxjk record);

    long insertSelective(JyzxJdxxgxjk record);

    JyzxJdxxgxjk selectByPrimaryKey(Long id);

    long updateByPrimaryKeySelective(JyzxJdxxgxjk record);

    long updateByPrimaryKey(JyzxJdxxgxjk record);
    
    List<JyzxJdxxgxjk> selectList(Map<String,String> paramMap);
    
    long selectCount ( String condition);
    
    JyzxJdxxgxjk selectOne ( String condition);
    
    long updateByCondition(Map<String,String> paramMap);
}

package com.xiaocui.platform.dao.ext;

import java.util.List;
import java.util.Map;

import com.xiaocui.platform.model.ext.JoinConditionExt;

public interface JoinConditionExtMapper {
	 
	List<JoinConditionExt> selectList(Map<String,String> paramMap);
	 
	long selectCount ( String condition);
	
	JoinConditionExt selectOne(String condition);
	
}

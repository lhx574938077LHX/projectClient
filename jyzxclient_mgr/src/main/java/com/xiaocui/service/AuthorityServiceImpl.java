package com.xiaocui.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaocui.common.TreeHelper;
import com.xiaocui.platform.dao.perm.AuthorityMapper;
import com.xiaocui.platform.dao.perm.NavTreeMapper;
import com.xiaocui.platform.model.common.EmpAcctount;
import com.xiaocui.platform.model.ext.NavTreeEx;
import com.xiaocui.platform.model.perm.Authority;
import com.xiaocui.platform.model.perm.NavTree;

@Service("authorityService")
public class AuthorityServiceImpl {
	
	@Autowired
	private NavTreeMapper navTreeMapper;
	
	@Autowired
	private AuthorityMapper authorityMapper;
	
	public List<NavTreeEx> selectMenuAuthority(EmpAcctount empAcctount, long pid) {
		//得到所有菜单
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("orderBy", " ID ASC");
		List<NavTree> navtreeList = navTreeMapper.selectList(paramMap);

		Long authTplId = empAcctount.getAuthTplId();
		List<Authority> authorityList = new ArrayList<Authority>();
		List<Long> myNodesIdList = new ArrayList<Long>();
		if (authTplId != 0) {
			Map<String, String> paramMap1 = new HashMap<String, String>();
			String condition = " AUTH_TPL_ID = " + authTplId + " AND TYPE = 1";
			paramMap1.put("condition", condition);
			authorityList = authorityMapper.selectList(paramMap1);
			for (Authority authority : authorityList) {
				String uuid = authority.getCode();
				String condition1 = " GUID = '" + uuid + "'";
				NavTree navtree = navTreeMapper.selectOne(condition1);
				myNodesIdList.add(navtree.getId());
			}
		} else {
			List<NavTree> navTreeLists = navTreeMapper.selectList(paramMap);
			for (NavTree navtree : navTreeLists) {
				myNodesIdList.add(navtree.getId());
			}
		}
		TreeHelper treeHelper = new TreeHelper(navtreeList, myNodesIdList.toArray());
		List<NavTreeEx> p_NavTrees = treeHelper.genUserPermTree(pid);
		return p_NavTrees;
	}

}

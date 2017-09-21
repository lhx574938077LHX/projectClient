package com.xiaocui.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaocui.common.JsonSerializer;
import com.xiaocui.platform.entity.DataGridJson;
import com.xiaocui.platform.model.common.EmpAcctount;
import com.xiaocui.platform.model.ext.NavTreeEx;
import com.xiaocui.service.AuthorityServiceImpl;

@Controller
@RequestMapping("authority")
public class AuthorityController {
	
	private final static Logger logger = Logger.getLogger(AuthorityController.class);
	
	@Autowired
	private AuthorityServiceImpl authorityService;
	
	@RequestMapping("selectMenuAuthority")
	@ResponseBody
	public DataGridJson selectMenuAuthority(HttpServletRequest request, long pid) {
		//一级菜单查询功能
		DataGridJson result = new DataGridJson();
		try {
			EmpAcctount empAcctount = (EmpAcctount) request.getSession().getAttribute("empAcctount");
			List<NavTreeEx> navTreeExList = authorityService.selectMenuAuthority(empAcctount, pid);
			result.setDataCount(navTreeExList.size());
			result.setDatas(navTreeExList);
			result.setMsg("查询菜单权限成功");
			result.setResult(true);
		} catch (Exception e) {
//			logger.error("JYZX Exception: ", e);
			e.printStackTrace();
			result.setDataCount(0);
			result.setDatas(null);
			result.setMsg("查询菜单权限失败");
			result.setResult(true);
		}
		return result;
	}
	

	@RequestMapping("searchViewTree")	
	@ResponseBody
	public List<NavTreeEx> searchViewTree(HttpServletRequest request, long pid) {
		List<NavTreeEx> resultList = new ArrayList<NavTreeEx>();
		try {
			EmpAcctount empAcctount = (EmpAcctount) request.getSession().getAttribute("empAcctount");
			resultList = authorityService.selectMenuAuthority(empAcctount, pid);
			@SuppressWarnings("unused")
			String jsonStr = JsonSerializer.serializer(resultList);
		} catch (Exception e) {
			logger.error("JYZX Exception: ", e);
			e.printStackTrace();
		}
		return resultList;
	}
}

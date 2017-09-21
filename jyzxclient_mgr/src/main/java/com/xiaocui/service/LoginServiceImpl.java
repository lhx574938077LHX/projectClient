package com.xiaocui.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xiaocui.platform.common.MD5Utils;
import com.xiaocui.platform.dao.common.EmpAcctountMapper;
import com.xiaocui.platform.entity.AUDJson;
import com.xiaocui.platform.model.common.EmpAcctount;

@Service("loginService")
public class LoginServiceImpl {
	
	private final static Logger logger = Logger.getLogger(LoginServiceImpl.class);	
	
	@Autowired
	private EmpAcctountMapper empAcctountMapper;
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)	
	public AUDJson userLogin(HttpServletRequest request, String username,
			String password) {
		AUDJson result = new AUDJson();
		HttpSession session = request.getSession();
		try {
			String passwordMd5 = MD5Utils.MD5(password);
			String condition = " USERNAME = '"+username + "' AND PASSWORD = '"+passwordMd5 +"' AND STATE = 1 ";
			long count = empAcctountMapper.selectCount(condition);
			if(count==1){
				
				EmpAcctount empAcctount = empAcctountMapper.selectOne(condition);
				empAcctount.setLastLoginTime(System.currentTimeMillis());
				empAcctountMapper.updateByPrimaryKeySelective(empAcctount);
				
				session.setAttribute("empAcctount", empAcctount);
				
				result.setMsg("登陆成功");
				result.setResult(true);
				result.setData(empAcctount);
			}else if(count==0){
				result.setData(null);
				result.setMsg("用户名密码错误！");
				result.setResult(false);				
			}
		} catch (Exception e) {
			e.printStackTrace();
//			logger.error("91征信Exception: ", e);
			result.setData(null);
			result.setMsg("系统错误！");
			result.setResult(false);
		}
		return result;
	}

	public AUDJson userLogOut(HttpServletRequest request,
			HttpServletResponse response) {
		AUDJson result = new AUDJson();
		HttpSession session = request.getSession();
		try {
			session.removeAttribute("empAcctount");
			result.setData(null);
			result.setMsg("退出登录成功！");
			result.setResult(true);			
		} catch (Exception e) {
			e.printStackTrace();
//			logger.error("91征信Exception: ", e);
			result.setData(null);
			result.setMsg("退出登录失败！");
			result.setResult(false);
		}
		return result;
	}

}

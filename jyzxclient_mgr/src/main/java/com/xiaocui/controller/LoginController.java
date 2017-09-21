package com.xiaocui.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaocui.platform.entity.AUDJson;
import com.xiaocui.service.LoginServiceImpl;

@Controller
@RequestMapping("login")
public class LoginController {
	
	private final static Logger logger = Logger.getLogger(LoginController.class);
	
	@Autowired
	private LoginServiceImpl loginService;
	
	@RequestMapping("loginJ")
	@ResponseBody
	public AUDJson loginJ(HttpServletRequest request, String vcode, String username, String password) {
		AUDJson result = new AUDJson();
		try {
			String vcodestr = (String) request.getSession().getAttribute("vCode");
			if (!vcodestr.toLowerCase().equals(vcode.toLowerCase())) {
				result.setData(null);
				result.setMsg("登陆失败,验证码错误！");
				result.setResult(false);
				return result;
			}

			result = loginService.userLogin(request, username, password);

		} catch (Exception e) {
//			logger.error("91zhengxin Exception: ", e);
			e.printStackTrace();
			result.setData(null);
			result.setMsg("登陆失败！");
			result.setResult(false);
		}
		return result;
	}

	@RequestMapping("logOutJ")
	@ResponseBody
	public AUDJson loginJ(HttpServletRequest request, HttpServletResponse response) {
		AUDJson result = new AUDJson();
		try {
			result = loginService.userLogOut(request, response);
		} catch (Exception e) {
			logger.error("HYXY Exception: ", e);
			result.setData(null);
			result.setMsg("退出登录失败！");
			result.setResult(false);
		}
		return result;
	}	
	
	
}

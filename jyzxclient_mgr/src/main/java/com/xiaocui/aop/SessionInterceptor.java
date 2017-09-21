package com.xiaocui.aop;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xiaocui.platform.common.SkipException;
import com.xiaocui.platform.model.common.EmpAcctount;

@Aspect
@Component
public class SessionInterceptor {

	@Autowired
	private HttpSession session;

	public void InterceptToLogin() {
		EmpAcctount empAcctount = (EmpAcctount) session.getAttribute("empAcctount");
		if (empAcctount == null) {
			throw new SkipException("未登录异常抛出");
		}
	}

}

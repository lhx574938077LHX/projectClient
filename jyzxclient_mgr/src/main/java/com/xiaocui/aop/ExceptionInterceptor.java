package com.xiaocui.aop;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.cglib.proxy.InvocationHandler;
import org.springframework.stereotype.Component;


public class ExceptionInterceptor implements InvocationHandler {

	@Override
	public Object invoke(Object arg0, Method arg1, Object[] arg2) throws Throwable {
		// TODO Auto-generated method stub
		Object object = new Object();
		return null;
	}

	
	
}

package com.xiaocui.listener;


import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class MyServletContextAttributeListener implements ServletContextAttributeListener,HttpSessionListener {

	@Override
	public void attributeAdded(ServletContextAttributeEvent event) {
		// TODO Auto-generated method stub
//		String str =MessageFormat.format(
//				"ServletContext域对象中添加了属性:{0}，属性值是:{1}"
//				,event.getName()
//				,event.getValue());
//		System.out.println(str);		
	}

	@Override
	public void attributeRemoved(ServletContextAttributeEvent event) {
		// TODO Auto-generated method stub
//		String str =MessageFormat.format(
//				"ServletContext域对象中添加了属性:{0}，属性值是:{1}"
//				,event.getName()
//				,event.getValue());
//		System.out.println(str);		
	}

	@Override
	public void attributeReplaced(ServletContextAttributeEvent event) {
		// TODO Auto-generated method stub
//		String str =MessageFormat.format(
//				"ServletContext域对象中添加了属性:{0}，属性值是:{1}"
//				,event.getName()
//				,event.getValue());
//		System.out.println(str);		
	}

	@Override
	public void sessionCreated(HttpSessionEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		// TODO Auto-generated method stub
		
	}

}

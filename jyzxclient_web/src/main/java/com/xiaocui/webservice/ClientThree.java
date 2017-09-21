package com.xiaocui.webservice;

import java.util.List;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import com.xiaocui.entity.LoanInfo;

public class ClientThree {
	
	public static void main(String[] args) {
        String address = "http://schemas.xmlsoap.org/wsdl/"; //此处最好用系统参数
        JaxWsProxyFactoryBean bean = new JaxWsProxyFactoryBean();
        bean.setAddress(address);
		
		System.exit(0);
	}
	
}

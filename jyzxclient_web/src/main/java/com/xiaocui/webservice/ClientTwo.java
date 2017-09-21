package com.xiaocui.webservice;

import java.util.List;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xiaocui.entity.LoanInfo;

public class ClientTwo {
	
	public ClientTwo() {
	}
	
	public static void main(String[] args) {
//        @SuppressWarnings("resource")
//		ClassPathXmlApplicationContext context 
//            = new ClassPathXmlApplicationContext(new String[] {"classpath:spring-beans.xml"});
//
//        SearchService client = (SearchService)context.getBean("clientTwo");
//
//        List<LoanInfo> loanInfos = client.searchCustInfo("陈水金", "350424198301082038", "P2P4HJK0000100010", "5a59392b2f3c0f13f56cbbcfccfff25b");
//        System.out.println(loanInfos.size());

        String address = "http://localhost:9080/jyzxclient_web/services/SearchService?wsdl"; //此处最好用系统参数
        JaxWsProxyFactoryBean bean = new JaxWsProxyFactoryBean();
        bean.setAddress(address);
        bean.setServiceClass(SearchService.class);
        SearchService ws = (SearchService) bean.create();
        List<LoanInfo> result = ws.searchCustInfo("陈水金", "350424198301082038", "P2P4HJK0000100010", "5a59392b2f3c0f13f56cbbcfccfff25b");
        System.out.println(result.size());
		
		System.exit(0);
	}
	
}

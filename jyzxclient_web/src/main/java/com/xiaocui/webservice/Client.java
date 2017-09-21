package com.xiaocui.webservice;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public final class Client {

    private Client() {
    }

    public static void main(String args[]) throws Exception {
        @SuppressWarnings("resource")
		ClassPathXmlApplicationContext context 
            = new ClassPathXmlApplicationContext(new String[] {"classpath:spring-beans.xml"});

        HelloWorld client = (HelloWorld)context.getBean("client");

        String response = client.sayHi("李洪翔");
        System.out.println("Response: " + response);
        System.exit(0);
    }
}

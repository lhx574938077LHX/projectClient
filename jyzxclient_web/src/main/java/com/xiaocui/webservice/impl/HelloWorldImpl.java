package com.xiaocui.webservice.impl;

import javax.jws.WebService;

import com.xiaocui.webservice.HelloWorld;

@WebService(endpointInterface = "com.xiaocui.webservice.HelloWorld")
public class HelloWorldImpl implements HelloWorld {

    public String sayHi(String text) {
        System.out.println("sayHi called");
        return "Hello " + text;
    }
}

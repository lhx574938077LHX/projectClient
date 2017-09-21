package com.xiaocui.webservice;

import javax.xml.ws.Endpoint;

import com.xiaocui.webservice.impl.HelloWorldImpl;

public class Server {
	
	 protected Server() throws Exception {
	        System.out.println("Starting Server");
	        HelloWorldImpl implementor = new HelloWorldImpl();
	        String address = "http://localhost:9002/HelloWorld";
	        Endpoint.publish(address, implementor);
	    }

    public static void main(String args[]) throws Exception {
        new Server();
        System.out.println("Server ready...");

        Thread.sleep(5 * 60 * 1000);
        System.out.println("Server exiting");
        System.exit(0);
    }
	    
}

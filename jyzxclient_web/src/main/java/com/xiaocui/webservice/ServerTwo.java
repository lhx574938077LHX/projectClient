package com.xiaocui.webservice;

import javax.xml.ws.Endpoint;

import com.xiaocui.webservice.impl.SearchServiceImpl;

public class ServerTwo {
	
	 protected ServerTwo() throws Exception {
	        System.out.println("Starting Server");
	        SearchServiceImpl implementor = new SearchServiceImpl();
	        String address = "http://localhost:9003/SearchService";
	        Endpoint.publish(address, implementor);
	    }

	 public static void main(String args[]) throws Exception {
	     new Server();
	     System.out.println("Server ready...");
	
	     Thread.sleep(500 * 60 * 1000);
	     System.out.println("Server exiting");
	     System.exit(0);
	 }
	
}

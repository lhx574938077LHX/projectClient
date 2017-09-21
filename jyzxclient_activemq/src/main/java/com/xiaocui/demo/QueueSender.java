package com.xiaocui.demo;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

public class QueueSender {
	
	public static void main(String[] args) throws Exception {
		ConnectionFactory connectionFactory = new org.apache.activemq.ActiveMQConnectionFactory("tcp://192.168.2.36:61616");
		Connection connection = connectionFactory.createConnection();
		connection.start();
		
		Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
		Destination destination  = session.createQueue("myQueue");
		
		MessageProducer producer = session.createProducer(destination);
		
		for(int i=0;i<3;i++){
			TextMessage message = session.createTextMessage("sendMessage---->"+i);
			Thread.sleep(1000);
			producer.send(message);
		}
		session.commit();
		session.close();
		connection.close();
	}
	
}

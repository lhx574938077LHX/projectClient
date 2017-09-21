package com.xiaocui.demo;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

public class QueueReceiver {
	
	public static void main(String[] args) throws Exception {
		ConnectionFactory connectionFactory = new org.apache.activemq.ActiveMQConnectionFactory("tcp://192.168.2.36:61616");
		Connection connection = connectionFactory.createConnection();
		connection.start();
		
		Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
		Destination destination  = session.createQueue("myQueue");
		
		MessageConsumer consumer = session.createConsumer(destination);
		
		int i=0;
		while(i<3){
			i++;
			TextMessage testMessage = (TextMessage)consumer.receive();
			session.commit();
			System.out.println("收到消息---"+testMessage);
		}
		session.close();
	}
	
}

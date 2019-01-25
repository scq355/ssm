package com.qs.p2p.jms;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by scq on 2018-02-12 10:46:55
 */
public class QueuesAccept {
	public static void main(String[] args) throws Exception {
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
		Connection conn = connectionFactory.createConnection();
		Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
		conn.start();
		//消息目的地
		Destination dest = session.createQueue("gosun");
		//消息消费者
		MessageConsumer consumer = session.createConsumer(dest);
		//接收消息，超时时间为10秒，先手动接受JMS消息，这儿可以用监听
		TextMessage textMessage = (TextMessage) consumer.receive(10 * 1000);
		String text = textMessage.getText();
		System.out.println("接收到的消息为：" + text);
		//关闭通道
		consumer.close();
		session.close();
		conn.close();
	}
}

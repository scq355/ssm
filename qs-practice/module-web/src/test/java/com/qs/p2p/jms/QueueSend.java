package com.qs.p2p.jms;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTempQueue;

import javax.jms.*;

/**
 * Created by scq on 2018-02-12 10:34:07
 */
public class QueueSend {

	public static void main(String[] args) throws JMSException {
		//第一步：根据url创建一个jms Connection
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
		Connection connection = connectionFactory.createConnection();
		connection.start();
		//第二步：根据connection获取session
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		//第三步：消息的目的地
		Destination destination = new ActiveMQTempQueue("scq");
		//第四步：创建消息生产者
		MessageProducer producer = session.createProducer(destination);
		//设置非持久化
		producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
		//第五步：创建消息
		Message message = session.createTextMessage("JMS 告诉你我是scq");
		//第六步：生产者向JMS发送消息到队列
		producer.send(message);
		//第七步：关闭连接
		session.close();
		connection.close();
	}
}

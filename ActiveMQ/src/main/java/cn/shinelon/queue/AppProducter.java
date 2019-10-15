package cn.shinelon.queue;


import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;




public class AppProducter {
	private static final String URL="tcp://127.0.0.1:61616";
	private static final String QueueName="QueueTest";
	public static void main(String[] args) throws JMSException {
		//创建工厂类
		ConnectionFactory connectionFactory=new ActiveMQConnectionFactory(URL);
		//创建连接
		Connection conn=connectionFactory.createConnection();
		//启动连接
		conn.start();
		//创建会话
		Session session=conn.createSession(false,Session.AUTO_ACKNOWLEDGE);
		//创建目的地(消息发送的目标)
		Destination destination=session.createQueue(QueueName);
		//创建消息发布者
		MessageProducer producer= session.createProducer(destination);
		for(int i=0;i<100;i++) {
			//创建消息
			TextMessage message=session.createTextMessage("test"+i);
			producer.send(message);
			System.out.println("发送消息"+i+" "+message.getText());
		}
		//关闭连接
			conn.close();
	}
}

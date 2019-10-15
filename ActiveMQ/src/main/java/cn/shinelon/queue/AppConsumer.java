package cn.shinelon.queue;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class AppConsumer {
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
			//创建消息发送者
			MessageConsumer consumer=session.createConsumer(destination);
			//创建监听器 
			consumer.setMessageListener(new MessageListener() {
				public void onMessage(Message msg) {
					TextMessage message=(TextMessage) msg;
					try {
						System.out.println("接收消息："+" "+message.getText());
					} catch (JMSException e) {
						e.printStackTrace();
					}
				}
			});
			//因为接收消息是异步的，所以不能关闭连接
	}
}

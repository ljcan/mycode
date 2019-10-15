package cn.shinelon.jms;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

public class ProductorServerImpl implements ProductorServer{
	@Autowired
	JmsTemplate jmsTemplate;
	//队列模式
//	@Resource(name="queueDestination")	//因为我们可能在spring容器中注入多个目的地，所以这里使用@Resource注解
	//主题模式，消费者和订阅者
	@Resource(name="topicDestination")
	Destination destination;
	public void SendMessage(final String message) {
		//使用JmsTemplate发送消息
		jmsTemplate.send(destination, new MessageCreator() {
			//创建一个消息
			public Message createMessage(Session session) throws JMSException {
				TextMessage textMessage=session.createTextMessage(message);
				return textMessage;
			}
		});
		System.out.println("发送消息:"+message);
	}
}

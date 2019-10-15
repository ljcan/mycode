package cn.shinelon.jms;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppProducer {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("productor.xml");
		ProductorServer server=context.getBean(ProductorServer.class);
		for(int i=0;i<100;i++) {
			server.SendMessage("test"+i);
		}
		context.close();
	}
}

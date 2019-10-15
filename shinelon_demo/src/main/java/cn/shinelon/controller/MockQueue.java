package cn.shinelon.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 模拟消息队列来接收返回的订单处理结果
 * @author Shinelon
 *
 */
@Component
public class MockQueue {
	private String placeOrder;
	private String completeOrder;
	private Logger log=LoggerFactory.getLogger(getClass());
	public String getPlaceOrder() {
		return placeOrder;
	}
	public void setPlaceOrder(String placeOrder) {
		new Thread(()->{
			log.info("开始处理订单"+placeOrder);
			this.completeOrder = placeOrder;
			log.info("订单处理完毕"+placeOrder);
		}).start();
	}
	public String getCompleteOrder() {
		return completeOrder;
	}
	public void setCompleteOrder(String completeOrder) {
		this.completeOrder = completeOrder;
	}
	
}

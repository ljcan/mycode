package cn.shinelon.controller;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * 设置监听器
 * @author Shinelon
 *
 */
@Component
public class QueueListener implements ApplicationListener<ContextRefreshedEvent>{//整个spring容器初始化完毕的事件
	@Autowired
	private MockQueue mockQueue;
	@Autowired
	private DeferedResultHolder deferedResultHolder;
	private Logger log=LoggerFactory.getLogger(getClass());
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		new Thread(()->{		//放入子线程中
			while(true) {
				if(StringUtils.isNotBlank(mockQueue.getCompleteOrder())) {
					String number=mockQueue.getCompleteOrder();
					log.info("返回订单结果"+number);
					deferedResultHolder.getMap().get(number).setResult("place order success");
					mockQueue.setCompleteOrder(null); 		//防止无限循环
				}else {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					} 			//如果订单号为空的话睡眠100毫秒
				}
			}
		}).start();
	}
	
}

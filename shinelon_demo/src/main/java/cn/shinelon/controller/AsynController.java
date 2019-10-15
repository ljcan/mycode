package cn.shinelon.controller;

import java.util.concurrent.Callable;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
public class AsynController {
	
	@Autowired
	private MockQueue mockQueue;
	@Autowired
	private DeferedResultHolder deferedResultHolder;
	
	
	//使用Callable来处理异步请求
	@RequestMapping("/callable")
	public Callable<String> thread1(){
		System.out.println("主线程开始");
		//使用callable来处理异步请求，它会在主线程中调起一个副线程来处理请求
		Callable<String> result=new Callable<String>() {

			@Override
			public String call() throws Exception {
				System.out.println("副线程开始");
				Thread.sleep(1000);
				System.out.println("副线程结束");
				return "success";
			}
			
		};
		System.out.println("主线程结束");
		return result;
	}
	private Logger log=Logger.getLogger(getClass());
	//使用DeferredResult来处理异步请求
	@RequestMapping("/result")
	public DeferredResult<String> thread2(){
		log.info("主线程开始");
		//生成一个随机订单号
		String order=RandomStringUtils.randomNumeric(8);		//生成一个随机的8为订单号
		mockQueue.setPlaceOrder(order);
		DeferredResult<String> deferredResult=new DeferredResult<String>();
		deferedResultHolder.getMap().put(order, deferredResult);
		log.info("主线程结束");
		return deferredResult;
	}
}

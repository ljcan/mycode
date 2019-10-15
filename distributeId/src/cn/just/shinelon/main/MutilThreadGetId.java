package cn.just.shinelon.main;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import cn.just.shinelon.service.OrderService;
import cn.just.shinelon.service.OrderServiceImpl;

public class MutilThreadGetId {
	
	static OrderService orderService=new OrderServiceImpl();
	
	public static void main(String[] args) {
		ExecutorService service=Executors.newCachedThreadPool();
		final CountDownLatch latch=new CountDownLatch(1);
		service.submit(new Runnable() {
			public void run() {
				try {
					latch.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				String orderId=orderService.getOrderId();
				System.out.println(orderId);
			}
		});
		latch.countDown();
		service.shutdown();
	}

}

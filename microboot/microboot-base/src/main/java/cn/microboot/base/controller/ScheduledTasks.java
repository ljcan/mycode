package cn.microboot.base.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
//定时器
@Component
public class ScheduledTasks {
	private static SimpleDateFormat simpleDateFormat=new SimpleDateFormat("HH:mm:ss");
	@Scheduled(fixedRate=1000)		//以毫秒为单位，一秒一个
	public void time() {
		System.out.println("现在的时间是:"+simpleDateFormat.format(new Date()));
		System.out.println("时间戳是："+new Date());
	}
}

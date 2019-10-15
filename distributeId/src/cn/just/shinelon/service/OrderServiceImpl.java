package cn.just.shinelon.service;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderServiceImpl implements OrderService{
	int num;
	public String getOrderId() {
		SimpleDateFormat dateFormat=new SimpleDateFormat("YYYYmmDDHHMMss");
		return dateFormat.format(new Date())+"_"+num++;
	}

}

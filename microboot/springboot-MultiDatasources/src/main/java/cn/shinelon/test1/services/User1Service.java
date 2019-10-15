package cn.shinelon.test1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.shinelon.test1.dao.User1Dao;
import cn.shinelon.test2.dao.User2Dao;

@Service
public class User1Service {
	@Autowired
	public User1Dao user1Dao;
	
	public void insert(String username,int age) {
		user1Dao.insert(username, age);
	}
	
	//测试分布式事务的处理
	@Autowired
	public User2Dao user2Dao;
	@Transactional
	public void addUser1AndUser2(String username,int age) {
		//因为在数据源1下访问数据源2，他们的事务处理是分离的，所以test2数据源不会回滚
		//在数据源的配置中给test2数据源的配置加了@Primary就是默认访问这个数据源
		user2Dao.insert(username, age);		
		int i=1/0;
		user1Dao.insert(username, age);
	}
	
	
	//测试springboot自带的多线程
	@Async
	public void thread() {
		System.out.println("子线程启动2");
		for(int i=0;i<5;i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("i="+i);
		}
		System.out.println("子线程运行结束3");
	}
}

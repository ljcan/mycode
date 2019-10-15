package cn.shinelon.test2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.shinelon.test2.dao.User2Dao;

@Service
public class User2Service {
	@Autowired
	public User2Dao user2Dao;
	
	public void insert(String username,int age) {
		user2Dao.insert(username, age);
	}
}

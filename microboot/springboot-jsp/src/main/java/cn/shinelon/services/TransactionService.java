package cn.shinelon.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.shinelon.mapper.UserMapper;

@Service
public class TransactionService {
	@Autowired
	public UserMapper userMapper;
	//测试事务
	@Transactional				//添加事务
	public void insert(String username,String pwd) {
		userMapper.insert(username, pwd);
		int i=1/0;			//在不加事务之前，会在数据库中保存，可能造成脏数据，加入事务之后就不会造成脏数据 
	}
}

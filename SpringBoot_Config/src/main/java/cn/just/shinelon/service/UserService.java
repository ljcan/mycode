package cn.just.shinelon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import cn.just.shinelon.dao.UserDao;
import cn.just.shinelon.domain.User;
@Component
@Service
public class UserService {
	@Autowired
	public UserDao userDao;
	public List<User> getList(){
		return this.userDao.getList();
	}
}

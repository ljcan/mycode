package cn.just.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.just.dao.BaseDao;
import cn.just.vo.User;

@Service("userService")
public class UserServiceImpl implements BaseService {
	@Autowired
	public BaseDao userDao;
	@Override
	public <User> void insert(User user) {
		userDao.insert(user);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public <T> void update(Integer id, T t) {
		// TODO Auto-generated method stub

	}

	@Override
	public <T> T select(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String select(cn.just.vo.User user) {
		return userDao.select(user);
	}



	

}

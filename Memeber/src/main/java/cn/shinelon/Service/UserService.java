package cn.shinelon.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.shinelon.Dao.UserDao;
import cn.shinelon.Entity.UserEntity;
@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	public List<UserEntity> getAllUser(){
		return userDao.getAllUser();
	}
	public UserEntity getUser(Integer id) {
		List<UserEntity> list=getAllUser();
		for(int i=0;i<list.size();i++) {
			UserEntity user=list.get(i);
			if(id==user.getId()) {
				return user;
			}
		}
		return null;
	}
}

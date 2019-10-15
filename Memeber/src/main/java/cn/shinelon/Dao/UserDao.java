package cn.shinelon.Dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import cn.shinelon.Entity.UserEntity;
@Repository
public class UserDao {
	public List<UserEntity> getAllUser() {
		List<UserEntity> list=new ArrayList<UserEntity>();
		for(int i=0;i<20;i++) {
		  list.add(new UserEntity(i, "name"+i));
		}
		return list;
	}
}

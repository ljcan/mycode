package cn.just.shinelon.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import cn.just.shinelon.domain.User;
@Component
public class UserDao {
	private List<User> list=new ArrayList<User>();
	public List<User> getList() {
		for(int i=0;i<10;i++) {
			User user=new User();
			user.setId(i);
			user.setName("name"+i);
			list.add(user);
		}
		return list;
	}
}

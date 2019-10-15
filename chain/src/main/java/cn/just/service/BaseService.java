package cn.just.service;

import cn.just.vo.User;

public interface BaseService {
	public <T> void insert(T t);
	public void delete(Integer id);
	public <T> void update(Integer id,T t);
	public <T> T select(Integer id);
	public String select(User user);
}

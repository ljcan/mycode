package cn.just.dao;

import cn.just.vo.User;

/**
 * ��ɾ�Ĳ�Ľӿ�
 * @author Shinelon
 *
 */
public interface BaseDao {
	public <T> void insert(T t);
	public void delete(Integer id);
	public <T> void update(Integer id,T t);
	public <T> T select(Integer id);
	public <T> T select(User user);
	
}

package cn.just.dao.impl;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.just.dao.BaseDao;
import cn.just.vo.User;

@Repository("userDao")
public class UserDaoImpl extends SqlSessionDaoSupport implements BaseDao {
	@Autowired
	@Override
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		// TODO Auto-generated method stub
		super.setSqlSessionFactory(sqlSessionFactory);
	}
	@Override
	public <User> void insert(User user) {
		getSqlSession().insert("mybatis.user.insert", user);
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
	public <String> String  select(Integer id) {
		
		return null;
	}
	@Override
	public <String> String select(User user) {
		String pwd=getSqlSession().selectOne("mybatis.user.selectPwd", user);
		if(pwd==null) {
			pwd=(String) "1";
		}
		return (String)pwd;
	}

}

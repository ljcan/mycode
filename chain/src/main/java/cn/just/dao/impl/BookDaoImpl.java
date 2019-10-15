package cn.just.dao.impl;
import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.just.dao.BookDao;
import cn.just.vo.Book;
@Repository("bookDao")
public class BookDaoImpl extends SqlSessionDaoSupport implements BookDao{
	@Autowired
	@Override
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}
	@Override
	public List<Book> getBook() {
		return getSqlSession().selectList("mybatis.bookSelect");
	}

}

package cn.just.dubbo.server.dao;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import cn.just.dubbo.api.ProductDao;
import cn.just.dubbo.domain.Product;
@Repository("productDao")
public class ProductDaoImpl extends SqlSessionDaoSupport implements ProductDao {
	@Autowired
	@Override
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		// TODO Auto-generated method stub
		super.setSqlSessionFactory(sqlSessionFactory);
	}
	@Override
	public Product select(Integer id) {
		return getSqlSession().selectOne("mybatis.product.select", id);
	}

}

package cn.shinelon.datasource;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;

import cn.shinelon.config.DBConfig1;

@Configuration
@MapperScan(basePackages="cn.shinelon.test2",sqlSessionFactoryRef="test2SqlSessionFactory")
public class test2MybatisConfig {
	//配置数据源
	@Bean(name="test2Datasource")
	public DataSource testDatasource(DBConfig1 config1) throws SQLException {
		MysqlXADataSource mysqlXADataSource=new MysqlXADataSource();
		mysqlXADataSource.setUrl(config1.getUrl());
		mysqlXADataSource.setPinGlobalTxToPhysicalConnection(true);
		mysqlXADataSource.setPassword(config1.getPassword());
		mysqlXADataSource.setUser(config1.getUsername());
		mysqlXADataSource.setPinGlobalTxToPhysicalConnection(true);
		
		AtomikosDataSourceBean atomikosDataSourceBean=new AtomikosDataSourceBean();
		atomikosDataSourceBean.setXaDataSource(mysqlXADataSource);
		atomikosDataSourceBean.setUniqueResourceName("test2Datasource");
		atomikosDataSourceBean.setMinPoolSize(config1.getMinPoolSize());
		atomikosDataSourceBean.setMaxPoolSize(config1.getMaxPoolSize());
		atomikosDataSourceBean.setMaxLifetime(config1.getMaxLifetime());
		atomikosDataSourceBean.setBorrowConnectionTimeout(config1.getBorrowConnectionTimeout());
		atomikosDataSourceBean.setLoginTimeout(config1.getLoginTimeout());
		atomikosDataSourceBean.setMaintenanceInterval(config1.getMaintenanceInterval());
		atomikosDataSourceBean.setMaxIdleTime(config1.getMaxIdleTime());
		return atomikosDataSourceBean;
	}
	
	@Bean(name="test2SqlSessionFactory")
	public SqlSessionFactory testSqlSessionFactory(@Qualifier("test2Datasource")DataSource dataSource) 
			throws Exception {
		SqlSessionFactoryBean bean=new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		//如果还有分页等其他事务
//		bean.setMapperLocations(new PathMatchingResourcePatternResolver().
//				getResources("classpath:mybatis/test1/*.xml"));
		return bean.getObject();
	}
	
	
	@Bean(name="test2SqlSessionTemplate")
	public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("test2SqlSessionFactory")
	SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
}

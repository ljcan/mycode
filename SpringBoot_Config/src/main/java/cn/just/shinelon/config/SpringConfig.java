package cn.just.shinelon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import cn.just.shinelon.dao.UserDao;

@Configuration
@ComponentScan(basePackages="cn.just.shinelon.config")
public class SpringConfig {
	@Bean
	public UserDao getUserDao() {
		return new UserDao();
	}
}

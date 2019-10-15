package cn.shinelon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.shinelon.dao.UserDao;

@RestController
@SpringBootApplication
@EntityScan(basePackages="cn.shinelon.vo")
@EnableJpaRepositories(basePackages="cn.shinelon.dao")
public class UserController {
	@Autowired
	private UserDao userDao;
	@RequestMapping("/index")
	public String select(int id) {
		return userDao.findOne(id).toString();
	}
	public static void main(String[] args) {
		SpringApplication.run(UserController.class, args);
	}
}

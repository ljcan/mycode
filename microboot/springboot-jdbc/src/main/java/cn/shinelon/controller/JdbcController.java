package cn.shinelon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.shinelon.services.JdbcService;

@RestController
public class JdbcController {
	@Autowired
	public JdbcService jdbcService;
	@RequestMapping("/jdbc")
	public String update() {
		jdbcService.select();
		return "insert success";
	}
}

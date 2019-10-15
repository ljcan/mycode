package cn.shinelon.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class JdbcService {
	@Autowired
	public JdbcTemplate jdbcTemplate;
	public void select() {
		System.out.println("插入数据");
		jdbcTemplate.update("insert into test values(1,?)","shinelon");
	}
}

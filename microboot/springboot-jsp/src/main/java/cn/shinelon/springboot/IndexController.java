package cn.shinelon.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.shinelon.mapper.UserMapper;
import cn.shinelon.services.TransactionService;
import cn.shinelon.vo.User;

@SpringBootApplication
@Controller
@MapperScan("cn.shinelon.mapper")
@ComponentScan("cn.shinelon")
public class IndexController {
	
	@RequestMapping("/ws")
	public String ws() {
		return "index";
	}
	
	
	
	
	@RequestMapping("/index")
	public String index() {
		return "index";
	}
	@Autowired
	private UserMapper userMapper;
	
	@ResponseBody
	@RequestMapping("/select")
	public User select(int id) {
		return userMapper.findname(id);
	}
	
	@Autowired
	public TransactionService transactionService;
	
	//测试事务
	@RequestMapping("/insert")
	public String insert(String username,String pwd) {
		transactionService.insert(username, pwd);
		return "insert success";
	}
	
	public static void main(String[] args) {
		SpringApplication.run(IndexController.class, args);
	}
	
	
	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}
}

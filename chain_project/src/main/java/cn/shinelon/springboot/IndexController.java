package cn.shinelon.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.shinelon.mapper.UserMapper;
import cn.shinelon.services.TransactionService;
import cn.shinelon.vo.User;

@SpringBootApplication
@Controller
@MapperScan("cn.shinelon.mapper")
@ComponentScan("cn.shinelon.services")
public class IndexController {
	@RequestMapping("/index")
	public String index(ModelMap model) {
		String name=(String) model.get("name");
		return "index";
	}
	@Autowired
	private UserMapper userMapper;
	
	
	@RequestMapping("/login")
	public String login(ModelMap model) {
		System.out.println("===============----==========");
		model.addAttribute("name", "你好");
		return "index";
	}
	
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
	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}
}

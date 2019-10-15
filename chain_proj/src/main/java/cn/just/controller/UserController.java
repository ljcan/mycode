package cn.just.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.just.service.BaseService;
import cn.just.vo.User;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	public BaseService userService;
	
	
	@RequestMapping("/login")
	public String login(User user) {
		String pwd=userService.select(user);
		if(pwd.equals("1")) {
			return "redirect:../html/login.html";	//密码错误或者不存在该用户
		}
		return "../html/index.html";
	}
	@RequestMapping("/register")
	public String register(User user) {
		System.out.println(user.toString());
		userService.insert(user);
		return "../html/login.html";
	}
}

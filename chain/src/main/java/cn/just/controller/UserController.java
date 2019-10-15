package cn.just.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.just.service.BaseService;
import cn.just.service.LoginService;
import cn.just.vo.User;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	public BaseService userService;
	@Autowired
	public LoginService loginService;
	
	
	@RequestMapping("/login")
	public String login(String username,String pwd) {
		   boolean hasUser=loginService.GetOneByName(username, pwd);
		   if(hasUser){
			   return "redirect:../html/index.html";
		   }else{
			   return "redirect:../html/login.html";
		   }
	}
	@RequestMapping("/register")
	public String register(User user) {
		System.out.println(user.toString());
		userService.insert(user);
		return "../html/login.html";
	}
}

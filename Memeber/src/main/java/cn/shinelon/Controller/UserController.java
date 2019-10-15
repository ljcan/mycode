package cn.shinelon.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.shinelon.Entity.UserEntity;
import cn.shinelon.Service.UserService;

@RestController
public class UserController {
	@Autowired
	private UserService userService;
	@RequestMapping("/getUser")
	@ResponseBody
	public UserEntity getUser(Integer id) {
		return userService.getUser(id);
	}
}

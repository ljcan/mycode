package cn.just.shinelon.app;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import cn.just.shinelon.config.SpringConfig;
import cn.just.shinelon.domain.User;
import cn.just.shinelon.service.UserService;
@Component
public class Test {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(SpringConfig.class);
		UserService userService=context.getBean(UserService.class);
		List<User> list=userService.getList();
		for(User user:list) {
			System.err.println("id="+user.getId()+"  name="+user.getName());
		}
		context.destroy();
	}
	
}

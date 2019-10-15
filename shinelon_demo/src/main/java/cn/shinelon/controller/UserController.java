package cn.shinelon.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.log4j.spi.LoggerFactory;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import com.fasterxml.jackson.annotation.JsonView;

import cn.shinelon.vo.User;
import cn.shinelon.vo.UserQueryCondition;

/**
 * @author Shinelon
 *
 */

@RestController
public class UserController {
	
	Logger logger=org.slf4j.LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ProviderSignInUtils providerSignInUtils;
	
	@PostMapping("/user/regist")
	public void signUpUser(User user, HttpServletRequest request) {
		logger.info("用户注册");
		//不管是注册用户还是绑定用户，都会拿到一个用户唯一标识。
		String userId = user.getUsername();
		//第一次注册会将用户和qq等信息绑定存入数据库表userConnection中
		providerSignInUtils.doPostSignUp(userId, new ServletWebRequest(request));
	}
	
	
	@GetMapping("/me")
	public Object getMeDetail() {
		return SecurityContextHolder.getContext().getAuthentication();
	}
	@GetMapping("/me1")
	public Object getMeDetail(Authentication authentication){
		return authentication;
	}
	@GetMapping("/me2")
	public Object getMeDetail(@AuthenticationPrincipal UserDetails userDetails){
		return userDetails;
	}
	
	//代码的重构，每次都写请求的方式显得繁琐，我们可以通过设置@GetMapping和@PostMappin等RequestMapping的变体来指定请求方式下面的代码和Requestmapping这个注解的作用一样
	@GetMapping("/user")
	//@RequestMapping(value="/user",method=RequestMethod.GET)
	
	
	//required表示是否是必须要填的，false表示不需要，然后defaultValue表示默认值
//	public List<User> query(@RequestParam(required=false,defaultValue="jerrty") String username){
	//当前台需要传来多个值的时候，可以把参数封装到一个对象中
	
	//pageable表示分页的信息                 同样的，如果前台没有传来数据，也可以给分页信息来设置默认值
	@JsonView(User.SimpleJsonView.class)			//3.在controller上指定视图
	public List<User> query(UserQueryCondition condition,@PageableDefault(size=15,page=3,sort="username.asc") Pageable pageable){
		
		System.out.println(pageable.getPageSize());
		System.out.println(pageable.getPageNumber());
		System.out.println(pageable.getSort());
		
//		System.out.println(list.username);
//		System.out.println(list.age);
//		System.out.println(list.sex);
//		System.out.println(list.address);
		System.out.println(ReflectionToStringBuilder.toString(condition,ToStringStyle.MULTI_LINE_STYLE));
		
		List<User> list=new ArrayList<User>();
		list.add(new User());
		list.add(new User());
		list.add(new User());
		return list;
	}
	//\\d+这个正则表达式表示接受的参数是整型
	@GetMapping("/user/{id:\\d+}")
	//@RequestMapping(value="/user/{id:\\d+}",method=RequestMethod.GET)
	@JsonView(User.DetailJsonView.class)
	public User get(@PathVariable String id) {
//		throw new RuntimeException();
//		throw new UserNotExistException(id);
		User user=new User();
		return user;
	}
	
	
	//使用JsonView的步骤
	//使用接口来声明多个视图
	//在值对象的get方法上指定视图
	//在controller方法上指定视图
	
	@PostMapping("/user")
//	@JsonView(User.SimpleJsonView.class)
	//@Valid和BindingResult一起使用，如果没有BindingResult则当验证发生错误的时候不会进入到方法体中
	public User create(@Valid @RequestBody User user,BindingResult errors) {
		
		if(errors.hasErrors()) {
			errors.getAllErrors().forEach(error->System.out.println(error.getDefaultMessage()));;
		}
		
		user.setId(1);
		System.out.println(user.getId());
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		System.out.println(user.getBirthday());
		return user;
	}
	@PutMapping("/user/{id:\\d+}")
//	@JsonView(User.SimpleJsonView.class)
	//@Valid和BindingResult一起使用，如果没有BindingResult则当验证发生错误的时候不会进入到方法体中
	public User update(@Valid @RequestBody User user,BindingResult errors) {
		
		if(errors.hasErrors()) {
			//1.
//			errors.getAllErrors().forEach(error->System.out.println(error.getDefaultMessage()));;
			//2.
			//			errors.getAllErrors().forEach(error->{
//				FieldError fieldError=(FieldError) error;
//				String message=fieldError.getField()+":"+fieldError.getDefaultMessage();
//				System.out.println(message);
//			});
			errors.getAllErrors().forEach(error->{
			System.out.println(error.getDefaultMessage());
			});
		}
		
		user.setId(1);
		System.out.println(user.getId());
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		System.out.println(user.getBirthday());
		return user;
	}
	
	@DeleteMapping("/user/{id:\\d+}")
	public void delete(@PathVariable int id) {
		System.out.println("删除的用户的id为:"+id);
	}
	
	
	
	
}

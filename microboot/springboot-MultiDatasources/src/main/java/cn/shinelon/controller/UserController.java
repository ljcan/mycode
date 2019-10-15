package cn.shinelon.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.shinelon.config.DBConfig1;
import cn.shinelon.config.DBConfig2;
import cn.shinelon.entity.User;
import cn.shinelon.test1.dao.User1Dao;
import cn.shinelon.test1.services.User1Service;
import cn.shinelon.test2.services.User2Service;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages={"cn.shinelon.aop","cn.shinelon.config","cn.shinelon.datasource",
		"cn.shinelon.test1","cn.shinelon.test2"})
@RestController
@EnableConfigurationProperties(value= {DBConfig1.class,DBConfig2.class})
@EnableCaching		//支持缓存
@EnableScheduling     //支持定时器
@EnableAsync        //支持多线程异步运行
@EnableSwagger2
public class UserController {
	//测试日志
//	private static Logger log=Logger.getLogger(UserController.class);
	
	
	@Autowired
	public User1Service user1Service;
	@Autowired
	public User2Service user2Service;
	@RequestMapping("/add")
	@ApiOperation(value="增加用户")		//相当添加注解，在swagger生成的文档中会有这个中文注释
	public String insert(@ApiParam(value="姓名") String username,int age) {
		user1Service.insert(username, age);
		user2Service.insert(username, age);
		return "insert success";
	}
	
	@RequestMapping("/addUser1andUser2")
	public String addAll(String username,int age) {
		user1Service.addUser1AndUser2(username, age);
		return "yes";
	}
	
	@RequestMapping("/log")
	public String log() {
//		log.info("启动日志");
		return "success";
	}
	@ResponseBody
	@RequestMapping("/aop")
	public String LogAop(String name) {
		return "success";
	}
	
	//测试缓存
	@Autowired
	private User1Dao user1Dao;
	@ResponseBody
	@RequestMapping("/cache")
	public String cache(int id) {
		return user1Dao.select(id).toString();
	}
	//清除缓存
	@Autowired
	private CacheManager cacheManager;
	@ResponseBody
	@RequestMapping("/clear")
	public String clear() {
		cacheManager.getCache("ehcache").clear();
		return "success";
	}
	//使用异步处理
	@ResponseBody
	@RequestMapping("/thread")
	public String thread() {		//当使用线程异步运行的时候，主线程先执行，以为子线程还需要CPU调度，消耗时间
		System.out.println("主线程开启 1");
		user1Service.thread();
		System.out.println("主线程结束4");
		return "success";
	}
	
	//读取配置文件中的信息
	@Value("${name}")
	public  String name;
	@ResponseBody
	@RequestMapping("/value")
	public String name() {
		return name;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(UserController.class, args);
	}
}

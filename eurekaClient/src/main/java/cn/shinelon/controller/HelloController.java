package cn.shinelon.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableAutoConfiguration
@EnableEurekaClient
@RestController
public class HelloController {
	public static void main(String[] args) {
		SpringApplication.run(HelloController.class, args);
	}
	
	@Value("${server.port}")
	private String port;
	
	@RequestMapping("/hello")
	public String hello(){
		return "hello";
	}
	
	
	private final Logger logger=Logger.getLogger(getClass());
	
	@Autowired
	DiscoveryClient client;
	
	@RequestMapping("/test")
	public String test(String index) {
		ServiceInstance instance=client.getLocalServiceInstance();
		logger.info("host:"+instance.getHost()+",service_id:"+instance.getServiceId());
		return "success";
	}
	
	
	
}

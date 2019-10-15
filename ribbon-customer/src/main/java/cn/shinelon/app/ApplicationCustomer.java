package cn.shinelon.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@EnableAutoConfiguration
@EnableDiscoveryClient
@SpringBootApplication
@RestController
public class ApplicationCustomer {
	
	@Bean
	@LoadBalanced
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
	public static void main(String[] args) {
		SpringApplication.run(ApplicationCustomer.class, args);
	}
	
	
	@Autowired
	RestTemplate restTemplate;
	@RequestMapping("/customer")
	public String helloCustomer() {
		return restTemplate.getForEntity("http://SERVICE-HI/hello", String.class).getBody();
	}
	@RequestMapping("/test")
	public String test() {
		return "test";
	}
}

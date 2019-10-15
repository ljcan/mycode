/**
 * 
 */
package cn.shinelon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.swagger2.annotations.EnableSwagger2;



/**
 * @author Shinelon
 *
 */
//告诉这是一个springboot项目
@SpringBootApplication
@RestController
@EnableSwagger2		//引入swagger工具
public class DemoApplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	@GetMapping("/hello")
	public String hello() {
		return "hello spring security";
	}

}

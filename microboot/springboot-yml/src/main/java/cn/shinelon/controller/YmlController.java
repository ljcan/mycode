package cn.shinelon.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@RestController
@EnableSwagger2
public class YmlController {
	@RequestMapping("/index")
	public String index() {
		return "success";
	}
	public static void main(String[] args) {
		SpringApplication.run(YmlController.class, args);
	}
}

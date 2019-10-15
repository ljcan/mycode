package cn.shinelon.stater;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan({"cn.shinelon.controller","cn.shinelon.services"})
public class AppSpringBootStarter {
	public static void main(String[] args) {
		SpringApplication.run(AppSpringBootStarter.class, args);
	}
}

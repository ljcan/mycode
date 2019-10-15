package cn.just.service.impl;

import org.dubbo.demo.api.HelloWorld;

import cn.just.service.HelloWorldService;

public class HelloWorldServiceImpl implements HelloWorldService{
	//业务处理
	@Override
	public HelloWorld helloworld(HelloWorld hello) {
		hello.setName("shinelon");
		return hello;
	}
}

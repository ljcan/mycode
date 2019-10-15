package org.dubbo.demo.server;

import java.io.IOException;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;

import cn.just.service.HelloWorldService;
import cn.just.service.impl.HelloWorldServiceImpl;

/**
 * Hello world!
 *
 */
public class App {
	HelloWorldServiceImpl helloWorldService=new HelloWorldServiceImpl();
	private void helloworld() {
		//当前应用配置
    	ApplicationConfig applicationConfig = new ApplicationConfig();
    	//当前应用名(不能用空格)
    	applicationConfig.setName("helloWorld");
       
    	//注册中心配置
    	RegistryConfig registryConfig = new RegistryConfig();
    	//设置注册中心地址
//    	registryConfig.setAddress("multicast://224.5.6.7:1234");
    	registryConfig.setAddress("zookeeper://127.0.0.1:2181");
    	//registryConfig.set
    	
    	//服务提供者协议配置
    	ProtocolConfig protocolConfig = new ProtocolConfig();
    	//协议名
    	protocolConfig.setName("dubbo");
    	//协议端口
    	protocolConfig.setPort(20880);
    	
    	//服务配置
    	ServiceConfig<HelloWorldService> serviceConfig = new ServiceConfig<HelloWorldService>();
    	//设置当前应用配置
    	serviceConfig.setApplication(applicationConfig);
    	//设置注册中心
    	serviceConfig.setRegistry(registryConfig);
    	//设置协议配置
    	serviceConfig.setProtocol(protocolConfig);
    	//添加服务的接口字节码
    	serviceConfig.setInterface("cn.just.service.HelloWorldService");
    	//添加服务接口的具体实现类
    	serviceConfig.setRef(helloWorldService);
    	
    	//把服务发布出去
    	serviceConfig.export();
    	System.out.println("服务启动");
        try { 
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
	}
	
    public static void main( String[] args )
    {
//        System.out.println( "Hello World!" );
    	new App().helloworld();
    }
}

package org.dubbo.demo.client;
import org.dubbo.demo.api.HelloWorld;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;

import cn.just.service.HelloWorldService;

/**
 * Hello world!
 *
 */
public class App {
	private void helloworld() {
		 //应用配置
		ApplicationConfig applicationConfig = new ApplicationConfig();
		applicationConfig.setName("helloWorldConsumer");
		
		//连接注册中心
		RegistryConfig registryConfig = new RegistryConfig();
		//设置注册中心地址
//		registryConfig.setAddress("multicast://224.5.6.7:1234");
		registryConfig.setAddress("zookeeper://127.0.0.1:2181");
		//引用远程服务地址
		ReferenceConfig<HelloWorldService> referenceConfig = new ReferenceConfig<HelloWorldService>();
		referenceConfig.setApplication(applicationConfig);
		referenceConfig.setRegistry(registryConfig);
		referenceConfig.setInterface("cn.just.service.HelloWorldService");
		
		//和本地一样调用服务
		HelloWorldService helloWorldService = referenceConfig.get();
		HelloWorld helloWorld=new HelloWorld();
		helloWorld=helloWorldService.helloworld(helloWorld);
		System.out.println("=======================");
		System.out.println(helloWorld.getName());
	}
    public static void main( String[] args )
    {
    	new App().helloworld();
    }
}

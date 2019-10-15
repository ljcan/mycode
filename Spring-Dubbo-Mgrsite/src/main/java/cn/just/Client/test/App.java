package cn.just.Client.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.just.dubbo.api.ProductService;
import cn.just.dubbo.domain.Product;

public class App {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("application-client.xml");
		context.start();
		ProductService productService=(ProductService) context.getBean("ProductService");
		Product product=new Product();
		product=productService.getProduct(product);
		System.out.println(product.getName());
	}
}

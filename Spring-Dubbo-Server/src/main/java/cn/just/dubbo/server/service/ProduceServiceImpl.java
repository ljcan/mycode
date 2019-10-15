package cn.just.dubbo.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import cn.just.dubbo.api.ProductDao;
import cn.just.dubbo.api.ProductService;
import cn.just.dubbo.domain.Product;
@Service("produceService")
public class ProduceServiceImpl implements ProductService{
	@Autowired
	public ProductDao productDao;

	@Override
	public Product select(Integer id) {
		Product p=productDao.select(id);
		System.out.println(p.toString());
		return p;
	}
	
}

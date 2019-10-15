package cn.just.dubbo.api;

import cn.just.dubbo.domain.Product;

public interface ProductDao {
	public Product select(Integer id);
}

/**
 * 
 */
package cn.shinelon.service.impl;

import org.springframework.stereotype.Service;

import cn.shinelon.service.HelloService;

/**
 * @author Shinelon
 *
 */
@Service
public class HelloServiceImpl implements HelloService {
	/* (non-Javadoc)
	 * @see cn.shinelon.service.HelloService#hello()
	 */
	@Override
	public void hello(String name) {
		System.out.println("hello"+name);
	}
}

package cn.microboot.base;


import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import junit.framework.TestCase;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=StartSpringBootMain.class)
@WebAppConfiguration		//使得程序以web应用 运行
public class TestSampleController {
	//这两个都是自动注入的意思
	//@Autowired
	@Resource
	private StartSpringBootMain sampleController;
	@Test
	public void testHome() {
//		TestCase.assertEquals(this.sampleController.home(), "Hello World!");
	}
}

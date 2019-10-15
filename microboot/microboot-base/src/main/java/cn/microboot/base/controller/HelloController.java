package cn.microboot.base.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.microboot.base.exception.MyException;
//@Controller
@RestController		//表示这个控制器所有的返回值按照rest方式处理
public class HelloController {
//	地址重写
//	@RequestMapping("/echo")
//	public String echo(String msg) {
//		return "【ECHO】"+msg;
//	}
	
	//地址传递，rest风格
	@RequestMapping(value="/echo/{message}",method=RequestMethod.GET)
	public String echo(@PathVariable("message") String msg) {
		return "【ECHO】"+msg;
	}
	
	@RequestMapping("/mul")
	public Object coun(int num) {
		return num*10;
	}
	
	//springboot获得jsp的内置对象 ，和springmvc一样
	@RequestMapping("/object")
	public String obj(HttpServletRequest request,HttpServletResponse response) {
		System.out.println("***IP地址："+request.getRemoteAddr());
		System.out.println("***sessionId:"+request.getSession().getId());
		System.out.println("***session:"+request.getSession());
		System.out.println("***获取路径："+request.getServletContext().getRealPath("/upload"));
		return "object";
	}

	  @RequestMapping("/")
//	  @ResponseBody		//在restful的架构中，该注解是将返回值作为字符串或者json的格式直接返回
	  public String home() {
	      return "Hello World!";
	  }
	  
	  
	  //测试捕获异常
	  @RequestMapping("/exception")
	  public String exp() {
		  try {
		  int i=1/0;
		  }catch (Exception e) {
			  throw new MyException();
		  }
		  return "catched exception success";
	  }
	  
}

//package cn.shinelon.aop;
//
//import java.util.Enumeration;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.aopalliance.intercept.Joinpoint;
//import org.aspectj.lang.annotation.After;
//import org.aspectj.lang.annotation.AfterReturning;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//@Aspect
//@Component
//public class WebLogAspect {
////	public static Logger log=Logger.getLogger(WebLogAspect.class);
//	public org.slf4j.Logger log=LoggerFactory.getLogger(this.getClass());
//	@Pointcut("execution(public * cn.shinelon.controller.*.*(..))")
//	public void weblog() {
//	}
//	
//	@Before("weblog()")
//	public void bofore(Joinpoint joinpoint) {
//		//接受请求，记录请求内容
//		ServletRequestAttributes servletRequestAttributes=(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//		HttpServletRequest request=servletRequestAttributes.getRequest();
//		//记录请求的内容
//		log.info("request addr:"+request.getRemoteAddr());
//		log.info("request url:"+request.getRequestURI());
//		log.info("request methodname:"+request.getMethod());
//		log.info("request param:"+request.getParameterNames());
//		Enumeration<String> enumeration=request.getParameterNames();
//		while(enumeration.hasMoreElements()) {
//			String name=enumeration.nextElement();
//			log.info("name:{},value:{}",name,request.getParameter(name));
//		}
//	}
//	@After("weblog()")
//	public void after() {
//		log.info("===============");
//	}
//	
//	
//	@AfterReturning(returning="ref",pointcut="weblog()")
//	public void afterReturn(Object ref) {
//		//处理完请求，返回内容
//		log.info("RESPONSE"+ref);
//	}
//	
//}

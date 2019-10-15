package cn.shinelon.config;

import java.util.ArrayList;
import java.util.List;

import org.aopalliance.intercept.Interceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import cn.shinelon.filter.MyFilter;
import cn.shinelon.interceptor.TimeInteceptor;

/**
 * @author Shinelon
 *
 */
@Configuration
public class FilterConfig extends WebMvcConfigurerAdapter{
	//设置支持异步请求
//	@Override
//		public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
//			// TODO Auto-generated method stub
//			super.configureAsyncSupport(configurer);
//		}
	
	@Autowired
	public TimeInteceptor timeInteceptor;
	
	
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(timeInteceptor);
	}
//	@Override
//	public void addInterceptors(Interceptor registry) {
//		registry.addInterceptor(timeInteceptor);
//	}


	@Bean
	public FilterRegistrationBean myFilter() {
		FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean();
		MyFilter myFilter=new MyFilter();
		filterRegistrationBean.setFilter(myFilter);
		List<String> urls=new ArrayList<String>();
		urls.add("/*"); 					//过滤所有请求
		filterRegistrationBean.setUrlPatterns(urls);
		return filterRegistrationBean;
	}
}

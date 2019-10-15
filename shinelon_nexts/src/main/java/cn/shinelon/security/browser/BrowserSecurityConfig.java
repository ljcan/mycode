package cn.shinelon.security.browser;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.social.security.SpringSocialConfigurer;

import cn.shinelon.security.authenticate.ImoocAuthenctiationFailureHandler;
import cn.shinelon.security.authenticate.ImoocAuthenticationSuccessHandler;
import cn.shinelon.security.core.authentication.SmsAuthenticationFilter;
import cn.shinelon.security.core.authentication.SmsAuthenticationSecurityConfig;
import cn.shinelon.security.core.properties.SecurityProperties;
import cn.shinelon.security.core.validate.code.SmsCodeFilter;
import cn.shinelon.security.core.validate.code.ValidateCodeFilter;
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter{
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new SCryptPasswordEncoder();
	}
	@Autowired
	//因为跳转的页面是登录页面，所以也需要授权认证
	private SecurityProperties securityproperties;
	
	@Autowired
	private ImoocAuthenticationSuccessHandler imoocAuthenticationSuccessHandler;
	@Autowired
	private ImoocAuthenctiationFailureHandler imoocAuthenctiationFailureHandler;
	
	@Autowired
	private SmsAuthenticationSecurityConfig smsAuthenticationSecurityConfig;
	
	@Autowired
	private SpringSocialConfigurer socialSecurityConfig;
	
	@Autowired
	private DataSource dataSource;
	
	//记住我后的登录页面
	@Autowired
	private UserDetailsService userDetailsService;
	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.formLogin()
////		http.httpBasic()
//			.and()
//			.authorizeRequests()
//			.anyRequest()
//			.authenticated();
//	}
//	
	//记住我的功能
	@Bean
	public PersistentTokenRepository getPersistentTokenRepository() {
		JdbcTokenRepositoryImpl jdbcTokenRepositoryImpl=new JdbcTokenRepositoryImpl();
		jdbcTokenRepositoryImpl.setDataSource(dataSource);
		//启动时创建一张表
//		jdbcTokenRepositoryImpl.setCreateTableOnStartup(true);
		return jdbcTokenRepositoryImpl;
	}
	
	
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		ValidateCodeFilter validateCodeFilter=new ValidateCodeFilter();
		validateCodeFilter.setAuthenticationFailureHandler(imoocAuthenctiationFailureHandler);
		
		SmsCodeFilter smsCodeFilter=new SmsCodeFilter();
		smsCodeFilter.setAuthenticationFailureHandler(imoocAuthenctiationFailureHandler);
		
//		http.formLogin()
//		.loginPage("/authentication/required")
//		.loginProcessingUrl("/autentication/forms")
//		.and()
//		.authorizeRequests()
//		.antMatchers("/authentication/required",
//				securityproperties.getBrowser().getLoginPage()
//				).permitAll()		//因为所有的请求都会需要认证处理，所以允许访问这个页面
//		.antMatchers("/login.html").permitAll()
//		.anyRequest()
//		.authenticated()
//		.and()
//		.csrf().disable();		//取消放置csrf攻击的防护;
//		
		
		//通过表单认证登录，对于所有的请求都需要认证	
//		http.httpBasic()
		
		http.addFilterBefore(smsCodeFilter, UsernamePasswordAuthenticationFilter.class)
			.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
			.formLogin()
			.loginPage("/login.html")
			.loginPage("/authentication/required")
			.loginProcessingUrl("/autentication/forms")
			.successHandler(imoocAuthenticationSuccessHandler)
			.failureHandler(imoocAuthenctiationFailureHandler)
			.and()
			.rememberMe() 
			.tokenRepository(getPersistentTokenRepository())
			.tokenValiditySeconds(securityproperties.getBrowser().getRemeberMeSeconds())
			.userDetailsService(userDetailsService)
			.and()
			.authorizeRequests()
			.antMatchers("/authentication/required","/authentication/mobile",
					securityproperties.getBrowser().getLoginPage(),
					"/code/*",
					"/user/regist",
					securityproperties.getBrowser().getSignUpUrl()
					).permitAll()		//因为所有的请求都会需要认证处理，所以允许访问这些页面
			.antMatchers("/login.html").permitAll()
			.anyRequest()
			.authenticated()
			.and()
			.apply(socialSecurityConfig)		//加入第三方登录授权认证
			.and()
			.csrf().disable()		//取消放置csrf攻击的防护
			.apply(smsAuthenticationSecurityConfig)
			;
	}
}

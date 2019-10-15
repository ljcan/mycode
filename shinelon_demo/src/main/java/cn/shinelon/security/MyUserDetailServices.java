package cn.shinelon.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;
@Component
public class MyUserDetailServices implements UserDetailsService,SocialUserDetailsService{
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private Logger log=LoggerFactory.getLogger(getClass());
	/**
	 * 从数据库中获取密码与前端提交的密码进行对比
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("表单登录用户名："+username);
		//指定用户授权登录信息,这里密码指定了，实际中需要到数据库中验证密码
		return buildUser(username);
	}
	@Override
	public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
		log.info("社交登录用户ID："+userId);
		//指定用户授权登录信息,这里密码指定了，实际中需要到数据库中验证密码
		return buildUser(userId);
	}
	private SocialUserDetails buildUser(String username) {
		String password=passwordEncoder.encode("123456");
		log.info("数据库中密码是："+password);
		return new SocialUser(username, password,
				true,true,true,true,		//用户被锁定		
				AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
	}
}

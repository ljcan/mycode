package cn.shinelon.security.browser;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;

import cn.shinelon.security.core.properties.SecurityProperties;
import cn.shinelon.security.core.validate.code.ValidateCode;
import cn.shinelon.security.support.SimpleObject;
import cn.shinelon.security.support.SocialUserInfo;
@RestController
public class BrowserSecurityController {
	private Logger log=LoggerFactory.getLogger(getClass());
	//将请求放入缓存中
	private RequestCache requestCache=new HttpSessionRequestCache();
	//重定向到一个页面
	private RedirectStrategy redirectStrategy=new DefaultRedirectStrategy();
	//Spring的工具类
	private SessionStrategy sessionStrategy=new HttpSessionSessionStrategy();
	
	@Autowired
	private SecurityProperties securityProperties;
	
	//注入Spring Social提供的一个工具类
	@Autowired
	private ProviderSignInUtils providerSignInUtils;
	
	/**
	 * 当需要身份认证的时候，跳转到这里
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/authentication/required")
	@ResponseStatus(code=HttpStatus.UNAUTHORIZED)	//返回未授权的状态码
	public SimpleObject sendRedict(HttpServletRequest request,HttpServletResponse response) throws IOException {
		SavedRequest savedRequest=requestCache.getRequest(request, response);
		requestCache.removeRequest(request, response);
		if(savedRequest!=null) {
			String targetUrl=savedRequest.getRedirectUrl();
			log.info("请求的URL是："+targetUrl);
			if(StringUtils.endsWithIgnoreCase(targetUrl, ".html")) {
				redirectStrategy.sendRedirect(request, response, securityProperties.getBrowser().getLoginPage());
			}
		}
		return new SimpleObject("你访问的页面不存在，请引导用户到登录页面");
	}
	@RequestMapping("/authentication/mobile")
	public String mobileCode(HttpServletRequest request,HttpServletResponse response) {
		String smsCode=request.getParameter("smsCode");
		ValidateCode validateCode=(ValidateCode) sessionStrategy.getAttribute(
				new ServletWebRequest(request), "MOBILE_SESSION_KEY");
		if(null!=validateCode.getCode()) {
			if(validateCode.getCode().equals(smsCode)) {
				return "短信验证成功";
			}
			return "短信验证码为空";
		}
		return "短信验证码失败";
	}
	
	@GetMapping("/social/user")
	public SocialUserInfo getSocialUserInfo(HttpServletRequest request) {
		SocialUserInfo userInfo = new SocialUserInfo();
		Connection<?> connection = providerSignInUtils.getConnectionFromSession(new ServletWebRequest(request));
		userInfo.setProviderId(connection.getKey().getProviderId());
		userInfo.setProviderUserId(connection.getKey().getProviderUserId());
		userInfo.setNickname(connection.getDisplayName());
		userInfo.setHeadimg(connection.getImageUrl());
		return userInfo;
	}
	
}

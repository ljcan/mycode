package cn.shinelon.security.core.validate.code;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;
//OncePerRequestFilter是Spring提供的一个工具类保证过滤器链每次只被调用一次
public class SmsCodeFilter extends OncePerRequestFilter {
	
	private AuthenticationFailureHandler authenticationFailureHandler;
	
	private SessionStrategy sessionStrategy=new HttpSessionSessionStrategy();

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		//登录请求必须是/autentication/forms路径，并且是post请求
		if(StringUtils.equals("/authentication/mobile",request.getRequestURI())&&
				StringUtils.equalsIgnoreCase("post", request.getMethod())) {
			try {
				validate(new ServletWebRequest(request));
			}catch (ValidateCodeException e) {
				//如果发生异常则返回抛出该异常
				authenticationFailureHandler.onAuthenticationFailure(request, response, e);
				return;		//图片验证没有通过，直接返回，不再调用后面的过滤器
			}
		}
		filterChain.doFilter(request, response);
	}
	/**
	 * 处理验证码校验逻辑
	 * @param servletWebRequest
	 * @throws ServletRequestBindingException 
	 */
	private void validate(ServletWebRequest request) throws ServletRequestBindingException {
		ValidateCode codeInSession=(ValidateCode) sessionStrategy.getAttribute(request, 
				ValidateCodeController.MOBILE_SESSION_KEY);
		String codeInRequest=ServletRequestUtils.getStringParameter(request.getRequest(), "smsCode");
		if(StringUtils.isBlank(codeInRequest)) {
			throw new ValidateCodeException("验证码为空");
		}
		if(codeInSession==null) {
			throw new ValidateCodeException("验证码不存在");
		}
		if(!StringUtils.equals(codeInSession.getCode(), codeInRequest)) {
			throw new ValidateCodeException("验证码不匹配");
		}
		if(codeInSession.isExpried()) {
			//如果验证码过期，则从session中移除掉过期的验证码
			sessionStrategy.removeAttribute(request, ValidateCodeController.SESSION_KEY);
			throw new ValidateCodeException("验证码过期");
		}
		sessionStrategy.removeAttribute(request, ValidateCodeController.SESSION_KEY);
	}
	public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
		this.authenticationFailureHandler = authenticationFailureHandler;
	}
	public AuthenticationFailureHandler getAuthenticationFailureHandler() {
		return authenticationFailureHandler;
	}
	public void setSessionStrategy(SessionStrategy sessionStrategy) {
		this.sessionStrategy = sessionStrategy;
	}
	public SessionStrategy getSessionStrategy() {
		return sessionStrategy;
	}

}

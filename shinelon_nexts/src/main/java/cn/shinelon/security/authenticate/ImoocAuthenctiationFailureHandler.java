/**
 * 
 */
package cn.shinelon.security.authenticate;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import cn.shinelon.security.core.properties.SecurityProperties;
import cn.shinelon.security.support.SimpleObject;

/**
 * @author zhailiang
 *
 */
@Component("imoocAuthenctiationFailureHandler")
public class ImoocAuthenctiationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private SecurityProperties securityProperties;

	
	/* (non-Javadoc)
	 * @see org.springframework.security.web.authentication.AuthenticationFailureHandler#onAuthenticationFailure(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.AuthenticationException)
	 */
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		logger.info("登录失败");
		response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write(objectMapper.writeValueAsString(new SimpleObject(exception.getMessage())));
//		if (LoginType.JSON.equals(securityProperties.getBrowser().getLoginPage())) {
//			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
//			response.setContentType("application/json;charset=UTF-8");
//			response.getWriter().write(objectMapper.writeValueAsString(new SimpleObject(exception.getMessage())));
//		}else{
//			super.onAuthenticationFailure(request, response, exception);
//		}
		
		
	}

}

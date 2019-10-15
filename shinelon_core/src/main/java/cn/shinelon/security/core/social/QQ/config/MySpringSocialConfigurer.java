package cn.shinelon.security.core.social.QQ.config;

import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

public class MySpringSocialConfigurer extends SpringSocialConfigurer{
	
	private String filterProcessesUrl;
	
	public MySpringSocialConfigurer(String filterProcessesUrl) {
		this.filterProcessesUrl=filterProcessesUrl;
	}
	
	@Override
	protected <T> T postProcess(T object) {
		SocialAuthenticationFilter authenticationFilter=(SocialAuthenticationFilter) super.postProcess(object);
		authenticationFilter.setFilterProcessesUrl(filterProcessesUrl);
		return (T) authenticationFilter;
	}
}

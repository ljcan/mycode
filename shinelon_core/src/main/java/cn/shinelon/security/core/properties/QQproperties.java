package cn.shinelon.security.core.properties;

import org.springframework.boot.autoconfigure.social.SocialProperties;

public class QQproperties extends SocialProperties{
	
	private String providerId="qq";		//服务提供商的唯一标识

	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}
	
}

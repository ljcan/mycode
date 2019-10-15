package cn.shinelon.security.core.social.QQ.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

import cn.shinelon.security.core.properties.QQproperties;
import cn.shinelon.security.core.properties.SecurityProperties;
import cn.shinelon.security.core.social.QQ.connect.QQConnectionFactory;
@Configuration
//当配置文件中配置了这个选项的时候才会生效
@ConditionalOnProperty(prefix="cn.shinelon.security.socialProperties.qqProperties",name="app-id")
public class QQAutoConfig extends SocialAutoConfigurerAdapter {
	
	@Autowired
	private SecurityProperties securityProperties;
	
	@Override
	protected ConnectionFactory<?> createConnectionFactory() {
		QQproperties qQproperties=securityProperties.getSocialProperties().getQqProperties();
		return new QQConnectionFactory(qQproperties.getProviderId(), qQproperties.getAppId(), qQproperties.getAppSecret());
	}
	
	
}

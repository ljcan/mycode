package cn.shinelon.security.core.social.QQ.connect;

import org.springframework.social.connect.support.OAuth2ConnectionFactory;

import cn.shinelon.security.core.social.QQ.QQApi.QQ;

public class QQConnectionFactory extends OAuth2ConnectionFactory<QQ>{

	public QQConnectionFactory(String providerId,String appId,String appSecret) {
		super(providerId, new QQServiceProvider(appId, appSecret), new QQApiAdapter());
	}

}

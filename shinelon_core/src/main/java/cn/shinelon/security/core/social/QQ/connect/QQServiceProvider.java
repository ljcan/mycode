package cn.shinelon.security.core.social.QQ.connect;

import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Template;

import cn.shinelon.security.core.social.QQ.QQApi.QQ;
import cn.shinelon.security.core.social.QQ.QQApi.QQImpl;

public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ>{

	private String appId;
	// 获取Authorization Code
	//将用户导向服务器认证的地址
	private static final String URL_AUTHORIZE = "https://graph.qq.com/oauth2.0/authorize";
	// 通过Authorization Code获取Access Token
	//获取授权码后获取Token的地址
	private static final String URL_ACCESS_TOKEN = "https://graph.qq.com/oauth2.0/token";
	
	
	public QQServiceProvider(String appId,String appSecret) {
		super(new QQOAuth2Template(appId, appSecret, URL_AUTHORIZE, URL_ACCESS_TOKEN));
		this.appId=appId;
	}

	@Override
	public QQ getApi(String accessToken) {
		return new QQImpl(accessToken, appId);
	}

}

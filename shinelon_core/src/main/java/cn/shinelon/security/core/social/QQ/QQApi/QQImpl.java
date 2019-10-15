package cn.shinelon.security.core.social.QQ.QQApi;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

public class QQImpl extends AbstractOAuth2ApiBinding implements QQ{
	
	private static final String URL_GET_OPENID = "https://graph.qq.com/oauth2.0/me?access_token=%s";
	
	private static final String URL_GET_USERINFO = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";
	
	public String appId;
	
	public String openId;
	
	public ObjectMapper objectMapper=new ObjectMapper();
	
	public QQImpl(String accessToken,String openId) {
		super(accessToken,TokenStrategy.ACCESS_TOKEN_PARAMETER);
		
		this.appId=appId;
		
		String url=String.format(URL_GET_OPENID, accessToken);
		//将请求的结果转化为字符串
		String result=getRestTemplate().getForObject(url, String.class);
		
		System.out.println(result);
		//从返回结果中截取用户的openId
		this.openId = StringUtils.substringBetween(result, "\"openid\":\"", "\"}");
		
	}
	
	@Override
	public QQUserInfo getQQInfo() {
		
		String url=String.format(URL_GET_USERINFO, appId,openId);
		//注册应用之后请求用户的具体信息
		String result=getRestTemplate().getForObject(url, String.class);
		
		System.out.println(result);
		
		 QQUserInfo qqUserInfo=null;
		try {
			qqUserInfo=objectMapper.readValue(result, QQUserInfo.class);
			qqUserInfo.setOpenId(openId);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return qqUserInfo;
	}

}

package cn.shinelon.security.core.social.WeiXin.Api;

/**
 * 
 */

/**
 * 微信API调用接口
 * 
 * @author shinelon
 *
 */
public interface Weixin {

	/* (non-Javadoc)
	 * @see com.ymt.pz365.framework.security.social.api.SocialUserProfileService#getUserProfile(java.lang.String)
	 */
	WeixinUserInfo getUserInfo(String openId);
	
}

package cn.shinelon.security.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
//加上@ConfigurationProperties注释表示这个类会去properties文件中读取前缀为cn.shinelon.securit的属性
import org.springframework.web.bind.annotation.GetMapping;
@ConfigurationProperties(prefix="cn.shinelon.security")
public class SecurityProperties {
	//实例对象的名与资源文件的名必须对应 cn.shinelon.security.browser.loginPage=/doem_login.html
	private BrowserProperties browser=new BrowserProperties();
	
	private SocialProperties socialProperties=new SocialProperties();
	
	public SocialProperties getSocialProperties() {
		return socialProperties;
	}
	public void setSocialProperties(SocialProperties socialProperties) {
		this.socialProperties = socialProperties;
	}
	public void setBrowser(BrowserProperties browser) {
		this.browser = browser;
	}
	public BrowserProperties getBrowser() {
		return browser;
	}
	
}

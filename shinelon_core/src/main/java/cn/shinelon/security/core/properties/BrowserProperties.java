package cn.shinelon.security.core.properties;

public class BrowserProperties {
	//变量名与资源文件中的变量相同 cn.shinelon.security.browser.loginPage=/doem_login.html
	private String loginPage="/login.html";		//如果用户没有自定义跳转的页面，就会默认跳转页面
	private int remeberMeSeconds=3600;
	
	private String signUpUrl="/signUp.html";
	
	public void setLoginPage(String loginPage) {
		this.loginPage = loginPage;
	}
	public String getLoginPage() {
		return loginPage;
	}
	public void setRemeberMeSeconds(int remeberMeSeconds) {
		this.remeberMeSeconds = remeberMeSeconds;
	}
	public int getRemeberMeSeconds() {
		return remeberMeSeconds;
	}
	
	public void setSignUpUrl(String signUpUrl) {
		this.signUpUrl = signUpUrl;
	}
	public String getSignUpUrl() {
		return signUpUrl;
	}
	
}

package cn.shinelon.security.core.validate.code;
/**
 * 封装对接短信发送API的信息
 * @author shinelon
 *
 */
public class SimpleSmsAPI {
	private static final String SID="757e8132cb02fee7bc01c1fb14111006";
	private static final String TOKEN="a89a86fa9f5fcca1ec70f944956a7df3";
	private static final String APPID="2556cbb9471e48e3b14864a45ec4b0f6";
	private static final String TEMPLATEID="317076";
	private String sid=SID;
	private String token=TOKEN;
	private String appid=APPID;
	private String templateid=TEMPLATEID;
	private String param;		//对应调用url中的参数（要发送的code）
	private String mobile;
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getTemplateid() {
		return templateid;
	}
	public void setTemplateid(String templateid) {
		this.templateid = templateid;
	}
	public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	@Override
	public String toString() {
		return "SimpleSmsAPI [sid=" + sid + ", token=" + token + ", appid=" + appid + ", templateid=" + templateid
				+ ", param=" + param + ", mobile=" + mobile + "]";
	}
	
}

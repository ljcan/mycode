package cn.shinelon.security.core.properties;

public class SocialProperties {
	private QQproperties qqProperties=new QQproperties();
	
	private WeixinProperties weixin = new WeixinProperties();
	
	private String filterProcessesUrl = "/auth";

	public QQproperties getQqProperties() {
		return qqProperties;
	}

	public void setQqProperties(QQproperties qqProperties) {
		this.qqProperties = qqProperties;
	}
	public String getFilterProcessesUrl() {
		return filterProcessesUrl;
	}
	public void setFilterProcessesUrl(String filterProcessesUrl) {
		this.filterProcessesUrl = filterProcessesUrl;
	}
	public void setWeixin(WeixinProperties weixin) {
		this.weixin = weixin;
	}
	public WeixinProperties getWeixin() {
		return weixin;
	}
}

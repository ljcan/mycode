package cn.just.O2O.Entity;

import java.util.Date;

/**
 * …Ã∆∑œÍ«ÈÕº∆¨
 * @author Shinelon
 *
 */
public class ProductImage {
	private Long productImageId;
	private String imageAddr;
	private String imageDesc;
	private Integer priority;
	private Date createTime;
	private Long productId;
	public Long getProductImageId() {
		return productImageId;
	}
	public void setProductImageId(Long productImageId) {
		this.productImageId = productImageId;
	}
	public String getImageAddr() {
		return imageAddr;
	}
	public void setImageAddr(String imageAddr) {
		this.imageAddr = imageAddr;
	}
	public String getImageDesc() {
		return imageDesc;
	}
	public void setImageDesc(String imageDesc) {
		this.imageDesc = imageDesc;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	
}

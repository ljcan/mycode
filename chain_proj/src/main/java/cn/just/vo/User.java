package cn.just.vo;

import java.util.HashSet;
import java.util.Set;

public class User {
	public Integer id;
	public String username;
	public String pwd;
	public String email;
	public String email_code;	//邮箱验证码
	public String invite_code;	//邀请码
	public Set<Account> account=new HashSet<Account>();		//用户钱包账户
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public Set<Account> getAccount() {
		return account;
	}
	public void setAccount(Set<Account> account) {
		this.account = account;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmail_code() {
		return email_code;
	}
	public void setEmail_code(String email_code) {
		this.email_code = email_code;
	}
	public String getInvite_code() {
		return invite_code;
	}
	public void setInvite_code(String invite_code) {
		this.invite_code = invite_code;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", pwd=" + pwd + ", email=" + email + ", email_code="
				+ email_code + ", invite_code=" + invite_code + ", account=" + account + "]";
	}
	
	
}

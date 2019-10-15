package cn.just.vo;

import java.util.HashSet;
import java.util.Set;

public class Account {
	public Integer a_id;
	public String a_address; //该账户的地址
	public Integer a_user_id;
	public Set<Coin> coin=new HashSet<Coin>();
	public User user=new User();
	public Integer getA_id() {
		return a_id;
	}
	public void setA_id(Integer a_id) {
		this.a_id = a_id;
	}
	
	
	public Integer getA_user_id() {
		return a_user_id;
	}
	public void setA_user_id(Integer a_user_id) {
		this.a_user_id = a_user_id;
	}
	public String getA_address() {
		return a_address;
	}
	public void setA_address(String a_address) {
		this.a_address = a_address;
	}
	public Set<Coin> getCoin() {
		return coin;
	}
	public void setCoin(Set<Coin> coin) {
		this.coin = coin;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}

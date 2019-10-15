package cn.just.vo;

public class Coin {
	public Integer c_id;
	public Integer c_money;		//该货币的金额
	public Integer c_account_id;		//对应哪个账户
	public Account account=new Account();
	public Integer getC_id() {
		return c_id;
	}
	public void setC_id(Integer c_id) {
		this.c_id = c_id;
	}
	public Integer getC_money() {
		return c_money;
	}
	public void setC_money(Integer c_money) {
		this.c_money = c_money;
	}
	public Integer getC_account_id() {
		return c_account_id;
	}
	public void setC_account_id(Integer c_account_id) {
		this.c_account_id = c_account_id;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	
}

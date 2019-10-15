package cn.just.vo;
/**
 * 交易记录信息
 * @author Shinelon
 *
 */
public class Trade {
	public Integer t_id;
	public User trade_from;	//卖家
	public User trade_to;		//买家
	public Account trade_account;	//交易钱包
	public Coin trade_coin;		//交易货币
	public Integer getT_id() {
		return t_id;
	}
	public void setT_id(Integer t_id) {
		this.t_id = t_id;
	}
	public User getTrade_from() {
		return trade_from;
	}
	public void setTrade_from(User trade_from) {
		this.trade_from = trade_from;
	}
	public User getTrade_to() {
		return trade_to;
	}
	public void setTrade_to(User trade_to) {
		this.trade_to = trade_to;
	}
	public Account getTrade_account() {
		return trade_account;
	}
	public void setTrade_account(Account trade_account) {
		this.trade_account = trade_account;
	}
	public Coin getTrade_coin() {
		return trade_coin;
	}
	public void setTrade_coin(Coin trade_coin) {
		this.trade_coin = trade_coin;
	}
	
}

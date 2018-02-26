package com.feicuiedu.atm.entity;

import java.util.Date;

import com.feicuiedu.atm.anno.Column;
import com.feicuiedu.atm.anno.Table;

@Table(value = "trade_record")
public class TradeRecord {
	
	//交易流水号
	@Column("id")
	private int id;
	
	//源账户：业务发起账户
	@Column("account")
	private String account;
	
	//目标账户：业务接受账户
	@Column("targetAccount")
	private String targetAccount;
	
	//业务类型：1:存款 2:取款 3: 转账-支出  4:转账-收入
	@Column("tradeType")
	private int tradeType;
	
	//交易时间
	@Column("tradeDate")
	private Date tradeDate;
	
	//交易金额
	@Column("tradeAmount")
	private double tradeAmount;
	
	//源账户余额
	@Column("amount")
	private double  amount;

	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getTargetAccount() {
		return targetAccount;
	}

	public void setTargetAccount(String targetAccount) {
		this.targetAccount = targetAccount;
	}

	public int getTradeType() {
		return tradeType;
	}

	public void setTradeType(int tradeType) {
		this.tradeType = tradeType;
	}

	public Date getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(Date tradeDate) {
		this.tradeDate = tradeDate;
	}

	public double getTradeAmount() {
		return tradeAmount;
	}

	public void setTradeAmount(double tradeAmount) {
		this.tradeAmount = tradeAmount;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}	

	public TradeRecord(int id, String account, String targetAccount, int tradeType, Date tradeDate,
			double tradeAmount, double amount) {
		super();
		this.id = id;
		this.account = account;
		this.targetAccount = targetAccount;
		this.tradeType = tradeType;
		this.tradeDate = tradeDate;
		this.tradeAmount = tradeAmount;
		this.amount = amount;
	}

	public TradeRecord() {
		
	}

	@Override
	public int hashCode() {
		
		return account!=null ? account.hashCode() : 0;
	}
}

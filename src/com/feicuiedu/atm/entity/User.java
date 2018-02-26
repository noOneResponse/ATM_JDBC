package com.feicuiedu.atm.entity;

import java.util.Date;

import com.feicuiedu.atm.anno.Column;
import com.feicuiedu.atm.anno.Table;

@Table(value = "atm_user")
public class User {
	
	//账户id
	@Column("id")
	private int id;
	
	//用户账号
	@Column("account")
	private String account;
	
	//姓名
	@Column("user_name")
	private String name;
	
	//余额
	@Column("amount")
	private double amount;
	
	//性别 1:男2：女
	@Column("gender")
	private int gender;
	
	//身份证号
	@Column("card")
	private String card;
	
	//账户状态 0：管理员 1：正常用户 2：销户 3：锁定
	@Column("userType")
	private String userType;
	
	//密码
	@Column("passwd")
	private String passwd;
	
	//出生日期
	@Column("birthday")
	private Date birthday;

	//地址
	@Column("address")
	private String address;
	
	//备注
	@Column("remark")
	private String remark;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCard(String card) {
		this.card = card;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getAddress() {
		
		return address;
	}
	
	public String getAccount() {
		
		return account;
	}
	
	public String getName() {
		
		return name;
	}
	
	public double getAmount() {
		
		return amount;
	}
	
	public int getGender() {
		
		return gender;
	}
	
	public String getCard() {
		
		return card;
	}
	
	public String getUserType() {
		
		return userType;
	}
	
	public String getPasswd() {
		
		return passwd;
	}
	
	public void setAmount(double amount) {
		
		this.amount = amount;
	}
	
	public void setGender(int gender) {
		
		this.gender = gender;
	}
	
	public void setPasswd(String passwd) {
		
		this.passwd = passwd;
	}
	
	public void setAddress(String address) {
		
		this.address = address;
	}
	public User() {
		super();
	}
	
	public User(int id,String account,String name, double amount,int gender,
			String card,String userType,String passwd,Date birthday,String address,String remark) {
		
		this.id = id;
		this.account = account;
		this.name = name;
		this.amount = amount;
		this.gender = gender;
		this.card = card;
		this.userType = userType;
		this.passwd = passwd;
		this.birthday = birthday;
		this.address = address;
		this.remark = remark;
		
	}
	@Override
    public boolean equals(Object o) {
		
        if (this == o) {
        	
        	return true;
    		}
        if (!(o instanceof User)) {
        	
        	return false;
        	}

        User user = (User) o;

        return account != null ? account.equals(user.account) : user.account == null;
    }
	
	@Override
	public int hashCode() {
		
		return account!=null ? account.hashCode() : 0;
	}

	
	
}

package entity;

public class User {
	//账号
	private String account;
	//姓名
	private String name;
	//金额
	private double amount;
	//性别
	private String gender;
	//身份证号
	private String card;
	//管理员标识	 0:管理员 	1：普通用户
	private String userType;
	//密码
	private String passwd;
	//地址
	private String address;
	
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
	
	public String getGender() {
		
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
	
	public void setGender(String gender) {
		
		this.gender = gender;
	}
	
	public void setPasswd(String passwd) {
		
		this.passwd = passwd;
	}
	
	public void setAddress(String address) {
		
		this.address = address;
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

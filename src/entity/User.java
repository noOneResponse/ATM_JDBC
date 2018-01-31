package entity;

public class User {
	//�˺�
	private String account;
	//����
	private String name;
	//���
	private double amount;
	//�Ա�
	private String gender;
	//���֤��
	private String card;
	//����Ա��ʶ	 0:����Ա 	1����ͨ�û�
	private String userType;
	//����
	private String passwd;
	//��ַ
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

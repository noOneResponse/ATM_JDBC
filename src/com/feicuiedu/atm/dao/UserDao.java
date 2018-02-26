package com.feicuiedu.atm.dao;

import com.feicuiedu.atm.entity.User;

public class UserDao extends AbstractDao<User> {
	/**
	 * 注册mysql驱动
	 */
	public  UserDao() {
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}

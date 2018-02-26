package com.feicuiedu.atm.dao;

import com.feicuiedu.atm.entity.TradeRecord;

public class TradeRecordDao extends AbstractDao<TradeRecord>{
	/**
	 * 注册mysql驱动
	 */
	public  TradeRecordDao() {
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

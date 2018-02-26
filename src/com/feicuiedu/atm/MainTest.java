package com.feicuiedu.atm;

import com.feicuiedu.atm.service.TradeRecordService;

public class MainTest {
	
	public static void main(String[] args) {
		TradeRecordService tr = new TradeRecordService();
		int a = tr.findMaxId();
		System.out.println(a);
	}
	
}

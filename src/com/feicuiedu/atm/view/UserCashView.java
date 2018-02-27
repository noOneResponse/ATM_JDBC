package com.feicuiedu.atm.view;

import java.util.Date;

import com.feicuiedu.atm.core.AbstractView;
import com.feicuiedu.atm.entity.TradeRecord;
import com.feicuiedu.atm.entity.User;
import com.feicuiedu.atm.service.TradeRecordService;
import com.feicuiedu.atm.service.UserService;
import com.feicuiedu.atm.util.CommonUtils;

public class UserCashView extends AbstractView{
	
	@Override
	public AbstractView view() {
		
		CommonUtils.printFromProperties("C013");
		String input = scr.next();
		User user = getCurrentUser();
		
		//判断金额是否符合金钱格式
		if(!CommonUtils.isMoney(input)) {
			
			CommonUtils.printFromProperties("M106");
			return CommonUtils.setDispatherPathReturnAbstractView("UserDepositView");
		}
		double amount = Double.valueOf(input);
		
		//判断输入的金额是否大于零，以及金额有没有超过本帐户金额
		if(amount<0||amount>user.getAmount()) {
			
			CommonUtils.printFromProperties("M110");
			return CommonUtils.setDispatherPathReturnAbstractView("UserCashView");
		}
		UserService us = new UserService();
		user.setAmount(user.getAmount()-amount);
		
		us.modify(user,
				"amount",
				user.getAmount(),
				user.getAccount(),
				user.getAccount()
				);
		
		CommonUtils.printFromProperties("M004");
		TradeRecordService tradeRecordService = new TradeRecordService();
		TradeRecord tradeRecord = new TradeRecord(
				tradeRecordService.findMaxId()+1,
				user.getAccount(),
				user.getAccount(),
				2,
				new Date(),
				amount,
				user.getAmount()
				);
		tradeRecordService.create(tradeRecord);
		
		return CommonUtils.setDispatherPathReturnAbstractView("UserMenuView");
	}
}

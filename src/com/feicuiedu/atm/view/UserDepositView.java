package com.feicuiedu.atm.view;

import java.util.Date;

import com.feicuiedu.atm.core.AbstractView;
import com.feicuiedu.atm.entity.TradeRecord;
import com.feicuiedu.atm.entity.User;
import com.feicuiedu.atm.service.TradeRecordService;
import com.feicuiedu.atm.service.UserService;
import com.feicuiedu.atm.util.CommonUtils;

public class UserDepositView extends AbstractView{
	
	@Override
	public AbstractView view() {
		
		CommonUtils.printFromProperties("C012");
		String input = scr.next();
		double amount;
		
		//判断金额是否符合金钱格式
		if(!CommonUtils.isMoney(input)) {
			
			CommonUtils.printFromProperties("M106");
			return CommonUtils.setDispatherPathReturnAbstractView("UserDepositView");
		}
		amount = Double.valueOf(input);
		
		UserService us = new UserService();
		User user = getCurrentUser();
		user.setAmount(user.getAmount()+amount);
		
		us.modify(user,
				"amount",
				user.getAmount(),
				user.getAccount()
				);
		
		CommonUtils.printFromProperties("M003");
		TradeRecordService tradeRecordService = new TradeRecordService();
		TradeRecord tradeRecord = new TradeRecord(
				tradeRecordService.findMaxId()+1,
				user.getAccount(),
				user.getAccount(),
				1,
				new Date(),
				amount,
				user.getAmount()
				);
		tradeRecordService.create(tradeRecord);
		
		return CommonUtils.setDispatherPathReturnAbstractView("UserMenuView");
	}
}

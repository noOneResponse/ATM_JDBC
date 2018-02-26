package com.feicuiedu.atm.view;

import java.util.Date;
import java.util.List;

import com.feicuiedu.atm.core.AbstractView;
import com.feicuiedu.atm.entity.TradeRecord;
import com.feicuiedu.atm.entity.User;
import com.feicuiedu.atm.service.TradeRecordService;
import com.feicuiedu.atm.service.UserService;
import com.feicuiedu.atm.util.CommonUtils;

public class UserTasferView extends AbstractView{
	
	@Override
	public AbstractView view() {
		
		CommonUtils.printFromProperties("C001");
		UserService us = new UserService();
		String account = scr.next();
		User trasferedUser = null;
		
		//判断是否存在此用户
		if(!us.hasAccountOrCard(account)) {
			
			CommonUtils.printFromProperties("M104");
			return CommonUtils.setDispatherPathReturnAbstractView("UserTasferView");
		}
		
		List<User> list = us.inputAccountOrCardReturnUser(account);
		trasferedUser = list.get(0);
		
		//判断被转账的用户是否是当前用户
		if(trasferedUser.equals(getCurrentUser())) {
			
			CommonUtils.printFromProperties("M111");
			return CommonUtils.setDispatherPathReturnAbstractView("UserTasferView");
		}
		
		CommonUtils.printFromProperties("C014");
		String input = scr.next();
		
		//判断输入的金额是否符合金钱的格式
		if(!CommonUtils.isMoney(input)) {
			
			CommonUtils.printFromProperties("M106");
			return CommonUtils.setDispatherPathReturnAbstractView("UserTasferView");
		}
		double amount = Double.valueOf(input);
		
		User user = getCurrentUser();
		
		//判断输入的金额是否符合金钱格式，不符合重输
		if(!(amount<user.getAmount()&&amount>0)) {
			
			CommonUtils.printFromProperties("M110");
			return CommonUtils.setDispatherPathReturnAbstractView("UserTasferView");
		}
		
		user.setAmount(user.getAmount()-amount);
		trasferedUser.setAmount(trasferedUser.getAmount()+amount);
		us.modify(user,"amount",user.getAmount(),user.getAccount());
		us.modify(trasferedUser,"amount",trasferedUser.getAmount(),trasferedUser.getAccount());
		CommonUtils.printFromProperties("M005");
		
		TradeRecordService tradeRecordService = new TradeRecordService();
		
		TradeRecord tradeRecord = new TradeRecord(
				
				tradeRecordService.findMaxId()+1,
				user.getAccount(),
				trasferedUser.getAccount(),
				3,
				new Date(),
				-amount,
				user.getAmount()
				);
		tradeRecordService.create(tradeRecord);
		
		TradeRecord tradeTrasferedRecord = new TradeRecord(
				
				tradeRecordService.findMaxId()+1,
				trasferedUser.getAccount(),
				user.getAccount(),
				4,
				new Date(),
				amount,
				trasferedUser.getAmount()
				);
		tradeRecordService.create(tradeTrasferedRecord);
		
		return CommonUtils.setDispatherPathReturnAbstractView("UserMenuView");
	}
}

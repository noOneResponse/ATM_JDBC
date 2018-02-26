package com.feicuiedu.atm.view;

import java.util.List;

import com.feicuiedu.atm.core.AbstractView;
import com.feicuiedu.atm.entity.TradeRecord;
import com.feicuiedu.atm.entity.User;
import com.feicuiedu.atm.service.TradeRecordService;
import com.feicuiedu.atm.service.UserService;
import com.feicuiedu.atm.util.CommonUtils;

public class UserInformationView extends AbstractView{
	
	@Override
	public AbstractView view() {
		
		UserService us = new UserService();
		TradeRecordService tradeRecordService = new TradeRecordService();
		User user = getCurrentUser();
		
		System.out.println("用户开户信息:");
		System.out.println(
				
				"用户账号|"+user.getAccount()+
				" 姓名|"+user.getName()+
				" 性别|"+us.inputGenderReturnObject(user.getGender())+
				" 身份证号|"+user.getCard()+
				" 密码|"+user.getPasswd()+
				" 出生日期|"+user.getBirthday().toString()+
				" 地址|"+user.getAddress()+
				" 备注|"+user.getRemark()
				);
		List<TradeRecord> list = tradeRecordService.findAll(user.getAccount());
		
		if(list==null||list.isEmpty()) {
			
    		return CommonUtils.setDispatherPathReturnAbstractView("UserMenuView");
    	}
		
		for(TradeRecord tradeRecord : list) {
			
			System.out.println(
					
					"流水号"+tradeRecord.getId()+
					" 源账户"+tradeRecord.getAccount()+
					" 目标账户"+tradeRecord.getTargetAccount()+
					" 账户类型"+tradeRecordService.inpuTradeTypeReturnObject(tradeRecord.getTradeType())+
					" 账务时间"+tradeRecord.getTradeDate()+
					" 交易金额"+tradeRecord.getTradeAmount()+
					" 交易后账户金额"+tradeRecord.getAmount()
					);
		}
		
		return CommonUtils.setDispatherPathReturnAbstractView("UserMenuView");
	}
}

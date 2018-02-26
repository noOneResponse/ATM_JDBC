package com.feicuiedu.atm.view;

import java.util.List;

import com.feicuiedu.atm.core.AbstractView;
import com.feicuiedu.atm.entity.User;
import com.feicuiedu.atm.service.UserService;
import com.feicuiedu.atm.util.CommonUtils;

public class AdminUnlockUserView extends AbstractView{
	
	@Override
	public AbstractView view() {
		
		CommonUtils.printFromProperties("C001");
		UserService us = new UserService();
		String account = scr.next();
		
		if(!us.hasLockedAccountOrCard(account)) {
			
			CommonUtils.printFromProperties("M104");
			return CommonUtils.setDispatherPathReturnAbstractView("AdminUnlockUserView");
		}
		
		List<User> list= us.inputAccountOrCardReturnUser(account);
		User user = list.get(0);
		
		us.modify(user," userType ","1",account); 
		
		CommonUtils.printFromProperties("M103");
		
		return CommonUtils.setDispatherPathReturnAbstractView("AdminMenuView");
	}
}

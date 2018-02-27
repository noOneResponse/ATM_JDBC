package com.feicuiedu.atm.view;

import java.util.List;

import com.feicuiedu.atm.core.AbstractView;
import com.feicuiedu.atm.entity.User;
import com.feicuiedu.atm.service.UserService;
import com.feicuiedu.atm.util.CommonUtils;

public class AdminRestUserPasswdView extends AbstractView {
	@Override
	public AbstractView view() {
		
		CommonUtils.printFromProperties("C001");
		UserService us = new UserService();
		String account = scr.next();
		
		if(!us.hasAccountOrCard(account)) {
			
			CommonUtils.printFromProperties("M104");
			return CommonUtils.setDispatherPathReturnAbstractView("AdminRestUserPasswdView");
		}
		List<User> list= us.inputAccountOrCardReturnUser(account);
		User user=null;
		
		if(!(list==null||list.isEmpty())) {
			user= list.get(0);
		}
		
		
		CommonUtils.printFromProperties("C002");
		String passwd = scr.next();
		
		us.modify(user," passwd ",passwd,account,account); 
		
		CommonUtils.printFromProperties("M103");
		
		return CommonUtils.setDispatherPathReturnAbstractView("AdminMenuView");
	}
}

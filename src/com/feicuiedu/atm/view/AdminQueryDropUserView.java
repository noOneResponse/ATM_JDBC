package com.feicuiedu.atm.view;

import java.util.List;

import com.feicuiedu.atm.core.AbstractView;
import com.feicuiedu.atm.entity.User;
import com.feicuiedu.atm.service.UserService;
import com.feicuiedu.atm.util.CommonUtils;

public class AdminQueryDropUserView extends AbstractView{

	@Override
	public AbstractView view() {
		
		UserService us = new UserService();
		List<User> list = us.findAll("2");
		
		//主键|姓名|身份证号|性别|卡号|余额|状态
		for(User user:list) {
			
			Object account = user.getAccount();
			Object name = user.getName();
			Object card = user.getCard();
			Object gender = us.inputGenderReturnObject(user.getGender());
			Object amount = user.getAmount();
			Object userType=us.inputUserTypeReturnObject(user.getUserType());
			
			System.out.println(
					
					"账户|"+account+
					"  姓名|"+	name+
					"  身份证号|"+card+
					"  性别|"+gender+
					"  卡号|"+card+
					"  余额|"+amount+
					"  状态|"+userType
					);
		}
		
		
		return CommonUtils.setDispatherPathReturnAbstractView("AdminMenuView");
	}
}

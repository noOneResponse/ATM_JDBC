package com.feicuiedu.atm.view;

import com.feicuiedu.atm.core.AbstractView;
import com.feicuiedu.atm.entity.User;
import com.feicuiedu.atm.service.UserService;
import com.feicuiedu.atm.util.CommonUtils;

public class UserLoginView extends AbstractView{
	
	@Override
	public AbstractView view() {
		
		CommonUtils.printFromProperties("C001");
		
		//输入账号
		String account = scr.next();
		UserService userService = new UserService();
		
		//验证账号是否存在如果用户不存在则重新输入账号
		if(!userService.hasAccountOrCard(account)) {
			
			CommonUtils.printFromProperties("M104");
			return CommonUtils.setDispatherPathReturnAbstractView("UserLoginView");
		}
		
		int count =1;
		
		//验证密码正确与否，密码输入错误重新输入
		while(true) {
			
			CommonUtils.printFromProperties("C002");
			String passwd = scr.next();
			User user = userService.getUserByLoginInfo(account,passwd,"1");
			
			//密码正确跳转到管理员菜单
			if(!(user==null)) {
				
				setCurrentUser(user);
				return CommonUtils.setDispatherPathReturnAbstractView("UserMenuView");
			}
			CommonUtils.printFromProperties("M105");
			
			//密码输入错误3次改变userType为3（已锁定）
			if(count==3) {
				
				UserService us = new UserService();
				us.modify(new User(),
						" userType ",
						"3",
						account
						); 
				CommonUtils.printFromProperties("M108");
				break;
			}
			count++;
		}
		
		return CommonUtils.setDispatherPathReturnAbstractView("UserLoginView");
	}
}

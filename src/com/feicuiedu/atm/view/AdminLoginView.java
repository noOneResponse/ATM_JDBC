package com.feicuiedu.atm.view;

import com.feicuiedu.atm.core.AbstractView;
import com.feicuiedu.atm.entity.User;
import com.feicuiedu.atm.service.UserService;
import com.feicuiedu.atm.util.CommonUtils;

public class AdminLoginView extends AbstractView{
	
	@Override
	public AbstractView view() {
		
		CommonUtils.printFromProperties("C011");
		
		//输入账号
		String account = scr.next();
		UserService userService = new UserService();
		
		//验证账号是否存在如果用户不存在则重新输入账号
		if(!"admin".equals(account)) {
			
			CommonUtils.printFromProperties("M104");
			return CommonUtils.setDispatherPathReturnAbstractView("AdminLoginView");
		}
				
		//验证密码正确与否
		CommonUtils.printFromProperties("C002");
		String passwd = scr.next();
		User user = userService.getUserByLoginInfo(account,passwd,"0");
		
		//密码正确跳转到管理员菜单
		if(!(user==null)) {
			
			setCurrentUser(user);
			CommonUtils.printFromProperties("C002");
			return CommonUtils.setDispatherPathReturnAbstractView("AdminMenuView");
		}
		CommonUtils.printFromProperties("M105");
		
		return CommonUtils.setDispatherPathReturnAbstractView("AdminLoginView");
	}
}

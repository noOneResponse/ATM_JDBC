package com.feicuiedu.atm.view;

import java.util.List;

import com.feicuiedu.atm.core.AbstractView;
import com.feicuiedu.atm.entity.User;
import com.feicuiedu.atm.service.UserService;
import com.feicuiedu.atm.util.CommonUtils;

public class AdminDropUserView extends AbstractView{
	
	@Override
	public AbstractView view() {
		
		CommonUtils.printFromProperties("C001");
		String account = scr.next();
		UserService us = new UserService();
		User thisUser = null;
		if(us.hasAccountOrCard(account)) {
			
			List<User> list= us.inputAccountOrCardReturnUser(account);
			thisUser=list.get(0);
			/*objects[0] 修改的数据的列名
			 *objects[1] 修改数据的内容
			 *objects[2] where条件account or card
			 */
			us.modify(thisUser," userType ","2",account);
			CommonUtils.printFromProperties("M102");
			
			return CommonUtils.setDispatherPathReturnAbstractView("AdminMenuView");
		}
		CommonUtils.printFromProperties("M104");
		
		return CommonUtils.setDispatherPathReturnAbstractView("AdminMenuView");
	}
		
}

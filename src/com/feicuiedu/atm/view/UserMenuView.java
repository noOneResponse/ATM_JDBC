package com.feicuiedu.atm.view;

import java.io.IOException;

import com.feicuiedu.atm.core.AbstractView;
import com.feicuiedu.atm.util.CommonUtils;

public class UserMenuView extends AbstractView{
	
	@Override
	public AbstractView view() {
		
		String path;
		try {
			CommonUtils.printFromFile("menu_user.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String input = scr.next();
		
		try {
			
			path = CommonUtils.getMenuValueFromProp(input,"menu_user");
			if(path==null) {
				
				CommonUtils.printFromProperties("M106");
				return CommonUtils.setDispatherPathReturnAbstractView("UserMenuView");
			}
			return CommonUtils.setDispatherPathReturnAbstractView(path);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return CommonUtils.setDispatherPathReturnAbstractView("UserMenuView");
	}
}

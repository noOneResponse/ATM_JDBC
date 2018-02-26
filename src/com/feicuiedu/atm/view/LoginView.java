package com.feicuiedu.atm.view;

import java.io.IOException;

import com.feicuiedu.atm.core.AbstractView;
import com.feicuiedu.atm.util.CommonUtils;

public class LoginView extends AbstractView{
	
	@Override
	public AbstractView view() {
		
		AbstractView av = null;

		try {
			
			CommonUtils.printFromFile("login.txt");
		} catch (IOException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String input = scr.next();
		
		if("1".equals(input)) {
			
			av = CommonUtils.setDispatherPathReturnAbstractView("AdminLoginView");
		}
		else if("2".equals(input)) {
			
			av = CommonUtils.setDispatherPathReturnAbstractView("UserLoginView");
		}
		else {
			
			CommonUtils.printFromProperties("M106");
			av = CommonUtils.setDispatherPathReturnAbstractView("LoginView");
			
		}

		return av;
	}
}

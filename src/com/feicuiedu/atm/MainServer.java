package com.feicuiedu.atm;

import com.feicuiedu.atm.core.AbstractView;
import com.feicuiedu.atm.util.CommonUtils;

public class MainServer {

	public static void main(String[] args) {
		
		AbstractView av = CommonUtils.setDispatherPathReturnAbstractView("LoginView");
		
		while(true) {
			
			av = av.view();
		}
		
	}
	
}

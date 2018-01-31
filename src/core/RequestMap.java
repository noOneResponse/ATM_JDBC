package core;

import java.io.IOException;
import java.util.HashMap;

import util.CommonUtils;

public class RequestMap<K,V> extends HashMap<K,V> {
	
	private String dispatherPath;
	
	public void setDispatherPath(String dispatherPath) {
		
		this.dispatherPath = dispatherPath;
	}
	
	public String getDispatherPath() {
		
		return dispatherPath;
	}
	
	public AbstractView forward() {
		
		AbstractView av = null;
		
		try {
			av = (AbstractView) CommonUtils.reflectInstanceFromProp(dispatherPath);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return av;
	}
}

package com.feicuiedu.atm.core;

import java.util.List;

public interface IService<T> {
	 	
		void create(T t);
	    void modify(T t,Object setValue,Object...objects);
	    List<T> findAll(String str);
	    int findMaxId();
	   

}
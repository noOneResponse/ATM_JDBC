package com.feicuiedu.atm.exception;

public class MyException extends Exception{
	
	private static final long serialVersionUID = -7347114786487137651L;

	public MyException(){
        super();
    }
	
	public MyException(String msg){
        super(msg);
    } 
	
}

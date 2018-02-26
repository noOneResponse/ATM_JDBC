package com.feicuiedu.atm.core;

import java.util.Scanner;

import com.feicuiedu.atm.entity.User;

public abstract class AbstractView {
	
	public Scanner scr = new Scanner(System.in);
	
	private static User currentUser;

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser_) {
		currentUser = currentUser_;
	}
	
	public abstract AbstractView view();
}

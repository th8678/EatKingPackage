package com.tbond.eatking.model;

public class User {
	public static User instance = new User();
	
	public boolean isLogin = false;
	public String uid = "";
	public String userName = "";
	
	public static User getInstance(){
		return instance;
	}
}

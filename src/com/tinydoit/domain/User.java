package com.tinydoit.domain;

public class User {

	private int iD;
	private String userName;
	private String password;

	public int getiD() {
		return iD;
	}

	public void setiD(int iD) {
		this.iD = iD;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User(int iD, String userName, String password) {
		super();
		this.iD = iD;
		this.userName = userName;
		this.password = password;
	}

}

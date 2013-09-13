package com.tinydoit.domain;

public class User {

	private String iD;
	private String userName;
	private String password;

	public String getiD() {
		return iD;
	}

	public void setiD(String iD) {
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

	public User(String iD, String userName, String password) {
		super();
		this.iD = iD;
		this.userName = userName;
		this.password = password;
	}

}

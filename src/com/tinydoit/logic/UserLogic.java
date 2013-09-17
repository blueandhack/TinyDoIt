package com.tinydoit.logic;

import java.sql.SQLException;

import com.tinydoit.dao.UserDao;
import com.tinydoit.domain.User;

public class UserLogic {

	public static UserDao userDao;

	public static boolean checkUserByUsername(String Username) {
		return userDao.checkUserByUsername(Username);

	}

	public static boolean addUser(String Username, String Password) {
		return userDao.addUser(Username, Password);
	}

	public static boolean checkLoginByUser(String Username, String Password) {
		return userDao.checkLoginByUser(Username, Password);
	}

	public static boolean creatUserTable(String Username) {
		return userDao.creatUserTable(Username);
		// return true;
	}

	public static boolean checkUserTable(String Username) {
		return userDao.checkUserTable(Username);
	}

	public static User getUserByUsername(String Username) {
		return userDao.getUserByUsername(Username);
	}
}

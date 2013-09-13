package com.tinydoit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.tinydoit.daoimpl.DBUtil;
import com.tinydoit.domain.User;

public class UserDao {

	private static Connection conn = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;

	// 查找用户名是否注册
	public static boolean checkUserByUsername(String Username) {

		// TODO Auto-generated method stub
		try {
			conn = DBUtil.getConnection();
			String sql = "select username from userinfo  where username = '"
					+ Username + "'";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				DBUtil.free(rs, ps, conn);
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBUtil.free(rs, ps, conn);
		return true;
	}

	public User getUserByID(String ID) {
		// TODO Auto-generated method stub
		return null;
	}

	// 验证登陆账号密码
	public static boolean checkLoginByUser(String Username, String Password) {
		// TODO Auto-generated method stub
		try {
			conn = DBUtil.getConnection();
			String sql = "select username,password from userinfo  where username = '"
					+ Username + "'";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				String oldPassword = rs.getString(2);
				if (oldPassword.equals(Password)) {
					return true;
				}
			}

			// int count = rs.getMetaData().getColumnCount();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	// 添加用户
	public static boolean addUser(String Username, String Password) {

		// ps.setString(1, "1");

		try {
			conn = DBUtil.getConnection();
			String sql = "insert into userinfo(username,password) values (?,?) ";
			ps = conn.prepareStatement(sql);
			ps.setString(1, Username);
			ps.setString(2, Password);
			int i;
			i = ps.executeUpdate();
			if (i > 0) {
				DBUtil.free(rs, ps, conn);
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBUtil.free(rs, ps, conn);
		return false;
	}

	public boolean updateUserPasswordByUsername(String Username, String Password) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean deleteUserByUsername(String Username) {
		// TODO Auto-generated method stub
		return false;
	}

}

package com.tinydoit.dao;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
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
					DBUtil.free(rs, ps, conn);
					return true;
				}
			}

			// int count = rs.getMetaData().getColumnCount();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBUtil.free(rs, ps, conn);
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

	// 创建用户任务数据库（false表示创建失败，true表示创建成功）
	public static boolean creatUserTable(String Username) {

		String tableName = "TASK_TABLE";
		try {
			conn = DBUtil.getConnection();
			// String sql = "CREATE TABLE "
			// + tableName
			// +
			// "(uID int not null,tID int not null,title varchar(64) not null,description varchar(256) not null,"
			// + "date datetime not null,deadlineDate datetime not null,"
			// +
			// "project varchar(32) not null,context varchar(32) not null,flag int not null,tag varchar(128) not null,"
			// +
			// "reminder int not null,reminderDate datetime not null,checks int not null,box varchar(32) not null, primary key (tID))";

			String sql = "CREATE TABLE "
					+ tableName
					+ "(uID int not null,tID int auto_increment not null,title varchar(64) not null,description varchar(256) not null,"
					+ "date varchar(32) not null,"
					+ "checks int not null, primary key (tID))";
			ps = conn.prepareStatement(sql);
			int i = ps.executeUpdate();

			if (i == 0) {
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

	// 查询任务数据库（false表示未创建，true表示已创建）
	public static boolean checkUserTable(String Username) {
		try {
			conn = DBUtil.getConnection();
			String tableName = "TASK_TABLE";
			DatabaseMetaData meta = conn.getMetaData();
			rs = meta.getTables("bluehack_doit", null, tableName,
					new String[] { "TABLE" });

			while (rs.next()) {
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

	// 通过用户名获取用户对象
	public static User getUserByUsername(String Username) {
		User user = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select id,username,password from userinfo  where username = '"
					+ Username + "'";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				user = new User(rs.getInt(1), rs.getString(2), rs.getString(3));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBUtil.free(rs, ps, conn);
		return user;
	}

	public static boolean updateUserPasswordByUsername(String Username,
			String Password) {
		// TODO Auto-generated method stub
		return false;
	}

	public static boolean deleteUserByUsername(String Username) {
		// TODO Auto-generated method stub
		return false;
	}

}

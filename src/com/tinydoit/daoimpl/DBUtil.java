package com.tinydoit.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class DBUtil {

	//�������ݿ��Ҫ����
	private static String driverName = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://blueandhack.com/bluehack_doit";
	private static String userName = "bluehack_doit";
	private static String password = "tinydoit";

	//������������̬��
	private DBUtil() {
	}

	private static DBUtil DBU = null;

	public static DBUtil getInstance() {
		if (DBU != null) {
			synchronized (DBUtil.class) {
				if (DBU != null) {
					DBU = new DBUtil();
				}
			}
		}
		return DBU;
	}

	static {
		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//�������ݿ�
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, userName, password);
	}

	//�ͷ���Դ
	public static void free(ResultSet rs, Statement stat, Connection conn) {
		try {
			if (rs != null)// ������׿�ָ���쳣
				rs.close();
			rs = null;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stat != null)
					stat.close();
				stat = null;
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (conn != null)
						conn.close();
					conn = null;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

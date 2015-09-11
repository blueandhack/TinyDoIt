package com.tinydoit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import com.tinydoit.daoimpl.DBUtil;
import com.tinydoit.domain.Date;
import com.tinydoit.domain.Tag;
import com.tinydoit.domain.Task;

public class TaskDao {

	private static int uID; // �����������ĸ��û�
	private static int tID; // ������ı��
	private static String title; // ����
	private static String description; // ��������
	private static String date; // ����ʼʱ��
	private static java.util.Date deadlineDate; // �������ʱ��
	private static String project; // ��Ŀ
	private static String context; // �龳
	private static int flag; // ��Ҫ�ȼ���0.�� 1.�� 3.�ͣ�
	private static Tag tag; // ��ǩ
	private static int reminder; // ���ѣ�Ҳ�������壩
	private static Date reminderDate;
	private static boolean check; // �Ƿ���ɣ�false.����� true.��ɣ�
	private static String box; // �����ĸ���Ϣ���ӣ�����մ��죬���մ���ȵȣ�

	private static Connection conn = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;

	private static String id;

	// ����û�����
	public static boolean addTask(Task newTask) {
		uID = newTask.getuID();
		title = newTask.getTitle();
		description = newTask.getDescription();
		date = newTask.getDate();
		// deadlineDate = newTask.getDeadlineDate();
		// project = newTask.getBox();
		// context = newTask.getContext();
		// flag = newTask.getFlag();
		// tag = newTask.getTag();
		// reminder = newTask.getReminder();
		// reminderDate = newTask.getReminderDate();
		check = newTask.isCheck();
		// box = newTask.getBox();

		try {
			conn = DBUtil.getConnection();
			String sql = "insert into task_table (uID,title,description,date,checks) values(?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, uID);
			ps.setString(2, title);
			ps.setString(3, description);
			ps.setString(4, date);
			ps.setBoolean(5, check);
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

	public static boolean updateTask(Task updateTask, int tID) {

		uID = updateTask.getuID();
		title = updateTask.getTitle();
		description = updateTask.getDescription();
		date = updateTask.getDate();

		check = updateTask.isCheck();

		try {
			conn = DBUtil.getConnection();
			String sql = "update task_table set title='" + title
					+ "',description='" + description + "',date='" + date
					+ "' where tID=" + tID;
			ps = conn.prepareStatement(sql);
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

	public static Task getAllTaskByUsername(String Username) {
		return null;
	}

	public static void getAllTaskToJList(JList list, int uID) {
		DefaultListModel model = new DefaultListModel(); // create a new list
															// model

		try {
			conn = DBUtil.getConnection();

			String sql = "select tID,title from TASK_TABLE where uID='" + uID
					+ "' and " + "checks=" + false;
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery(); // run your query

			while (rs.next()) // go through each row that your query returns
			{
				id = rs.getString("tID");
				String itemCode = rs.getString("title") + "-" + id;

				System.out.println(id);
				model.addElement(itemCode); // add each item to the model
			}

			list.setModel(model);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBUtil.free(rs, ps, conn);

	}

	// ͨ������ID��ȡ��������
	public static Task getTaskByTID(int tID) {
		Task newTask = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select uID,title,description,date,checks from TASK_TABLE where tID='"
					+ tID + "'";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				uID = rs.getInt("uID");
				title = rs.getString("title");
				description = rs.getString("description");
				date = rs.getString("date");
				check = rs.getBoolean("checks");
			}

			newTask = new Task(uID, tID, title, description, date, check);
			DBUtil.free(rs, ps, conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return newTask;
	}

	public static boolean checkedTaskByTID(int tID) {
		try {
			conn = DBUtil.getConnection();
			String sql = "update task_table set checks=" + true + " where tID="
					+ tID;
			ps = conn.prepareStatement(sql);
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

	public static void getTodayTaskToJList(JList list, int uID) {
		DefaultListModel model = new DefaultListModel(); // create a new list
		// model

		try {
			conn = DBUtil.getConnection();

			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date date = new java.util.Date();
			String dateString=sf.format(date);
			System.out.println(dateString);
			
			String dateArray[]=dateString.split("-");
			
			
			String sql = "select tID,title from TASK_TABLE where uID='" + uID
					+ "' and " + "checks=" + false +" and date='"+dateString+"'";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery(); // run your query

			while (rs.next()) // go through each row that your query returns
			{
				id = rs.getString("tID");
				String itemCode = rs.getString("title") + "-" + id;

				System.out.println(id);
				model.addElement(itemCode); // add each item to the model
			}

			list.setModel(model);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBUtil.free(rs, ps, conn);

	}

}

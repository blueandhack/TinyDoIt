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

	private static int uID; // 此任务属于哪个用户
	private static int tID; // 此任务的编号
	private static String title; // 标题
	private static String description; // 任务描述
	private static String date; // 任务开始时间
	private static java.util.Date deadlineDate; // 任务结束时间
	private static String project; // 项目
	private static String context; // 情境
	private static int flag; // 重要等级（0.高 1.中 3.低）
	private static Tag tag; // 标签
	private static int reminder; // 提醒（也就是闹铃）
	private static Date reminderDate;
	private static boolean check; // 是否完成（false.待完成 true.完成）
	private static String box; // 属于哪个消息盒子（如今日待办，明日待办等等）

	private static Connection conn = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;

	private static String id;

	// 添加用户任务
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

	// 通过任务ID获取任务详情
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

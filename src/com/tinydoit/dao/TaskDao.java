package com.tinydoit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
	private static Date date; // 任务开始时间
	private static Date deadlineDate; // 任务结束时间
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

	// 添加用户任务
	public static boolean addTask(Task newTask) {
		title = newTask.getTitle();
		description = newTask.getDescription();
		date = newTask.getDate();
		deadlineDate = newTask.getDeadlineDate();
		project = newTask.getBox();
		context = newTask.getContext();
		flag = newTask.getFlag();
		tag = newTask.getTag();
		reminder = newTask.getReminder();
		reminderDate = newTask.getReminderDate();
		check = newTask.isCheck();
		box = newTask.getBox();

		return false;
	}

	public static boolean updateTask(Task updateTask) {
		return false;
	}

	public static Task getAllTaskByUsername(String Username) {
		return null;
	}

	public static void getAllTaskToJList(JList list, String Username) {
		DefaultListModel model = new DefaultListModel(); // create a new list
															// model

		try {
			conn = DBUtil.getConnection();

			String sql = "select title from " + Username + "_TASK_TABLE";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery(); // run your query

			while (rs.next()) // go through each row that your query returns
			{
				String itemCode = rs.getString("title"); // get the
															// element in
															// column
															// "item_code"
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

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

	private static int uID; // �����������ĸ��û�
	private static int tID; // ������ı��
	private static String title; // ����
	private static String description; // ��������
	private static Date date; // ����ʼʱ��
	private static Date deadlineDate; // �������ʱ��
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

	// ����û�����
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

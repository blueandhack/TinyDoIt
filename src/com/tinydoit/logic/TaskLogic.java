package com.tinydoit.logic;

import javax.swing.JList;

import com.tinydoit.dao.TaskDao;
import com.tinydoit.domain.Task;

public class TaskLogic {

	public static TaskDao taskDao;

	// �������
	public static boolean addTask(Task newTask) {
		return taskDao.addTask(newTask);
	}

	// �޸�����
	public static boolean updateTask(Task newTask) {
		return false;
	}

	// ɾ������
	public static boolean deleteTask(int tID) {
		return false;
	}

	public static void getAllTaskToJList(JList list, String Username) {
		taskDao.getAllTaskToJList(list, Username);
	}
}

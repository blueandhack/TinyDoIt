package com.tinydoit.logic;

import javax.swing.JList;

import com.tinydoit.dao.TaskDao;
import com.tinydoit.domain.Task;

public class TaskLogic {

	public static TaskDao taskDao;

	// 添加任务
	public static boolean addTask(Task newTask) {
		return taskDao.addTask(newTask);
	}

	// 修改任务
	public static boolean updateTask(Task newTask, int tID) {
		return taskDao.updateTask(newTask, tID);
	}

	// 删除任务
	public static boolean deleteTask(int tID) {
		return false;
	}

	public static void getAllTaskToJList(JList list, int uID) {
		taskDao.getAllTaskToJList(list, uID);
	}

	public static void getTodayTaskToJList(JList list, int uID) {
		taskDao.getTodayTaskToJList(list, uID);
	}

	public static Task getTaskByTID(int tID) {
		return taskDao.getTaskByTID(tID);
	}

	public static boolean checkedTaskByTID(int tID) {
		return taskDao.checkedTaskByTID(tID);
	}
}

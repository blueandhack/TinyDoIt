package com.tinydoit.dao;

import com.tinydoit.domain.Task;
import com.tinydoit.domain.User;

public interface InterfaceTaskList {

	// 添加任务
	public boolean addTask(Task Newtask);

	// 通过任务ID删除任务
	public boolean deleteTask(int tID);
	
	//通过任务ID更新任务
	public boolean updateTask(int tID,Task task);
}

package com.tinydoit.dao;

import com.tinydoit.domain.Task;
import com.tinydoit.domain.User;

public interface InterfaceTaskList {

	// �������
	public boolean addTask(Task Newtask);

	// ͨ������IDɾ������
	public boolean deleteTask(int tID);
	
	//ͨ������ID��������
	public boolean updateTask(int tID,Task task);
}

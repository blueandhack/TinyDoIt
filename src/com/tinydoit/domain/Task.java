package com.tinydoit.domain;

import java.util.Date;

public class Task {

	private int uID; // �����������ĸ��û�
	private int tID; // ������ı��
	private String title; // ����
	private String description; // ��������
	private String date; // ����ʼʱ��
	// private Date deadlineDate; // �������ʱ��
	// private String project; // ��Ŀ
	// private String context; // �龳
	// private int flag; // ��Ҫ�ȼ���0.�� 1.�� 3.�ͣ�
	// private Tag tag; // ��ǩ
	// private int reminder; // ���ѣ�Ҳ�������壩
	// private Date reminderDate; // ��������ʱ��
	private boolean check; // �Ƿ���ɣ�false.����� true.��ɣ�

	// private String box; // �����ĸ���Ϣ���ӣ�����մ��죬���մ���ȵȣ�

	public Task(int uID, String title, String description, String date,
			boolean check) {
		super();
		this.uID = uID;
		this.title = title;
		this.description = description;
		this.date = date;
		this.check = check;
	}

	public Task(int uID, int tID, String title, String description,
			String date, boolean check) {
		super();
		this.uID = uID;
		this.tID = tID;
		this.title = title;
		this.description = description;
		this.date = date;
		this.check = check;
	}

	public int getuID() {
		return uID;
	}

	public void setuID(int uID) {
		this.uID = uID;
	}

	public int gettID() {
		return tID;
	}

	public void settID(int tID) {
		this.tID = tID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public boolean isCheck() {
		return check;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}

}

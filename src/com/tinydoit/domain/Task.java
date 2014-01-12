package com.tinydoit.domain;

import java.util.Date;

public class Task {

	private int uID; // 此任务属于哪个用户
	private int tID; // 此任务的编号
	private String title; // 标题
	private String description; // 任务描述
	private String date; // 任务开始时间
	// private Date deadlineDate; // 任务结束时间
	// private String project; // 项目
	// private String context; // 情境
	// private int flag; // 重要等级（0.高 1.中 3.低）
	// private Tag tag; // 标签
	// private int reminder; // 提醒（也就是闹铃）
	// private Date reminderDate; // 设置闹铃时间
	private boolean check; // 是否完成（false.待完成 true.完成）

	// private String box; // 属于哪个消息盒子（如今日待办，明日待办等等）

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

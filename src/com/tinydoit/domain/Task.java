package com.tinydoit.domain;

public class Task {

	private int uID; //此任务属于哪个用户
	private int tID; //此任务的编号
	private String title; //标题
	private String description; //任务描述
	private Date date; //任务开始时间
	private Date deadlineDate; //任务结束时间
	private String project; //项目
	private String context; //情境
	private int flag; //重要等级（0.高 1.中 3.低）
	private String tag; //标签
	private int reminder; //提醒（也就是闹铃）
	private boolean check; //是否完成（false.待完成 true.完成）
	private String box; //属于哪个消息盒子（如今日待办，明日待办等等）
	
	
	
	public Task(int uID, String title, String description, Date date,
			Date deadlineDate, String project, String context, int flag,
			String tag, int reminder, boolean check, String box) {
		super();
		this.uID = uID;
		this.title = title;
		this.description = description;
		this.date = date;
		this.deadlineDate = deadlineDate;
		this.project = project;
		this.context = context;
		this.flag = flag;
		this.tag = tag;
		this.reminder = reminder;
		this.check = check;
		this.box = box;
	}
	
	public int getuID() {
		return uID;
	}
	public void setuID(int uID) {
		this.uID = uID;
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
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Date getDeadlineDate() {
		return deadlineDate;
	}
	public void setDeadlineDate(Date deadlineDate) {
		this.deadlineDate = deadlineDate;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public int getReminder() {
		return reminder;
	}
	public void setReminder(int reminder) {
		this.reminder = reminder;
	}
	public boolean isCheck() {
		return check;
	}
	public void setCheck(boolean check) {
		this.check = check;
	}
	public String getBox() {
		return box;
	}
	public void setBox(String box) {
		this.box = box;
	}

	
	
	
}

package com.tinydoit.domain;

public class Task {

	private int uID; //�����������ĸ��û�
	private int tID; //������ı��
	private String title; //����
	private String description; //��������
	private Date date; //����ʼʱ��
	private Date deadlineDate; //�������ʱ��
	private String project; //��Ŀ
	private String context; //�龳
	private int flag; //��Ҫ�ȼ���0.�� 1.�� 3.�ͣ�
	private String tag; //��ǩ
	private int reminder; //���ѣ�Ҳ�������壩
	private boolean check; //�Ƿ���ɣ�false.����� true.��ɣ�
	private String box; //�����ĸ���Ϣ���ӣ�����մ��죬���մ���ȵȣ�
	
	
	
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

package com.tinydoit.ui;

import javax.swing.JFrame;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.PopupMenu;
import java.awt.Toolkit;

import javax.swing.JTextField;

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.Popup;

import com.qt.datapicker.DatePicker;
import com.tinydoit.domain.Date;
import com.tinydoit.domain.Tag;
import com.tinydoit.domain.Task;
import com.tinydoit.domain.User;
import com.tinydoit.logic.TaskLogic;

import javax.swing.JButton;
import javax.swing.JCheckBox;

public class AddTask extends JFrame {

	// int uID; // 此任务属于哪个用户
	// int tID; // 此任务的编号
	// String title; // 标题
	// String description; // 任务描述
	// Date date; // 任务开始时间
	// Date deadlineDate; // 任务结束时间
	// String project; // 项目
	// String context; // 情境
	// int flag; // 重要等级（0.高 1.中 3.低）
	// Tag tag; // 标签
	// int reminder; // 提醒（也就是闹铃）
	// boolean check; // 是否完成（false.待完成 true.完成）
	// String box; // 属于哪个消息盒子（如今日待办，明日待办等等）

	private JTextField textTaskTitle;
	private JTextArea textTaskDescription;
	private JButton btnReminderDate;
	private DatePicker chooseDate;
	private JButton btnTaskDate;

	private static AddTask addTask = null;

	private String WindowsTitle = "添加任务";
	private String TaskTitle = "任务名";
	private String TaskDescription = "任务描述";
	private String TaskDate = "开始时间";
	private String ChooseDate = "选择日期";
	private String OK = "确定";
	private String Cancel = "取消";
	private String Reminder = "设置闹铃";
	private String YesOrNo = "是否设置？";

	private ObservingTextField textBtnChooseDate;// 选择开始日期按钮与文本框
	private ObservingTextField textBtnReminderDate;// 选择闹铃按钮
	private JButton btnOkButton;
	private JButton btnCancelButton;

	private Task newTask;
	static User newUser;

	private AddTask() {

		init();
	}

	public static AddTask getInstance(User user) {
		newUser = new User(user.getiD(), user.getUserName(), user.getPassword());
//		if (addTask == null) {
//			synchronized (AddTask.class) {
//				if (addTask == null) {
//					addTask = new AddTask();
//				}
//			}
//		}
		addTask = new AddTask();
		return addTask;
	}

	private void init() {
		getContentPane().setLayout(null);

		setTitle(WindowsTitle);

		textTaskTitle = new JTextField();
		textTaskTitle.setBounds(70, 7, 304, 21);
		getContentPane().add(textTaskTitle);
		textTaskTitle.setColumns(10);

		textTaskDescription = new JTextArea();
		textTaskDescription.setBounds(70, 38, 304, 60);
		getContentPane().add(textTaskDescription);

		JLabel lblTaskTitle = new JLabel(TaskTitle);
		lblTaskTitle.setBounds(15, 10, 54, 15);
		getContentPane().add(lblTaskTitle);

		JLabel lblTaskDescription = new JLabel(TaskDescription);
		lblTaskDescription.setBounds(15, 42, 54, 15);
		getContentPane().add(lblTaskDescription);

		JLabel lblTaskDate = new JLabel(TaskDate);
		lblTaskDate.setBounds(15, 112, 54, 15);
		getContentPane().add(lblTaskDate);

		btnTaskDate = new JButton(ChooseDate);
		btnTaskDate.setBounds(196, 108, 95, 23);
		getContentPane().add(btnTaskDate);
		btnTaskDate.addActionListener(new ClickButtonAction());

		textBtnChooseDate = new ObservingTextField();
		textBtnChooseDate.setEditable(false);
		textBtnChooseDate.setBounds(70, 116, 95, 23);
		getContentPane().add(textBtnChooseDate);

		// textBtnReminderDate = new ObservingTextField();
		// textBtnReminderDate.setEditable(false);
		// textBtnReminderDate.setBounds(70, 163, 93, 23);
		// getContentPane().add(textBtnReminderDate);

		btnOkButton = new JButton(OK);
		btnOkButton.setBounds(70, 153, 93, 23);
		getContentPane().add(btnOkButton);
		btnOkButton.addActionListener(new ClickButtonAction());

		btnCancelButton = new JButton(Cancel);
		btnCancelButton.setBounds(281, 153, 93, 23);
		getContentPane().add(btnCancelButton);
		btnCancelButton.addActionListener(new ClickButtonAction());

		// JLabel lblNewLabel = new JLabel(Reminder);
		// lblNewLabel.setBounds(15, 129, 54, 15);
		// getContentPane().add(lblNewLabel);
		//
		// JCheckBox chckbxNewCheckBox = new JCheckBox(YesOrNo);
		// chckbxNewCheckBox.setBounds(90, 125, 103, 23);
		// getContentPane().add(chckbxNewCheckBox);
		//
		// btnReminderDate = new JButton(ChooseDate);
		// btnReminderDate.setBounds(197, 163, 93, 23);
		// getContentPane().add(btnReminderDate);
		// btnReminderDate.addActionListener(new ClickButtonAction());

		// 设置在屏幕居中显示
		Toolkit kit = this.getToolkit();
		Dimension winSize = kit.getScreenSize();
		setBounds(winSize.width / 4, winSize.height / 4, winSize.width / 2,
				winSize.height / 2);

		this.setSize(400, 250);

		// // SimpleDateFormat sdf = new
		// SimpleDateFormat(" yyyy-MM-dd HH:mm:ss ");
		// // java.util.Date date = null;
		// // try {
		// // date = sdf.parse(textBtnChooseDate.getText());
		// // } catch (ParseException e) {
		// // // TODO Auto-generated catch block
		// // e.printStackTrace();
		// // }
		//
		// // 创建新任务构造方法
		// newTask = new Task(1, 1, textTaskTitle.getText(),
		// textTaskDescription.getText(), date, 1, date, false);

	}

	public class ClickButtonAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnTaskDate) {

				chooseDate = new DatePicker(textBtnChooseDate, Locale.CHINA);
				java.util.Date selectedDate = chooseDate
						.parseDate(textBtnChooseDate.getText());
				chooseDate.setSelectedDate(selectedDate);
				// textBtnChooseDate.setBounds(146, 86, 81, 21);
				chooseDate.start(textBtnChooseDate);

			}
			if (e.getSource() == btnOkButton) {

				newTask = new Task(newUser.getiD(), textTaskTitle.getText(),
						textTaskDescription.getText(),
						textBtnChooseDate.getText(), false);
				// System.out.println(newUser.getUserName());
				if (TaskLogic.addTask(newTask)) {
					setVisible(false);
				} else {
					JOptionPane.showMessageDialog(null, "添加错误请重试");
					return;
				}
			}
			if (e.getSource() == btnCancelButton) {
				setVisible(false);
			}

		}
	}

	public class ObservingTextField extends JTextField implements Observer {
		public void update(Observable o, Object arg) {
			Calendar calendar = (Calendar) arg;
			DatePicker dp = (DatePicker) o;
			setText(dp.formatDate(calendar));

		}
	}

}

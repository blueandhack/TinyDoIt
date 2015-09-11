package com.tinydoit.ui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Locale;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;
import javax.swing.JButton;

import com.qt.datapicker.DatePicker;
import com.tinydoit.domain.Task;
import com.tinydoit.domain.User;
import com.tinydoit.logic.TaskLogic;

public class OldTask extends JFrame {

	private String OK = "确认修改";
	private String Cancel = "取消";
	private String Delete = "标记完成";
	private String TaskTitle = "任务名";
	private String TaskDescription = "任务描述";
	private String TaskDate = "开始时间";
	private String ChooseDate = "选择日期";
	private String WindowsTitle = "修改任务";
	private String Reminder = "设置闹铃";
	private String YesOrNo = "是否设置？";

	private ObservingTextField textBtnChooseDate;

	JButton btnOK;
	JButton btnDelete;
	JButton btnCancel;
	JButton btnTaskDate;
	static int NewtID;
	static User NewUser;
	static Task OldTaskShow;
	static Task NewTaskUpdate;

	private DatePicker chooseDate;

	private static OldTask oldTask = null;
	private JTextField textTitle;
	JTextArea textDescription;

	public static OldTask getInstance(int tID, User user) {
		NewtID = tID;
		NewUser = new User(user.getiD(), user.getUserName(), user.getPassword());
		OldTaskShow = TaskLogic.getTaskByTID(NewtID);
		// if (oldTask == null) {
		// synchronized (OldTask.class) {
		// if (oldTask == null) {
		// oldTask = new OldTask();
		// }
		// }
		// }
		oldTask = new OldTask();

		return oldTask;
	}

	private OldTask() {

		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		getContentPane().setLayout(null);

		setTitle(WindowsTitle);

		JLabel lblNewLabel = new JLabel(TaskTitle);
		lblNewLabel.setBounds(10, 10, 54, 15);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel(TaskDescription);
		lblNewLabel_1.setBounds(10, 35, 54, 15);
		getContentPane().add(lblNewLabel_1);

		textTitle = new JTextField();
		textTitle.setBounds(74, 7, 300, 21);
		getContentPane().add(textTitle);
		textTitle.setColumns(10);
		textTitle.setText(OldTaskShow.getTitle());

		textDescription = new JTextArea();
		textDescription.setBounds(74, 35, 300, 67);
		getContentPane().add(textDescription);
		textDescription.setText(OldTaskShow.getDescription());

		JLabel lblNewLabel_2 = new JLabel(TaskDate);
		lblNewLabel_2.setBounds(10, 116, 54, 15);
		getContentPane().add(lblNewLabel_2);

		textBtnChooseDate = new ObservingTextField();
		textBtnChooseDate.setEditable(false);
		textBtnChooseDate.setBounds(70, 116, 95, 23);
		getContentPane().add(textBtnChooseDate);
		textBtnChooseDate.setText(OldTaskShow.getDate());

		// JLabel lblReminder = new JLabel(Reminder);
		// lblReminder.setBounds(10, 106, 54, 15);
		// getContentPane().add(lblReminder);
		//
		// JCheckBox chckbxNewCheckBox = new JCheckBox(YesOrNo);
		// chckbxNewCheckBox.setBounds(74, 102, 103, 23);
		// getContentPane().add(chckbxNewCheckBox);
		//
		// JButton btnReminder = new JButton(ChooseDate);
		// btnReminder.setBounds(166, 131, 93, 23);
		// getContentPane().add(btnReminder);

		btnTaskDate = new JButton(ChooseDate);
		btnTaskDate.setBounds(176, 112, 93, 23);
		getContentPane().add(btnTaskDate);
		btnTaskDate.addActionListener(new ClickButtonAction());

		btnOK = new JButton(OK);
		btnOK.setBounds(41, 155, 93, 23);
		getContentPane().add(btnOK);
		btnOK.addActionListener(new ClickButtonAction());

		btnDelete = new JButton(Delete);
		btnDelete.setBounds(144, 155, 93, 23);
		getContentPane().add(btnDelete);
		btnDelete.addActionListener(new ClickButtonAction());

		btnCancel = new JButton(Cancel);
		btnCancel.setBounds(247, 155, 93, 23);
		getContentPane().add(btnCancel);
		btnCancel.addActionListener(new ClickButtonAction());

		// 设置在屏幕居中显示
		Toolkit kit = this.getToolkit();
		Dimension winSize = kit.getScreenSize();
		setBounds(winSize.width / 4, winSize.height / 4, winSize.width / 2,
				winSize.height / 2);

		this.setSize(400, 250);
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
			if (e.getSource() == btnOK) {
				NewTaskUpdate = new Task(NewUser.getiD(), textTitle.getText(),
						textDescription.getText(), textBtnChooseDate.getText(),
						false);
				if (TaskLogic.updateTask(NewTaskUpdate, NewtID)) {
					setVisible(false);
					JOptionPane.showMessageDialog(null, "修改成功");
					Main.getInstance(NewUser).setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "修改出错，请重试");
					return;
				}
			}
			if (e.getSource() == btnCancel) {
				setVisible(false);
				Main.getInstance(NewUser).setVisible(true);

			}
			if (e.getSource() == btnDelete) {
				if (TaskLogic.checkedTaskByTID(NewtID)) {
					JOptionPane.showMessageDialog(null, "此任务被标记已完成，将在任务列表中消失");
				} else {
					JOptionPane.showMessageDialog(null, "标记不成功请重试");
					return;
				}
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

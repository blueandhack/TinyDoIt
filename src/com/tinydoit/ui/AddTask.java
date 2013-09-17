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
import java.util.Calendar;
import java.util.Locale;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.Popup;

import com.qt.datapicker.DatePicker;
import com.tinydoit.domain.Date;
import com.tinydoit.domain.Tag;

import javax.swing.JButton;

public class AddTask extends JFrame {

	// int uID; // �����������ĸ��û�
	// int tID; // ������ı��
	// String title; // ����
	// String description; // ��������
	// Date date; // ����ʼʱ��
	// Date deadlineDate; // �������ʱ��
	// String project; // ��Ŀ
	// String context; // �龳
	// int flag; // ��Ҫ�ȼ���0.�� 1.�� 3.�ͣ�
	// Tag tag; // ��ǩ
	// int reminder; // ���ѣ�Ҳ�������壩
	// boolean check; // �Ƿ���ɣ�false.����� true.��ɣ�
	// String box; // �����ĸ���Ϣ���ӣ�����մ��죬���մ���ȵȣ�

	private JTextField textTaskTitle;
	private JTextArea textTaskDescription;
	private DatePicker chooseDate;
	private JButton btnTaskDate;

	private static AddTask addTask = null;

	private String WindowsTitle = "�������";
	private String TaskTitle = "������";
	private String TaskDescription = "��������";
	private String TaskDate = "��ʼʱ��";
	private String ChooseDate = "ѡ������";
	private String OK = "ȷ��";
	private String Cancel = "ȡ��";

	private ObservingTextField textBtnChooseDate;// ѡ��ʼ���ڰ�ť���ı���
	private JButton btnOkButton;
	private JButton btnCancelButton;

	private AddTask() {

		init();
	}

	public static AddTask getInstance() {
		if (addTask == null) {
			synchronized (AddTask.class) {
				if (addTask == null) {
					addTask = new AddTask();
				}
			}
		}
		return addTask;
	}

	private void init() {
		getContentPane().setLayout(null);

		setTitle(WindowsTitle);

		textTaskTitle = new JTextField();
		textTaskTitle.setBounds(70, 7, 66, 21);
		getContentPane().add(textTaskTitle);
		textTaskTitle.setColumns(10);

		textTaskDescription = new JTextArea();
		textTaskDescription.setBounds(70, 38, 304, 40);
		getContentPane().add(textTaskDescription);

		JLabel lblTaskTitle = new JLabel(TaskTitle);
		lblTaskTitle.setBounds(15, 10, 54, 15);
		getContentPane().add(lblTaskTitle);

		JLabel lblTaskDescription = new JLabel(TaskDescription);
		lblTaskDescription.setBounds(15, 42, 54, 15);
		getContentPane().add(lblTaskDescription);

		JLabel lblTaskDate = new JLabel(TaskDate);
		lblTaskDate.setBounds(15, 87, 54, 15);
		getContentPane().add(lblTaskDate);

		btnTaskDate = new JButton(ChooseDate);
		btnTaskDate.setBounds(195, 88, 95, 23);
		getContentPane().add(btnTaskDate);
		btnTaskDate.addActionListener(new ClickButtonAction());

		textBtnChooseDate = new ObservingTextField();
		textBtnChooseDate.setEditable(false);
		textBtnChooseDate.setBounds(70, 88, 95, 23);
		getContentPane().add(textBtnChooseDate);

		btnOkButton = new JButton(OK);
		btnOkButton.setBounds(70, 229, 93, 23);
		getContentPane().add(btnOkButton);

		btnCancelButton = new JButton(Cancel);
		btnCancelButton.setBounds(269, 229, 93, 23);
		getContentPane().add(btnCancelButton);

		// ��������Ļ������ʾ
		Toolkit kit = this.getToolkit();
		Dimension winSize = kit.getScreenSize();
		setBounds(winSize.width / 4, winSize.height / 4, winSize.width / 2,
				winSize.height / 2);

		this.setSize(600, 300);

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

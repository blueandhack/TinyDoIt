package com.tinydoit.ui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Calendar;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;
import javax.swing.JButton;

import com.qt.datapicker.DatePicker;

public class OldTask extends JFrame {

	private String OK = "ȷ���޸�";
	private String Cancel = "ȡ��";
	private String Delete = "ȷ��ɾ��";
	private String TaskTitle = "������";
	private String TaskDescription = "��������";
	private String TaskDate = "��ʼʱ��";
	private String ChooseDate = "ѡ������";
	private String WindowsTitle = "�޸�����";

	private static OldTask oldTask = null;
	private JTextField textField;

	public static OldTask getInstance() {
		if (oldTask == null) {
			synchronized (OldTask.class) {
				if (oldTask == null) {
					oldTask = new OldTask();
				}
			}
		}
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

		textField = new JTextField();
		textField.setBounds(74, 7, 66, 21);
		getContentPane().add(textField);
		textField.setColumns(10);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(74, 31, 350, 24);
		getContentPane().add(textArea);

		JLabel lblNewLabel_2 = new JLabel(TaskDate);
		lblNewLabel_2.setBounds(10, 60, 54, 15);
		getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setBounds(10, 106, 54, 15);
		getContentPane().add(lblNewLabel_3);

		JCheckBox chckbxNewCheckBox = new JCheckBox("New check box");
		chckbxNewCheckBox.setBounds(74, 102, 103, 23);
		getContentPane().add(chckbxNewCheckBox);

		JButton btnNewButton = new JButton(ChooseDate);
		btnNewButton.setBounds(120, 131, 93, 23);
		getContentPane().add(btnNewButton);

		JButton btnNewButton_1 = new JButton(ChooseDate);
		btnNewButton_1.setBounds(132, 65, 93, 23);
		getContentPane().add(btnNewButton_1);

		JButton btnOK = new JButton(OK);
		btnOK.setBounds(33, 215, 93, 23);
		getContentPane().add(btnOK);

		JButton btnDelete = new JButton(Delete);
		btnDelete.setBounds(187, 215, 93, 23);
		getContentPane().add(btnDelete);

		JButton btnCancel = new JButton(Cancel);
		btnCancel.setBounds(316, 215, 93, 23);
		getContentPane().add(btnCancel);

		// ��������Ļ������ʾ
		Toolkit kit = this.getToolkit();
		Dimension winSize = kit.getScreenSize();
		setBounds(winSize.width / 4, winSize.height / 4, winSize.width / 2,
				winSize.height / 2);

		this.setSize(600, 300);
	}

	public class ObservingTextField extends JTextField implements Observer {
		public void update(Observable o, Object arg) {
			Calendar calendar = (Calendar) arg;
			DatePicker dp = (DatePicker) o;
			setText(dp.formatDate(calendar));

		}
	}
}

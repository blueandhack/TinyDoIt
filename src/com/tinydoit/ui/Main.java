package com.tinydoit.ui;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.tinydoit.dao.TaskDao;
import com.tinydoit.domain.User;
import com.tinydoit.logic.TaskLogic;
import java.awt.Font;
import java.awt.Window.Type;

public class Main extends JFrame {

	JButton btnAddTask;
	String ShowAll = "所有任务";
	String ShowYestoday = "昨日任务";
	String ShowToday = "今日任务";
	String ShowTomorrow = "未来任务";
	JButton btnShowAll;
	JButton btnShowToday;

	JList list;

	static User newUser;
	static int newTID;

	private Main() {
		setTitle("TinyDoIt \u5FAE\u52A8");
		setType(Type.POPUP);

		init();

	}

	// 创建单例
	private static Main main = null;

	public static Main getInstance(User user) {
		newUser = new User(user.getiD(), user.getUserName(), user.getPassword());
		if (main == null) {
			synchronized (Main.class) {
				if (main == null)
					main = new Main();
			}
		}
		return main;
	}

	public static Main getInstance() {
		if (main == null) {
			synchronized (Main.class) {
				if (main == null)
					main = new Main();
			}
		}
		return main;
	}

	// 单例创建完毕

	private void init() {
		// TODO Auto-generated method stub
		String addTaskString = "+添加任务";

		getContentPane().setLayout(null);

		btnAddTask = new JButton(addTaskString);
		btnAddTask.setFont(new Font("宋体", Font.PLAIN, 13));
		btnAddTask.setBounds(10, 17, 100, 66);
		getContentPane().add(btnAddTask);
		btnAddTask.addActionListener(new ClickButtonAction());

		String Date[] = { "任务列表未加载", "请点击左侧所有任务按钮" };
		list = new JList(Date);
		list.setVisibleRowCount(10);
		list.setFont(new Font("宋体", Font.PLAIN, 18));
		TaskLogic.getAllTaskToJList(list, newUser.getiD()); // 获取此用户所有任务列表
		list.setBounds(120, 19, 354, 382);
		getContentPane().add(list);
		list.addMouseListener(new ClickListAction());
		list.addListSelectionListener(new SelectListAction());

		btnShowToday = new JButton(ShowToday);
		btnShowToday.setBounds(10, 139, 100, 35);
		getContentPane().add(btnShowToday);
		btnShowToday.addActionListener(new ClickButtonAction());

		btnShowAll = new JButton(ShowAll);
		btnShowAll.setBounds(10, 93, 100, 36);
		getContentPane().add(btnShowAll);
		btnShowAll.addActionListener(new ClickButtonAction());

		// 设置在屏幕居中显示
		Toolkit kit = this.getToolkit();
		Dimension winSize = kit.getScreenSize();
		setBounds(winSize.width / 6, winSize.height / 6, winSize.width / 2,
				winSize.height / 2);

		this.setSize(500, 450);
	}

	public class ClickButtonAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == btnAddTask) {
				AddTask.getInstance(newUser).setVisible(true);

			}
			if (e.getSource() == btnShowAll) {
				TaskLogic.getAllTaskToJList(list, newUser.getiD());// 获取此用户所有任务列表
			}
			if (e.getSource() == btnShowToday) {
				TaskLogic.getTodayTaskToJList(list, newUser.getiD());
			}
		}
	}

	public class ClickListAction implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			if (e.getClickCount() == 2) {
				OldTask.getInstance(newTID, newUser).setVisible(true);
			}

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}
	}

	public class SelectListAction implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent e) {
			// TODO Auto-generated method stub
			// System.out.println(list.getSelectedValue().toString());
			String SelectedValue = list.getSelectedValue().toString();
			String SelectedValueArray[] = SelectedValue.split("-");
			newTID = Integer.parseInt(SelectedValueArray[1]);
			// System.out.println(newTID);

		}
	}
}

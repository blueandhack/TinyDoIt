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

import com.tinydoit.domain.User;
import com.tinydoit.logic.TaskLogic;

public class Main extends JFrame {

	JButton btnAddTask;
	String ShowAll = "所有任务";
	String ShowYestoday = "昨日任务";
	String ShowToday = "今日任务";
	String ShowTomorrow = "未来任务";
	JButton btnShowAll;
	JButton btnShowYestoday;

	JList list;

	static User newUser;

	private Main() {

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

	// 单例创建完毕

	private void init() {
		// TODO Auto-generated method stub
		String addTaskString = "+添加任务";

		getContentPane().setLayout(null);

		btnAddTask = new JButton(addTaskString);
		btnAddTask.setBounds(10, 10, 100, 35);
		getContentPane().add(btnAddTask);
		btnAddTask.addActionListener(new ClickButtonAction());

		String Date[] = { "Red", "Green" };
		list = new JList(Date);
		TaskLogic.getAllTaskToJList(list, newUser.getUserName()); // 获取此用户所有任务列表
		list.setBounds(120, 19, 634, 382);
		getContentPane().add(list);
		list.addMouseListener(new ClickListAction());

		JButton btnShowToday = new JButton(ShowToday);
		btnShowToday.setBounds(10, 130, 93, 23);
		getContentPane().add(btnShowToday);

		btnShowYestoday = new JButton(ShowYestoday);
		btnShowYestoday.setBounds(10, 228, 93, 23);
		getContentPane().add(btnShowYestoday);

		JButton btnShowTomorrow = new JButton(ShowTomorrow);
		btnShowTomorrow.setBounds(10, 178, 93, 23);
		getContentPane().add(btnShowTomorrow);

		btnShowAll = new JButton(ShowAll);
		btnShowAll.setBounds(10, 75, 93, 23);
		getContentPane().add(btnShowAll);
		btnShowAll.addActionListener(new ClickButtonAction());

		// 设置在屏幕居中显示
		Toolkit kit = this.getToolkit();
		Dimension winSize = kit.getScreenSize();
		setBounds(winSize.width / 6, winSize.height / 6, winSize.width / 2,
				winSize.height / 2);

		this.setVisible(true);
		this.setSize(800, 450);
	}

	public class ClickButtonAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == btnAddTask) {
				AddTask.getInstance().setVisible(true);
			}
			if (e.getSource() == btnShowAll) {
				TaskLogic.getAllTaskToJList(list, newUser.getUserName());// 获取此用户所有任务列表
			}
		}
	}

	public class ClickListAction implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			if (e.getClickCount() == 2) {
				OldTask.getInstance().setVisible(true);
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
}

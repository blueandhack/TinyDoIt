package com.tinydoit.ui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JLabel;

import com.tinydoit.dao.UserDao;
import com.tinydoit.daoimpl.DBUtil;
import com.tinydoit.logic.UserLogic;

public class Register extends JFrame {
	private JTextField UsernameTextField;
	private JPasswordField PasswordField;
	private JPasswordField PasswordConfirmField;
	private JButton RegisterButton;
	private JButton CancelButton;

	private String TinyDoIt = "TinyDoIt";

	private String Username = "用户名";
	private String Password = "密码";
	private String PasswordConfirm = "确认密码";
	private String Register = "确认注册";
	private String Cancel = "取消注册";

	private static Connection conn = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;

	public Register() {

		init();

	}

	private void init() {
		setResizable(false);
		setTitle("\u6CE8\u518C" + TinyDoIt + "(\u5FAE\u52A8)");
		getContentPane().setLayout(null);

		UsernameTextField = new JTextField();
		UsernameTextField.setBounds(143, 48, 190, 35);
		getContentPane().add(UsernameTextField);
		UsernameTextField.setColumns(10);

		RegisterButton = new JButton(Register);
		RegisterButton.setBounds(124, 183, 90, 35);
		getContentPane().add(RegisterButton);
		RegisterButton.addActionListener(new ClickButtonAction());

		CancelButton = new JButton(Cancel);
		CancelButton.setBounds(224, 183, 90, 35);
		getContentPane().add(CancelButton);
		CancelButton.addActionListener(new ClickButtonAction());

		PasswordField = new JPasswordField();
		PasswordField.setBounds(143, 93, 190, 35);
		getContentPane().add(PasswordField);

		PasswordConfirmField = new JPasswordField();
		PasswordConfirmField.setBounds(143, 138, 190, 35);
		getContentPane().add(PasswordConfirmField);

		JLabel UsernameLabel = new JLabel(Username);
		UsernameLabel.setBounds(79, 58, 54, 15);
		getContentPane().add(UsernameLabel);

		JLabel PasswordLabel = new JLabel(Password);
		PasswordLabel.setBounds(79, 103, 54, 15);
		getContentPane().add(PasswordLabel);

		JLabel PasswordConfirmLabel = new JLabel(PasswordConfirm);
		PasswordConfirmLabel.setBounds(79, 148, 54, 15);
		getContentPane().add(PasswordConfirmLabel);

		// 设置在屏幕居中显示
		Toolkit kit = this.getToolkit();
		Dimension winSize = kit.getScreenSize();
		setBounds(winSize.width / 4, winSize.height / 4, winSize.width / 2,
				winSize.height / 2);

		setSize(450, 300);
		setVisible(true);

	}

	public class ClickButtonAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String UsernameString = UsernameTextField.getText();
			char PasswordChar[] = PasswordField.getPassword();
			char PasswordConfirmChar[] = PasswordConfirmField.getPassword();

			String PasswordString = new String(PasswordChar);
			String PasswordConfirmString = new String(PasswordConfirmChar);

			if (e.getSource() == CancelButton) {
				setVisible(false);
				Login.getInstance().setVisible(true);
			}
			if (e.getSource() == RegisterButton) {

				// conn = DBUtil.getConnection();
				if (PasswordString.equals(PasswordConfirmString)) {
					if (UserLogic.checkUserByUsername(UsernameString)) {

						if (UserLogic.addUser(UsernameString, PasswordString)) {
							JOptionPane.showMessageDialog(null, "用户注册成功");

						} else {
							JOptionPane.showMessageDialog(null, "用户注册失败");
							return;
						}
					} else {
						JOptionPane.showMessageDialog(null, "用户名已被注册");
						return;
					}
				} else {
					JOptionPane.showMessageDialog(null, "两次输入密码不相同，请重新输入");
					return;
				}
				setVisible(false);
				Login.getInstance().setVisible(true);
			}

		}
	}

	public boolean RegisterUsername() throws SQLException {

		return false;
	}

}

package com.tinydoit.ui;

import javax.swing.JFrame;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JLabel;

import com.tinydoit.logic.UserLogic;

public class Login extends JFrame {
	private JTextField UsernameTextField;
	private JPasswordField PasswordField;
	private JButton LoginButton;
	private JButton RegisterButton;

	private String TinyDoIt = "TinyDoIt";

	private String Username = "用户名";
	private String Password = "密码";
	private String OK = "登陆";
	private String Register = "注册";

	private Login() {

		init();
	}

	private static Login login = null;

	public static Login getInstance() {
		if (login == null) {
			synchronized (Login.class) {
				if (login == null) {
					login = new Login();
				}
			}
		}
		return login;
	}

	private void init() {

		getContentPane().setLayout(null);
		setResizable(false);
		setTitle(TinyDoIt + "(\u5FAE\u52A8)");
		UsernameTextField = new JTextField();
		UsernameTextField.setBounds(162, 44, 190, 35);
		getContentPane().add(UsernameTextField);
		UsernameTextField.setColumns(10);

		PasswordField = new JPasswordField();
		PasswordField.setBounds(162, 89, 190, 35);
		getContentPane().add(PasswordField);

		LoginButton = new JButton(OK);
		LoginButton.setBounds(110, 160, 90, 35);
		getContentPane().add(LoginButton);
		LoginButton.addActionListener(new ClickButtonAction());

		RegisterButton = new JButton(Register);
		RegisterButton.setBounds(213, 160, 90, 35);
		getContentPane().add(RegisterButton);
		RegisterButton.addActionListener(new ClickButtonAction());

		JLabel UsernameLabel = new JLabel(Username);
		UsernameLabel.setBounds(98, 54, 54, 15);
		getContentPane().add(UsernameLabel);

		JLabel PasswordLabel = new JLabel(Password);
		PasswordLabel.setBounds(98, 99, 54, 15);
		getContentPane().add(PasswordLabel);

		// 设置在屏幕居中显示
		Toolkit kit = this.getToolkit();
		Dimension winSize = kit.getScreenSize();
		setBounds(winSize.width / 4, winSize.height / 4, winSize.width / 2,
				winSize.height / 2);

		this.setVisible(true);
		this.setSize(450, 300);
	}

	public class ClickButtonAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String UsernameString = UsernameTextField.getText();
			char PasswordChar[] = PasswordField.getPassword();
			String PasswordString = new String(PasswordChar);
			if (e.getSource() == LoginButton) {
				if ("".equals(PasswordString) && "".equals(UsernameString)) {
					JOptionPane.showMessageDialog(null, "用户名或密码为空，请重新输入");
					return;
				} else {
					if (UserLogic.checkLoginByUser(UsernameString,
							PasswordString)) {
						setVisible(false);
						new Main();
					} else {
						JOptionPane.showMessageDialog(null, "用户名或密码错误，请重新输入");
						return;
					}
				}
			}
			if (e.getSource() == RegisterButton) {
				setVisible(false);
				// System.out.println("Register");
				new Register();
			}
		}
	}

}

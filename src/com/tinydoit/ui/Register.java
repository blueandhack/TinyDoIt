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

import com.tinydoit.daoimpl.DBUtil;

public class Register extends JFrame {
	private JTextField UsernameTextField;
	private JPasswordField PasswordField;
	private JPasswordField PasswordConfirmField;
	private JButton RegisterButton;
	private JButton CancelButton;

	private String TinyDoIt = "TinyDoIt";

	private String Username = "�û���";
	private String Password = "����";
	private String PasswordConfirm = "ȷ������";
	private String Register = "ȷ��ע��";
	private String Cancel = "ȡ��ע��";

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

		// ��������Ļ������ʾ
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
			char PasswordChar[] = PasswordField.getPassword();
			char PasswordConfirmChar[] = PasswordConfirmField.getPassword();

			String PasswordString = new String(PasswordChar);
			String PasswordConfirmString = new String(PasswordConfirmChar);

			if (e.getSource() == CancelButton) {
				setVisible(false);
				Login.getInstance().setVisible(true);
			}
			if (e.getSource() == RegisterButton) {

				try {
					conn = DBUtil.getConnection();
					if (PasswordString.equals(PasswordConfirmString)) {
						if (SeacherUsername()) {

							if (RegisterUsername()) {
								JOptionPane.showMessageDialog(null, "�û�ע��ɹ�");

							} else {
								JOptionPane.showMessageDialog(null, "�û�ע��ʧ��");
								return;
							}
						} else {
							JOptionPane.showMessageDialog(null, "�û����ѱ�ע��");
							return;
						}
					} else {
						JOptionPane.showMessageDialog(null, "�����������벻��ͬ������������");
						return;
					}

					DBUtil.free(rs, ps, conn);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				setVisible(false);
				Login.getInstance().setVisible(true);
			}

		}
	}

	public boolean SeacherUsername() throws SQLException {

		String UsernameString = UsernameTextField.getText();
		String sql = "select username from userinfo  where username = '"
				+ UsernameString + "'";
		ps = conn.prepareStatement(sql);
		rs = ps.executeQuery();
		while (rs.next()) {
			return false;
		}
		return true;
	}

	public boolean RegisterUsername() throws SQLException {

		String UsernameString = UsernameTextField.getText();
		char PasswordChar[] = PasswordField.getPassword();
		String PasswordString = new String(PasswordChar);

		String sql = "insert into userinfo(username,password) values (?,?) ";
		ps = conn.prepareStatement(sql);
		// ps.setString(1, "1");
		ps.setString(1, UsernameString);
		ps.setString(2, PasswordString);
		int i = ps.executeUpdate();
		if (i > 0) {
			return true;
		}
		return false;
	}

}

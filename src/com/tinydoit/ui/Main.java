package com.tinydoit.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Main extends JFrame {
	public Main() {

		init();

	}

	private void init() {
		// TODO Auto-generated method stub
		String addTaskString = "+";

		getContentPane().setLayout(null);

		JButton btnNewButton = new JButton(addTaskString);
		btnNewButton.setBounds(68, 44, 81, 35);
		getContentPane().add(btnNewButton);

		setVisible(true);
	}

	public class ClickButtonAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

		}

	}
}

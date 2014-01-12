package com.tinydoit.ui;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import javax.swing.JTextArea;

public class AddTask extends JFrame {
	private JTextField textField;

	public AddTask() {

		init();
	}

	private void init() {
		getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(52, 26, 66, 21);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(49, 71, 261, 57);
		getContentPane().add(textArea);

	}
}

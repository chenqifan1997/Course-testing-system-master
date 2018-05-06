package com.mygroup;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class SubjectChose extends JFrame implements ActionListener {

	JTextArea chengjiArea = new JTextArea();
	String c;
	
	public SubjectChose() {
		super();
		this.setTitle("易考试在线考试系统");
		this.setSize(600, 380);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setContentPane(createContentPane());
	}

	private JPanel createContentPane() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(new EmptyBorder(6, 6, 6, 6));
		JLabel label = new JLabel();
		label.setIcon(new ImageIcon("image/maintop.png"));
		panel.add(BorderLayout.NORTH, label);
		panel.add(BorderLayout.CENTER, createchengjiPane());
		panel.add(BorderLayout.SOUTH, createToolsPane());
		return panel;
	}

	private JPanel createToolsPane() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(new EmptyBorder(6, 6, 6, 6));// 提供间隙

		panel.add(BorderLayout.CENTER, createBtnPane());
		return panel;
	}

	private JScrollPane createchengjiPane() {
		JScrollPane panel = new JScrollPane();
		panel.setBorder(new TitledBorder("可选科目"));
		chengjiArea.setLineWrap(true);// 设置允许换行
		chengjiArea.setEditable(false);// 设置考题不能修改
		return panel;
	}

	private JPanel createBtnPane() {
		JPanel panel = new JPanel(new FlowLayout());
		JButton ch = new JButton("返回");
		JButton exam = new JButton("组成原理");
		JButton exam2 = new JButton("低等算数");
		panel.add(exam);
		panel.add(exam2);
		exam.addActionListener(this);
		exam2.addActionListener(this);
		ch.addActionListener(this);
		panel.add(ch);
		panel.add(ch);
		return panel;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {


		if (arg0.getActionCommand().equals("返回")) {
			this.setVisible(false);
			TeachMain m = new TeachMain();
			m.setVisible(true);
		}
		if (arg0.getActionCommand().equals("组成原理")) {
			c = "1";
			try {
				File file = new File("E:/git/Course-testing-system-master/data/control.txt");
				if (!file.exists()) {
					file.createNewFile();
				}
				FileWriter fw = new FileWriter(file);
				fw.write(c);
				fw.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (arg0.getActionCommand().equals("低等算数")) {
			c = "2";
			try {
				File file = new File("E:/git/Course-testing-system-master/data/control.txt");
				if (!file.exists()) {
					file.createNewFile();
				}
				FileWriter fw = new FileWriter(file);
				fw.write(c);
				fw.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args) {

	}
}

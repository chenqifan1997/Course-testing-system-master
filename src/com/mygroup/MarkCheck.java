package com.mygroup;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Wrapper;
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

public class MarkCheck extends JFrame implements ActionListener {

	JTextArea chengjiArea = new JTextArea();
	int b = 0;
	public static String str = null; // 将StringBuffer转化成字符串
	public static StringBuffer sb = new StringBuffer(); // StringBuffer便于字符串的增删改查操作

	public MarkCheck() {
		super();
		this.setTitle("易考试在线考试系统");
		this.setSize(600, 380);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setContentPane(createContentPane());
		// this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

		ShowMark();
		panel.add(BorderLayout.CENTER, createBtnPane());
		return panel;
	}

	private JScrollPane createchengjiPane() {
		JScrollPane panel = new JScrollPane();
		panel.setBorder(new TitledBorder("学生成绩"));
		chengjiArea.setLineWrap(true);// 设置允许换行
		chengjiArea.setEditable(false);// 设置考题不能修改
		panel.getViewport().add(chengjiArea);
		return panel;
	}

	private JPanel createBtnPane() {
		JPanel panel = new JPanel(new FlowLayout());
		JButton ch = new JButton("返回");
		JButton ch1 = new JButton("下一个");
		ch.addActionListener(this);
		ch1.addActionListener(this);
		panel.add(ch);
		panel.add(ch1);
		return panel;
	}

	private void ShowMark() {
		// String op = null;
		Connection cn = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			cn = DataBase.getConnection("personal");
			st = cn.createStatement();
			rs = st.executeQuery("select * from mark");

			while (rs.next()) {

				/*
				 * chengjiArea.setText("姓名："+rs.getString("name") + "    班级：" +
				 * rs.getString("class")+"班" + "    学号：" +
				 * rs.getString("nummber") + "    分数：" + rs.getString("score"));
				 */

				sb.append(rs.getString(1)); // 读出每一列的数据
				sb.append("*"); // 在每列数据后面做标记，将来便于做拆分
				sb.append(rs.getString(2));
				sb.append("*");
				sb.append(rs.getString(3));
				sb.append("*");
				sb.append(rs.getString(4));
				sb.append("%"); // 在每条数据后面做标记，便于拆分

			}
			str = sb.toString(); // 将数据由StringBuffer类型转化成String类型
			// String datas;
			// 将总数据以指定字符分割成数组，每条数据为数组的一项
			String[] params = str.split("%");

			// 将每条数据再拆分，则param数据保存的是一条数据的每一项数据
			for (int i = 0; i < params.length; i++) {
				String[] param = params[b].split("\\*");
				if (b == i) {
					chengjiArea.setText("姓名：" + param[1] + "    班级：" + param[2] + "班" + "    学号：" + param[0] + "    分数："
							+ param[3]);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			chengjiArea.setText("未有任何考生成绩信息！！！");
		} finally {
			try {
				rs.close();
				st.close();
				cn.close();
			} catch (Exception e) {
			}

		}

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

		if (arg0.getActionCommand().equals("返回")) {
			this.setVisible(false);
			TeachMain m = new TeachMain();
			m.setVisible(true);
			Connection cn = null;
			Statement st = null;

			try {
				cn = DataBase.getConnection("personal");
				// 生成一条mysql语句
				String sql = "delete from mark ";
				st = cn.createStatement();// 创建一个Statement对象
				st.executeUpdate(sql);// 执行sql语句
				st.close();
				cn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		if (arg0.getActionCommand().equals("下一个")) {
			b++;
			ShowMark();
		}
	}

	public static void main(String[] args) {

	}
}

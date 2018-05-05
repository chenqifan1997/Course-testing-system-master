package com.mygroup;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Reset extends JFrame implements ActionListener {

	static JTextField text1 = new JTextField();
	static JTextField text2 = new JTextField();
	static JPasswordField pass1 = new JPasswordField();
	static JPasswordField pass2 = new JPasswordField();

	public Reset() {
		super();
		this.setSize(615, 400);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setTitle("忘记密码");
		getContentPane().add(topPanel(), BorderLayout.NORTH);
		getContentPane().add(centerPanel(), BorderLayout.CENTER);
		getContentPane().add(buttonPanel(), BorderLayout.SOUTH);
	}

	private JPanel topPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBorder(new EmptyBorder(0, 110, 20, 0));
		JLabel label = new JLabel();
		label.setIcon(new ImageIcon("image/reset.png"));
		panel.add(label, BorderLayout.CENTER);
		return panel;
	}

	private JPanel centerPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(4, 2));
		JLabel label1 = new JLabel();
		label1.setText("          请输入账号");
		label1.setFont(new Font("楷体", Font.BOLD, 25));
		text1.setFont(new Font("宋体", Font.BOLD, 30));
		panel.add(label1);
		panel.add(text1);
		JLabel label2 = new JLabel();
		label2.setText("          请输入姓名");
		label2.setFont(new Font("楷体", Font.BOLD, 25));
		text2.setFont(new Font("宋体", Font.BOLD, 30));
		panel.add(label2);
		panel.add(text2);
		JLabel label3 = new JLabel();
		label3.setText("          修改密码");
		label3.setFont(new Font("楷体", Font.BOLD, 25));
		pass1.setFont(new Font("宋体", Font.BOLD, 30));
		panel.add(label3);
		panel.add(pass1);
		JLabel label4 = new JLabel();
		label4.setText("          重复密码");
		label4.setFont(new Font("楷体", Font.BOLD, 25));
		pass2.setFont(new Font("宋体", Font.BOLD, 30));
		panel.add(label4);
		panel.add(pass2);
		return panel;
	}

	private JPanel buttonPanel() {
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(10, 80, 20, 80));
		panel.setLayout(new GridLayout(1, 2));
		JButton button1 = new JButton("确认");
		button1.setFont(new Font("宋体", Font.BOLD, 30));
		JButton button2 = new JButton("返回");
		button2.setFont(new Font("宋体", Font.BOLD, 30));
		button1.addActionListener(this);
		button2.addActionListener(this);
		panel.add(button1);
		panel.add(button2);
		return panel;
	}

	private void check() {
		String s1 = text1.getText();
		String s2 = text2.getText();
		String s3 = pass1.getText();
		String s4 = pass2.getText();

		Connection cn = null;
		Statement st = null;
		ResultSet rs = null;
		PreparedStatement pre = null;

		if (s1.isEmpty() || s2.isEmpty() || s3.isEmpty() || s4.isEmpty()) {
			JOptionPane.showMessageDialog(null, "输入不可为空", "警告", JOptionPane.ERROR_MESSAGE);
		}

		if (!s3.equals(s4)) {
			JOptionPane.showMessageDialog(null, "两次密码输入不一致", "警告", JOptionPane.ERROR_MESSAGE);
		}

		try {
			cn = DataBase.getConnection("personal");
			st = cn.createStatement();
			rs = st.executeQuery("select * from message");
			while (rs.next()) {
				if (s1.equals(rs.getString("id")) && s2.equals(rs.getString("name"))) {
					Connection conn = (Connection) DataBase.getConnection("personal");
					String sql = "update message set password=? where id=?";

					PreparedStatement ptmt = (PreparedStatement) conn.prepareStatement(sql);
					ptmt.setString(1, s3);
					ptmt.setString(2, s1);

					ptmt.execute();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				st.close();
				cn.close();
			} catch (Exception e) {
			}
		}
	}

	public static void main(String[] args) {

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getActionCommand().equals("确认")) {
			check();
			this.setVisible(false);
			Login l = new Login();
			l.setVisible(true);
		}

		if (arg0.getActionCommand().equals("返回")) {
			this.setVisible(false);
			Login l = new Login();
			l.setVisible(true);
		}
	}
}

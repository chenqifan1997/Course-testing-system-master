package com.mygroup;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.sql.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;

public class Login extends JFrame implements ActionListener {

	JTextField text = new JTextField();
	JPasswordField pass = new JPasswordField();

	public Login() {
		super();
		this.setSize(615, 400);
		this.setResizable(false);
		this.setTitle("登录界面");
		this.setLocationRelativeTo(null);
		Cursor cursor = createCursor();
		this.setCursor(cursor);
		getContentPane().add(topPanel(), BorderLayout.NORTH);
		getContentPane().add(centerPanel(), BorderLayout.CENTER);
		getContentPane().add(buttonPanel(), BorderLayout.SOUTH);
	}

	private JPanel topPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		JLabel label = new JLabel();
		label.setIcon(new ImageIcon("image/title.png"));
		panel.add(label, BorderLayout.CENTER);
		return panel;
	}

	private JPanel centerPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 2));
		panel.setBorder(new EmptyBorder(40, 80, 20, 100));
		JLabel label1 = new JLabel();
		label1.setText("账号：");
		label1.setFont(new Font("楷体", Font.BOLD, 20));
		text.setFont(new Font("宋体", Font.BOLD, 30));
		panel.add(label1);
		panel.add(text);
		JLabel label2 = new JLabel();
		label2.setText("密码：");
		label2.setFont(new Font("楷体", Font.BOLD, 20));
		pass.setFont(new Font("宋体", Font.BOLD, 30));
		panel.add(label2);
		panel.add(pass);
		return panel;
	}

	private JPanel buttonPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 4));
		panel.setBorder(new EmptyBorder(0, 80, 40, 80));
		JButton button1 = new JButton();
		button1.setText("学生登录");
		button1.setFont(new Font("宋体", Font.BOLD, 15));
		button1.addActionListener(this);
		JButton button2 = new JButton();
		button2.setText("注册");
		button2.setFont(new Font("宋体", Font.BOLD, 15));
		button2.addActionListener(this);
		JButton button3 = new JButton();
		button3.setText("重置");
		button3.setFont(new Font("宋体", Font.BOLD, 15));
		button3.addActionListener(this);
		JButton button4 = new JButton();
		button4.setText("教师登录");
		button4.setFont(new Font("宋体", Font.BOLD, 15));
		button4.addActionListener(this);
		panel.add(button2);
		panel.add(button1);
		panel.add(button3);
		panel.add(button4);
		return panel;
	}

	public Cursor createCursor() {
		String url = "image/cursor.png";
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image image = new ImageIcon(url).getImage();
		Cursor cursor = tk.createCustomCursor(image, new Point(10, 10), "norm");
		return cursor;
	}

	public static void main(String[] args) {
		Login l = new Login();
		l.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getActionCommand().equals("注册")) {
			this.setVisible(false);
			Register rg = new Register();
			rg.setVisible(true);
		}

		if (arg0.getActionCommand().equals("学生登录")) {
			String num = text.getText();
			String password = pass.getText();
			Connection cn = null;
			Statement st = null;
			ResultSet rs = null;
			try {
				cn = DataBase.getConnection("personal");
				st = cn.createStatement();
				rs = st.executeQuery("select * from message");
				boolean b = false;
				while (rs.next()) {
					if (num.equals(rs.getString("id")) && password.equals(rs.getString("password"))) {
						b = true;
						try {
							File file = new File("E:/git/Course-testing-system-master/data/data.txt");

							if (!file.exists()) {
								file.createNewFile();
							}

							FileWriter fw = new FileWriter(file);
							fw.write(num);
							fw.close();

						} catch (Exception e) {
							e.printStackTrace();
						}

						break;
					}
				}

				if (b) {
					this.setVisible(false);
					Message m = new Message();
					m.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "输入有误", "警告", JOptionPane.ERROR_MESSAGE);
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

		if (arg0.getActionCommand().equals("重置")) {
			this.setVisible(false);
			Reset r = new Reset();
			r.setVisible(true);
		}

		if (arg0.getActionCommand().equals("教师登录")) {
			String num = text.getText();
			String password = pass.getText();
			Connection cn = null;
			Statement st = null;
			ResultSet rs = null;
			try {
				cn = DataBase.getConnection("personal");
				st = cn.createStatement();
				rs = st.executeQuery("select * from tmessage");
				boolean b = false;
				while (rs.next()) {
					if (num.equals(rs.getString("id")) && password.equals(rs.getString("password"))) {
						b = true;
						try {
							File file = new File("E:/git/Course-testing-system-master/data/tdata.txt");

							if (!file.exists()) {
								file.createNewFile();
							}

							FileWriter fw = new FileWriter(file);
							fw.write(num);
							fw.close();

						} catch (Exception e) {
							e.printStackTrace();
						}

						break;
					}
				}

				if (b) {
					this.setVisible(false);
					TeachMain m = new TeachMain();
					m.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "输入有误", "警告", JOptionPane.ERROR_MESSAGE);
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
	}
}

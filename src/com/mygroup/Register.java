package com.mygroup;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Register extends JFrame implements ActionListener {
	
	TextField text0 = new TextField();
	TextField text1 = new TextField();
	TextField text2 = new TextField();
	TextField text3 = new TextField();
	TextField text4 = new TextField();
	TextField text5 = new TextField();
	TextField text6 = new TextField();
	JPasswordField pass1 = new JPasswordField();

	public Register(){
		super();
		this.setSize(615, 600);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setTitle("注册界面");
		getContentPane().add(topPanel(), BorderLayout.NORTH);
		getContentPane().add(centerPanel(), BorderLayout.CENTER);
		getContentPane().add(buttonPanel(), BorderLayout.SOUTH);
	}
	
	private JPanel topPanel(){
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBorder(new EmptyBorder(0,110,20,0));
		JLabel label = new JLabel();
		label.setIcon(new ImageIcon("image/register.png"));
		panel.add(label, BorderLayout.CENTER);
		return panel;
	}
	
	private JPanel centerPanel(){
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(8, 2));
		panel.setBorder(new EmptyBorder(0,0,10,0));
		JLabel label0 = new JLabel();
		label0.setText("          账号：");
		label0.setFont(new Font("楷体",Font.BOLD,20));
		text0.setFont(new Font("宋体",Font.BOLD,28));
		panel.add(label0);
		panel.add(text0);
		JLabel label1 = new JLabel();
		label1.setText("          姓名：");
		label1.setFont(new Font("楷体",Font.BOLD,20));
		text1.setFont(new Font("宋体",Font.BOLD,28));
		panel.add(label1);
		panel.add(text1);
		JLabel label2 = new JLabel();
		label2.setText("          学号：");
		label2.setFont(new Font("楷体",Font.BOLD,20));
		text2.setFont(new Font("宋体",Font.BOLD,28));
		panel.add(label2);
		panel.add(text2);
		JLabel label3 = new JLabel();
		label3.setText("          性别：");
		label3.setFont(new Font("楷体",Font.BOLD,20));
		text3.setFont(new Font("宋体",Font.BOLD,28));
		panel.add(label3);
		panel.add(text3);
		JLabel label4 = new JLabel();
		label4.setText("          专业：");
		label4.setFont(new Font("楷体",Font.BOLD,20));
		text4.setFont(new Font("宋体",Font.BOLD,28));
		panel.add(label4);
		panel.add(text4);
		JLabel label5 = new JLabel();
		label5.setText("          班级：");
		label5.setFont(new Font("楷体",Font.BOLD,20));
		text5.setFont(new Font("宋体",Font.BOLD,28));
		panel.add(label5);
		panel.add(text5);
		JLabel label6 = new JLabel();
		label6.setText("          年级：");
		label6.setFont(new Font("楷体",Font.BOLD,20));
		text6.setFont(new Font("宋体",Font.BOLD,28));
		panel.add(label6);
		panel.add(text6);
		JLabel label7 = new JLabel();
		label7.setText("          密码：");
		label7.setFont(new Font("楷体",Font.BOLD,20));
		pass1.setFont(new Font("宋体",Font.BOLD,28));
		panel.add(label7);
		panel.add(pass1);
		return panel;
	}
	
	private JPanel buttonPanel(){
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(10,80,20,80));
		panel.setLayout(new GridLayout(1, 2));
		JButton button1 = new JButton("确认");
		button1.setFont(new Font("宋体",Font.BOLD,30));
		JButton button2 = new JButton("返回");
		button2.setFont(new Font("宋体",Font.BOLD,30));
		button1.addActionListener(this);
		button2.addActionListener(this);
		panel.add(button1);
		panel.add(button2);
		return panel;
	}
	
	private void insert(){
		String s0 = text0.getText();
		String s1 = text1.getText();
		String s2 = text2.getText();
		String s3 = text3.getText();
		String s4 = text4.getText();
		String s5 = text5.getText();
		String s6 = text6.getText();
		String s7 = pass1.getText();
		
		Connection cn = null;
		Statement st = null;
		ResultSet rs = null;
		PreparedStatement pre = null;
		
		if(s0.isEmpty() ||s1.isEmpty() || s2.isEmpty() || s3.isEmpty() || s4.isEmpty() || s5.isEmpty() || s6.isEmpty() || s7.isEmpty()){
			JOptionPane.showMessageDialog(null, "输入不可为空", "警告", JOptionPane.ERROR_MESSAGE);
		}
		
		try{
			Class.forName("org.gjt.mm.mysql.Driver");
			cn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/personal","root","123456");
			st = cn.createStatement();
			
		    String sql = "insert into message(id,name,number,sex,major,class,grade,password) values(?,?,?,?,?,?,?,?)";
		        
		    PreparedStatement ptmt = (PreparedStatement) cn.prepareStatement(sql);

		    ptmt.setString(1, s0);
		    ptmt.setString(2, s1);
		    ptmt.setString(3, s2);
		    ptmt.setString(4, s3);
		    ptmt.setString(5, s4);
		    ptmt.setString(6, s5);
		    ptmt.setString(7, s6);
		    ptmt.setString(8, s7);

		        ptmt.execute();
			}catch(Exception e){
				e.printStackTrace();
			}
		finally{
				try{
					pre.close();
					rs.close();
					st.close();
					cn.close();
				}catch(Exception e){}
		}
	}
	
	public static void main(String[] arg0){
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("确认")){
			insert();
			this.setVisible(false);
			Login l = new Login();
			l.setVisible(true);
		}
		
		if(e.getActionCommand().equals("返回")){
			this.setVisible(false);
			Login l = new Login();
			l.setVisible(true);
		}
	}
	
}

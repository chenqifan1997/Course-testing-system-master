package com.mygroup;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Message extends JFrame  implements ActionListener {
	
	public Login l;
	
	TextField text0 = new TextField();
	TextField text1 = new TextField();
	TextField text2 = new TextField();
	TextField text3 = new TextField();
	TextField text4 = new TextField();
	TextField text5 = new TextField();
	
	public Message(){
		super();
		this.setSize(615, 400);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setTitle("个人信息");
		getContentPane().add(topPanel(), BorderLayout.NORTH);
		getContentPane().add(centerPanel(), BorderLayout.CENTER);
		getContentPane().add(bottomPanel(), BorderLayout.SOUTH);
	}
	
	private JPanel topPanel(){
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		JLabel label = new JLabel();
		label.setIcon(new ImageIcon("image/top.png"));
		panel.add(label, BorderLayout.CENTER);
		return panel;
	}
	
	private JPanel centerPanel(){
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(6, 2));
		panel.setBorder(new EmptyBorder(0,0,10,0));
		JLabel label0 = new JLabel();
		label0.setText("          姓名：");
		label0.setFont(new Font("楷体",Font.BOLD,20));
		text0.setFont(new Font("宋体",Font.BOLD,25));
		text0.setEditable(false);
		panel.add(label0);
		panel.add(text0);
		JLabel label1 = new JLabel();
		label1.setText("          学号：");
		label1.setFont(new Font("楷体",Font.BOLD,20));
		text1.setFont(new Font("宋体",Font.BOLD,25));
		text1.setEditable(false);
		panel.add(label1);
		panel.add(text1);
		JLabel label2 = new JLabel();
		label2.setText("          性别：");
		label2.setFont(new Font("楷体",Font.BOLD,20));
		text2.setFont(new Font("宋体",Font.BOLD,25));
		text2.setEditable(false);
		panel.add(label2);
		panel.add(text2);
		JLabel label3 = new JLabel();
		label3.setText("          专业：");
		label3.setFont(new Font("楷体",Font.BOLD,20));
		text3.setFont(new Font("宋体",Font.BOLD,25));
		text3.setEditable(false);
		panel.add(label3);
		panel.add(text3);
		JLabel label4 = new JLabel();
		label4.setText("          班级：");
		label4.setFont(new Font("楷体",Font.BOLD,20));
		text4.setFont(new Font("宋体",Font.BOLD,25));
		text4.setEditable(false);
		panel.add(label4);
		panel.add(text4);
		JLabel label5 = new JLabel();
		label5.setText("          年级：");
		label5.setFont(new Font("楷体",Font.BOLD,20));
		text5.setFont(new Font("宋体",Font.BOLD,25));
		text5.setEditable(false);
		panel.add(label5);
		panel.add(text5);
		return panel;
	}
	
	private JPanel bottomPanel(){
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 3));
		JButton button1 = new JButton();
		button1.setText("下一步");
		button1.addActionListener(this);
		JButton button2 = new JButton();
		button2.setText("确认");
		button2.addActionListener(this);
		JButton button3 = new JButton();
		button3.setText("返回上级");
		button3.addActionListener(this);
		panel.add(button1);
		panel.add(button2);
		panel.add(button3);
		return panel;
	}
	
	private void Person(){
		String s = "";
		Connection cn = null;
		Statement st = null;
		ResultSet rs = null;
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		try{
			File file = new File("E:/workspace for danei/Course-testing-system-master/data/data.txt");
			fis = new FileInputStream(file);
			isr = new InputStreamReader(fis);
			br = new BufferedReader(isr);
			String linetext = null;
			while ((linetext = br.readLine()) != null){
				s += linetext;
			}
			
			Class.forName("org.gjt.mm.mysql.Driver");
			cn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/personal","root","cqf85453306");
			st = cn.createStatement();
			rs = st.executeQuery("select * from message");
			while (rs.next()){
				if(s.equals(rs.getString("id"))){
					text0.setText(rs.getString("name"));
					text1.setText(rs.getString("number"));
					text2.setText(rs.getString("sex"));
					text3.setText(rs.getString("major"));
					text4.setText(rs.getString("class"));
					text5.setText(rs.getString("grade"));
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				br.close();
				isr.close();
				fis.close();
				rs.close();
				st.close();
				cn.close();
			}catch(Exception e){} 
		}
	}
	
	private void Check(){
		if(text0.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "请验证你的个人信息", "警告", JOptionPane.ERROR_MESSAGE);
		}else{
			this.setVisible(false);
			Main w = new Main();
			w.setVisible(true);
		}
	}
	
	public static void main(String[] args){
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getActionCommand().equals("下一步")){
			Person();
		}
		if(arg0.getActionCommand().equals("确认")){
			Check();
		}
		if(arg0.getActionCommand().equals("返回上级")){
			this.setVisible(false);
			Login l = new Login();
			l.setVisible(true);
		}
	}
	
}

package com.mygroup;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.sql.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Login extends JFrame implements ActionListener {
	
    JTextField text = new JTextField();
    JPasswordField pass = new JPasswordField();
	
	public Login(){
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
	
	private JPanel topPanel(){
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		JLabel label = new JLabel();
		label.setIcon(new ImageIcon("image/title.png"));
		panel.add(label, BorderLayout.CENTER);
		return panel;
	}
	
	private JPanel centerPanel(){
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 2));
		panel.setBorder(new EmptyBorder(40,80,20,100));
		JLabel label1 = new JLabel();
		label1.setText("账号：");
		label1.setFont(new Font("楷体",Font.BOLD,20));
		text.setFont(new Font("宋体",Font.BOLD,30));
		panel.add(label1);
		panel.add(text);
		JLabel label2 = new JLabel();
		label2.setText("密码：");
		label2.setFont(new Font("楷体",Font.BOLD,20));
		pass.setFont(new Font("宋体",Font.BOLD,30));
		panel.add(label2);
		panel.add(pass);
		return panel;
	}
	
	private JPanel buttonPanel(){
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBorder(new EmptyBorder(0,220,40,220));
		JButton button = new JButton();
		button.setText("登录");
		button.setFont(new Font("宋体",Font.BOLD,30));
		button.addActionListener(this);
		panel.add(button, BorderLayout.CENTER);
		return panel;
	}
	
	public Cursor createCursor(){
		String url = "image/cursor.png"; 
		Toolkit tk = Toolkit.getDefaultToolkit(); 
		Image image = new ImageIcon(url).getImage(); 
		Cursor cursor = tk.createCustomCursor(image, new Point(10, 10), "norm");
		return cursor;
	}
	
	public static void main(String[] args){
		Login l = new Login();
		l.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getActionCommand().equals("登录")){
		    String num = text.getText();
			String password = pass.getText();
			Connection cn = null;
			Statement st = null;
			ResultSet rs = null;
			try{
				Class.forName("org.gjt.mm.mysql.Driver");
				cn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/personal","root","123456");
				st = cn.createStatement();
				rs = st.executeQuery("select * from message");
				boolean b = false;
				while(rs.next()){
					if(num.equals(rs.getString("id")) && password.equals(rs.getString("password"))){
						b = true;
						try{
							File file = new File("E:/BaiduNetdiskDownload/64位window系统/workspace/Course-testing-system-master/data/data.txt");
							
							if(!file.exists()){
								file.createNewFile();
							}
							
							FileWriter fw = new FileWriter(file);  
							fw.write(num);
							fw.close();
							
						}catch(Exception e){
							e.printStackTrace();
						}
						
						break;
					}
				}
				
				if(b){
					this.setVisible(false);
					Message m = new Message();
					m.setVisible(true);
				}else{
					JOptionPane.showMessageDialog(null, "输入有误", "警告", JOptionPane.ERROR_MESSAGE);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			finally{
				try{
					rs.close();
					st.close();
					cn.close();
				}catch(Exception e){}
			}
		}
	}

}

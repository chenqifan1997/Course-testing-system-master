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
	
	public Login(){
		super();
		this.setSize(615, 400);
		this.setResizable(false);
		this.setTitle("��¼����");
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
		label1.setText("�˺ţ�");
		label1.setFont(new Font("����",Font.BOLD,20));
		text.setFont(new Font("����",Font.BOLD,30));
		panel.add(label1);
		panel.add(text);
		JLabel label2 = new JLabel();
		label2.setText("���룺");
		label2.setFont(new Font("����",Font.BOLD,20));
		pass.setFont(new Font("����",Font.BOLD,30));
		panel.add(label2);
		panel.add(pass);
		return panel;
	}
	
	private JPanel buttonPanel(){
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 3));
		panel.setBorder(new EmptyBorder(0,120,40,120));
		JButton button1 = new JButton();
		button1.setText("��¼");
		button1.setFont(new Font("����",Font.BOLD,30));
		button1.addActionListener(this);
		JButton button2 = new JButton();
		button2.setText("ע��");
		button2.setFont(new Font("����",Font.BOLD,30));
		button2.addActionListener(this);
		JButton button3 = new JButton();
		button3.setText("����");
		button3.setFont(new Font("����",Font.BOLD,30));
		button3.addActionListener(this);
		panel.add(button2);	
		panel.add(button1);	
		panel.add(button3);	
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
		if(arg0.getActionCommand().equals("ע��")){
			this.setVisible(false);
			Register rg = new Register();
			rg.setVisible(true);
		}
		
		if(arg0.getActionCommand().equals("��¼")){
		    String num = text.getText();
			String password = pass.getText();
			Connection cn = null;
			Statement st = null;
			ResultSet rs = null;
			try{
				Class.forName("org.gjt.mm.mysql.Driver");
				cn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/personal","root","cqf85453306");
				st = cn.createStatement();
				rs = st.executeQuery("select * from message");
				boolean b = false;
				while(rs.next()){
					if(num.equals(rs.getString("id")) && password.equals(rs.getString("password"))){
						b = true;
						try{
							File file = new File("E:/workspace for danei/Course-testing-system-master/data/data.txt");
							
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
					JOptionPane.showMessageDialog(null, "��������", "����", JOptionPane.ERROR_MESSAGE);
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
		
		if(arg0.getActionCommand().equals("����")){
			this.setVisible(false);
			Reset r = new Reset();
			r.setVisible(true);
		}
	}

}

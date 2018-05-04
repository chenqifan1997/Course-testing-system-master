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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

public class Main extends JFrame implements ActionListener{
	
	JTextField text1 = new JTextField();
	JLabel text3 = new JLabel();
	JLabel text4 = new JLabel();
	JLabel text5 = new JLabel();
	JLabel  questionCount = new JLabel();
	JTextArea questionArea = new JTextArea();
	Option[] options = new Option[4];
	static int a = 180;
	int b = 1;
	int sum = 0;
	String str,str1,str2,str3 = null;
public  Main(){
	    super();
		this.setTitle("易考试在线考试系统");
		this.setSize(600, 380);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setContentPane(createContentPane());
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	private JPanel createContentPane(){
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(new EmptyBorder(6,6,6,6));
		JLabel label = new JLabel();
		label.setIcon(new ImageIcon("image/maintop.png"));
		panel.add(BorderLayout.NORTH, label);
		panel.add(BorderLayout.CENTER, createCenterPane());
		panel.add(BorderLayout.SOUTH,createToolsPane());
		return panel;
	}
	private JPanel createCenterPane(){
		JPanel panel = new JPanel(new BorderLayout()); 
		panel.add(BorderLayout.NORTH, createExaminfoPane());
		panel.add(BorderLayout.CENTER, createQuestionPane());
		panel.add(BorderLayout.SOUTH, createOptionPane());
		return panel;
	}
	
	private JPanel createExaminfoPane(){
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 6));
		JLabel name = new JLabel("姓名：");
		Name();
		text1.setEditable(false);
		panel.add(name);
		panel.add(text1);
		JLabel time = new JLabel("时间：");
		JTextField text2 = new JTextField();
		text2.setEditable(false);
		Date day=new Date();    
		SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");  
		text2.setText(df.format(day));
		panel.add(time);
		panel.add(text2);
		JLabel score = new JLabel("分数：");
		text3.setText(0+"分");
		panel.add(score);
		panel.add(text3);
		return panel;
	}
	
																																																																																																																																																																																																																																																																																																																																																																																																																																																		 private JPanel createToolsPane(){
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(new EmptyBorder(6,6,6,6));//提供间隙
		
		questionCount.setText("第"+b+"题");
	    
		Exam();
		
		JLabel time = new JLabel();
		new Thread(){
			@SuppressWarnings("deprecation")
			@Override
		public void run() {
			// TODO Auto-generated method stub
			
			while(a > 0){
			try
			{   
				time.setText(a+"秒");	
				Thread.sleep(1000);
				a--;
			}
			catch(Exception e){
				 e.printStackTrace();
			}
			if(a==0){
					 time.setText("0秒"); 
				     JOptionPane.showMessageDialog(null, "时间到！请交卷", "警告", JOptionPane.ERROR_MESSAGE);
				     this.stop();
				     this.destroy();
				 }
		}
	}}.start();

		panel.add(BorderLayout.WEST,questionCount);
		panel.add(BorderLayout.EAST,time);
		panel.add(BorderLayout.CENTER,createBtnPane());
		return panel;
	}
	
	private JScrollPane createQuestionPane(){
		JScrollPane panel  =  new JScrollPane();
		panel.setBorder(new TitledBorder("题目"));
		//questionArea.setText("");
		questionArea.setLineWrap(true);//设置允许换行
		questionArea.setEditable(false);//设置考题不能修改
		panel.getViewport().add(questionArea);//把题目放到不带有滚动条的部分 
		return panel;
	}
	
	private JPanel createBtnPane(){
		JPanel panel = new JPanel(new FlowLayout());
		JButton pre = new JButton("上一题");
		JButton next = new JButton("下一题");
		JButton send = new JButton("提交");
		pre.addActionListener(this);
		next.addActionListener(this);
		send.addActionListener(this);
		panel.add(pre);
		panel.add(next);
		panel.add(send);
		return panel;
	}

	class Option extends JRadioButton{
		int value;
		public Option(int val, String txt){
			super(txt);//表示调用父类有参构造器
			this.value = val;
		}
	}
	
	private JPanel createOptionPane(){
		JPanel panel = new JPanel();
		ButtonGroup bg = new ButtonGroup();
		Option a = new Option(0,"A");
		Option b = new Option(1,"B");
		Option c = new Option(2,"C");
		Option d = new Option(3,"D");
		//Option[] opt = new Option[4];
		options[0] = a;
		options[1] = b;
		options[2] = c;
		options[3] = d;
		bg.add(a);
		bg.add(b);
		bg.add(c);
		bg.add(d);
		panel.add(a);
		panel.add(b);
		panel.add(c);
		panel.add(d);
		return panel;
	}
	
	
	private void Name(){
		String s = "";
		Connection cn = null;
		Statement st = null;
		ResultSet rs = null;
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		try{
			File file = new File("E:/git/Course-testing-system-master/data/data.txt");
			fis = new FileInputStream(file);
			isr = new InputStreamReader(fis);
			br = new BufferedReader(isr);
			String linetext = null;
			while ((linetext = br.readLine()) != null){
				s += linetext;
			}
			
			Class.forName("org.gjt.mm.mysql.Driver");
			cn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/personal","root","123456");
			st = cn.createStatement();
			rs = st.executeQuery("select * from message");
			while (rs.next()){
				if(s.equals(rs.getString("id"))){
					text1.setText(rs.getString("name"));
					text4.setText(rs.getString("class"));
					text5.setText(rs.getString("number"));
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
	
	private void Exam(){
		//String op = null;
		Connection cn = null;
		Statement st = null;
		ResultSet rs = null;
		try{
			Class.forName("org.gjt.mm.mysql.Driver");
			cn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/personal","root","123456");
			st = cn.createStatement();
			rs = st.executeQuery("select * from exam");
			while (rs.next()){
				if(b == rs.getInt(1)){
					questionArea.setText(rs.getString("no") + "." + rs.getString("content")
					+ "\n" + rs.getString("option1")
					+ "\n" + rs.getString("option2")
					+ "\n" + rs.getString("option3")
					+ "\n" + rs.getString("option4"));
					
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				rs.close();
				st.close();
				cn.close();
			}catch(Exception e){} 
		}
	}
	
	private void check(){
		String op = null;
		Connection cn = null;
		Statement st = null;
		ResultSet rs = null;
		try{
			Class.forName("org.gjt.mm.mysql.Driver");
			cn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/personal","root","123456");
			st = cn.createStatement();
			rs = st.executeQuery("select * from exam");
			while (rs.next()){
				if(b-1 == rs.getInt(1)){
				for(int i = 0;i < options.length; ++i){
					if(options[i].isSelected()){
						op = options[i].getText().toString();
						if(op.equals(rs.getString("answer"))){
							sum += 10;
						}else{
							sum += 0;
						}
						System.out.println(sum);
						text3.setText(sum + "分");
					}
				}	
			}
		}	
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				rs.close();
				st.close();
				cn.close();
			}catch(Exception e){} 
		}
	}
	
@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getActionCommand().equals("下一题")&& b < 20){
			b++;
			questionCount.setText("第"+b+"题");
			check();
			Exam();
	    if(b == 20){
	    	JOptionPane.showMessageDialog(null, "你已做完所有试题！", "警告", JOptionPane.ERROR_MESSAGE);	
	    }
		}else if(arg0.getActionCommand().equals("上一题")&&b>1){
			//	b--;
			//	questionCount.setText("第"+b+"题");
			//	Exam();
				//check();
			}
		if(arg0.getActionCommand().equals("提交")){
			 try {
			     Class.forName("com.mysql.jdbc.Driver");//加载数据库驱动
			     String url="jdbc:mysql://localhost:3306/personal";//声明数据库test的url
			     String user="root";//数据库的用户名
			     String password="123456";//数据库的密码
			     //建立数据库连接，获得连接对象conn(抛出异常即可)
			     Connection conn=DriverManager.getConnection(url, user, password);
			     //生成一条mysql语句
			     str = text3.getText();//分数
			     str1 = text1.getText();//姓名
			     str2 = text4.getText();//班级
			     str3 = text5.getText();//学号
			     String sql="insert into mark(nummber,name,class,score) values(?,?,?,?)";
			      PreparedStatement ps=conn.prepareStatement(sql);//创建一个Statement对象
			      ps.setNString(1,str3);//为sql语句中第一个问号赋值
			      ps.setNString(2,str1);//为sql语句中第二个问号赋值
			      ps.setNString(3,str2);//为sql语句第三个问号赋值
			      ps.setNString(4,str);//为sql语句的第四个问号赋值
			      ps.executeUpdate();//执行sql语句
			      System.out.println("成功");
			     conn.close();
		 } catch (ClassNotFoundException e) {
			 // TODO Auto-generated catch block
			   e.printStackTrace();
	}//
	 catch (SQLException e) {
     // TODO Auto-generated catch block
  e.printStackTrace();
 }
		}
		}


	public static void main(String[] args){
		
	}
}

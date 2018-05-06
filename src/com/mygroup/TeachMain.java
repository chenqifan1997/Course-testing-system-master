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

public class TeachMain extends JFrame implements ActionListener {

	JTextField text1 = new JTextField();
	JLabel questionCount = new JLabel();
	JTextArea questionArea = new JTextArea();
	JTextArea chengjiArea = new JTextArea();
	JTextArea choseArea = new JTextArea();
	int b = 1;
	long a;
	String str = null;

	String s = "";
    String ss = GetS();
	public String GetS(){
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		try {
			File file = new File("E:/git/Course-testing-system-master/data/control.txt");
			fis = new FileInputStream(file);
			isr = new InputStreamReader(fis);
			br = new BufferedReader(isr);
			String linetext = null;
			while ((linetext = br.readLine()) != null) {
				s += linetext;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
				isr.close();
				fis.close();
			} catch (Exception e) {
			}
		}
        return s;
	}
	
	public TeachMain() {
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
		panel.add(BorderLayout.CENTER, createQuestionPane());
		panel.add(BorderLayout.SOUTH, createToolsPane());
		return panel;
	}

	private JPanel createToolsPane() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(new EmptyBorder(6, 6, 6, 6));// 提供间隙

		questionCount.setText("第" + b + "题");
		if(ss.equals("1")){
			  Exam();
			}
			else {
			  Exam1();
			}
		panel.add(BorderLayout.WEST, questionCount);
		panel.add(BorderLayout.CENTER, createBtnPane());
		return panel;
	}

	private JScrollPane createQuestionPane() {
		JScrollPane panel = new JScrollPane();
		panel.setBorder(new TitledBorder("题目"));
		questionArea.setLineWrap(true);// 设置允许换行
		questionArea.setEditable(true);// 设置考题不能修改
		panel.getViewport().add(questionArea);// 把题目放到不带有滚动条的部分
		// panel.setBorder(new EmptyBorder(6,6,6,6));//提供间隙
		return panel;
	}

	private JPanel createBtnPane() {
		JPanel panel = new JPanel(new FlowLayout());
		JButton ch = new JButton("选择科目");
		JButton t = new JButton("添加题目");
		JButton s = new JButton("删除题目");
		JButton c = new JButton("查询成绩");
		JButton pre = new JButton("上一题");
		JButton next = new JButton("下一题");
		pre.addActionListener(this);
		next.addActionListener(this);
		c.addActionListener(this);
		ch.addActionListener(this);
		t.addActionListener(this);
		s.addActionListener(this);
		panel.add(pre);
		panel.add(next);
		panel.add(t);
		panel.add(s);
		panel.add(ch);
		panel.add(c);
		return panel;
	}

	private void Addquestion() {
		Connection cn = null;
		try {
			cn = DataBase.getConnection("personal");
			// 生成一条mysql语句
			str = questionArea.getText();
			String[] fen = str.split("[\n]");
			String[] fen1 = fen[0].split("[.]");
			try {
				a = Integer.parseInt(fen1[0]);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
			String sql = "insert into exam(no,content,option1,option2,option3,option4,answer) values(?,?,?,?,?,?,?)";
			PreparedStatement ps = cn.prepareStatement(sql);// 创建一个Statement对象
			ps.setInt(1, (int) a);// 为sql语句中第一个问号赋值
			ps.setNString(2, fen1[1]);// 为sql语句中第二个问号赋值
			ps.setNString(3, fen[1]);// 为sql语句第三个问号赋值
			ps.setNString(4, fen[2]);// 为sql语句的第四个问号赋值
			ps.setNString(5, fen[3]);
			ps.setNString(6, fen[4]);
			ps.setNString(7, fen[5]);
			ps.executeUpdate();// 执行sql语句
			cn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	private void Addquestion1() {
		Connection cn = null;
		try {
			cn = DataBase.getConnection("personal");
			// 生成一条mysql语句
			str = questionArea.getText();
			String[] fen = str.split("[\n]");
			String[] fen1 = fen[0].split("[.]");
			try {
				a = Integer.parseInt(fen1[0]);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
			String sql = "insert into exam2(no,content,option1,option2,option3,option4,answer) values(?,?,?,?,?,?,?)";
			PreparedStatement ps = cn.prepareStatement(sql);// 创建一个Statement对象
			ps.setInt(1, (int) a);// 为sql语句中第一个问号赋值
			ps.setNString(2, fen1[1]);// 为sql语句中第二个问号赋值
			ps.setNString(3, fen[1]);// 为sql语句第三个问号赋值
			ps.setNString(4, fen[2]);// 为sql语句的第四个问号赋值
			ps.setNString(5, fen[3]);
			ps.setNString(6, fen[4]);
			ps.setNString(7, fen[5]);
			ps.executeUpdate();// 执行sql语句
			cn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	private void Exam() {
		Connection cn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			cn = DataBase.getConnection("personal");
			st = cn.createStatement();
			rs = st.executeQuery("select * from exam");
			while (rs.next()) {
				if (b == rs.getInt(1)) {
					questionArea.setText(rs.getString("no") + "." + rs.getString("content") + "\n"
							+ rs.getString("option1") + "\n" + rs.getString("option2") + "\n" + rs.getString("option3")
							+ "\n" + rs.getString("option4"));
				}
				if (b > rs.getInt(1)) {
					questionArea.setText("");
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

	private void Exam1() {
		Connection cn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			cn = DataBase.getConnection("personal");
			st = cn.createStatement();
			rs = st.executeQuery("select * from exam2");
			while (rs.next()) {
				if (b == rs.getInt(1)) {
					questionArea.setText(rs.getString("no") + "." + rs.getString("content") + "\n"
							+ rs.getString("option1") + "\n" + rs.getString("option2") + "\n" + rs.getString("option3")
							+ "\n" + rs.getString("option4"));
				}
				if (b > rs.getInt(1)) {
					questionArea.setText("");
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
	private void Deletequestion() {
		Connection cn = null;
		Statement st = null;
		try {
			cn = DataBase.getConnection("personal");
			String sql = "delete from exam where no = " + b;
			st = cn.createStatement();// 创建一个Statement对象
			st.executeUpdate(sql);// 执行sql语句
			st.close();
			cn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	private void Deletequestion1() {
		Connection cn = null;
		Statement st = null;
		try {
			cn = DataBase.getConnection("personal");
			String sql = "delete from exam2 where no = " + b;
			st = cn.createStatement();// 创建一个Statement对象
			st.executeUpdate(sql);// 执行sql语句
			st.close();
			cn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getActionCommand().equals("下一题")) {
			b++;
			questionCount.setText("第" + b + "题");
			if(ss.equals("1")){
			      Exam();
				}
				if(ss.equals("2")){
				 Exam1();
				}

		} else if (arg0.getActionCommand().equals("上一题") && b > 1) {
			b--;
			questionCount.setText("第" + b + "题");
			if(ss.equals("1")){
			      Exam();
				}
				if(ss.equals("2")){
				 Exam1();
				}
		}

		if (arg0.getActionCommand().equals("选择科目")) {
			this.setVisible(false);
			SubjectChose n = new SubjectChose();
			n.setVisible(true);
		} else if (arg0.getActionCommand().equals("查询成绩")) {
			this.setVisible(false);
			MarkCheck m = new MarkCheck();
			m.setVisible(true);
		}
		if (arg0.getActionCommand().equals("添加题目")) {
			if(ss.equals("1")){
				 Addquestion();
				}
				if(ss.equals("2")){
					Addquestion1();
				}		
		}

		if (arg0.getActionCommand().equals("删除题目")) {
			if(ss.equals("1")){
				  Deletequestion();
				  Exam();
				}
				if(ss.equals("2")){
					  Deletequestion1();
					  Exam1();
					}
		}
	}

	public static void main(String[] args) {

	}
}

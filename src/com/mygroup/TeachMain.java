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
		this.setTitle("�׿������߿���ϵͳ");
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
		panel.setBorder(new EmptyBorder(6, 6, 6, 6));// �ṩ��϶

		questionCount.setText("��" + b + "��");
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
		panel.setBorder(new TitledBorder("��Ŀ"));
		questionArea.setLineWrap(true);// ����������
		questionArea.setEditable(true);// ���ÿ��ⲻ���޸�
		panel.getViewport().add(questionArea);// ����Ŀ�ŵ������й������Ĳ���
		// panel.setBorder(new EmptyBorder(6,6,6,6));//�ṩ��϶
		return panel;
	}

	private JPanel createBtnPane() {
		JPanel panel = new JPanel(new FlowLayout());
		JButton ch = new JButton("ѡ���Ŀ");
		JButton t = new JButton("�����Ŀ");
		JButton s = new JButton("ɾ����Ŀ");
		JButton c = new JButton("��ѯ�ɼ�");
		JButton pre = new JButton("��һ��");
		JButton next = new JButton("��һ��");
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
			// ����һ��mysql���
			str = questionArea.getText();
			String[] fen = str.split("[\n]");
			String[] fen1 = fen[0].split("[.]");
			try {
				a = Integer.parseInt(fen1[0]);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
			String sql = "insert into exam(no,content,option1,option2,option3,option4,answer) values(?,?,?,?,?,?,?)";
			PreparedStatement ps = cn.prepareStatement(sql);// ����һ��Statement����
			ps.setInt(1, (int) a);// Ϊsql����е�һ���ʺŸ�ֵ
			ps.setNString(2, fen1[1]);// Ϊsql����еڶ����ʺŸ�ֵ
			ps.setNString(3, fen[1]);// Ϊsql���������ʺŸ�ֵ
			ps.setNString(4, fen[2]);// Ϊsql���ĵ��ĸ��ʺŸ�ֵ
			ps.setNString(5, fen[3]);
			ps.setNString(6, fen[4]);
			ps.setNString(7, fen[5]);
			ps.executeUpdate();// ִ��sql���
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
			// ����һ��mysql���
			str = questionArea.getText();
			String[] fen = str.split("[\n]");
			String[] fen1 = fen[0].split("[.]");
			try {
				a = Integer.parseInt(fen1[0]);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
			String sql = "insert into exam2(no,content,option1,option2,option3,option4,answer) values(?,?,?,?,?,?,?)";
			PreparedStatement ps = cn.prepareStatement(sql);// ����һ��Statement����
			ps.setInt(1, (int) a);// Ϊsql����е�һ���ʺŸ�ֵ
			ps.setNString(2, fen1[1]);// Ϊsql����еڶ����ʺŸ�ֵ
			ps.setNString(3, fen[1]);// Ϊsql���������ʺŸ�ֵ
			ps.setNString(4, fen[2]);// Ϊsql���ĵ��ĸ��ʺŸ�ֵ
			ps.setNString(5, fen[3]);
			ps.setNString(6, fen[4]);
			ps.setNString(7, fen[5]);
			ps.executeUpdate();// ִ��sql���
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
			st = cn.createStatement();// ����һ��Statement����
			st.executeUpdate(sql);// ִ��sql���
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
			st = cn.createStatement();// ����һ��Statement����
			st.executeUpdate(sql);// ִ��sql���
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
		if (arg0.getActionCommand().equals("��һ��")) {
			b++;
			questionCount.setText("��" + b + "��");
			if(ss.equals("1")){
			      Exam();
				}
				if(ss.equals("2")){
				 Exam1();
				}

		} else if (arg0.getActionCommand().equals("��һ��") && b > 1) {
			b--;
			questionCount.setText("��" + b + "��");
			if(ss.equals("1")){
			      Exam();
				}
				if(ss.equals("2")){
				 Exam1();
				}
		}

		if (arg0.getActionCommand().equals("ѡ���Ŀ")) {
			this.setVisible(false);
			SubjectChose n = new SubjectChose();
			n.setVisible(true);
		} else if (arg0.getActionCommand().equals("��ѯ�ɼ�")) {
			this.setVisible(false);
			MarkCheck m = new MarkCheck();
			m.setVisible(true);
		}
		if (arg0.getActionCommand().equals("�����Ŀ")) {
			if(ss.equals("1")){
				 Addquestion();
				}
				if(ss.equals("2")){
					Addquestion1();
				}		
		}

		if (arg0.getActionCommand().equals("ɾ����Ŀ")) {
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

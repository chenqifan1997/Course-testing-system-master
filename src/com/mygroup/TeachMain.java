package com.mygroup;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
import javax.swing.JComboBox;
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

public class TeachMain extends JFrame implements ActionListener,ItemListener {

	JTextField text1 = new JTextField();
	JTextField text3 = new JTextField();
	JLabel text5 = new JLabel();
	JLabel questionCount = new JLabel();
	JTextArea questionArea = new JTextArea();
	JTextArea chengjiArea = new JTextArea();
	JTextArea choseArea = new JTextArea();
	
	String kemu1="��������ԭ��",kemu2="�͵�����";
	
	String kemu3="�����ӵĿ�Ŀ";
		
	Vector strType = new Vector();
    
	JComboBox selectBox;
	
	
	int b = 1;
	long a;
	String c;
	String str = null;
	
	public TeachMain() {
		super();
		this.setTitle("�׿������߿���ϵͳ");
		this.setSize(750, 450);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setContentPane(createContentPane());
	}

	private JPanel createContentPane() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(new EmptyBorder(6, 6, 6, 6));
		JLabel label = new JLabel();
		label.setIcon(new ImageIcon("image/maintop.png"));
		panel.add(BorderLayout.NORTH, label);
		panel.add(BorderLayout.CENTER, createCenterPane());
		panel.add(BorderLayout.SOUTH, createToolsPane());
		return panel;
	}

	private JPanel createToolsPane() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(new EmptyBorder(6, 6, 6, 6));// �ṩ��϶

		questionCount.setText("��" + b + "��");
		Exam();
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
		return panel;
	}
	
	private JPanel createCenterPane() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(BorderLayout.NORTH, createExaminfoPane());
		panel.add(BorderLayout.CENTER, createQuestionPane());
		return panel;
	}
	private JPanel createExaminfoPane() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 6));
		JLabel name = new JLabel("������");
		Name();
		text1.setEditable(false);
		panel.add(name);
		panel.add(text1);
		JLabel time = new JLabel("��ʦ��ţ�");
		JTextField text2 = new JTextField();
		text2.setEditable(false);
		text2.setText(text5.getText());
		panel.add(time);
		panel.add(text2);
		JLabel score = new JLabel("�����ӵĿ�ĿΪ��");
		//text3.getText().toString();
		panel.add(score);
		panel.add(text3);
		return panel;
	}

	private JPanel createBtnPane() {

		JPanel panel = new JPanel(new FlowLayout());
		//JButton ch = new JButton("ѡ���Ŀ");
		JButton t = new JButton("�����Ŀ");
		JButton s = new JButton("ɾ����Ŀ");
		JButton c = new JButton("��ѯ�ɼ�");
		JButton pre = new JButton("��һ��");
		JButton next = new JButton("��һ��");
		JButton q = new JButton("ȷ���޸�");
		pre.addActionListener(this);
		next.addActionListener(this);
		c.addActionListener(this);
		//ch.addActionListener(this);
		t.addActionListener(this);
		s.addActionListener(this);
		q.addActionListener(this);
		
	    strType.addElement(kemu1);
	    strType.addElement(kemu2);
	    strType.addElement(kemu3);
	    selectBox = new JComboBox(strType);
	    selectBox.setSelectedIndex(0);
		selectBox.setVisible(true);
		selectBox.addItemListener(this);
		
		panel.add(selectBox);
		panel.add(pre);
		panel.add(next);
		panel.add(t);
		panel.add(s);
		panel.add(c);
		panel.add(q);
		return panel;
	}
	
	
	private void Name() {
		Connection cn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
            UseFile th = new UseFile();
			cn = DataBase.getConnection("personal");
			st = cn.createStatement();
			rs = st.executeQuery("select * from tmessage");
			while (rs.next()) {
				if (th.ReadteachFile().equals(rs.getString("id"))) {
					text1.setText(rs.getString("name"));
					//text4.setText(rs.getString("major"));
					text5.setText(rs.getString("number"));
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
	
	private void Addquestion() {
		String sAdd;
		ChoseExam se = new ChoseExam();
		sAdd=se.getAddExam();
		Connection cn = null;
		try {
			cn = DataBase.getConnection("personal");
			str = questionArea.getText();
			String[] fen = str.split("[\n]");
			String[] fen1 = fen[0].split("[.]");
			try {
				a = Integer.parseInt(fen1[0]);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
			String sql = sAdd;
			PreparedStatement ps = cn.prepareStatement(sql);
			ps.setInt(1, (int) a);
			ps.setNString(2, fen1[1]);
			ps.setNString(3, fen[1]);
			ps.setNString(4, fen[2]);
			ps.setNString(5, fen[3]);
			ps.setNString(6, fen[4]);
			ps.setNString(7, fen[5]);
			ps.executeUpdate();
			cn.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	private void Exam() {
		String sexam;
		ChoseExam se = new ChoseExam();
		sexam =se.getExam();
		Connection cn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			cn = DataBase.getConnection("personal");
			st = cn.createStatement();
			rs = st.executeQuery(sexam.toString());
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
		String sdele;
		ChoseExam se = new ChoseExam();
		sdele=se.getDeleteExam();
		Connection cn = null;
		Statement st = null;
		try {
			cn = DataBase.getConnection("personal");
			String sql = sdele.toString() + b;
			st = cn.createStatement();
			st.executeUpdate(sql);
			st.close();
			cn.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getActionCommand().equals("��һ��")) {
			b++;
			questionCount.setText("��" + b + "��");
			Exam();

		} else if (arg0.getActionCommand().equals("��һ��") && b > 1) {
			b--;
			questionCount.setText("��" + b + "��");	
			Exam();
		}

	    if (arg0.getActionCommand().equals("��ѯ�ɼ�")) {
			this.setVisible(false);
			MarkCheck m = new MarkCheck();
			m.setVisible(true);
		}
		if (arg0.getActionCommand().equals("�����Ŀ")) {
			 Addquestion();
			 Exam();
		}

		if (arg0.getActionCommand().equals("ɾ����Ŀ")) {
		    Deletequestion();
		    Exam();
		}
		if(arg0.getActionCommand().equals("ȷ���޸�")){
		  
				kemu3 = text3.getText().toString();
				System.out.println(kemu3);
				UseFile kemu = new UseFile();
				kemu.writekemuFile(text3.getText());
				
				if(selectBox.getSelectedIndex()==-1||selectBox.getSelectedIndex()==0||selectBox.getSelectedIndex()==1){
					JOptionPane.showMessageDialog(null, "���ܸ��ģ�", "����", JOptionPane.ERROR_MESSAGE);
				}else{
					strType.removeElement(selectBox.getSelectedItem().toString());
					strType.addElement(kemu3);
					selectBox.setSelectedIndex(2);
				
				}

				revalidate();
				repaint();
				selectBox.setVisible(true);
				selectBox.addItemListener(this);
				
				
				
			
		}
	}
	public static void main(String[] args) {

	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub
		
		if(arg0.getStateChange() == ItemEvent.SELECTED){
			if(arg0.getSource() == selectBox){
				int index = selectBox.getSelectedIndex();
				if(index == 0){
					c = "1";
					UseFile d = new UseFile();
					d.writeFile(c);
					Exam();
				}else if(index == 1){
					c = "2";
					UseFile d = new UseFile();
					d.writeFile(c);
					Exam();
				}else if(index == 2){
					c = "3";
					UseFile d = new UseFile();
					d.writeFile(c);
					Exam();
				    System.out.println("�ɹ�");
				}
			}
		}
	}
}

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

public class Main extends JFrame implements ActionListener {

	JLabel text1 = new JLabel();
	JLabel sub = new JLabel();
	JLabel text3 = new JLabel();
	JLabel text4 = new JLabel();
	JLabel text5 = new JLabel();
	JLabel questionCount = new JLabel();
	JTextArea questionArea = new JTextArea();
	Option[] options = new Option[4];
	ButtonGroup bg = new ButtonGroup();
	static int a = 180;
	int b = 1;
	int sum = 0;
	String str, str1, str2, str3 = null;

	String s = "";
	String ss = GetS();

	public String GetS() {
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

	public Main() {
		super();
		this.setTitle("�׿������߿���ϵͳ");
		this.setSize(615, 380);
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
		panel.add(BorderLayout.CENTER, createCenterPane());
		panel.add(BorderLayout.SOUTH, createToolsPane());
		return panel;
	}

	private JPanel createCenterPane() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(BorderLayout.NORTH, createExaminfoPane());
		panel.add(BorderLayout.CENTER, createQuestionPane());
		panel.add(BorderLayout.SOUTH, createOptionPane());
		return panel;
	}

	private JPanel createExaminfoPane() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 8));
		JLabel name = new JLabel("������");
		Name();
		panel.add(name);
		panel.add(text1);
		JLabel time = new JLabel("ʱ�䣺");
		JLabel text2 = new JLabel();
		Date day = new Date();
		SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
		text2.setText(df.format(day));
		panel.add(time);
		panel.add(text2);
		JLabel score = new JLabel("������");
		text3.setText(0 + "��");
		panel.add(score);
		panel.add(text3);
		JLabel subject = new JLabel("���Կ�Ŀ��");
		if (ss.equals("1")) {
			sub.setText("���ԭ��");
		}
		if (ss.equals("2")) {
			sub.setText("�͵���ѧ");
		}
		panel.add(subject);
		panel.add(sub);
		return panel;
	}

	private JPanel createToolsPane() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(new EmptyBorder(6, 6, 6, 6));// �ṩ��϶

		questionCount.setText("��" + b + "��");

		if (ss.equals("1")) {
			Exam();
		}
		if (ss.equals("2")) {
			Exam1();
		}

		JLabel time = new JLabel();
		new Thread() {
			@SuppressWarnings("deprecation")
			@Override
			public void run() {
				// TODO Auto-generated method stub

				while (a > 0) {
					try {
						time.setText(a + "��");
						Thread.sleep(1000);
						a--;
					} catch (Exception e) {
						e.printStackTrace();
					}
					if (a == 0) {
						time.setText("0��");
						JOptionPane.showMessageDialog(null, "ʱ�䵽���뽻��", "����", JOptionPane.ERROR_MESSAGE);
						this.stop();
						this.destroy();
					}
				}
			}
		}.start();

		panel.add(BorderLayout.WEST, questionCount);
		panel.add(BorderLayout.EAST, time);
		panel.add(BorderLayout.CENTER, createBtnPane());
		return panel;
	}

	private JScrollPane createQuestionPane() {
		JScrollPane panel = new JScrollPane();
		panel.setBorder(new TitledBorder("��Ŀ"));
		questionArea.setLineWrap(true);// ����������
		questionArea.setEditable(false);// ���ÿ��ⲻ���޸�
		panel.getViewport().add(questionArea);// ����Ŀ�ŵ������й������Ĳ���
		return panel;
	}

	private JPanel createBtnPane() {
		JPanel panel = new JPanel(new FlowLayout());
		JButton pre = new JButton("��һ��");
		JButton next = new JButton("��һ��");
		JButton send = new JButton("�ύ");
		pre.addActionListener(this);
		next.addActionListener(this);
		send.addActionListener(this);
		panel.add(pre);
		panel.add(next);
		panel.add(send);
		return panel;
	}

	class Option extends JRadioButton {
		int value;

		public Option(int val, String txt) {
			super(txt);// ��ʾ���ø����вι�����
			this.value = val;
		}
	}

	private JPanel createOptionPane() {
		JPanel panel = new JPanel();
		Option a = new Option(0, "A");
		Option b = new Option(1, "B");
		Option c = new Option(2, "C");
		Option d = new Option(3, "D");
		// Option[] opt = new Option[4];
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

	private void Name() {
		String s = "";
		Connection cn = null;
		Statement st = null;
		ResultSet rs = null;
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		try {
			File file = new File("E:/git/Course-testing-system-master/data/data.txt");
			fis = new FileInputStream(file);
			isr = new InputStreamReader(fis);
			br = new BufferedReader(isr);
			String linetext = null;
			while ((linetext = br.readLine()) != null) {
				s += linetext;
			}

			cn = DataBase.getConnection("personal");
			st = cn.createStatement();
			rs = st.executeQuery("select * from message");
			while (rs.next()) {
				if (s.equals(rs.getString("id"))) {
					text1.setText(rs.getString("name"));
					text4.setText(rs.getString("class"));
					text5.setText(rs.getString("number"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
				isr.close();
				fis.close();
				rs.close();
				st.close();
				cn.close();
			} catch (Exception e) {
			}
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

	private void check() {
		String op = null;
		Connection cn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			cn = DataBase.getConnection("personal");
			st = cn.createStatement();
			rs = st.executeQuery("select * from exam");
			while (rs.next()) {
				if (b - 1 == rs.getInt(1)) {
					for (int i = 0; i < options.length; ++i) {
						if (options[i].isSelected()) {
							op = options[i].getText().toString();
							if (op.equals(rs.getString("answer"))) {
								sum += 10;
							} else {
								sum += 0;
							}
							System.out.println(sum);
							text3.setText(sum + "��");
						}
					}
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

	private void check1() {
		String op = null;
		Connection cn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			cn = DataBase.getConnection("personal");
			st = cn.createStatement();
			rs = st.executeQuery("select * from exam2");
			while (rs.next()) {
				if (b - 1 == rs.getInt(1)) {
					for (int i = 0; i < options.length; ++i) {
						if (options[i].isSelected()) {
							op = options[i].getText().toString();
							if (op.equals(rs.getString("answer"))) {
								sum += 10;
							} else {
								sum += 0;
							}
							System.out.println(sum);
							text3.setText(sum + "��");
						}
					}
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

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getActionCommand().equals("��һ��") && b < 20) {
			b++;
			questionCount.setText("��" + b + "��");
			if (ss.equals("1")) {
				check();
				Exam();
			}
			if (ss.equals("2")) {
				check1();
				Exam1();
			}
			if (b == 20) {
				JOptionPane.showMessageDialog(null, "���������������⣡", "����", JOptionPane.ERROR_MESSAGE);
			}
			bg.clearSelection();
		}
		if (arg0.getActionCommand().equals("�ύ")) {
			Connection cn = null;
			try {
				cn = DataBase.getConnection("personal");
				str = text3.getText();// ����
				str1 = text1.getText();// ����
				str2 = text4.getText();// �༶
				str3 = text5.getText();// ѧ��
				String sql = "insert into mark(nummber,name,class,score) values(?,?,?,?)";
				PreparedStatement ps = cn.prepareStatement(sql);
				ps.setNString(1, str3);
				ps.setNString(2, str1);
				ps.setNString(3, str2);
				ps.setNString(4, str);
				ps.executeUpdate();
				System.out.println("�ɹ�");
				cn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {

	}
}

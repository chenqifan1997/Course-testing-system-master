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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Wrapper;
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

public class MarkCheck extends JFrame implements ActionListener {

	JTextArea chengjiArea = new JTextArea();
	int b = 0;
	public static String str = null; // ��StringBufferת�����ַ���
	public static StringBuffer sb = new StringBuffer(); // StringBuffer�����ַ�������ɾ�Ĳ����

	public MarkCheck() {
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
		panel.add(BorderLayout.CENTER, createchengjiPane());
		panel.add(BorderLayout.SOUTH, createToolsPane());
		return panel;
	}

	private JPanel createToolsPane() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(new EmptyBorder(6, 6, 6, 6));// �ṩ��϶

		ShowMark();
		panel.add(BorderLayout.CENTER, createBtnPane());
		return panel;
	}

	private JScrollPane createchengjiPane() {
		JScrollPane panel = new JScrollPane();
		panel.setBorder(new TitledBorder("ѧ���ɼ�"));
		chengjiArea.setLineWrap(true);// ����������
		chengjiArea.setEditable(false);// ���ÿ��ⲻ���޸�
		panel.getViewport().add(chengjiArea);
		return panel;
	}

	private JPanel createBtnPane() {
		JPanel panel = new JPanel(new FlowLayout());
		JButton ch = new JButton("����");
		JButton ch1 = new JButton("��һ��");
		ch.addActionListener(this);
		ch1.addActionListener(this);
		panel.add(ch);
		panel.add(ch1);
		return panel;
	}

	private void ShowMark() {
		// String op = null;
		Connection cn = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			cn = DataBase.getConnection("personal");
			st = cn.createStatement();
			rs = st.executeQuery("select * from mark");

			while (rs.next()) {

				/*
				 * chengjiArea.setText("������"+rs.getString("name") + "    �༶��" +
				 * rs.getString("class")+"��" + "    ѧ�ţ�" +
				 * rs.getString("nummber") + "    ������" + rs.getString("score"));
				 */

				sb.append(rs.getString(1)); // ����ÿһ�е�����
				sb.append("*"); // ��ÿ�����ݺ�������ǣ��������������
				sb.append(rs.getString(2));
				sb.append("*");
				sb.append(rs.getString(3));
				sb.append("*");
				sb.append(rs.getString(4));
				sb.append("%"); // ��ÿ�����ݺ�������ǣ����ڲ��

			}
			str = sb.toString(); // ��������StringBuffer����ת����String����
			// String datas;
			// ����������ָ���ַ��ָ�����飬ÿ������Ϊ�����һ��
			String[] params = str.split("%");

			// ��ÿ�������ٲ�֣���param���ݱ������һ�����ݵ�ÿһ������
			for (int i = 0; i < params.length; i++) {
				String[] param = params[b].split("\\*");
				if (b == i) {
					chengjiArea.setText("������" + param[1] + "    �༶��" + param[2] + "��" + "    ѧ�ţ�" + param[0] + "    ������"
							+ param[3]);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			chengjiArea.setText("δ���κο����ɼ���Ϣ������");
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

		if (arg0.getActionCommand().equals("����")) {
			this.setVisible(false);
			TeachMain m = new TeachMain();
			m.setVisible(true);
			Connection cn = null;
			Statement st = null;

			try {
				cn = DataBase.getConnection("personal");
				// ����һ��mysql���
				String sql = "delete from mark ";
				st = cn.createStatement();// ����һ��Statement����
				st.executeUpdate(sql);// ִ��sql���
				st.close();
				cn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		if (arg0.getActionCommand().equals("��һ��")) {
			b++;
			ShowMark();
		}
	}

	public static void main(String[] args) {

	}
}

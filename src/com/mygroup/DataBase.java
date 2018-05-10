package com.mygroup;

import java.sql.*;

public class DataBase {
	public static Connection getConnection(String dbName) {
		Connection cn = null;
		try {
			String driver = "org.gjt.mm.mysql.Driver";
			String url = "jdbc:mysql://127.0.0.1:3306/" + dbName;
			String user = "root";
			String pass = "123456";
			Class.forName(driver);
			cn = DriverManager.getConnection(url, user, pass);
		} catch (Exception ex) {
		}
		return cn;
	}
}

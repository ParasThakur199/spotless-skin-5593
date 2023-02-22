package com.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DButil {
	public static Connection ProvideConnection() {
		ResourceBundle rb = ResourceBundle.getBundle("credentials");
		String url = rb.getString("url");
		String username = rb.getString("username");
		String password = rb.getString("password");
		
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			conn = DriverManager.getConnection(url,username,password);
			
		}catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return conn;

	}
}

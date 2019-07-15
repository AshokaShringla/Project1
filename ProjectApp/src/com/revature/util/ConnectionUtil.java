package com.revature.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	
	
	/**
	 * This should be used when deploying in a Tomcat server
	 */
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
		}
	}
	public static Connection getConnection() throws SQLException {
		String url = "jdbc:oracle:thin:@myrevaturedb.cqyaeos4pu09.us-east-2.rds.amazonaws.com:1521:ORCL";
		String username = "ERS_DB";
		String password ="p4ssw0rd";
		
		return DriverManager.getConnection(url, username, password);
	}
	
	public static void main(String[] args) {
		try {
			getConnection();
			System.out.println("Connection successful");
		} catch (SQLException e) {
			System.out.println("Connection failed");
			
		}
	}
}

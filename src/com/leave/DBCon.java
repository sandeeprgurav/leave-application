package com.leave;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBCon {
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		Connection con = null;
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(
				"jdbc:mysql://ols-admin.czajs616aco3.us-east-2.rds.amazonaws.com:3306/leaveApp", "root", "Jan2017!");
		return con;
	}
}
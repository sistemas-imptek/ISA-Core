package com.isacore.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ReportConnection {

	public static Connection getConectionISA() {
		try {
			return DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=isa;user=sa;password=sqlserver");
		} catch (SQLException e) {
			return null;
		}
	}
	
}

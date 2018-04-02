package com.isacore.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.pentaho.reporting.engine.classic.core.modules.misc.datafactory.sql.DriverConnectionProvider;

public class ReportConnection {

	public static Connection getConectionISA() {
		try {
			return DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=isa;user=sa;password=sqlserver");
		} catch (SQLException e) {
			return null;
		}
	}
	
	public static DriverConnectionProvider ConnectionPentaho() {
		
		final DriverConnectionProvider sampleDriverConnectionProvider = new DriverConnectionProvider();
        sampleDriverConnectionProvider.setDriver("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        sampleDriverConnectionProvider.setUrl("jdbc:sqlserver://localhost:1433;databaseName=isa;user=sa;password=sqlserver");
        
        return sampleDriverConnectionProvider;
	}
	
}

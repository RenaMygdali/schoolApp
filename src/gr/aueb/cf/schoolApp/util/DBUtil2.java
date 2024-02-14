package gr.aueb.cf.schoolApp.util;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;

public class DBUtil2 {
	
	private static BasicDataSource ds = new BasicDataSource();
	private static Connection conn;
	
	private DBUtil2() {
		
	}
	
	static {
		ds.setUrl("jdbc:mysql://localhost:3306/SchoolApp?serverTimeZone=UTC");
		ds.setUsername("schoolAppAdmin");
		ds.setPassword("12345");
		ds.setInitialSize(8);		// ο αριθμός των αρχικών connections
		ds.setMaxTotal(32);
		ds.setMinIdle(8);			// min αδρανή connections
		ds.setMaxIdle(10);			// max αδρανή connections
		ds.setMaxOpenPreparedStatements(100);
	}
	
	public static Connection getConnection() throws SQLException {
		conn = ds.getConnection();
		return conn;
	}
	
	public static void closeConnection() {
		try {
			if (conn != null) conn.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
}

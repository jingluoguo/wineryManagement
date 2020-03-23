package com.dw.tools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JdbcUtil {
	private static DataSource dataSource = null;

	static {
		dataSource = new ComboPooledDataSource("c3p0-config");
	}

	public static Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}

	public static void releaseConnection(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void release(ResultSet rs, PreparedStatement statement,Connection con) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			if (statement != null) {
				statement.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			if(con != null){
				con.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void main(String[] args) throws SQLException {
		System.out.println(dataSource.getConnection().toString());
	}
}

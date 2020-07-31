package com.ntels.ccbs.batch.up.common.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

public class UpBaseService {

	@Autowired
	DataSource dataSource;
	
	protected Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}
	
	protected void closeConnection(Connection conn) {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			
		}
	}
	
}

package com.liu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Component
public class JDBCDataSource {
	
	private String driverclass;
	@Value("#{jdbcProperties.url}")
	private String url;
	@Value("#{jdbcProperties.user}")
	private String user;
	@Value("#{jdbcProperties.pwd}")
	private String pwd;
	
	@Value("#{jdbcProperties.driverclass}")
	public void setDriverclass(String driverclass) {
		try {
			Class.forName(driverclass);
			this.driverclass=driverclass;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection()
	{
		Connection conn=null;
		try {
			conn=DriverManager.getConnection(url,user,pwd);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	public void close(Connection conn)
	{
		if(conn!=null)
		{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	
	
}

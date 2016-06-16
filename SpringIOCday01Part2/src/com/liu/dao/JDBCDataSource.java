package com.liu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCDataSource {
	private String driver;
	private String url;
	private String user;
	private String password;
	
	public Connection getConnection() throws SQLException
	{
		Connection conn=DriverManager.getConnection(url,user,password);
		return conn;
	}
	public void close(Connection conn) throws SQLException
	{
		if(null!=conn)
		{
			conn.close();
		}
	}
	
	
	
	
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		try {
			Class.forName(driver);
			this.driver=driver;
		} catch (ClassNotFoundException e) {
			throw new RuntimeException();
		}
		
		
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}

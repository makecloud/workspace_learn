package com.liu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.liu.entity.User;


public class MysqlUserDao implements UserDdao {
	
	@Override
	public User findByName(String name) {
		String sql	="select id,name,pwd,phone from users where name=?";
		Connection conn=null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, name);
			ResultSet rs=pstmt.executeQuery();
			User user=null;
			while(rs.next())
			{
				user=new User();
				user.setName(rs.getString("name"));
				user.setId(rs.getInt("id"));
				user.setPwd(rs.getString("pwd"));
				user.setPhone(rs.getString("phone"));
				
			}
			rs.close();
			pstmt.close();
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		finally
		{
			try {
				dataSource.close(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	private JDBCDataSource dataSource;
	//创建MysqlUserDao必须依赖于JDBCDataSource的示例
	public MysqlUserDao(JDBCDataSource ds) {
		dataSource=ds;
	}
}

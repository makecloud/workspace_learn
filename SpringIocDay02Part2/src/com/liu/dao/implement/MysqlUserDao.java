package com.liu.dao.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liu.dao.JDBCDataSource;
import com.liu.dao.UserDao;
import com.liu.entity.User;

@Service
public class MysqlUserDao implements UserDao {
	
	@Autowired
	private JDBCDataSource dataSource;
	
	
	@Override
	public User findByName(String name) {
		
		String sql="select * from Users where name=?";
		Connection conn=dataSource.getConnection();
		try {
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, name);
			ResultSet rs=pstmt.executeQuery();
			User user=null;
			while(rs.next())
			{
				user=new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setPwd(rs.getString("pwd"));
				user.setPhone(rs.getString("phone"));
			}
			pstmt.close();
			rs.close();
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			dataSource.close(conn);
		}
		
		return null;
	
	}

	@Override
	public User find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User delete(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub

	}

}

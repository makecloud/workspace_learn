package com.liuyihui.lrn.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
/**
 * @author yihui
 * 
 * AdminMapper
 * ʵ�֣�RowMapper�ӿ�
 * 
 * ӳ����jdbcTemplate��ѯ���صĽ��
 *
 */
public class JdbcAdminMapper implements RowMapper<Admin> {
	
	
	public Admin mapRow(ResultSet rs, int arg1) throws SQLException {
		Admin admin=new Admin();
		admin.setUserId(rs.getInt("userId"));
		admin.setUserName(rs.getString("userName"));
		admin.setUserPw(rs.getString("userPw"));
		return admin;
	}

	
}

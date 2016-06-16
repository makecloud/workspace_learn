package com.liuyihui.lrn.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.liuyihui.lrn.entity.Admin;
/**
 * 
 * @author yihui
 *
 *  Dao类
 * 实现：AdminDao
 * 
 * 原理：注入jdbcTemplate，调用jdbcTemplate的增删改方法。
 */
public class JdbcAdminDao2 implements AdminDao{
	private JdbcTemplate jdbctemplate;
	
	public void save(Admin admin) {
		StringBuilder sql=new StringBuilder();
		sql.append("insert into t_admin values(?,?,?) ");
		Object[] params={
				admin.getUserId(),
				admin.getUserName(),
				admin.getUserPw()
		};
		jdbctemplate.update(sql.toString(), params);
	}

	public void update(Admin admin) {
		StringBuilder sql= new StringBuilder();
		sql.append("update t_admin t set t.userName=? ,t.userPw=? where t.userId=? ");
		Object[] params={
				admin.getUserName(),
				admin.getUserPw(),
				admin.getUserId()
		};
		jdbctemplate.update(sql.toString(),params);
	}

	public Admin findByNo(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	public List<Admin> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public JdbcTemplate getJdbctemplate() {
		return jdbctemplate;
	}

	public void setJdbctemplate(JdbcTemplate jdbctemplate) {
		this.jdbctemplate = jdbctemplate;
	}

	
}

package com.liuyihui.lrn.dao;

import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.liuyihui.lrn.entity.Admin;
import com.liuyihui.lrn.entity.JdbcAdminMapper;

/**
 * @author yihui
 * 
 * Dao类
 * 继承：JdbcDaoSupport
 * 实现：AdminDao
 * 
 * 原理：用JdbcDaoSupport里的jdbcTemplate，调用jdbcTemplate的增删改方法。
 *
 */
public class JdbcAdminDao extends JdbcDaoSupport implements AdminDao {

	public void save(Admin admin) {

	}

	public void update(Admin admin) {

	}

	public Admin findByNo(int id) {
		return null;
	}

	public List<Admin> findAll() {
		String sql="select * from t_admin";
		RowMapper<Admin> rm= new JdbcAdminMapper();
		List<Admin> adminList= getJdbcTemplate().query(sql, rm);
		return adminList;
	}

	public void delete(int id) {
		StringBuilder sql=new StringBuilder();
		sql.append("delete from t_admin a where a.userId = ? ");
		Object[] params={id};
		this.getJdbcTemplate().update(sql.toString(), params);
	}

}

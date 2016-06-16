package com.liuyihui.lrn.dao;

import java.util.List;

import com.liuyihui.lrn.entity.Admin;

/**
 * AdminDao 接口
 * 包含增删改查方法定义。
 * 
 * 已知实现类：JdbcAdminDao.java,JdbcAdminDao2.java
 * @author yihui
 * 
 */
public interface AdminDao {
	public void save(Admin admin);
	public void update(Admin admin);
	public Admin findByNo(int id);
	public void delete(int id);
	public List<Admin> findAll();
}

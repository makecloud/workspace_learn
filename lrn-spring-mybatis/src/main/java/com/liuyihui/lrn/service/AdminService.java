package com.liuyihui.lrn.service;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.liuyihui.lrn.dao.JdbcAdminDao;
import com.liuyihui.lrn.dao.JdbcAdminDao2;
import com.liuyihui.lrn.entity.Admin;

@Service("adminService")
public class AdminService {
	
	@Autowired
	@Qualifier("jdbcAdminDao1")
	private JdbcAdminDao adminDao;
	
	/**
	 * 鏌ヨ鎵�湁admin
	 * @return List<Admin>
	 */
	public List<Admin> getAllAdmin(){
		return adminDao.findAll();
	}

}

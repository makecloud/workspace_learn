package com.liuyihui.struts2.dao.interf;

import java.util.List;

import com.liuyihui.struts2.dao.UserDao;

public interface IUserDao<T> {

	public List<T> findAll();
	

}

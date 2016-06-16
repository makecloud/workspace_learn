package com.liuyihui.struts2.action;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.liuyihui.struts2.dao.UserDao;
import com.liuyihui.struts2.dao.interf.IUserDao;
import com.liuyihui.struts2.entity.RegUser;
import com.opensymphony.xwork2.ActionSupport;

public class RegisterAction extends ActionSupport {
	
	private RegUser regUser;
	
	@Autowired
	private IUserDao userDao;
	
	public RegisterAction() {
		
	}
	
	@Override
	public String doDefault() throws Exception {
		//regUser.setRegDate(new Date());
		
		
		userDao.findAll();
		return SUCCESS;
	}
}

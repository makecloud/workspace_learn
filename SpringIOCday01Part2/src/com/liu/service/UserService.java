package com.liu.service;

import com.liu.dao.UserDdao;
import com.liu.entity.User;

public class UserService {
	
	private UserDdao userDao;
	

	public UserDdao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDdao userDao) {
		this.userDao = userDao;
	}
	/**
	 * µÇÂ¼¹¦ÄÜ´úÂë
	 */
	public User Login(String name,String pwd)
	{
		User user=userDao.findByName(name);
		if(user.getPwd().equals(pwd))
		{
			return user;
		}
		return null;
		
	}
}

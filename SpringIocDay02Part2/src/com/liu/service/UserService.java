package com.liu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liu.dao.UserDao;
import com.liu.entity.User;

@Service	//采用注解默认的Bean Id 是首字母小写即userService
public class UserService {
	@Autowired
	private UserDao userDao;
	
	
	//登录功能代码
	public User login(String name,String pwd)
	{
		User user=userDao.findByName(name);
		if(user.getPwd().equals(pwd))
		{
			return user;
		}
		return null;
	}
}

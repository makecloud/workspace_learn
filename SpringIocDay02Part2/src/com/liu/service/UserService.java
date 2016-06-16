package com.liu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liu.dao.UserDao;
import com.liu.entity.User;

@Service	//����ע��Ĭ�ϵ�Bean Id ������ĸСд��userService
public class UserService {
	@Autowired
	private UserDao userDao;
	
	
	//��¼���ܴ���
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

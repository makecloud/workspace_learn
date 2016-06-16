package com.liu.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.liu.dao.JDBCDataSource;
import com.liu.entity.User;
import com.liu.service.UserService;

public class TestCase {
	@Test
	public void myTest()
	{
		String conf="applicationContext.xml";
		ApplicationContext ac=new ClassPathXmlApplicationContext(conf);
		UserService us=ac.getBean("userService",UserService.class);
		System.out.println(us);
		User u=us.login("Tom", "123");
		System.out.println(u);
	}
}	

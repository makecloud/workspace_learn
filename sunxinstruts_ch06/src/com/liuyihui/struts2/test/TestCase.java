package com.liuyihui.struts2.test;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.liuyihui.struts2.dao.UserDao;
import com.liuyihui.struts2.dao.interf.IUserDao;

public class TestCase {
	
	@Test
	public void test()
	{
		
		UserDao ud=new UserDao();
		
		String conf="applicationContext.xml";
		ApplicationContext ac= new ClassPathXmlApplicationContext(conf);
		ud.setSf((SessionFactory) ac.getBean("sessionFactoty"));
		ud.findAll();
		
	}
}	

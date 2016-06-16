package com.liu.test;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.liu.dao.JDBCDataSource;
import com.liu.dao.MysqlUserDao;
import com.liu.dao.UserDdao;
import com.liu.entity.User;
import com.liu.service.UserService;

public class testCase {
	
	@Test
	public void testJDBCDataSource() throws SQLException
	{
		String conf ="applicationContext.xml";
		ApplicationContext ac=new ClassPathXmlApplicationContext(conf);
		System.out.println(ac);
		JDBCDataSource ds=ac.getBean("dataSource", JDBCDataSource.class);
		Connection conn=ds.getConnection();
		System.out.println(conn);
	}
	@Test
	public void testMysqlUserDao()
	{
		String conf="applicationContext.xml";
		ApplicationContext ac=new ClassPathXmlApplicationContext(conf);
		UserDdao ud=ac.getBean("userDao", MysqlUserDao.class);
		User user=ud.findByName("Jerry");
		System.out.println(user);
	}
	@Test
	public void testUserService()
	{//自动属性注入测试
		String conf="applicationContext.xml";
		ApplicationContext ac=new ClassPathXmlApplicationContext(conf);
		UserService us=ac.getBean("userService", UserService.class);
		User Tom=us.Login("Tom", "123");
		System.out.println(Tom);
	}
}

package com.liuyihui.test;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.liuyihui.lrn.dao.AdminDao;
import com.liuyihui.lrn.entity.Admin;

public class TestSpringJdbc2 {
	private static Logger logger=Logger.getGlobal();
	public static void main(String[] args) {
		String confLocation="application-context.xml";
		ClassPathXmlApplicationContext ac=new ClassPathXmlApplicationContext(confLocation);
		AdminDao dao=ac.getBean("jdbcAdminDao2", AdminDao.class);
		Admin admin=new Admin();
		admin.setUserId(1);
		admin.setUserName("liu");
		admin.setUserPw("123");
		dao.update(admin);
		logger.log(Level.WARNING, "±£´æÍê³É");
	}
}	

package com.liuyihui.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.liuyihui.lrn.dao.AdminDao;
import com.liuyihui.lrn.entity.Admin;

public class TestSpringJdbc {
	public static void main(String[] args) {
		 String confLocation="application-context.xml";
		 ApplicationContext ac= new ClassPathXmlApplicationContext(confLocation);
		 AdminDao adminDao=ac.getBean("jdbcAdminDao1",AdminDao.class);
		 List<Admin> list=adminDao.findAll();
		 for(Admin a:list){
			 System.out.println(a.getUserId()+":"+a.getUserName()+":"+a.getUserPw());
		 }
	}
}

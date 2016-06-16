package com.liuyihui.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.liuyihui.lrn.controller.DemoController;
import com.liuyihui.lrn.dao.JdbcAdminDao;
import com.liuyihui.lrn.service.AdminService;

public class TestService {
	public static void main(String[] args) {
		String confLocation="application-context.xml";
		ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext(confLocation);
		AdminService as=ac.getBean("adminService", AdminService.class);
		JdbcAdminDao dc = ac.getBean("jdbcAdminDao1", JdbcAdminDao.class);
		if(dc==null){
			System.out.println("dc is null !");
		}else{
			System.out.println(dc.getClass().getName());
		}
	}
}

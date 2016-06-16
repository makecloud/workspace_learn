package com.liuyihui.test.mybatis;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.liuyihui.lrn.entity.Admin;
import com.liuyihui.lrn.mapper.interf.AdminMapper;

public class TestAdminMapper {
	
	private ClassPathXmlApplicationContext initContext(){
		//定义spring上下文配置文件位置
		String acLocation="application-context.xml";
		//获取spring上下文
		ClassPathXmlApplicationContext ac=new ClassPathXmlApplicationContext(acLocation);
		return ac;
	}
	
	@Test
	public void test(){
		//
		ClassPathXmlApplicationContext ac=initContext();
		
		//从上下文获取adminMapper
		AdminMapper adminMapper=ac.getBean("adminMapper", AdminMapper.class);
		//业务：
		List<Admin> admins=adminMapper.findAll();
		for(Admin a:admins){
			System.out.println(a.getUserId()+":"+a.getUserName()+":"+a.getUserPw());
		}
	}
	@Test
	public void testWithScan(){
		//
		ApplicationContext ac=initContext();
		
		//从上下文获取adminMapper
		AdminMapper adminMapper=ac.getBean("adminMapper",AdminMapper.class);
		
		//业务：
		List<Admin> admins=adminMapper.findAll();
		for(Admin a:admins){
			System.out.println(a.getUserId()+":"+a.getUserName()+":"+a.getUserPw());
		}
	}
	@Test
	public void testWithScanAnnotation(){
		//
		ApplicationContext ac=initContext();
		
		//从上下文获取adminMapper
		AdminMapper adminMapper=ac.getBean("adminMapper",AdminMapper.class);
		
		//业务：
		List<Admin> admins=adminMapper.findAll();
		for(Admin a:admins){
			System.out.println(a.getUserId()+":"+a.getUserName()+":"+a.getUserPw());
		}
	}
}

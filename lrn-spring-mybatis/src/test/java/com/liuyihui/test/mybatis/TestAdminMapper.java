package com.liuyihui.test.mybatis;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.liuyihui.lrn.entity.Admin;
import com.liuyihui.lrn.mapper.interf.AdminMapper;

public class TestAdminMapper {
	
	private ClassPathXmlApplicationContext initContext(){
		//����spring�����������ļ�λ��
		String acLocation="application-context.xml";
		//��ȡspring������
		ClassPathXmlApplicationContext ac=new ClassPathXmlApplicationContext(acLocation);
		return ac;
	}
	
	@Test
	public void test(){
		//
		ClassPathXmlApplicationContext ac=initContext();
		
		//�������Ļ�ȡadminMapper
		AdminMapper adminMapper=ac.getBean("adminMapper", AdminMapper.class);
		//ҵ��
		List<Admin> admins=adminMapper.findAll();
		for(Admin a:admins){
			System.out.println(a.getUserId()+":"+a.getUserName()+":"+a.getUserPw());
		}
	}
	@Test
	public void testWithScan(){
		//
		ApplicationContext ac=initContext();
		
		//�������Ļ�ȡadminMapper
		AdminMapper adminMapper=ac.getBean("adminMapper",AdminMapper.class);
		
		//ҵ��
		List<Admin> admins=adminMapper.findAll();
		for(Admin a:admins){
			System.out.println(a.getUserId()+":"+a.getUserName()+":"+a.getUserPw());
		}
	}
	@Test
	public void testWithScanAnnotation(){
		//
		ApplicationContext ac=initContext();
		
		//�������Ļ�ȡadminMapper
		AdminMapper adminMapper=ac.getBean("adminMapper",AdminMapper.class);
		
		//ҵ��
		List<Admin> admins=adminMapper.findAll();
		for(Admin a:admins){
			System.out.println(a.getUserId()+":"+a.getUserName()+":"+a.getUserPw());
		}
	}
}

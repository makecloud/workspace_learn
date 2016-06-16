package com.liuyihui.test.mybatis;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.liuyihui.lrn.entity.Admin;

public class TestFindUseMybatis {
	
	public static SqlSession getSqlSession() throws IOException{
		//
		String confirLocation="SqlMapConfig.xml";
		
		//
		Reader reader=Resources.getResourceAsReader(confirLocation);
		
		//
		SqlSessionFactoryBuilder sfb = new SqlSessionFactoryBuilder();
		
		//
		SqlSessionFactory sf = sfb.build(reader);
		
		//
		SqlSession session=sf.openSession();
		
		return session;
	}
	
	
	@Test
	public void testFindById() throws IOException{
		//��ȡSqlSession
		SqlSession session=getSqlSession();
		
		//ҵ��
		Admin a = session.selectOne("findById",2); 
		System.out.println(a.getUserId());
		System.out.println(a.getUserName());
		System.out.println(a.getUserPw());
		
		//�ر�
		session.close();
	}
	@Test
	public void testFindAll() throws IOException{
		//��ȡSqlSession
		SqlSession session=getSqlSession();
		
		//ҵ��
		List<Admin> admins=session.selectList("findAll");
		for(Admin a:admins){
			System.out.println(a.getUserId()+":"+a.getUserName()+":"+a.getUserPw());
		}
		//�ر�
		session.close();
	}
}

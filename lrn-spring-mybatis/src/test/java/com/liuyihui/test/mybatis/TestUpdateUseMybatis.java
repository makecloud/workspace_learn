package com.liuyihui.test.mybatis;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.liuyihui.lrn.entity.Admin;

public class TestUpdateUseMybatis {
	
	@Test
	public void test() throws IOException {
		//�����ļ�λ��
		String configLocation="SqlMapConfig.xml";
		
		//��ȡReader
		Reader reader=Resources.getResourceAsReader(configLocation);
		
		//����SessionFactoryBuilder
		SqlSessionFactoryBuilder sfb= new SqlSessionFactoryBuilder();
		
		//��ȡsessionfactory
		SqlSessionFactory sf=sfb.build(reader);
		
		//
		SqlSession session = sf.openSession();
		
		//
		Admin admin = new Admin();
		admin.setUserId(3);
		admin.setUserName("meng");
		admin.setUserPw("123");
		session.update("updateAdmin", admin);
		
		session.commit();
		session.close();
	}
}

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
		//定义文件位置
		String configLocation="SqlMapConfig.xml";
		
		//获取Reader
		Reader reader=Resources.getResourceAsReader(configLocation);
		
		//定义SessionFactoryBuilder
		SqlSessionFactoryBuilder sfb= new SqlSessionFactoryBuilder();
		
		//获取sessionfactory
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

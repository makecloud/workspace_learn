package com.liuyihui.test.mybatis;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

public class TestDeleteUseMybatis {
	@Test
	public void test () throws IOException{
		//
		String configLocation="SqlMapConfig.xml";
		
		//
		Reader reader = Resources.getResourceAsReader(configLocation);
		
		//
		SqlSessionFactoryBuilder sfb=new SqlSessionFactoryBuilder();
		
		//
		SqlSessionFactory sf =sfb.build(reader);
		
		//
		SqlSession session=sf.openSession();
		
		//
		session.delete("deleteById",3);
		
		//
		session.commit();
		session.close();
	}
}

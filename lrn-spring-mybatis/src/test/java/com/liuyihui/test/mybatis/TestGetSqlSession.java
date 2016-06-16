package com.liuyihui.test.mybatis;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
/**
 * @author yihui
 * 测试：获取mybatis的sqlSession 全部流程
 *
 */
public class TestGetSqlSession {
	@Test
	public void testSqlSession() throws IOException{
		
		String config="SqlMapConfig.xml";
		Reader reader = Resources.getResourceAsReader(config);
		SqlSessionFactoryBuilder sfb=new SqlSessionFactoryBuilder();
		SqlSessionFactory sf=sfb.build(reader);
		SqlSession session = sf.openSession();
		System.out.println(session);
		session.close();
	}
}

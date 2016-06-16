package com.liuyihui.test.mybatis;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.liuyihui.lrn.entity.Admin;
/**
 * @author yihui
 * 测试：用mybatis插入一条记录的全部流程
 *
 */
public class TestInsertUseMybatis {
	public static void main(String[] args) {
		
	}
	
	@Test
	public void testInetUseMybatis() throws IOException{
		//定义文件位置
		String configLocation="SqlMapConfig.xml";
		
		//获取reader
		Reader reader = Resources.getResourceAsReader(configLocation);
		
		//创建SessionFactoryBuilder对象
		SqlSessionFactoryBuilder sfb = new SqlSessionFactoryBuilder();
		
		//创建sessionFactory对象
		SqlSessionFactory sf=sfb.build(reader);
		
		//创建session
		SqlSession session=sf.openSession();
		
		//创建实体对象，并设置属性。
		Admin admin = new Admin();
		admin.setUserId(3);
		admin.setUserName("mengmeizi");
		admin.setUserPw("123456");
		
		//调用addAdmin操作
		session.insert("addAdmin", admin);
		
		//提交事务，关闭对象
		session.commit();
		session.close();
	}
}

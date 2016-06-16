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
 * ���ԣ���mybatis����һ����¼��ȫ������
 *
 */
public class TestInsertUseMybatis {
	public static void main(String[] args) {
		
	}
	
	@Test
	public void testInetUseMybatis() throws IOException{
		//�����ļ�λ��
		String configLocation="SqlMapConfig.xml";
		
		//��ȡreader
		Reader reader = Resources.getResourceAsReader(configLocation);
		
		//����SessionFactoryBuilder����
		SqlSessionFactoryBuilder sfb = new SqlSessionFactoryBuilder();
		
		//����sessionFactory����
		SqlSessionFactory sf=sfb.build(reader);
		
		//����session
		SqlSession session=sf.openSession();
		
		//����ʵ����󣬲��������ԡ�
		Admin admin = new Admin();
		admin.setUserId(3);
		admin.setUserName("mengmeizi");
		admin.setUserPw("123456");
		
		//����addAdmin����
		session.insert("addAdmin", admin);
		
		//�ύ���񣬹رն���
		session.commit();
		session.close();
	}
}

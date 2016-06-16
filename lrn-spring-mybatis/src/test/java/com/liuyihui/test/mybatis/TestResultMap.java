package com.liuyihui.test.mybatis;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.liuyihui.lrn.entity.Admin;
import com.liuyihui.lrn.entity.Entity;
import com.liuyihui.lrn.mapper.interf.AdminMapper;


public class TestResultMap {
	@Test
	public void test() throws IOException{
		//获取session
		SqlSession session = TestFindUseMybatis.getSqlSession();
		
		//
		AdminMapper adminMapper= session.getMapper(AdminMapper.class);
		
		//业务：
		List<Entity> entitys = adminMapper.findAllentity();
		for(Entity e:entitys){
			System.out.println(e.getUserId()+":"+e.getUserName()+":"+e.getUserPassword());
		}
		session.close();
	}
}

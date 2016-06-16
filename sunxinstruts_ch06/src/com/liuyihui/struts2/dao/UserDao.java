package com.liuyihui.struts2.dao;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.liuyihui.struts2.dao.interf.IUserDao;
import com.liuyihui.struts2.entity.RegUser;

public class UserDao extends HibernateDaoSupport implements IUserDao<RegUser>{
	
	@Autowired
	private SessionFactory sf;
	
	
	@Override
	public List<RegUser> findAll() {
		Session session=sf.openSession();
		Transaction ts= session.beginTransaction();
		String sql="select * from reg_user";
		SQLQuery sq=session.createSQLQuery(sql);
		List<RegUser> list=sq.list();
		ts.commit();
		session.close();
		
		return list;
		
	}
	
	
	
	
	
	
	
	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}
	
}

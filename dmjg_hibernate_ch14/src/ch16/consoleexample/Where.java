package ch16.consoleexample;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import ch14.ORM.HibernateSessionFactory;

/**
 * 在HQL中可以使用where字句进一步限制要查询的条件
 * 如果where字句包含多个条件则中间要使用or或and指定这些条件之间的关系。
 * @author yihui
 *
 */
public class Where {

	
	public static void main(String[] args) {
		Session session = HibernateSessionFactory.getSession();
		//包含一个查询条件的where字句
		String hql="select name from MyMessage where id > 12";
		Query query=session.createQuery(hql);
		List<String> list=query.list();
		for(Object obj:list)
		{
			System.out.println(obj);
		}
		//包含两个查询条件的where字句
		hql="select id , name from MyMessage where id>1 and not (name like '%x%')";
		query=session.createQuery(hql);
		list =query.list();
		for(Object obj:list)
		{
			Object[] properties=(Object[]) obj;
			System.out.println(properties[0]+" "+properties[1]);
			
		}
		session.close();
	}

}

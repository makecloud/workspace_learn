package ch16.consoleexample;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import ch14.ORM.HibernateSessionFactory;

/**
 * 如果使用select，就不会返回相应的持久化对象了，而是根据情况返回不同类型的List对象。如果上面的HQL语句只查询name属性
 * 那么就返回List<Object>类型的List；如果查询多个属性，就回返回List<Object[]>类型的List ！！
 * @author yihui
 *
 */
public class Select {
	public static void main(String[] args) {
		Session session = HibernateSessionFactory.getSession();
		//select字句只有一个属性的情况
		String hql="select name from MyMessage";
		Query query=session.createQuery(hql);
		List<String> list=query.list();
		for(String name:list)
		{
			System.out.println(name);
		}
		hql="select id,name from MyMessage";
		query=session.createQuery(hql);
		list=query.list();//list的元素是object数组
		for(Object obj:list)
		{
			Object[] properties=(Object[]) obj;
			System.out.println(properties[0]+" "+properties[1]);
			
		}
		session.close();
		
	}
}

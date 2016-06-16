package ch16.consoleexample;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import ch14.ORM.HibernateSessionFactory;
import ch14.entity.MyMessage;

/**
 * HQL入门
 * 	最简单的HQL查询是根据指定的实体bean返回所有满足条件的持久化对象。在这个查询中只有一个from关键字
 * form关键字后跟实体bean的类名。
 * 首先使用Session接口的createQuery方法创建一个Query对象。然后使用query类的list方法获得满足条件的持久化对象
 * @author yihui
 *
 */
public class FirstHQL {
	public static void main(String[] args) {
		Session session = HibernateSessionFactory.getSession();
		//定义HQL语句
		String hql="from MyMessage";
		//通过指定的HQL语句创建Query对象
		Query query=session.createQuery(hql);
		//session.createSQLQuery(hql);
		//执行HQL，并返回对应的MyMessage对象
		List<MyMessage> list=query.list();
		for(MyMessage message:list)
		{
			System.out.println(message.getId()+" "+message.getName());
			
		}
		session.close();
	}
}

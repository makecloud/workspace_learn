package ch16.consoleexample;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import ch14.ORM.HibernateSessionFactory;

/**
 * HQL也支持insert语句
 * 在HQL中的insert语句并不支持values，要想指定插入的值，必须使用select语句。
 * @author yihui
 *
 */
public class Insert {
	public static void main(String[] args) {
		Session session =HibernateSessionFactory.getSession();
		Transaction tx=session.beginTransaction();
		String hqlDelete="delete MyMessage where id=20";//删除id=20的MyMessage的对象
		Query query =session.createQuery(hqlDelete);
		query.executeUpdate();
		String hql="insert into MyMessage (id,name) select 123,name from MyMessage where id=20";
		//向t_message表中插入一条记录
		query=session.createQuery(hql);
		int count=query.executeUpdate();
		tx.commit();
		System.out.println(count);
		session.close();
		
		
	}
}

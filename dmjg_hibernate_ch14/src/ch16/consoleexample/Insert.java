package ch16.consoleexample;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import ch14.ORM.HibernateSessionFactory;

/**
 * HQLҲ֧��insert���
 * ��HQL�е�insert��䲢��֧��values��Ҫ��ָ�������ֵ������ʹ��select��䡣
 * @author yihui
 *
 */
public class Insert {
	public static void main(String[] args) {
		Session session =HibernateSessionFactory.getSession();
		Transaction tx=session.beginTransaction();
		String hqlDelete="delete MyMessage where id=20";//ɾ��id=20��MyMessage�Ķ���
		Query query =session.createQuery(hqlDelete);
		query.executeUpdate();
		String hql="insert into MyMessage (id,name) select 123,name from MyMessage where id=20";
		//��t_message���в���һ����¼
		query=session.createQuery(hql);
		int count=query.executeUpdate();
		tx.commit();
		System.out.println(count);
		session.close();
		
		
	}
}

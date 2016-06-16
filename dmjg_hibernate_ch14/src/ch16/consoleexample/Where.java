package ch16.consoleexample;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import ch14.ORM.HibernateSessionFactory;

/**
 * ��HQL�п���ʹ��where�־��һ������Ҫ��ѯ������
 * ���where�־��������������м�Ҫʹ��or��andָ����Щ����֮��Ĺ�ϵ��
 * @author yihui
 *
 */
public class Where {

	
	public static void main(String[] args) {
		Session session = HibernateSessionFactory.getSession();
		//����һ����ѯ������where�־�
		String hql="select name from MyMessage where id > 12";
		Query query=session.createQuery(hql);
		List<String> list=query.list();
		for(Object obj:list)
		{
			System.out.println(obj);
		}
		//����������ѯ������where�־�
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

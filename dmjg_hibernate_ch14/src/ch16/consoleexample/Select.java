package ch16.consoleexample;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import ch14.ORM.HibernateSessionFactory;

/**
 * ���ʹ��select���Ͳ��᷵����Ӧ�ĳ־û������ˣ����Ǹ���������ز�ͬ���͵�List������������HQL���ֻ��ѯname����
 * ��ô�ͷ���List<Object>���͵�List�������ѯ������ԣ��ͻط���List<Object[]>���͵�List ����
 * @author yihui
 *
 */
public class Select {
	public static void main(String[] args) {
		Session session = HibernateSessionFactory.getSession();
		//select�־�ֻ��һ�����Ե����
		String hql="select name from MyMessage";
		Query query=session.createQuery(hql);
		List<String> list=query.list();
		for(String name:list)
		{
			System.out.println(name);
		}
		hql="select id,name from MyMessage";
		query=session.createQuery(hql);
		list=query.list();//list��Ԫ����object����
		for(Object obj:list)
		{
			Object[] properties=(Object[]) obj;
			System.out.println(properties[0]+" "+properties[1]);
			
		}
		session.close();
		
	}
}

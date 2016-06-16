package ch16.consoleexample;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import ch14.ORM.HibernateSessionFactory;
import ch14.entity.MyMessage;

/**
 * HQL����
 * 	��򵥵�HQL��ѯ�Ǹ���ָ����ʵ��bean�����������������ĳ־û������������ѯ��ֻ��һ��from�ؼ���
 * form�ؼ��ֺ��ʵ��bean��������
 * ����ʹ��Session�ӿڵ�createQuery��������һ��Query����Ȼ��ʹ��query���list����������������ĳ־û�����
 * @author yihui
 *
 */
public class FirstHQL {
	public static void main(String[] args) {
		Session session = HibernateSessionFactory.getSession();
		//����HQL���
		String hql="from MyMessage";
		//ͨ��ָ����HQL��䴴��Query����
		Query query=session.createQuery(hql);
		//session.createSQLQuery(hql);
		//ִ��HQL�������ض�Ӧ��MyMessage����
		List<MyMessage> list=query.list();
		for(MyMessage message:list)
		{
			System.out.println(message.getId()+" "+message.getName());
			
		}
		session.close();
	}
}

package ch16.consoleexample;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import ch14.ORM.HibernateSessionFactory;
import ch14.entity.MyMessage;

/**
 * ʹ��org.hibernate.criteria.Order��Բ�ѯ�����������Order����������̬����asc��desc��������
 * ����������Order����ʹ����������������ָ��Ҫ�־û��Ķ�������ԡ����������ʾ���ʹ��Order����Խ������������
 * @author yihui
 *
 */
public class SortResult {
	public static void main(String[] args) {
		Session session=HibernateSessionFactory.getSession();
		Criteria crit=session.createCriteria(MyMessage.class);
		crit.addOrder(Order.desc("id"));
		List<MyMessage> messages=crit.list();
		for(MyMessage message:messages)
		{
			System.out.println(message.getId()+" "+message.getName());
			System.out.println();
			
		}
		session.close();
	}
}

package ch16.consoleexample;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import ch14.ORM.HibernateSessionFactory;

/**
 * �ڱ�׼API�д�����ʵ��bean���������������Order��Customer���������Ķ��һ��ϵ��Ҫ���������ֹ�ϵ
 * ��Ҫʹ��Criteria�ӿڵ�createCriteria��������һ��Criteria������������Ĳ�����һ��������������
 * ����Order��Customer����Ķ��һ��ϵ��Ҫ��ʹ��session�ӿڵ�createCriteria�����Ƚ���һ��Criteria����
 * Ȼ����ʹ�����Criteria�����createCriteria����ΪOrder���customer���Դ���һ��criteria����
 * @author yihui
 *
 */
public class Associations {
	public static void main(String[] args) {
		Session session=HibernateSessionFactory.getSession();
		//����Order�����Customer�����һ�Զ��ϵ
		Criteria crit1=session.createCriteria(Order.class);
		Criteria crit2=crit1.createCriteria("customer");
		//���Լ������
		crit1.add(Restrictions.ne("numbers", "20100014452"));
		crit2.add(Restrictions.eq("name","google"));
		crit2.addOrder(Order.desc("name"));
		List<Order> orders=crit1.list();
		//TODO
	}
}

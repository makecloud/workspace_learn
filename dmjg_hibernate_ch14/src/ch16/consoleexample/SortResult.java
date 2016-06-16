package ch16.consoleexample;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import ch14.ORM.HibernateSessionFactory;
import ch14.entity.MyMessage;

/**
 * 使用org.hibernate.criteria.Order类对查询结果进行排序。Order类有两个静态方法asc和desc，这两个
 * 方法都返回Order对象。使用这两个方法可以指定要持久化的对象的属性。下面代码演示如何使用Order对象对结果集进行排序。
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

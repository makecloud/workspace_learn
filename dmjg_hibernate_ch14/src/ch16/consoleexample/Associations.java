package ch16.consoleexample;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import ch14.ORM.HibernateSessionFactory;

/**
 * 在标准API中处理多个实体bean关联的情况。如想Order和Customer对象这样的多对一关系。要想描述这种关系
 * 需要使用Criteria接口的createCriteria方法创建一个Criteria对象。这个方法的参数是一个属性名，对于
 * 建立Order和Customer对象的多对一关系，要先使用session接口的createCriteria方法先建立一个Criteria对象
 * 然后再使用这个Criteria对象的createCriteria方法为Order类的customer属性创建一个criteria对象
 * @author yihui
 *
 */
public class Associations {
	public static void main(String[] args) {
		Session session=HibernateSessionFactory.getSession();
		//建立Order对象和Customer对象的一对多关系
		Criteria crit1=session.createCriteria(Order.class);
		Criteria crit2=crit1.createCriteria("customer");
		//添加约束条件
		crit1.add(Restrictions.ne("numbers", "20100014452"));
		crit2.add(Restrictions.eq("name","google"));
		crit2.addOrder(Order.desc("name"));
		List<Order> orders=crit1.list();
		//TODO
	}
}

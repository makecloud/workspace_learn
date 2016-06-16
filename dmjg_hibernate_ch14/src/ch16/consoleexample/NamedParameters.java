package ch16.consoleexample;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import ch14.ORM.HibernateSessionFactory;
import ch14.entity.MyMessage;
import ch15.entity.Address;
import ch15.entity.Employee;
/**
 * HQL和JDBC一样也支持查询参数。但他们不同的是，JDBC的查询参数只支持按位置赋值(按？出现的位置)，而HQL的查询参数
 * 支持按位置和查询参数名赋值。HQL查询参数不适用？表示，而是使用名称表示(称为命名参数).HQL命名参数使用冒号开头，
 * 如下面的代码所示：
 * from MyMessage where id>:id
 * 在为命名参数赋值的时候，需要使用Query接口的setXxx方法，Xxx是某某类型
 * HQL查询参数不仅可以是java简单类型，也可以使用实体bean作为参数，代码如下
 * String hql ="from Employee e where e.address=:address";
 * Query query=session.createQuery(hql);
 * query.setEntity("address",address); 
 * @author yihui
 *
 */
public class NamedParameters {

	public static void main(String[] args) {
		Session session = HibernateSessionFactory.getSession();
		String hql="from MyMessage where id<:id";
		Query query=session.createQuery(hql);
		query.setInteger("id", 1);
		List<MyMessage> list=query.list();
		for(MyMessage message:list)
		{
			System.out.println(message.getId()+" "+message.getName());
			
		}
		Address address=new Address();
		address.setId(132);
		//定义带address类型命名参数的hql
		hql="from Employee e where e.address=:address";
		query=session.createQuery(hql);
		query.setEntity("address", address);
		Object obj = query.uniqueResult();
		if(null!=obj)
		{
			Employee employee=(Employee)obj;
			System.out.println(employee.getName());
		}
		session.close();
	}

}

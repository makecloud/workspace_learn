package ch16.consoleexample;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import ch14.ORM.HibernateSessionFactory;
import ch14.entity.MyMessage;
import ch15.entity.Address;
import ch15.entity.Employee;
/**
 * HQL��JDBCһ��Ҳ֧�ֲ�ѯ�����������ǲ�ͬ���ǣ�JDBC�Ĳ�ѯ����ֻ֧�ְ�λ�ø�ֵ(�������ֵ�λ��)����HQL�Ĳ�ѯ����
 * ֧�ְ�λ�úͲ�ѯ��������ֵ��HQL��ѯ���������ã���ʾ������ʹ�����Ʊ�ʾ(��Ϊ��������).HQL��������ʹ��ð�ſ�ͷ��
 * ������Ĵ�����ʾ��
 * from MyMessage where id>:id
 * ��Ϊ����������ֵ��ʱ����Ҫʹ��Query�ӿڵ�setXxx������Xxx��ĳĳ����
 * HQL��ѯ��������������java�����ͣ�Ҳ����ʹ��ʵ��bean��Ϊ��������������
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
		//�����address��������������hql
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

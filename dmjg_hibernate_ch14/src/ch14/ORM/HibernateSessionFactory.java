package ch14.ORM;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactory {
	
	//指定hibernate的配置文件名
	private static String CONFIG_FILE_LOCATION="/hibernate.cfg.xml";
	//定义ThreadLocal对象
	private static final ThreadLocal<Session> threadLocal=new ThreadLocal<Session>();
	//定义Configuration对象用于读取hibernate配置文件
	private static Configuration configuration=new Configuration();
	private static SessionFactory sessionFactory;
	private static String configFile=CONFIG_FILE_LOCATION;
	
	static
	{
		try
		{
			//开始读取hibernate配置文件
			configuration.configure(configFile);
			configuration.setProperty("javax.persistence.validation.mode", "none");
			//实例sessionFactory
			sessionFactory=configuration.buildSessionFactory();
		}
		catch(Exception e)
		{
			System.out.println("error in creating sessionFactory");
			e.printStackTrace();
		}
	}
	private HibernateSessionFactory()
	{
		
	}
	//获得一个session对象
	public static Session getSession() throws HibernateException
	{
		//从threadLocal对象中获得Session对象
		Session session=(Session)threadLocal.get();
		//如果是threadLocal中没有当前线程的session对象，或Session对象未打开，则新建一个Session对象
		if(session==null || !session.isOpen())
		{
			//如果未建立SessionFactory对象，则重新建立一个SessionFactory对象
			if(sessionFactory == null)
			{
				rebuildSessionFactory();
			}
			//如果成功建立了SessionFactoty对象，则通过openSession方法建立一个Session对象
			session=(sessionFactory!=null)?sessionFactory.openSession():null;
			//将新建立的session对象保存在ThreadLocal对象中
			threadLocal.set(session);
		}
		return session;
	}
	public static void rebuildSessionFactory()
	{
		try
		{
			configuration.configure(configFile);//装载配置文件
			sessionFactory=configuration.buildSessionFactory();//configuration对象创建SessionFactory对象
			
		}
		catch(Exception e)
		{
			System.out.println("创建sessionFactory错误！");
			e.printStackTrace();
		}
	}
	public static  void colseSession()throws HibernateException//关闭session
	{
		Session session=(Session)threadLocal.get();
		threadLocal.set(null);
		if (session!=null)
		{
			session.close();
		}
	}

	
	
	public SessionFactory getSessionFactory()//获得sessionFactoty对象
	{
		return sessionFactory;
	}
	public void setConfigFile(String configFile)//重新设置configFile配置
	{
		HibernateSessionFactory.configFile=configFile;
		sessionFactory=null;
	}
	public static Configuration getConfiguration()//获得Configuration对象
	{
		return configuration;
	}
}

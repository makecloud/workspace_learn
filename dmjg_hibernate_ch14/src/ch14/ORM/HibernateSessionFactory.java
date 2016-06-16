package ch14.ORM;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactory {
	
	//ָ��hibernate�������ļ���
	private static String CONFIG_FILE_LOCATION="/hibernate.cfg.xml";
	//����ThreadLocal����
	private static final ThreadLocal<Session> threadLocal=new ThreadLocal<Session>();
	//����Configuration�������ڶ�ȡhibernate�����ļ�
	private static Configuration configuration=new Configuration();
	private static SessionFactory sessionFactory;
	private static String configFile=CONFIG_FILE_LOCATION;
	
	static
	{
		try
		{
			//��ʼ��ȡhibernate�����ļ�
			configuration.configure(configFile);
			configuration.setProperty("javax.persistence.validation.mode", "none");
			//ʵ��sessionFactory
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
	//���һ��session����
	public static Session getSession() throws HibernateException
	{
		//��threadLocal�����л��Session����
		Session session=(Session)threadLocal.get();
		//�����threadLocal��û�е�ǰ�̵߳�session���󣬻�Session����δ�򿪣����½�һ��Session����
		if(session==null || !session.isOpen())
		{
			//���δ����SessionFactory���������½���һ��SessionFactory����
			if(sessionFactory == null)
			{
				rebuildSessionFactory();
			}
			//����ɹ�������SessionFactoty������ͨ��openSession��������һ��Session����
			session=(sessionFactory!=null)?sessionFactory.openSession():null;
			//���½�����session���󱣴���ThreadLocal������
			threadLocal.set(session);
		}
		return session;
	}
	public static void rebuildSessionFactory()
	{
		try
		{
			configuration.configure(configFile);//װ�������ļ�
			sessionFactory=configuration.buildSessionFactory();//configuration���󴴽�SessionFactory����
			
		}
		catch(Exception e)
		{
			System.out.println("����sessionFactory����");
			e.printStackTrace();
		}
	}
	public static  void colseSession()throws HibernateException//�ر�session
	{
		Session session=(Session)threadLocal.get();
		threadLocal.set(null);
		if (session!=null)
		{
			session.close();
		}
	}

	
	
	public SessionFactory getSessionFactory()//���sessionFactoty����
	{
		return sessionFactory;
	}
	public void setConfigFile(String configFile)//��������configFile����
	{
		HibernateSessionFactory.configFile=configFile;
		sessionFactory=null;
	}
	public static Configuration getConfiguration()//���Configuration����
	{
		return configuration;
	}
}

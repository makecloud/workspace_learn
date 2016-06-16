package ch14.action;

import org.hibernate.Session;
import org.hibernate.Transaction;


import ch14.ORM.HibernateSessionFactory;
import ch14.entity.MyMessage;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

//ʹ��Action��������hibernate����������Ӽ�¼
public class FirstUseHibernate extends ActionSupport implements ModelDriven<MyMessage>{
	
	private String result;
	private MyMessage myMessage=new MyMessage();
	
	@Override
	public MyMessage getModel() {
		
		return myMessage;
	}
	@Override
	public String execute() throws Exception {
		
		Session session =HibernateSessionFactory.getSession();
		Transaction tx=null;
		System.out.println("MyMessage��name����ֵ��"+getModel().getName());
		try
		{	
			
			tx=session.beginTransaction();
			session.save(myMessage);
			tx.commit();
			setResult("��Ӽ�¼�ɹ�");
			System.out.println("resultֵ"+result);
			
		}
		catch(Exception e)
		{
			setResult(e.getMessage());
			if(null!=tx)
			{
				tx.rollback();//�����쳣���ع�����
			}
		}
		finally
		{
			if(null!=session)
			{
				session.close();//�ر�session
			}
		}
		
		return SUCCESS;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public MyMessage getMyMessage() {
		return myMessage;
	}

	public void setMyMessage(MyMessage myMessage) {
		this.myMessage = myMessage;
	}
	
	
	
}

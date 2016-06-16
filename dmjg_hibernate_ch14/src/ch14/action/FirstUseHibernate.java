package ch14.action;

import org.hibernate.Session;
import org.hibernate.Transaction;


import ch14.ORM.HibernateSessionFactory;
import ch14.entity.MyMessage;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

//使用Action类来调用hibernate框架向表中添加纪录
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
		System.out.println("MyMessage的name属性值："+getModel().getName());
		try
		{	
			
			tx=session.beginTransaction();
			session.save(myMessage);
			tx.commit();
			setResult("添加纪录成功");
			System.out.println("result值"+result);
			
		}
		catch(Exception e)
		{
			setResult(e.getMessage());
			if(null!=tx)
			{
				tx.rollback();//遇到异常，回滚事务
			}
		}
		finally
		{
			if(null!=session)
			{
				session.close();//关闭session
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

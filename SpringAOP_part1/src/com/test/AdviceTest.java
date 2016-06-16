package com.test;

import org.junit.Test;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.advice.GreetAdviceBefore;
import com.interf.Waiter;
import com.role.NaiveWaiter;
import com.role.Seller2;
/**
 * ��ǿ���е㣬Advisor���棬��������������
 * @author yihui
 *
 */
public class AdviceTest {
	
	@Test// ǰ����ǿtest
	public void beforeAdviceTest()
	{
		Waiter naiveWaiter=new NaiveWaiter();
		GreetAdviceBefore ga=new GreetAdviceBefore();
		ProxyFactory pf=new ProxyFactory();
		pf.addAdvice(ga);
		pf.setTarget(naiveWaiter);
		Waiter waiter=(Waiter) pf.getProxy();
		
		waiter.greetTo("Tom");
		waiter.serverTo("Tom");
	}
	@Test //ǰ����ǿ����2
	public void beforeAdviceTest2()
	{
		String applicationContextPath="applicationContext.xml";
		ApplicationContext ac=new ClassPathXmlApplicationContext(applicationContextPath);
		Waiter waiter=(Waiter)ac.getBean("waiter");
		waiter.greetTo("tom");
		waiter.serverTo("tom");
	}
	@Test //����������ǿ����
	public void afterAdviceTest()
	{
		String applicationContextPath="applicationContext.xml";
		ApplicationContext ac=new ClassPathXmlApplicationContext(applicationContextPath);
		Waiter waiter=(Waiter) ac.getBean("waiter");
		waiter.greetTo("tom");
		waiter.serverTo("tom");
	}
	@Test //�������1 //��̬ ��������ƥ��+����ƥ������
	public void advisorTest()
	{
		String applicationContextPath="applicationContext.xml";
		ApplicationContext ac=new ClassPathXmlApplicationContext(applicationContextPath);
		com.role.Waiter waiter=(com.role.Waiter) ac.getBean("waiter2");
		Seller2 seller= (Seller2) ac.getBean("seller2");
		waiter.greetTo("�Ը�");
		System.out.println();
		seller.greetTo("�Ը�");
	}
	@Test //�������2 //��̬ ��ͨ��������ʽƥ�䷽���� ����
	public void advisorTest2()
	{
		String applicationContextPath="applicationContext.xml";
		ApplicationContext ac=new ClassPathXmlApplicationContext(applicationContextPath);
		com.role.Waiter waiter=(com.role.Waiter) ac.getBean("waiter1");
		waiter.greetTo("�Ը�");
		
	}
	@Test //�Զ�����bean������ DefaultAdvisorAutoProxyCreator ����
	public void autoproxyTest1()
	{
		String path="com/autoproxyXML/beans.xml";
		ApplicationContext ac=new ClassPathXmlApplicationContext(path);
		com.role.Waiter w= (com.role.Waiter) ac.getBean("waiter");
		Seller2 s=(Seller2) ac.getBean("seller");
		w.greetTo("�Ը�");
		s.greetTo("�Ը�");
	}
}

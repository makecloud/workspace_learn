package com.test;

import org.junit.Test;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.aspectj.PreGreetingAspect;
import com.interf.Waiter;
import com.role.NaiveWaiter;

public class AspectJProxyTest {
	
	@Test//aspectJ����֯���߼�AspectJProxyFactory����
	public void test() 
	{
		Waiter target =new NaiveWaiter();
		AspectJProxyFactory factory=new AspectJProxyFactory();
		
		//����Ŀ�����
		factory.setTarget(target);
		
		//���������
		factory.addAspect(PreGreetingAspect.class);
		
		//����֯��������Ĵ���
		Waiter proxy = factory.getProxy();
		
		proxy.greetTo("�Ը�");
		proxy.serverTo("�Ը�");
		
	}
	@Test//aop-autoproxy���Զ�֯��AspectJ���沢���ɴ���
	public void test2()
	{
		String springConfig="com/autoproxyXML/beans.xml";
		ApplicationContext ac=new ClassPathXmlApplicationContext(springConfig);
		com.role.Waiter waiter3= (com.role.Waiter) ac.getBean("waiter3");
		waiter3.greetTo("huige");
		waiter3.serverTo("huige");
	}
}

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
 * 增强，切点，Advisor切面，代理创建器，测试
 * @author yihui
 *
 */
public class AdviceTest {
	
	@Test// 前置增强test
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
	@Test //前置增强测试2
	public void beforeAdviceTest2()
	{
		String applicationContextPath="applicationContext.xml";
		ApplicationContext ac=new ClassPathXmlApplicationContext(applicationContextPath);
		Waiter waiter=(Waiter)ac.getBean("waiter");
		waiter.greetTo("tom");
		waiter.serverTo("tom");
	}
	@Test //方法返回增强测试
	public void afterAdviceTest()
	{
		String applicationContextPath="applicationContext.xml";
		ApplicationContext ac=new ClassPathXmlApplicationContext(applicationContextPath);
		Waiter waiter=(Waiter) ac.getBean("waiter");
		waiter.greetTo("tom");
		waiter.serverTo("tom");
	}
	@Test //切面测试1 //静态 ，方法名匹配+类名匹配切面
	public void advisorTest()
	{
		String applicationContextPath="applicationContext.xml";
		ApplicationContext ac=new ClassPathXmlApplicationContext(applicationContextPath);
		com.role.Waiter waiter=(com.role.Waiter) ac.getBean("waiter2");
		Seller2 seller= (Seller2) ac.getBean("seller2");
		waiter.greetTo("辉哥");
		System.out.println();
		seller.greetTo("辉哥");
	}
	@Test //切面测试2 //静态 ，通过正则表达式匹配方法名 切面
	public void advisorTest2()
	{
		String applicationContextPath="applicationContext.xml";
		ApplicationContext ac=new ClassPathXmlApplicationContext(applicationContextPath);
		com.role.Waiter waiter=(com.role.Waiter) ac.getBean("waiter1");
		waiter.greetTo("辉哥");
		
	}
	@Test //自动代理bean创建器 DefaultAdvisorAutoProxyCreator 测试
	public void autoproxyTest1()
	{
		String path="com/autoproxyXML/beans.xml";
		ApplicationContext ac=new ClassPathXmlApplicationContext(path);
		com.role.Waiter w= (com.role.Waiter) ac.getBean("waiter");
		Seller2 s=(Seller2) ac.getBean("seller");
		w.greetTo("辉哥");
		s.greetTo("辉哥");
	}
}

package com.test;

import org.junit.Test;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.aspectj.PreGreetingAspect;
import com.interf.Waiter;
import com.role.NaiveWaiter;

public class AspectJProxyTest {
	
	@Test//aspectJ切面织入逻辑AspectJProxyFactory测试
	public void test() 
	{
		Waiter target =new NaiveWaiter();
		AspectJProxyFactory factory=new AspectJProxyFactory();
		
		//设置目标对象
		factory.setTarget(target);
		
		//添加切面类
		factory.addAspect(PreGreetingAspect.class);
		
		//生成织入了切面的代理
		Waiter proxy = factory.getProxy();
		
		proxy.greetTo("辉哥");
		proxy.serverTo("辉哥");
		
	}
	@Test//aop-autoproxy，自动织入AspectJ切面并生成代理
	public void test2()
	{
		String springConfig="com/autoproxyXML/beans.xml";
		ApplicationContext ac=new ClassPathXmlApplicationContext(springConfig);
		com.role.Waiter waiter3= (com.role.Waiter) ac.getBean("waiter3");
		waiter3.greetTo("huige");
		waiter3.serverTo("huige");
	}
}

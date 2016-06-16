package com.liu.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.liu.bean.ExampleBean;
import com.liu.bean.MessageBean;

public class TestCase {
	
	@Test
	public void testMessageBean()
	{
		String conf="applicationContext.xml";
		ApplicationContext ac=new ClassPathXmlApplicationContext(conf);
		ac.getBean("messagebean",MessageBean.class).execute();
	}
	
	@Test
	public void testMessageBean2()
	{
		String conf="applicationContext.xml";
		ApplicationContext ac=new ClassPathXmlApplicationContext(conf);
		ac.getBean("messagebean2",MessageBean.class).execute();
	}
	@Test
	public void testSpringAnnotation()
	{
		String conf="applicationContext.xml";
		ApplicationContext ac=new ClassPathXmlApplicationContext(conf);
		ExampleBean ex=(ExampleBean) ac.getBean("exampleBean");
		ex.execute();
	}
}

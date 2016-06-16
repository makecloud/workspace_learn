package com.liu.test;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.liu.DAO.ExampleBean;

public class TestCase {
	
	@Test
	public void testInitContext()
	{
		String conf="applicationContext.xml";
		ApplicationContext ac=new ClassPathXmlApplicationContext(conf);
		System.out.println(ac);
	}
	@Test
	public void testCreateBeanObject()
	{
		String conf="applicationContext.xml";
		ApplicationContext ac=new ClassPathXmlApplicationContext(conf);
		
		Calendar cale1=(Calendar) ac.getBean("calendarObj1");
		Calendar cale1_2=ac.getBean("calendarObj1", Calendar.class);
		System.out.println("cale1:"+cale1_2);
		
		Calendar cale2=ac.getBean("calendarObj2",Calendar.class);
		System.out.println("cale2:"+cale2);
		
		Date  date=ac.getBean("dateObj",Date.class);
		System.out.println("date:"+date);
	}
	@Test
	public void testExampleBean()
	{
		String conf="applicationContext.xml";
		ApplicationContext ac= new ClassPathXmlApplicationContext(conf);
	
		ExampleBean bean1= ac.getBean("exampleBean",ExampleBean.class);
		ExampleBean bean2= ac.getBean("exampleBean",ExampleBean.class);
		System.out.println(bean1==bean2);
		
		AbstractApplicationContext ctx = (AbstractApplicationContext) ac;
		ctx.close();
	}
}

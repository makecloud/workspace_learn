package com.liuyihui.lrn.dubbo.consumer;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.liuyihui.lrn.dubbo.service.DemoService;

/**
 * 启动spring上下文，
 * 上下文配置dubbo参数（zookeeper地址，服务的interface及对应的服务beanId）
 * main方法中直接使用beanId，使用过程即为去zookeeper寻找服务，并调用服务，获取返回值
 * 
 * @author Administrator
 *
 */
public class Consumer {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("consumer.xml");
		context.start();
		
		DemoService demoService = (DemoService) context.getBean("remoteService");//获取远程服务bean
		String hello = demoService.sayHello("远程调用服务哈哈哈");//执行远程服务方法
		System.out.println(hello);//显示调用结果
		context.close();
	}
}

package com.liuyihui.lrn.dubbo.provider;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;
/**
 * 启动spring上下文，
 * spring根据上下文中配置的dubbo参数，在上下文中生成服务bean，并将服务bean的interface注册到zookeeper
 * @author Administrator
 *
 */
public class Provider {
	public static void main(String[] args) throws Exception {
		ClassPathXmlApplicationContext context= new ClassPathXmlApplicationContext("provider.xml");
		context.start();
		System.in.read(); //按任意键退出
		context.close();
	}
}

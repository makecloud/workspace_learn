package com.advice;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

/**
 * ������ǰ����ǿ��1
 * @author yihui
 *
 */
public class GreetingBeforeAdvice implements MethodBeforeAdvice {

	@Override
	public void before(Method arg0, Object[] arg1, Object arg2) throws Throwable 
	{
		System.out.println(arg2.getClass().getName()+","+arg0.getName());
		System.out.println("ǰ����ǿ��"+"hello "+arg1[0]);
	}

}

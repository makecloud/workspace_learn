package com.advice;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

/**
 * 方法级前置增强类1
 * @author yihui
 *
 */
public class GreetingBeforeAdvice implements MethodBeforeAdvice {

	@Override
	public void before(Method arg0, Object[] arg1, Object arg2) throws Throwable 
	{
		System.out.println(arg2.getClass().getName()+","+arg0.getName());
		System.out.println("前置增强："+"hello "+arg1[0]);
	}

}

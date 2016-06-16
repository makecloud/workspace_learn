package com.advice;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

public class GreetAdviceBefore implements MethodBeforeAdvice {

	@Override
	public void before(Method arg0, Object[] arg1, Object arg2)
			throws Throwable {
		String name=(String) arg1[0];
		System.out.println("ÄãºÃ£¡"+name);
		
	}
	
}

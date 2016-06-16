package com.advisor;

import java.lang.reflect.Method;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;

import com.interf.Waiter;

//继承了这个Advisor的类，的性质就是一个切面
public class GreetAdvisor extends StaticMethodMatcherPointcutAdvisor {

	@Override
	public boolean matches(Method arg0, Class<?> arg1) {
		return "greetTo".equals(arg0.getName());
	}
	@Override
	public ClassFilter getClassFilter() {
		return new ClassFilter(){

			@Override
			public boolean matches(Class<?> arg0) {
				return Waiter.class.isAssignableFrom(arg0);
			}
		};
		
	}

}

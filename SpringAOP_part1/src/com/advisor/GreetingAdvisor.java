package com.advisor;

import java.lang.reflect.Method;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;

import com.role.Waiter;

/**
 * 静态，方法匹配+类名匹配切面。
 * @author yihui
 *
 */
public class GreetingAdvisor extends StaticMethodMatcherPointcutAdvisor{

	@Override
	public boolean matches(Method arg0, Class<?> arg1) {
		return arg0.getName().equals("greetTo");
	}
	
	/*public ClassFilter getClassFilter()
	{
		return new ClassFilter(){
			public boolean matches(Class clazz)
			{
				return Waiter.class.isAssignableFrom(clazz);
			}
		};
	}
	 */
	
	
}

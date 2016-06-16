package com.liu.bean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component
@Scope("prototype")
public class ExampleBean {
	
	public ExampleBean() {
		
		System.out.println("执行ExampBean构造方法");
		
	}
	public void execute()
	{
		System.out.println("执行ExampleBean处理");
	}
}

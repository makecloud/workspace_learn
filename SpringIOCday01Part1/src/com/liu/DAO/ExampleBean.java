package com.liu.DAO;

public class ExampleBean {
	public ExampleBean() {
		System.out.println("执行ExampleBean构造方法");
	}
	public void execute()
	{
		System.out.println("执行Example处理");
	}
	
	public void init()
	{
		System.out.println("执行Example的初始化方法");
	}
	public void destroy()
	{
		System.out.println("执行Example的销毁方法");
	}
	
}

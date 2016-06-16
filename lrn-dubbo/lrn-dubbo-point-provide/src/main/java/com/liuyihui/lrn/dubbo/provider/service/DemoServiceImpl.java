package com.liuyihui.lrn.dubbo.provider.service;

import com.liuyihui.lrn.dubbo.service.DemoService;

public class DemoServiceImpl implements DemoService {
	@Override
	public String sayHello(String name) {
		System.out.println("被调用一次！");
		return "Hello,hello:"+name;
	}
}

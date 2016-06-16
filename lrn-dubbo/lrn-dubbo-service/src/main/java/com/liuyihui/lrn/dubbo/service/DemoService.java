package com.liuyihui.lrn.dubbo.service;
/**
 * 
 * 服务interface，在服务实现端，和服务使用端，均需要放置此interface
 * 
 * 服务实现端，用此interface去注册到zookeeper
 * 服务使用端，用此interface去zookeeper寻找服务
 * 
 * @author Administrator
 *
 */
public interface DemoService {
	public String sayHello(String name);
}

package com.lyh.service;

public class AsynService {
	 public String execute(){
		 System.out.println("正在执行此代码。。");
		 try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return "完成";
	 }
}

package com.lyh.service;

public class AsynService {
	 public String execute(){
		 System.out.println("����ִ�д˴��롣��");
		 try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return "���";
	 }
}

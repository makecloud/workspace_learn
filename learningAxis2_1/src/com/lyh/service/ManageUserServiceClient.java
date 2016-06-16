package com.lyh.service;

import java.rmi.RemoteException;

import org.apache.axis2.AxisFault;

import com.lyh.service.ManageUserServiceStub.*;

public class ManageUserServiceClient {
	public static void main(String[] args) throws RemoteException {
		String target = "http://localhost:8080/axis2/services/ManageUserService";
		ManageUserServiceStub stub = new ManageUserServiceStub(target);
		/**
		 * 调用UserBean模式
		 * RditUser User 是内部静态类，axis2会帮我们进行参数封装
		 */
		EditUser editUser=new ManageUserServiceStub.EditUser();
		User u = new ManageUserServiceStub.User();
		u.setName("jjj");
		u.setEmail("jjjj@jj.com");
		u.setAddress2("beijing");
		u.setId(22);
		editUser.setUser(u);
		//返回值也被封装
		EditUserResponse eur=stub.editUser(editUser);
		User returnUser=eur.get_return();
		//returnUser.getId()在服务器端动态的重置过
		System.out.println("#id:"+returnUser.getId()+"\n#name:"+returnUser.getName()+"\n#address:"+returnUser.getAddress2()+"\n#email:"+returnUser.getEmail()+"_");
		/**
		 * GetUsers 是ManageUserStub的内部类，axis2的代码生成工具会帮我们把参数根据wsdl文件的内容进行封装。
		 */
		ManageUserServiceStub.GetUsers getUsers = new GetUsers();
		getUsers.setJ(3);
		GetUsersResponse gur=stub.getUsers(getUsers);
		System.out.println(gur.get_return());
	}
}

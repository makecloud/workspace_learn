package com.lyh.service;

import java.rmi.RemoteException;

import org.apache.axis2.AxisFault;

import com.lyh.service.ManageUserServiceStub.*;

public class ManageUserServiceClient {
	public static void main(String[] args) throws RemoteException {
		String target = "http://localhost:8080/axis2/services/ManageUserService";
		ManageUserServiceStub stub = new ManageUserServiceStub(target);
		/**
		 * ����UserBeanģʽ
		 * RditUser User ���ڲ���̬�࣬axis2������ǽ��в�����װ
		 */
		EditUser editUser=new ManageUserServiceStub.EditUser();
		User u = new ManageUserServiceStub.User();
		u.setName("jjj");
		u.setEmail("jjjj@jj.com");
		u.setAddress2("beijing");
		u.setId(22);
		editUser.setUser(u);
		//����ֵҲ����װ
		EditUserResponse eur=stub.editUser(editUser);
		User returnUser=eur.get_return();
		//returnUser.getId()�ڷ������˶�̬�����ù�
		System.out.println("#id:"+returnUser.getId()+"\n#name:"+returnUser.getName()+"\n#address:"+returnUser.getAddress2()+"\n#email:"+returnUser.getEmail()+"_");
		/**
		 * GetUsers ��ManageUserStub���ڲ��࣬axis2�Ĵ������ɹ��߻�����ǰѲ�������wsdl�ļ������ݽ��з�װ��
		 */
		ManageUserServiceStub.GetUsers getUsers = new GetUsers();
		getUsers.setJ(3);
		GetUsersResponse gur=stub.getUsers(getUsers);
		System.out.println(gur.get_return());
	}
}

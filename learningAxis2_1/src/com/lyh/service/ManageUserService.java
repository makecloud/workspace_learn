package com.lyh.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.lyh.entity.User;

public class ManageUserService {
	
	public User editUser(User user){
		user.setId(new Random().nextInt(55));
		return user;
	}
	public List<User> getUsers(int j){
		List<User> users = new ArrayList<User>();
		for (int i=0;i<j;i++){
			User user = new User();
			user.setAddress2("中国");
			user.setEmail("aaa@qq.com");
			user.setName("name");
			user.setId(i);
			users.add(user);
		}
		return users;
	}
	public Map<String, User> getUser4Map(int j){
		Map<String , User> users = new HashMap<String, User>();
		for (int i=0;i<j;i++){
			User user = new User(); 	
			user.setAddress2("中国");
			user.setEmail("aaa@qq.com");
			user.setName("name");
			user.setId(i);
			users.put(i+"", user);
		}
		return users;
	}
}

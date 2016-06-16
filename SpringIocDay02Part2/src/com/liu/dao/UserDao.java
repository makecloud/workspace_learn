package com.liu.dao;

import com.liu.entity.User;

public interface UserDao {
	public User findByName(String name);
	public User find(int id);
	public User delete(int id);
	public void update(User user);
}

 package com.deepak.service;

import java.util.List;

import com.deepak.model.User;

public interface UserService {
	
	public User registerUser(User user);
	
	public User findUserById(Integer userId);
	
	public User findUserByEmail(String email);
	
	public User followUser(Integer user1, Integer user2);
	
	public User updateUser(User user);
	
	public List<User> searchUser(String query);
}

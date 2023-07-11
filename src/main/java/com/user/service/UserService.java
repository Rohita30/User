package com.user.service;

import java.util.List;

import com.user.entity.User;

public interface UserService { 
	
	public User addUser(User user);
	public User getUser(Long userId);
	public List<User> getAllUsers();
	public String deleteUser(Long userId);
	public String updateUser(Long userId, User user);


}

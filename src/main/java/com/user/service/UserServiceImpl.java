package com.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.entity.User;
import com.user.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public User addUser(User user) {
		// TODO Auto-generated method stub
		return userRepo.save(user);
	}

	@Override
	public User getUser(Long userId) {
		// TODO Auto-generated method stub
		return userRepo.findById(userId).get();
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return userRepo.findAll();
	}

	@Override
	public String deleteUser(Long userId) {
		// TODO Auto-generated method stub
		userRepo.deleteById(userId);
		return "User Successfully Deleted";
	}

	@Override
	public String updateUser(Long userId, User user) {
		// TODO Auto-generated method stub
		User userFromDb = userRepo.findById(userId).get();
        System.out.println(userFromDb.toString());
        userFromDb.setUserName(user.getUserName());
        userFromDb.setUserEmail(user.getUserEmail());
        userFromDb.setUserPassword(user.getUserPassword());
        userRepo.save(userFromDb);
		return "User Successfully Updated";
	}

}

package com.user.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.user.entity.User;
import com.user.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController { 
	
	@Autowired
	private UserService userServ;
	
	@PostMapping("/addUser")
	public ResponseEntity<?> addUser(@RequestBody User user) {
		return new ResponseEntity<User> (userServ.addUser(user), HttpStatus.CREATED);
	}
	
	@GetMapping("/getUser/{userId}")
	@HystrixCommand(fallbackMethod = "fallback_getUser", commandProperties = {
	        @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
	})
	public ResponseEntity<?> getUser(@PathVariable Long userId){
		return new ResponseEntity<User> (userServ.getUser(userId), HttpStatus.OK);
	}
	
	@GetMapping("/getUser")
	@HystrixCommand(fallbackMethod = "fallback_getAllUsers", commandProperties = {
	        @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
	})
	public ResponseEntity<?> getAllUsers() {
		return new ResponseEntity<List<User>> (userServ.getAllUsers(), HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteUser/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
		return new ResponseEntity<String> (userServ.deleteUser(userId), HttpStatus.OK);
	}
	
	@PutMapping("/updateUser/{userId}")
	public ResponseEntity<?> updateUser(@PathVariable Long userId, @RequestBody User user) {
		userServ.updateUser(userId, user);
		return new ResponseEntity<User> (userServ.getUser(userId), HttpStatus.OK);
	}
	
	private ResponseEntity<?> fallback_getUser(Long userId) {
        // Fallback response when getUser fails
        return new ResponseEntity<String>("Unable to fetch user. Please try again later.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    private ResponseEntity<?> fallback_getAllUsers() {
        // Fallback response when getAllUsers fails
        return new ResponseEntity<String>("Unable to fetch users. Please try again later.", HttpStatus.INTERNAL_SERVER_ERROR);
    }


}

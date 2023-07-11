package com.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.user.exception.UserNotFoundException;

@ControllerAdvice
public class UserExceptionController { 
	
	@ExceptionHandler(value = UserNotFoundException.class)
	   public ResponseEntity<Object> exception(UserNotFoundException exception) {
		   
	      return new ResponseEntity<>("User is not found", HttpStatus.NOT_FOUND);
	      
	   }

}
